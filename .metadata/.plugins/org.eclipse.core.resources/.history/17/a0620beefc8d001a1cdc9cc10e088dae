package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 2차원 격자맵이 있고
 * 0 : 빈칸
 * 1 : 벽
 * 2 : 바이러스
 * 
 * 0인칸에 3개를 뽑는 모든 경우의 수에 대해 테스트 
 */
public class 연구소 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] lab;
	static int[][] testLab;
	static int N,M;
	static int maxSafeArea;
	static ArrayList<int[]> wallList;
	static ArrayList<int[]> virusList;
	static int wallListSize;
	static int[] pickWall = new int[3];
	static boolean[] pickVisit;
	static boolean[][] virusVisit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		setData();	
		nCr(0,0);
		System.out.println(maxSafeArea);
	}
	private static void nCr(int idx, int depth) {
		if(depth==3){//3개 골랐음
			setWall();
			return;
		}
		for(int i=idx;i<wallListSize;i++){
			if(pickVisit[i] == false) {
				pickVisit[i] = true;
				pickWall[depth] = i;
				nCr(i+1,depth+1);
				pickVisit[i] = false;
			}
		}
	}
	private static void setWall() {
		copyMap();

		for(int i=0,wallRow,wallCol;i<3;i++) {
			int[] wallRC = wallList.get(pickWall[i]);
			wallRow = wallRC[0];
			wallCol = wallRC[1];
			testLab[wallRow][wallCol] = 1;//벽세우기
		}

		spreadVirus();
	}
	private static void spreadVirus() {
		virusVisit = new boolean[N][M];
		Queue<int[]> q = new LinkedList<int[]>();
		for(int i=0,virusRow,virusCol;i<virusList.size();i++) {
			int[] virusRC = virusList.get(i);
			virusRow = virusRC[0];
			virusCol = virusRC[1];
			q.add(virusRC);
		}
		while(!q.isEmpty()){ //바이러스 퍼짐 BFS
			int[] curVirusRC = q.poll();
			for(int dir=0;dir<4;dir++) {
				int nr = curVirusRC[0]+dr[dir];
				int nc = curVirusRC[1]+dc[dir];
				if(rangeCheck(nr,nc)) {
					if(testLab[nr][nc]==0 && virusVisit[nr][nc]==false) {
						testLab[nr][nc]=2;
						virusVisit[nr][nc] = true;
						q.add(new int[] {nr,nc});
					}
				}
			}
		}

		getSafeArea();
	}
	private static void getSafeArea() {
		int cnt = 0;

		for(int row=0;row<N;row++) 
			for(int col=0;col<M;col++) if(testLab[row][col]==0) cnt++;

		maxSafeArea = Math.max(maxSafeArea,cnt); //최대값 update
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
	private static void copyMap() {
		for(int row=0;row<N;row++)
			for(int col=0;col<M;col++) testLab[row][col] = lab[row][col];
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][M];
		testLab = new int[N][M];
		wallList = new ArrayList<int[]>();
		virusList = new ArrayList<int[]>();
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				lab[row][col] = Integer.parseInt(st.nextToken());
				if(lab[row][col]==0) wallList.add(new int[] {row,col});
				if(lab[row][col]==2) virusList.add(new int[] {row,col});
			}
		}
		wallListSize = wallList.size(); //n, r=3
		pickVisit = new boolean[wallListSize];
	}
}
