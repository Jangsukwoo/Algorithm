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
 * 6 = 22
 * 
 * 1+1+1+1+1+1 1개
 * 1+1+1+1+2 5개
 * 1+1+2+2+ 4개
 * 1+3+2 6개
 * 2+2+2 1개
 * 1+1+1+3 4개
 * 3+3 1개
 * 
 * 1 2 4 7 13 22 
 * 1 2 3 6 9 12
 * 
 */
public class 일이삼더하기 {
	static int[] memo = new int[12];
	public static void main(String[] args) {
		memoization();
		setData();
	}
	private static void memoization() {
	}
	private static void setData() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++) {
			
		}
	}
}
