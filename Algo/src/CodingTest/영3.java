package CodingTest;

import java.util.Arrays;

public class 영3 {
	
	static boolean[] eratosthenesPrimes;
	static int[] pickIdx;
	static int n,r;
	static int possibleCaseCount;
	
	public static void main(String[] args) {
		System.out.println(solution(new int[] {1,2,3,4}));
		System.out.println(solution(new int[] {1,2,7,6,4}));
	}
	public static int solution(int[] nums) {
		int answer = -1;
		setPrimeBySieveofEratosthenes();

		n = nums.length;
		r= 3;
		possibleCaseCount = 0;
		pickIdx = new int[3];

		nCr(0,0,nums);

		answer = possibleCaseCount;

		return answer;
	}
	private static void nCr(int idx, int depth, int[] nums) {
		if(depth==r) {
			int sum = nums[pickIdx[0]]+nums[pickIdx[1]]+nums[pickIdx[2]];
			if(eratosthenesPrimes[sum]) possibleCaseCount++;
			return;	
		}
		for(int i=idx;i<n;i++) {
			pickIdx[depth] = i;
			nCr(i+1, depth+1, nums);
		}
	}
	private static void setPrimeBySieveofEratosthenes() { //에라토스테네스의 체 
		eratosthenesPrimes = new boolean[3001];
		Arrays.fill(eratosthenesPrimes,true);
		eratosthenesPrimes[1] = false;
		for(int i=2;i<=3000;i++){
			if(i>2 && i%2==0) continue; //짝수는 어치피 소수가 아니고 2의 배수는 다 지움
			for(int j=2;j*i<=3000;j++){
				eratosthenesPrimes[j*i] = false;	
			}
		}
	}
}
