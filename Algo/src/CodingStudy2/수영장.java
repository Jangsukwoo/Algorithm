package CodingStudy2;

import java.util.Scanner;

/*
 * 이용권의 종류 1일,1달,3달,1년
 * 11월 12월에도 3달 이용권 사용가능
 * 
 * 모든 이용권을 섞어서 쓰는 경우의 수 -> DFS
 * Ex)
 * 1일 이후 또 1일,1달,3달,12년 쓰는 경우의수
 * 이후 또 1일, 1달, 3달, 12년을 쓰는 경우의수 .......
 * 
 */
public class 수영장 {
	static int T; //Testcase
	static int[] monthPlan; //달별 이용계획
	static int dayTicket; //1일 이용권 금액
	static int monthTicket; //1달 이용권 금액;
	static int threeMonthTicket; //3달 이용권 금액
	static int yearTicket; //1년 이용권 금액
	static int minFee; //최소요금 
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
			dfs(0,0);//첫번쨰 달부터 모든 티켓을 이용하는 경우의 수 (가지)
			System.out.println("#"+testcase+" "+minFee);
		}
	}
	private static void dfs(int month,int fee){//모든 티켓을 이용하는 경우에 대해서 재귀로 조사한다.
		if(month>=12){//더 이상 이용권을 사용할 수 없을 때 
			minFee = Math.min(fee,minFee);//최소 요금 업데이트
			return;
		}
		dfs((month+1),(fee+(monthPlan[month]*dayTicket)));//1일 이용권을 사용하는 경우
		dfs((month+1),(fee+monthTicket));//1달 이용권을 사용하는 경우
		dfs((month+3),(fee+threeMonthTicket));//3달 이용권을 사용하는 경우
		dfs((month+12),(fee+yearTicket));//1년치 이용권을 사용하는 경우
	}
}
