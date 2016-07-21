package softstone.paper.sql.visitorImpl;

import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.OrderByVisitor;
import org.apache.log4j.Logger;

public class OrderByVisitorImpl implements OrderByVisitor {
	private final static Logger log = Logger.getLogger(OrderByVisitorImpl.class);
	public void visit(OrderByElement orderBy) {
		
	}

}
