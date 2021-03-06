package BAEKJOON;

import java.util.Scanner;

/*
 * Eratosthenes' sieve 
 * 음.. 소수 중에 2보다 큰 짝수는 있을 수 없음
 * 에라토스 테네스의 체로 풀면
 * 2부터 시작해서 배수들을 전부 true로 바꾸고
 * false인 애들이 소수임을 명시하는 알고리즘임
 * 
 * https://blog.naver.com/yechan54/10168144809 
 * O(N)으로 푸는 에라토스테네스 소스
 * 
 * 
 */
public class 소수구하기 {
	static boolean flag;
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		makeEratosThenesList();
		System.out.println(sb.toString());
	}
	private static void makeEratosThenesList() {
        for(int i=M;i<=N;i++){
        	if((i>3 && i%2==0) || i==1) continue; //짝수는 볼필요 X
            flag = true;
            int value = (int) Math.sqrt(i);
            for(int j=value;j>=2;j--){
                //자기보다 작은애들 나눴는데 0이 나왔으면 소수가 아님.
                if(i%j==0) {
                    flag = false; //실패
                    break;
                }
            }
            if(flag) sb.append(i+"\n");
        } 
	}
}
