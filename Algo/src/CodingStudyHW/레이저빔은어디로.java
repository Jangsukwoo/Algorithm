package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 레이저빔은어디로 {
	static int T,n,r;
	static int[][] board;
	static boolean[][][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int laserR,laserC;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean escape;
	static int escapeR,escapeC;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			escape = true;
			setData();
			simulation();
			if(escape) System.out.println(laserR+" "+laserC);
			else System.out.println("0 0");
		}
		
	}
	private static void simulation() {
		int dir = 0;
		if(laserC==0) dir = 1;
		else if(laserC==n+1) dir=3;
		else if(laserR==0) dir = 2;
		else if(laserR==n+1) dir = 0;
		while(true){
			laserR+=dr[dir];
			laserC+=dc[dir];
			if(laserR==n+1 || laserR==0 || laserC==n+1 || laserC==0) break;//도착
			else{
				if(visit[laserR][laserC][dir]==false){
					visit[laserR][laserC][dir]=true;
					if(board[laserR][laserC]==1) dir= (dir+1)%4; //방향꺾기
					else continue;
				}else {
					escape=false;
					return;
				}
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		board = new int[n+2][n+2];
		visit = new boolean[n+2][n+2][4];
		for(int mirror=0,row,col;mirror<r;mirror++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			board[row][col] = 1;//거울
		}
		st = new StringTokenizer(br.readLine());
		laserR = Integer.parseInt(st.nextToken());
		laserC = Integer.parseInt(st.nextToken());
	}
}
