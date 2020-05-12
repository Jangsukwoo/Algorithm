package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
/*
7 3
1 1 1 1 1 1 1
1 1 1

10 5
1 2 1 2 1 2 1 2 1 2 
1 2 1 2 1 

구현문제라며~ ㅠ
dp+binarySearch

binarySearch를 하기 위한 조건은 정렬상태여야함 
 */
public class 피자굽기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int D,N;
	static long[] dough;
	static long[] oven;
	static long[] dp;
	static int doughIdx;
	static int answer;
	public static void main(String[] args) throws IOException {
		setData();
		memoization();
		simulation();
		if(doughIdx==N) System.out.println(answer+1);
		else System.out.println("0");
	}
	private static void simulation() {
		for(int i=0;i<N;i++) {
			int idx = Arrays.binarySearch(dp,dough[i]);
			System.out.println(idx);
		}
	}
	private static void memoization(){
		//적은 지름 밑 큰 지름은 의미가 없으니 전부 커팅하는 작업
		dp[0] = oven[0];
		for(int i=1;i<D;i++) {
			if(oven[i]>=dp[i-1]) dp[i] = dp[i-1];
			else if(oven[i]<dp[i-1]) dp[i] = oven[i];
		}
	}
	private static void setData() throws IOException{
		st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		oven = new long[D];
		dp = new long[D];
		dough = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<D;i++) oven[i] = Long.parseLong(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) dough[i] = Long.parseLong(st.nextToken());
	}
}
