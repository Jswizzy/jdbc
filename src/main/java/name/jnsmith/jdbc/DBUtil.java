package name.jnsmith.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/rumbl_dev";
    private static final String username = "postgres";
    private static final String password = "postgres";

    public static Connection getConnection(DBType dbType) throws Exception {
        switch (dbType) {
            case POSTGRES:
                return DriverManager.getConnection(dbUrl, username, password);
            default:
                throw new Exception("Error: no driver selected");
        }
    }

    public static void showErrorMessage(SQLException e) {
        System.err.println("Error :" + e.getMessage());
        System.err.println("Error Code: " + e.getErrorCode());
    }
}
