package org.dm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dm.model.*;
import org.dm.repo.JDBC_Artist;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParserArtist {
    private static final Logger logger = LogManager.getLogger(ParserArtist.class);
    public Connection connection;
    //----------------------------------------------------------------------------------
    public ParserArtist() {
    }
    //----------------------------------------------------------------------------------
    public void parser(Connection connection, File file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            handler.connection = connection;
            parser.parse(file, handler);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    //----------------------------------------------------------------------------------
    public List<DC_Artist> parser(String sXML) {
        List<DC_Artist> lArtist;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            handler.bTest = true;
            parser.parse(new ByteArrayInputStream(sXML.getBytes()), handler);
            lArtist = handler.lArtist;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return lArtist;
    }
    //----------------------------------------------------------------------------------
    private static class XMLHandler extends DefaultHandler {
        Connection connection;
        Boolean bTest = false;
        DC_Artist artist;
        DC_ArtistAlias artistAlias;
        DC_ArtistVariation artistVariation;

        DZ_Seq seqArtist = new DZ_Seq();
        DZ_Seq seqArtistVariation = new DZ_Seq();
        DZ_Seq seqArtistAlias = new DZ_Seq();

        int num = 0;
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

        private StringBuilder stringBuilder = null;
        private JDBC_Artist jdbcArtist = new JDBC_Artist();

        List<DC_Artist> lArtist = new ArrayList<>();
        List<DC_ArtistVariation> lArtistVariation = new ArrayList<>();
        List<DC_ArtistAlias> lArtistAlias = new ArrayList<>();
        //----------------------------------------------------------------------------------
        @Override
        public void startDocument() throws SAXException {
            try {
                if (!bTest) {
                    JDBC_Response respTruncate = jdbcArtist.truncateAll(connection);
                    if (!respTruncate.bSuccess) {
                        throw new RuntimeException("truncateAll msg:" + respTruncate.sMessage);
                    }
                    connection.commit();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        //----------------------------------------------------------------------------------
        @Override
        public void endDocument() throws SAXException {
            if (!bTest) writeToDB();
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
                artist.init();
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
                    throw new RuntimeException("artist parser error " + artist.idDC);

                if ((num % 100000 == 0) & !bTest) {
                    writeToDB();
                    logger.info(num + " artists done");
                }
            }
        }
        //----------------------------------------------------------------------------------
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            stringBuilder.append(new String(ch, start, length));
        }
        //----------------------------------------------------------------------------------
        private void writeToDB() {
            JDBC_Response respInsertArtist = jdbcArtist.insert(lArtist, connection);
            if (!respInsertArtist.bSuccess)
                throw new RuntimeException("Artist msg:" + respInsertArtist.sMessage);

            JDBC_Response respInsertArtistVariation = jdbcArtist.insertVariation(lArtistVariation, connection);
            if (!respInsertArtistVariation.bSuccess)
                throw new RuntimeException("ArtistVariation msg:" + respInsertArtistVariation.sMessage);

            JDBC_Response respInsertArtistAlias = jdbcArtist.insertAlias(lArtistAlias, connection);
            if (!respInsertArtistAlias.bSuccess)
                throw new RuntimeException("ArtistAlias msg:" + respInsertArtistAlias.sMessage);

            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
            lArtist = new ArrayList<>();
            lArtistVariation = new ArrayList<>();
            lArtistAlias = new ArrayList<>();
        }
    }
}
