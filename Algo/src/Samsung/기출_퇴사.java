package Samsung;

import java.util.Scanner;

/*
 *냉장고 문제랑 비슷
 *안겹치는 것의 합의 최대값
 *-------------------DP로 접근 다시 풀기---------------------------
 *
 *를 하려 했으나
 *삼성은 완탐이 답인듯..DP로 접근했다가 너무 시간낭비했다..ㅠ
 */
class DayReservation{
	int term;
	int pay;
	public DayReservation(int t, int p) {
		term = t;
		pay = p;
	}
}
public class 기출_퇴사 {
	static int N;
	static int[] maxDaybenefit;
	static int maxBenefit;
	static DayReservation[] reservation;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		maxDaybenefit = new int[N];
		reservation = new DayReservation[N];
		for(int i=0;i<N;i++) {
			int t = sc.nextInt();
			int p = sc.nextInt();
			reservation[i] = new DayReservation(t,p);
		}//입력 끝
		for(int startDay=0;startDay<N;startDay++) {
			int benefit=0;
			dfs(startDay,benefit);
		}
		System.out.println(maxBenefit);
	}
	private static void dfs(int startDay,int benefit) {
		if(startDay>=N){ //퇴소한 후 면 종료
			maxBenefit = Math.max(maxBenefit,benefit);
			return;
		}
		else{//아니면, 그날 상담을 진행하거나 다음날로 가보기.
			int nextDay = startDay+ reservation[startDay].term;
			dfs(startDay+1,benefit);//이날 상담 안하고 그냥 다음날 진행
			if(nextDay<=N) {
				benefit = benefit + reservation[startDay].pay;
				dfs(nextDay,benefit);
			}
		}
		
	}
		
}

