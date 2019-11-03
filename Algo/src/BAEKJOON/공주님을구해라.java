package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 9:40~
 * 0은 땅
 * 1은 벽
 * 2는 그람
 * 
 * 성입구 0,0
 * 공주 위치 N-1,M-1
 * 
 * int[3]에서
 * 0,1은 row,col
 * 2는 그람 보유 유무
 * 
 * 
6 6 9
0 0 0 0 1 1
0 0 0 0 0 2
1 1 1 0 1 0
0 0 0 0 0 0
0 1 1 1 1 1
0 0 0 0 0 0

6 6 10
0 0 0 0 1 1
0 0 0 0 0 2
1 1 1 0 1 0
0 0 0 0 0 0
0 1 1 1 1 1
0 0 0 0 0 0

????????왜틀리지????
 */
public class 공주님을구해라 {
	static int N,M,T;//row,col,time
	static int second;
	static int[][] castle;
	static int[][] moveCheck;
	static boolean[][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static boolean possible;
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		castle = new int[N][M];
		visit= new boolean[N][M];
		moveCheck = new int[N][M];
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				castle[row][col] = sc.nextInt();
				moveCheck[row][col] =castle[row][col];
			}
		}
		
		if(castle[0][0]==0)q.add(new int[]{0,0,0});
		else if(castle[0][0]==2) q.add(new int[] {0,0,1});
		visit[0][0] = true;
		moveCheck[0][0] = 3;
		possible = false;
		BFS();
		if(possible) System.out.println(second);
		else System.out.println("Fail");
	}
	private static void BFS() {
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRC = q.poll();
				if(curRC[0]==(N-1) && curRC[1]==(M-1)){//공주면 제한시간내에 도달 가능
					possible=true;
					return;//끝
				}
				for(int dir=0;dir<4;dir++) {
					int nr = curRC[0]+dr[dir];
					int nc = curRC[1]+dc[dir];
					if(rangeCheck(nr,nc)){//영역 만족하는데
						switch(curRC[2]){ //그람이 있으면 1
						case 0://그람이 없으면
							if(castle[nr][nc]==0 && visit[nr][nc]==false){//맨땅이면 감
								visit[nr][nc]=true;
								q.add(new int[] {nr,nc,0});
								moveCheck[nr][nc]=3;
							}else if(castle[nr][nc]==2 && visit[nr][nc]==false){
								visit[nr][nc]=true;
								q.add(new int[] {nr,nc,1});//그람 획득
								moveCheck[nr][nc]=3;
							}
							break;
						case 1: //그람이 있으면
							if(visit[nr][nc]==false){//안가본 곳이면 다 갈 수 있다.
								visit[nr][nc]=true;
								q.add(new int[] {nr,nc,1});
								moveCheck[nr][nc]=3;
							}
							break;
						}
					}
				}
			}
			if(second==T) return;
			second++;
			System.out.println(second+"초후");
			view();
		}
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				System.out.print(moveCheck[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
}
