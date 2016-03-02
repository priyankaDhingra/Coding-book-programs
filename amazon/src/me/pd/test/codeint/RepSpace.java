package me.pd.test.codeint;
/*Write a method to replace all spaces in a string with'%20'. 
 * You may assume that the string has sufficient space at the 
 * end of the string to hold the additional characters, 
 * and that you are given the "true" length of the string. */

public class RepSpace {

	public static void main(String []args){
		RepAllSpaces("Mr Dohn Smith    ");
	}
	
	public static void RepAllSpaces(String str){
		char []arr=str.toCharArray();
		int j=str.trim().length()-1;
		for(int i=arr.length-1;i>=0;i--){
			if(arr[j]==' '){
				arr[i]='0';
				arr[--i]='2';
				arr[--i]='%';
				
			}else{
				arr[i]=arr[j];
			}
			j--;
			
		}
		for(int i=0;i<arr.length;i++){
		System.out.print(arr[i]);
		}
	}
}
