package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 보급로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,R,C,L;
	static int[][] basement;
	static boolean[][] visit;
	static int possibleSpot;
	static Queue<int[]> q;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			bfs();
			System.out.println("#"+testcase+" "+possibleSpot);
		}
	}

	private static void bfs(){
		q.clear();
		insertQueue(R,C);
		int time = 1;
		while(!q.isEmpty()) {
			if(time==L) return;
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentSpot = q.poll();
				int cr = currentSpot[0];
				int cc = currentSpot[1];
				switch (basement[cr][cc]) {
				case 1: //상우좌하
					up(cr,cc);
					right(cr,cc);
					down(cr,cc);
					left(cr,cc);
					break;
				case 2: //상하
					up(cr,cc);
					down(cr,cc);
					break;
				case 3: //좌우
					left(cr,cc);
					right(cr,cc);
					break;
				case 4: //상우
					up(cr,cc);
					right(cr,cc);
					break;
				case 5: //우하
					right(cr,cc);
					down(cr,cc);
					break;
				case 6: //하좌
					down(cr,cc);
					left(cr,cc);
					break;
				case 7: //좌상
					left(cr,cc);
					up(cr,cc);
					break;
				}
			}
			time++;
		}
	}
	private static void up(int cr, int cc) {
		int nr = cr + dr[0];
		int nc=  cc + dc[0];
		if(rangeAndVisitCheck(nr,nc) 
				&& ( basement[nr][nc] == 1
				|| basement[nr][nc] == 2
				|| basement[nr][nc] ==5
				|| basement[nr][nc] == 6)) {
			insertQueue(nr, nc);
		}
	}
	private static void right(int cr, int cc) {
		int nr = cr + dr[1];
		int nc=  cc + dc[1];
		if(rangeAndVisitCheck(nr,nc) 
				&& ( basement[nr][nc] == 1
				|| basement[nr][nc] == 3
				|| basement[nr][nc] ==6
				|| basement[nr][nc] == 7)) {
			insertQueue(nr, nc);
		}
	}
	private static void down(int cr, int cc) {
		int nr = cr + dr[2];
		int nc=  cc + dc[2];
		if(rangeAndVisitCheck(nr,nc) 
				&& (basement[nr][nc] == 1
				|| basement[nr][nc] == 2
				|| basement[nr][nc] == 4
				|| basement[nr][nc] == 7)) {
			insertQueue(nr, nc);
		}
	}
	private static void left(int cr, int cc) {
		int nr = cr + dr[3];
		int nc=  cc + dc[3];
		if(rangeAndVisitCheck(nr,nc) 
				&& ( basement[nr][nc] == 1
				|| basement[nr][nc] == 3
				|| basement[nr][nc] ==4
				|| basement[nr][nc] == 5)) {
			insertQueue(nr, nc);
		}
	}

	private static boolean rangeAndVisitCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M && visit[nr][nc] == false) return true;
		return false;
	}
	private static void insertQueue(int cr, int cc) {
		q.add(new int[] {cr,cc});
		visit[cr][cc] = true;
		possibleSpot++;//숨기
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		basement = new int[N][M];
		visit = new boolean[N][M];
		for(int row=0; row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				basement[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		q = new LinkedList<int[]>();
		possibleSpot = 0;
	}
}
