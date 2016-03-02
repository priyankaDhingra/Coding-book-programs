package me.pd.test;

public class RRotation {

//	public static void main(String[] args) {
//	
//		String elm="abc";
//		String cmpet="cab";
//		if(getStrRightRot(elm).equals(cmpet)){
//			System.out.println("yes");
//		}else{
//			System.out.println("value"+getStrRightRot(elm));
//		}
//	}
//
//	private static String getStrRightRot(String elm) {
//		return elm.charAt(elm.length()-1)+""+elm.substring(0, elm.length()-1);
//	}
	public static void main(String[] args) {
		
		String elm="abc";
		String cmpet="bca";
		if((elm.length()==cmpet.length())&&((elm+elm+"").contains(cmpet))){
			System.out.println("yes");
		}
	}

}
