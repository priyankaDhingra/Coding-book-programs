import java.util.LinkedList;
import java.util.Queue;

public class Stack {
	Queue Q1;
	public Stack(){
		Q1=new LinkedList();
	}
public void push(int data) {
		Q1.add(data);
	}
public int pop() {
		int data = 0;
		try {
			Queue Q2 = new LinkedList() ;
			if(Q1.isEmpty()) {
				return -1;
			}
			while(!Q1.isEmpty()) {
				Q2.add(Q1.remove());
			}
			data = (int) Q2.remove();
			while(!Q2.isEmpty()) {
				Q1.add(Q2.remove());
			}
			return data;
		}
	catch(Exception e) {
		System.out.println("Exception"+ e);
	}
		return -1;
}
public static void main(String []args) {
//push into the stack
	Stack s = new Stack();
	s.push(10);
	s.push(20);

//pop
	System.out.println(s.pop());
	System.out.println(s.pop());

//check pop when Q is empty
	System.out.println(s.pop());

}

}

