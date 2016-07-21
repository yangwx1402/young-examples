package softstone.paper.service.visitorImpl;

import net.sf.jsqlparser.statement.select.*;
import softstone.paper.service.util.PlainSelectUtil;


public class SelectVisitorImpl implements SelectVisitor {
	public void visit(PlainSelect plainSelect) {
        //通过访问者模式处理fromItem
      	FromItem fromItem=plainSelect.getFromItem();
      	PlainSelectUtil.acceptFromItem(fromItem);
	}

	public void visit(SetOperationList setOpList) {

	}

	public void visit(WithItem withItem) {

	}

}
