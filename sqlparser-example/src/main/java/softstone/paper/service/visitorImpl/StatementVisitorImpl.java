package softstone.paper.service.visitorImpl;


import jline.internal.Log;
import net.sf.jsqlparser.statement.SetStatement;
import net.sf.jsqlparser.statement.StatementVisitor;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
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

import java.util.List;


public class StatementVisitorImpl implements StatementVisitor {
	public void visit(Select select) {
		PlainSelect ps=(PlainSelect)select.getSelectBody();
		SelectVisitorImpl selectVisitorImpl=new SelectVisitorImpl();
		ps.accept(selectVisitorImpl);
	}

	public void visit(Delete delete) {
		
	}

	public void visit(Update update) {
		
	}

	public void visit(Insert insert) {
		
	}

	public void visit(Replace replace) {
		
	}

	public void visit(Drop drop) {
		
	}

	public void visit(Truncate truncate) {
		
	}

	public void visit(CreateIndex createIndex) {
		
	}

	public void visit(CreateTable createTable) {
		System.out.println(createTable.getTable().getName().replaceAll("`", ""));
		List<ColumnDefinition> columns=createTable.getColumnDefinitions();
		for (ColumnDefinition columnDefinition : columns) {
			System.out.println(columnDefinition.getColumnName().replaceAll("`", ""));
			System.out.println(columnDefinition.getColDataType().getDataType());
			List<String> arguments=columnDefinition.getColDataType().getArgumentsStringList();
			if(arguments!=null){
				System.out.println(arguments.get(0));
			}
			List<String> spec=columnDefinition.getColumnSpecStrings();
			System.out.println(spec.get(spec.size()-1).replaceAll("`", ""));
		}
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
