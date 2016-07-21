package softstone.paper.hiveparser;

import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;

import java.util.List;

public class HQLParser {

	static ParseDriver pd = new ParseDriver();
	public static void main(String[] args) {
		String sql6="insert into table cc partition(aa) select aa,bb from dd";
		try {
			ASTNode astNode = pd.parse(sql6);
			
			System.out.println();
			
			/*List<Node> childrens = astNode.getChildren();
			for (Node node : childrens) {
				System.out.println("node.getName:" + node.getName());
				System.out.println("node.toString:" + node.toString());
				System.out.println("#########################################");
				printNodes(node);
			}*/
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printNodes(Node node) {
		List<? extends Node> nodes = node.getChildren();
		if (nodes != null) {
			System.out.println("********************************");
			for (Node node2 : nodes) {
				System.out.println(node2.getName());
				System.out.println(node2.toString());
				printNodes(node2);
			}
		}
	}
}
