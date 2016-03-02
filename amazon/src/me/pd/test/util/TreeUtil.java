package me.pd.test.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Tree {
	Tree left;
	Tree right;
	int data;

	public Tree(int data) {
		this.data = data;
	}

}

public class TreeUtil {
	Tree root;

	public static enum traversals {
		DEFAULT, PREORDER, POSTORDER
	}

	public TreeUtil(int data) {
		root = new Tree(data);
	}

	public void addNode(int data) {
		if (root == null) {
			root.data = data;
		}
		Tree temp = root;
		traverse(temp, data);

	}

	private void traverse(Tree temp, int data) {
		if (temp.data > data) {
			if (temp.left == null)
				temp.left = new Tree(data);
			else
				traverse(temp.left, data);
		} else if (temp.data < data) {
			if (temp.right == null)
				temp.right = new Tree(data);
			else
				traverse(temp.right, data);

		}

	}

	public void printBFS() {
		Queue<Tree> treeQ = new LinkedList();
		treeQ.add(root);
		while (!treeQ.isEmpty()) {
			Tree temp = treeQ.remove();
			System.out.print(temp.data + "->");
			if (temp.left != null)
				treeQ.add(temp.left);
			if (temp.right != null)
				treeQ.add(temp.right);
		}

	}

	public void printDFS(traversals traversal_type) {
		switch (traversal_type) {
		case DEFAULT: // inorder traversal
			Stack<Tree> treeStack_D = new Stack();
			treeStack_D.push(root);
			traverse_inorder(root, treeStack_D);
			break;
		case PREORDER: // PREORDER traversal
			
			Stack<Tree> treeStack = new Stack();
			treeStack.push(root);
			while (!treeStack.isEmpty()) {
				Tree temp = treeStack.pop();
				System.out.print(temp.data + "->");
				if (temp.right != null)
					treeStack.push(temp.right);
				if (temp.left != null)
					treeStack.push(temp.left);

			}break;
		case POSTORDER: // POSTORDER traversal
			Stack<Tree> treeStack_Post = new Stack();
			treeStack_Post.push(root);
			traverse_postorder(root, treeStack_Post);
			break;
		}

	}

	private void traverse_inorder(Tree root, Stack<Tree> treeStack) {

		Tree temp = root;

		while (temp.left != null) {
			treeStack.push(temp.left);
			temp = temp.left;
		}
		while (!treeStack.isEmpty()) {
			Tree element = treeStack.pop();
			System.out.print(element.data + "->");

			if (element.right != null) {
				treeStack.push(element.right);
				traverse_inorder(element.right, treeStack);
			}

		}
	}

	private void traverse_postorder(Tree root,Stack<Tree> treeStack1) {
		Tree temp = root;
		Stack<Tree> treeStack = new Stack();
		treeStack.push(root);
		while (temp.left != null) {
			treeStack.push(temp.left);
			temp = temp.left;
		}
		while (!treeStack.isEmpty()) {
			Tree element = treeStack.pop();
			
			if (element.right != null) {
				traverse_postorder(element.right, treeStack);
				System.out.print(element.data + "->");
			}else{
				System.out.print(element.data + "->");
			}
				
		}
	}

}
