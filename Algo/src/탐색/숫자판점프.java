package 탐색;

import java.util.HashSet;
import java.util.Scanner;

public class 숫자판점프 {
	static Scanner sc = new Scanner(System.in);
	static int[][] board;
	static HashSet<String> possibleNumberSet;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int cnt;
	public static void main(String[] args) {
		board = new int[5][5];
		possibleNumberSet = new HashSet<String>();
		for(int row=0;row<5;row++)
			for(int col=0;col<5;col++) board[row][col] = sc.nextInt();
		for(int row=0;row<5;row++) {
			for(int col=0;col<5;col++) {
				String number_string = Integer.toString(board[row][col]);
				dfs(row,col,number_string,0);
			}
		}
		System.out.println(cnt);
		System.out.println(possibleNumberSet.size());
	}
	private static void dfs(int cr, int cc, String number_string, int depth) {
		cnt++;
		if(depth==5){
			possibleNumberSet.add(number_string);
			return;
		}
		for(int dir=0;dir<4;dir++) {
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(rangeCheck(nr,nc)) dfs(nr,nc,number_string+Integer.toString(board[nr][nc]),depth+1);
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<5 && nc>=0 && nc<5) return true;
		return false;
	}
}
