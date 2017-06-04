package name.jnsmith.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {

    public static void main(String[] args) throws Exception {

        try (
                Connection connection = DBUtil.getConnection(DBType.POSTGRES);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users")
        ) {

            while (resultSet.next()) {
                String format = "name: %s\n";
                System.out.format(format, resultSet.getString("name"));
            }

        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        } 
    }
}
