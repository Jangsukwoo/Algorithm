package CodingStudyHW;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * N by M 맵 (N: row, M: col)
 * 
 * 1<=N,M<=1000
 * 
 * 0: 이동가능
 * 1: 벽
 * 
 * 1,1에서 N,M까지 이동하는 최단경로
 * 시작하는칸,끝나는칸 포함해서 카운팅
 * 
 * 0,0시작 N-1,M-1 끝나는 점 
 * 이동도중에 하나의 벽은 부수고 갈 수 있다.
 * 
 * 벽을 부수고 도전하는 길인지 여부를 알아야할것같음
6 5
00000
11110
00000
01111
01111
00010 <- 반례

-> 3차원 방문 배열로 세번째 인덱스로 부수고 방문하는 경우랑 안부수고 방문하는 경우를 구분해주어서 두 가지의 방문처리를 검사.
 */
public class 벽부수고이동하기 {
	static int N,M;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][][] distMap;
	static int INF = 987654321;
	static char[][] map;
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		distMap = new int[N][M][2]; //3번째 인덱스 0이 안부쉈을 때 , 1이 부쉈을 때
		sc.nextLine();
		for(int row=0;row<N;row++) {
			String readLine = sc.nextLine();
			map[row] = readLine.toCharArray();
		}
		setDistMap();

		//처리
		BFS();
		
		//처리
		printAnswer();
		
	}
	private static void printAnswer(){
		int min = Integer.MAX_VALUE;
		for(int wall=0;wall<2;wall++) {
			min = Math.min(min,distMap[N-1][M-1][wall]);
		}
		if(min==INF) System.out.println("-1"); 
		else System.out.println(min);
	}
	private static void setDistMap() {
		for(int row=0;row<N;row++) 
			for(int col=0;col<M;col++) 
				for(int wall=0;wall<2;wall++) distMap[row][col][wall] = INF;
	}
	private static void BFS() {
		distMap[0][0][0] = 1;//안부숨
		distMap[0][0][0] = 1;//안부숨
		q.add(new int[] {0,0,1,1});//인덱스 0,1은 row,col, 2는 벽 부순 여부, 3은 거리
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] curInfo = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = curInfo[0]+dr[dir];
					int nc = curInfo[1]+dc[dir];
					int crash = curInfo[2];
					int dist = curInfo[3];
					if(rangeCheck(nr,nc)){//영역 만족
						switch (map[nr][nc]){//가려는 곳이
						case '0'://그냥 땅이고
							if(crash==1){//아직 안부쉈으면
								if(distMap[nr][nc][0]>dist+1) {
									distMap[nr][nc][0] = dist+1;
									q.add(new int[] {nr,nc,crash,dist+1});
								}		
							}else {//부시고 가는길이면
								if(distMap[nr][nc][1]>dist+1) {
									distMap[nr][nc][1] = dist+1;
									q.add(new int[] {nr,nc,crash,dist+1});
								}	
							}
														
							break;
						case '1'://벽인데
							if(crash==1){//아직 안부쉈으면 
								if(distMap[nr][nc][0]>dist+1) {
									distMap[nr][nc][0] = dist+1;
									q.add(new int[] {nr,nc,0,dist+1});//사용
								}	
							}
							break;
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
