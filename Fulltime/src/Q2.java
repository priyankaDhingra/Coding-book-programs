import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Q2 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int[] input = new int[scanner.nextInt()];
		
		for (int i=0; i < input.length; i++) {
			input[i] = scanner.nextInt();
		}
		
		List<Integer> output = getClosetNumbers(input);
		
		for (int i : output) {
			System.out.print(i+" ");
		}
		
		scanner.close();
	}

	private static List<Integer> getClosetNumbers(int[] input) {
		List<Integer> result = new ArrayList<Integer>();
		long min=Long.MAX_VALUE;
		 Arrays.sort(input);

		for(int i=1;i<input.length;i++){
			if(min >= Math.abs(input[i]-input[i-1])){
				
				min =Math.abs(input[i]-input[i-1]);
				result.add(input[i-1]);
				result.add(input[i]);
			}
		}
		return result;
	}
	
}
