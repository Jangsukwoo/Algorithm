package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 최소값을 구하는거니 dfs는 적절한 해법이 되지 못함 
 */
public class 미로탐색_dfs {
	static char[][] maze;
	static int R,C;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean[][] visit;
	static int answer = Integer.MAX_VALUE;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		setData();
		visit[0][0]=true;
		dfs(0,0,1);
		System.out.println(answer);
	}
	private static void dfs(int cr, int cc, int depth){
		if(cr==R-1 && cc==C-1){
			answer = Math.min(depth,answer);
			return; 
		}
		for(int dir=0;dir<4;dir++){
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(rangeCheck(nr,nc)) {
				if(maze[nr][nc]=='1' && visit[nr][nc]==false) {
					visit[nr][nc] = true;
					dfs(nr,nc,depth+1);
					visit[nr][nc] = false;
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maze = new char[R][C];
		visit = new boolean[R][C];
		for(int row=0;row<R;row++) {
			maze[row] = br.readLine().toCharArray();
		}
	}
}
