package softstone.paper.hivesql.util;

import softstone.paper.hivesql.AnalyzeException;
import softstone.paper.hivesql.hive.HiveUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultUtil {

	public static List<AnalyzeException> analyzeExceptions;
	//public static Map<String,String> tablenames;//有别名的表
	public static List<String> columns; //列名
	//public static List<String> tables; //没有别名的表
	
	//public static List<String> resultColumns=new ArrayList<String>();//列名结果集
	
	/*public static void traverseMap(Map<String,String> map){
		for (String key : map.keySet()) {
			   System.out.println("key= "+ key + " and value= " + map.get(key));
			  }
	}*/
	
	public static List<String> descTable(String tablename) throws SQLException {
		List<String> columns=new ArrayList<String>();
		Connection con = HiveUtils.getConnection();
		Statement stmt = con.createStatement();
		String sql="desc "+tablename;
		
		ResultSet rsc = stmt.executeQuery(sql);
		//StringBuffer sbc = new StringBuffer();
		while (rsc.next()) {
			columns.add(rsc.getString(1).trim());
		}
		return columns;
	}
	
	public static void executeSql(String sql) throws SQLException {
		Connection con = HiveUtils.getConnection();
		Statement stmt = con.createStatement();
		stmt.execute(sql);
	}
	
	public static List<String> copyList(List<String> list){
		List<String> list2=new ArrayList<String>();
		for (String string : list) {
			list2.add(string);
		}
		return list2;
	}
	
	public static void main(String[] args) {
		try {
			List<String> columns=descTable("data_flow.zrp");
			for (String string : columns) {
				System.out.println(string);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
