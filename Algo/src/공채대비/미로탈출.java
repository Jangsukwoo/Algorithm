package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 17:35 시작
 * 0은 땅 1은 빈칸
 * 17:49
 */
public class 미로탈출 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M;
	static int[][] maze;
	static int startR, startC, endR, endC;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean[][][] visit;
	static int time=0;
	static boolean escape;
	static Queue<int[]> q  = new LinkedList<int[]>();
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		if(escape) System.out.println(time);
		else System.out.println("-1");
	}
	private static void bfs(){
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] currentNode = q.poll();
				int cr = currentNode[0];
				int cc = currentNode[1];
				int ca = currentNode[2];
				if(cr==endR && cc ==endC) {
					escape = true;
					return;
				}
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(maze[nr][nc]==0){
							if(visit[nr][nc][ca]==false) {
								insertQueue(new int[] {nr,nc,ca});
							}
						}else if(maze[nr][nc]==1) {
							if(visit[nr][nc][ca]==false && ca==1){
								insertQueue(new int[] {nr,nc,ca-1});
							}
						}
					}
				}
			}
			time++;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visit = new boolean[N][M][2];
		maze = new int[N][M];
		st = new StringTokenizer(br.readLine());
		startR = Integer.parseInt(st.nextToken())-1;
		startC = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		endR = Integer.parseInt(st.nextToken())-1;
		endC = Integer.parseInt(st.nextToken())-1;
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				maze[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		insertQueue(new int[] {startR,startC,1});
	}
	private static void insertQueue(int[] currentInformation){
		int cr = currentInformation[0];
		int cc = currentInformation[1];
		int ca = currentInformation[2];
		q.add(currentInformation);
		visit[cr][cc][ca] = true;
	}
}
