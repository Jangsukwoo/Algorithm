package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 두 문자열에 모두 포함된 부분 문자열 중 가장 긴 것의 길이를 출력하라.
 */
public class 동적계획법_4일차_공통부분문자열 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static String str1;
	static String str2;
	static int answer;
	static int[][] dp = new int[4005][4005];
	public static void main(String[] args) throws IOException {
		setData();
	}
	private static void setData() throws IOException {
		str1 = br.readLine();
		str2 = br.readLine();
		for(int row=0;row<str1.length();row++) {
			for(int col=0;col<str2.length();col++) {
				if(str1.charAt(row)==str2.charAt(col)){//같은 문자면
					dp[row+1][col+1]=dp[row][col]+1;
					answer = Math.max(dp[row+1][col+1],answer);
				}
			}
		}
		System.out.println(answer);
	}
}
