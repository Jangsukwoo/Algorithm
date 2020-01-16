package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 동적계획법_4일차_LCS2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static String str1;
	static String str2;
	static int cnt;
	static char[] answer;
	static int[][] dp = new int[1005][1005];
	public static void main(String[] args) throws IOException {
		setData();
	}
	private static void setData() throws IOException {
		str1 = br.readLine();
		str2 = br.readLine();
		answer = new char[Math.max(str1.length(),str2.length())];
		
		for(int i=1;i<=str1.length();i++) {
			for(int j=1; j<=str2.length();j++) {
				if(str1.charAt(i-1)==str2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1]+1;
				}else {
					dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
				}
			}
		}
		System.out.println(dp[str1.length()][str2.length()]);
		int c = str1.length();
		int d = str2.length();
		while(dp[c][d]!=0){
			if(dp[c][d]==dp[c][d-1]) d--;
			else if(dp[c][d]==dp[c-1][d]) c--;
			else if(dp[c][d]-1==dp[c-1][d-1]) {
				answer[cnt++] = str1.charAt(c-1);
				c--;
				d--;
			}
		}
		for(int i=cnt-1;i>=0;i--) {
			System.out.print(answer[i]);
		}
	}
}
