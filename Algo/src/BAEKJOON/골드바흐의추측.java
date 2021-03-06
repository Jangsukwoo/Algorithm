package BAEKJOON;

import java.util.Scanner;


public class 골드바흐의추측 {
	static int N;
	static boolean[] Eratosthenes = new boolean[10001];
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		makeEratosthenes();
		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++) {
			N = sc.nextInt();
			int tmpA = 0;
			int tmpB = 0;
			int A=0;
			int B=0;
			int gap = Integer.MAX_VALUE;
			for(int i=2;i<=(N/2);i++){
				tmpA = i;
				tmpB = N-i;
				if(!Eratosthenes[tmpA] && !Eratosthenes[tmpB] && (tmpA+tmpB)==N && gap>(tmpB-tmpA)) {
					gap = tmpB-tmpA;
					A = tmpA;
					B = tmpB;
				}
			}
			System.out.println(A+" "+B);
		}
	}
	private static void makeEratosthenes() {
		for(int i=2;i<=10000;i++){
			if(Eratosthenes[i]) continue;
			for(int j=i+i;j<=10000;j+=i){
				Eratosthenes[j] = true;
			}
		}
	}
}
