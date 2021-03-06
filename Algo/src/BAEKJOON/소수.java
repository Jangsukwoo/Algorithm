package BAEKJOON;

import java.util.Scanner;

/*
 * Eratosthenes' sieve 
 * 음.. 소수 중에 2보다 큰 짝수는 있을 수 없음
 * 에라토스 테네스의 체로 풀면
 * 2부터 시작해서 배수들을 전부 true로 바꾸고
 * false인 애들이 소수임을 명시하는 알고리즘임
 */
public class 소수 {
	static boolean[] Eratosthenes = new boolean[10001];
	static int N,M;
	static int sum;
	static boolean possible;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		makeEratosthenes();

		for(int i=M;i<=N;i++){
			if(i==1) continue;
			if(!Eratosthenes[i]) {
				possible = true;
				System.out.println(sum);
				System.out.println(i);
				break;
			}
		}

		if(!possible) System.out.println("-1");
	}
	private static void makeEratosthenes() {
		for(int i=2;i<=N;i++){
			if(Eratosthenes[i]) continue;
			if(i>=M) sum+=i;
			for(int j=i+i;j<=N;j+=i){
				Eratosthenes[j] = true;
			}
		}
	}
}
