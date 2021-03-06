package Samsung;

import java.util.Scanner;

/*
 * 로봇 동작
 * 현재 위치 청소
 * 현재 방향 기준 왼쪽 방향으로 90도 탐색
 * 아직 청소하지 않은 공간있으면 회전 후 전진
 * 모두 청소되어있으면 바라보는 방향 유지 후 한칸 후진
 * 후진 불가능하면 정지
 * 
 * 이미 청소되어있는 칸은 청소하지 않음.
 * 벽 통과 불가
 */
class ROBOT{
	int row;
	int col;
	int direction;
	public ROBOT(int r, int c, int d) {
		row = r;
		col = c;
		direction = d;
	}
}
public class 기출_로봇청소기 {
	static int N,M;//세로,가로
	static int clean;
	static int[][] map;
	static boolean[][] cleanMap;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1}; //상우하좌
	static ROBOT Robot;
	static boolean robotOFF;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N =sc.nextInt();
		M =sc.nextInt();
		map = new int[N][M];
		cleanMap = new boolean[N][M];
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		Robot = new ROBOT(r,c,d);
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				map[row][col] = sc.nextInt();
				if(map[row][col]==1) cleanMap[row][col]=true;
			}
		}
		clean++;
		simulation();
		System.out.println(clean);
	}
	private static void simulation() {
		while(true){
			boolean work = false;
			map[Robot.row][Robot.col] = 9;//현위치 청소
			int currentdir = Robot.direction;
			boolean allSideCheck = true;
			for(int dir=0;dir<4;dir++){
				int nextDir = currentdir-1;
				if(nextDir == -1) nextDir = 3;
				int nr = Robot.row+dr[nextDir];
				int nc = Robot.col+dc[nextDir];
				if(rangeCheck(nr,nc)){//영역 만족하고
					if(map[nr][nc]==0){//청소하지않은 곳이면
						work=true;
						Robot.row = nr;
						Robot.col = nc;
						Robot.direction = nextDir;
						allSideCheck = false;
						break;
					}else currentdir = nextDir;	
				}
			}
			if(work) clean++;
			if(allSideCheck){//모든면을 체크했다면 청소할 곳이 없는 곳이므로
				//방향을 유지한채 후진
				int backR=0;
				int backC=0;
				switch (Robot.direction){//현재 방향에 따라
				case 0://북
					backR = Robot.row+dr[2];
					backC = Robot.col+dc[2];
					break;
				case 1://동
					backR = Robot.row+dr[3];
					backC = Robot.col+dc[3];
					break;
				case 2://남
					backR = Robot.row+dr[0];
					backC = Robot.col+dc[0];
					break;
				case 3://서
					backR = Robot.row+dr[1];
					backC = Robot.col+dc[1];
					break;
				}
				if(rangeCheck(backR,backC)){//영역 만족하고
					if(map[backR][backC]!=1) {//벽이아니면
						Robot.row = backR;
						Robot.col = backC;
						//Robot.direction = currentdir;//현재방향 유지
					}else break;//벽이면 작동종료
				}else break;//후진 불가능하면 작동 종료
			}
			
		}
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				System.out.print(map[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0&&nr<N && nc>=0&&nc<M) return true;
		return false;
	}
}
