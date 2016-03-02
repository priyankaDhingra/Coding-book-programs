package me.pd.test.codeint;

public class Rotate90 {

	public static void main(String []args){
		int [][]matrix=new int[5][5];
		int k=0;
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
					matrix[i][j]=++k;
				}
			}
		print(matrix);
		rotate(matrix,5);
		
	}
	public static void rotate(int [][]mat, int n){
		int i=0,j=0;
		
		while (i!=n/2&&j!=n/2){
			for(int k=0;k<n-2;k++)
			mat=transform(i,k,mat,n);
			i++;
			j++;
		}
		System.out.println();
		System.out.println();
		print(mat);
	}
	private static int[][] transform(int row, int col, int[][] mat,int n) {
		
		int i=row;
		int j=col;
		int temp=mat[i][j];
		do{
		int newtemp=mat[j][n-i-1];
		mat[j][n-i-1]=temp;
		temp=newtemp;
		int itemp=j;
		j=n-i-1;
		i=itemp;
		}while(!(i==row&&j==col));
		
		return mat;
	}
	private static void print(int[][] matrix) {
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
					System.out.print("\t"+matrix[i][j]);
				}
			System.out.println();
			}
	}


}
