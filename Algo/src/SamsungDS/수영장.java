package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수영장 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] months;
	static int[] fees;
	static int minCost;
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			dfs(0,0);
			System.out.println("#"+testcase+" "+minCost);
		}
	}
	private static void dfs(int month, int cost) {
		if(month>11) {
			minCost = Math.min(cost,minCost);
			return;
		}
		dfs(month+1,fees[0]*months[month]+cost);
		dfs(month+1,fees[1]+cost);
		dfs(month+3,fees[2]+cost);
		dfs(month+12,fees[3]+cost);
	}
	private static void setData() throws IOException {
		fees = new int[4];
		months = new int[12];
		st = new StringTokenizer(br.readLine());
		for(int fee=0;fee<4;fee++) fees[fee] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int month=0;month<12;month++) months[month] = Integer.parseInt(st.nextToken());
		minCost = Integer.MAX_VALUE;
	}
}
