package org.dm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dm.model.*;
import org.dm.repo.JDBC_Artist;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserArtist extends Parser {
    private static final Logger logger = LogManager.getLogger(ParserArtist.class);
    //----------------------------------------------------------------------------------
    public ParserArtist() {
        handler = regHandler();
    }
    //----------------------------------------------------------------------------------
    public ParserArtist(Connection connection) {
        handler = regHandler();
        handler.connection = connection;
    }
    //----------------------------------------------------------------------------------
    @Override
    public XMLHandler regHandler() {
        return new XMLHandlerArtist();
    }
    //----------------------------------------------------------------------------------
    public static class XMLHandlerArtist extends XMLHandler {
        public Map<Integer, Integer> idsArtist = new HashMap<>();
        DC_Artist artist;
        DC_ArtistAlias artistAlias;
        DC_ArtistVariation artistVariation;

        Seq seqArtist = new Seq();
        Seq seqArtistVariation = new Seq();
        Seq seqArtistAlias = new Seq();

        boolean bId = false;
        boolean bName = false;
        boolean bRealName = false;
        boolean bNameVariations = false;
        boolean bNameVariation = false;
        boolean bAliases = false;
        boolean bNameAlias = false;
        boolean bMembers = false;
        boolean bNameMember = false;
        boolean bGroups = false;
        boolean bNameGroup = false;

        private JDBC_Artist jdbcArtist = new JDBC_Artist();

        List<DC_Artist> lArtist = new ArrayList<>();
        List<DC_ArtistVariation> lArtistVariation = new ArrayList<>();
        List<DC_ArtistAlias> lArtistAlias = new ArrayList<>();
        //----------------------------------------------------------------------------------
        @Override
        public void truncate() {
            JDBC_Response respTruncate = jdbcArtist.truncateAll(connection);
            if (!respTruncate.bSuccess) {
                throw new RuntimeException("truncateAll msg:" + respTruncate.sMessage);
            }
        }
        //----------------------------------------------------------------------------------
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("artist")) {
                artist = new DC_Artist();
                artist.id = seqArtist.getNext();
            } else if (qName.equalsIgnoreCase("id") && !bMembers) {
                bId = true;
            } else if (qName.equalsIgnoreCase("name") && bNameVariations) {
                bNameVariation = true;
                artistVariation = new DC_ArtistVariation();
                artistVariation.id = seqArtistVariation.getNext();
                artistVariation.idArtist = artist.id;
                artistVariation.idArtistDC = artist.idDC;
                lArtistVariation.add(artistVariation);
                if (bTest) artist.lVariation.add(artistVariation);
            } else if (qName.equalsIgnoreCase("name") && bAliases) {
                bNameAlias = true;
                artistAlias = new DC_ArtistAlias();
                artistAlias.id = seqArtistAlias.getNext();
                artistAlias.idDC = Integer.parseInt(attributes.getValue("id"));
                artistAlias.idArtist = artist.id;
                artistAlias.idArtistDC = artist.idDC;
                lArtistAlias.add(artistAlias);
                if (bTest) artist.lAlias.add(artistAlias);
            } else if (qName.equalsIgnoreCase("name") && bMembers) {
                bNameMember = true;
            } else if (qName.equalsIgnoreCase("name") && bGroups) {
                bNameGroup = true;
            } else if (qName.equalsIgnoreCase("name")) {
                bName = true;
            } else if (qName.equalsIgnoreCase("realname")) {
                bRealName = true;
            } else if (qName.equalsIgnoreCase("namevariations")) {
                bNameVariations = true;
            } else if (qName.equalsIgnoreCase("aliases")) {
                bAliases = true;
            } else if (qName.equalsIgnoreCase("members")) {
                bMembers = true;
            } else if (qName.equalsIgnoreCase("groups")) {
                bGroups = true;
            }
            stringBuilder = new StringBuilder();
        }
        //----------------------------------------------------------------------------------
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (bId && !bMembers) {
                artist.idDC = Integer.parseInt(stringBuilder.toString());
                bId = false;
            } else if (bName && !bNameVariations && !bNameAlias && !bNameMember && !bNameGroup) {
                artist.setsName(stringBuilder.toString());
                bName = false;
            } else if (bRealName) {
                artist.setsRealName(stringBuilder.toString());
                bRealName = false;
            } else if (bNameVariation && bNameVariations) {
                artistVariation.setsName(stringBuilder.toString());
                artistVariation.init();
                bNameVariation = false;
            } else if (bNameAlias && bAliases) {
                artistAlias.setsName(stringBuilder.toString());
                bNameAlias = false;
            } else if (bNameVariations && qName.equalsIgnoreCase("NameVariations")) {
                bNameVariations = false;
            } else if (bAliases && qName.equalsIgnoreCase("Aliases")) {
                bAliases = false;
            } else if (bNameMember && bMembers && qName.equalsIgnoreCase("name")) {
                bNameMember = false;
            } else if (bNameGroup && bGroups && qName.equalsIgnoreCase("name")) {
                bNameGroup = false;
            } else if (bMembers && qName.equalsIgnoreCase("Members")) {
                bMembers = false;
            } else if (bGroups && qName.equalsIgnoreCase("Groups")) {
                bGroups = false;
            } else if (qName.equalsIgnoreCase("Artist")) {
                lArtist.add(artist);
                idsArtist.put(artist.idDC, artist.id);
                num++;
                if (bId
                        || bName
                        || bRealName
                        || bNameVariations
                        || bNameVariation
                        || bAliases
                        || bNameAlias
                        || bMembers
                        || bNameMember
                        || bGroups
                        || bNameGroup)
                    throw new RuntimeException("artist parse error " + artist.idDC);

                if ((num % 100000 == 0) & !bTest) {
                    writeToDB();
                    clearLists();
                    logger.info(num + " artists done");
                }
            }
        }
        //----------------------------------------------------------------------------------
        @Override
        public void insert() {
            JDBC_Response respInsertArtist = jdbcArtist.insert(lArtist, connection);
            if (!respInsertArtist.bSuccess)
                throw new RuntimeException("Artist msg:" + respInsertArtist.sMessage);

            JDBC_Response respInsertArtistVariation = jdbcArtist.insertVariation(lArtistVariation, connection);
            if (!respInsertArtistVariation.bSuccess)
                throw new RuntimeException("ArtistVariation msg:" + respInsertArtistVariation.sMessage);

            JDBC_Response respInsertArtistAlias = jdbcArtist.insertAlias(lArtistAlias, connection);
            if (!respInsertArtistAlias.bSuccess)
                throw new RuntimeException("ArtistAlias msg:" + respInsertArtistAlias.sMessage);
        }
        //----------------------------------------------------------------------------------
        @Override
        public void clearLists() {
            lArtist = new ArrayList<>();
            lArtistVariation = new ArrayList<>();
            lArtistAlias = new ArrayList<>();
        }
        //----------------------------------------------------------------------------------
        @Override
        public List<? extends DC_Entity> getResult() {
            return lArtist;
        }
    }
    //----------------------------------------------------------------------------------
    public Map<Integer, Integer> getIds() {
        return ((XMLHandlerArtist)getHandler()).idsArtist;
    }
}
