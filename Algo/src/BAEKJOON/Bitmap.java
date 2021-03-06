package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 */
public class Bitmap {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R,C;
	static char[][] bitMap;
	static int[][] bitDistmap;
	static boolean[][] visit;
	static Queue<int[]> q;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		setData();
		setBitdistMap();
		getAnswer();
	}
	private static void getAnswer() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				System.out.print(bitDistmap[row][col]+" ");
			}
			System.out.println();
		}
	}
	private static void setBitdistMap() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(bitMap[row][col]=='1'){
					bitDistmap[row][col]=0;
					insertQueue(row,col,1);
				}else bitDistmap[row][col] = Integer.MAX_VALUE;
			}
		}
		bfs();
	}
	private static void bfs() {
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentRC = q.poll();
				int cr = currentRC[0];
				int cc = currentRC[1];
				int cd = currentRC[2];
				for(int dir=0;dir<4;dir++) {
					int nr = cr+ dr[dir];
					int nc = cc+ dc[dir];
					if(rangeCheck(nr,nc)){
						if(bitMap[nr][nc]=='0' && cd<bitDistmap[nr][nc]){
							bitDistmap[nr][nc] = cd;
							insertQueue(nr,nc,cd+1);
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void insertQueue(int row, int col,int dist) {
		q.add(new int[] {row,col,dist});
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		bitMap = new char[R][C];
		bitDistmap = new int[R][C];
		q = new LinkedList<int[]>();
		for(int row=0;row<R;row++) {
			bitMap[row] = br.readLine().toCharArray();
		}
	}
}
