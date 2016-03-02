package me.pd.test.codeint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class zeroonealgo {

	public static void main(String[] args) {
		int z=10;
		 HashMap<Integer,Integer> factors = new HashMap<Integer,Integer>();
	       
	        int tempInput = z;
	        int count=0;
	        for (int i = 2; i <= tempInput; i++) { 
		        	if (tempInput % i == 0) { 
	                    count = factors.get(i);
	
		        		factors.put(i,count++); }
		        }
	        
	        
	        
	        int min = Collections.min(factors.values());

	        Iterator factorMap = factors.entrySet().iterator();
	        while(factorMap.hasNext()){
	            Map.Entry keyVal = (Map.Entry)factorMap.next();
	            int counter= keyVal.getValue();
	            if(counter==1)return 0;
	            if(counter%min!=0)
	                return 0;
	        }
	        
	}
//		 Scanner sc= new Scanner (System.in);
//	        int n=sc.nextInt();
//	        
//	        int c=2;
//	        
//	        HashMap<Integer,Integer> mymap=new HashMap<Integer,Integer>();
//	        mymap.put(1, 1);
//	        mymap.put(2, 10);
//	        mymap.put(3, 21);
//	        mymap.put(4, 20);
//	        mymap.put(5, 10);
//	        mymap.put(6, 30);
//	        mymap.put(7, 21);
//	        mymap.put(8, 40);
//	        mymap.put(9, 81);
//	        mymap.put(0, 0);
//	        
//	        if(n>0 && n<100000){
//	         int t= n%10;
//	        	int val=(mymap.get(t)-c)/t;
//	        	System.out.println(val);
//	         
//	        }
	}

}
