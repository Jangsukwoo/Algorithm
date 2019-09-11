package BAEKJOON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* 아기상어는 1초에 상하좌우로 인접한 한칸 씩 이동
 * 아기상어의 크기는 2부터 시작
 * 자신보다 큰 물고기가 있는 칸은 지나가기 x
 * 자신과 같은 크기의 물고기가 있는 칸은 지나가기 O
 * 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
 * 더 이상 먹을 물고기가 없을 때 까지 진행
 * 먹을 수 있는 물고기가 1마리면 그 물고기를 먹으러 감
 * 1마리 이상일 때의 조건
 * 1. 가장 가까운 물고기
 * 2. 가까운 물고기가 여러마리일 때 가장 왼쪽에 있는 물고기 
 * 물고기를 먹으면 그 자리는 빈칸처리
 */
/*
4
9 0 0 0
0 3 3 3
0 3 1 3
0 3 3 3
 */
class Fish implements Comparable<Fish>{
	int row;
	int col;
	int dist;
	Fish(int r, int c, int d){
		row = r;
		col = c;
		dist = d;
	}
	@Override
	public int compareTo(Fish o) {
		if(this.dist==o.dist) {
			if(this.row==o.row) return Integer.compare(this.col,o.col);
			else return Integer.compare(this.row,o.row);
		}
		return Integer.compare(this.dist,o.dist);
	}
}
class Shark{
	int row;
	int col;
	Shark(int r, int c){
		row =r;
		col =c;
	}
}
public class 아기상어{
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] Map;
	static int N;
	static int sharkR,sharkC;
	static int sharkSize = 2;
	static int eat=0;
	static boolean[][] visit;
	static int time=0;
	static int dist=0;
	static Queue<Shark> q;
	static ArrayList<Fish> fishlist;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		q = new LinkedList<>();
		Map = new int[N][N];
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) {
				Map[row][col] = sc.nextInt();
				if(Map[row][col]==9) {
					sharkR = row;
					sharkC = col;
				}
			}
		while(fishCheck()){//먹이감이 있으면 true 없으면 false
			findFish(sharkR,sharkC);//가까운 먹이감 조사하기
			Collections.sort(fishlist);
			if(fishlist.size()>0) {
				time += fishlist.get(0).dist;
				eat++;

				if(sharkSize==eat) {
					sharkSize++;
					eat=0;
				}
				Map[sharkR][sharkC]=0;
				sharkR = fishlist.get(0).row;
				sharkC = fishlist.get(0).col;
				Map[fishlist.get(0).row][fishlist.get(0).col]=9;	
			}
			else if(fishlist.size()==0) break;
		}
		System.out.println(time);
				
	}
	private static boolean fishCheck() {
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) {
				if(row==sharkR && col==sharkC) continue;//아기상어의 위치를 제외하고
				if(Map[row][col]<sharkSize && Map[row][col]>0) return true;		
			}
		return false;
	}
	private static void findFish(int curR, int curC) {
		fishlist = new ArrayList<>();
		visit = new boolean[N][N];	
		dist=0;
		q.add(new Shark(curR,curC));
		visit[curR][curC] = true;
		while(!q.isEmpty()){
			dist++;
			int size = q.size();
			for(int i=0;i<size;i++) {
				Shark shark = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = shark.row+dr[dir];
					int nc = shark.col+dc[dir];
					if(range(nr,nc)) { //범위안의 이동
						if(Map[nr][nc]<=sharkSize && !visit[nr][nc]){
							visit[nr][nc]=true;
							q.add(new Shark(nr,nc));
							if(Map[nr][nc]<sharkSize && Map[nr][nc]>0){
								fishlist.add(new Fish(nr,nc,dist));
							}
						}
					}
				}
			}
		}
	}
	private static boolean range(int nr, int nc) {
		if((nr>=0&&nr<N) && (nc>=0&&nc<N)) return true;
		return false;
	}
}