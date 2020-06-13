package CodingAcademy;

import java.util.Arrays;

public class 부분집합구하기 {
	static int[] set = new int[] {-1,1,1,1,1,1};
	static int answer;
	static boolean[] include;
	static int[] pick;
	static int cnt=0;
	public static void main(String[] args){
		include = new boolean[3];
		pick = new int[4];
		System.out.println(numSubsets(4,4));
		//nCr(0,0);
		System.out.println(cnt);
	}
	
	private static void nCr(int idx, int depth){
		if(depth==4){
			System.out.println(Arrays.toString(pick));
			return ;
		}
		for(int i=idx;i<7;i++) {
			pick[depth] = set[i];
			nCr(i+1,depth+1);
		}
		return ;
	}

	

	
	private static int numSubsets(int n, int k) {
		cnt++;
		if(k==0){
			return 1;
		}
		
		int sum=0;
		for(int i=n;i>=1;i--){
			sum += numSubsets(i-1, k-1);
		}
		return sum;
	}
}
