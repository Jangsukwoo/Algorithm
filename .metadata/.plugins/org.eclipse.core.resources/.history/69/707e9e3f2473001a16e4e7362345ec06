package CodingStudyHW;

import java.util.Scanner;

/*
 * 1 = 1 (1)
 * 2 = 2 (1+1, 2)
 * 3 = 4 (1+1+1,1+2,2+1,3)
 * 4 = 7 (1+1+1+1,1+2+1 3개, 1+3 2개, 2+2)
 * 5 = 13
 * 1+1+1+1+1 1개
 * 1+2+1+1 4개
 * 2+2+1 3개
 * 1+3+1 3개
 * 3+2 2개
 * 
 * 6 = 24
 * 
 * 1+1+1+1+1+1 1개
 * 1+1+1+1+2 5개
 * 1+1+2+2+ 6개
 * 1+3+2 6개
 * 2+2+2 1개
 * 1+1+1+3 4개
 * 3+3 1개
 * 
 * 1 2 4 7 13 24 
 * 
 * 1. dp 배열 -> int[] memo
 * 2. dp 초기화 dp[1] = 1, dp[2] = 2, dp[3] = 4  
 * 3  dp 점화식
 * ->  memo[n] = memo[n-3]+memo[n-2]+memo[n-1] 
 * 
 *
 * 
 */
public class 일이삼더하기 {
	static int[] memo = new int[12]; //1. 디피 배열 
	public static void main(String[] args) {
		memoization();
		setData();
	}
	private static void memoization() {
		//2. 초항 정의
		memo[1] = 1;
		memo[2] = 2;
		memo[3] = 4;
		
		//3. memo[n] = memo[n-3]+memo[n-2]+memo[n-1] 
		for(int n=4;n<=11;n++) memo[n] = memo[n-3]+memo[n-2]+memo[n-1];

	}
	private static void setData() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testcase=1,n;testcase<=T;testcase++) {
			n = sc.nextInt();
			System.out.println(memo[n]);
		}
	}
}
