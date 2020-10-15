package DP;

import java.util.Scanner;

public class 하와와대학생쨩하와이로가는서이와요 {
	static int n;
	static long[] memo;
	static long answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		memo = new long[50001];
		memo[1] = 1;
		memo[2] = 1;
		memo[3] = 2;
		memo[4] = 3;
		for(int i=5;i<=50000;i++) memo[i] = (memo[i-1]+memo[i-3])%1000000009;
		System.out.println(memo[n]);
	}
}
