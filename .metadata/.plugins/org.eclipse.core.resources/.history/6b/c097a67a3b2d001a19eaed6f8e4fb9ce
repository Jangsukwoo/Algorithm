package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Two Pointer?? 
 * https://www.youtube.com/watch?v=2wVjt3yhGwg&t=6s
 * 
 * 스트링,배열,링크드리스
 * O(n^2) or O(n^3)를 O(n)으로 줄여줄 수 있다.
 * 
 * 정렬상태에 따라 O(nlogn)까지 걸릴 수 있다.
 * 
 * 숫자 배열이 있고
 * 배열을 포인팅하는 두개의 포인터를 만든다. startPointer, endPointer
 * 
 * ---백준 1806번 <- 투포인터 ---
 * 길이 N짜리의 수열 입력
 * 10<=N<=100000
 * 
 * 연속된 수들의 부분합이 1억 이상이 되는 부분 수열중 가장 짧은 것의 길이를 구한다.
 * 
 * -> 풀이
 * 
 * endPointer가 뒤로 도망가고
 * startPointer가 따라잡을 수 있는 만큼 따라잡는 방식으로 구현
 * end가 뒤로, start가 targetNumber를 만족하면서 따라잡을 수 있는 만큼 따라잡음 
 * 최대한 startPointer를 줄였을 때 최소 길이 update
 * 
 * 최소길이를 구할 수 없는 경우의 예외처리를 뺴먹어서 몇번 틀렸다.
*/
public class 시간복잡도_부분합_투포인터 {
	static int N,targetSum,minLength;
	static int startPointer,endPointer;
	static int[] series;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		targetSum = Integer.parseInt(st.nextToken());
		series = new int[N];
		minLength = 100000;//최대 길이
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) series[i] = Integer.parseInt(st.nextToken());
		twoPointer();
		if(minLength!=100000) System.out.println(minLength);
		else if(minLength==100000) System.out.println("0"); //예외처리를 안해서 몇번 틀렸다.
		
	}
	private static void twoPointer() {
		long sum=0;
		while(endPointer<N){//endPointer를 끝까지 
			sum+=series[endPointer];
			if(sum>=targetSum){
				while(startPointer<=endPointer){
					if((sum-series[startPointer])>=targetSum){
						sum-=series[startPointer];
						startPointer++;
					}else break;
				}
				minLength = Math.min(minLength,(endPointer-startPointer+1));
			}
			
			//이부분에 else면 endPonter++로 했더니 무한반복..
			//sum>=target에 계속해서 걸려버려서 end가 도망가지 못했음 
			endPointer++;
		}
	}
}
