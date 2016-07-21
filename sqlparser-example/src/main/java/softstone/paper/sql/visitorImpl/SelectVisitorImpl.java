package softstone.paper.sql.visitorImpl;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.*;
import org.apache.log4j.Logger;
import softstone.paper.sql.util.PlainSelectUtil;

import java.util.List;

public class SelectVisitorImpl implements SelectVisitor {
	private final static Logger log = Logger.getLogger(SelectVisitorImpl.class);
	public void visit(PlainSelect plainSelect) {
		
		//通过访问者模式处理selectItems
        List<SelectItem> selectItems=plainSelect.getSelectItems();
        /*if(selectItems.toString().contains(ReturnSqlUtil.getRegex())){
        	if(ReturnSqlUtil.getPlainSelect().getSelectItems()==null){
        		ReturnSqlUtil.getPlainSelect().setSelectItems(new ArrayList<SelectItem>());
            }
       }*/
        PlainSelectUtil.acceptSelectItem(selectItems);
       
        log.debug("distinct:"+plainSelect.getDistinct());
        
        //通过访问者模式处理fromItem
      	FromItem fromItem=plainSelect.getFromItem();
      	log.info("fromItem:"+fromItem);
      	PlainSelectUtil.acceptFromItem(fromItem);
      	
      	//通过访问者模式处理where
		Expression whereExpression=plainSelect.getWhere();
		
		PlainSelectUtil.acceptExpression(whereExpression);
		
		Expression havingExpression=plainSelect.getHaving();
		PlainSelectUtil.acceptExpression(havingExpression);
		
		List<Expression> groupByExpriessions=plainSelect.getGroupByColumnReferences();
		PlainSelectUtil.acceptGroupByExpressions(groupByExpriessions);

		List<OrderByElement> orderByElements=plainSelect.getOrderByElements();
		PlainSelectUtil.acceptOrderByElement(orderByElements);
		
		//if(selectItems.toString().contains(ReturnSqlUtil.getRegex())){
			List<Join> joins=plainSelect.getJoins();
			//log.info("joins.toString():"+joins.toString());
			PlainSelectUtil.acceptJoin(joins);
       // }
	}

	public void visit(SetOperationList setOpList) {
		// TODO Auto-generated method stub

	}

	public void visit(WithItem withItem) {
		// TODO Auto-generated method stub

	}

}
