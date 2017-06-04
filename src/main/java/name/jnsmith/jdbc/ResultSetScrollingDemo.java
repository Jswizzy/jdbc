package name.jnsmith.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetScrollingDemo {


    public static void main(String[] args) throws Exception {

        try (
                Connection connection = DBUtil.getConnection(DBType.POSTGRES);
                Statement statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
                );
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users")
        ) {

            while (resultSet.next()) {
                String format = "name: %s\n";
                System.out.format(format, resultSet.getString("name"));
            }

            resultSet.absolute(3);

            System.out.println(resultSet.getString("name"));

            resultSet.relative(-1);

            System.out.println(resultSet.getString("name"));

            resultSet.next();

            System.out.println(resultSet.getString("name"));

            resultSet.previous();

            System.out.println(resultSet.getString("name"));

        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
    }
}

