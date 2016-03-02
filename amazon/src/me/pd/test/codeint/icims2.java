package me.pd.test.codeint;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class icims2 {
	public static void main(String[] args) {

	}
}
class Tree{
	public int x;
	public Tree l;
	public Tree r;
	
	
}
class Solution1 {
	

	public static void solution(Tree t) {
		Tree probTree = null;//(5,(3,null,null),(10,(12,(21,null,null),(20,null,null)),null))
		 int max_path=0;
		 int goingright=0;
		if(probTree==null){
			max_path =-1;
		}
		if(probTree.l==null&&probTree.r==null){
			max_path=0;
		}
		Tree temp1=probTree;
		int path=0;
		Stack<Tree> treeStack = new Stack();
		Map isLeftRight = new HashMap();
		treeStack.push(temp1);
		while (!treeStack.isEmpty()) {
			Tree temp = treeStack.pop();
			if(isLeftRight.containsKey(temp.x)){
				if(goingright==(int)isLeftRight.get(temp.x)){
					path=path+1;
					if(max_path<path){
						max_path=path;
					}else{
						path=0;
						goingright=(int)isLeftRight.get(temp.x);
					}
				}
			}
			System.out.print(temp.x + "->");
			if (temp.r != null)
				treeStack.push(temp.r);
				isLeftRight.put(temp.r.x,0);
			if (temp.l != null)
				treeStack.push(temp.l);
				isLeftRight.put(temp.r.x,1);

		}
	}
	
	
}