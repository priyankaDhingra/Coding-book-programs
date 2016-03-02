package me.pd.test;
//Replace wild cards with all possible combinations of zeros and ones using rec
public class WildCardRep {
	
	
	public static void main(String[] args) {
		String s = "0m1m";
		replaceWildCard(s);
	}

	public static String replaceWildCard(String s) {
		
		// System.out.println(s);
		if(!s.contains("m")){
			return s;
		}else{
			 String cloneS1=s.replaceFirst("m","0");
			 String cloneS2=s.replaceFirst("m","1");

		 System.out.println(cloneS1);
		 System.out.println(cloneS2);
		replaceWildCard(cloneS1);
		replaceWildCard(cloneS2);
		}
		return s;
	}
}
