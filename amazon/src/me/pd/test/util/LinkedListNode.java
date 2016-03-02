package me.pd.test.util;

import java.util.Stack;

 class Node {
	String data;
	 Node next;
	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}
	
}
public class LinkedListNode{
	private static Node head;
	public LinkedListNode(String data) {
		 head=new Node(data, null);
	}
	public  void addNode(int pos, String data){
		if(head==null)
			head=new Node(data, null);
		else if(pos>length())
			System.out.println("position is more than length of the list!!");
		
		else{
			int i=0;
			Node tempPtr=head;
			while (i!=pos){
			 tempPtr=tempPtr.next;i++;
			}
			 if(pos==length())
					tempPtr.next=new Node(data,null);
			 else
				 tempPtr.next=new Node(data,tempPtr.next);
		}
	}
	public  void deleteNode(String data){
		if(head==null)
			System.out.println("No data!!");
		else{
			Node tempPtr=head;
			while (!(data.equals(tempPtr.next.data)||tempPtr.next.next==null)){
			 tempPtr=tempPtr.next;
			}
			 if(data.equals(tempPtr.next.data))
					tempPtr.next=tempPtr.next.next;
			
		}
	}
	public int length(){
		Node tempPtr=head;
		int i=0;
		while(tempPtr.next!=null){
			tempPtr=tempPtr.next;
			i++;
		}
		return i;
		
	}
	public void removeDup(){
		if(head==null){
			System.out.println("List is empty!!");
			return;
		}
		//int size=1;
		if(head.next==null)
			System.out.println(head.data);
		else{
			Node temPtr1=head;
			
			while(temPtr1!=null){
				Node temPtr2=temPtr1;
				while(temPtr2.next!=null){
					if(temPtr1.data==temPtr2.next.data){
						temPtr2.next=temPtr2.next.next;
					}else{
						temPtr2=temPtr2.next;
					}
				}
				temPtr1=temPtr1.next;
			}
		}
		
		
	}
	public boolean isPalindrome(){
		if(head==null){
			System.out.println("No List!!");
			return false;
		}else{
			Stack stck=new Stack();
			Node tempPtr=head;
			Node jmpPtr=head;
			while(!(tempPtr==null||tempPtr.next==null)){
				tempPtr=tempPtr.next.next;
				stck.push(jmpPtr.data);
				jmpPtr=jmpPtr.next;
			}
			if(tempPtr!=null){
				jmpPtr=jmpPtr.next;
			}
			while(jmpPtr.next!=null){
				String x=stck.pop().toString();
				if(x!=jmpPtr.data)return false;
				jmpPtr=jmpPtr.next;
			}
		}
		return true;
	}
	public void print() {
		Node tempPtr=head;
		if(head==null) return;
		while(tempPtr!=null){
			System.out.print(tempPtr.data+"->");
			tempPtr=tempPtr.next;
		}
	}
}