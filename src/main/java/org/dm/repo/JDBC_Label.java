package org.dm.repo;

import org.dm.Core;
import org.dm.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

public class JDBC_Label {
    private static String SQL_INSERT_ONE = "            " +
            "INSERT INTO dc_label                       " +
            "   (   id                                  " +
            "      ,idDC                                " +
            "      ,sName                               " +
            "   )                                       " +
            " VALUES                                    " +
            "   (?, ?, ?)                               ";
    //----------------------------------------------------------------------------------
    public JDBC_Response insert(List<DC_Label> lLabel, Connection con) {
        JDBC_Response response = new JDBC_Response();
        try {
            if (lLabel.size() > 0) {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ONE, Statement.RETURN_GENERATED_KEYS);
                for (DC_Label label: lLabel) {
                    if (label.id == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, label.id);
                    if (label.idDC == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, label.idDC);
                    if (Core.isNull(label.sName)) ps.setNull(3, 1); else ps.setString(3, label.sName);
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
            statement.executeUpdate("TRUNCATE TABLE dc_label");
            statement.executeUpdate("SET foreign_key_checks=1;");
            response.bSuccess = true;
        } catch (Exception e) {
            response.bSuccess = false;
            response.sMessage = e.getMessage();
        }
        return response;
    }
}
