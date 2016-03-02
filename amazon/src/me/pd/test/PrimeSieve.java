package me.pd.test;

public class PrimeSieve {

	//import java.lang.*;

	
	public static void main(String[] x){
	if(x.length<1){
	System.out.println("entermber");
	return;
	}

	int n = Integer.parseInt(x[0]);
	int c = 0;
	int[] pc = new int[n];
	int[] p2 = new int[n];
	if(n<=1){
	System.out.println(2);
	return;
	}
	c++;
	pc[0] = 2;
	p2[0] = 2;
	int cn = 1;
	long g = 2;
	while(c<n){
	cn+=2;
	boolean Prime = true;
	for(int i=1;i<c;i++){
	p2 =p2- 2;
	if(p2 == 0)
	Prime = false;
	if(p2 < 1)
	p2 += pc;
	}
	if(Prime){
	pc[c] = cn;
	p2[c++] = cn;
	if((c%2048) == 0)
	System.out.println(cn);
	if(cn<1000000)
	g+=cn;
	else{
	System.out.println("Yayllion reached!");
	break;
	}

	}
	}
	System.out.println("Grandl " + g);
	System.out.println(pc[n-1]);
	}

	}
}
