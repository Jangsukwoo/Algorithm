package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MooyoMooyo {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,K;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static char[][] board;
	static boolean[][] visit;
	static boolean isNeedGravity;
	static int adjCnt;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new char[N][10];
		for(int i=0;i<N;i++) board[i] = br.readLine().toCharArray();
		getNewBoard();
		dfsAll();
		getAnswer();
	}
	private static void getAnswer() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<10;col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
	}
	private static void getNewBoard() {
		while(true) {
			isNeedGravity = false;
			dfsAll();//삭제 대상 전부 삭제처리 후
			if(isNeedGravity) gravity();
			if(isNeedGravity==false) break;
		}
	}
	private static void gravity() {
		for(int col=0;col<10;col++) {
			for(int row=N-1;row>=0;row--){
				if(board[row][col]=='0'){
					int nr = row;
					while(true){
						nr+=dr[0];
						if(rangeCheck(nr,col)) {
							if(board[nr][col]!='0') {
								board[row][col] = board[nr][col];
								board[nr][col] = '0';
								break;
							}
						}else break;
					}
				}
			}
		}
	}
	private static void dfsAll() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<10;col++) {
				if(board[row][col]!='0') {
					adjCnt=0;
					visit = new boolean[N][10];
					visit[row][col]= true;
					adjCnt++;
					dfs(row,col,board[row][col]);
					if(adjCnt>=K) {
						isNeedGravity = true;
						convertToZero();
					}
				}
			}
		}
	}
	private static void convertToZero() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<10;col++) {
				if(visit[row][col]) {
					board[row][col]='0';
				}
			}
		}
	}
	private static void dfs(int row, int col, char shape) {
		for(int dir=0;dir<4;dir++) {
			int nr = row+dr[dir];
			int nc = col+dc[dir];
			if(rangeCheck(nr,nc) && visit[nr][nc]==false && board[nr][nc]==shape) {
				adjCnt++;
				visit[nr][nc]= true;
				dfs(nr,nc,shape);
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<10) return true;
		return false;
	}
}
