package softstone.paper.service.visitorImpl;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import softstone.paper.service.util.PlainSelectUtil;


public class FromItemVisitorImpl implements FromItemVisitor {
	public void visit(ValuesList valuesList) {
	}
	
	public void visit(LateralSubSelect lateralSubSelect) {
	}
	
	public void visit(SubJoin subjoin) {
	}
	
	public void visit(SubSelect subSelect) {
		
	}
	
	public void visit(Table tableName) {
		if(PlainSelectUtil.tableName==null)
			PlainSelectUtil.tableName=tableName.getName();
		else{
			tableName.setName(PlainSelectUtil.tableName);
		}
	}
}
