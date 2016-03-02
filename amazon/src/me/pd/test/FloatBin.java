package me.pd.test;

public class FloatBin {

	private static String x="";

	public static void main(String[] args) {
		double i=7.65;
		System.out.println((int)i);
		f2b((int)i);
		System.out.println(x);
	}

	private static double f2b(int n) {
		if(n==1||n==0){
			return n;
		}
			x=(n%2+"")+x;
			System.out.println("inside x "+x);
			return f2b(n/2);
		
	}

}
