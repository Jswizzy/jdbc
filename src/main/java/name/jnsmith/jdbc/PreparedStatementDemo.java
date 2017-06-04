package name.jnsmith.jdbc;

import java.sql.*;

public class PreparedStatementDemo {

    public static void main(String[] args) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection(DBType.POSTGRES);
            pstmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE username = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            prepareStatement(pstmt, "Tommy");

        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        } finally {

            if (!(conn == null)) {
                conn.close();
            }

        }
    }

    private static void prepareStatement(PreparedStatement pstmt, String username) throws SQLException {
        ResultSet rs;
        pstmt.setString(1, username);

        rs = pstmt.executeQuery();

        String format = "name: %s username: %s\n";

        while (rs.next()) {
            System.out.printf(format, rs.getString("name"), rs.getString("username"));
        }

        rs.last();
    }
}
