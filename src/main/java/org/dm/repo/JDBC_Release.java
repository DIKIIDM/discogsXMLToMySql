package org.dm.repo;

import org.dm.Core;
import org.dm.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

public class JDBC_Release {
    private static String SQL_INSERT_ONE = "            " +
            "INSERT INTO dc_release                     " +
            "   (   id                                  " +
            "      ,idDC                                " +
            "      ,nYear                               " +
            "      ,sTitle                              " +
            "      ,sReleased                           " +
            "      ,sCountry                            " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?, ?, ?)                      ";
    private static String SQL_INSERT_Genre = "          " +
            "INSERT INTO dc_releaseGenre                " +
            "   (   id                                  " +
            "      ,idRelease                           " +
            "      ,idReleaseDC                         " +
            "      ,idGenre                             " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?)                            ";
    private static String SQL_INSERT_Style = "          " +
            "INSERT INTO dc_releaseStyle                " +
            "   (   id                                  " +
            "      ,idRelease                           " +
            "      ,idReleaseDC                         " +
            "      ,idStyle                             " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?)                            ";
    private static String SQL_INSERT_ReleaseArtist = "  " +
            "INSERT INTO dc_releaseArtist               " +
            "   (   id                                  " +
            "      ,idArtist                            " +
            "      ,idArtistDC                          " +
            "      ,idRelease                           " +
            "      ,idReleaseDC                         " +
            "      ,idTrack                             " +
            "      ,sName                               " +
            "      ,sAnv                                " +
            "      ,sJoin                               " +
            "      ,sRole                               " +
            "      ,sTracks                             " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)       ";
    private static String SQL_INSERT_ReleaseExtraArtist = "  " +
            "INSERT INTO dc_releaseExtraArtist          " +
            "   (   id                                  " +
            "      ,idArtist                            " +
            "      ,idArtistDC                          " +
            "      ,idRelease                           " +
            "      ,idReleaseDC                         " +
            "      ,idTrack                             " +
            "      ,sName                               " +
            "      ,sAnv                                " +
            "      ,sJoin                               " +
            "      ,sRole                               " +
            "      ,sTracks                             " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)       ";
    private static String SQL_INSERT_ReleaseTrack = "   " +
            "INSERT INTO dc_releaseTrack                " +
            "   (   id                                  " +
            "      ,idRelease                           " +
            "      ,idReleaseDC                         " +
            "      ,sTitle                              " +
            "      ,sType                               " +
            "      ,sPosition                           " +
            "      ,sDuration                           " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?, ?, ?, ?)                   ";
    private static String SQL_INSERT_ReleaseLabel = "   " +
            "INSERT INTO dc_releaseLabel                " +
            "   (   id                                  " +
            "      ,idRelease                           " +
            "      ,idReleaseDC                         " +
            "      ,idLabel                             " +
            "      ,idLabelDC                           " +
            "      ,sCatno                              " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?, ?, ?)                      ";
    private static String SQL_INSERT_ReleaseFormat = "  " +
            "INSERT INTO dc_releaseFormat               " +
            "   (   id                                  " +
            "      ,idRelease                           " +
            "      ,idReleaseDC                         " +
            "      ,idFormat                            " +
            "      ,nQty                                " +
            "      ,sText                               " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?, ?, ?)                      ";
    //----------------------------------------------------------------------------------
    public JDBC_Response insert(List<DC_Release> lRelease, Connection con) {
        JDBC_Response response = new JDBC_Response();
        int idvReturnedId = 0;
        try {
            if (lRelease.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ONE, Statement.RETURN_GENERATED_KEYS);
                for (DC_Release release : lRelease) {
                    if (release.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, release.id);
                    if (release.idDC == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, release.idDC);
                    if (release.nYear == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, release.nYear);
                    if (Core.isNull(release.sTitle)) ps.setNull(4, Types.VARCHAR); else ps.setString(4, release.sTitle);
                    if (Core.isNull(release.sReleased)) ps.setNull(5, Types.VARCHAR); else ps.setString(5, release.sReleased);
                    if (Core.isNull(release.sCountry)) ps.setNull(6, Types.VARCHAR); else ps.setString(6, release.sCountry);
                   // if (Core.isNull(release.sLabel)) ps.setNull(7, Types.VARCHAR); else ps.setString(7, release.sLabel);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
    //----------------------------------------------------------------------------------
    public JDBC_Response insertReleaseGenre(List<DC_ReleaseGenre> lReleaseGenre, Connection con) {
        JDBC_Response response = new JDBC_Response();
        int idvReturnedId = 0;
        try {
            if (lReleaseGenre.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_Genre, Statement.RETURN_GENERATED_KEYS);
                for (DC_ReleaseGenre releaseGenre : lReleaseGenre) {
                    if (releaseGenre.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, releaseGenre.id);
                    if (releaseGenre.idRelease == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, releaseGenre.idRelease);
                    if (releaseGenre.idReleaseDC == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, releaseGenre.idReleaseDC);
                    if (releaseGenre.idGenre == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, releaseGenre.idGenre);
                   // if (Core.isNull(releaseGenre.sName)) ps.setNull(4, Types.VARCHAR); else ps.setString(4, releaseGenre.sName);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
    //----------------------------------------------------------------------------------
    public JDBC_Response insertReleaseStyle(List<DC_ReleaseStyle> lReleaseStyle, Connection con) {
        JDBC_Response response = new JDBC_Response();
        int idvReturnedId = 0;
        try {
            if (lReleaseStyle.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_Style, Statement.RETURN_GENERATED_KEYS);
                for (DC_ReleaseStyle releaseStyle : lReleaseStyle) {
                    if (releaseStyle.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, releaseStyle.id);
                    if (releaseStyle.idRelease == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, releaseStyle.idRelease);
                    if (releaseStyle.idReleaseDC == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, releaseStyle.idReleaseDC);
                    if (releaseStyle.idStyle == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, releaseStyle.idStyle);
                    //if (Core.isNull(releaseStyle.sName)) ps.setNull(4, Types.VARCHAR); else ps.setString(4, releaseStyle.sName);

                    ps.addBatch();
                }
                ps.executeBatch();
            }
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
    //----------------------------------------------------------------------------------
    public JDBC_Response insertReleaseArtist(List<DC_ReleaseArtist> lReleaseArtist, Connection con) {
        JDBC_Response response = new JDBC_Response();
        int idvReturnedId = 0;
        try {
            if (lReleaseArtist.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ReleaseArtist, Statement.RETURN_GENERATED_KEYS);
                for (DC_ReleaseArtist releaseArtist : lReleaseArtist) {
                    if (releaseArtist.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, releaseArtist.id);
                    if (releaseArtist.idArtist == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, releaseArtist.idArtist);
                    if (releaseArtist.idArtistDC == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, releaseArtist.idArtistDC);
                    if (releaseArtist.idRelease == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, releaseArtist.idRelease);
                    if (releaseArtist.idReleaseDC == null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, releaseArtist.idReleaseDC);
                    if (releaseArtist.idTrack == null) ps.setNull(6, Types.INTEGER); else ps.setInt(6, releaseArtist.idTrack);
                    //if (Core.isNull(releaseArtist.sName)) ps.setNull(7, Types.VARCHAR); else ps.setString(7, releaseArtist.sName);
                    ps.setNull(7, Types.VARCHAR); //data normalization
                    if (Core.isNull(releaseArtist.sAnv)) ps.setNull(8, Types.VARCHAR); else ps.setString(8, releaseArtist.sAnv);
                    if (Core.isNull(releaseArtist.sJoin)) ps.setNull(9, Types.VARCHAR); else ps.setString(9, releaseArtist.sJoin);
                    if (Core.isNull(releaseArtist.sRole)) ps.setNull(10, Types.VARCHAR); else ps.setString(10, releaseArtist.sRole);
                    if (Core.isNull(releaseArtist.sTracks)) ps.setNull(11, Types.VARCHAR); else ps.setString(11, releaseArtist.sTracks);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
    //----------------------------------------------------------------------------------
    public JDBC_Response insertReleaseExtraArtist(List<DC_ReleaseArtist> lReleaseExtraArtist, Connection con) {
        JDBC_Response response = new JDBC_Response();
        int idvReturnedId = 0;
        try {
            if (lReleaseExtraArtist.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ReleaseExtraArtist, Statement.RETURN_GENERATED_KEYS);
                for (DC_ReleaseArtist releaseExtraArtist : lReleaseExtraArtist) {
                    if (releaseExtraArtist.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, releaseExtraArtist.id);
                    if (releaseExtraArtist.idArtist == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, releaseExtraArtist.idArtist);
                    if (releaseExtraArtist.idArtistDC == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, releaseExtraArtist.idArtistDC);
                    if (releaseExtraArtist.idRelease == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, releaseExtraArtist.idRelease);
                    if (releaseExtraArtist.idReleaseDC == null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, releaseExtraArtist.idReleaseDC);
                    if (releaseExtraArtist.idTrack == null) ps.setNull(6, Types.INTEGER); else ps.setInt(6, releaseExtraArtist.idTrack);
                   // if (Core.isNull(releaseExtraArtist.sName)) ps.setNull(7, Types.VARCHAR); else ps.setString(7, releaseExtraArtist.sName);
                    ps.setNull(7, Types.VARCHAR); //data normalization
                    if (Core.isNull(releaseExtraArtist.sAnv)) ps.setNull(8, Types.VARCHAR); else ps.setString(8, releaseExtraArtist.sAnv);
                    if (Core.isNull(releaseExtraArtist.sJoin)) ps.setNull(9, Types.VARCHAR); else ps.setString(9, releaseExtraArtist.sJoin);
                    if (Core.isNull(releaseExtraArtist.sRole)) ps.setNull(10, Types.VARCHAR); else ps.setString(10, releaseExtraArtist.sRole);
                    if (Core.isNull(releaseExtraArtist.sTracks)) ps.setNull(11, Types.VARCHAR); else ps.setString(11, releaseExtraArtist.sTracks);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
    //----------------------------------------------------------------------------------
    public JDBC_Response insertReleaseTrack(List<DC_ReleaseTrack> lReleaseTrack, Connection con) {
        JDBC_Response response = new JDBC_Response();
        int idvReturnedId = 0;
        try {
            if (lReleaseTrack.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ReleaseTrack, Statement.RETURN_GENERATED_KEYS);
                for (DC_ReleaseTrack releaseTrack : lReleaseTrack) {
                    if (releaseTrack.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, releaseTrack.id);
                    if (releaseTrack.idRelease == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, releaseTrack.idRelease);
                    if (releaseTrack.idReleaseDC == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, releaseTrack.idReleaseDC);
                    if (Core.isNull(releaseTrack.sTitle)) ps.setNull(4, Types.VARCHAR); else ps.setString(4, releaseTrack.sTitle);
                    if (Core.isNull(releaseTrack.sType)) ps.setNull(5, Types.VARCHAR); else ps.setString(5, releaseTrack.sType);
                    if (Core.isNull(releaseTrack.sPosition)) ps.setNull(6, Types.VARCHAR); else ps.setString(6, releaseTrack.sPosition);
                    if (Core.isNull(releaseTrack.sDuration)) ps.setNull(7, Types.VARCHAR); else ps.setString(7, releaseTrack.sDuration);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
    //----------------------------------------------------------------------------------
    public JDBC_Response insertReleaseLabel(List<DC_ReleaseLabel> lReleaseLabel, Connection con) {
        JDBC_Response response = new JDBC_Response();
        try {
            if (lReleaseLabel.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ReleaseLabel, Statement.RETURN_GENERATED_KEYS);
                for (DC_ReleaseLabel object : lReleaseLabel) {
                    if (object.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, object.id);
                    if (object.idRelease == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, object.idRelease);
                    if (object.idReleaseDC == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, object.idReleaseDC);
                    if (object.idLabel == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, object.idLabel);
                    if (object.idLabelDC == null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, object.idReleaseDC);
                    if (Core.isNull(object.sCatno)) ps.setNull(6, Types.VARCHAR); else ps.setString(6, object.sCatno);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
    //----------------------------------------------------------------------------------
    public JDBC_Response insertReleaseFormat(List<DC_ReleaseFormat> lReleaseFormat, Connection con) {
        JDBC_Response response = new JDBC_Response();
        try {
            if (lReleaseFormat.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ReleaseFormat, Statement.RETURN_GENERATED_KEYS);
                for (DC_ReleaseFormat object : lReleaseFormat) {
                    if (object.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, object.id);
                    if (object.idRelease == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, object.idRelease);
                    if (object.idReleaseDC == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, object.idReleaseDC);
                    if (object.idFormat == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, object.idFormat);
                    if (object.nQty == null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, object.nQty);
                    if (Core.isNull(object.sText)) ps.setNull(6, Types.VARCHAR); else ps.setString(6, object.sText);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
    //----------------------------------------------------------------------------------
    public JDBC_Response truncateAll(Connection con) {
        JDBC_Response response = new JDBC_Response();
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("SET foreign_key_checks=0;");
            statement.executeUpdate("TRUNCATE TABLE dc_releasegenre");
            statement.executeUpdate("TRUNCATE TABLE dc_releasestyle");
            statement.executeUpdate("TRUNCATE TABLE dc_releaseformat");
            statement.executeUpdate("TRUNCATE TABLE dc_releaseartist");
            statement.executeUpdate("TRUNCATE TABLE dc_releaseextraartist");
            statement.executeUpdate("TRUNCATE TABLE dc_releasetrack");
            statement.executeUpdate("TRUNCATE TABLE dc_releaselabel");
            statement.executeUpdate("TRUNCATE TABLE dc_release");
            statement.executeUpdate("SET foreign_key_checks=1;");
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
}
