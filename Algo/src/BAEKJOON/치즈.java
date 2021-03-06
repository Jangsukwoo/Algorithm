package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 치즈 {
	static Queue<int[]> airZone;
	static Queue<int[]> q;
	static int[][] map;
	static boolean[][] visitAircheck;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean clear;
	static int turn;
	static int cheeseCount;
	static int saveCount;
	static int H,W;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		H = sc.nextInt();
		W = sc.nextInt();
		map = new int[H][W];
		visitAircheck = new boolean[H][W];
		airZone = new LinkedList<int[]>();
		q = new LinkedList<int[]>();
		for(int row=0;row<H;row++)
			for(int col=0;col<W;col++) map[row][col] = sc.nextInt();

		while(!clear){
			visitAircheck = new boolean[H][W];
			setAirzone();
			remainCheeseCheck();
			removeCheese();
			if(clear) break;
			turn++;
		}
		System.out.println(turn);
		System.out.println(saveCount);
	}
	private static void view2() {
		for(int row=0;row<H;row++) {
			for(int col=0;col<W;col++) {
				System.out.print(visitAircheck[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void remainCheeseCheck(){
		saveCount = cheeseCount;
		cheeseCount=0;
		for(int row=0;row<H;row++) {
			for(int col=0;col<W;col++) {
				if(map[row][col]==1) {
					cheeseCount++;
				}
			}
		}
		if(cheeseCount==0) clear = true;
	}
	private static void removeCheese() {
		while(!airZone.isEmpty()){
			int[] curRC = airZone.poll();
			for(int dir=0;dir<4;dir++){
				int nr = curRC[0]+dr[dir];
				int nc = curRC[1]+dc[dir];
				if(rangeCheck(nr,nc)) {
					map[nr][nc]=0;
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<H && nc>=0 && nc<W) return true;
		return false;
	}
	private static void view() {
		for(int row=0;row<H;row++) {
			for(int col=0;col<W;col++) {
				System.out.print(map[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void setAirzone() {
		boolean boundary = false;
		for(int col=0;col<W;col++) {
			if(map[0][col]==0 && visitAircheck[0][col]==false){
				boundary = true;
				q.add(new int[] {0,col});
				airZone.add(new int[] {0,col});
				visitAircheck[0][col]=true;
				BFS();
			}
		}
		for(int col=0;col<W;col++) {
			if(map[(H-1)][col]==0 && visitAircheck[(H-1)][col]==false){
				boundary = true;
				q.add(new int[] {(H-1),col});
				airZone.add(new int[] {(H-1),col});
				visitAircheck[(H-1)][col]=true;
				BFS();
			}
		}
		for(int row=0;row<H;row++) {
			if(map[row][0]==0 && visitAircheck[row][0]==false){
				boundary = true;
				q.add(new int[] {row,0});
				airZone.add(new int[] {row,0});
				visitAircheck[row][0]=true;
				BFS();
			}
		}
		for(int row=0;row<H;row++) {
			if(map[row][(W-1)]==0 && visitAircheck[row][(W-1)]==false){
				boundary = true;
				q.add(new int[] {row,(W-1)});
				airZone.add(new int[] {row,(W-1)});
				visitAircheck[row][(W-1)]=true;
				BFS();
			}
		}
		if(!boundary){//테두리검사 후에 바운더리가 여전히 false면
			for(int col=0;col<W;col++) {
				map[0][col]=0;
				map[H-1][col] =0;
			}
			for(int row=0;row<H;row++) {
				map[row][0] =0;
				map[row][W-1]=0;
			}
			saveCount = H+W-4;
			turn++;
		}
	}

	private static void BFS() {
		while(!q.isEmpty()){
			int[] curRC = q.poll();
			for(int dir=0;dir<4;dir++){
				int nr = curRC[0]+dr[dir];
				int nc = curRC[1]+dc[dir];
				if(rangeCheck(nr,nc)) {
					if(map[nr][nc]==0 && visitAircheck[nr][nc]==false){
						visitAircheck[nr][nc] = true;
						q.add(new int[] {nr,nc});
						airZone.add(new int[] {nr,nc});
					}
				}
			}
		}
	}
}
