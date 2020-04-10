package CodingStudyHW;
/*
 * dp 조건
 * 1. int[] 
 * 2. dp[0] = 1, dp[1] = 2
 * 3. dp[n] = dp[n-1]+ dp[n-2]
 */
import java.util.Scanner;
public class 이xN타일링 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		short[] dp = new short[n];
		if(n==1)  System.out.println("1");
		else if(n==2) System.out.println("2");
		else if(n>2) {
			dp[0]=1;
			dp[1]=2;
			for(int i=2;i<n;i++) {
				dp[i]= (short) ((dp[i-1]+dp[i-2])%10007);
			}
			System.out.println(dp[n-1]);
		}
	}
}

