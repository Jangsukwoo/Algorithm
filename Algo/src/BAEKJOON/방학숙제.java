package BAEKJOON;

import java.util.Scanner;

public class 방학숙제 {
	static int L,A,B,C,D;
	static int korean,math;
	static int homeWorkDay;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt(); //방학
		A = sc.nextInt(); //국어
		B = sc.nextInt(); //수학
		C = sc.nextInt(); //국어 최대
		D = sc.nextInt(); //수학 최대
		if(A%C==0) korean = A/C;
		else korean =(A/C)+1;
		if(B%D==0) math = B/D;
		else math = (B/D)+1;
		homeWorkDay = Math.max(korean,math);
		System.out.println(L-homeWorkDay);
	}
}
