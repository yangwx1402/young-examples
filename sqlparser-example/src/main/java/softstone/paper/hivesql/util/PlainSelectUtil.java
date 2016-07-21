package softstone.paper.hivesql.util;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.SelectItem;
import org.apache.log4j.Logger;
import softstone.paper.hivesql.visitorImpl.ExpressionVisitorImpl;
import softstone.paper.hivesql.visitorImpl.FromItemVisitorImpl;
import softstone.paper.hivesql.visitorImpl.SelectItemVisitorImpl;

import java.util.List;


public class PlainSelectUtil {
	private final static Logger log = Logger.getLogger(PlainSelectUtil.class);
	
	

	public static void acceptSelectItem(List<SelectItem> selectItems) {
		if(selectItems!=null)
		for (SelectItem selectItem : selectItems) {
			SelectItemVisitorImpl selectItemVisitorImpl=new SelectItemVisitorImpl();
			selectItem.accept(selectItemVisitorImpl);
			
		}
	}

	public static void acceptFromItem(FromItem fromItem) {
		if(fromItem!=null){
			FromItemVisitorImpl fromItemVisitorImpl=new FromItemVisitorImpl();
			fromItem.accept(fromItemVisitorImpl);
		}
	}

	public static void acceptExpression(Expression expression) {
		if(expression!=null){
			//log.debug("############:"+expression);
			ExpressionVisitorImpl expressionVisitorImpl=new ExpressionVisitorImpl();
			expression.accept(expressionVisitorImpl);
		}
	}

	public static void acceptJoin(List<Join> joins) {
		// TODO Auto-generated method stub
		if(joins!=null)
			for (Join join : joins) {
				/*Expression expression=join.getOnExpression();
				System.out.println("join.expression"+expression);
				PlainSelectUtil.acceptExpression(expression);*/
				
				FromItem fromItem=join.getRightItem();
				//System.out.println("join.fromItem"+fromItem);
				PlainSelectUtil.acceptFromItem(fromItem);
				
				
				/*List<Column> columns=join.getUsingColumns();
				PlainSelectUtil.acceptColumn(columns);
				
				
				log.debug("join.getOnExpression():"+join.getOnExpression());
				log.debug("join.getRightItem():"+join.getRightItem());
				log.debug("join.getUsingColumns():"+join.getUsingColumns());*/
			}
	}
	
}
