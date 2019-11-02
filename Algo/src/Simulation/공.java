package Simulation;

import java.util.Scanner;

public class 공 {
	static int N;
	static int[] cup = new int[] {0,1,2};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for(int i=0;i<N;i++){
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			swap(a,b);
		}
		for(int i=0;i<3;i++) if(cup[i]==0) System.out.println(i+1);
	}
	private static void swap(int a, int b) {
		int tmp = cup[a];
		cup[a] = cup[b];
		cup[b] = tmp;
	}
}
