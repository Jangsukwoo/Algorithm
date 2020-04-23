package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 10:00
 */
public class 보물섬 {
	static int R,C;
	static char[][] treausureMap;
	static boolean[][] visit;
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Queue<int[]> q;
	static int answer;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		setData();
		bfsAll();
		System.out.println(answer-1);
	}
	private static void bfsAll() {
		for(int row=0;row<R;row++){
			for(int col=0;col<C;col++){
				if(treausureMap[row][col]=='L'){
					visit = new boolean[R][C];
					q.clear();
					insertQueue(row,col);
					bfs();
				}
			}
		}
	}
	private static void bfs(){
		int dist=0;
		while(!q.isEmpty()){
			dist++;
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentRC = q.poll();
				int cr = currentRC[0];
				int cc = currentRC[1];
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc) && visit[nr][nc]==false && treausureMap[nr][nc]=='L'){
						insertQueue(nr,nc);
					}
				}
			}
		}
		answer = Math.max(dist,answer);
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void insertQueue(int row, int col) {
		q.add(new int[] {row,col});
		visit[row][col] = true;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		treausureMap = new char[R][C];
		for(int row=0;row<R;row++){
			treausureMap[row] = br.readLine().toCharArray();
		}
		q = new LinkedList<int[]>();
	}
}
