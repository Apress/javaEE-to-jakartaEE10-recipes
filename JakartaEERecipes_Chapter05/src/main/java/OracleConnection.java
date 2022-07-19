import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {


    // Use the TNS Alias name along with the TNS_ADMIN - For ATP and ADW
    // final static String DB_URL="jdbc:oracle:thin:@dbname_alias?TNS_ADMIN=/Users/test/wallet_dbname";
    final static String DB_USER = "hr";
    final static String DB_PASSWORD = "hr";
    final static String CONN_FACTORY_CLASS_NAME="oracle.jdbc.pool.OracleDataSource";
    private static final String DB_URL = "jdbc:oracle:thin:" +
            "@(description=" +
            "(address=(host=adb.ap-hyderabad-1.oraclecloud.com)(port=1522)(protocol=tcp))" +
            "(connect_data=(service_name=g1899853859ed26_mydatabase_low.adb.oraclecloud.com))" +
            ")";

    // host: adb.ap-hyderabad-1.oraclecloud.com
    public static void main(String[] args) {
        Connection conn = null;
        String jdbcUrl = DB_URL;
        try {
            conn = DriverManager.getConnection(jdbcUrl);
            System.out.println("Successfully connected");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("inside catch...");
        }
    }
}
