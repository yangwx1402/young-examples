package softstone.paper.sql.visitorImpl;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;
import org.apache.log4j.Logger;

public class SelectItemVisitorImpl implements SelectItemVisitor {
	private final static Logger log = Logger.getLogger(SelectItemVisitorImpl.class);
	public void visit(AllColumns allColumns) {
		// TODO Auto-generated method stub
		System.out.println("**********************allColumns");
		System.out.println("allColumns:"+allColumns.toString());
	}

	public void visit(AllTableColumns allTableColumns) {
		// TODO Auto-generated method stub
		System.out.println("**********************allTableColumns");
		System.out.println("allTableColumns:"+allTableColumns.toString());
	}

	public void visit(SelectExpressionItem selectExpressionItem) {
		// TODO Auto-generated method stub
		ExpressionVisitor evai=new ExpressionVisitorImpl();
		Expression exp=selectExpressionItem.getExpression();
		exp.accept(evai);
		//»ñµÃas name
		Alias alias=selectExpressionItem.getAlias();
		log.debug("selectExpressionItem:"+selectExpressionItem);
		
		/*if(selectExpressionItem.toString().contains(PlainSelectUtil.getRegex()))
		PlainSelectUtil.getPlainSelect().getSelectItems().add(selectExpressionItem);
		*/
		if(alias!=null){
			log.debug("Expression:"+exp+"   alias.getName():"+alias.getName());
			if("sum(c1)".equals(exp.toString())) alias.setName("gg");
		}
	}

}
