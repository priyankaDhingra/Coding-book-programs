import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Pangram {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String pangram =sc.nextLine();
        /**
        while(sc.hasNext()){
        	pangram += sc.next();
        }
        **/
        int flag = 0;
        flag = findpan(pangram.toUpperCase());
        if(flag==1){
            System.out.println("pangram");
        }else{
            System.out.println("not pangram");
        }
    }
    static int findpan(String pangram){
    	System.out.println("pangram:"+pangram);
        for(int i=65; i<91; i++){
        	 System.out.println("char:"+Character.toString ((char) i));
            if(!(pangram.contains(Character.toString ((char) i)))){
               return 0;
            }
        }
        return 1;
    }
}