package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 꽃길 {
	static int N;
	static int[][] flower;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int minCost = Integer.MAX_VALUE;
	static int cost;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] coordination;
	static int[] pickGround;
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		plantFlower();
		System.out.println(minCost);
	}
	private static void plantFlower() {
		/*
		 * 세 송이의 꽃이 안겹치게 피게하면서 가장 싼 비용!
		 * 
		 */
		nCr(0,0);
	}
	private static void nCr(int idx, int depth) {
		if(depth==3){
			if(possibleCheck()) minCost = Math.min(minCost, cost);
			return;
		}
		for(int i=idx;i<N*N;i++) {
			pickGround[depth] = i;
			nCr(i+1,depth+1);
		}
	}
	private static boolean possibleCheck() {
		cost = 0;
		boolean[][] isFlower = new boolean[N][N];
		for(int i=0;i<3;i++) {
			int cr = coordination[pickGround[i]][0];
			int cc = coordination[pickGround[i]][1];
			isFlower[cr][cc] = true;
			int nr = cr;
			int nc = cc;
			for(int dir=0;dir<4;dir++) {
				nr=cr+dr[dir];
				nc=cc+dc[dir];
				if(rangeCheck(nr,nc) && isFlower[nr][nc]==false) isFlower[nr][nc] = true;
				else return false;
			}
		}
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(isFlower[row][col]) cost+=flower[row][col];
			}
		}
		return true;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		flower = new int[N][N];
		coordination = new int[N*N][2];
		pickGround = new int[3];
		int idx=0;
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				coordination[idx][0] = row;
				coordination[idx++][1] = col;
				flower[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
	}	
}
