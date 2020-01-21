package SDS;

import java.util.Scanner;

/*
 * N은 수열의 개수
 * M은 타겟
 * 투포인터
 * 
 * 
 * 
10 1
1 2 3 4 2 5 3 1 1 2

4 3
1 1 1 1

2 3
1 3
 */
public class 수들의합2_2일차 {
	static int N,M;
	static int start,end;
	static int[] series;
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		series = new int[N];
		for(int i=0;i<N;i++) series[i]= sc.nextInt();
		twoPointer();
		System.out.println(cnt);
	}
	private static void twoPointer() {
		int sum=0;
		while(end<N){
			if(sum>M) {
				sum-=series[start++];
			}else if(sum<M) {
				sum+=series[end++];
			}
			if(sum==M) {
				cnt++;
				sum-=series[start++];
			}
		}
		if(start<end) {
			while(start<end){
				sum-=series[start++];
				if(sum==M) cnt++;
			}
		}
	}
}
