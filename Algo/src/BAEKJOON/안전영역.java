package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 
5
1 8 2 6 2
3 2 3 4 6
6 7 3 3 2
7 2 5 3 6
8 9 5 100 7

5
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
 */

public class 안전영역 {
	static int N;
	static int maxHeight;
	static int[][] zone;
	static int maxArea;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][] visit;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		rain();
		System.out.println(maxArea);
	}
	private static void rain(){
		//비내리는 양에 대한 모든 조사
		for(int rainfall=0;rainfall<=maxHeight;rainfall++) bfsAll(rainfall);
	}
	private static void bfsAll(int rainfall){
		q.clear();
		visit = new boolean[N][N];
		int areacnt=0;
		for(int row=0;row<N;row++){
			for(int col=0;col<N;col++) {
				if(zone[row][col]>rainfall && visit[row][col]==false){
					areacnt++;
					insertQueue(row,col);
					bfs(rainfall);
				}
			}
		}
		maxArea = Math.max(maxArea,areacnt);
	}
	private static void bfs(int rainfall) {
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentArea = q.poll();
				int cr = currentArea[0];
				int cc = currentArea[1];
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(zone[nr][nc]>rainfall && visit[nr][nc]==false) {
							insertQueue(nr,nc);
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void insertQueue(int row, int col) {
		q.add(new int[] {row,col});
		visit[row][col] = true;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		zone = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				zone[row][col] = Integer.parseInt(st.nextToken());
				if(zone[row][col]>maxHeight) maxHeight = zone[row][col];
			}
		}
	}
}
