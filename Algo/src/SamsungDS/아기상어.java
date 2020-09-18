package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어 {
	static int N;
	static int[][] aquarium;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PriorityQueue<Fish> targetList;
	static BabyShark babyShark;
	static StringTokenizer st;
	static int time;
	static boolean impossible;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static class BabyShark{
		int row;
		int col;
		int size;
		int eat;
		public BabyShark(int row, int col, int size, int eat) {
			this.row = row;
			this.col = col;
			this.size = size;
			this.eat = eat;
		}
	}
	static class Fish{
		int row;
		int col;
		int manhattanDistance;
		int size;
		public Fish(int row, int col, int manhattanDistance, int size) {
			this.row = row;
			this.col = col;
			this.manhattanDistance = manhattanDistance;
			this.size = size;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		simulation();
		System.out.println(time);
	}
	private static void simulation() {
		/*
		 * 1. 먹을 물고기가 있는지?
		 * 2. 이동결정
		 * 3. bfs로 이동
		 */
		while(true) {
			/*
			 * 그냥  bfs로 통과한 풀이
			 */
			bfs2();
			if(targetList.size()==0) return;
			else {
				Fish targetFish = targetList.poll();
				aquarium[babyShark.row][babyShark.col] = 0;
				aquarium[targetFish.row][targetFish.col] = 9;//아기상어 위치 변경
				babyShark.row = targetFish.row;
				babyShark.col = targetFish.col;
				babyShark.eat++;
				if(babyShark.eat==babyShark.size) {
					babyShark.size++;
					babyShark.eat=0;
				}
				time+=targetFish.manhattanDistance;
			}
			/*
			 * 맨해튼 거리로 풀었는데 틀린 풀이..
			 */
			//			putTarget();//우선순위 큐에 먹을 수 있는 물고기 담기
			//			//먹을 수 있는 물고기가 없다면 종료
			//			if(targetList.size()==0) return;
			//			//아니면 물고기를 먹으러 가본다.
			//			Fish targetFish = targetList.poll();
			//			impossible = bfs(targetFish);
			//			if(impossible) return; //불가능하면 끝냄
			//			else {//가능했으니 먹힘처리
			//				aquarium[babyShark.row][babyShark.col] = 0;
			//				aquarium[targetFish.row][targetFish.col] = 9;//아기상어 위치 변경
			//				babyShark.row = targetFish.row;
			//				babyShark.col = targetFish.col;
			//				babyShark.eat++;
			//				if(babyShark.eat==babyShark.size) {
			//					babyShark.size++;
			//					babyShark.eat=0;
			//				}
			//				time+=targetFish.manhattanDistance;
			//			}
		}
	}
	private static void putTarget() {
		targetList.clear();
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(aquarium[row][col]!=0 
						&& !(row==babyShark.row && col==babyShark.col) 
						&& aquarium[row][col]<babyShark.size){
					System.out.println(aquarium[row][col]);
					int manhattanDistance = Math.abs(row-babyShark.row)+Math.abs(col-babyShark.col);
					targetList.add(new Fish(row, col, manhattanDistance,aquarium[row][col]));
				}
			}
		}
	}
	private static boolean bfs(Fish targetFish) {
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {babyShark.row,babyShark.col,0});
		visit[babyShark.row][babyShark.col] = true;
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			int cr = rc[0];
			int cc = rc[1];
			int cd = rc[2];
			if(cr==targetFish.row && cc==targetFish.col) {
				System.out.println("이동값"+cd);
				//ans+=cd;
				return false;
			}
			for(int dir=0;dir<4;dir++) {
				int nr = cr+dr[dir];
				int nc = cc+dc[dir];
				if(rangeCheck(nr,nc)) {
					if(aquarium[nr][nc]<=babyShark.size && visit[nr][nc]==false) {
						visit[nr][nc] = true;
						q.add(new int[] {nr,nc,cd+1});
					}
				}
			}
		}
		return true;
	}
	private static void bfs2() {
		targetList.clear();
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {babyShark.row,babyShark.col,0});
		visit[babyShark.row][babyShark.col] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] rc = q.poll();
				int cr = rc[0];
				int cc = rc[1];
				int cd = rc[2];
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(aquarium[nr][nc]<=babyShark.size && visit[nr][nc]==false){
							visit[nr][nc] = true;
							q.add(new int[] {nr,nc,cd+1});
							if(aquarium[nr][nc]<babyShark.size && aquarium[nr][nc]!=0) {
								targetList.add(new Fish(nr,nc,cd+1,aquarium[nr][nc]));
							}
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		aquarium = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				aquarium[row][col] = Integer.parseInt(st.nextToken());
				if(aquarium[row][col]==9) babyShark = new BabyShark(row, col, 2, 0);
			}
		}
		targetList = new PriorityQueue<Fish>(new Comparator<Fish>() {
			@Override
			public int compare(Fish o1, Fish o2) {
				if(o1.manhattanDistance==o2.manhattanDistance) {
					//1. 거리가 같음
					if(o1.row==o2.row) {
						//2. 둘다 가장 위에 위치함
						return Integer.compare(o1.col,o2.col); //가장 왼쪽 return
					}
					return Integer.compare(o1.row,o2.row);//가장 위 return
				}
				return Integer.compare(o1.manhattanDistance,o2.manhattanDistance);//가장 가까운 물고기 리턴
			}
		});
	}
}
