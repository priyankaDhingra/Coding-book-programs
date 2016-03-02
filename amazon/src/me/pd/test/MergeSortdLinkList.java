package me.pd.test;


public class MergeSortdLinkList {
	public static void main(String[] args) {
		Node n11 = new Node(5);
		Node n12 = new Node(11);
		Node n13 = new Node(9);
		Node n14 = new Node(11);
		Node n15 = new Node(10);

		n11.next = n12;
		n12.next = n13;
		n13.next = n14;
		n14.next = n15;

		Node n21 = new Node(3);
		Node n22 = new Node(19);
		Node n23 = new Node(12);
		Node n24 = new Node(4);
		Node n25 = new Node(6);
		Node n26 = new Node(11);
		Node n27 = new Node(4);

		n21.next = n22;
		n22.next = n23;
		n23.next = n24;
		n24.next = n25;
		n25.next = n26;
		n26.next = n27;

		Node temp = null;
		Node current = null;
		Node head = temp = n11;
		int count = 0;
		while (temp != null) {
			current = temp;
			temp = temp.next;
			count++;
		}
		temp = current.next = n21;
		while (temp != null) {
			temp = temp.next;
			count++;
		}
		temp = head;
		while (temp != null) {
			System.out.print(temp.value + " ");
			temp = temp.next;
		}
		System.out.println();
		System.out.println("Count : " + count);

		Node finalHead = mergeSort(head);
		temp = finalHead;
		while (temp != null) {
			System.out.print(temp.value + " ");
			temp = temp.next;
		}
		temp = current = finalHead;
		while (temp != null && temp.next != null) {
			boolean considered = false;
			while (temp.value == temp.next.value) {
				temp = temp.next;
				considered = true;
			}
			if (considered) {
				current.next = temp;
			}
			current = temp;
			temp = temp.next;
		}
		System.out.println();
		temp = finalHead;
		while (temp != null) {
			System.out.print(temp.value + " ");
			temp = temp.next;
		}
	}

	public static Node mergeSort(Node headOriginal) {
		if (headOriginal == null || headOriginal.next == null)
			return headOriginal;
		Node a = headOriginal;
		Node b = headOriginal.next;
		while ((b != null) && (b.next != null)) {
			headOriginal = headOriginal.next;
			b = (b.next).next;
		}
		b = headOriginal.next;
		headOriginal.next = null;
		return merge(mergeSort(a), mergeSort(b));

	}

	public static Node merge(Node a, Node b) {
		Node temp = new Node();
		Node head = temp;
		Node c = head;
		while ((a != null) && (b != null)) {
			if (a.value <= b.value) {
				c.next = a;
				c = a;
				a = a.next;
			} else {
				c.next = b;
				c = b;
				b = b.next;
			}
		}
		c.next = (a == null) ? b : a;
		return head.next;
	}
}

public class Node {
	int value;
	Node next;

	public Node(int value) {
		this.value = value;
	}

	public Node() {
	}
}