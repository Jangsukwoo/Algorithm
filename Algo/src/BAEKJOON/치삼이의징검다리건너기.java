package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/* 치삼이는 상,하,좌,우,대각선으로 이동 가능
 * 땅,돌은 너무 뜨겁다.
 * 돌이 있는 부분은 물이 차오르면 지나갈 수 있다.
 * 물이 차오르는 위치가 주어짐.
 * 물은 인접해있으면 퍼지게 되는데
 * 상,하,좌,우로 인접한 경우만 퍼진다.
 * 치삼이가 이동하는 것은 시간이 걸리지 않음.
 * 치삼이가 1,1 -> N,N까지 가장 빠르게 도착하는 경우는 몇일?
 * 
 * 돌이 있는 부분은 물이 차오르면 식혀져서 지나갈 수 있다.
 * 1시간 걸림
 * 
 */
public class 치삼이의징검다리건너기 {
	static int N;
	static int W;
	static int[] dr = {-1,0,1,0,-1,1,1,-1};
	static int[] dc = {0,1,0,-1,1,1,-1,-1};//상우하좌
	static int[][] ground;
	static boolean[][] visit;
	static boolean[][] water;
	static int day;
	static boolean flag;
	static Queue<int[]> q = new LinkedList<int[]>();
	static Queue<int[]> chisamQ = new LinkedList<int[]>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		W = sc.nextInt();
		ground = new int[N][N];
		water = new boolean[N][N];
		for(int i=0;i<W;i++) {
			int x = sc.nextInt()-1;
			int y = sc.nextInt()-1;
			water[x][y]=true;
			q.add(new int[] {x,y});
		}
		sc.nextLine();
		for(int row=0;row<N;row++) {
			String readLine = sc.nextLine();
			char[] data = readLine.toCharArray();
			for(int col=0;col<N;col++) {
				ground[row][col] = Character.getNumericValue(data[col]);
			}
		}//입력 끝
		
		BFS();
		if(flag) System.out.println(day);
		else System.out.println("-1");
	}

	private static void BFS() {
		while(!q.isEmpty()){
			chisam();
			if(flag) return;
			day++;
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRC = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = curRC[0]+dr[dir];
					int nc = curRC[1]+dc[dir];
					if(rangeCheck(nr,nc)){
						if(water[nr][nc]==false){
							water[nr][nc]=true;
							q.add(new int[] {nr,nc});
						}
					}
				}
			}	
		}
	}
	private static void chisam() {
		visit = new boolean[N][N];
		chisamQ.add(new int[] {0,0});
		visit[0][0] = true;
		while(!chisamQ.isEmpty()){
			int size = chisamQ.size();
			for(int i=0;i<size;i++) {
				int[] curRC = chisamQ.poll();
				for(int dir=0;dir<8;dir++){
					int nr = curRC[0] + dr[dir];
					int nc = curRC[1] + dc[dir];
					if(rangeCheck(nr, nc)) {
						if(nr==(N-1) && nc==(N-1)) {
							flag = true;
							return;
						}
						if(water[nr][nc]==true && ground[nr][nc]==1 && visit[nr][nc]==false){
							visit[nr][nc] = true;
							chisamQ.add(new int[] {nr,nc});
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(visit[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
