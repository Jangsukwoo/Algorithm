package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoDots {
	static int R,C;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static char[][] gameMap;
	static boolean[][] visit;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean cycle;
	public static void main(String[] args) throws IOException {
		setData();
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				dfs(row,col,row,col,1);
				if(cycle) break;
			}
			if(cycle) break;
		}
		if(cycle) System.out.println("yes");
		else System.out.println("no");
	}
	private static void dfs(int tr, int tc, int cr, int cc, int dist) {
		if(cycle) return;
		visit[cr][cc] = true;
		for(int dir=0;dir<4;dir++) {
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(rangeCheck(nr,nc) && sameCheck(cr,cc,nr,nc)){
				if(visit[nr][nc]==false) {
					visit[nr][nc] = true;
					dfs(tr,tc,nr,nc,dist+1);
					visit[nr][nc] = false;
				}else if(dist>=4 && nr==tr && nc==tc){
					cycle = true;
					break;
				}
			}//영역만족하고 가려는 곳이 같으면
		}
	}
	private static boolean sameCheck(int nr, int nc, int cr, int cc) {
		if(gameMap[nr][nc]==gameMap[cr][cc]) return true;
		return false;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		gameMap = new char[R][C];
		visit = new boolean[R][C];
		for(int row=0;row<R;row++) gameMap[row] = br.readLine().toCharArray();
	}
}
