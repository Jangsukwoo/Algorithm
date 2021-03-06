package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 19:07~
 * 가장 처음 모든 바이러스는 비활성 상태
 * 활성 상태 바이러스 -> 상하좌우
 * M개를 활성 상태
 * 0 빈칸, 1 벽, 2 바이러스 위치
 * 모든 빈칸에 바이러스를 퍼뜨리는 최소 시간
 * 19:54
 */
public class 연구소3 {
	static int N,M;
	static int cnt;
	static int[][] laboratory;
	static boolean[][] visit;
	static int time=0;
	static int[] pickVirusIdx;
	static int answer = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Virus> inactiveVirusList;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<Virus> q = new LinkedList<Virus>();
	static class Virus{
		int row;
		int col;
		int time;
		public Virus(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		nCr(0,0);
		if(answer==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(answer);
	}
	private static void nCr(int r, int idx) {
		if(r==M) {
			initialization();
			bfs();
			return;
		}
		for(int pick=idx;pick<inactiveVirusList.size();pick++){
			pickVirusIdx[r] = pick;
			nCr(r+1,pick+1);
		}
	}

	private static boolean activeCheck() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(visit[row][col]==false && laboratory[row][col]==0) return false;
			}
		}
		return true;
	}
	private static void bfs(){
		int time=0;
		while(!q.isEmpty()){
			int size = q.size();
			if(activeCheck()) {
				answer = Math.min(time,answer);
				return;
			}
			for(int i=0;i<size;i++) {
				Virus currentVirus = q.poll();
				int cr = currentVirus.row;
				int cc = currentVirus.col;
				int ct = currentVirus.time;
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(check(nr,nc)) {
						Virus nextVirus = new Virus(nr,nc,ct+1);
						insertQueue(nextVirus);
					}
				}
			}
			time++;
		}
	}
	private static void initialization(){
		visit = new boolean[N][N];
		q.clear();
		for(int pick=0;pick<M;pick++) insertQueue(inactiveVirusList.get(pickVirusIdx[pick]));
		time=0;
	}
	private static void insertQueue(Virus virus) {
		visit[virus.row][virus.col] = true;
		q.add(virus);
	}

	private static boolean check(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) {//영역 체크
			if(visit[nr][nc]==false && laboratory[nr][nc]!=1) {//방문 및 벽체크
				return true;
			}
		}
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		laboratory  = new int[N][N];
		visit = new boolean[N][N];
		inactiveVirusList = new ArrayList<Virus>();
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				laboratory[row][col] = Integer.parseInt(st.nextToken());
				if(laboratory[row][col]==2) inactiveVirusList.add(new Virus(row,col,0));
			}
		}
		pickVirusIdx = new int[M];
	}

	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(visit[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
