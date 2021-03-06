package CodingStudy;

import java.util.Scanner;

/*
 * 인터넷에서 참고한 DP 점화식.
 * 이해가 좀 필요하다.
 */
public class 출근경로_dp {
	static int[][][][] dp;
	static int w,h,answer;
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		w = sc.nextInt();
		h = sc.nextInt();
		dp = new int[h+1][w+1][2][2];
		for(int i=2;i<=h;i++) dp[i][1][0][0] = 1;
		for(int i=2;i<=w;i++) dp[1][i][1][0] = 1;
		
		for(int i=2; i<=h; i++){
			for(int j=2; j<=w; j++){
				dp[i][j][0][0] = (dp[i-1][j][0][0] + dp[i-1][j][0][1]) % 100000;
				dp[i][j][0][1] = dp[i-1][j][1][0];
				dp[i][j][1][0] = (dp[i][j-1][1][0] + dp[i][j-1][1][1]) % 100000;
				dp[i][j][1][1] = dp[i][j-1][0][0];
			}
		}
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				answer += dp[h][w][i][j];
			}
		}
		System.out.println(answer%100000);
	}
}
