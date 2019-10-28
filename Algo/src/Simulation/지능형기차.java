package Simulation;

import java.util.Scanner;

public class 지능형기차 {
	static int maxPeople;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int people=0;
		for(int i=0;i<4;i++) {
			int getOFF = sc.nextInt();//하차
			int getON = sc.nextInt();//승차
			people-=getOFF;
			people+=getON;
			maxPeople = Math.max(maxPeople,people);
		}
		System.out.println(maxPeople);
	}
}
