package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 19:00~
 * 명령 1: go k (1~3)
 * 명령 2: turn dir (left ~right)
 */
public class 로봇 {
	static int R,C;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] factory;
	static boolean[][][] visit;
	static int[] dr = {0,0,0,1,-1};
	static int[] dc = {0,1,-1,0,0};
	static Queue<int[]> q = new LinkedList<int[]>();
	static int endR,endC,endD;
	static int answer=0;
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		System.out.println(answer);
	}

	private static void bfs(){
		int comm = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentInfo = q.poll();
				int cr = currentInfo[0];
				int cc = currentInfo[1];
				int cd = currentInfo[2];
				int currentCommand = currentInfo[3];
				int nr = cr;
				int nc = cc;
				if(cr==endR && cc==endC && cd ==endD) {
					answer = comm;
					return;
				}
				for(int k=1;k<=3;k++) {
					nr= cr+(k*dr[cd]);
					nc= cc+(k*dc[cd]);
					if(rangeCheck(nr,nc) && visit[nr][nc][cd]==false && factory[nr][nc]!=1) insertQueue(nr,nc,cd,currentCommand+1);
					if(rangeCheck(nr, nc) && factory[nr][nc]==1) break;
				}
				switch (cd) {
				case 1: case 2:
					if(visit[cr][cc][3]==false) insertQueue(cr,cc,3,currentCommand+1);
					if(visit[cr][cc][4]==false) insertQueue(cr,cc,4,currentCommand+1);
					break;
				case 3: case 4:
					if(visit[cr][cc][1]==false) insertQueue(cr,cc,1,currentCommand+1);
					if(visit[cr][cc][2]==false) insertQueue(cr,cc,2,currentCommand+1);
					break;

				}
			}
			comm++;
		}
	}

	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=1 && nr<=R && nc>=1 && nc<=C) return true;
		return false;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		factory = new int[R+1][C+1];
		visit = new boolean[R+1][C+1][5];
		for(int row=1;row<=R;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=1;col<=C;col++) {
				factory[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		int sd = Integer.parseInt(st.nextToken());
		insertQueue(sr,sc,sd,0);
		st = new StringTokenizer(br.readLine());
		endR = Integer.parseInt(st.nextToken());
		endC = Integer.parseInt(st.nextToken());
		endD = Integer.parseInt(st.nextToken());
	}

	private static void insertQueue(int r, int c, int d, int command) {
		q.add(new int[] {r,c,d,command});
		visit[r][c][d] = true;
	}
}
