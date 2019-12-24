package CodingStudy;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 포도주 잔 선택시 포도주 모두 마심
 * 연속으로 놓여있는 3잔은 마실 수 없음
 * 
 * 포도주 잔의 개수 n
 * 1<=n<=10000
 * dp??
 * 처음 포도주부터 읽어나가면서 이 포도주를 마실건지 안마실건지 판단하기 위해 참조하는 boolean DP 배열을 만들었다.
 * 1 2 3 4 5 6
 * -> 6 16 16 25 33 33
 * -> 0 10 23 23 31 32
 */
public class 포도주시식 {
	static int n;
	static int[] wine;
	static int[] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		wine = new int[n];
		memo = new int[n];
		for(int i=0;i<n;i++) {
			wine[i] = sc.nextInt();
			memo[i] = wine[i];
		}
		if(n>1) memo[1] +=memo[0];
		if(n>2) {
			for(int i=2;i<n;i++) {
				memo[i] = Math.max(wine[i-1]+wine[i],wine[i-2]+wine[i]);
				memo[i] = Math.max(memo[i-1],memo[i]);
			}
		}
		System.out.println(Arrays.toString(memo));
	}
}
