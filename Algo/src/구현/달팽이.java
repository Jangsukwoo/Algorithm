package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 달팽이 {
	static int n,m;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] board;
	static boolean[][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		view();
	}
	private static void simulation() {
		int number = 1;
		boolean flag = false;
		int dir = 1;
		int nr = 0;
		int nc = 0;
		while(number<=(n*m)){
			if(visitCheck(nr,nc)) {
				board[nr][nc] = number++;
				visit[nr][nc] = true;	
				nr+=dr[dir];
				nc+=dc[dir];
			}else {
				nr-=dr[dir];
				nc-=dc[dir];
				dir = (dir+1)%4;
				nr+=dr[dir];
				nc+=dc[dir];
			}
		}
	}
	private static void view() {
		for(int row=0;row<n;row++) {
			for(int col=0;col<m;col++) {
				System.out.print(board[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static boolean visitCheck(int nr, int nc){
		if(nr>=0 && nr<n && nc>=0 && nc<m){
			if(visit[nr][nc]==false) return true;
		}
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		visit = new boolean[n][m];
	}
}
