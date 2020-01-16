package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 동적계획법_3일차_구간합구하기4 {
	static int N,M;
	static int[] series;
	static int[] dp;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		input();
		output();
	}

	private static void input() throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		series = new int[N+1];
		dp = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			series[i] = Integer.parseInt(st.nextToken());
			dp[i]+=series[i]+dp[i-1];
		}
	}
	private static void output() throws IOException {		
		StringTokenizer st;
		for(int i=0,start,end,sum;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			sum = dp[end]-dp[start-1];
			bw.write(sum+"\n");
		}
		bw.flush();
		bw.close();
	}
}
