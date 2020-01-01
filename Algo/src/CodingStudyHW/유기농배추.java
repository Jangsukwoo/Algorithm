package CodingStudyHW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 입력
 * 테스트케이스의 개수 T
 * 가로 M,세로 N, 배추가 심어져있는 위치 개수 k
 * 1<=M,N<=50
 * 1<=K<=2500
 * 
 * 맵에 1은 배추 0은 그냥 땅
 * 1로 모인 군집의 개수를 구하기
 * 
 * 
 */
public class 유기농배추 {
	static int M,N,K;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int earthworm;
	static int[][] farm;
	static boolean[][] visit;
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int testcase=1;testcase<=T;testcase++){
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());//가로
			N = Integer.parseInt(st.nextToken());//세로
			K = Integer.parseInt(st.nextToken());
			farm = new int[N][M];
			visit = new boolean[N][M];
			earthworm=0;
			for(int k=0;k<K;k++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				farm[Y][X] = 1; 
			}//입력 끝
			
			//처리
			search();
			
			bw.write(earthworm+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void search() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				if(farm[row][col]==1 && visit[row][col]==false) {
					earthworm++;
					q.add(new int[] {row,col});
					visit[row][col] = true;
					BFS();
				}
			}
		}
	}
	private static void BFS() {
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentRC = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = currentRC[0]+dr[dir];
					int nc = currentRC[1]+dc[dir];
					if(rangeCheck(nr,nc) && visit[nr][nc]==false && farm[nr][nc]==1) {
						//영역 만족하고 방문하지 않은 칸이면서 인접한 배추면
						visit[nr][nc] = true;
						q.add(new int[] {nr,nc});
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
