package softstone.paper.service.util;

import net.sf.jsqlparser.statement.select.FromItem;
import org.apache.log4j.Logger;
import softstone.paper.service.visitorImpl.FromItemVisitorImpl;


public class PlainSelectUtil {
	private final static Logger log = Logger.getLogger(PlainSelectUtil.class);

	public static String tableName=null;


	public static void acceptFromItem(FromItem fromItem) {
		if(fromItem!=null){
			FromItemVisitorImpl fromItemVisitorImpl=new FromItemVisitorImpl();
			fromItem.accept(fromItemVisitorImpl);
		}
	}
}
