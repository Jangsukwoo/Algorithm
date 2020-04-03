package CodingStudy;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 약이 N개 담긴 병
 * 한 조각을 꺼낸 날은 W
 * 반 조각을 꺼낸 날은 H
 * 2N일이 지나면 길이가 2N인 문자열이 만들어짐
 * 이때 서로 다른 2N 길이의 문자열의 개수는?
3814986502092304
9223372036854775807 //long 최대 

재귀 + 디피
 */
public class 알약 {
	static int N;
	static long[] answer = new long[31];
	static long[][] memo = new long[31][31];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		setDrugMemo();
		while(true){
			N = sc.nextInt();
			if(N==0) return;
			else System.out.println(answer[N]);
		}
	}
	private static void setDrugMemo() {
		for(int drug=1;drug<=30;drug++){
			recursive(drug,0);
			answer[drug] = memo[drug][0];
		}
	}
	private static long recursive(int w, int h) {
		if(memo[w][h]!=0) return memo[w][h];
		if(w==0) return 1;
		else{//먹을 알약이 있는 경우
			memo[w][h] += recursive(w-1,h+1);//한조각--, 반조각++
			if(h>0) memo[w][h]+=recursive(w, h-1); //반조각 --, 한조각 그대로
			return memo[w][h];
		}
	}
}
