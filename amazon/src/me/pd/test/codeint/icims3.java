package me.pd.test.codeint;

public class icims3 {
	public static void main(String[] args) {
		int a[]={6,-2,
				3,4,
				5,-9,
				6,-2,
				3,4,
				5,-9,
				-16,-2,
				3,-4,
				5,-9,
				6,-2,
				3,-4,
				5,-9};
		
		System.out.println(Solution.solution(8));
	}
}

class Solution {
//	public static int solution(int[] S) {
//		int max_sum = 0;
//		int current_sum = 0;
//		boolean positive = false;
//		int n = S.length;
//		for (int i = 0; i < n; ++i) {
//			System.out.print(i+"\t");
//			int item = S[i];
//			if (item < 0) {
//				if (max_sum < current_sum) {
//					
//					max_sum = current_sum;
//					
//				}
//				current_sum = 0;
//			} else {
//				positive = true;
//				current_sum += item;
//			}
//			System.out.print(item+"\t" +current_sum+"\t"+max_sum );
//			System.out.println();
//		}
//		if (current_sum > max_sum) {
//			max_sum = current_sum;
//		}
//		if (positive) {
//			return max_sum;
//		}
//		return -1;
//	}
	
	
	  public static  int solution(int N) {
	        int largest = N;
	        int shift = 0;
	        int temp = N;
	        for (int i = 1; i < 30; ++i) {
	            int index = (temp & 1);
	            temp = ((temp >> 1) | (index << 29));
	            if (temp > largest) {
	                largest = temp;
	                shift = i;
	            }
	        }
	        return shift;
	    }
	

}
