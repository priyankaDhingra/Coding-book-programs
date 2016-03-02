package me.pd.test.codeint;
//Given two strings, write a method to decide if one is a permutation of the other.
public class StrPermut {

	public static void main(String[] args) {
		System.out.println(checkPermut("aabc", "abvc")?"isPermut":"not Permut");
	}
	public static boolean checkPermut(String str1, String str2){
		if(str1==null||str2==null) return false;
		if(str1.length()!=str2.length()) return false;
		
		
		int [] occArr1=new int[256];
		int [] occArr2=new int[256];
		for(int charAscii:str1.toCharArray()){
			occArr1[charAscii]+=1;
		}
		for(int charAscii:str2.toCharArray()){
			occArr2[charAscii]+=1;
		}	
		for(int i=0;i<256;i++)
		if(occArr1[i]!=occArr2[i])
			return false;
		return true;
	}
}
