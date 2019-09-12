package BAEKJOON;

import java.util.Scanner;

//벽이나 자기 몸과 부딪히면 게임 종료
class Mission{
	int X;
	char C;
	public Mission(int x, char c) {
		this.X = x;
		this.C = c;
	}
}
class Snake{
	int headRow;
	int headCol;
	int tailRow;
	int tailCol;
	int headDirection;
	int tailDirection;

	public Snake(int hr,int hc,int tr, int tc, int hd,int td) {
		headRow = hr;
		headCol = hc;
		tailRow = tr;
		tailCol = tc;
		headDirection = hd;
		tailDirection = td;
	}
}
public class 뱀{
	static int[] dr = {-1,0,1,0}; 
	static int[] dc = {0,1,0,-1};//상우하좌
	static int[][] Map;
	static int[][] rotateMap;
	static int N,K,L;
	static Mission[] mission;
	static boolean gameOver;
	static int time;
	static Snake snake;
	static int MissionNumber;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Map = new int[N][N];
		rotateMap = new int[N][N];
		K = sc.nextInt();
		for(int i=0;i<K;i++){
			int row = sc.nextInt()-1;
			int col = sc.nextInt()-1;
			Map[row][col] = 4;//사과
		}
		L = sc.nextInt();
		mission = new Mission[L];
		for(int i=0;i<L;i++) {
			int x = sc.nextInt();
			String data = sc.next();
			char c = data.charAt(0);
			mission[i] = new Mission(x,c);
		}
	
		snake = new Snake(0,0,0,0,1,1); //Start
		Map[0][0]=1;
		rotateMap[0][0] = 1;//우
		headmove();
		System.out.println(time);
	}

	private static void headmove() {
		while(!gameOver){
			time++;
			int hnr = snake.headRow+dr[snake.headDirection];
			int hnc = snake.headCol+dc[snake.headDirection];//머리 이동
			if(rangeCheck(hnr,hnc)){//영역 만족
				rotateMap[hnr][hnc] = snake.headDirection;
				switch (Map[hnr][hnc]){//맵을 보니
				case 0://맨땅
					snake.headRow = hnr;
					snake.headCol = hnc;
					Map[hnr][hnc]=1;
					tailMove();	
					break;
				case 1: //자기자신
					gameOver = true;
					break;
				case 4: //사과 
					Map[hnr][hnc] = 1;
					snake.headRow = hnr;
					snake.headCol = hnc;
					break;
				}
			}
			else {
				gameOver = true;
				break;
			}

			if(gameOver) break;
			if(time==mission[MissionNumber].X){//미션넘버
				switch (mission[MissionNumber].C){
				case 'L':
					turnLeft();
					break;
				case 'D':
					turnRight();
					break;
				}
				if(MissionNumber < (mission.length-1)) {
					MissionNumber++;	
				}
			}
		}
	}

	private static void turnRight() {
		switch (snake.headDirection) {
		case 0:
			snake.headDirection = 1;
			break;
		case 1:
			snake.headDirection = 2;
			break;
		case 2:
			snake.headDirection = 3;
			break;
		case 3:
			snake.headDirection = 0;
			break;
		}
		rotateMap[snake.headRow][snake.headCol] = snake.headDirection;
	}

	private static void turnLeft() {
		switch (snake.headDirection) {
		case 0:
			snake.headDirection = 3;
			break;
		case 1:
			snake.headDirection = 0;
			break;
		case 2:
			snake.headDirection = 1;
			break;
		case 3:
			snake.headDirection = 2;
			break;
		}
		rotateMap[snake.headRow][snake.headCol] = snake.headDirection;
	}

	private static void tailMove() {
		int ntr = snake.tailRow+dr[rotateMap[snake.tailRow][snake.tailCol]];
		int ntc = snake.tailCol+dc[rotateMap[snake.tailRow][snake.tailCol]];
		Map[snake.tailRow][snake.tailCol] = 0;
		snake.tailRow = ntr;
		snake.tailCol = ntc;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc <N) return true;
		return false;
	}
}