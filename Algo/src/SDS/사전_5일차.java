package SDS;


import java.math.BigInteger;
import java.util.Scanner;

/*
 * 사전에 수록되어 있는 모든 문자열은 N개의 a와 M개의 z로 이루어져 있음
 * 문자열 하나를 볼건데
 * N과 M이 주어졌을 때 K번째 문자열이 무엇인지 구하는 프로그램
 * 
 * 사전순으로 등록이 되어있다고함.
 * 
 * ->빅인트저로 풀었는데 메모리초과난다...ㅠㅠ
 * 
 * 
 * 
 */
public class 사전_5일차 {
	static long[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a,z;
		a = sc.nextInt();
		z = sc.nextInt();
		dp = new long[201][201];
		StringBuilder sb = new StringBuilder();
		long k=sc.nextLong();
		if(combination(a+z,z)!=0) query(a,z,k,sb);
		
//		for(int row=0;row<101;row++) {
//			for(int col=0;col<101;col++) {
//				if(dp[row][col]!=0) {
//					System.out.println(row+" "+col);
//					System.out.println(dp[row][col]);
//				}
//			}
//		}
		
		System.out.println(sb.toString());
	}
	private static void query(int a, int z , long k, StringBuilder sb) {
		if(a+z==0) return;
		else if(a==0) {//a가 없다
			sb.append("z");
			query(a,z-1,k,sb);
		}
		else if(z==0) {//z가 없다 
			sb.append("a");
			query(a-1,z,k,sb);
		}
		else {
			long leftCount = combination(a+z-1, z);
			if(k<=leftCount){//같거나 작으면?
				sb.append("a");
				query(a-1, z, k, sb);
			}else if(k>leftCount) {
				//K가 result보다 크다? -> z구간
				k-=leftCount;
				sb.append("z");
				query(a, z-1, k, sb);
			}
		}
	}
	
	private static long combination(int n, int r) {
		if(n<r) return 0;
		else if(n==r || r==0) return 1;
		else if(dp[n][r]!=0) return dp[n][r];
		else return dp[n][r] = combination(n-1,r)+combination(n-1, r);
	}
}
