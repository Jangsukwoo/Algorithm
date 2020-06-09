package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 로봇 {
	static int R,C;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] factory;
	static boolean[][][] visit;
	static int[] dr = {0,0,0,1,-1};
	static int[] dc = {0,1,-1,0,0};//동서남북 1,2,3,4
	static int startR,startC,starDir;
	static int endR,endC,endD;
	static int answer;
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		System.out.println(answer);
	}
	private static void bfs() {
		int command=0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentInformation = q.poll();
				int cr = currentInformation[0];
				int cc = currentInformation[1];
				int cd = currentInformation[2];
				if(cr==endR && cc==endC && cd ==endD) {
					answer = command;
					return;
				}//종료조건
				//1. 직진 가능한지
				
				int nr = cr;
				int nc = cc;
				for(int k=1;k<=3;k++){
					nr = cr+(k*dr[cd]);
					nc = cc+(k*dc[cd]);
					if(rangeCheck(nr,nc)){//영역은 만족하는데
						if(factory[nr][nc]!=1 && visit[nr][nc][cd]==false) {//벽도 아니고 방문한 적도없다.
							insertQueue(nr, nc, cd);
						}else if(factory[nr][nc]==1) break;
					}else break;
				}
				//2.고개를 꺾을 수 있는가
				switch (cd){
				case 1: case 2 :
					if(visit[cr][cc][3]== false) insertQueue(cr, cc, 3);
					if(visit[cr][cc][4]== false) insertQueue(cr, cc, 4);
					break;
				case 3: case 4:
					if(visit[cr][cc][1]== false) insertQueue(cr, cc, 1);
					if(visit[cr][cc][2]== false) insertQueue(cr, cc, 2);
					break;
				}
			}
			command++;
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
		startR = Integer.parseInt(st.nextToken());
		startC = Integer.parseInt(st.nextToken());
		starDir = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		endR = Integer.parseInt(st.nextToken());
		endC = Integer.parseInt(st.nextToken());
		endD = Integer.parseInt(st.nextToken());
		insertQueue(startR,startC,starDir);
	}
	private static void insertQueue(int cr, int cc, int cd) {
		q.add(new int[] {cr,cc,cd});
		visit[cr][cc][cd] = true;
	}
}
