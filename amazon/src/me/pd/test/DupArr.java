package me.pd.test;

import java.util.HashMap;
import java.util.Map;

public class DupArr {

	public static void main(String[] args) {
		
		Map set1=new HashMap();
		int arr[]={2,3,4,1,2,2,4,4,2,3};
		for (Integer i:arr){
			if(set1.containsKey(i)){
				int val=((int)set1.get(i))+1;
				set1.replace(i,val );
			}else{
				set1.put(i, 1);
			}
		}
		System.out.println(set1.toString());
	}

}
