package me.pd.test;

public class MaxPath {
	static int cost[][] = { { 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 }, { 1, 5, 3 } };
	static String path[] ={"0","0","0","0","0","0"};
	static int value = 0;
	static int ind=0;

	public static void main(String[] args) {
		value=cost[3][2]+value;
		path[ind++] = "(3,2)";
		calculateMax(3, 2);
		
		for(String p:path){
		System.out.println(p);
		}
		System.out.println("cost:"+value);
	}

	private static int calculateMax(int m, int n) {
		// for(int i=0;i<3;i++){
		// for(int j=0;j<4;j++){
		if (m == 0 && n == 0) {
			path[ind++] = "(" + m + "," + n + ")";
			value = cost[0][0] + value;
			return value;
		}  else {
			 if (m - 1 < 0 ) {
				return calculateMax(m, n-1);
			}else if (n-1 < 0){
				return calculateMax(m-1, n);
			}
			if (cost[m - 1][n] > cost[m][n - 1]) {
				if (cost[m - 1][n - 1] > cost[m - 1][n]) {
					m = m - 1;
					n = n - 1;
					path[ind++] = "(" + m + "," + n + ")";

				} else {
					m = m - 1;
					
					path[ind++] = "(" + m + "," + n + ")";
				}
			} else {
				if (cost[m - 1][n - 1] > cost[m][n - 1]) {
					m = m - 1;
					n = n - 1;
					path[ind++] = "(" + m + "," + n + ")";
				} else {
					n = n - 1;
					path[ind++] = "(" + m + "," + n + ")";
				}
			}
			value = cost[m][n] + value;
			return calculateMax(m, n);
		}

	}

}
