package org.dm.repo;

import org.dm.Core;
import org.dm.model.DC_Style;
import org.dm.model.JDBC_Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

public class JDBC_Style {
    private static String SQL_INSERT_ONE = "            " +
            "INSERT INTO dc_style                       " +
            "   (   id                                  " +
            "      ,sName                               " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?)                                  ";
    //----------------------------------------------------------------------------------
    public JDBC_Response insert(List<DC_Style> lObject, Connection con) {
        JDBC_Response response = new JDBC_Response();
        try {
            if (lObject.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ONE, Statement.RETURN_GENERATED_KEYS);
                for (DC_Style object: lObject) {
                    if (object.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, object.id);
                    if (Core.isNull(object.sName)) ps.setNull(2, Types.VARCHAR); else ps.setString(2, object.sName);
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
            statement.executeUpdate("TRUNCATE TABLE dc_style");
            statement.executeUpdate("SET foreign_key_checks=1;");
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
}
