package Simulation;

import java.util.ArrayList;
import java.util.Scanner;

public class 카드1 {
	static int N;
	static ArrayList<Integer> numberList = new ArrayList<Integer>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for(int i=1;i<=N;i++) numberList.add(i);
		while(true){
			sb.append(numberList.get(0)+" ");
			if(numberList.size()==1) break;
			numberList.remove(0);
			numberList.add(numberList.get(0));
			numberList.remove(0);
		}
		System.out.println(sb.toString());
	}
}
