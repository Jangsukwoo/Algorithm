package SDS_WEEK1;

import java.util.Scanner;

/*
 * 피보나치 수열의  n번째 값 뽑기
 * 미리 다 구해놓고 입력값에 대해 출력하기
 */
public class 피보나치수2_2일차 {
	static long[] fibonacci = new long[91];
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		makeFibonacci();
		System.out.println(fibonacci[N]);
	}
	private static void makeFibonacci() {
		fibonacci[0] = 0;
		fibonacci[1] = 1;
		fibonacci[2] = 1;
		for(int i=3;i<=90;i++) fibonacci[i] = fibonacci[i-1]+fibonacci[i-2];
	}
}
