package 탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 쉬운최단거리 {
	static int N,M;
	static int[][] map;
	static boolean[][] visit;
	static int[][] dist;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<int[]> q = new LinkedList<int[]>();
	/*
	 * 벽 : 0,도달할 수 없으면 -1
	 */
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dist = new int[N][M];
		visit = new boolean[N][M];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col]==2) insertQueue(row,col,0);
			}
		}
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				int cd = current[2];
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc) && visit[nr][nc]==false && map[nr][nc]!=0) {
						dist[nr][nc] = cd+1;
						insertQueue(nr, nc, cd+1);
					}
				}
			}
		}
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				if(visit[row][col]) bw.write(dist[row][col]+" ");
				else if(visit[row][col]==false && map[row][col]==0) bw.write(dist[row][col]+" ");
				else if(visit[row][col]==false && map[row][col]!=0) bw.write("-1"+" ");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
	private static void insertQueue(int cr, int cc, int dist) {
		visit[cr][cc] = true;
		q.add(new int[] {cr,cc,dist});
	}
}
