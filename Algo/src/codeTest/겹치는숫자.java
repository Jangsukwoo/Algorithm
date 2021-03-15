package codeTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 겹치는숫자 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {17,18,19,20,21}, new int[] {18,20})));
	}

	private static int[] solution(int[] A, int[] B) {
		int[] C = new int[A.length]; //A사이즈만큼
		
		Set<Integer> B_data = new HashSet<Integer>();
		
		for(int i=0;i<B.length;i++) B_data.add(B[i]);
		
		for(int i=0;i<A.length;i++) {
			
			int A_value = A[i];
			
			if(B_data.contains(A_value)) C[i] = A_value;
			else C[i] = 0;
			
		}
		
		return C;
		
	}
}
