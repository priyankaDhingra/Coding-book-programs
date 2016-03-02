package me.pd.test.codeint;
//Write an algorithm such that if an element in an MxN matrix is 0, 
//its entire row and column is set to 0

public class RowColZero {

	public static void main(String[] args) {
		int [][]matrix=new int[4][3];
		
		for(int i=0;i<4;i++){
			for(int j=0;j<3;j++){
					matrix[i][j]=2;
				}
			}
		matrix[1][1]=0;
		print(matrix);
		rcZero(matrix);
		
	}
	
	
	private static void print(int[][] matrix) {
		for(int i=0;i<4;i++){
			for(int j=0;j<3;j++){
					System.out.print(matrix[i][j]);
				}
			System.out.println();
			}
	}


	public static void rcZero(int [][]matrix){
		int []cols=new int[matrix[0].length];
		int []rows=new int[matrix.length];
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				if(matrix[i][j]==0){
					rows[i]=1;
					cols[j]=1;
				}
			}
		}
		
		for(int i=0;i<rows.length;i++){
			for(int j=0;j<cols.length;j++){
				if(rows[i]==1||cols[j]==1){
					matrix[i][j]=0;
				}
			}
		}
		print(matrix);
	}
}
