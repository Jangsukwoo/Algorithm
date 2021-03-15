package CodingTest;

import java.util.Arrays;

public class Test3 {
	static StringBuilder sb;
	public static void main(String[] args) {
		 int[] A = { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 };
		  printallSubarrays(A);
		  sortingArray(A);
	}

	private static void sortingArray(int[] array) {
		
		int n = array.length;
		
		for(int i=0;i<n-1;i++) {
			for(int j=i;j<n-1;j++) {
				if(array[j+1]>array[j]) {
					int temp = array[j+1];
					array[j+1] = array[j];
					array[j] = temp;
				}
			}
		}
		
		System.out.println(Arrays.toString(array));
		
	}

	private static void printallSubarrays(int[] array) {
		sb = new StringBuilder();
		
		for(int start=0;start<array.length;start++) {
			
			for(int end=0;end<array.length;end++) {
				
				int sum = 0;
				String subArray = "";
				for(int idx = start; idx<=end; idx++) {
					sum+=array[idx];
					subArray+= Integer.toString(array[idx])+" ";
				}
				if(sum==0) {
					sb.append(subArray+"\n");
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
