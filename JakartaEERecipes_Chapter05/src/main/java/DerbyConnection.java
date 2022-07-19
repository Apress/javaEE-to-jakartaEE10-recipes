import javax.naming.NamingException;
import java.sql.*;

public class DerbyConnection {
    private static final String hostname = "localhost";
    private static final String port = "1527";
    private static final String database = "acme";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        String jdbcUrl = "jdbc:derby://" + hostname + ":"
                + port + "/" + database;
        conn = DriverManager.getConnection(jdbcUrl);
        System.out.println("Successfully connected");
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        getConnection();
        // createConnection();
    }

    private static String tableName = "BOOK_AUTHOR";

    private static void createConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String dbUrl = "jdbc:derby://" + hostname + ":"
                + port + "/" + database;
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            try (Statement stmt = conn.createStatement()) {
                int id = 1;
//                String bookName = "Jakarta EE 10 Recipes";
//                String createQuery = "create table " + tableName + "(id int, title varchar(40))";
//                stmt.execute(createQuery);

//                String query = "insert into " + tableName + " values (" + id + ",'" + bookName + "')";
//                stmt.execute(query);

                ResultSet results = stmt.executeQuery("select * from " + tableName);
                ResultSetMetaData rsmd = results.getMetaData();
                int numberCols = rsmd.getColumnCount();

                for(int i=1; i<=numberCols; i++) {
                    System.out.print(rsmd.getColumnLabel(i) + "\t\t");
                }

                System.out.println("\n-------------------------------------------------");

                while(results.next()) {
                    int rid = results.getInt(1);
                    String firstName = results.getString(2);
                    String lastName = results.getString(3);
                    String bio = results.getString(4);
                    System.out.println(rid + "\t\t" + firstName + "\t\t" + lastName + "\t\t" + bio);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
