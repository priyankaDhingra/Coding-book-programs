package me.pd.test.codeint;

public class RemoveDup {

	public static void main(String[] args) {
		remDup(("aaabccd").toCharArray());
	}
	public static void remDup(char []str){
		if(str.equals(null)){
			return;
		}
		if (str.length<2){
			return;
		}
			
			int temp=1;
			for(int i=1; i<str.length;++i){
				int j;
				for( j=0 ; j<temp; ++j)
					if(str[i]==str[j]) break;
				if(j==temp){
					str[temp]=str[i];
					++temp;
				}
					
			}
			str[temp]=0;
		
		for(int i=0;i<temp;i++)
		System.out.print(str[i]);
	}
}
