package CodingStudyHW;

import java.util.Scanner;

/*
 * 나무는 1,2 두그루가 있다.
 * 자두가 떨어질 때 까지 기다림
 * 자두는 1<=T<=1000초 동안 떨어짐
 * 자두는 최대 1<=W<=30만큼 움직이고싶음
 * 자두가 받을 수 있는 최대 자두 수?
 * 자두는 1번 자두 나무 위치에 있다고 한다.
 * 
 * 3차원으로 점화식 세우려고했는데 아이디어가 잘 떠오르지 않아서 인터넷 참고함
 * 
 * 완벽히 이해하진 못했으나 시간낭비가 너무 심해서 천천히 이해해보자
 */

public class 자두나무 {
	static int T;
	static int W;
	static int[] postion;
	static int[][][] memo;
	static int max;
	public static void main(String[] args) {
		setData();
		memoization();
		System.out.println(max);
	}
	private static void memoization() {
		for(int time=1;time<=T;time++){
			//매 초 확인하는데
			for(int move=1;move<=(W+1);move++) {//이동했다는 가정
				//몇번 움직이면서 왔는가를 확인하면서 최대값 저장
				if(postion[time]==1){//현재 받아먹는 위치가 1이면
					memo[1][time][move]= Math.max(memo[1][time-1][move]+1,(memo[2][time-1][move-1]+1));
					memo[2][time][move]= Math.max(memo[1][time-1][move-1],(memo[2][time-1][move]));
				}else {
					if(time==1 && move==1) continue;
					memo[1][time][move]= Math.max(memo[2][time-1][move-1],(memo[1][time-1][move]));
					memo[2][time][move]= Math.max(memo[1][time-1][move-1]+1,(memo[2][time-1][move])+1);
				}
			}
		}
		for(int i=1;i<=(W+1);i++) {
			max = Math.max(max,Math.max(memo[1][T][i],memo[2][T][i]));
		}
	}
	private static void setData() {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		W = sc.nextInt();
		postion = new int[T+1];
		memo = new int[3][T+1][W+2];
		for(int i=1;i<=T;i++) postion[i] = sc.nextInt();
	}
}


/*
 * 인터넷 참고함
 * 
 * 1번 나무에서 시작하고
 * 한번 움직이면 2
 * 두번움직이면 1
 * 세번 움직이면 2
 * 
 * n번 움직이면 
 * n이 짝수일때 1
 * 홀수일떄 2
 * 
 * 
 * 
 */

//public class 자두나무 {
//	static int T;
//	static int W;
//	static int[] postion;
//	static int[][] memo;
//	static int max;
//	public static void main(String[] args) {
//		setData();
//		memoization();
//		if(max==0 && postion[1]==1) System.out.println("1");
//		else System.out.println(max);
//	}
//	private static void memoization() {
//		for(int time=1;time<=T;time++){
//			//매 초 확인하는데
//			if(postion[time]==1) { //떨어질 자두 위치가 1이면
//				memo[time][0]=memo[time-1][0]+1;
//			}else memo[time][0]=memo[time-1][0];
//			
//			for(int move=1;(move<=time && move<=W) ;move++){
//				//처음 위치부터 현재까지 움직인 위치까지, 이동할 수 있는 최대 위치까지
//				if(postion[time]==1 && move%2==0) {
//					memo[time][move] = Math.max(memo[time-1][move],memo[time-1][move-1])+1;
//				}else if(postion[time]==2 && move%2==1) {
//					memo[time][move] = Math.max(memo[time-1][move],memo[time-1][move-1])+1;					
//				}else {
//					memo[time][move] = Math.max(memo[time-1][move],memo[time-1][move-1]);					
//				}
//				max = Math.max(max,memo[time][move]);
//			}
//		}
//	}
//	private static void setData() {
//		Scanner sc = new Scanner(System.in);
//		T = sc.nextInt();
//		W = sc.nextInt();
//		postion = new int[T+1];
//		memo = new int[T+1][W+1];
//		for(int i=1;i<=T;i++) postion[i] = sc.nextInt();
//	}
//}
