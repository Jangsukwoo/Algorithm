package Simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class PuyoPuyo {
	static char[][] field = new char[12][6];
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][] visit;
	static int adjCount;
	static boolean cascadeExplosionFlag;
	static int cascade;
	public static void main(String[] args) {
		setData();
		play();
		System.out.println(cascade);
	}
	private static void play() {
		while(true) {	
			explosion();
			if(cascadeExplosionFlag){
				cascade++;
				gravity();
			}else break;
		}
	}
	private static void gravity() {
		for(int col=0;col<6;col++) {
			for(int row=11;row>=0;row--){
				if(field[row][col]=='.'){
					int nr = row;
					while(true){
						nr+=dr[0];
						if(rangeCheck(nr,col)) {
							if(field[nr][col]!='.') {
								field[row][col] = field[nr][col];
								field[nr][col] = '.';
								break;
							}
						}else break;
					}
				}
			}
		}
	}
	private static void explosion() {
		cascadeExplosionFlag = false;
		
		for(int row=0;row<12;row++) {
			for(int col=0;col<6;col++) {
				if(field[row][col]!='.') {
					adjCount = 1;
					bfs(row,col,field[row][col]);
					if(adjCount>=4) {
						bomb();
						cascadeExplosionFlag = true;
					}
				}
			}
		}
	}
	private static void bomb() {
		for(int row=0;row<12;row++) {
			for(int col=0;col<6;col++) {
				if(visit[row][col]) field[row][col] ='.';
			}
		}
	}
	private static void bfs(int row, int col, char puyo) {
		q.clear();
		visit = new boolean[12][6];
		insertQueue(row,col);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRC = q.poll();
				int cr = curRC[0];
				int cc = curRC[1];
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(visit[nr][nc]==false && field[nr][nc]==puyo) {
							insertQueue(nr,nc);
							adjCount++;
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<12 && nc>=0 && nc<6) return true;
		return false;
	}
	private static void insertQueue(int cr, int cc) {
		q.add(new int[] {cr,cc});
		visit[cr][cc] = true;
	}
	private static void setData() {
		Scanner sc = new Scanner(System.in);
		for(int row=0;row<12;row++) {
			String readLine = sc.nextLine();
			field[row] = readLine.toCharArray();
		}
	}
}
