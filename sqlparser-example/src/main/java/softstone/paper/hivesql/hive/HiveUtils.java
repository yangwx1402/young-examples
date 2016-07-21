package softstone.paper.hivesql.hive;

import java.sql.*;

public class HiveUtils {

private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
	
	public static Connection getConnection() {
		Connection conn = connectionHolder.get();
		if (conn == null) {
			try {
				JdbcInfo jdbcInfo = ConfigReader.getInstance().getJdbcInfo();
				Class.forName(jdbcInfo.getDriverName());
				conn = DriverManager.getConnection(jdbcInfo.getUrl(), jdbcInfo.getUsername(), jdbcInfo.getPassword());
				connectionHolder.set(conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		Connection conn = connectionHolder.get();
		if (conn != null) {
			try {
				conn.close();
				connectionHolder.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
	
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
