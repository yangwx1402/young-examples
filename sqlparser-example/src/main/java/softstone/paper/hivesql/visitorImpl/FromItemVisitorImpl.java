package softstone.paper.hivesql.visitorImpl;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import softstone.paper.hivesql.AnalyzeException;
import softstone.paper.hivesql.util.ResultUtil;

import java.sql.SQLException;
import java.util.List;

public class FromItemVisitorImpl implements FromItemVisitor {
	private final static Logger log = Logger.getLogger(FromItemVisitorImpl.class);

	public void visit(ValuesList valuesList) {
		log.info("valuesList:" + valuesList);
	}

	public void visit(LateralSubSelect lateralSubSelect) {
		log.info("lateralSubSelect:" + lateralSubSelect);
	}

	public void visit(SubJoin subjoin) {
		log.info("subjoin:" + subjoin);
	}

	public void visit(SubSelect subSelect) {
		// TODO Auto-generated method stub
		log.info("subSelect:" + subSelect);
		String aliasName = subSelect.getAlias().getName();

		PlainSelect ps = (PlainSelect) subSelect.getSelectBody();
		// ResultUtil.tablenames.put(aliasName,ps.toString());

		List<String> columnscopy = ResultUtil.copyList(ResultUtil.columns);
		if (!CollectionUtils.isEmpty(columnscopy)) {
			for (String column : columnscopy) {
				if (column.contains(".*")) {
					String tableAlias = column.substring(0, column.indexOf("."));
					if (tableAlias.equalsIgnoreCase(aliasName)) {
						ResultUtil.columns.remove(column);
						if (ps.toString().contains("select") || ps.toString().contains("SELECT")) {
							SelectVisitorImpl selectVisitorImpl = new SelectVisitorImpl();
							ps.accept(selectVisitorImpl);
						}
					}
				}
			}
		}
	}

	public void visit(Table tableName) {
		log.debug("tableName:" + tableName);
		Alias alias = tableName.getAlias();

		List<String> columnscopy = ResultUtil.copyList(ResultUtil.columns);
		if (!CollectionUtils.isEmpty(columnscopy)) {
			for (String column : columnscopy) {
				if (column.equals("*")) {
					descTable(tableName.getFullyQualifiedName(),column);
				} else if (column.contains(".*")) {
					String tableAlias = column.substring(0, column.indexOf("."));
					if(alias==null){
						if(tableAlias.equalsIgnoreCase(tableName.getFullyQualifiedName())){
							descTable(tableName.getFullyQualifiedName(),column);
						}
					}
					else{
						if (tableAlias.equalsIgnoreCase(alias.getName())) {
							descTable(tableName.getFullyQualifiedName(),column);
						}
					}	
				}
			}
		}
	}
	
	public void descTable(String tableName,String column){
		List<String> columns;
		try {
			columns = ResultUtil.descTable(tableName);
			for (String col : columns) {
				ResultUtil.columns.add(col);
			}
		} catch (SQLException e) {
			ResultUtil.analyzeExceptions.add(new AnalyzeException(false, e.getMessage()));
			log.error(e.getMessage());
		}
		ResultUtil.columns.remove(column);
	}
}
