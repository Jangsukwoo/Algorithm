package BAEKJOON;


/*
 * 좌표만 필요해서 RC 클래스 만들지 않고
 * 2개짜리 int 배열로 0 = rowValue, 1 = colValue로 지정함
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 유기농배추 {
	static int N,M,K;//세로크기,가로크기
	static int[][] yard;
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][] visit;
	static int bugs;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++) {
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			yard = new int[N][M];
			visit = new boolean[N][M];
			bugs=0;
			for(int k=0;k<K;k++){
				int m = sc.nextInt();
				int n = sc.nextInt();
				yard[n][m] = 1;
			}
			for(int row=0;row<N;row++) {
				for(int col=0;col<M;col++){
					if(visit[row][col]==false && yard[row][col]==1){
						bugs++;
						q.add(new int[]{row,col});
						visit[row][col] = true;
						BFS(row,col);
					}
				}
			}
			System.out.println(bugs);
		}
	}
	private static void BFS(int row, int col) {
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] curRC = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = curRC[0]+dr[dir];
					int nc = curRC[1]+dc[dir];
					if(rangeCheck(nr,nc)){
						if(visit[nr][nc]==false && yard[nr][nc]==1){
							q.add(new int[] {nr,nc});
							visit[nr][nc] = true;
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
}
