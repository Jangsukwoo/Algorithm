package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 14:00~
 * 유닛 최소 이동 회수 
5 5 2 1 3
3 2
3 3
4 1
1 4
15:16 끝
오른쪽처리를 잘못해서 오래걸림 구현 연습 더 빡세게 ㅠ
 */
public class 유닛이동시키기 {
	static int N,M,A,B,K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] map; //N by M
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean[][] visit;
	static Queue<int[]> q = new LinkedList<int[]>();
	static int dist;
	static boolean end;
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		if(end) System.out.println(dist);
		else System.out.println("-1");
	}
	private static void bfs(){
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRC = q.poll();
				int cr = curRC[0];
				int cc = curRC[1];
				if(map[cr][cc]=='E') {
					end = true;
					return;
				}
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					boolean possible = true;
					if(rangeCheck(nr, nc)) {
						switch (dir){//상 우 하 좌 
						case 0: //상
							for(int col=nc;col<(nc+B);col++){
								if(possibleCheck(nr,col)) continue;	
								else {
									possible = false;
									break;
								}
							}
							break;
						case 2://하
							for(int col=nc;col<(nc+B);col++){
								if(possibleCheck((nr+A-1),col)) continue;	
								else {
									possible = false;
									break;
								}
							}
							break;
						case 1:
							for(int row=nr;row<(nr+A);row++){
								if(possibleCheck(row,(nc+B-1))) continue;	
								else {
									possible = false;
									break;
								}
							}
							break;
						case 3:
							for(int row=nr;row<(nr+A);row++){
								if(possibleCheck(row,nc)) continue;	
								else {
									possible = false;
									break;
								}
							}
							break;
						}
					}else possible = false;
					if(possible) insertQueue(nr,nc);
				}
			}
			dist++;
		}
	}
	private static boolean possibleCheck(int nr, int nc) {
		if(map[nr][nc]!='X') return true;
		return false;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=1 && nr<=N && nc>=1 && nc<=M){//영역 만족하고
			if((nc+B-1)<=M && (nr+A-1)<=N) {
				if(visit[nr][nc]==false){//방문하지 않았다.
					return true;
				}
			}
		}
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N+1][M+1];
		visit = new boolean[N+1][M+1];
		for(int row=1;row<=N;row++) Arrays.fill(map[row],'.');
		for(int k=0,row,col;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			map[row][col] = 'X';//장애물
		}
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		map[sr][sc] = 'S';
		st = new StringTokenizer(br.readLine());
		int er = Integer.parseInt(st.nextToken());
		int ec = Integer.parseInt(st.nextToken());
		map[er][ec] = 'E';
		insertQueue(sr,sc);
	}
	private static void insertQueue(int row, int col) {
		q.add(new int[] {row,col});
		visit[row][col] = true;
	}
}
