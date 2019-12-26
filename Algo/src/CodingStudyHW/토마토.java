package CodingStudyHW;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * M은 상자 가로의 개수(열)
 * N은 상자 세로의 개수(행)
 * 1<=M,N<=1000
 * 
 * 1:익은 토마토
 * 0:익을 토마토
 * -1:토마토가 없음
 */
public class 토마토{
	static int N,M;//행,열
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int day;
	static int[][] box;
	static boolean[][] visit;
	static Queue<int[]> q;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //열
		M = sc.nextInt(); //행
		box = new int[M][N];
		visit= new boolean[M][N];
		q = new LinkedList<int[]>();
		for(int row=0;row<M;row++) {
			for(int col=0;col<N;col++) {
				box[row][col] = sc.nextInt();
				if(box[row][col]==1) {
					q.add(new int[] {row,col});
					visit[row][col] = true;
				}
			}
		}
		BFS();
		if(ripenCheck()) System.out.println(day-1);
		else System.out.println("-1");
	}
	private static boolean ripenCheck() {
		for(int row=0;row<M;row++) {
			for(int col=0;col<N;col++) {
				if(box[row][col]==0) return false;
			}
		}
		return true;
	}
	private static void BFS() {
		while(!q.isEmpty()) {
			day++;
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRC = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = curRC[0]+dr[dir];
					int nc = curRC[1]+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(visit[nr][nc]==false && box[nr][nc]==0){
							box[nr][nc] = 1;
							q.add(new int[] {nr,nc});
							visit[nr][nc] = true;
						}
					}
				}
			}
		}
			
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<M && nc>=0 && nc<N) return true;
		return false;
	}
}
