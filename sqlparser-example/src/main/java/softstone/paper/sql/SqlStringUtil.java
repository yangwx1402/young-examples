package softstone.paper.sql;

public class SqlStringUtil {
	public final static String subselect="select y,sum(c1) as m1,sum(c2) as m2,sum(c3) as m3,sum(c4) as m4,sum(c5) as m5,sum(c6) as m6,"+
			"sum(c7) as m7,sum(c8) as m8,sum(c9) as m9,sum(c10) as m10,sum(c11) as m11,sum(c12) as m12"+
			" from"+
			" (select"+
			 " y,"+
			  "case m when 1 then c else 0 end as c1,"+
			  "case m when 2 then c else 0 end as c2,"+
			  "case m when 3 then c else 0 end as c3,"+
			  "case m when 4 then c else 0 end as c4,"+
			  "case m when 5 then c else 0 end as c5,"+
			  "case m when 6 then c else 0 end as c6,"+
			  "case m when 7 then c else 0 end as c7,"+
			  "case m when 8 then c else 0 end as c8,"+
			  "case m when 9 then c else 0 end as c9,"+
			  "case m when 10 then c else 0 end as c10,"+
			  "case m when 11 then c else 0 end as c11,"+
			  "case m when 12 then c else 0 end as c12"+
			" from"+
			 " ("+
			  " select y,m,count(s_date) as c from"+
			   " ("+
			    " select datepart(year,convert(DateTime,s_date)) as y,"+
			     " datepart(month,convert(DateTime,s_date)) as m ,"+
			     " s_date from exam"+
			   ")  as T1"+
			   " group by T1.y,T1.m"+
			  " )"+
			" as T2"+
			" ) as T3"+
			" where T3.y<'2015-10.28'"+
			" group by T3.y"+
			" order by T3.y";
	public final static String innerjoin="select s.ymd, s.symbol, s.price_close, d.dividend as mm"+
		    " from stocks s join dividend d on s.ymd = d.ymd and s.symbol = d.symbol"+
		    " where s.symbol = 'APPL'";
	public final static String outjoin="select s.ymd, s.symbol, s.price_close, d.dividend"+
		    " from stocks s left outer join dividend d on s.ymd = d.ymd and s.symbol = d.symbol"+
		    " where s.symbol = 'APPL'";
	public final static String innerjoins="select a.ymd, a.price_close, b.price_close,c.price_close"+
		    " from stocks a join stocks b on a.ymd = b.ymd"+
		    "               join stocks c on a.ymd = c.ymd"+
		    " where a.symbol = 'APPL' and b.symbol = 'IBM' and c.symbol = 'GE'";
	public final static String qiantao="from("+
    " select upper(name),deductions['Federal Taxes']  as fed_taxs,"+
    " round(salary * (1 - deductions['Federal Taxes'])) as salary_minus_fed_taxes from employees;"+
    " ) e"+
    " select e.name, e.salary_minus_fed_taxes"+
    " where e.salary_minus_fed_taxes > 7000";
	public final static String joinsub="select a.ymd, a.price_close, b.price_close,c.price_close"+
		    " from stocks a join stocks b on a.ymd = b.ymd"+
		    " join (select ymd from tablename1) as c on a.ymd = c.ymd"+
		    " where a.symbol = 'APPL' and b.symbol = 'IBM' and c.symbol = 'GE'";
}
