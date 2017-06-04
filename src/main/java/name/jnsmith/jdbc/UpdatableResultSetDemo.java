package name.jnsmith.jdbc;

import java.sql.*;
import java.time.LocalDateTime;

public class UpdatableResultSetDemo {

    public static void main(String[] args) throws Exception {

        try (
                Connection connection = DBUtil.getConnection(DBType.POSTGRES);
                Statement statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE
                );
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users")
        ) {
            resultSet.absolute(5);

            resultSet.updateString("name", "Tom");
            resultSet.updateRow();

            resultSet.moveToInsertRow();
            resultSet.updateInt("id", 539);
            resultSet.updateString("name", "Tommy");
            resultSet.updateString("username", "Tommy");
            resultSet.updateString("password_hash", "Password");
            resultSet.updateTimestamp("inserted_at", java.sql.Timestamp.valueOf(LocalDateTime.now()));
            resultSet.updateTimestamp("updated_at", java.sql.Timestamp.valueOf(LocalDateTime.now()));
            resultSet.insertRow();

            resultSet.beforeFirst();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
    }
}
