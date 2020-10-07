package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 점프왕쩰리 {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] gameMap;
	
	//방향은 우,하로만 움직이기 가능 
	static int[] dr = {0,1};
	static int[] dc = {1,0};
	
	static boolean[][] visit;
	static String message = "Hing";
	static Queue<int[]> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		bfs();
		System.out.println(message);
	}
	private static void bfs() {
		q = new LinkedList<int[]>();
		insertQueue(0,0);
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				for(int dir=0;dir<2;dir++) {
					int jump = gameMap[cr][cc];
					int nr = cr + dr[dir]*jump;
					int nc = cc + dc[dir]*jump;
					if(nr==(N-1) && nc==(N-1)){
						message = "HaruHaru";
						return;
					}
					if(check(nr,nc)) insertQueue(nr,nc);
				}
			}
		}
	
	}
	private static boolean check(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0&& nc<N && visit[nr][nc]==false) return true;
		return false;
	}
	private static void insertQueue(int row, int col) {
		q.add(new int[] {row,col});
		visit[row][col] = true;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		gameMap = new int[N][N];
		visit = new boolean[N][N];
		
		for(int row=0;row<N;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				gameMap[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}