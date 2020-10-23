package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class IcyPerimeter {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] machine;
	static boolean[][] visit;
	static PriorityQueue<Blob> pq;
	static int area,perimeter ;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static class Blob implements Comparable<Blob>{
		int area; //영역 크기
		int perimeter ; //둘레
		public Blob(int area, int perimeter ) {
			this.area = area;
			this.perimeter  = perimeter ;
		}
		@Override
		public int compareTo(Blob o) {
			if(this.area==o.area) {
				return Integer.compare(this.perimeter ,o.perimeter );
			}
			return -Integer.compare(this.area,o.area);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		machine= new char[N][N];
		for(int row=0;row<N;row++) machine[row] = br.readLine().toCharArray();
		visit = new boolean[N][N];
		pq = new PriorityQueue<Blob>();
		dfsAll();
		System.out.println(pq.peek().area+" "+pq.peek().perimeter );
	}
	private static void dfsAll() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(visit[row][col]==false && machine[row][col]=='#') {
					visit[row][col] = true;
					area = 1;
					perimeter  = 0;
					dfs(row,col);
					pq.add(new Blob(area, perimeter));
				}
			}
		}
	}
	private static void dfs(int row, int col) {
		for(int dir=0;dir<4;dir++) {
			int nr = row+dr[dir];
			int nc = col+dc[dir];
			if(rangeCheck(nr,nc)){
				if(visit[nr][nc]==false && machine[nr][nc]=='#') {
					area++;
					visit[nr][nc] = true;
					dfs(nr,nc);
				}else if(machine[nr][nc]=='.') perimeter ++;
			}else perimeter ++;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
}
