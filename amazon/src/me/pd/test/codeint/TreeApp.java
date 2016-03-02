package me.pd.test.codeint;

import me.pd.test.util.TreeUtil;


public class TreeApp {

	public static void main(String[] args) {
		TreeUtil tu=new TreeUtil(22);
		tu.addNode(12);
		tu.addNode(6);
		tu.addNode(5);
		tu.addNode(21);
		tu.addNode(25);
		tu.addNode(23);
		tu.addNode(40);
		tu.addNode(39);
		tu.addNode(24);
		tu.addNode(80);
		tu.addNode(37);
		tu.addNode(2);
		tu.addNode(9);
		tu.addNode(14);
		tu.addNode(10);
		tu.addNode(27);
		tu.addNode(0);
		
		System.out.println("BFS");
		tu.printBFS();
		System.out.println("\nPREORDER");
		tu.printDFS(TreeUtil.traversals.PREORDER);
		System.out.println("\nPOSTORDER");
		tu.printDFS(TreeUtil.traversals.POSTORDER);
		System.out.println("\nDEFAULT");
		tu.printDFS(TreeUtil.traversals.DEFAULT);
		String s="sf";
		
	}
	
}
