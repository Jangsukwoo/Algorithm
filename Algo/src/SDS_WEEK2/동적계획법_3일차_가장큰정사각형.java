package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 가장 큰 정사각형 넓이는??
 */
public class 동적계획법_3일차_가장큰정사각형 {
	static int N,M;
	static int[][] arr;
	static int[][] dp;
	static int max;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
	}
	private static void input() throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		
		for(int row=1;row<=N;row++){
			String[] data = br.readLine().split("");
			for(int col=1;col<=M;col++) {
				arr[row][col] = Integer.parseInt(data[col-1]);
				if(arr[row][col]==1) {
					dp[row][col] = min(dp[row-1][col-1],dp[row-1][col],dp[row][col-1])+1;
					max = max>dp[row][col] ? max:dp[row][col]; //참 max 거짓 dp[row][col]
				}
			}
		}
		System.out.println(max*max);
	}
	private static int min(int leftTop, int rightTop, int leftBottom){
		
		leftTop = leftTop<rightTop ? leftTop:rightTop;
		
		return leftTop<leftBottom ? leftTop:leftBottom;
	}
}
