package softstone.paper.sql.visitorImpl;

import jline.internal.Log;
import net.sf.jsqlparser.statement.SetStatement;
import net.sf.jsqlparser.statement.StatementVisitor;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.view.CreateView;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.execute.Execute;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.replace.Replace;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.truncate.Truncate;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.log4j.Logger;

public class StatementVisitorImpl implements StatementVisitor {
	private final static Logger log = Logger.getLogger(StatementVisitorImpl.class);
	
	public void visit(Select select) {
		/*boolean flag=ReturnSqlUtil.isFlag();//
		if(!flag){//表不再同一个数据仓库中
			Map<String,String> typeMap=ReturnSqlUtil.getTypeMap();
			Set<String> set=	typeMap.keySet();
			ReturnSqlUtil.setPlainSelects(new HashMap<String,PlainSelect>());
			ReturnSqlUtil.setTablealias(null);
			for (String string : set) {
				//System.out.println(string);
				if(typeMap.get(string).equals("hive")){
					
				}else{
					String regex=string.split(" ")[1]+".";
					ReturnSqlUtil.setRegex(regex);
					ReturnSqlUtil.setPlainSelect(new PlainSelect());
					ReturnSqlUtil.setTablealias(string);
					log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+ReturnSqlUtil.getTablealias());
					
					PlainSelect ps=(PlainSelect)select.getSelectBody();
					log.debug(ps);
					SelectVisitorImpl selectVisitorImpl=new SelectVisitorImpl();
					ps.accept(selectVisitorImpl);
					
					
					ReturnSqlUtil.getPlainSelects().put(ReturnSqlUtil.getTablealias(),ReturnSqlUtil.getPlainSelect());
				}
			}
		}else{//在同一个数据仓库中
			PlainSelect ps=(PlainSelect)select.getSelectBody();
			log.debug(ps);
			SelectVisitorImpl selectVisitorImpl=new SelectVisitorImpl();
			ps.accept(selectVisitorImpl);
		}*/
		PlainSelect ps=(PlainSelect)select.getSelectBody();
		log.debug(ps);
		SelectVisitorImpl selectVisitorImpl=new SelectVisitorImpl();
		ps.accept(selectVisitorImpl);
	}

	public void visit(Delete delete) {
		// TODO Auto-generated method stub
		log.debug("Delete");
	}

	public void visit(Update update) {
		// TODO Auto-generated method stub
		log.debug("Update");
		
	}

	public void visit(Insert insert) {
		// TODO Auto-generated method stub
		log.debug("Insert");
	}

	public void visit(Replace replace) {
		// TODO Auto-generated method stub

	}

	public void visit(Drop drop) {
		// TODO Auto-generated method stub

	}

	public void visit(Truncate truncate) {
		// TODO Auto-generated method stub

	}

	public void visit(CreateIndex createIndex) {
		// TODO Auto-generated method stub

	}

	public void visit(CreateTable createTable) {
		// TODO Auto-generated method stub

	}

	public void visit(CreateView createView) {
		// TODO Auto-generated method stub

	}

	public void visit(Alter alter) {
		// TODO Auto-generated method stub

	}

	public void visit(Statements stmts) {
		// TODO Auto-generated method stub
		Log.debug("Statements");
	}

	public void visit(Execute execute) {
		// TODO Auto-generated method stub

	}

	public void visit(SetStatement set) {
		// TODO Auto-generated method stub

	}

}
