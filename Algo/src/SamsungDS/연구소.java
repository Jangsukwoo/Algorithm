package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소 {
	/*
	 * 0 : 빈칸
	 * 1 : 벽
	 * 2 : 바이러스
	 * 
	 * 벽은 반드시 3개
	 */
	static int R,C;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<int[]> blankList = new ArrayList<int[]>();
	static ArrayList<int[]> virusList = new ArrayList<int[]>();
	static int[][] lab;
	static int[][] copyLab;
	static boolean[][] visit;
	static int[] pick = new int[3];
	static Queue<int[]> q = new LinkedList<int[]>();
	static int maxArea;
	public static void main(String[] args) throws IOException {
		setData();
		nCr(0,0);//조합 dfs
		System.out.println(maxArea);
	}
	private static void nCr(int idx, int depth) {
		if(depth==3){ //벽 설치할 자리 3개 선정 끝
			copyLab(); //맵 복사
			installWall(); //벽 설치
			bfs(); //바이러스 확산
			//view();
			int area = getArea(); 
			maxArea = Math.max(area,maxArea); //최대 영역 update
			return;
		}
		for(int i=idx;i<blankList.size();i++) {
			pick[depth] = i;
			nCr(i+1, depth+1);
		}
	}
	private static int getArea() {
		int area = 0;
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(copyLab[row][col]==0) area++;
			}
		}
		return area;
	}
	private static void bfs(){
		initialization();
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentVirusRC = q.poll();
				int cr = currentVirusRC[0];
				int cc = currentVirusRC[1];
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc) 
							&& visit[nr][nc]==false 
							&& copyLab[nr][nc]!=1) {
						//영역만족하고, 방문하지 않았고, 벽이 아니면
						visit[nr][nc] = true;
						copyLab[nr][nc] = 2;
						q.add(new int[] {nr,nc});
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void initialization() {
		visit = new boolean[R][C];
		for(int i=0;i<virusList.size();i++) {
			int[] virusRC = virusList.get(i);
			int row = virusRC[0];
			int col = virusRC[1];
			q.add(virusRC);
			visit[row][col] = true;
		}
	}
	private static void installWall() {
		for(int i=0;i<3;i++) {
			int pickNumber = pick[i];
			int[] RC = blankList.get(pickNumber);
			int row = RC[0];
			int col = RC[1];
			copyLab[row][col] = 1;
		}
	}
	private static void copyLab() {
		copyLab= new int[R][C];
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				copyLab[row][col] = lab[row][col];  
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		lab = new int[R][C];
		for(int row=0;row<R;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<C;col++) {
				lab[row][col] = Integer.parseInt(st.nextToken());
				if(lab[row][col]==0) blankList.add(new int[] {row,col});
				if(lab[row][col]==2) virusList.add(new int[] {row,col});
			}
		}
	}
}
