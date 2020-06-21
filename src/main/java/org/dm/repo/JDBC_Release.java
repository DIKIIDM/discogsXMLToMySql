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
            "   (?, ?, ?, ?, ?, ?);                     ";
    private static String SQL_INSERT_Genre = "          " +
            "INSERT INTO dc_releaseGenre                " +
            "   (   id                                  " +
            "      ,idRelease                           " +
            "      ,idReleaseDC                         " +
            "      ,sName                               " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?);                           ";
    private static String SQL_INSERT_Style = "          " +
            "INSERT INTO dc_releaseStyle                " +
            "   (   id                                  " +
            "      ,idRelease                           " +
            "      ,idReleaseDC                         " +
            "      ,sName                               " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?);                           ";
    private static String SQL_INSERT_ReleaseArtist = "  " +
            "INSERT INTO dc_releaseArtist               " +
            "   (   id                                  " +
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
            "   (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);         ";
    private static String SQL_INSERT_ReleaseExtraArtist = "  " +
            "INSERT INTO dc_releaseExtraArtist          " +
            "   (   id                                  " +
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
            "   (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);         ";
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
            "   (?, ?, ?, ?, ?, ?, ?);                  ";
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
    public JDBC_Response insertReleseGenre(List<DC_ReleaseGenre> lReleaseGenre, Connection con) {
        JDBC_Response response = new JDBC_Response();
        int idvReturnedId = 0;
        try {
            if (lReleaseGenre.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_Genre, Statement.RETURN_GENERATED_KEYS);
                for (DC_ReleaseGenre releaseGenre : lReleaseGenre) {
                    if (releaseGenre.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, releaseGenre.id);
                    if (releaseGenre.idRelease == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, releaseGenre.idRelease);
                    if (releaseGenre.idReleaseDC == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, releaseGenre.idReleaseDC);
                    if (Core.isNull(releaseGenre.sName)) ps.setNull(4, Types.VARCHAR); else ps.setString(4, releaseGenre.sName);
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
                    if (Core.isNull(releaseStyle.sName)) ps.setNull(4, Types.VARCHAR); else ps.setString(4, releaseStyle.sName);
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
                    if (releaseArtist.idArtistDC == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, releaseArtist.idArtistDC);
                    if (releaseArtist.idRelease == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, releaseArtist.idRelease);
                    if (releaseArtist.idReleaseDC == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, releaseArtist.idReleaseDC);
                    if (releaseArtist.idTrack == null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, releaseArtist.idTrack);
                    if (Core.isNull(releaseArtist.sName)) ps.setNull(6, Types.VARCHAR); else ps.setString(6, releaseArtist.sName);
                    if (Core.isNull(releaseArtist.sAnv)) ps.setNull(7, Types.VARCHAR); else ps.setString(7, releaseArtist.sAnv);
                    if (Core.isNull(releaseArtist.sJoin)) ps.setNull(8, Types.VARCHAR); else ps.setString(8, releaseArtist.sJoin);
                    if (Core.isNull(releaseArtist.sRole)) ps.setNull(9, Types.VARCHAR); else ps.setString(9, releaseArtist.sRole);
                    if (Core.isNull(releaseArtist.sTracks)) ps.setNull(10, Types.VARCHAR); else ps.setString(10, releaseArtist.sTracks);
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
                    if (releaseExtraArtist.idArtistDC == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, releaseExtraArtist.idArtistDC);
                    if (releaseExtraArtist.idRelease == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, releaseExtraArtist.idRelease);
                    if (releaseExtraArtist.idReleaseDC == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, releaseExtraArtist.idReleaseDC);
                    if (releaseExtraArtist.idTrack == null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, releaseExtraArtist.idTrack);
                    if (Core.isNull(releaseExtraArtist.sName)) ps.setNull(6, Types.VARCHAR); else ps.setString(6, releaseExtraArtist.sName);
                    if (Core.isNull(releaseExtraArtist.sAnv)) ps.setNull(7, Types.VARCHAR); else ps.setString(7, releaseExtraArtist.sAnv);
                    if (Core.isNull(releaseExtraArtist.sJoin)) ps.setNull(8, Types.VARCHAR); else ps.setString(8, releaseExtraArtist.sJoin);
                    if (Core.isNull(releaseExtraArtist.sRole)) ps.setNull(9, Types.VARCHAR); else ps.setString(9, releaseExtraArtist.sRole);
                    if (Core.isNull(releaseExtraArtist.sTracks)) ps.setNull(10, Types.VARCHAR); else ps.setString(10, releaseExtraArtist.sTracks);
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
    public JDBC_Response truncateAll(Connection con) {
        JDBC_Response response = new JDBC_Response();
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("TRUNCATE TABLE dc_release");
            statement.executeUpdate("TRUNCATE TABLE dc_releaseArtist");
            statement.executeUpdate("TRUNCATE TABLE dc_releaseExtraArtist");
            statement.executeUpdate("TRUNCATE TABLE dc_releaseGenre");
            statement.executeUpdate("TRUNCATE TABLE dc_releaseStyle");
            statement.executeUpdate("TRUNCATE TABLE dc_releaseTrack");
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
}
