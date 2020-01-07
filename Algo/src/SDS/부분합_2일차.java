package SDS;

import java.util.Scanner;

/*
 * 수열 N
 * 10<=N<=100000
 * 
 * 부분합 S
 * 0<=S<=100000000
 * 
 * ->투포인터
 * end가 도망가고 start가 따라가는방식
 * 
 */
public class 부분합_2일차 {
	static int[] series;
	static int N;
	static int target;
	static int minLength = Integer.MAX_VALUE;
	static int startPointer,endPointer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		target = sc.nextInt();
		series = new int[N];
		for(int i=0;i<N;i++) series[i] = sc.nextInt();
		twoPointer();
		if(minLength==Integer.MAX_VALUE) System.out.println("0");
		else System.out.println(minLength);
	}
	private static void twoPointer() {
		int sum=0;
		while(endPointer<N) {
			sum+=series[endPointer];
			while(sum>=target) {
				minLength = Math.min(minLength,endPointer-startPointer+1);
				sum-=series[startPointer++];				
			}			
			endPointer++;
		}
	}
}
