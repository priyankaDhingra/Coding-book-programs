package me.pd.test;


public class Mergesorts {

	private static int[] sortedLst ={70, 60, 30,40};
	private static int[] lstInt={0,0,0,0};

	public static void main(String s[]) {
		// split
		mergesort( 0, sortedLst.length-1 );
		for(int k=0;k<sortedLst.length;k++){
		System.out.println(sortedLst[k]);}
		
	}

	private static void mergesort( int fInd, int lInd) {
		
		if (fInd < lInd) {
			int mid = (int) Math.floor((fInd + lInd) / 2);
			mergesort( fInd, mid);
			mergesort( mid + 1, lInd);
			merge(fInd, lInd, mid);

		}

	}

	private static void merge( int fInd, int lInd, int mid) {
		System.out.println("values:: fid->" + fInd + " mid->"
				+ mid+ " lId->" + lInd );
		 for (int i = fInd; i <= lInd; i++) {
			 lstInt[i] = sortedLst[i];
		    }
		int i = fInd;
		int j = mid + 1;
		
		int k=fInd;
		while (i <= mid && j <= lInd) {
			if (lstInt[i] > lstInt[j]) {
				sortedLst[k] = lstInt[j];
				j++;
			} else {
				sortedLst[k] =(lstInt[i]);
				i++;
			}
			k++;
		}

		while (i <= mid) {
			sortedLst[k]=(lstInt[i]);
			k++;
			i++;
		}
	}

}
