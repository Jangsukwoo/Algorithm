package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 8x8의 체스판이 있고
 * 킹의 위치가 주어짐
 * 알파벳은 열을 상징하고 숫자는 행을 상징함 
 * 가장 왼쪽열이 A, 오른쪽 열이 H
 * 행은 가장 아래가 1,위가 8
 * 이동 명령에서
 * R,L,B,T는 우,좌,하,상을 뜻함
 * 
 */
public class 킹 {
	static int[][] chessMap = new int[8][8];//1은 킹, 2는 돌 
	static int kingRow,kingCol;
	static int stoneRow,stoneCol;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};//12시부터 시계방향 
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String readfirstLine = br.readLine();
		StringTokenizer st = new StringTokenizer(readfirstLine);
		String coordination = st.nextToken();
		kingRow = 8-Character.getNumericValue(coordination.charAt(1));
		kingCol = (int)(coordination.charAt(0)-'A');
		chessMap[kingRow][kingCol] = 1;
		coordination = st.nextToken();
		stoneRow = 8-Character.getNumericValue(coordination.charAt(1));
		stoneCol = (int)(coordination.charAt(0)-'A');
		chessMap[stoneRow][stoneCol] = 2;
		int N = Integer.parseInt(st.nextToken());
		for(int i=0;i<N;i++){
			String command = br.readLine();
			move(command);
		}
		System.out.println((char)(kingCol+'A')+""+(8-kingRow));
		System.out.println((char)(stoneCol+'A')+""+(8-stoneRow));
	}
	private static void move(String command) {
		switch (command) {
		case "R":
			moveDirection(2);
			break;
		case "L":
			moveDirection(6);
			break;
		case "B":
			moveDirection(4);
			break;
		case "T":
			moveDirection(0);
			break;
		case "RT":
			moveDirection(1);
			break;
		case "LT":
			moveDirection(7);
			break;
		case "RB":
			moveDirection(3);
			break;
		case "LB":
			moveDirection(5);
			break;
		}
	}
	private static void moveDirection(int dir) {
		int nr = kingRow + dr[dir];
		int nc = kingCol + dc[dir];
		if(rangeCheck(nr,nc)){//영역 안넘고 
			if(isStone(nr,nc)) {//돌이 있으면 
				int stoneNR = stoneRow + dr[dir];
				int stoneNC = stoneCol + dc[dir];//돌의 방향을 정해주고
				if(rangeCheck(stoneNR, stoneNC)){//이동 가능하면
					chessMap[stoneRow][stoneCol] = 0;
					chessMap[kingRow][kingCol] = 0;
					chessMap[nr][nc] = 1;
					chessMap[stoneNR][stoneNC]=2;
					kingRow = nr;
					kingCol = nc;
					stoneRow = stoneNR;
					stoneCol = stoneNC;
				}else return; //돌이 영역 밖으로 이동 못하므로 return
			}else {//돌이 없으면
				chessMap[kingRow][kingCol] = 0;
				chessMap[nr][nc] = 1;
				kingRow = nr;
				kingCol = nc;
			}
		}
	}
	private static boolean isStone(int nr, int nc) {
		if(chessMap[nr][nc]==2) return true;
		return false;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<8 && nc>=0 && nc<8) return true;
		return false;
	}
}
