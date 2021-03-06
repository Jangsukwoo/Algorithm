package SamsungDS;
/*
 * 16:00~18:08
 * 문제 속에 답이있다..
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
8 2 
1 1 1 1 0 1 1 1 
1 1 0 1 0 1 0 1 
0 0 0 1 1 1 0 1 
0 0 0 0 0 0 0 1 
0 0 0 0 0 1 0 1 
0 0 0 0 0 1 1 1 
0 0 0 0 0 0 0 10 
0 0 0 0 0 0 1 1

4 3 
1 1 100 100 
1 1 100 100 
0 0 1 1 
1 0 1 1 

4 3 
1 1 20 20 
1 1 20 20 
0 0 1 1 
1 0 1 1 

5 10 
1 1 2 3 1 
1 1 0 0 1 
13 0 0 0 1 
1 1 1 1 1 
1 1 1 1 1 
 */
public class 견우와직녀 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int[][] test;
	static ArrayList<int[]> bridgeList;
	static Queue<int[]> q;
	static int minTime;
	static boolean[][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int N,M;
	public static void main(String[] args) throws IOException {
		setData();
		setPossiblebridge();
		initailizationQueue(); //그냥 가보기
		bfs();
		for(int i=0;i<bridgeList.size();i++) { //설치해서 가보기
			int[] bridge = bridgeList.get(i);
			int br = bridge[0];
			int bc = bridge[1];
			map[br][bc] = M;
			initailizationQueue();
			bfs();
			//System.out.println(minTime);
			map[br][bc] = 0;
		}
		System.out.println(minTime);
	}
	private static void initailizationQueue() {
		q.clear();
		visit = new boolean[N][N];
		insertQueue(0,0);
	}
	private static void insertQueue(int cr, int cc) {
		q.add(new int[]{cr,cc});
		visit[cr][cc] = true;
	}
	private static void setPossiblebridge() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(map[row][col]==0) {
					if(isInterSection(row,col)) continue;
					else {
						bridgeList.add(new int[] {row,col});
					}	
				}
			}
		}
	}
	private static boolean isInterSection(int row, int col) {//교차로검사
		int nr1 = row+dr[0];
		int nc1 = col+dc[0];
		int nr2 = row+dr[1];
		int nc2 = col+dc[1];
		//두 좌표 다 영역 만족하고
		if(rangeCheck(nr1, nc1) && rangeCheck(nr2, nc2)) if(map[nr1][nc1]==0 && map[nr2][nc2]==0) return true;
		nr2 = row+dr[3];
		nc2 = col+dc[3];
		if(rangeCheck(nr1, nc1) && rangeCheck(nr2, nc2)) if(map[nr1][nc1]==0 && map[nr2][nc2]==0) return true;
		nr1 = row+dr[2];
		nc1 = col+dc[2];
		if(rangeCheck(nr1, nc1) && rangeCheck(nr2, nc2)) if(map[nr1][nc1]==0 && map[nr2][nc2]==0) return true;
		nr2 = row+dr[1];
		nc2 = col+dc[1];
		if(rangeCheck(nr1, nc1) && rangeCheck(nr2, nc2)) if(map[nr1][nc1]==0 && map[nr2][nc2]==0) return true;
		
		return false;
	}
	private static void bfs() {
		/*
		 * 0 : 절벽
		 * 1 : 땅
		 * 2이상 : 주기
		 * 견우 : 0,0
		 * 직녀 : N-1,N-1
		 * 두번 연속 오작교 건너기 없기
		 */
		int time=0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				if(cr==N-1 && cc==N-1) {
					minTime = Math.min(minTime,time);
					return;
				}
				boolean wait = false;
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr, nc)){//영역 만족
						if(map[cr][cc]>1 && map[nr][nc]>1) continue;
						if(map[nr][nc]==1 && visit[nr][nc]==false) insertQueue(nr,nc);
						else if(map[nr][nc] > 1&& visit[nr][nc]==false){//오작교인데
							//건널 수 있으면
							if((map[nr][nc]%(time+1)==0 || (time+1)%map[nr][nc]==0) && (time+1)>=map[nr][nc]) {
								insertQueue(nr,nc);
							}
							if(visit[nr][nc]==false) wait=true;
						}
					}
				}
				if(wait) insertQueue(cr,cc);
			}
			time++;
		}
		
	}
	private static void setData() throws IOException {
		minTime  = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		bridgeList = new ArrayList<int[]>();
		q = new LinkedList<int[]>();
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
}
