package me.pd.test;

import java.util.HashSet;

public class permutation {
	static HashSet<String> hashSet = new HashSet<String>();
	    private static void permutations(String prefix, String str){
	        int n = str.length();
	        if (n == 0) 
	        	hashSet.add(prefix);
	        else {
	            for (int i = 0; i < n; i++)
	                permutations(prefix + str.charAt(i), 
	            str.substring(0, i) + str.substring(i+1));
	        }
	    }
	    public static void main(String[] args) {
	        permutations("", "ABBCD");
	        int i=0;
	        for(String str:hashSet){
	        	i++;
	        	System.out.println(str);
	        }
	        System.out.println(i);
	    }
	}