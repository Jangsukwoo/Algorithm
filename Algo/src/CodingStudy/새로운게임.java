package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 새로운게임 {
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
		public Horse(int row, int col,int dir,int num) {
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.num = num;
		}		
	}
	
	public static void main(String[] args) throws IOException {
		setData();
		moveHorse();
		if(turn==1001 && find==false) System.out.println("-1");
		else System.out.println(turn);
	}
	
	private static void moveHorse() {
		turn = 1;
		while(turn<=1000){
			for(int i=0;i<K;i++){
				nextCheck(horeses[i]);
				if(find) return;//4개 이상쌓이면 종료
			}	
			turn++;
		}
	}
	private static void nextCheck(Horse horse) {//현재말에대해서
		int cr = horse.row;
		int cc = horse.col;
		int currentNum = horse.num;
		int dir = horse.dir;
		int nr = cr+dr[dir];
		int nc = cc+dc[dir];//다음칸
		int currentHorseIdx=0;
		ArrayList<Horse> redlist = null;
		currentHorseIdx = findCurrentIdx(currentNum,cr,cc);
		if(rangeCheck(nr,nc)){//경계안쪽에 있으면. 최초움직임
			kindOfGround(ground[nr][nc],cr,cc,dir,nr,nc,currentHorseIdx,redlist,false);//첫번재 시도니까 맨끝에 false
		}else {//경계값 만나서 반대로 돌린 후 재시도
			dir = reverseDirection(dir);
			nr = cr+dr[dir];
			nc = cc+dc[dir];//역방향으로 재시도		
			horseMap[cr][cc].get(currentHorseIdx).dir=dir;//역방향으로 바꿔준 뒤 
			kindOfGround(ground[nr][nc],cr,cc,dir,nr,nc,currentHorseIdx,redlist,true);//두번째 시도니까 맨끝에 true
		}
		if(find) return;//찾았으면 끝낸다.
	}
	private static void kindOfGround(int i, int cr, int cc,int dir, int nr, int nc, int currentHorseIdx, ArrayList<Horse> redlist, boolean retry){
		switch (ground[nr][nc]){//흰,빨,파
		case 0://흰땅		
			moveNextWhiteGround(currentHorseIdx,cr,cc,nr,nc);//업힌 애들까지 전부 이주 
			removeHorseAtCurrentGround(cr,cc,currentHorseIdx);
			if(isEnd(nr,nc)) return;
			break;
		case 1:	//빨간땅
			redlist = getHorseListToRedGround(currentHorseIdx,cr,cc,nr,nc);
			moveNextRedGround(redlist,nr,nc);
			removeHorseAtCurrentGround(cr,cc,currentHorseIdx);
			if(isEnd(nr,nc)) return;
			break;
		case 2://파랑땅
			if(retry==false){// 시도해봐야한다.
				nr = cr+dr[reverseDirection(dir)];
				nc = cc+dc[reverseDirection(dir)];//방향 바꿔줌		
				horseMap[cr][cc].get(currentHorseIdx).dir=reverseDirection(dir);//역방향으로 바꿔준 뒤 
				if(rangeCheck(nr, nc)){//역방향이 영역내에있으면
					switch (ground[nr][nc]){//흰,빨,파
					case 0:			
						moveNextWhiteGround(currentHorseIdx,cr,cc,nr,nc);//업힌 애들까지 전부 이주 
						removeHorseAtCurrentGround(cr,cc,currentHorseIdx);
						break;
					case 1:
						redlist = getHorseListToRedGround(currentHorseIdx,cr,cc,nr,nc);
						moveNextRedGround(redlist,nr,nc);
						removeHorseAtCurrentGround(cr,cc,currentHorseIdx);
						break;
					}	
					if(isEnd(nr,nc)) return;
				}
			}
			//만약 두번째 시도면 경계값을 만나서 뒤로 돌린거니까 무조건 영역 안에 있는것이고, 이경우 파랑땅을 만난 경우니까 방향을 바꿔준 뒤 아무 액션도 취하지 않는다.
			break;
		}
		
	}

	private static void moveNextRedGround(ArrayList<Horse> redlist, int nr, int nc) {
		for(int i=redlist.size()-1;i>=0;i--){//반대로 넣어줌
			horseMap[nr][nc].add(redlist.get(i));
		}
	}
	private static ArrayList<Horse> getHorseListToRedGround(int currentHorseIdx, int cr, int cc, int nr, int nc) {
		ArrayList<Horse> redlist = new ArrayList<Horse>();
		for(int i=currentHorseIdx;i<horseMap[cr][cc].size();i++){
			horseMap[cr][cc].get(i).row=nr;
			horseMap[cr][cc].get(i).col=nc;//바꿔주고
			redlist.add(horseMap[cr][cc].get(i));//리버스 처리 위해 일단 저장
		}
		return redlist;
	}
	private static void removeHorseAtCurrentGround(int cr, int cc, int currentHorseIdx) {
		for(int i=horseMap[cr][cc].size()-1;i>=currentHorseIdx;i--) horseMap[cr][cc].remove(i);//리스트니까 끝에서부터 역순으로 제거 
	}
	private static boolean isEnd(int nr, int nc) {
		if(horseMap[nr][nc].size()>=4) {
			find = true;
			return true;
		}
		return false;
	}
	private static void moveNextWhiteGround(int currentHorseIdx, int cr, int cc, int nr, int nc) {
		for(int i=currentHorseIdx;i<horseMap[cr][cc].size();i++){
			horseMap[cr][cc].get(i).row=nr;
			horseMap[cr][cc].get(i).col=nc;//바꿔주고
			horseMap[nr][nc].add(horseMap[cr][cc].get(i));
		}
	}
	private static int findCurrentIdx(int currentNum, int cr, int cc){
		for(int i=0;i<horseMap[cr][cc].size();i++){
			if(currentNum==horseMap[cr][cc].get(i).num){
				return i;
			}
		}
		return -1;
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
			horeses[i] = new Horse(row,col,dir,(i+1));
			horseMap[row][col].add(horeses[i]);
		}
	}
}
