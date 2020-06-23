package org.dm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dm.model.*;
import org.dm.repo.JDBC_Genre;
import org.dm.repo.JDBC_Release;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserRelease extends Parser {
    private static final Logger logger = LogManager.getLogger(ParserRelease.class);
    //----------------------------------------------------------------------------------
    public ParserRelease() {
        handler = regHandler();
    }
    //----------------------------------------------------------------------------------
    public ParserRelease(Connection connection) {
        handler = regHandler();
        handler.connection = connection;
    }
    //----------------------------------------------------------------------------------
    @Override
    public org.dm.XMLHandler regHandler() {
        return new XMLHandlerRelease();
    }
    //----------------------------------------------------------------------------------
    public void setIdsLabel(Map<Integer, Integer> ids) {
        ((XMLHandlerRelease)handler).setIdsLabel(ids);
    }
    //----------------------------------------------------------------------------------
    public void setIdsArtist(Map<Integer, Integer> ids) {
        ((XMLHandlerRelease)handler).setIdsArtist(ids);
    }
    //----------------------------------------------------------------------------------
    private static class XMLHandlerRelease extends XMLHandler {
        Map<Integer, Integer> idsLabel;
        Map<Integer, Integer> idsArtist;
        Map<String, Integer> idsGenre = new HashMap<>();

        DC_Release release;
        DC_ReleaseArtist releaseArtist;
        DC_ReleaseArtist releaseExtraArtist;
        DC_ReleaseArtist releaseTrackArtist;
        DC_ReleaseArtist releaseTrackExtraArtist;
        DC_ReleaseStyle releaseStyle;
        DC_ReleaseGenre releaseGenre;
        DC_ReleaseTrack releaseTrack;
        DC_ReleaseLabel releaseLabel;
        
        Seq seqRelease = new Seq();
        Seq seqArtist = new Seq();
        Seq seqExtraArtist = new Seq();
        Seq seqStyle = new Seq();
        Seq seqReleaseGenre = new Seq();
        Seq seqTrack = new Seq();
        Seq seqLabel = new Seq();
        Seq seqGenre = new Seq();

        boolean bArtists = false;
        boolean bArtist = false;
        boolean bArtistId = false;
        boolean bArtistName = false;
        boolean bArtistAnv = false;
        boolean bArtistJoin = false;
        boolean bArtistRole = false;
        boolean bArtistTracks = false;
        boolean bExtraArtists = false;
        boolean bExtraArtist = false;
        boolean bExtraArtistId = false;
        boolean bExtraArtistName = false;
        boolean bExtraArtistAnv = false;
        boolean bExtraArtistJoin = false;
        boolean bExtraArtistRole = false;
        boolean bExtraArtistTracks = false;
        boolean bTracks = false;
        boolean bTrack = false;
        boolean bTrackTitle = false;
        boolean bTrackPosition = false;
        boolean bTrackDuration = false;
        boolean bTrackArtists = false;
        boolean bTrackArtist = false;
        boolean bTrackArtistId = false;
        boolean bTrackArtistName = false;
        boolean bTrackArtistAnv = false;
        boolean bTrackArtistJoin = false;
        boolean bTrackArtistRole = false;
        boolean bTrackArtistTracks = false;
        boolean bTrackExtraArtists = false;
        boolean bTrackExtraArtist = false;
        boolean bTrackExtraArtistId = false;
        boolean bTrackExtraArtistName = false;
        boolean bTrackExtraArtistAnv = false;
        boolean bTrackExtraArtistJoin = false;
        boolean bTrackExtraArtistRole = false;
        boolean bTrackExtraArtistTracks = false;
        boolean bTitle = false;
        boolean bVideo = false;
        boolean bCountry = false;
        boolean bReleased = false;
        boolean bStyle = false;
        boolean bGenre = false;
        boolean bLabels = false;
        boolean bLabel = false;

        private JDBC_Release jdbcRelease = new JDBC_Release();
        private JDBC_Genre jdbcGenre = new JDBC_Genre();

        List<DC_Release> lRelease = new ArrayList<>();
        List<DC_ReleaseArtist> lReleaseArtist = new ArrayList<>();
        List<DC_ReleaseArtist> lReleaseExtraArtist = new ArrayList<>();
        List<DC_ReleaseStyle> lReleaseStyle = new ArrayList<>();
        List<DC_ReleaseGenre> lReleaseGenre = new ArrayList<>();
        List<DC_ReleaseTrack> lReleaseTrack = new ArrayList<>();
        List<DC_ReleaseLabel> lReleaseLabel = new ArrayList<>();
        List<DC_Genre> lGenre = new ArrayList<>();
        //----------------------------------------------------------------------------------
        @Override
        public void truncate() {
            JDBC_Response respTruncateRelease = jdbcRelease.truncateAll(connection);
            if (!respTruncateRelease.bSuccess) {
                throw new RuntimeException("truncate release msg:" + respTruncateRelease.sMessage);
            }
            JDBC_Response respTruncateGenre = jdbcGenre.truncateAll(connection);
            if (!respTruncateGenre.bSuccess) {
                throw new RuntimeException("truncate genre msg:" + respTruncateGenre.sMessage);
            }
        }
        //----------------------------------------------------------------------------------
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("Release")) {
                release = new DC_Release();
                release.id = seqRelease.getNext();
                release.idDC = Integer.parseInt(attributes.getValue("id"));
                lRelease.add(release);
            } else if (qName.equalsIgnoreCase("Artists") && !bTrack) {
                bArtists = true;
            } else if (qName.equalsIgnoreCase("Artists") && bTrack) {
                bTrackArtists = true;
            } else if (qName.equalsIgnoreCase("Artist") && bArtists) {
                bArtist = true;
                releaseArtist = new DC_ReleaseArtist();
                releaseArtist.id = seqArtist.getNext();
                releaseArtist.idRelease = release.id;
                releaseArtist.idReleaseDC = release.idDC;
                lReleaseArtist.add(releaseArtist);
                if (bTest) release.lArtist.add(releaseArtist);
            } else if (qName.equalsIgnoreCase("Artist") && bTrackArtists) {
                bTrackArtist = true;
                releaseTrackArtist = new DC_ReleaseArtist();
                releaseTrackArtist.id = seqArtist.getNext();
                releaseTrackArtist.idRelease = release.id;
                releaseTrackArtist.idReleaseDC = release.idDC;
                releaseTrackArtist.idTrack = releaseTrack.id;
                lReleaseArtist.add(releaseTrackArtist);
                if (bTest) release.lArtist.add(releaseTrackArtist);
            } else if (bArtist && qName.equalsIgnoreCase("id")) {
                bArtistId = true;
            } else if (bArtist && qName.equalsIgnoreCase("name")) {
                bArtistName = true;
            } else if (bArtist && qName.equalsIgnoreCase("anv")) {
                bArtistAnv = true;
            } else if (bArtist && qName.equalsIgnoreCase("join")) {
                bArtistJoin = true;
            } else if (bArtist && qName.equalsIgnoreCase("role")) {
                bArtistRole = true;
            } else if (bTrackArtist && qName.equalsIgnoreCase("id")) {
                bTrackArtistId = true;
            } else if (bTrackArtist && qName.equalsIgnoreCase("name")) {
                bTrackArtistName = true;
            } else if (bTrackArtist && qName.equalsIgnoreCase("anv")) {
                bTrackArtistAnv = true;
            } else if (bTrackArtist && qName.equalsIgnoreCase("join")) {
                bTrackArtistJoin = true;
            } else if (bTrackArtist && qName.equalsIgnoreCase("role")) {
                bTrackArtistRole = true;
            } else if (qName.equalsIgnoreCase("ExtraArtists") && !bTrack) {
                bExtraArtists = true;
            } else if (qName.equalsIgnoreCase("ExtraArtists") && bTrack) {
                bTrackExtraArtists = true;
            } else if (qName.equalsIgnoreCase("Artist") && bExtraArtists) {
                bExtraArtist = true;
                releaseExtraArtist = new DC_ReleaseArtist();
                releaseExtraArtist.id = seqExtraArtist.getNext();
                releaseExtraArtist.idRelease = release.id;
                releaseExtraArtist.idReleaseDC = release.idDC;
                lReleaseExtraArtist.add(releaseExtraArtist);
                if (bTest) release.lExtraArtist.add(releaseExtraArtist);
            } else if (qName.equalsIgnoreCase("Artist") && bTrackExtraArtists) {
                bTrackExtraArtist = true;
                releaseTrackExtraArtist = new DC_ReleaseArtist();
                releaseTrackExtraArtist.id = seqExtraArtist.getNext();
                releaseTrackExtraArtist.idRelease = release.id;
                releaseTrackExtraArtist.idReleaseDC = release.idDC;
                releaseTrackExtraArtist.idTrack = releaseTrack.id;
                lReleaseExtraArtist.add(releaseTrackExtraArtist);
                if (bTest) release.lExtraArtist.add(releaseTrackExtraArtist);
            } else if (bExtraArtist && qName.equalsIgnoreCase("id")) {
                bExtraArtistId = true;
            } else if (bExtraArtist && qName.equalsIgnoreCase("name")) {
                bExtraArtistName = true;
            } else if (bExtraArtist && qName.equalsIgnoreCase("anv")) {
                bExtraArtistAnv = true;
            } else if (bExtraArtist && qName.equalsIgnoreCase("join")) {
                bExtraArtistJoin = true;
            } else if (bExtraArtist && qName.equalsIgnoreCase("role")) {
                bExtraArtistRole = true;
            } else if (bTrackExtraArtist && qName.equalsIgnoreCase("id")) {
                bTrackExtraArtistId = true;
            } else if (bTrackExtraArtist && qName.equalsIgnoreCase("name")) {
                bTrackExtraArtistName = true;
            } else if (bTrackExtraArtist && qName.equalsIgnoreCase("anv")) {
                bTrackExtraArtistAnv = true;
            } else if (bTrackExtraArtist && qName.equalsIgnoreCase("join")) {
                bTrackExtraArtistJoin = true;
            } else if (bTrackExtraArtist && qName.equalsIgnoreCase("role")) {
                bTrackExtraArtistRole = true;
            } else if (qName.equalsIgnoreCase("track") && bTracks) {
                bTrack = true;
                releaseTrack = new DC_ReleaseTrack();
                releaseTrack.id = seqTrack.getNext();
                releaseTrack.idRelease = release.id;
                releaseTrack.idReleaseDC = release.idDC;
                lReleaseTrack.add(releaseTrack);
                if (bTest) release.lTrack.add(releaseTrack);
            } else if (qName.equalsIgnoreCase("video")) {
                bVideo = true;
            } else if (qName.equalsIgnoreCase("title") && !bTrack && !bVideo) {
                bTitle = true;
            } else if (qName.equalsIgnoreCase("title") && bTrack && !bVideo) {
                bTrackTitle = true;
            } else if (qName.equalsIgnoreCase("country")) {
                bCountry = true;
            } else if (qName.equalsIgnoreCase("released")) {
                bReleased = true;
            } else if (qName.equalsIgnoreCase("Style")) {
                bStyle = true;
                releaseStyle = new DC_ReleaseStyle();
                releaseStyle.id = seqStyle.getNext();
                releaseStyle.idRelease = release.id;
                releaseStyle.idReleaseDC = release.idDC;
                lReleaseStyle.add(releaseStyle);
                if (bTest) release.lStyle.add(releaseStyle);
            } else if (qName.equalsIgnoreCase("Genre")) {
                bGenre = true;
                releaseGenre = new DC_ReleaseGenre();
                releaseGenre.id = seqReleaseGenre.getNext();
                releaseGenre.idRelease = release.id;
                releaseGenre.idReleaseDC = release.idDC;
                lReleaseGenre.add(releaseGenre);
                if (bTest) release.lGenre.add(releaseGenre);
            } else if (qName.equalsIgnoreCase("tracklist")) {
                bTracks = true;
            } else if (qName.equalsIgnoreCase("position") && bTrack) {
                bTrackPosition = true;
            } else if (qName.equalsIgnoreCase("duration") && bTrack) {
                bTrackDuration = true;
            } else if (qName.equalsIgnoreCase("Labels")) {
                bLabels = true;
            } else if (qName.equalsIgnoreCase("Label") && bLabels) {
                bLabel = true;
                releaseLabel = new DC_ReleaseLabel();
                releaseLabel.id = seqLabel.getNext();
                releaseLabel.idRelease = release.id;
                releaseLabel.idReleaseDC = release.idDC;
                releaseLabel.idLabelDC =Integer.valueOf(attributes.getValue("id"));
                if (!bTest)
                    releaseLabel.idLabel = idsLabel.get(Integer.valueOf(attributes.getValue("id")));
                releaseLabel.setsCatno(attributes.getValue("catno"));
                lReleaseLabel.add(releaseLabel);
                if (bTest)
                    release.lLabel.add(releaseLabel);
            }
            stringBuilder = new StringBuilder();
        }
        //----------------------------------------------------------------------------------
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (bArtists && (qName.equalsIgnoreCase("Artists"))) {
                bArtists = false;
            } if (bTrackArtists && (qName.equalsIgnoreCase("Artists"))) {
                bTrackArtists = false;
            } if (bArtist && (qName.equalsIgnoreCase("Artist"))) {
                bArtist = false;
            } else if (bArtist && bArtistId) {
                bArtistId = false;
                releaseArtist.idArtistDC = Integer.parseInt(stringBuilder.toString());
                if (!bTest)
                    releaseArtist.idArtist = idsArtist.get(releaseArtist.idArtistDC);
            } else if (bArtist && bArtistName) {
                bArtistName = false;
                releaseArtist.setsName(stringBuilder.toString());
            } else if (bArtist && bArtistAnv) {
                bArtistAnv = false;
                releaseArtist.setsAnv(stringBuilder.toString());
            } else if (bArtist && bArtistJoin) {
                bArtistJoin = false;
                releaseArtist.setsJoin(stringBuilder.toString());
            } else if (bArtist && bArtistRole) {
                bArtistRole = false;
                releaseArtist.setsRole(stringBuilder.toString());
            } else if (bArtist && bArtistTracks) {
                bArtistTracks = false;
                releaseArtist.setsTracks(stringBuilder.toString());
            } if (bTrackArtist && (qName.equalsIgnoreCase("Artist"))) {
                bTrackArtist = false;
            } else if (bTrackArtist && bTrackArtistId) {
                bTrackArtistId = false;
                releaseTrackArtist.idArtistDC = Integer.parseInt(stringBuilder.toString());
                if (!bTest)
                    releaseTrackArtist.idArtist = idsArtist.get(releaseTrackArtist.idArtistDC);
            } else if (bTrackArtist && bTrackArtistName) {
                bTrackArtistName = false;
                releaseTrackArtist.setsName(stringBuilder.toString());
            } else if (bTrackArtist && bTrackArtistAnv) {
                bTrackArtistAnv = false;
                releaseTrackArtist.setsAnv(stringBuilder.toString());
            } else if (bTrackArtist && bTrackArtistJoin) {
                bTrackArtistJoin = false;
                releaseTrackArtist.setsJoin(stringBuilder.toString());
            } else if (bTrackArtist && bTrackArtistRole) {
                bTrackArtistRole = false;
                releaseTrackArtist.setsRole(stringBuilder.toString());
            } else if (bTrackArtist && bTrackArtistTracks) {
                bTrackArtistTracks = false;
                releaseTrackArtist.setsTracks(stringBuilder.toString());
            }  if (bExtraArtists && (qName.equalsIgnoreCase("ExtraArtists"))) {
                bExtraArtists = false;
            }  if (bTrackExtraArtists && (qName.equalsIgnoreCase("ExtraArtists"))) {
                bTrackExtraArtists = false;
            } if (bExtraArtist && (qName.equalsIgnoreCase("Artist"))) {
                bExtraArtist = false;
            } else if (bExtraArtist && bExtraArtistId) {
                bExtraArtistId = false;
                releaseExtraArtist.idArtistDC = Integer.parseInt(stringBuilder.toString());
                if (!bTest)
                    releaseExtraArtist.idArtist = idsArtist.get(releaseExtraArtist.idArtistDC);
            } else if (bExtraArtist && bExtraArtistName) {
                bExtraArtistName = false;
                releaseExtraArtist.setsName(stringBuilder.toString());
            } else if (bExtraArtist && bExtraArtistAnv) {
                bExtraArtistAnv = false;
                releaseExtraArtist.setsAnv(stringBuilder.toString());
            } else if (bExtraArtist && bExtraArtistJoin) {
                bExtraArtistJoin = false;
                releaseExtraArtist.setsJoin(stringBuilder.toString());
            } else if (bExtraArtist && bExtraArtistRole) {
                bExtraArtistRole = false;
                releaseExtraArtist.setsRole(stringBuilder.toString());
            } else if (bExtraArtist && bExtraArtistTracks) {
                bExtraArtistTracks = false;
                releaseExtraArtist.setsTracks(stringBuilder.toString());
            } if (bTrackExtraArtist && (qName.equalsIgnoreCase("Artist"))) {
                bTrackExtraArtist = false;
            } else if (bTrackExtraArtist && bTrackExtraArtistId) {
                bTrackExtraArtistId = false;
                releaseTrackExtraArtist.idArtistDC = Integer.parseInt(stringBuilder.toString());
                if (!bTest)
                    releaseTrackExtraArtist.idArtist = idsArtist.get(releaseTrackExtraArtist.idArtistDC);
            } else if (bTrackExtraArtist && bTrackExtraArtistName) {
                bTrackExtraArtistName = false;
                releaseTrackExtraArtist.setsName(stringBuilder.toString());
            } else if (bTrackExtraArtist && bTrackExtraArtistAnv) {
                bTrackExtraArtistAnv = false;
                releaseTrackExtraArtist.setsAnv(stringBuilder.toString());
            } else if (bTrackExtraArtist && bTrackExtraArtistJoin) {
                bTrackExtraArtistJoin = false;
                releaseTrackExtraArtist.setsJoin(stringBuilder.toString());
            } else if (bTrackExtraArtist && bTrackExtraArtistRole) {
                bTrackExtraArtistRole = false;
                releaseTrackExtraArtist.setsRole(stringBuilder.toString());
            } else if (bTrackExtraArtist && bTrackExtraArtistTracks) {
                bTrackExtraArtistTracks = false;
                releaseTrackExtraArtist.setsTracks(stringBuilder.toString());
            } else if (bTitle && !bTrack && !bVideo) {
                bTitle = false;
                release.setsTitle(stringBuilder.toString());
            } else if (bTrack && (qName.equalsIgnoreCase("track")) && bTracks) {
                bTrack = false;
            } else if (bVideo && (qName.equalsIgnoreCase("video"))) {
                bVideo = false;
            } else if (bCountry) {
                bCountry = false;
                release.setsCountry(stringBuilder.toString());
            } else if (bReleased) {
                bReleased = false;
                release.setsReleased(stringBuilder.toString());
            } else if (bStyle) {
                bStyle = false;
                releaseStyle.setsName(stringBuilder.toString());
            } else if (bGenre) {
                bGenre = false;
                // releaseGenre.setsName(stringBuilder.toString());
                if (!idsGenre.containsKey(stringBuilder.toString())) {
                    DC_Genre genre = new DC_Genre();
                    genre.id = seqGenre.getNext();
                    genre.setsName(stringBuilder.toString());
                    lGenre.add(genre);
                    releaseGenre.idGenre = genre.id;
                    idsGenre.put(stringBuilder.toString(), genre.id);
                } else {
                    releaseGenre.idGenre = idsGenre.get(stringBuilder.toString());
                }
            } else if (bTracks && qName.equalsIgnoreCase("tracklist")) {
                bTracks = false;
            } else if (bTracks && qName.equalsIgnoreCase("track")) {
                bTrack = false;
            } else if (bTrackTitle && bTrack) {
                bTrackTitle = false;
                releaseTrack.setsTitle(stringBuilder.toString());
            } else if (bTrackPosition && bTrack) {
                bTrackPosition = false;
                releaseTrack.setsPosition(stringBuilder.toString());
            } else if (bTrackDuration && bTrack) {
                bTrackDuration = false;
                releaseTrack.setsDuration(stringBuilder.toString());
            }  if (bLabels && (qName.equalsIgnoreCase("labels"))) {
                bLabels = false;
            }  if (bLabel && (qName.equalsIgnoreCase("label"))) {
                bLabel = false;
            }
            if (qName.equalsIgnoreCase("Release")) {
                if ((bArtists)
                        || (bArtist)
                        || (bArtistId)
                        || (bArtistName)
                        || (bArtistAnv)
                        || (bArtistJoin)
                        || (bArtistRole)
                        || (bArtistTracks)
                        || (bExtraArtists)
                        || (bExtraArtist)
                        || (bExtraArtistId)
                        || (bExtraArtistName)
                        || (bExtraArtistAnv)
                        || (bExtraArtistJoin)
                        || (bExtraArtistRole)
                        || (bExtraArtistTracks)
                        || (bTracks)
                        || (bTrack)
                        || (bTrackTitle)
                        || (bTrackPosition)
                        || (bTrackDuration)
                        || (bTrackArtists)
                        || (bTrackArtist)
                        || (bTrackArtistId)
                        || (bTrackArtistName)
                        || (bTrackArtistAnv)
                        || (bTrackArtistJoin)
                        || (bTrackArtistRole)
                        || (bTrackArtistTracks)
                        || (bTrackExtraArtists)
                        || (bTrackExtraArtist)
                        || (bTrackExtraArtistId)
                        || (bTrackExtraArtistName)
                        || (bTrackExtraArtistAnv)
                        || (bTrackExtraArtistJoin)
                        || (bTrackExtraArtistRole)
                        || (bTrackExtraArtistTracks)
                        || (bTitle)
                        || (bVideo)
                        || (bCountry)
                        || (bReleased)
                        || (bStyle)
                        || (bGenre)
                        || (bLabels)
                        || (bLabel)
                        ) {
                    throw new RuntimeException("release parse error " + release.idDC);
                }
                num++;
                if ((num % 100000 == 0) & !bTest) {
                    writeToDB();
                    clearLists();
                    logger.info(num + " releases done");
                }
            }
        }
        //----------------------------------------------------------------------------------
        @Override
        public void insert() {
            JDBC_Response respInsertGenre = jdbcGenre.insert(lGenre, connection);
            if (!respInsertGenre.bSuccess) {
                throw new RuntimeException("Genre msg:" + respInsertGenre.sMessage);
            }
            JDBC_Response respInsertRelease = jdbcRelease.insert(lRelease, connection);
            if (!respInsertRelease.bSuccess) {
                throw new RuntimeException("Release msg:" + respInsertRelease.sMessage);
            }
            JDBC_Response respInsertReleaseGenre = jdbcRelease.insertReleaseGenre(lReleaseGenre, connection);
            if (!respInsertReleaseGenre.bSuccess) {
                throw new RuntimeException("ReleaseGenre msg:" + respInsertReleaseGenre.sMessage);
            }
            JDBC_Response respInsertStyle = jdbcRelease.insertReleaseStyle(lReleaseStyle, connection);
            if (!respInsertStyle.bSuccess) {
                throw new RuntimeException("ReleaseStyle msg:" + respInsertStyle.sMessage);
            }
            JDBC_Response respInsertArtist = jdbcRelease.insertReleaseArtist(lReleaseArtist, connection);
            if (!respInsertArtist.bSuccess) {
                throw new RuntimeException("ReleaseArtist msg:" + respInsertArtist.sMessage);
            }
            JDBC_Response respInsertExtraArtist = jdbcRelease.insertReleaseExtraArtist(lReleaseExtraArtist, connection);
            if (!respInsertExtraArtist.bSuccess) {
                throw new RuntimeException("ReleaseExtraArtist msg:" + respInsertExtraArtist.sMessage);
            }
            JDBC_Response respInsertTrack = jdbcRelease.insertReleaseTrack(lReleaseTrack, connection);
            if (!respInsertTrack.bSuccess) {
                throw new RuntimeException("ReleaseTrack msg:" + respInsertTrack.sMessage);
            }
            JDBC_Response respInsertLabel = jdbcRelease.insertReleaseLabel(lReleaseLabel, connection);
            if (!respInsertLabel.bSuccess) {
                throw new RuntimeException("ReleaseLabel msg:" + respInsertLabel.sMessage);
            }
        }
        //----------------------------------------------------------------------------------
        @Override
        public void clearLists() {
            lRelease = new ArrayList<>();
            lReleaseTrack = new ArrayList<>();
            lReleaseGenre = new ArrayList<>();
            lReleaseStyle = new ArrayList<>();
            lReleaseArtist = new ArrayList<>();
            lReleaseExtraArtist = new ArrayList<>();
            lReleaseLabel = new ArrayList<>();
            lGenre = new ArrayList<>();
        }
        //----------------------------------------------------------------------------------
        @Override
        public List<? extends DC_Entity> getResult() {
            return lRelease;
        }
        //----------------------------------------------------------------------------------
        public void setIdsLabel(Map<Integer, Integer> ids) {
            this.idsLabel = ids;
        }
        //----------------------------------------------------------------------------------
        public void setIdsArtist(Map<Integer, Integer> ids) {
            this.idsArtist = ids;
        }
    }
}