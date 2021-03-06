package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 단지번호붙이기_dfs {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] area;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean[][] visit;
	static int areaCount;
	static int areaSize;
	static ArrayList<Integer> answerlist = new ArrayList<Integer>(); 
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		dfsAll();
		answerSorting();
		System.out.println(areaCount);
		for(Integer answer : answerlist) System.out.println(answer);
	}

	private static void answerSorting() {
		Collections.sort(answerlist);
	}

	private static void dfsAll() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(visit[row][col]==false && area[row][col]=='1') {
					areaCount++;
					areaSize=0;
					dfs(row,col);
					answerlist.add(areaSize);
				}
			}
		}
	}

	private static void dfs(int cr, int cc) {
		visit[cr][cc] = true;
		areaSize++;
		for(int dir=0;dir<4;dir++) {
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(rangeCheck(nr,nc)) {
				if(visit[nr][nc]==false && area[nr][nc]=='1') dfs(nr,nc);
			}
		}
	}

	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}

	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		area = new char[N][N];
		visit = new boolean[N][N];
		for(int row=0;row<N;row++) area[row] = br.readLine().toCharArray();
	}
}
