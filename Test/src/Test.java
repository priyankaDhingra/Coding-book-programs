
public class Test {
	
	
	//public static double remainder3(double p,double n, double [] p)
	
	
	public static double Info(double p, double n)
	{
		if((p==0) || (n==0))
		{
			return 0;
		}
		else{
		double pos = p/(p+n);
		double neg = n/(p+n);
		double answer = (1/Math.log10(2))*((-1)*pos*Math.log10(pos)+(-1)*neg*Math.log10(neg));
		
		return answer;
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("I(1/2,1/2) "+Info(6,6));
		
		double rem = (3.0/3) * Info(1, 2) ;//+ (0/5)* Info(1, 2) ;// + (6.0/12) * Info(2,4);
		
		System.out.println(rem);
		System.out.println(1-rem);

	}

}
