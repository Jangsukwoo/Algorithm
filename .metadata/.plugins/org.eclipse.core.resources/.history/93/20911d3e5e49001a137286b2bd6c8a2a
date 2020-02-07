package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 2차원 게임판 맵이 있음
 * -1 : 블랙홀
 * 0 : 빈공간
 * 1~5 : 블록
 * 6~10 : 웜홀
 * 
 * 벽이나 블록에 부딪혔을 떄 점수를 획득 함
 * 임의의 빈공간에서 임의의 방향으로 출발시켰을 떄 
 * 얻을 수 있는 최대의 점수.
 * 블랙홀을 만나거나 출발위치로 돌아왔을 떄 게임은 끝난다
 * 
 * 제약사항
 * 웜홀과 블랙홀이 존재하지 않을 수도 있음.
 * 웜홀은 서로 묶여있고 
1
10
0 1 0 3 0 0 0 0 7 0 
0 0 0 0 -1 0 5 0 0 0 
0 4 0 0 0 3 0 0 2 2 
1 0 0 0 1 0 0 3 0 0 
0 0 3 0 0 0 0 0 6 0 
3 0 0 0 2 0 0 1 0 0 
0 0 0 0 0 1 0 0 4 0 
0 5 0 4 1 0 7 0 0 5 
0 0 0 0 0 1 0 0 0 0 
2 0 6 0 0 4 0 0 0 4 

1
10
0 4 0 0 0 0 4 0 5 0 
0 0 0 0 0 0 0 0 3 0 
0 0 0 5 6 0 0 0 0 2 
3 0 0 1 0 0 1 4 0 2 
2 0 0 0 0 5 0 0 5 0 
0 0 1 0 2 0 0 0 5 0 
0 0 0 0 0 0 6 1 0 0 
0 0 1 0 2 0 2 4 0 0 
0 0 0 0 0 0 2 0 0 0 
2 0 0 0 0 0 0 3 0 0 

1
8
0 0 0 3 0 0 0 0
0 0 2 0 0 5 0 0
0 0 5 0 3 0 0 0
0 0 1 1 0 0 0 4
0 0 0 0 0 0 0 0
0 0 0 0 0 0 5 0
0 0 4 0 0 3 1 0
2 0 0 4 3 4 0 0
 *
 */
public class 핀볼게임_시간초과 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] pinballMap;
	static int[][] moveMap;
	static boolean[][][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static int N;
	static int maxScore;
	static int startR,startC;
	static boolean blackhall;
	static WormHall[] wormhalls = new WormHall[11];
	static class WormHall{
		int firstGateR;
		int firstGateC;
		int secondGateR;
		int secondGateC;
		
		public int[] gateCheck(int row,int col){
			int nextR=0;
			int nextC=0;
			if(row==firstGateR && col==firstGateC){
				nextR = secondGateR;
				nextC = secondGateC;
			}else if(row==secondGateR && col==secondGateC) {
				nextR = firstGateR;
				nextC = firstGateC;
			}
			return new int[] {nextR,nextC};
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			gameStart();
			System.out.println("#"+testcase+" "+maxScore);
		}
	}
	private static void gameStart() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(pinballMap[row][col]==0){
					//빈공간이면 출발
					for(int dir=0;dir<4;dir++){
						startR = row;
						startC = col;//시작 위치 
					//	if(startR==5 && startC==9) {
							//System.out.println("시작위치방향"+startR+" "+startC+" "+dir);
							shoot(dir);
						//}
					}
				}
			}
		}
	}
	private static void shoot(int d){
		visit = new boolean[N][N][4];
		visit[startR][startC][d] = true;
		int getScore = 0;
		int nr = startR;
		int nc = startC;
		blackhall = false;
		moveMap = new int[N][N];
		moveMap[startR][startC]=1;
		int dir = d;
		
		while(true){
			//다음 위치 값
			//view();
			nr +=dr[dir];
			nc +=dc[dir];
			if(rangeCheck(nr,nc)){//경계 안에 있는 경우면서
				if(visit[nr][nc][dir]==false){//방문해보지 못한 자리면 
					if(nr==startR && nc==startC){//시작 위치면 
						maxScore = Math.max(getScore,maxScore);
						return;
					}
					visit[nr][nc][dir] = true;//밟은 땅과 방향 체크해주고
					moveMap[nr][nc]=1;
					int[] nextInfo = positionCheck(nr,nc,dir,getScore);//다음 위치 결정해주기
					if(blackhall) {
						getScore = nextInfo[3];
						maxScore = Math.max(getScore,maxScore);
						return;
					}
					nr = nextInfo[0];
					nc = nextInfo[1];
					dir =nextInfo[2];
					getScore = nextInfo[3];
				}
			}else {//경계값 벗어나는 경우면 그냥 바깥 테두리 벽에 부딪히는 경우 
				dir=(dir+2)%4;//역방향 처리하고 
				nr+=dr[dir];
				nc+=dc[dir];
				getScore+=1;//그냥 경계 벽에 부딪혔으니 
				//벽 부딪힐때도 getscore++;
				if(nr==startR && nc==startC){//시작 위치면 
					maxScore = Math.max(getScore,maxScore);
					return;
				}
				if(visit[nr][nc][dir]==false){//방문해보지 못한 자리면 {
					visit[nr][nc][dir] = true;//밟은 땅과 방향 체크해주고 
					moveMap[nr][nc]=1;
					int[] nextInfo = positionCheck(nr,nc,dir,getScore);//다음 위치 결정해주기
					if(blackhall) {
						getScore = nextInfo[3];
						maxScore = Math.max(getScore,maxScore);
						return;
					}
					nr = nextInfo[0];
					nc = nextInfo[1];
					dir =nextInfo[2];
					getScore = nextInfo[3];
				}
			}
		}
		
	}


	private static void view() {
		System.out.println("이동");
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(moveMap[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static int[] positionCheck(int nr, int nc, int dir, int getScore){//밟은 땅에 대해 조사
		int nextR=0;
		int nextC=0;
		int nextDir=0;
		int[] nextInfo = null;
		switch (pinballMap[nr][nc]){
		case 0:
			nextInfo = new int[] {nr,nc,dir,getScore};//맨땅이면 그냥감 
			break;
		case -1://블랙홀이면		
			blackhall=true;
			nextInfo = new int[] {nr,nc,dir,getScore};//맨땅이면 그냥감 
			break;
		case 1:
			nextDir = blockNo1(dir);
			nextInfo = new int[] {nr,nc,nextDir,getScore+1};
			break;
		case 2:
			nextDir = blockNo2(dir);
			nextInfo = new int[] {nr,nc,nextDir,getScore+1};
			break;
		case 3:
			nextDir = blockNo3(dir);
			nextInfo = new int[] {nr,nc,nextDir,getScore+1};
			break;
		case 4:
			nextDir = blockNo4(dir);
			nextInfo = new int[] {nr,nc,nextDir,getScore+1};
			break;
		case 5:
			nextDir = blockNo5(dir);
			nextInfo = new int[] {nr,nc,nextDir,getScore+1};
			break;
			
		case 6: case 7: case 8: case 9: case 10://웜홀이면
			int[] nextGate = wormhalls[pinballMap[nr][nc]].gateCheck(nr, nc);
			nextR = nextGate[0];
			nextC = nextGate[1];
			nextInfo = new int[] {nextR,nextC,dir,getScore};//방향 그대로 
			break;
		}
		return nextInfo;
	}
	
	private static int blockNo1(int dir) {
		int nextDir=0;
		switch (dir) {
		case 0:
			nextDir = 2;
			break;
		case 1:
			nextDir = 3;
			break;
		case 2:
			nextDir = 1;
			break;
		case 3:
			nextDir = 0;
			break;
		}
		return nextDir;
	}
	private static int blockNo2(int dir) {
		int nextDir=0;
		switch (dir) {
		case 0:
			nextDir = 1;
			break;
		case 1:
			nextDir = 3;
			break;
		case 2:
			nextDir = 0;
			break;
		case 3:
			nextDir = 2;
			break;
		}
		return nextDir;
	}
	private static int blockNo3(int dir) {
		int nextDir=0;
		switch (dir) {
		case 0:
			nextDir = 3;
			break;
		case 1:
			nextDir = 2;
			break;
		case 2:
			nextDir = 0;
			break;
		case 3:
			nextDir = 1;
			break;
		}
		return nextDir;
	}
	private static int blockNo4(int dir) {
		int nextDir=0;
		switch (dir) {
		case 0:
			nextDir = 2;
			break;
		case 1:
			nextDir = 0;
			break;
		case 2:
			nextDir = 3;
			break;
		case 3:
			nextDir = 1;
			break;
		}
		return nextDir;
	}
	private static int blockNo5(int dir) {
		int nextDir=0;
		switch (dir) {
		case 0:
			nextDir = 2;
			break;
		case 1:
			nextDir = 3;
			break;
		case 2:
			nextDir = 0;
			break;
		case 3:
			nextDir = 1;
			break;
		}
		return nextDir;
	}

	
	
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		pinballMap = new int[N][N];
		maxScore=0;
		wormhalls = new WormHall[11];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++){
				pinballMap[row][col] = Integer.parseInt(st.nextToken());
				//웜홀 묶기 
				if(pinballMap[row][col]>=6 && pinballMap[row][col]<=10){
					//웜홀이면
					if(wormhalls[pinballMap[row][col]]==null){
						wormhalls[pinballMap[row][col]] = new WormHall();
						wormhalls[pinballMap[row][col]].firstGateR = row;
						wormhalls[pinballMap[row][col]].firstGateC = col;
					}else {
						wormhalls[pinballMap[row][col]].secondGateR = row;
						wormhalls[pinballMap[row][col]].secondGateC = col;
					}
				}
			}
		}
	}
}
