package softstone.paper.sql.util;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.SelectItem;
import org.apache.log4j.Logger;
import softstone.paper.sql.visitorImpl.ExpressionVisitorImpl;
import softstone.paper.sql.visitorImpl.FromItemVisitorImpl;
import softstone.paper.sql.visitorImpl.OrderByVisitorImpl;
import softstone.paper.sql.visitorImpl.SelectItemVisitorImpl;

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
			log.debug("Expression:"+expression);
			ExpressionVisitorImpl expressionVisitorImpl=new ExpressionVisitorImpl();
			expression.accept(expressionVisitorImpl);
		}
	}

	public static void acceptGroupByExpressions(List<Expression> groupByExpriessions) {
		if(groupByExpriessions!=null)
		for (Expression groupByexpression : groupByExpriessions) {
			acceptExpression(groupByexpression);
		}
	}

	public static void acceptOrderByElement(List<OrderByElement> orderByElements) {
		if(orderByElements!=null)
		for (OrderByElement orderByElement : orderByElements) {
			OrderByVisitorImpl orderByVisitorImpl=new OrderByVisitorImpl();
			orderByElement.accept(orderByVisitorImpl);
		}
	}

	public static void acceptJoin(List<Join> joins) {
		// TODO Auto-generated method stub
		if(joins!=null)
			for (Join join : joins) {
				Expression expression=join.getOnExpression();
				PlainSelectUtil.acceptExpression(expression);
				
				FromItem fromItem=join.getRightItem();
				PlainSelectUtil.acceptFromItem(fromItem);
				
				List<Column> columns=join.getUsingColumns();
				PlainSelectUtil.acceptColumn(columns);
				
				
				log.debug("join.getOnExpression():"+join.getOnExpression());
				log.debug("join.getRightItem():"+join.getRightItem());
				log.debug("join.getUsingColumns():"+join.getUsingColumns());
			}
	}

	private static void acceptColumn(List<Column> columns) {
		if(columns!=null)
		for (Column column : columns) {
			ExpressionVisitorImpl expressionVisitorImpl=new ExpressionVisitorImpl();
			column.accept(expressionVisitorImpl);
		}
	}

}
