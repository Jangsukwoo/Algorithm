package codeTest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 유기농배추 {
	static int M,N,K;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] farm;
	static int worm;
	static boolean[][] visit;
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++) {
			M = sc.nextInt();//가로
			N = sc.nextInt();//세로
			K = sc.nextInt();
			farm = new int[N][M];
			visit = new boolean[N][M];
			worm = 0;
			for(int k=0;k<K;k++) {
				int X = sc.nextInt();
				int Y = sc.nextInt();
				farm[Y][X]=1;
			}
			//맵 생성
			
			for(int row=0;row<N;row++) {
				for(int col=0;col<M;col++) {
					if(farm[row][col]==1 && visit[row][col]==false){
						worm++;
						visit[row][col] = true;
						q.add(new int[] {row,col});
						BFS();
					}
				}
			}
			System.out.println(worm);
		}
	}
	private static void BFS() {
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] popRC = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = popRC[0]+dr[dir];
					int nc = popRC[1]+dc[dir];
					if(rangeCheck(nr,nc)) {//경계 안에 있다.
						if(farm[nr][nc]==1 && visit[nr][nc]==false) {
							q.add(new int[] {nr,nc});
							visit[nr][nc]=true;
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 & nr<N && nc>=0 && nc<M) return true;
		return false;
	}
}
