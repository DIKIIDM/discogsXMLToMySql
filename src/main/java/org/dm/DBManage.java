package org.dm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManage {
    //-------------------------------------------------------------------------
    public static Connection getDBConnect(String dbLogin, String dbPassword, String dbURL) {
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Sql driver not found");
        }
        try {
            con = DriverManager.getConnection(dbURL, dbLogin, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException("Connection to database error, " + e.getMessage());
        }
        return con;
    }
}
