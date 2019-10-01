package Codeforce;

import java.util.Scanner;

//1번문제
public class EqualizePricesAgain {
	static int q; // 1 ≤ q ≤ 100
	static int n; // 1 ≤ n ≤ 100
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		q = sc.nextInt();
		for(int query=1;query<=q;query++) {
			n = sc.nextInt();
			double sum=0;
			int result=0;
			for(int i=0;i<n;i++) sum+=sc.nextInt();
			result = (int) Math.ceil((sum/n));
			System.out.println(result);
		}
	}
}
