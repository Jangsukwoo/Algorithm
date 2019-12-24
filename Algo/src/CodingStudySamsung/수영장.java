package CodingStudySamsung;

import java.util.Scanner;

/*
 * 이용권의 종류 1일,1달,3달,1년
 * 11월 12월에도 3달 이용권 사용가능
 * 
 * 모든 이용권을 섞어서 쓰는 경우의 수 -> DFS
 */
public class 수영장 {
	static int T;
	static int[] monthPlan;
	static int dayTicket;
	static int monthTicket;
	static int threeMonthTicket;
	static int yearTicket;
	static int minFee;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++) {
			dayTicket = sc.nextInt();
			monthTicket = sc.nextInt();
			threeMonthTicket = sc.nextInt();
			yearTicket = sc.nextInt();
			monthPlan = new int[12];
			minFee = 987654321;
			for(int i=0;i<12;i++) monthPlan[i] = sc.nextInt();
			//처리
			dfs(0,0);
			System.out.println("#"+testcase+" "+minFee);
		}
	}
	private static void dfs(int month,int fee) {
		if(month>=12){
			minFee = Math.min(fee,minFee);
			return;
		}
		dfs((month+1),(fee+(monthPlan[month]*dayTicket)));
		dfs((month+1),(fee+monthTicket));
		dfs((month+3),(fee+threeMonthTicket));
		dfs((month+12),(fee+yearTicket));
	}
}
