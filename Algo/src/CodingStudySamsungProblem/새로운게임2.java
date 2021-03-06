
package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * N by N 격자판
 * 0 : 흰색  -> 그 칸으로 이동
 * 1 : 빨간색 -> 쌓여있는 말의 순서를 바꿈 
 * 2: 파란색  -> A번 말의 이동방향을 반대로 하고 한 칸 이동
 * 단, 이동하려는 칸이 파란색인 경우에는 이동 하지 않고 방향만 반대 
 * 
 * 말의 이동정보가 주어지고 임의의 말이 업고있는 말이 4이상이 되는 턴을 출력
 * 
 * 1000턴 넘게 이동해도 답을 못구하면 그냥 끝낸다.
 * 
 * 역방향 처리할 때 인덱스 0인 말의 방향을 바꿔줘서 계속 틀렸다...
 * 태환씨 도움받아서 해결해버림..허무하다 반성하자
 */
public class 새로운게임2 {
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
		public Horse(int row, int col, int dir,int num) {
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
				if(find) return;//4개 이상 쌓이는 순간이므로 종료시켜버림
			}	
			turn++;
		}
	}
	private static void nextCheck(Horse horse) {//현재말에대해서
		int currentR = horse.row;
		int currentC = horse.col;
		int currentNum = horse.num;
		int dir = horse.dir;
		int nr = currentR+dr[dir];
		int nc = currentC+dc[dir];//다음칸
		int currentHorseIdx=0;
		ArrayList<Horse> redlist;
		currentHorseIdx = findCurrentIdx(currentNum,currentR,currentC);
		if(rangeCheck(nr,nc)){//경계안쪽에 있으면. 최초움직임
			switch (ground[nr][nc]){//흰,빨,파
			case 0://흰땅이면		
				moveNextWhiteGround(currentHorseIdx,currentR,currentC,nr,nc);//업힌 애들까지 전부 이주 
				removeCurrentGroundHorse(currentR,currentC,currentHorseIdx);
				if(isEnd(nr,nc)) return;
				break;
			case 1:			
				redlist = getMoveToRedGroundHorseList(currentHorseIdx,currentR,currentC,nr,nc);
				moveNextRedGround(redlist,nr,nc);
				removeCurrentGroundHorse(currentR,currentC,currentHorseIdx);
				if(isEnd(nr,nc)) return;
				break;
			case 2://파랑색이면			
				dir = reverseDirection(dir);//역방향준뒤
				nr = currentR+dr[dir];
				nc = currentC+dc[dir];//방향 바꿔줌		
				horseMap[currentR][currentC].get(currentHorseIdx).dir=dir;//역방향으로 바꿔준 뒤 
				if(rangeCheck(nr, nc)){//역방향이 영역내에있으면
					switch (ground[nr][nc]){//흰,빨,파
					case 0:			
						moveNextWhiteGround(currentHorseIdx,currentR,currentC,nr,nc);//업힌 애들까지 전부 이주 
						removeCurrentGroundHorse(currentR,currentC,currentHorseIdx);
						break;
					case 1:
						redlist = getMoveToRedGroundHorseList(currentHorseIdx,currentR,currentC,nr,nc);
						moveNextRedGround(redlist,nr,nc);
						removeCurrentGroundHorse(currentR,currentC,currentHorseIdx);
						break;
					case 2://파랑색이면 아무것도 안함
						break;
					}	
					if(isEnd(nr,nc)) return;
				}
				break;
			}
		
		}else {//경계값 만나서 반대로 돌렸는데 
			dir = reverseDirection(dir);//역방향을 준뒤 재시도
			nr = currentR+dr[dir];
			nc = currentC+dc[dir];			
			horseMap[currentR][currentC].get(currentHorseIdx).dir=dir;
			switch (ground[nr][nc]){//흰,빨,파
			case 0://흰땅이면		
				moveNextWhiteGround(currentHorseIdx,currentR,currentC,nr,nc);//업힌 애들까지 전부 이주 
				removeCurrentGroundHorse(currentR,currentC,currentHorseIdx);
				break;
			case 1:			
				redlist = getMoveToRedGroundHorseList(currentHorseIdx,currentR,currentC,nr,nc);
				moveNextRedGround(redlist,nr,nc);
				removeCurrentGroundHorse(currentR,currentC,currentHorseIdx);
				break;	
			case 2://파랑색이면 아무것도 안함
				break;
			}
			if(isEnd(nr,nc)) return;
		}

	}
	private static void moveNextRedGround(ArrayList<Horse> redlist, int nr, int nc) {
		for(int i=redlist.size()-1;i>=0;i--){//반대로 넣어줌
			horseMap[nr][nc].add(redlist.get(i));
		}
	}
	private static ArrayList<Horse> getMoveToRedGroundHorseList(int currentHorseIdx, int currentR, int currentC, int nr, int nc) {
		ArrayList<Horse> redlist = new ArrayList<Horse>();
		for(int i=currentHorseIdx;i<horseMap[currentR][currentC].size();i++){
			horseMap[currentR][currentC].get(i).row=nr;
			horseMap[currentR][currentC].get(i).col=nc;//바꿔주고
			redlist.add(horseMap[currentR][currentC].get(i));//리버스 처리 위해 일단 저장
		}
		return redlist;
	}
	private static void removeCurrentGroundHorse(int currentR, int currentC, int currentHorseIdx) {
		for(int i=horseMap[currentR][currentC].size()-1;i>=currentHorseIdx;i--) {
			horseMap[currentR][currentC].remove(i);
		}
	}
	private static boolean isEnd(int nr, int nc) {
		if(horseMap[nr][nc].size()>=4) {
			find = true;
			return true;
		}
		return false;
	}
	private static void moveNextWhiteGround(int currentHorseIdx, int currentR, int currentC, int nr, int nc) {
		for(int i=currentHorseIdx;i<horseMap[currentR][currentC].size();i++){
			horseMap[currentR][currentC].get(i).row=nr;
			horseMap[currentR][currentC].get(i).col=nc;//바꿔주고
			horseMap[nr][nc].add(horseMap[currentR][currentC].get(i));
		}
	}
	private static int findCurrentIdx(int currentNum, int currentR, int currentC) {
		for(int i=0;i<horseMap[currentR][currentC].size();i++){
			if(currentNum==horseMap[currentR][currentC].get(i).num){
				return i;
			}
		}
		return -1;
	}
	private static int reverseDirection(int dir) {
		int reverseDir=0;
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
			horeses[i] = new Horse(row, col, dir,(i+1));
			horseMap[row][col].add(horeses[i]);
		}
	}
}
