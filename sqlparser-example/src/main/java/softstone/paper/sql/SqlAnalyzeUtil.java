package softstone.paper.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.log4j.Logger;
import softstone.paper.sql.util.ReturnSqlUtil;
import softstone.paper.sql.visitorImpl.StatementVisitorImpl;

import java.io.StringReader;
import java.util.Map;

public class SqlAnalyzeUtil {

	private final static Logger log = Logger.getLogger(SqlAnalyzeUtil.class);
	static CCJSqlParserManager parserManager = new CCJSqlParserManager();
	static ParseDriver pd = new ParseDriver();
	/**
	 * selectItems analyze
	 * @throws JSQLParserException
	 */
	/*public static void analyzeSelectItems(List<SelectItem> selectItems) throws JSQLParserException{
		SelectItemVisitorImpl selectItemVisitorImpl=new SelectItemVisitorImpl(); {
			public void visit(SelectExpressionItem selectExpressionItem) {
				ExpressionVisitor evai=new SelectItemExpressionVisitorImpl();
				Expression exp=selectExpressionItem.getExpression();
				exp.accept(evai);
				//获得as name
				Alias alias=selectExpressionItem.getAlias();
				//if(alias!=null)
				//System.out.println("alias.getName():"+alias.getName());
			}

			public void visit(AllTableColumns allTableColumns) {
				System.out.println("**********************allTableColumns");
				System.out.println("allTableColumns:"+allTableColumns.toString());
			}
			
			public void visit(AllColumns allColumns) {
				System.out.println("**********************allColumns");
				System.out.println("allColumns:"+allColumns.toString());
			}
		};
		
        for (SelectItem selectItem : selectItems) {
			selectItem.accept(selectItemVisitorImpl);
		}
	}
	*/
	
	/**
	 * fromItems analyze
	 * @throws JSQLParserException 
	 */
	/*public static void analyzeFromItems(FromItem fromItem) throws JSQLParserException{
		FromItemVisitor fromItemVisitor=new FromItemVisitor() {
			
			public void visit(ValuesList valuesList) {
				// TODO Auto-generated method stub
				log.debug("valuesList:"+valuesList);
			}
			
			public void visit(LateralSubSelect lateralSubSelect) {
				// TODO Auto-generated method stub
				log.debug("lateralSubSelect:"+lateralSubSelect);
			}
			
			public void visit(SubJoin subjoin) {
				// TODO Auto-generated method stub
				log.debug("subjoin:"+subjoin);
			}
			
			public void visit(SubSelect subSelect) {
				// TODO Auto-generated method stub
				log.debug("subSelect:"+subSelect);
				log.debug("SelectBody:"+subSelect.getSelectBody());
				log.debug("Alias:"+subSelect.getAlias());
				log.debug("subSelect.selectItems:"+((PlainSelect)subSelect.getSelectBody()).getSelectItems());
				log.debug("subSelect.getFromItems:"+((PlainSelect)subSelect.getSelectBody()).getFromItem());
				log.debug("subSelect.where:"+((PlainSelect)subSelect.getSelectBody()).getWhere());
			}
			
			public void visit(Table tableName) {
				// TODO Auto-generated method stub
				log.debug("tableName:"+tableName);
			}
		};
		fromItem.accept(fromItemVisitor);
	}

	public static void analyzeWhere(Expression whereExpression){
		ExpressionVisitorImpl wevi=new ExpressionVisitorImpl();
		if(whereExpression!=null)
		whereExpression.accept(wevi);
	}*/
	
	public static void main(String[] args){
		String sql=SqlStringUtil.subselect;
		
		try {
			//1.Hiveparser判断hive sql语法结构是否正确
			ASTNode astNode = pd.parse(sql);
			
			
			
			//解析转换hivesql
			Select select = (Select) 
					parserManager.parse(new StringReader(sql));
			StatementVisitorImpl statementVisitorImpl=new StatementVisitorImpl();
			select.accept(statementVisitorImpl);
			log.debug(select.toString());
			
			Map<String,PlainSelect> plainSelects=ReturnSqlUtil.getPlainSelects();
			/*Set<String> tablealias=plainSelects.keySet();
			for (String string : tablealias) {
				log.info("#######################################"+plainSelects.get(string));
			}*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
