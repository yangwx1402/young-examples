package com.young.java.examples.sqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;

import java.io.StringReader;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class JsqlParserExample {
    public static void main(String[] args) throws JSQLParserException
    {
        // TODO Auto-generated method stub
        String statement= "select users.username,users.age,users.gender,groups.group_name from users users,groups groups where users.user_id = ? and users.group_id=groups.id";
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select=(Select) parserManager.parse(new StringReader(statement));

        PlainSelect plain=(PlainSelect)select.getSelectBody();
        List selectitems=plain.getSelectItems();
        System.out.println(selectitems.size());
        for(int i=0;i<selectitems.size();i++) {
            Expression expression=((SelectExpressionItem) selectitems.get(i)).getExpression();
            System.out.println("Expression:-"+expression);
            Column col=(Column)expression;
            System.out.println(col.getTable()+","+col.getColumnName());
        }
        System.out.println(plain.getJoins().get(0));
        System.out.println(plain.getWhere().toString());
        System.out.println(plain.getSelectItems());
        System.out.println(plain.getIntoTables());
    }
}
