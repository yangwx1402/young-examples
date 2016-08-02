package com.young.java.examples.sqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.WithItem;

import java.util.List;

/**
 * Created by dell on 2016/7/22.
 */
public class SqlParser {

    public static void main(String[] args) throws JSQLParserException {
        String sql = "select a,b,c from d where a=?";
        Statement statement = CCJSqlParserUtil.parse(sql);
        Select select = (Select) statement;
        System.out.println(select.getSelectBody());
        List<WithItem> items = null;
        for(WithItem item:items){
            System.out.println(item.getName());
        }
    }
}
