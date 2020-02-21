package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 가장 처음 아기상어의 크기는 2
 * 1초에 상하좌우로 이동
 * 자기보다 큰 물고기가 있는 칸은 이동할 수 없다.
 * 자기보다 작은 물고기는 먹을 수 있다.
 * 자기랑 같은 크기를 가지는 물고기는 지나갈 수 있다.
 * 먹을 수 있는 물고기가 1마리면 그 물고기를 먹으러감
 * 1마리보다 더 많으면 거리가 가장 가까운 물고기 먹으러 감.
 * 거리가 가장 가까운 물고기가 많으면 가장 위, 왼쪽에 있는 물고기를 먹음.
 * 물고기를 먹으면 그 칸은 빈칸이 됌.
 * 아기 상어는 자신의 크기와 같은 수의 물고기를 먹으면 크기가 1증가함
 * 몇초동안 먹을 수 있는지?
 * 
 */
public class 아기상어 {
	static int N;
	static int[][] space;
	static ArrayList<Fish> fishlist;
	static BabyShark babyShark;
	static int time;
	static class BabyShark{
		int row;
		int col;
		int size;
		int eat;
		public BabyShark(int row, int col, int size, int eat) {
			this.row = row;
			this.col = col;
			this.size = size;
			this.eat = eat;
		}
	}
	static class Fish{
		int row;
		int col;
		int dist;
		public Fish(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		fishing();
		System.out.println(time);
	}
	private static void fishing(){
		while(true){
			if(possibleCheck()){//가능하면
				getFishList();
				if(fishlist.size()==0){//먹을 수 있는 물고기가 있지만 지나갈 수 없어서 못먹은 경우
					return;
				}
				else if(fishlist.size()==1){//먹을 수 있는 물고기가 딱 한마리인 경우 
					space[babyShark.row][babyShark.col]=0;
					babyShark.row = fishlist.get(0).row;
					babyShark.col = fishlist.get(0).col;
					space[babyShark.row][babyShark.col]=9;
					time+=fishlist.get(0).dist;
					babyShark.eat+=1;
				}else if(fishlist.size()>1){//정렬
					Collections.sort(fishlist, new Comparator<Fish>() {
						@Override
						public int compare(Fish o1, Fish o2) {
							if(o1.dist==o2.dist){
								if(o1.row==o2.row) return Integer.compare(o1.col,o2.col);
								else return Integer.compare(o1.row,o2.row);
							}
							return Integer.compare(o1.dist,o2.dist);//위 조건에 안걸리면 가장 가까운 물고기
						}
					});				
					space[babyShark.row][babyShark.col]=0;
					babyShark.row = fishlist.get(0).row;
					babyShark.col = fishlist.get(0).col;
					space[babyShark.row][babyShark.col]=9;
					time+=fishlist.get(0).dist;
					babyShark.eat+=1;
				}
			}else return; //먹을 수 있는 물고기가 한마리도 없다면
			if(babyShark.eat==babyShark.size){//먹은 물고기양이 현재 사이즈랑 같으면
				babyShark.size+=1;//사이즈증가
				babyShark.eat=0;
			}
		}
	}
	private static void getFishList() {
		int[] dr = {-1,0,1,0};
		int[] dc = {0,1,0,-1};//상우하좌
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> q = new LinkedList<int[]>();
		fishlist = new ArrayList<Fish>();
		q.add(new int[] {babyShark.row,babyShark.col});
		visit[babyShark.row][babyShark.col]=true;
		int dist=0;
		while(!q.isEmpty()){
			dist++;
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] currentShark = q.poll();
				int currentRow = currentShark[0];
				int currentCol = currentShark[1];
				for(int dir=0;dir<4;dir++) {
					int nextRow = currentRow+dr[dir];
					int nextCol = currentCol+dc[dir];
					if(rangeCheck(nextRow,nextCol)){//영역 만족하고
						if(visit[nextRow][nextCol]==false){//방문 안했고
							if(space[nextRow][nextCol]<=babyShark.size){
								//같은 크기면 지나갈 수 있으니깐
								visit[nextRow][nextCol] = true;
								q.add(new int[] {nextRow,nextCol});
								if(space[nextRow][nextCol]>0 && space[nextRow][nextCol]<babyShark.size) {
									fishlist.add(new Fish(nextRow,nextCol,dist));
								}
							}
						}
					}
				}
			}			
		}
	}
	private static boolean rangeCheck(int nextRow, int nextCol) {
		if(nextRow>=0 && nextRow<N && nextCol>=0 && nextCol<N) return true;
		return false;
	}
	private static boolean possibleCheck(){
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++){
				if(row==babyShark.row && col==babyShark.col) continue;
				if(space[row][col]<babyShark.size && space[row][col]>0){//아직 아기상어보다 작은 물고기가 존재하면
					return true;
				}
			}
		}//못만났으면 false
		return false;
	}
	private static void setData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		space = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++){
				space[row][col] = Integer.parseInt(st.nextToken());
				if(space[row][col]==9) {
					babyShark = new BabyShark(row,col,2,0);
				}
			}
		}
	}
}

