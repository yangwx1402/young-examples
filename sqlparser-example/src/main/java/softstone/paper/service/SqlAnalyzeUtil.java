package softstone.paper.service;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import softstone.paper.service.util.PlainSelectUtil;
import softstone.paper.service.visitorImpl.StatementVisitorImpl;

import java.io.StringReader;

public class SqlAnalyzeUtil {

	static CCJSqlParserManager parserManager = new CCJSqlParserManager();

	public static void main(String[] args){
		String sql="CREATE TABLE `workflow_dataset_hive` ("+
  " `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '离线文件id',"+
  " `name` varchar(255) DEFAULT NULL COMMENT '名称',"+
  " `dataset_id` int(11) DEFAULT NULL COMMENT '数据集id',"+
  " `clustr_id` int(11) DEFAULT NULL COMMENT '集群id',"+
  " `database_name` varchar(255) DEFAULT NULL COMMENT '数据库名称',"+
  " `table_name` varchar(255) DEFAULT NULL COMMENT '表名称',"+
  " PRIMARY KEY (`id`)"+
  " ) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8";
		
		try {
			analyzeSql(sql);
		} catch (JSQLParserException e) {
			e.printStackTrace();
		}
		
		/*getTableName(sql);
		getSql("zltzlt",sql);*/
	}
	
	public static void analyzeSql(String sql) throws JSQLParserException{
		Statement st = parserManager.parse(new StringReader(sql));
		StatementVisitorImpl statementVisitorImpl=new StatementVisitorImpl();
		st.accept(statementVisitorImpl);
	}
	
	public static String getTableName(String sql){
		String tableName=null;
		try {
			Statement st = parserManager.parse(new StringReader(sql));
			StatementVisitorImpl statementVisitorImpl=new StatementVisitorImpl();
			st.accept(statementVisitorImpl);
			tableName=PlainSelectUtil.tableName;
		} catch (JSQLParserException e) {
			e.printStackTrace();
		}
		PlainSelectUtil.tableName=null;
		return tableName;
	}
	public static String getSql(String tableName,String sql){
		Statement st = null ;
		PlainSelectUtil.tableName=tableName;
		try {
			st =  parserManager.parse(new StringReader(sql));
			StatementVisitorImpl statementVisitorImpl=new StatementVisitorImpl();
			st.accept(statementVisitorImpl);
		} catch (JSQLParserException e) {
			e.printStackTrace();
		}
		PlainSelectUtil.tableName=null;
		return st.toString();
	}
}
