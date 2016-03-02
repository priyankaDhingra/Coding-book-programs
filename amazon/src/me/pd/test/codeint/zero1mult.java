package me.pd.test.codeint;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zero1mult {
	 public static void main(String args[] ) throws Exception {
		 Scanner sc= new Scanner (System.in);
	        int n=sc.nextInt();
	        if(n>0 && n<100000){
	            Queue<Long> childQ=new LinkedList<Long>();
	            
	            childQ.add((long) 10);
	            childQ.add((long) 11);
	            long  x=childQ.remove();
	        	while(true) {
	        		
	            System.out.println(x);
	            if(x%n==0){
	                System.out.println("found:"+x);
	               return;
	            } 
	            childQ.add(x*10);
	            childQ.add((x*10)+1);
	            x=childQ.remove();
	        	}
	            
	        }
	        }
}
