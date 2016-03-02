package me.pd.test.codeint;

// to determine if a string has all unique characters
// What if you can not use additional data structures
public class FindUniqCharStr {
	static String test = "abcd";

	public static void main(String[] s) {
		System.out.println((isUnique(test)?"Uniques":"Repitions"));
		
	}
//	public static boolean isUnique(String str){
//		Boolean []isUniqList=new Boolean[256];
//		for(int i=0; i<str.length();i++){
//			int val = str.charAt(i);
//			try{
//				if(isUniqList[val]) return false;
//			}catch(NullPointerException npe){
//				isUniqList[val]=true;
//			}
//		}
//		return true;
//	}
	public static boolean isUnique(String str){
		int checker=0;
		for(int i=0; i<str.length();i++){
			int val = str.charAt(i)-'a';
			System.out.println("checker: "+checker+" val: "+val);
				if((checker & (1<<val))>0) return false;
				checker |=1<<val;
		}
		return true;
	}
}
