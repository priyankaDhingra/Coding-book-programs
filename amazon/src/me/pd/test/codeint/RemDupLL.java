package me.pd.test.codeint;

import me.pd.test.util.LinkedListNode;

//Write code to remove duplicates from an unsorted linked list
public class RemDupLL {

	public static void main(String[] args) {
		System.out.println();
		LinkedListNode ll=new LinkedListNode("0");
		for(int i=0;i<10;i++)
			ll.addNode(i, 4+"");
		
		ll.deleteNode("4");
		ll.addNode(4, 4+"");
		ll.addNode(9, 4+"");
		ll.addNode(10, 4+"");
		
		ll.print();
		ll.removeDup();
		System.out.println("");
		ll.print();
	}
}
