package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 21:45~
 *
 * 예쁘게 짜보자
 * 
 * 10:16 
 */
public class 로봇청소기 {
	static int R,C;
	static int[][] room;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1}; //북동남서
	static int robotR,robotC,robotDirection;
	static int clean=1;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		//입력
		setData();
		//로직
		simulation();
		//출력
		System.out.println(clean);
	}
	private static void simulation(){
		while(true){
			room[robotR][robotC] = 2;//현위치 청소 
			boolean needBack = true;
			int nextDir = robotDirection-1;
			for(int dir=0;dir<4;dir++){//4방향 쳐다보기
				if(nextDir<0) nextDir=3;
				int nr = robotR+dr[nextDir];
				int nc = robotC+dc[nextDir];
				if(rangeCheck(nr,nc) && room[nr][nc]==0){//영역만족하고 청소 안한곳이면
					clean++; //청소했다.
					robotR=nr;
					robotC=nc;
					robotDirection=nextDir;
					needBack = false;//후진할 필요 X
					break;
				}else nextDir-=1;//왼쪽으로 
			}
			if(needBack){//후진 필요 
				//방향 유지한채 그대로 후진만 한다.
				int backRow = robotR+dr[(robotDirection+2)%4];
				int backCol = robotC+dc[(robotDirection+2)%4];
				if(rangeCheck(backRow,backCol) && room[backRow][backCol]!=1) {
					robotR=backRow;
					robotC=backCol;
				}else break;//아니면 끝냄
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		robotR = Integer.parseInt(st.nextToken());
		robotC = Integer.parseInt(st.nextToken());
		robotDirection = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		for(int row=0;row<R;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<C;col++) {
				room[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
