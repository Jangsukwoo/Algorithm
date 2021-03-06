package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 17:30~ 18:00
 */
public class 빙산_BFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R,C;
	static int[][] sea;
	static int[][] countZeroMap;
	static boolean divide;
	static int year;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		if(divide) System.out.println(year);
		else System.out.println("0");
	}

	private static void simulation() {
		divide = true;
		while(true){//언제 끝날지 모름 
			if(divideCheck()) return ;//bfs로 빙산 덩어리 조사
			setCountZeroMap();//빙산에 인접한 0 개수 새기
			melt();//인접한 0의 개수만큼 빙산 녹이기 처리 
			if(existICE()){ //빙산 다 녹았고 바다냐 ?
				divide = false;
				return;
			}
			year++;
		}
	}
	private static boolean existICE(){
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(sea[row][col]>0) return false;
			}
		}
		return true;
	}

	private static boolean divideCheck(){
		q.clear();
		visit = new boolean[R][C];
		int mass=0;
		for(int row=0;row<R;row++){
			for(int col=0;col<C;col++){
				if(visit[row][col]==false && sea[row][col]>0 && mass==0){
					mass++;
					insertQueue(row,col);
					bfs();
				}else if(visit[row][col]==false && sea[row][col]>0 && mass==1){
					return true;
				}
			}
		}
		return false;
	}

	private static void bfs(){
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] currentIce = q.poll();
				int cr = currentIce[0];
				int cc = currentIce[1];
				for(int dir=0;dir<4;dir++) { 
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr, nc)){//영역 만족
						if(sea[nr][nc]>0 && visit[nr][nc]==false) {
							insertQueue(nr,nc);
						}
					}
				}
			}
		}
	}

	private static void insertQueue(int row, int col) {
		q.add(new int[] {row,col});
		visit[row][col] = true;
	}

	private static void melt() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				sea[row][col]-=countZeroMap[row][col];
				if(sea[row][col]<0) sea[row][col]=0;
			}
		}
	}

	private static void setCountZeroMap(){
		countZeroMap = new int[R][C];
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++){
				if(sea[row][col]>0){
					int count=0;
					for(int dir=0;dir<4;dir++) {
						int nr = row+dr[dir];
						int nc = col+dc[dir];
						if(rangeCheck(nr,nc)){
							if(sea[nr][nc]==0) count++;
						}
					}
					countZeroMap[row][col] = count;
				}
			}
		}
	}

	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sea = new int[R][C];
		for(int row=0;row<R;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<C;col++) {
				sea[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
