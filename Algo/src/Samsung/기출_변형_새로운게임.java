package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/*
 * 16:45~
 */

public class 기출_변형_새로운게임 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] ground;
	static int[] dr = {0,0,0,-1,1};
	static int[] dc = {0,1,-1,0,0};//1번부터 우좌상하
	static ArrayList<Horse>[][] horseMap; 
	static Horse[] horeses;
	static int N,K;
	static int turn;
	static boolean find;
	static class Horse{
		int row;
		int col;
		int dir;
		int num;
		int idx;
		public Horse(int row, int col,int dir,int num, int idx) {
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.num = num;
			this.idx = idx;
		}		
	}

	public static void main(String[] args) throws IOException {
		setData();
		while(turn<=1000) {
			turn++;
			for(int i=0;i<K;i++){
				simultation(horeses[i]);
			}
		}
	}
	private static void simultation(Horse horse){
//		int cr = horse.row;
//		int cc = horse.col;
//		int cd = horse.dir;
//		int dir = horse.dir;
//		int nr = cr+dr[dir];
//		int nc = cc+dc[dir];//다음칸
//		int currentHorseIdx=0;
//		ArrayList<Horse> redlist = null;
//		if(rangeCheck(nr,nc)){//경계안쪽에 있으면. 최초움직임
//			kindOfGround(ground[nr][nc],cr,cc,dir,nr,nc,redlist,false);//첫번재 시도니까 맨끝에 false
//		}else {//경계값 만나서 반대로 돌린 후 재시도
//			dir = reverseDirection(dir);
//			nr = cr+dr[dir];
//			nc = cc+dc[dir];//역방향으로 재시도		
//			horseMap[cr][cc].get(currentHorseIdx).dir=dir;//역방향으로 바꿔준 뒤 
//			kindOfGround(ground[nr][nc],cr,cc,dir,nr,nc,redlist,true);//두번째 시도니까 맨끝에 true
//		}
//		if(find) return;//찾았으면 끝낸다.
	}

	private static int reverseDirection(int dir) {
		if(dir%2!=0) return (dir+1); //짝수면+1
		return (dir-1);//홀수면 -1
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ground = new int[N][N];
		horseMap = new ArrayList[N][N];
		horeses = new Horse[K];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				ground[row][col] = Integer.parseInt(st.nextToken());
				horseMap[row][col] = new ArrayList<Horse>();
			}
		}
		for(int i=0;i<K;i++){
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken())-1;
			int col = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			horeses[i] = new Horse(row,col,dir,(i+1),i);
			horseMap[row][col].add(horeses[i]);
		}
	}
}
