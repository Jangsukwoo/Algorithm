package CodingStudyHW;

import java.util.Scanner;
/*
 * 물통 A,B,C 세개가 있는데
 * 첫번째 물통 A가 비워져있을 때 
 * C물 통에 담겨져있는 물로 가능한 모든 리터 경우를 출력
 * 
 */
public class 물통 {
	static int A,B,C;
	static int[] amount = new int[201];
	public static void main(String[] args) {
		setData();
		bfs();
	}
	private static void bfs() {
	}
	private static void setData() {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
	}
}
