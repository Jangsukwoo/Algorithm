package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 격자 맵에 0,1,2 숫자가 적혀있다.
 * 0:빈칸, 1:벽, 2:바이러스 
 * 최초의 바이러스 상태는 모두 비활성화 상태
 * 활성 바이러스가 비활성 바이러스 상태로 가면
 * 비활성 바이러스는 활성화 상태롭 변신
 * 
 * M개를 선택해서 활성화 시킨다.
 * 
 * 모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간을 구하자.
 * DFS 바이러스를 놓는 경우의 수 + BFS 확산
 * 
 */
public class 연구소3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M;//연구소 크기, 놓을 수 있는 바이러스 개수
	static int[][] laboratory;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static ArrayList<Virus> viruslist = new ArrayList<Virus>();
	static Queue<Virus> q = new LinkedList<Virus>();
	static int[] pickVirus;
	static int[][] visit;
	static int minTime = Integer.MAX_VALUE;
	static class Virus{
		int row;
		int col;
		public Virus(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		pickVirus();
		if(minTime==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(minTime);
	}
	private static void pickVirus() {
		dfs(0,0);
	}
	private static void dfs(int idx, int depth) {
		if(depth==M){//바이러스 M개 고름
			spread();
			return;
		}
		for(int i=idx;i<viruslist.size();i++){
			pickVirus[depth]=i;
			dfs(i+1,depth+1);
		}
	}
	private static void spread() {
		//BFS
		visit = new int[N][N];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				visit[row][col]=-1;
			}
		}
		int time=0;
		q.clear();//큐를 클리어 해줬어야지~!!
		for(int i=0;i<M;i++) insertQueue(viruslist.get(pickVirus[i]),0);
		while(!q.isEmpty()) {
			if(completeCheck()) {
				minTime = Math.min(time,minTime);
				return;
			}
			time++;
			int size = q.size();
			for(int i=0;i<size;i++){
				Virus currentVirus = q.poll();
				for(int dir=0;dir<4;dir++){
					int nr = currentVirus.row+dr[dir];
					int nc = currentVirus.col+dc[dir];
					if(rangCheck(nr,nc) && laboratory[nr][nc]!=1 && visit[nr][nc]==-1){
						//영역 만족하고 벽이 아니면서 빈칸 혹은 바이러스면 큐에 삽입
						insertQueue(new Virus(nr,nc),time);
					}
				}
			}

		}

	}
	private static boolean completeCheck() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(visit[row][col]==-1 && laboratory[row][col]==0) return false; 
				//0인 자리인데 아직 방문 못했으면 
			}
		}
		return true;
	}
	private static boolean rangCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void insertQueue(Virus virus, int time) {
		visit[virus.row][virus.col]=time;
		q.add(virus);
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		laboratory=new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				laboratory[row][col] = Integer.parseInt(st.nextToken());
				if(laboratory[row][col]==2) viruslist.add(new Virus(row,col));
			}
		}
		pickVirus = new int[M];
	}
}
