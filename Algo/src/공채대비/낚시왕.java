package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 9:45 ~ 10:25
 */
public class 낚시왕 {
	static int R,C,M;//겪자크기,상어 수
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,1,-1};//상하우좌
	static int answer;
	static Shark[] sharks;
	static PriorityQueue<Shark>[][] aquarium;
	static class Shark implements Comparable<Shark>{
		int row,col,speed,dir,size,idx;
		boolean life;
		public Shark(int row, int col, int speed, int dir, int size,int idx, boolean life) {
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
			this.idx = idx;
			this.life = life;
		}
		@Override
		public int compareTo(Shark o) {
			return -Integer.compare(this.size,o.size);
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		System.out.println(answer);
	}
	private static void simulation() {
		for(int col=0;col<C;col++){//shoot
			//한 컬럼씩 이동
			
			for(int row=0;row<R;row++){
				if(aquarium[row][col].size()>0){
					Shark shark = aquarium[row][col].poll();
					answer+=shark.size;
					shark.life = false;
					break;
				}
			}//작살 내림
			moveSharks();//상어들 이동 
			removeSharks();
		}
	}
	private static void removeSharks() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(aquarium[row][col].size()>1){//한마리 이상이다?
					Shark maxSizeShark = aquarium[row][col].poll();
					while(!aquarium[row][col].isEmpty()){
						Shark shark = aquarium[row][col].poll();
						shark.life=false;//죽음
					}
					aquarium[row][col].add(maxSizeShark);
				}
			}
		}
	}
	private static void moveSharks() {
		for(int i=0;i<M;i++) {
			if(sharks[i].life){//살아있는 상어면 
				Shark shark = sharks[i];
				ArrayList<Shark> temp = new ArrayList<Shark>();
				while(!aquarium[shark.row][shark.col].isEmpty()){//수족관에서 꺼냄
					if(aquarium[shark.row][shark.col].peek().idx==i) {
						aquarium[shark.row][shark.col].poll();
						break;
					}else temp.add(aquarium[shark.row][shark.col].poll());
				}
				move(shark);//이동시키고 넣음
				aquarium[shark.row][shark.col].add(shark);
				for(Shark s : temp) aquarium[s.row][s.col].add(s);
			}
		}
	}
	private static void move(Shark shark){
		int nr = shark.row;
		int nc = shark.col;
		int dir = shark.dir;
		for(int i=0;i<shark.speed;i++) {
			nr += dr[dir];
			nc += dc[dir];
			if(!rangeCheck(nr,nc)){//영역 벗어나면 
				nr-=dr[dir];
				nc-=dc[dir];
				if(dir%2==0) dir-=1;
				else dir+=1;
				nr+=dr[dir];
				nc+=dc[dir];
			}
		}
		shark.row = nr;
		shark.col = nc;
		shark.dir = dir;
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		aquarium = new PriorityQueue[R][C];
		sharks = new Shark[M];
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				aquarium[row][col] = new PriorityQueue<Shark>();
			}
		}
		for(int i=0,row,col,speed,dir,size;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken())-1;
			col = Integer.parseInt(st.nextToken())-1;
			speed = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			size = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(row, col, speed, dir, size,i,true);
			sharks[i] = shark;
			aquarium[row][col].add(shark);
		}
	}
}
