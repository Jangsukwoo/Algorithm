package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 19:05~
 */
public class 어른상어 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,-1,1};
	static Smell[][] smellMap;
	static Shark[] sharks;
	static PriorityQueue<Integer>[][] pq;
	static int N,M,K;
	static class Smell{
		int id;
		int time;
		public Smell(int id, int time) {
			this.id = id;
			this.time = time;
		}
	}
	static class Shark implements Comparable<Shark>{
		int id;
		int row;
		int col;
		int dir;
		int[][] priorityDir;
		boolean life;
		public Shark(int id, int row, int col,int dir, int[][] priorityDir, boolean life) {
			this.id = id;
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.priorityDir = priorityDir;
			this.life = life;
		}
		@Override
		public int compareTo(Shark o) {
			return Integer.compare(this.id, o.id);
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		System.out.println(moveAll());
	}
	private static int moveAll() {
		int time=0;
		while(time<=1000) {
//			System.out.println("이동전");
//			view();
			if(lifeCheck()) return time;
			moveSharks();
			System.out.println("시간"+time);
			view();
			timeWork();//시간 깎기
			removeSharks();

			time++;
		}
		return -1;
	}
	private static void view() {
		System.out.println("id");
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(smellMap[row][col].id+" ");
			}
			System.out.println();
		}
		System.out.println("TIME");
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(smellMap[row][col].time+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void removeSharks() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(pq[row][col].size()>1) {
					int id = pq[row][col].poll();
					smellMap[row][col].id = id;
					smellMap[row][col].time = K;
					
					while(!pq[row][col].isEmpty()) {
						int removeId = pq[row][col].poll();
						sharks[removeId].life = false;
					}

				}else if(pq[row][col].size()==1) {
					int id = pq[row][col].poll();
					smellMap[row][col].id = id;
					smellMap[row][col].time = K;
				}
			}
		}
	}
	private static void moveSharks() {
		for(int id=1;id<=M;id++) {
			if(id==2) {
				System.out.println("2번좌표");
				System.out.println(sharks[id].row+" "+sharks[id].col);
			}
			if(sharks[id].life) {
				if(moveBlank(id)) {
					continue;//인접한 칸으로 이동시킴
				}
				else {
					moveSmellBlank(id);
				}
			}
		}
	}
	private static boolean moveBlank(int id) {
		int nextDir = 0;
		int currentDir = sharks[id].dir;
		int cr = sharks[id].row;
		int cc = sharks[id].col;
		int blankCount = 0;
		/*
		 * 인접한 빈칸 조사
		 */
		int nr = cr;
		int nc = cc;
		int nextR = 0;
		int nextC = 0;
		for(int dir=1;dir<=4;dir++) {
			nr = cr+dr[dir];
			nc = cc+dc[dir];
			if(rangeCheck(nr,nc)) {
				if(smellMap[nr][nc].id==0) {
					nextDir = dir;
					nextR = nr;
					nextC = nc;
					blankCount++;
				}
			}
		}
		
		if(blankCount==1) { //인접한칸이 유일하면 
			sharks[id].dir = nextDir;
			if(id==2) {
				System.out.println("인접칸 유일");
				System.out.println(nextDir);
				System.out.println(sharks[id].row+" "+sharks[id].col);
				System.out.println(nextR+" "+nextC);
			}
			
			sharks[id].row = nextR;
			sharks[id].col= nextC;
			pq[nextR][nextC].add(id);
			return true;
		}
		else if(blankCount>1){ //인접한 칸이 여러개면
			for(int j=0;j<4;j++) {
				nextDir = sharks[id].priorityDir[currentDir][j];
				nr =cr+dr[nextDir];
				nc =cc+dc[nextDir];
				if(rangeCheck(nr, nc) && smellMap[nr][nc].id==0) break;
			}
			sharks[id].dir = nextDir;
			sharks[id].row =nr;
			sharks[id].col= nc;
			pq[nr][nc].add(id);
			return true;
		}
		return false;
	}
	private static void moveSmellBlank(int id) {
		int nextDir = 0;
		int currentDir = sharks[id].dir;
		int cr = sharks[id].row;
		int cc = sharks[id].col;
		int smellCount = 0;
		/*
		 * 인접한 빈칸 조사
		 */
		int nr = cr;
		int nc = cc;
		int nextR = 0;
		int nextC = 0;
		for(int dir=1;dir<=4;dir++) {
			nr = cr+dr[dir];
			nc = cc+dc[dir];
			if(rangeCheck(nr,nc)) {
				if(smellMap[nr][nc].id==id) {
					nextDir = dir;
					nextR = nr;
					nextC = nc;
					smellCount++;
				}
			}
		}
		
		if(smellCount==1) { //냄새 칸이 유일하면
			sharks[id].dir = nextDir;
			sharks[id].row = nextR;
			sharks[id].col = nextC;
			pq[nextR][nextC].add(id);
		}
		else if(smellCount>1){ //인접한 칸이 여러개라면 우선순위 선택
			for(int j=0;j<4;j++) {
				nextDir = sharks[id].priorityDir[currentDir][j];
				nr =cr+dr[nextDir];
				nc =cc+dc[nextDir];
				if(rangeCheck(nr, nc) && smellMap[nr][nc].id==id) break;
			}
			sharks[id].dir = nextDir;
			sharks[id].row = nr;
			sharks[id].col = nc;
			pq[nextR][nextC].add(id);
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void timeWork() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(smellMap[row][col].id!=0) {
					smellMap[row][col].time-=1;
					if(smellMap[row][col].time==0) {
						smellMap[row][col].id=0;
					}
				}
			}
		}
	}
	private static boolean lifeCheck() {
		for(int id=2;id<=M;id++) {
			if(sharks[id].life) return false; //2번 이상의 상어가 한마리라도 살아있으면 false
		}
		return true;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		smellMap = new Smell[N][N];
		pq = new PriorityQueue[N][N];
		sharks = new Shark[M+1];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				pq[row][col] = new PriorityQueue<Integer>();
				int id = Integer.parseInt(st.nextToken());
				if(id!=0) {
					smellMap[row][col] = new Smell(id, K);
					sharks[id] = new Shark(id, row, col,0, null,true);
				}
				else smellMap[row][col] = new Smell(id, 0);
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int id=1;id<=M;id++) {
			int dir = Integer.parseInt(st.nextToken());
			sharks[id].dir = dir;
		}
		for(int id=1;id<=M;id++) {
			int[][] priorityDir = new int[5][4];
			for(int dir=1;dir<=4;dir++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<4;j++) {
					priorityDir[dir][j] = Integer.parseInt(st.nextToken());
				}
			}
			sharks[id].priorityDir = priorityDir;
		}
	}
}
