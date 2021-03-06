package 탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 섬의개수 {
	static int w,h;
	static int[][] map;
	static boolean[][] visit;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int answer;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws IOException {
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w ==0 && h ==0) break;
			map = new int[h][w];
			setData();
			dfsAll();
			bw.write(answer+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void dfsAll() {
		visit = new boolean[h][w];
		answer = 0;
		for(int row=0;row<h;row++) {
			for(int col=0;col<w;col++) {
				if(map[row][col]==1 && visit[row][col]==false) {
					//땅이고 방문 안해봤으면
					dfs(row,col);
					answer++;
				}
			}
		}
	}
	private static void dfs(int cr, int cc) {
		visit[cr][cc] = true;
		for(int dir=0;dir<8;dir++){
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(rangeCheck(nr,nc) && islandCheck(nr,nc)){
				dfs(nr,nc);
			}
		}
	}
	private static boolean islandCheck(int nr, int nc) {
		if(visit[nr][nc]==false && map[nr][nc]==1) return true;
		return false;
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<h && nc>=0 && nc<w) return true;
		return false;
	}
	private static void setData() throws IOException {
		for(int row=0;row<h;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<w;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
