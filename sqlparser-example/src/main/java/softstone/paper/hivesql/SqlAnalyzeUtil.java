package softstone.paper.hivesql;

import net.sf.jsqlparser.parser.CCJSqlParserManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.log4j.Logger;
import softstone.paper.hivesql.util.ResultUtil;
import softstone.paper.hivesql.visitorImpl.StatementVisitorImpl;
import softstone.paper.sql.SqlStringUtil;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class SqlAnalyzeUtil {

	private final static Logger log = Logger.getLogger(SqlAnalyzeUtil.class);
	static CCJSqlParserManager parserManager = new CCJSqlParserManager();
	static ParseDriver pd = new ParseDriver();
	
	
	public static List<String> executeSql(String sql) throws Exception {
		ResultUtil.columns=new ArrayList<String>();
		ResultUtil.analyzeExceptions=new ArrayList<AnalyzeException>();
		checkSql(sql);
		parseSql(sql);//不支持hive的插入语句
		for (int i=0;i<ResultUtil.columns.size();i++) {
			String column=ResultUtil.columns.get(i);
			if(column.contains(".")){
				String newColumn=column.substring(column.indexOf(".")+1);
				ResultUtil.columns.remove(column);
				ResultUtil.columns.add(i, newColumn);
			}
		}
		if(!CollectionUtils.isEmpty(ResultUtil.analyzeExceptions)){
			StringBuilder message=new StringBuilder();
			for (AnalyzeException analyzeException : ResultUtil.analyzeExceptions) {
				message.append(analyzeException.getMessage()+"\n");
			}
			throw new AnalyzeException(false,message.toString());
		}
		return ResultUtil.columns;
	}
	
	public static void checkSql(String sql) throws Exception {
		//1.Hiveparser判断hive sql语法结构是否正确
		System.out.println();
		ASTNode astNode = pd.parse(sql);
	}
	
	public static void parseSql(String sql) throws Exception {
		//解析sql
		StatementVisitorImpl statementVisitorImpl=new StatementVisitorImpl();
		parserManager.parse(new StringReader(sql)).accept(statementVisitorImpl);
	}
	
	
	public static void main(String[] args){
		String sql=SqlStringUtil.subselect;
			for(int i=0;i<args.length;i++){
				String para=args[i];
				String[] paravalue=para.split("|||");
				if("sql".equalsIgnoreCase(paravalue[0])){
					sql=paravalue[1];
				}
			}
			try {
				List<String> selectItems=executeSql(sql);
				log.info("#################################################################################");
				log.info("######################"+selectItems);
				System.out.println(selectItems);
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}

		
		
		
		/*String sql2="select sum(a.a3) as a4,a.a1,a.a2,c.* from aa     a right join (select b.b1,b.b2,d.*from bbb b, data_flow.zrp d)     c";
		String sql3="select a.*,b.* from a,b";
		String sql4="select d.* from (select sum(c.a) as aa,sum(c.b) as bb from ab c) d";
		String sql5="select count(1),count(2) as aa from zrp";
		String sql6="insert into cc partition(aa) select aa,bb from dd"; 
		try {
			List<String> selectItems=executeSql(sql2);
			System.out.println(selectItems);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}*/
		
	}
}
