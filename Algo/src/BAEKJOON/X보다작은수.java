package BAEKJOON;

import java.util.Scanner;

public class X보다작은수 {
	static int N,X;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		X = sc.nextInt();
		for(int i=0;i<N;i++) {
			int num = sc.nextInt();
			if(num<X) System.out.print(num+" ");
		}
	}
}
