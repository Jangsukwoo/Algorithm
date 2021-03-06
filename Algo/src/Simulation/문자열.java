package Simulation;

import java.util.Scanner;
/*
 * 7:40~
 * A의 앞 또는 뒤로 아무 알파벳 하나를 추가해서
 * B와 길이가 같으면서 차이가 최소로 하는 프로그램 작성
 * 7:53
 */
public class 문자열 {
	static String A;
	static String B;
	static int sizeA,sizeB;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.next();
		B = sc.next();
		sizeA = A.length();
		sizeB = B.length();
		for(int i=0;i<=(sizeB-sizeA);i++){
			int cnt=0;
			for(int j=0;j<sizeA;j++){
				if(A.charAt(j)!=B.charAt(i+j)) cnt++;
			}
			min = Math.min(min,cnt);
		}
		System.out.println(min);
	}
}
