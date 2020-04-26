package CodingStudyHW;

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
 * 
 */
public class 아기상어 {
	static int N;
	static int[][] aquarium;
	static StringTokenizer st;
	static BabyShark babyshark;
	static boolean[][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static ArrayList<Fish> catchFishlist;
	static Queue<BabyShark> q = new LinkedList<BabyShark>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static class BabyShark{
		int row;
		int col;
		int size;
		int eat;
		int time;
		public BabyShark(int row, int col, int size, int eat, int time) {
			this.row = row;
			this.col = col;
			this.size = size;
			this.eat = eat;
			this.time = time;
		}
	}
	static class Fish {
		int row;
		int col;
		int catchTime;
		public Fish(int row, int col, int catchTime) {
			this.row = row;
			this.col = col;
			this.catchTime = catchTime;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		simulation();
		System.out.println(babyshark.time);
	}
	private static void simulation() {
		while(true){
			int feedAmount = aquariumCheck();
			if(feedAmount==0) return;
			else {
				bfs();
				switch (catchFishlist.size()){
				case 0://탐색해봤더니 갈 수없다. 그 먹이로 
					return;
				default://여러마리인 경우 
					sorting(); //물고기를 문제의 요구대로 정렬함 
					int catchFishR = catchFishlist.get(0).row;
					int catchFishC = catchFishlist.get(0).col;
					int catchTime = catchFishlist.get(0).catchTime;
					aquarium[babyshark.row][babyshark.col] = 0;
					aquarium[catchFishR][catchFishC] = 9;
					babyshark.row = catchFishR;
					babyshark.col = catchFishC;
					babyshark.eat++;
					babyshark.time+=catchTime;
					break;
				}
				if(babyshark.size==babyshark.eat) {
					babyshark.size++;
					babyshark.eat=0;
				}
			}
		}
	}
	private static void sorting() {
		Collections.sort(catchFishlist, new Comparator<Fish>() {
			@Override
			public int compare(Fish o1, Fish o2) {
				if(o1.catchTime==o2.catchTime){
					if(o1.row==o2.row) {
						return Integer.compare(o1.col,o2.col);
					}else {
						return Integer.compare(o1.row,o2.row);
					}
				}
				return Integer.compare(o1.catchTime,o2.catchTime);
			}
		});
	}
	private static void bfs(){
		catchFishlist = new ArrayList<Fish>();
		visit = new boolean[N][N];
		q.clear();
		insertQueue(babyshark);
		int time=0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				BabyShark currentShark = q.poll();
				int cr = currentShark.row;
				int cc = currentShark.col;
				int cs = currentShark.size;
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc) && visit[nr][nc]==false){
						if(aquarium[nr][nc]>currentShark.size) continue;
						else if(aquarium[nr][nc]==currentShark.size) {
							insertQueue(new BabyShark(nr,nc,cs,0,time+1));
						}else if(aquarium[nr][nc]>0 && aquarium[nr][nc]<currentShark.size){
							catchFishlist.add(new Fish(nr,nc,time+1));
							insertQueue(new BabyShark(nr,nc,cs,0,time+1));
						}else if(aquarium[nr][nc]==0){
							insertQueue(new BabyShark(nr,nc,cs,0,time+1));
						}
					}
				}
			}
			time++;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void insertQueue(BabyShark babyshark) {
		q.add(babyshark);
		visit[babyshark.row][babyshark.col] = true;
	}
	private static int aquariumCheck(){
		int cnt=0;
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(aquarium[row][col]<babyshark.size && aquarium[row][col]!=0) cnt++;
			}
		}
		return cnt;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		aquarium = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				aquarium[row][col] = Integer.parseInt(st.nextToken());
				if(aquarium[row][col]==9) {
					babyshark = new BabyShark(row,col,2,0,0);
				}
			}
		}
	}
}
