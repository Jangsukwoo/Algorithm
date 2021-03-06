package BAEKJOON;

import java.util.Scanner;

/*
 * A->B 백준 16953번
 * 정수 A와 B를 입력받아서
 * 다음의 연산을 통해 A를 B를 만들려고 한다.
 * 1. x2
 * 2. 1을 수의 가장 오른쪽에 추가
 * 
 * long으로 해줘야한다.
 * 인트가 10의 9승 x2인데
 * *10 연산 들어가면서 int 자료형을 넘어선 값 때문에 답이 이상하게 나왔다.
 * 
 */
public class FromAtoB {
	static long A,B;
	static boolean flag;
	static long result = Long.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextLong();
		B = sc.nextLong();
		dfs(A,0);
		if(result!=Long.MAX_VALUE)System.out.println(result+1);
		else System.out.println("-1");
	}
	private static void dfs(long a,long cnt) {
		if(a>B) return;
		if(a==B){
			result = Math.min(result,cnt);
			return;
		}
		dfs(a*2,cnt+1);
		dfs((a*10)+1,cnt+1); //int로 하면 이부분에서 터진다.
	}
}
