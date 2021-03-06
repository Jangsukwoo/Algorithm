package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 알고스팟 {
	static int C,R;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] maze;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] dist;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		System.out.println(min);
	}

	private static void bfs() {
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2],o2[2]);
			}
		});
		q.add(new int[] {0,0,0});
		while(!q.isEmpty()){
			int[] currentInfo = q.poll();
			int cr = currentInfo[0];
			int cc = currentInfo[1];
			int distance = currentInfo[2];
			if(cr==(R-1) && cc==(C-1)) {
				min = distance;
				return;
			}
			if(distance>dist[cr][cc]) continue;
			for(int dir=0;dir<4;dir++){
				int nr = cr+dr[dir];
				int nc = cc+dc[dir];
				if(rangeCheck(nr, nc)){
					if(dist[nr][nc]>(distance+Character.getNumericValue(maze[nr][nc]))){
						dist[nr][nc] = distance+Character.getNumericValue(maze[nr][nc]);
						q.add(new int[] {nr,nc,dist[nr][nc]});
					}
				}
			}
		}
	}

	private static boolean rangeCheck(int nr, int nc) {
		if(nr<R && nr>=0 && nc<C && nc>=0) return true;
		return false;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		maze = new char[R][C];
		dist = new int[R][C];
		for(int row=0;row<R;row++)
			for(int col=0;col<C;col++) dist[row][col] = min; 
		dist[0][0] = 0;
		for(int i=0;i<R;i++) maze[i] = br.readLine().toCharArray();
	}
}
