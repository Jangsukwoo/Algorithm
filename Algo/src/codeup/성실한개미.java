package codeup;

import java.util.Scanner;

public class 성실한개미 {
	static int[] dr = {0,1};
	static int[] dc = {1,0};//0: 우 , 1: 하
	static int[][] maze = new int[11][11];//10 by 10
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int row=1;row<=10;row++) {
			for(int col=1;col<=10;col++) {
				maze[row][col] = sc.nextInt();
			}
		}
		
		antMove();
		
		printMaze();
	}
	private static void printMaze() {
		for(int row=1;row<=10;row++) {
			for(int col=1;col<=10;col++) {
				if(col<10) System.out.print(maze[row][col]+" ");
				else System.out.print(maze[row][col]);
			}
			System.out.println();
		}
	}
	private static void antMove() {
		int currentRow = 2;
		int currentCol = 2; //초기위치
		if(maze[2][2]==2) {
			maze[2][2] = 9;
			return ;
		}
		else maze[2][2] = 9;
		int nextRow = currentRow;
		int nextCol = currentCol;
		while(true){//언제 끝날지 모르니 이동 시작
			//먹이를 찾거나 이동경로가 없을때 멈춘다.
			if(rangeCheck(nextRow+dr[0],nextCol+dc[0])){//오른쪽으로 갈수 있는 경로면 간다.
				nextRow += dr[0];
				nextCol += dc[0];//우
				if(maze[nextRow][nextCol]==2) {
					maze[nextRow][nextCol]=9;
					return; //먹이면 함수 끝내버림
				}
				else maze[nextRow][nextCol] = 9;//그냥 땅이면 9라고 쓴다.
			}else if(rangeCheck(nextRow+dr[1],nextCol+dc[1])){//오른쪽으로 못가는 경우면 아래쪽으로 간다.
				nextRow += dr[1];
				nextCol += dc[1];//하
				if(maze[nextRow][nextCol]==2) {
					maze[nextRow][nextCol]=9;
					return; //먹이면 함수 끝내버림
				}
				else maze[nextRow][nextCol] = 9;//그냥 땅이면 9라고 쓴다.
			}else {//우,하 둘다 못가면 끝내버린다.
				return;
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=1 && nr<=10 && nc>=1 && nc<=10){//10 by 10 영역 내에 있으면서
			if(maze[nr][nc]==0 || maze[nr][nc]==2){//0이거나 먹이면 true
				return true;
			}
		}
		return false;
	}
}
