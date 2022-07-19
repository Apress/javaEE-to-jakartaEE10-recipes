package org.jakartaeerecipe.chapter06.recipe06_02;

import javax.naming.*;
import java.sql.*;
import javax.sql.DataSource;

public class OracleConnection {
    // Change the values of these variables to match that of your
    // database

    private static String hostname = "myHost";
    private static String port = "1521";
    private static String database = "myDatabase";
    private static String username = "user";
    private static String password = "password";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        String jdbcUrl = "jdbc:oracle:thin:@" + hostname + ":"
                + port + ":" + database;
        conn = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Successfully connected");
        return conn;
    }

    public static Connection getDSConnection() {
        Connection conn = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/OracleConnection");
            conn = ds.getConnection();
            System.out.println("Connection:" + conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        try {
            OracleConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Connection Exception: " + e);
        }

    }
}
