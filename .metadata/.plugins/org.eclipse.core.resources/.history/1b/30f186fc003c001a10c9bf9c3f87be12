package SDS;

import java.util.Scanner;

/*
 * 퀸과 퀸은 서로 직선,대각선상에 놓이면 안된다.
 */
public class Nqueen_1일차 {
	static boolean[][] map;
	static int N;//N by N에서 퀸 N개 놓는 방법 
	static int cnt;
	static int[] rowcol = new int[15];//0행 1열 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new boolean[N][N];
		dfs(0);
		System.out.println(cnt);
	}
	private static void dfs(int currentRow){
		if(currentRow==N) {
			cnt++;
			return;
		}
		else {
			boolean check = false;
			for(int col=0;col<N;col++) {
				rowcol[currentRow] = col; //행 열 
				if(currentRow==0) check=true;
				for(int row=0;row<currentRow;row++) {
					if(rowcol[currentRow]==rowcol[row] || Math.abs(rowcol[currentRow]-rowcol[row])==currentRow-row) {
						//열이 같거나 대각선이 같으면 break
						check = false;
						break;
					}else {//만족함
						if(row==(currentRow-1)){
							check = true;
							break;
						}
					}
				}
				if(check) dfs(currentRow+1);
			}
		}
	}
}
