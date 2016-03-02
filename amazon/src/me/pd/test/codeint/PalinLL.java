package me.pd.test.codeint;

import me.pd.test.util.LinkedListNode;

public class PalinLL {

	public static void main(String[] args) {
		System.out.println();
		LinkedListNode ll=new LinkedListNode("0");
			ll.addNode(0, 1+"");
			ll.addNode(1, 2+"");
			ll.addNode(2, 3+"");
			ll.addNode(3, 2+"");
			ll.addNode(4, 1+"");
			//ll.addNode(5, 0+"");
			
//			ll.addNode(3, 3+"");
//			ll.addNode(4, 2+"");
//			ll.addNode(5, 1+"");
//			ll.addNode(6, 0+"");
			ll.print();
			System.out.println(ll.isPalindrome());
	}

}
