package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 0은 빈칸
 * 6은 벽
 * 1~5는 CCTV
 * 
 * 
 * 사각지대 최소 크기 
 */
public class 감시 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N,M;//세로 가로
	static int[][] map;
	static int[][] testMap;
	static int cctvSize;
	static int[] cctvDirectionPick;
	static CCTV[] cctvs;
	static int deadZone = Integer.MAX_VALUE;
	static boolean[] visit;
	static class CCTV{
		int row;
		int col;
		int num;
		public CCTV(int row, int col, int num) {
			this.row = row;
			this.col = col;
			this.num = num;
		}
		
	}
	public static void main(String[] args) throws IOException {
		setData();
		dfs(0);
		System.out.println(deadZone);
	}
	private static void dfs(int depth) {
		if(depth==cctvSize){
			copyMap();
			observation();
			countDeadZone();
			return;
		}
		for(int i=0;i<4;i++){
			cctvDirectionPick[depth] = i;
			dfs(depth+1);
		}
	}
	private static void countDeadZone(){
		int cnt=0;
		for(int row=0;row<N;row++)
			for(int col=0;col<M;col++) if(testMap[row][col]==0) cnt++;
		deadZone = Math.min(deadZone,cnt);
	}
	private static void copyMap() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				testMap[row][col] = map[row][col];
			}
		}
	}
	private static void observation() {
		for(int i=0;i<cctvSize;i++){
			switch (cctvs[i].num) {
			case 1:
				cctvNo1(i,cctvDirectionPick[i]);
				break;
			case 2:
				cctvNo2(i,cctvDirectionPick[i]);
				break;
			case 3:
				cctvNo3(i,cctvDirectionPick[i]);
				break;
			case 4:
				cctvNo4(i,cctvDirectionPick[i]);
				break;
			case 5:
				cctvNo5(i,cctvDirectionPick[i]);
				break;
			}
		}
	}


	private static void cctvNo1(int idx,int dir){//1번 cctv
		switch (dir) {
		case 0://상
			up(idx);
			break;
		case 1://우
			right(idx);
			break;
		case 2://하
			down(idx);
			break;
		case 3://좌
			left(idx);
			break;
		}
	}

	private static void cctvNo2(int idx,int dir){
		switch (dir) {
		case 0: case 1://양옆
			right(idx);
			left(idx);
			break;
		case 2: case 3://위아래
			up(idx);
			down(idx);
			break;
		}
	}
	private static void cctvNo3(int idx,int dir){
		switch (dir) {
		case 0:
			up(idx);
			right(idx);
			break;
		case 1:
			right(idx);
			down(idx);
			break;
		case 2:
			down(idx);
			left(idx);
			break;
		case 3:
			left(idx);
			up(idx);
			break;
		}
	}
	private static void cctvNo4(int idx,int dir){
		switch (dir) {
		case 0:
			left(idx);
			up(idx);
			right(idx);
			break;
		case 1:
			up(idx);
			right(idx);
			down(idx);
			break;
		case 2:
			right(idx);
			down(idx);
			left(idx);
			break;
		case 3:
			down(idx);
			left(idx);
			up(idx);
			break;
		}
	}
	private static void cctvNo5(int idx,int dir){
		switch (dir) {
		case 0: case 1: case 2: case 3:
			up(idx);
			left(idx);
			down(idx);
			right(idx);
			break;
		}
	}
	private static void up(int idx) {
		for(int row = cctvs[idx].row;row>=0;row--){
			if(testMap[row][cctvs[idx].col]==6) break;
			else testMap[row][cctvs[idx].col]=-1;
		}
	}
	private static void down(int idx) {
		for(int row = cctvs[idx].row;row<N;row++){
			if(testMap[row][cctvs[idx].col]==6) break;
			else testMap[row][cctvs[idx].col]=-1;
		}
	}
	private static void left(int idx) {
		for(int col = cctvs[idx].col;col>=0;col--){
			if(testMap[cctvs[idx].row][col]==6) break;
			else testMap[cctvs[idx].row][col]=-1;
		}
	}
	private static void right(int idx) {
		for(int col = cctvs[idx].col;col<M;col++){
			if(testMap[cctvs[idx].row][col]==6) break;
			else testMap[cctvs[idx].row][col]=-1;
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		testMap = new int[N][M];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col]!=6 && map[row][col]!=0) cctvSize++;
			}
		}//입력
		cctvDirectionPick = new int[cctvSize];
		cctvs = new CCTV[cctvSize];
		int cctvIdx=0;
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				if(map[row][col]!=6 && map[row][col]!=0) cctvs[cctvIdx++] = new CCTV(row,col,map[row][col]);
			}
		}
	}
}
