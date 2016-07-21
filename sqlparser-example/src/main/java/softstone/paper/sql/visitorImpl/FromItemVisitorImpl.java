package softstone.paper.sql.visitorImpl;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.apache.log4j.Logger;

public class FromItemVisitorImpl implements FromItemVisitor {
	private final static Logger log = Logger.getLogger(FromItemVisitorImpl.class);
	public void visit(ValuesList valuesList) {
		log.info("valuesList:"+valuesList);
	}
	
	public void visit(LateralSubSelect lateralSubSelect) {
		log.info("lateralSubSelect:"+lateralSubSelect);
	}
	
	public void visit(SubJoin subjoin) {
		log.info("subjoin:"+subjoin);
	}
	
	public void visit(SubSelect subSelect) {
		// TODO Auto-generated method stub
		log.info("subSelect:"+subSelect);
		String aliasName=subSelect.getAlias().getName();
		//ResultUtil.tablenames.put(aliasName,subSelect.getSelectBody().toString());
		/*if(!CollectionUtils.isEmpty(ResultUtil.columns)){
			for(String column:ResultUtil.columns){
				if(column.trim().equalsIgnoreCase(aliasName+".*")){
					ResultUtil.columns.remove(column);
					PlainSelect ps=(PlainSelect) subSelect.getSelectBody();
					SelectVisitorImpl selectVisitorImpl=new SelectVisitorImpl();
					ps.accept(selectVisitorImpl);
				}
			}
		}*/
		/*log.debug("SelectBody:"+subSelect.getSelectBody());
		log.debug("Alias:"+subSelect.getAlias());
		log.debug("subSelect.selectItems:"+((PlainSelect)subSelect.getSelectBody()).getSelectItems());
		log.debug("subSelect.getFromItems:"+((PlainSelect)subSelect.getSelectBody()).getFromItem());
		log.debug("subSelect.where:"+((PlainSelect)subSelect.getSelectBody()).getWhere());*/
		
		/*PlainSelect ps=(PlainSelect) subSelect.getSelectBody();
		SelectVisitorImpl selectVisitorImpl=new SelectVisitorImpl();
		ps.accept(selectVisitorImpl);*/
	}
	
	public void visit(Table tableName) {
		log.debug("tableName:"+tableName);
		String[] tablenames=tableName.getName().split(" ");
		/*if(tablenames.length==2)
			ResultUtil.tablenames.put(tablenames[1], tablenames[0]);
		else
			ResultUtil.tables.add(tableName.getName());*/
		/*if(tableName.toString().trim().equals(ReturnSqlUtil.getTablealias().trim()))
			ReturnSqlUtil.getPlainSelect().setFromItem(tableName);*/
	}
}
