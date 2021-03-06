package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 음식물피하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R,C,foodTrash;
	static int foodTrashSize;
	static int maxFoodTrashSize;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static char[][] map;
	static boolean[][] visit;
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) throws IOException {
		setData();
		bfsAll();
		System.out.println(maxFoodTrashSize);
	}
	private static void bfsAll() {
		for(int row=1;row<=R;row++) {
			for(int col=1;col<=C;col++){
				if(foodCheck(row,col)) {
					bfs(row,col);
					maxFoodTrashSize = Math.max(maxFoodTrashSize, foodTrashSize);
				}
			}
		}
	}
	private static void bfs(int row, int col) {
		foodTrashSize=0;
		q.add(new int[] {row,col});
		visit[row][col] = true;
		foodTrashSize++;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc) && foodCheck(nr,nc)) {
						q.add(new int[] {nr,nc});
						visit[nr][nc] = true;
						foodTrashSize++;
					}
				}
			}
		}
	}
	private static boolean foodCheck(int nr, int nc) {
		if(visit[nr][nc]==false && map[nr][nc] =='#') return true;
		return false;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=1 && nr<=R && nc>=1 && nc<=C) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		foodTrash = Integer.parseInt(st.nextToken());
		map = new char[R+1][C+1];
		visit = new boolean[R+1][C+1];
		for(int i=0;i<foodTrash;i++){
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			map[row][col] = '#';
		}
	}
}
