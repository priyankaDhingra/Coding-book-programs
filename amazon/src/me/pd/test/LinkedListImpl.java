package me.pd.test;

class Node {
	String data;
	Node link;

	public Node(String data, Node link) {
		super();
		this.data = data;
		this.link = link;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getLink() {
		return link;
	}

	public void setLink(Node link) {
		this.link = link;
	}

}

public class LinkedListImpl {

	private static Node front;

	public static void main(String[] args) {
		//printList();
		addNode("one");
		addNode("two");
		addNode("2");
		addNode("3");
		addNode("4");
		addNode("three");
		addNode("5");
		addNode("6");
		delNode();
		addNode("7");
		addNode("8");
		addNode("9");
		addNode("9");
		addNode("9");
		
		printList();
	}

	private static void printList() {
		if (front == null) {
			System.out.println("List Empty");
		} else {
			Node temp = front;
			while (temp.getLink() != null) {
				System.out.print(","+temp.getData());
				temp = temp.getLink();
			}
			System.out.print(temp.getData()+"\n");
		}
	}

	public static void delNode() {
		if (front == null) {
			System.out.println("List Empty");
		} else {
			Node temp = front;
			while (temp.getLink().getLink() != null) {
				temp = temp.getLink();
				System.out.print("Value: "+temp.getData());
			}
			System.out.println();
			temp.setLink(null);
		}

	}

	public static void addNode(String data) {
		if (front == null) {
			front = new Node(data, null);

		} else {
			Node temp = front;
			while (temp.getLink() != null) {
				temp = temp.getLink();
				System.out.print("Adding: "+temp.getData());
			}
			System.out.println();
			temp.setLink(new Node(data, null));
		}
	}

}
