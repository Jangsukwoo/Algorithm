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
	static boolean[] visit;
	static int answer;
	static int mid;
	static boolean flag = true;
	public static void main(String[] args) throws IOException {
		setData();
		memoization();
		binarySearchAll();
		answer=mid;
		if(flag) System.out.println(answer+1);
		else System.out.println("0");
	}
	private static void binarySearchAll() {
		for(int i=0;i<N;i++) {
			binarySearch(dough[i]);
			if(flag==false) return;
		}
	}
	private static void binarySearch(long pizza){
		flag = false;
		int start = 0;
		int end = mid-1;
		int m=0;
		while(start<=end){//이분탐색+UpperBound
			m = (start+end)/2;
			if(dp[m]>=pizza){
				flag = true;
				start = m+1;
			}else end = m-1;
		}
		mid = end;
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
		mid = D;
	}
}
