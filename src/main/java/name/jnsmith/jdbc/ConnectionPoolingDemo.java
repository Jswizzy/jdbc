package name.jnsmith.jdbc;

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.PooledConnection;



public class ConnectionPoolingDemo {

	public static void main(String[] args) throws SQLException {
		
		PGConnectionPoolDataSource ds = new PGConnectionPoolDataSource();
		

		ds.setServerName("localhost");
		ds.setPortNumber(5432);
		ds.setDatabaseName("rumbl_dev");
		ds.setUser("postgres");
		ds.setPassword("postgres");
		
		PooledConnection pconn = ds.getPooledConnection();
		
		Connection conn = pconn.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * From users");
		
		String format = "%30s %50s\n";
		System.out.format(format,"Name","UserName");
		System.out.format(format,"-------------","-----------------");
		
		while(rs.next()){
			System.out.format(format,rs.getString("name"),rs.getString("username"));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		pconn.close();
	}
}
