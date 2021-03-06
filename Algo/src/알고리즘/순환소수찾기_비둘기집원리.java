package 알고리즘;

import java.util.Scanner;

public class 순환소수찾기_비둘기집원리 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("실행 :1 , 종료 : 0");
			int command = sc.nextInt();
			if(command==0) break;
			else {
				System.out.println("분수 a/b에서 a,b값 차례대로 입력");
				int a = sc.nextInt();
				int b = sc.nextInt();
				printDecimal(a,b);
				System.out.println();
			}
		}
		System.out.println("끝");
	}

	private static void printDecimal(int a, int b) {
		int iter=0;
		while(a>0) {
			//첫 번째와 두 번째 사이에 소수점 찍기
			if(iter++==1) System.out.print(".");
			System.out.print(a/b);
			a=(a%b)*10;
		}
	}
}
