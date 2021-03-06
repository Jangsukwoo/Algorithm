package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 18:15~
 */
public class 수영대회결승전완탐구현 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] sea;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] timeVisit;
	static int startR,startC,endR,endC;
	static Queue<int[]> q = new LinkedList<int[]>();
	static int N;
	static int minTime;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			bfs();
			if(timeVisit[endR][endC]!=Integer.MAX_VALUE) System.out.println("#"+testcase+" "+timeVisit[endR][endC]);
			else System.out.println("#"+testcase+" "+"-1");
		}
	}
	/*
1
5
0 0 0 0 0
0 0 0 1 0
0 0 0 1 0
2 2 1 1 0
0 0 0 0 0
4 0
2 0
	 */
	private static void bfs() {
		/*
		 * 0 : 갈 수 있는 땅
		 * 1 : 장애물
		 * 2 : 주기 2초 소용돌이
		 */
		int time=0;

		ququeInitialization();

		boolean whirlpoolGone = false;

		while(!q.isEmpty()){

			if(time%3==2) whirlpoolGone = true; //흐른 시간의 %3 나머지값이 2면 소용돌이사라진 상태
			else whirlpoolGone = false;

//			if(whirlpoolGone) System.out.println("시간:"+time+" "+"소용돌이 사라짐");
//			else  System.out.println("시간:"+time+" "+"소용돌이 생김");

			int size = q.size();

			for(int i=0;i<size;i++) {
				int[] current  = q.poll();
				//현재 꺼낸 땅의 좌표와 시간
				int cr = current[0];
				int cc = current[1];
				int ct = current[2];

				if(cr==endR && cc==endC) {
					return;
				}
				for(int dir=0;dir<4;dir++) {

					int nr = cr+dr[dir];
					int nc = cc+dc[dir];

					if(rangeCheck(nr,nc) && sea[nr][nc]!=1){ //갈 수 있는 칸이면서 장애물이 아니면

						//그냥 갈 수 있는 칸이고 현재 기록해둔 시간 + 1해서 가는게 더 빠른 길이면 간다.
						if(sea[nr][nc]==0 && ct+1<=timeVisit[nr][nc]) insertQueue(nr,nc,ct+1);

						//소용돌이인 경우 
						else if(sea[nr][nc]==2) {//소용돌이라면

							//소용돌이가 사라졌고, 현재 기록해둔 시간 + 1해서가는게 더 빠르면 간다.
							if(whirlpoolGone && ct+1<=timeVisit[nr][nc]) insertQueue(nr, nc, ct+1);

							//소용돌이가 아직안사라졌고, 현재 기록해둔 시간 + 1해서 현재칸에서 기다리는게 더 이득이면 기다린다.
							else if(!whirlpoolGone && ct+1<=timeVisit[nr][nc]) insertQueue(cr, cc, ct+1);//제자리 머뭄 
						}
					}
				}

			}

			time++;

//			for(int row=0;row<N;row++) {
//				for(int col=0;col<N;col++) {
//					System.out.print(timeVisit[row][col]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
	}


	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void ququeInitialization() { //큐 초기화, 메모 배열 초기화
		q.clear();
		timeVisit = new int[N][N];
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) timeVisit[row][col] = Integer.MAX_VALUE;
		insertQueue(startR,startC,0);
	}
	private static void insertQueue(int cr, int cc,int time){
		q.add(new int[] {cr,cc,time});
		timeVisit[cr][cc] = time;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		sea = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				sea[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		startR = Integer.parseInt(st.nextToken());
		startC = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		endR = Integer.parseInt(st.nextToken());
		endC = Integer.parseInt(st.nextToken());
	}
}
