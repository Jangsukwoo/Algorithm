package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미로탈출하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] maze;
	static boolean[][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static int[][] memo;
	static int possiblePath;
	static StringTokenizer st;
	static int N,M;
	public static void main(String[] args) throws IOException {
		setData();
		dfsAll();
		System.out.println(possiblePath);
	}
	private static void dfsAll() {
		/*
		 * 모든 칸에서 시작해본다.
		 */
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) { //최악일 때 250000
				if(memo[row][col]==0) memo[row][col] = dfs(row,col);
				if(memo[row][col]==1) possiblePath++;
			}
		}
		
	}
	private static int dfs(int cr, int cc) {
		visit[cr][cc] = true;
		
		int dir=getDirection(cr,cc);
		
		int nr = cr+dr[dir];
		int nc = cc+dc[dir];
		
		if(!rangeCheck(nr,nc)) return 1; //영역을 벗어났다면 
		
		if(memo[nr][nc]>0) return memo[nr][nc]; //메모가 되어있는 땅을 밟는 거라면 
		
		if(visit[nr][nc]==false) memo[nr][nc] = dfs(nr,nc); //방문 못한 땅은 메모
		
		/*
		 * 방문한점
		 */
		if(memo[nr][nc]==1) { 
			memo[cr][cc] = 1;
			return 1;
		}
		else return 2;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
	
	
	
	private static int getDirection(int cr, int cc) {
		int dir = 0;
		switch (maze[cr][cc]) {
		case 'U':
			dir = 0;
			break;
		case 'R':
			dir = 1;
			break;
		case 'D':
			dir = 2;
			break;
		case 'L':
			dir = 3;
			break;
		}
		return dir;
	}
	
	
	
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		memo = new int[N][M];
		visit = new boolean[N][M];
		for(int row=0;row<N;row++) {
			String readLine= br.readLine();
			for(int col=0;col<M;col++) {
				maze[row][col] = readLine.charAt(col);
			}
		}
	}
}
