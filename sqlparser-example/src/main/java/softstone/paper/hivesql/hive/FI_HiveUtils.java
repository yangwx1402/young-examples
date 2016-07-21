package softstone.paper.hivesql.hive;

import java.sql.*;


public class FI_HiveUtils {
	/**
	 * 所连接的集群是否为安全版本
	 */
	private static PropertiesResolve pr = new PropertiesResolve();

	private static final String ZK_SERVER_PRINCIPAL = "zookeeper.server.principal";
	private static final String SECURITY_KRB5_CONF = "java.security.krb5.conf";
	private static final String ZK_SASL_CLIENT = "zookeeper.sasl.client";

	static {
		if ("true".equals(pr.readMapByKey("/hivejdbc.properties",
				"isSecureVerson"))) {
			System.setProperty(SECURITY_KRB5_CONF, pr.readMapByKey("/hivejdbc.properties", "SECURITY_KRB5_CONF"));
			System.setProperty(ZK_SERVER_PRINCIPAL, pr.readMapByKey("/hivejdbc.properties", "ZK_SERVER_PRINCIPAL"));
			System.setProperty(ZK_SASL_CLIENT, pr.readMapByKey("/hivejdbc.properties", "ZK_SASL_CLIENT"));
		}
	}
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();

	public static Connection getConnection() {
		Connection conn = connectionHolder.get();
		if (conn == null) {
			try {
				JdbcInfo jdbcInfo = ConfigReader.getInstance().getJdbcInfo();
				Class.forName(jdbcInfo.getDriverName());
				conn = DriverManager.getConnection(jdbcInfo.getUrl(),
						jdbcInfo.getUsername(), jdbcInfo.getPassword());
				connectionHolder.set(conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static Connection getConnection(boolean isSecureVerson){

		String zkQuorum = pr.readMapByKey("/hivejdbc.properties", "zkQuorum");
		// 拼接JDBC URL
		StringBuilder sBuilder = new StringBuilder(pr.readMapByKey(
				"/hivejdbc.properties", "jdbch")).append(zkQuorum).append("/");
		if (isSecureVerson) {
			// 设置新建用户的userPrincipal，此处填写为带域名的用户名，例如创建的用户为user，其userPrincipal则为user@HADOOP.COM。
			// 如果使用Hive组件的系统启动用户hive/hadoop.hadoop.com，则设置userPrincipal为hive/hadoop.hadoop.com。
			String userPrincipal = pr.readMapByKey("/hivejdbc.properties",
					"userPrincipal");
			// 设置客户端的keytab文件路径
			String userKeyTab = pr.readMapByKey("/hivejdbc.properties",
					"userKeyTab");
			sBuilder.append(";serviceDiscoveryMode=")
					.append("zooKeeper")
					.append(";zooKeeperNamespace=")
					.append("hiveserver2;sasl.qop=auth-conf;auth=KERBEROS;principal=")
					.append(pr.readMapByKey("/hivejdbc.properties","userPrincipal"))
					.append(";user.principal=")
					.append(userPrincipal).append(";user.keytab=")
					.append(userKeyTab).append(";");
		} else {
			// 非安全版
			sBuilder.append(";serviceDiscoveryMode=").append("zooKeeper")
					.append(";zooKeeperNamespace=")
					.append("hiveserver2;auth=none");
		}
		String url = sBuilder.toString();

		try {
			Class.forName(pr.readMapByKey("/hivejdbc.properties", "HIVE_DRIVER"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection conn = null;
		try {
			// 获取JDBC连接
			conn = DriverManager.getConnection(url, "", "");

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 关闭JDBC连接
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
