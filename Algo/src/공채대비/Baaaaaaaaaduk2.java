package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 18:45~
 * 19:18
 * 
 * 0은 빈칸
 * 1은 나의 돌
 * 2는 상대 돌
 * 
 * 돌 두개를 두어 죽일 수 있는 상대 돌의 최대 개수
 */
public class Baaaaaaaaaduk2 {
	static int[][] board;
	static int[][] testBoard;
	static int R,C;
	static int n;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<int[]> positions = new ArrayList<int[]>();
	static int[] pick;
	static boolean[][] visit;
	static Queue<int[]> q = new LinkedList<int[]>();
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int answer;
	static int testAnswer;
	public static void main(String[] args) throws IOException {
		setData();
		nCr(0,0);
		System.out.println(answer);
	}
	private static void nCr(int idx, int depth){
		if(depth==2){
			setTestBoard();
			bfsAll();
			return;
		}
		for(int i=idx;i<n;i++) {
			pick[depth] = i;
			nCr(i+1,depth+1);
		}
	}
	private static void bfsAll(){
		visit = new boolean[R][C];
		testAnswer = 0;
		for(int row=0;row<R;row++){
			for(int col=0;col<C;col++) {
				if(visit[row][col]==false && testBoard[row][col]==2) {
					q.clear();
					insertQueue(row,col);
					bfs();
				}
			}
		}
		answer = Math.max(answer,testAnswer);
	}
	private static void bfs(){
		int cnt=1;
		boolean success = true;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				for(int dir=0;dir<4;dir++){//빈칸이 하나라도 닿아있으면 실패임
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)){
						//영역 안에 있을 때 빈칸이면 실패
						if(testBoard[nr][nc]==0) {
							success=false;
						}else if(testBoard[nr][nc]==2 && visit[nr][nc]==false) {
							insertQueue(nr,nc);
							cnt++;
						}
					}
				}
			}
		}
		if(success) testAnswer+=cnt;
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void insertQueue(int row, int col) {
		q.add(new int[] {row,col});
		visit[row][col] = true;
	}
	private static void setTestBoard() {
		//바둑판 복사 
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				testBoard[row][col] = board[row][col];
			}
		}
		//뽑은 두개 두기 
		for(int i=0;i<2;i++) {
			int[] position = positions.get(pick[i]);
			testBoard[position[0]][position[1]] = 1;
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		testBoard = new int[R][C];
		pick = new int[2];
		for(int row=0;row<R;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<C;col++){
				board[row][col] = Integer.parseInt(st.nextToken());
				if(board[row][col]==0) positions.add(new int[] {row,col});
			}
		}
		n = positions.size();
	}
}
