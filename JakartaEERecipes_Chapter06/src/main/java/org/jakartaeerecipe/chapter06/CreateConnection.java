package org.jakartaeerecipe.chapter06;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class CreateConnection {

    static Properties props = new Properties();
    static String hostname = null;
    static String port = null;
    static String database = null;
    static String username = null;
    static String password = null;
    static String jndi = null;

    public CreateConnection() {

    }

//    public static void loadProperties() {
//        // Return if the host has already been loaded
//        if(hostname != null){
//            return;
//        }
//
//        try(InputStream in = Files.newInputStream(FileSystems.getDefault().getPath(System.getProperty("user.dir")
//                + File.separator + "db_props.properties"))) {
//            // Looks for properties file in the root of the src directory in Netbeans project
//
//            System.out.println(FileSystems.getDefault().getPath(System.getProperty("user.dir")
//                    + File.separator + "db_props.properties"));
//            props.load(in);
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        hostname = props.getProperty("host_name");
//        port = props.getProperty("port_number");
//        database = props.getProperty("db_name");
//        username = props.getProperty("username");
//        password = props.getProperty("password");
//        jndi = props.getProperty("jndi");
//        System.out.println(hostname);
//    }

    /**
     * Demonstrates obtaining a connection via DriverManager
     *
     * @return database connection
     * @throws SQLException in case of failure to connect to Database
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        String jdbcUrl = "jdbc:derby://localhost:1527/acme";
        conn = DriverManager.getConnection(jdbcUrl);
        System.out.println("Successfully connected");
        return conn;
    }

    /**
     * Demonstrates obtaining a connection via a DataSource object
     *
     * @return
     */
    public static Connection getDSConnection() {
        Connection conn = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(jndi);
            conn = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        // CreateConnection.loadProperties();
        System.out.println("Beginning connection..");
        try (Connection conn = CreateConnection.getConnection()) {
            //performDbTask();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
