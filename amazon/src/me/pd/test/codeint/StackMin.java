package me.pd.test.codeint;

/*How would you design a stack which,
 *  in addition to push and pop,
 *   also has a function min which returns the minimum element?
 *    Push, pop and min should all operate in O(1) time
 */
public class StackMin {

	public static void main(String[] args) {
		
	}
	void push(){
	
	}
	void pop(){
		
	}
	class Stack{
		int top=-1;
		int []stck;
	}
	class StackNode {

		int min;
		int val;
		public StackNode(int min, int val) {
			this.min = min;
			this.val = val;
		}
	}
}
