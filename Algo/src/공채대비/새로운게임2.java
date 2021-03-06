package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


/*
 * 13:56~
 * 15:56.. 딱 두시간 ㅠ
 */
public class 새로운게임2{
	static int N,K;
	static int[][] chess;
	static Horse[] horselist;
	static int turn=0;
	static ArrayList<Horse>[][] horseMap;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean end;
	static int[] dr = {0,0,0,-1,1};
	static int[] dc = {0,1,-1,0,0};//1,2,3,4
	
	static class Horse{
		int row;
		int col;
		int dir;
		int idx;
		public Horse(int row, int col, int dir, int idx) {
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws IOException {
		setData();
		
		simulation();
		
		if(turn==1001) System.out.println("-1");
		else System.out.println(turn);
	}
	private static void simulation(){
		turn=1;
		while(turn<=1000) {
			for(int i=0;i<K;i++) {
				//업고 간다.
				move(horselist[i]);
				if(end) return;
			}
			turn++;
		}
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(horseMap[row][col].size());
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void move(Horse horse) {
		/*
		 * 0:흰
		 * 1:빨
		 * 2:파
		 */
		int currentIdx = getCurrenthorseIdx(horse);
		
		int reverseDir=getReverseDir(horse.dir);
		
		int dir = horse.dir;
		int nr = horse.row+dr[dir];
		int nc = horse.col+dc[dir];
		
		if(!rangeCheck(nr, nc)){//영역 밖임
			
			nr = horse.row+dr[reverseDir];
			nc = horse.col+dc[reverseDir];
			
			horselist[horse.idx].dir=reverseDir;
			if(chess[nr][nc]==2) {
			}else if(chess[nr][nc]==0) {
				moveWhiteArea(horse,currentIdx,nr,nc);
			}else if(chess[nr][nc]==1) {
				moveRedArea(horse,currentIdx,nr,nc);
			}
			if(horseMap[nr][nc].size()>=4) end=true;
		}else if(rangeCheck(nr, nc)){//영역 안쪽이면
			
			if(chess[nr][nc]==2){
				nr = horse.row+dr[reverseDir];
				nc = horse.col+dc[reverseDir];
				horselist[horse.idx].dir=reverseDir;
				if(!rangeCheck(nr, nc)){
				}else if(chess[nr][nc]==0) {
					moveWhiteArea(horse,currentIdx,nr,nc);
					if(horseMap[nr][nc].size()>=4) end=true;
				}else if(chess[nr][nc]==1) {
					moveRedArea(horse,currentIdx,nr,nc);
					if(horseMap[nr][nc].size()>=4) end=true;
				}
			}else if(chess[nr][nc]==0) {
				moveWhiteArea(horse,currentIdx,nr,nc);
				if(horseMap[nr][nc].size()>=4) end=true;
			}else if(chess[nr][nc]==1) {
				moveRedArea(horse,currentIdx,nr,nc);
				if(horseMap[nr][nc].size()>=4) end=true;
			}
		}
	}
	private static void moveRedArea(Horse horse, int currentIdx, int nr, int nc){
		ArrayList<Horse> redlist = new ArrayList<Horse>();
		for(int i=currentIdx;i<horseMap[horse.row][horse.col].size();i++) redlist.add(horseMap[horse.row][horse.col].get(i));
		for(int i=horseMap[horse.row][horse.col].size()-1;i>=currentIdx;i--) {
			horseMap[horse.row][horse.col].remove(i);
		}
		Collections.reverse(redlist);
		for(int i=0;i<redlist.size();i++){
			horseMap[nr][nc].add(redlist.get(i));
			horselist[redlist.get(i).idx].row=nr;
			horselist[redlist.get(i).idx].col=nc;
			horselist[redlist.get(i).idx].dir=redlist.get(i).dir;
			horselist[redlist.get(i).idx].idx=redlist.get(i).idx;
		}
	}
	private static void moveWhiteArea(Horse horse, int currentIdx,int nr,int nc){
		ArrayList<Horse> movelist = new ArrayList<Horse>();
		for(int i=currentIdx;i<horseMap[horse.row][horse.col].size();i++) movelist.add(horseMap[horse.row][horse.col].get(i));
		for(int i=horseMap[horse.row][horse.col].size()-1;i>=currentIdx;i--){
			horseMap[horse.row][horse.col].remove(i);
		}
		for(int i=0;i<movelist.size();i++){
			horseMap[nr][nc].add(movelist.get(i));
			horselist[movelist.get(i).idx].row=nr;
			horselist[movelist.get(i).idx].col=nc;
			horselist[movelist.get(i).idx].dir=movelist.get(i).dir;
			horselist[movelist.get(i).idx].idx=movelist.get(i).idx;
		}

	}

	private static int getReverseDir(int dir) {
		int reverseDir = 0;
		switch (dir) {
		case 1:
			reverseDir=2;
			break;
		case 2:
			reverseDir=1;
			break;
		case 3:
			reverseDir=4;
			break;
		case 4:
			reverseDir=3;
			break;
		}
		return reverseDir;
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static int getCurrenthorseIdx(Horse horse){
		int size = horseMap[horse.row][horse.col].size();
		for(int i=0;i<size;i++) {
			if(horseMap[horse.row][horse.col].get(i).idx==horse.idx) return i;
		}
		return 0;
	}
	
	
	
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		chess = new int[N][N];
		horseMap = new ArrayList[N][N];
		horselist = new Horse[K];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++){
				horseMap[row][col] = new ArrayList<Horse>();
				chess[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0,row,col,dir;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken())-1;
			col = Integer.parseInt(st.nextToken())-1;
			dir = Integer.parseInt(st.nextToken());
			horselist[i] = new Horse(row, col, dir,i);
			horseMap[row][col].add(horselist[i]);
		}
	}
}
