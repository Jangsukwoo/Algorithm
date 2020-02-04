package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1
5 6 2 1 3
0 0 5 3 6 0
0 0 2 0 2 0
3 3 1 3 7 0
0 0 0 0 0 0
0 0 0 0 0 0 
 */
public class 탈주범검거 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N,M,R,C,L;
	static int Answer;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌, 시계방향 
	static Queue<int[]> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			bfs();
			bw.write("#"+testcase+" "+Answer+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void bfs() {
		q = new LinkedList<int[]>();
		q.add(new int[] {R,C,map[R][C]});
		visit[R][C] = true;
		int time=1;
		while(!q.isEmpty()){
			if(time==L) return;
			time++;
			int size = q.size();
			for(int i=0,currentRow,currentCol,currentShape;i<size;i++){
				int[] data = q.poll();
				currentRow = data[0];
				currentCol = data[1];
				currentShape = data[2];
				for(int dir=0;dir<4;dir++){
					int nr = currentRow+dr[dir];
					int nc = currentCol+dc[dir];
					if(rangeCheck(nr,nc)){
						//영역 만족하고
						if(visit[nr][nc]==false) shapeAndDirectionCheck(nr,nc,currentShape,dir);
						//안가본곳에대해서
					}
				}
			}
		}
	}
	private static void shapeAndDirectionCheck(int nr, int nc, int currentShape, int dir) {
		switch (currentShape){//각 모양에 따라 방향을 정해주고 안가본 곳이면 가게한다.
		case 1://+
			switch (dir) {
			case 0:
				up(nr,nc);
				break;
			case 1:
				right(nr,nc);
				break;
			case 2:
				down(nr,nc);
				break;
			case 3:
				left(nr,nc);
				break;
			}
			break;
		case 2:// | 
			switch (dir) {
			case 0:
				up(nr,nc);
				break;
			case 2:
				down(nr,nc);
				break;
			}
			break;
		case 3: //ㅡ
			switch (dir) {
			case 1:
				right(nr,nc);
				break;
			case 3:
				left(nr,nc);
				break;
			}
			break;
		case 4:			
			switch (dir) {
			case 0:
				up(nr,nc);
				break;
			case 1:
				right(nr,nc);
				break;
			}
			break;
		case 5:		
			switch (dir) {
			case 1:
				right(nr,nc);
				break;
			case 2:
				down(nr,nc);
				break;
			}
			break;
		case 6:		
			switch (dir) {
			case 2:
				down(nr,nc);
				break;
			case 3:
				left(nr,nc);
				break;
			}
			break;
		case 7:
			switch (dir) {
			case 3:
				left(nr,nc);
				break;
			case 0:
				up(nr,nc);
				break;
			}
			break;
		}
	}
	private static void up(int nr, int nc) {
		if(map[nr][nc]==1||map[nr][nc]==2 ||map[nr][nc]==5 ||map[nr][nc]==6)insertQueue(nr,nc);
		return;
	}
	private static void right(int nr, int nc) {
		if(map[nr][nc]==1||map[nr][nc]==3 ||map[nr][nc]==6 ||map[nr][nc]==7)insertQueue(nr,nc);
		return;
	}
	private static void down(int nr, int nc) {
		if(map[nr][nc]==1||map[nr][nc]==2 ||map[nr][nc]==4 ||map[nr][nc]==7)insertQueue(nr,nc);
		return;
	}
	private static void left(int nr, int nc) {
		if(map[nr][nc]==1||map[nr][nc]==3 ||map[nr][nc]==4 ||map[nr][nc]==5)insertQueue(nr,nc);
		return;
	}
	private static void insertQueue(int nr, int nc) {
		visit[nr][nc] = true;
		q.add(new int[] {nr,nc,map[nr][nc]});
		Answer++;
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//맵크기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		//맨홀 위치
		L = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		Answer=1;
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
