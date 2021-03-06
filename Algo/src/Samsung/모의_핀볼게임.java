package Samsung;
import java.util.ArrayList;

/*
 * 시작 200
 * 보이저처럼 풀려고 했으나
 * 그냥 완전탐색으로 풀었음
 * 재귀함수로하니 스택오버플로우 터져서
 * 그냥 무한루프로 해결
 * 끝 459
 * 걸린시간 딱 3시간..ㄷㄷ
 */
import java.util.Scanner;
class Pinball{
	int row;
	int col;
	int dir;
	public Pinball(int r, int c, int d){
		row = r;
		col = c;
		dir =d;
	}
}
class Wormhole{
	int row;
	int col;
	public Wormhole(int r,int c) {
		row =r;
		col =c;
	}
}
public class 모의_핀볼게임 {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static int N;
	static int[][] board;
	static int maxscore;
	static Wormhole[][] wormholes;
	static int[] wormholeCount;
	static int startR, startC;
	static ArrayList<Pinball> startList = new ArrayList<Pinball>();
	static int[][] testmap;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++){
			N = sc.nextInt();
			board = new int[N][N];
			
			maxscore=0;
			wormholes = new Wormhole[5][2]; 
			wormholeCount = new int[5];
			startList.clear();
			for(int row=0;row<N;row++) {
				for(int col=0;col<N;col++) {
					board[row][col] = sc.nextInt();
					if(board[row][col]==0){
						startList.add(new Pinball(row,col,0));
					}
					else if(board[row][col]>=6 && board[row][col]<=10){
						if(wormholeCount[board[row][col]-6]==0){
							wormholeCount[board[row][col]-6]++;
							wormholes[board[row][col]-6][0] = new Wormhole(row,col);
						}else {							
							wormholes[board[row][col]-6][1] = new Wormhole(row,col);
						}
					}
				}
			}
			int size = startList.size();
			for(int i=0;i<size;i++){
				for(int dir=0;dir<4;dir++){
					Pinball curPinball = startList.get(i);
					startR = curPinball.row;
					startC = curPinball.col;
					int nr = curPinball.row+dr[dir];
					int nc = curPinball.col+dc[dir];
					int score=0;
					int td = dir;
					while(true) {
						if(nr==-1){ //벽을 만나는 4가지의 경우
							nr= 0;
							dir = (dir+2)%4;//대칭
							score++;
						}else if(nr==N){
							nr= N-1;
							dir = (dir+2)%4;//대칭
							score++;
						}
						if(nc==-1){
							nc= 0;
							dir = (dir+2)%4;//대칭
							score++;
						}else if(nc==N){
							nc= N-1;
							dir = (dir+2)%4;//대칭
							score++;
						}else {//벽이 아니고
							if(board[nr][nc]== -1 || (nr==startR && nc==startC)){// 블랙홀이거나 시작점이라면
								maxscore = Math.max(maxscore,score);
								break;//무한루프 종료
							}else {// 맨땅, 블록, 웜홀이라면
								switch (board[nr][nc]) {
								case 0:	//맨땅				
									nr=nr+dr[dir];
									nc=nc+dc[dir];
									break;
								case 1:	//1~5는 블록 케이스
									switch (dir) {
									case 0: case 1:
										dir = (dir+2)%4;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									case 2:
										dir = 1;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									case 3:
										dir = 0;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									}
									
									break;
								case 2:	
									switch (dir) {
									case 1: case 2:
										dir = (dir+2)%4;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									case 0:
										dir = 1;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									case 3:
										dir = 2;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									}
									break;
								case 3:		
									switch (dir) {
									case 2: case 3:
										dir = (dir+2)%4;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									case 0:
										dir = 3;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									case 1:
										dir = 2;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									}
									break;
								case 4:		
									switch (dir) {
									case 0: case 3:
										dir = (dir+2)%4;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									case 1:
										dir = 0;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									case 2:
										dir = 3;
										nr=nr+dr[dir];
										nc=nc+dc[dir];
										score++;
										break;
									}
									break;
								case 5:		
									dir = (dir+2)%4;
									nr=nr+dr[dir];
									nc=nc+dc[dir];
									score++;
									break;
								case 6:case 7:case 8:case 9:case 10: //웜홀케이스	
									if(wormholes[board[nr][nc]-6][0].row==nr && wormholes[board[nr][nc]-6][0].col==nc){
										int tr = wormholes[board[nr][nc]-6][1].row+dr[dir];
										int tc = wormholes[board[nr][nc]-6][1].col+dc[dir];
										nr = tr;
										nc = tc;
									}else {
										int tr = wormholes[board[nr][nc]-6][0].row+dr[dir];
										int tc = wormholes[board[nr][nc]-6][0].col+dc[dir];
										nr = tr;
										nc = tc;
									}
									break;
								}
							}
						}
					}
					dir = td;
				}
			}
			System.out.println("#"+testcase+" "+maxscore);
		}
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(testmap[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
