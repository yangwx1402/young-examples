package softstone.paper.hivesql.hive;


public class ConfigReader {

	private static ConfigReader instance = new ConfigReader();
	
	private JdbcInfo jdbcInfo;
	PropertiesResolve pr = null;
	
	private ConfigReader() {
		pr = new PropertiesResolve();
		jdbcInfo = new JdbcInfo();
		jdbcInfo.setDriverName(pr.readMapByKey("/hivejdbc.properties","driverName"));
		jdbcInfo.setUrl(pr.readMapByKey("/hivejdbc.properties", "url"));
		jdbcInfo.setUsername(pr.readMapByKey("/hivejdbc.properties", "name"));
		jdbcInfo.setPassword(pr.readMapByKey("/hivejdbc.properties", "password")); 
	}
	
	public static ConfigReader getInstance() {
		return instance;
	}
	
	public JdbcInfo getJdbcInfo() {
		return jdbcInfo;
	}
	
}
