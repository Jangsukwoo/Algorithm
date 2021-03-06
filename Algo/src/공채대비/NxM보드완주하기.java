package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 17:45~
 */
public class NxM보드완주하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] board;
	static boolean[][] visit;
	static int R,C;
	static int dotSize;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int answer;
	public static void main(String[] args) throws IOException {
		int testcase=0;
		while(true){
			testcase++;
			String str = null;
			str = br.readLine();
			if(str==null) return;
			st = new StringTokenizer(str);
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			setData();
			for(int row=0;row<R;row++) {
				for(int col=0;col<C;col++) {
					if(board[row][col]=='.') {
						visit[row][col] = true;
						dfs(row,col,dotSize-1,-1,0);
						visit[row][col] = false;	
					}
				}
			}
			
			if(answer==Integer.MAX_VALUE)System.out.println("Case "+testcase+": -1");
			else System.out.println("Case "+testcase+": "+answer);
		}
	}
	private static void dfs(int cr, int cc, int dotCnt, int currentDir,int depth) {
		if(dotCnt==0){
			answer = Math.min(depth,answer);
			return ;
		}
		for(int dir=0;dir<4;dir++) {
			boolean go = false;
			if(currentDir!=dir) {
				int dot = dotCnt;
				int nr = cr+dr[dir];
				int nc = cc+dc[dir];
				while(rangeCheck(nr,nc) && visit[nr][nc]==false && board[nr][nc]!='*') {
					visit[nr][nc] = true;
					dot--;
					nr+=dr[dir];
					nc+=dc[dir];
					go =true;
				}
				nr-=dr[dir];
				nc-=dc[dir];
				if(go) {
					dfs(nr,nc,dot,dir,depth+1);
					while(true){
						visit[nr][nc] = false;
						nr-=dr[dir];
						nc-=dc[dir];
						if(nr==cr && nc==cc) break;
					}
				}

			}
		}
	}
	private static void view() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				System.out.print(visit[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException{
		board = new char[R][C];
		visit = new boolean[R][C];
		dotSize = 0;
		answer = Integer.MAX_VALUE;
		for(int row=0;row<R;row++) {
			board[row] = br.readLine().toCharArray();
			for(int col=0;col<C;col++) {
				if(board[row][col]=='.') {
					dotSize++;
				}
			}
		}
	}
}
