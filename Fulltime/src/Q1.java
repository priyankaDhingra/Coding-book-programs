public class Q1 {
	public static void main(String args[]) {
		String s1 = "aabcccdddeeeee";
		System.out.println(s1 + " compressed "+ strCompression(s1));
		//System.out.println(s1 + " compressed "+ strCompressionBetter(s1));;

	}
	public static String strCompression(String input)
	{
		char reference = input.charAt(0);
		int charCount=0;
		String result = new String();
		for(int i=0;i<input.length();i++)
		{
			char currentChar=input.charAt(i);
			if(currentChar==reference)
			{
				charCount++;
			}
			else
			{
				result=result+reference+((charCount>1)?charCount:"");
				reference = currentChar;
				charCount=1;
			}
		}
		result=result+reference+((charCount>1)?charCount:"");

		return result;
		
		
	}
	
	public static String strCompressionBetter(String input)
	{
		char reference = input.charAt(0);
		int charCount=0;
		StringBuffer result = new StringBuffer();
		
		for(int i=0;i<input.length();i++)
		{
			char currentChar=input.charAt(i);
			if(currentChar==reference)
			{
				charCount++;
			}
			else
			{
				result.append(reference);
				result.append(charCount);
				reference = currentChar;
				charCount=1;
			}
		}
		
		result.append(reference);
		result.append(charCount);

		if(input.length()>result.length())
		{
			return result.toString();
		}
		else return input;
	}
	

}
