package me.pd.test;

public class ConscutGray {

	public static void main(String[] args) {
		int n=5; int m=7;
//		int binN = Integer.parseInt(Integer.toBinaryString(n));
//		int binM = Integer.parseInt(Integer.toBinaryString(m));
		
//		 System.out.println(binM+":m "+binN+" :n");
		int xored=(n ^ m);
		System.out.println("XOR:"+xored);
		
 		if(((xored) & (xored-1))==0){
 			System.out.println("yes");
 		}else{
 			System.out.println("no");
 		}
		
	}

}
