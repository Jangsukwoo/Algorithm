//package Samsung;
//
//import java.util.Scanner;
//
///*
// *냉장고 문제랑 비슷
// *안겹치는 것의 합의 최대값
// *-------------------DP로 접근 다시 풀기---------------------------
// */
//class DayReservation{
//	int term;
//	int pay;
//	public DayReservation(int t, int p) {
//		term = t;
//		pay = p;
//	}
//}
//public class 기출_퇴사 {
//	static int N;
//	static int[] maxDaybenefit;
//	static int maxBenefit;
//	static DayReservation[] reservation;
//	public static void main(String[] args) {
//			Scanner sc = new Scanner(System.in);
//			N = sc.nextInt();
//			maxDaybenefit = new int[N];
//			reservation = new DayReservation[N];
//			for(int i=0;i<N;i++) {
//				int t = sc.nextInt();
//				int p = sc.nextInt();
//				reservation[i] = new DayReservation(t,p);
//			}//입력 끝
//			
//			for(int daystart=0;daystart<N;daystart++){//매일 최대 수익 메모
//				int max =0;
//				int day = daystart;
//				while(true) {
//					int nextday=day+reservation[day].term;
//					if(nextday>N) break;
//					if(nextday==N) {
//						max += reservation[day].pay;
//						break;
//					}
//					if(nextday<N) {
//						max += reservation[day].pay;
//						day = nextday;
//					}
//				}
//				maxBenefit = Math.max(maxBenefit,max);
//			}	
//			System.out.println(maxBenefit);
//	}
//}
