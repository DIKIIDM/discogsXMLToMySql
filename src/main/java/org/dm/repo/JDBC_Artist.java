package org.dm.repo;

import org.dm.Core;
import org.dm.model.DC_Artist;
import org.dm.model.DC_ArtistAlias;
import org.dm.model.DC_ArtistVariation;
import org.dm.model.JDBC_Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

public class JDBC_Artist {
    private static String SQL_INSERT_ONE = "            " +
            "INSERT INTO dc_artist                      " +
            "   (   id                                  " +
            "      ,idDC                                " +
            "      ,sName                               " +
            "      ,sRealName                           " +
            "      ,sNameShort                          " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?, ?);                        ";
    private static String SQL_INSERT_NAME_VARIATION = " " +
            "INSERT INTO dc_artistVariation             " +
            "   (   id                                  " +
            "      ,idArtist                            " +
            "      ,idArtistDC                          " +
            "      ,sName                               " +
            "      ,sNameShort                          " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?, ?);                        ";
    private static String SQL_INSERT_ALIAS = "          " +
            "INSERT INTO dc_artistAlias                 " +
            "   (   id                                  " +
            "      ,idArtist                            " +
            "      ,idArtistDC                          " +
            "      ,idDC                                " +
            "      ,sName                               " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?, ?, ?);                        ";
    //----------------------------------------------------------------------------------
    public JDBC_Response insert(List<DC_Artist> lArtist, Connection con) {
        JDBC_Response response = new JDBC_Response();
        try {
            if (lArtist.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ONE, Statement.RETURN_GENERATED_KEYS);
                for (DC_Artist artist: lArtist) {
                    if (artist.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, artist.id);
                    if (artist.idDC == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, artist.idDC);
                    if (Core.isNull(artist.sName)) ps.setNull(3, 1); else ps.setString(3, artist.sName);
                    if (Core.isNull(artist.sRealName)) ps.setNull(4, 1); else ps.setString(4, artist.sRealName);
                    if (Core.isNull(artist.sNameShort)) ps.setNull(5, 1); else ps.setString(5, artist.sNameShort);
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
    public JDBC_Response insertVariation(List<DC_ArtistVariation> lArtistVariation, Connection con) {
        JDBC_Response response = new JDBC_Response();
        try {
            if (lArtistVariation.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_NAME_VARIATION);
                for (int j = 0; j < lArtistVariation.size(); j++) {
                    DC_ArtistVariation artistVariation = lArtistVariation.get(j);
                    if (artistVariation.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, artistVariation.id);
                    if (artistVariation.idArtist == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, artistVariation.idArtist);
                    if (artistVariation.idArtistDC == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, artistVariation.idArtistDC);
                    if (Core.isNull(artistVariation.sName)) ps.setNull(4, Types.VARCHAR); else ps.setString(4, artistVariation.sName);
                    if (Core.isNull(artistVariation.sNameShort)) ps.setNull(5, 1); else ps.setString(5, artistVariation.sNameShort);
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
    public JDBC_Response insertAlias(List<DC_ArtistAlias> lArtistAlias, Connection con) {
        JDBC_Response response = new JDBC_Response();
        try {
            if (lArtistAlias.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ALIAS);
                for (int j = 0; j < lArtistAlias.size(); j++) {
                    DC_ArtistAlias artistAlias = lArtistAlias.get(j);
                    if (artistAlias.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, artistAlias.id);
                    if (artistAlias.idArtist == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, artistAlias.idArtist);
                    if (artistAlias.idArtistDC == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, artistAlias.idArtistDC);
                    if (artistAlias.idDC == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, artistAlias.idDC);
                    if (Core.isNull(artistAlias.sName)) ps.setNull(5, Types.VARCHAR); else ps.setString(5, artistAlias.sName);
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
            statement.executeUpdate("TRUNCATE TABLE dc_artist");
            statement.executeUpdate("TRUNCATE TABLE dc_artistAlias");
            statement.executeUpdate("TRUNCATE TABLE dc_artistVariation");
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
}
