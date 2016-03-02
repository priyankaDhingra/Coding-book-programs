package me.pd.test.codeint;

import java.util.Scanner;

public class MissingTermAP {

	public static void main(String[] args) {
		int an,ft,d,i=0;
		 Scanner sc = new Scanner(System.in);
	     int n = sc.nextInt();
	     if(!(n<3 || n>2500)){
	     int a[]=new int[n];
		
		for( i=0;i<n;i++)
			a[i]=sc.nextInt();
		ft=a[0];
		d=a[1]-a[0];
		int d1=a[2]-a[1];
		if(d!=d1&& n>3)
			if(d1==(a[3]-a[2]))
				d=d1;
		
		for(i=2;i<n;i++){
			an=ft+((i)*d);
			if(a[i]!=an)
				{System.out.println(an);break;}
		}
	     }
	}

}
