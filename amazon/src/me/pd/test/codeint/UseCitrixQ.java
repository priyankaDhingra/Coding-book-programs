package me.pd.test.codeint;

import me.pd.test.QuickInsertQueue;

public class UseCitrixQ {
	 public static void main(String args[]){
		 QuickNextQueue qs=new QuickNextQueue();
		 qs.queue(new Item(2));
		 qs.queue(new Item(3));
		 qs.queue(new Item(1));
		 qs.queue(new Item(5));
		 qs.queue(new Item(6));
		 qs.queue(new Item(2));
		 qs.dequeue();
		 
		 qs.print();
		 
		 
//		 QuickNextQueue qs1=new QuickNextQueue();
//		 qs1.queue(new Item(2));
//		 qs1.queue(new Item(3));
//		 qs1.queue(new Item(4));
//		 qs1.queue(new Item(5));
//		 qs1.queue(new Item(6));
//		 qs1.dequeue();
//		 
//		 qs1.print();
		 
	 }
}
