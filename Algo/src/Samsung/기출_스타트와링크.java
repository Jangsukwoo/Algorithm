package Samsung;

import java.util.Scanner;

/*
 * 시작 750
 * N = 짝수
 * 끝 820
 * 걸린시간 30분
 */
public class 기출_스타트와링크 {
	static int N;//팀수
	static int gap = Integer.MAX_VALUE;
	static int r;
	static int[][] abilityTable;
	static boolean[] pickTable;
	static int[] pickTeam; //선택된 팀
	static int[] anotherTeam;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		r = N/2;
		pickTable = new boolean[N];
		pickTeam = new int[r];
		anotherTeam = new int[r];
		abilityTable = new int[N][N];
		for(int row=0; row<N;row++)
			for(int col=0;col<N;col++) abilityTable[row][col] = sc.nextInt();
		//입력 끝
		
		nCr(0,0);
		
		System.out.println(gap);
	}
	private static void nCr(int idx, int cnt) {
		if(cnt==r){
			makeAnotherTeam();
			calculation();
			return;
		}
		for(int i = idx;i<N;i++){
			pickTeam[cnt] = i;
			pickTable[i] = true;
			nCr(i+1,cnt+1);
			pickTable[i] = false;
		}
	}
	private static void calculation() {
		int firstTeamScore =0;
		int secondTeamScore =0;
		int value=0;
		for(int i=0;i<r;i++) {
			for(int j=0;j<r;j++) {
				firstTeamScore+=abilityTable[pickTeam[i]][pickTeam[j]];
			}
		}
		for(int i=0;i<r;i++) {
			for(int j=0;j<r;j++) {
				secondTeamScore+=abilityTable[anotherTeam[i]][anotherTeam[j]];
			}
		}
		value = Math.abs(firstTeamScore-secondTeamScore);
		gap = Math.min(gap,value);
	}
	private static void makeAnotherTeam() {
		int cnt=0;
		for(int i=0;i<N;i++){
			if(pickTable[i]==false) {
				anotherTeam[cnt] = i;
				cnt++;
			}
		}
	}
}
