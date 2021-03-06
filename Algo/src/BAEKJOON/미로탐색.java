package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * dfs버전 추가
 */
public class 미로탐색 {
	static int N,M;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1}; 
	static char[][] map;
	static boolean[][] visit;
	static int dist;
	static boolean goal;
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		visit = new boolean[N][M];
		dist=1;
		sc.nextLine();
		for(int row=0;row<N;row++) {
			String readLine = sc.nextLine();
			map[row] = readLine.toCharArray();
		}
		//q.add(new int[] {0,0});
		//visit[0][0] = true;
		//BFS();
		
		visit[0][0] = true;
		dfs(0,0,1);
		System.out.println(dist);
	}
	private static void dfs(int cr, int cc, int detph){
		if(goal) return;
		if(cr==(N-1) && cc==(M-1)) {
			goal = true;
			dist = detph;
			return;
		}
		for(int dir=0;dir<4;dir++) {
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(rangeCheck(nr, nc) && map[nr][nc]!='0' && visit[nr][nc]==false){
				visit[nr][nc] = true;
				dfs(nr,nc,detph+1);
				visit[nr][nc] = false;
			}
		}
		
	}
	private static void BFS() {
		while(!q.isEmpty()){
			
			dist++;
			
			int size = q.size();
			
			for(int i=0;i<size;i++){
				
				int[] curRC = q.poll();
				
				for(int dir=0;dir<4;dir++) {
					
					int nr = curRC[0] + dr[dir];
					int nc = curRC[1] + dc[dir];
					
					if(rangeCheck(nr,nc)){
						
						if(visit[nr][nc]==false && map[nr][nc]=='1'){
							if(nr==(N-1) && nc==(M-1)) return ;//도착지점이면 그냥 끝냄
							q.add(new int[] {nr,nc});
							visit[nr][nc] = true;
						}
					}
				}
			}
			
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc <M) return true;
		return false;
	}
}
