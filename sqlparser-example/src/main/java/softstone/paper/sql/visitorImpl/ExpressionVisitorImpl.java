package softstone.paper.sql.visitorImpl;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SubSelect;
import org.apache.log4j.Logger;
import softstone.paper.sql.util.PlainSelectUtil;

import java.util.List;

public class ExpressionVisitorImpl implements ExpressionVisitor {
	private final static Logger log = Logger.getLogger(ExpressionVisitorImpl.class);
	public void visit(NullValue nullValue) {
		// TODO Auto-generated method stub
		log.info("nullValue:"+nullValue);
	}

	public void visit(Function function) {
		// TODO Auto-generated method stub
		
		log.info("function:"+function);
	}

	public void visit(SignedExpression signedExpression) {
		// TODO Auto-generated method stub
		log.info("signedExpression:"+signedExpression);
	}

	public void visit(JdbcParameter jdbcParameter) {
		// TODO Auto-generated method stub
		log.info("jdbcParameter:"+jdbcParameter);
	}

	public void visit(JdbcNamedParameter jdbcNamedParameter) {
		// TODO Auto-generated method stub
		log.info("jdbcNamedParameter:"+jdbcNamedParameter);
	}

	public void visit(DoubleValue doubleValue) {
		// TODO Auto-generated method stub
		log.info("doubleValue:"+doubleValue);
	}

	public void visit(LongValue longValue) {
		// TODO Auto-generated method stub
		log.info("longValue:"+longValue);
	}

	public void visit(HexValue hexValue) {
		// TODO Auto-generated method stub
		log.info("hexValue:"+hexValue);
	}

	public void visit(DateValue dateValue) {
		// TODO Auto-generated method stub
		log.info("dateValue:"+dateValue);
	}

	public void visit(TimeValue timeValue) {
		// TODO Auto-generated method stub
		log.info("timeValue:"+timeValue);
	}

	public void visit(TimestampValue timestampValue) {
		// TODO Auto-generated method stub
		log.info("timestampValue:"+timestampValue);
	}

	public void visit(Parenthesis parenthesis) {
		// TODO Auto-generated method stub
		log.info("parenthesis:"+parenthesis);
	}

	public void visit(StringValue stringValue) {
		// TODO Auto-generated method stub
		log.info("stringValue:"+stringValue);
	}

	public void visit(Addition addition) {
		// TODO Auto-generated method stub
		log.info("addition:"+addition);
	}

	public void visit(Division division) {
		// TODO Auto-generated method stub
		log.info("division:"+division);
	}

	public void visit(Multiplication multiplication) {
		// TODO Auto-generated method stub
		log.info("multiplication:"+multiplication);
	}

	public void visit(Subtraction subtraction) {
		// TODO Auto-generated method stub
		log.info("subtraction:"+subtraction);
	}

	public void visit(AndExpression andExpression) {
		// TODO Auto-generated method stub
		log.info("andExpression:"+andExpression);
		Expression leftExpression=andExpression.getLeftExpression();
		Expression rightExpression=andExpression.getRightExpression();
		PlainSelectUtil.acceptExpression(leftExpression);
		PlainSelectUtil.acceptExpression(rightExpression);
	}

	public void visit(OrExpression orExpression) {
		// TODO Auto-generated method stub
		log.info("orExpression:"+orExpression);
	}

	public void visit(Between between) {
		// TODO Auto-generated method stub
		log.info("between:"+between);
	}

	public void visit(EqualsTo equalsTo) {
		// TODO Auto-generated method stub
		log.info("equalsTo:"+equalsTo);
		PlainSelectUtil.acceptExpression(equalsTo.getLeftExpression());
		PlainSelectUtil.acceptExpression(equalsTo.getRightExpression());
	}

	public void visit(GreaterThan greaterThan) {
		// TODO Auto-generated method stub
		log.info("greaterThan:"+greaterThan);
	}

	public void visit(GreaterThanEquals greaterThanEquals) {
		// TODO Auto-generated method stub
		log.info("greaterThanEquals:"+greaterThanEquals);
	}

	public void visit(InExpression inExpression) {
		// TODO Auto-generated method stub
		log.info("inExpression:"+inExpression);
	}

	public void visit(IsNullExpression isNullExpression) {
		// TODO Auto-generated method stub
		log.info("isNullExpression:"+isNullExpression);
	}

	public void visit(LikeExpression likeExpression) {
		// TODO Auto-generated method stub
		log.info("likeExpression:"+likeExpression);
	}

	public void visit(MinorThan minorThan) {
		// TODO Auto-generated method stub
		log.info("minorThan:"+minorThan);
	}

	public void visit(MinorThanEquals minorThanEquals) {
		// TODO Auto-generated method stub
		log.info("minorThanEquals:"+minorThanEquals);
	}

	public void visit(NotEqualsTo notEqualsTo) {
		// TODO Auto-generated method stub
		log.info("notEqualsTo:"+notEqualsTo);
	}

	public void visit(Column tableColumn) {
		//if(tableColumn instanceof )
		// TODO Auto-generated method stub
		//获得列明
		//Column col=(Column)selectExpressionItem.getExpression();
		log.info("tableColumn:"+tableColumn);
		String columnName=tableColumn.getColumnName();//列明
		String fullQualifiedName=tableColumn.getFullyQualifiedName();//列明的全名
		log.debug("columnName:"+columnName);
		log.debug("fullQualifiedName:"+fullQualifiedName);
		/*if(tableColumn.toString().contains(ReturnSqlUtil.getRegex())){
			if(ReturnSqlUtil.getPlainSelect().getSelectItems()==null){
				ReturnSqlUtil.getPlainSelect().setSelectItems(new ArrayList<SelectItem>());
            }
			SelectExpressionItem sei=new SelectExpressionItem();
			sei.setExpression(tableColumn);
			ReturnSqlUtil.getPlainSelect().getSelectItems().add(sei);
			System.out.println("PlainSelectUtil.getPlainSelect().getSelectItems():"+ReturnSqlUtil.getPlainSelect().getSelectItems());
		}*/
		SelectExpressionItem sei=new SelectExpressionItem();
		sei.setExpression(tableColumn);
		//ReturnSqlUtil.getPlainSelect().getSelectItems().add(sei);
		//System.out.println("PlainSelectUtil.getPlainSelect().getSelectItems():"+ReturnSqlUtil.getPlainSelect().getSelectItems());
	}

	public void visit(SubSelect subSelect) {
		// TODO Auto-generated method stub
		log.info("subSelect:"+subSelect);
	}

	public void visit(CaseExpression caseExpression) {
		// TODO Auto-generated method stub
		//System.out.println("caseExpression : "+caseExpression.toString());
		List<Expression> list=caseExpression.getWhenClauses();
		for (Expression expression : list) {
			log.debug("expression:"+expression);
		}
		log.debug("getElseExpression:"+caseExpression.getElseExpression());
		log.debug("getSwitchExpression():"+caseExpression.getSwitchExpression());
	}

	public void visit(WhenClause whenClause) {
		// TODO Auto-generated method stub
		log.info("whenClause:"+whenClause);
	}

	public void visit(ExistsExpression existsExpression) {
		// TODO Auto-generated method stub
		log.info("existsExpression:"+existsExpression);
	}

	public void visit(AllComparisonExpression allComparisonExpression) {
		// TODO Auto-generated method stub
		log.info("allComparisonExpression:"+allComparisonExpression);
	}

	public void visit(AnyComparisonExpression anyComparisonExpression) {
		// TODO Auto-generated method stub
		log.info("anyComparisonExpression:"+anyComparisonExpression);
	}

	public void visit(Concat concat) {
		// TODO Auto-generated method stub
		log.info("concat:"+concat);
	}

	public void visit(Matches matches) {
		// TODO Auto-generated method stub
		log.info("matches:"+matches);
	}

	public void visit(BitwiseAnd bitwiseAnd) {
		// TODO Auto-generated method stub
		log.info("bitwiseAnd:"+bitwiseAnd);
	}

	public void visit(BitwiseOr bitwiseOr) {
		// TODO Auto-generated method stub
		log.info("bitwiseOr:"+bitwiseOr);
	}

	public void visit(BitwiseXor bitwiseXor) {
		// TODO Auto-generated method stub
		log.info("bitwiseXor:"+bitwiseXor);
	}

	public void visit(CastExpression cast) {
		// TODO Auto-generated method stub
		log.info("cast:"+cast);
	}

	public void visit(Modulo modulo) {
		// TODO Auto-generated method stub
		log.info("modulo:"+modulo);
	}

	public void visit(AnalyticExpression aexpr) {
		// TODO Auto-generated method stub
		log.info("aexpr:"+aexpr);
	}

	public void visit(WithinGroupExpression wgexpr) {
		// TODO Auto-generated method stub
		log.info("wgexpr:"+wgexpr);
	}

	public void visit(ExtractExpression eexpr) {
		// TODO Auto-generated method stub
		log.info("eexpr:"+eexpr);
	}

	public void visit(IntervalExpression iexpr) {
		// TODO Auto-generated method stub
		log.info("iexpr:"+iexpr);
	}

	public void visit(OracleHierarchicalExpression oexpr) {
		// TODO Auto-generated method stub
		log.info("oexpr:"+oexpr);
	}

	public void visit(RegExpMatchOperator rexpr) {
		// TODO Auto-generated method stub
		log.info("rexpr:"+rexpr);
	}

	public void visit(JsonExpression jsonExpr) {
		// TODO Auto-generated method stub
		log.info("jsonExpr:"+jsonExpr);
	}

	public void visit(RegExpMySQLOperator regExpMySQLOperator) {
		// TODO Auto-generated method stub
		log.info("regExpMySQLOperator:"+regExpMySQLOperator);
	}

	public void visit(UserVariable var) {
		// TODO Auto-generated method stub
		log.info("var:"+var);
	}

	public void visit(NumericBind bind) {
		// TODO Auto-generated method stub
		log.info("bind:"+bind);
	}

	public void visit(KeepExpression aexpr) {
		// TODO Auto-generated method stub
		log.info("aexpr:"+aexpr);
	}

	public void visit(MySQLGroupConcat groupConcat) {
		// TODO Auto-generated method stub
		log.info("groupConcat:"+groupConcat);
	}

	public void visit(RowConstructor rowConstructor) {
		// TODO Auto-generated method stub
		log.info("rowConstructor:"+rowConstructor);
	}

}
