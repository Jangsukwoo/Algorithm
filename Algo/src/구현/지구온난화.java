package 구현;

import java.util.Scanner;

public class 지구온난화 {
	static int R,C;
	static char[][] map;
	static boolean[][] isSinkIsland;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	/*
	 * 인접한 세칸 or 네칸에바다가 있는땅은 잠김
	 */
	public static void main(String[] args) {
		Scanner sc = new  Scanner(System.in);
		R = sc.nextInt();
		C= sc.nextInt();
		map= new char[R][C];
		isSinkIsland = new boolean[R][C];
		sc.nextLine();
		
		for(int row=0;row<R;row++) map[row] = sc.nextLine().toCharArray();
		
		getAfter50YearsMap();
	}
	private static void getAfter50YearsMap() {
		
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(map[row][col]=='X') { //섬이면 조사해봄
					int adjCount = 0;
					for(int dir=0;dir<4;dir++) {
						int nr = row+dr[dir];
						int nc = col+dc[dir];
						if(isSea(nr,nc)) adjCount++;
					}
					if(adjCount>=3) isSinkIsland[row][col] = true;//3개 이상이면 가라앉을 것임
				}
			}
		}
		
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(isSinkIsland[row][col]) map[row][col] ='.';//가라앉아서 바다됌
			}
		}
		
		int leftTopRow=11;
		int leftTopCol=11;
		int rightBottomRow=0;
		int rightBottomCol=0;
		
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(map[row][col]=='X') {
					leftTopRow = Math.min(row,leftTopRow);
					leftTopCol = Math.min(col,leftTopCol);
					rightBottomRow = Math.max(row,rightBottomRow);
					rightBottomCol = Math.max(col,rightBottomCol);
				}
			}
		}
		for(int row=leftTopRow;row<=rightBottomRow;row++) {
			for(int col=leftTopCol;col<=rightBottomCol;col++) {
				System.out.print(map[row][col]);
			}
			System.out.println();
		}
	}
	private static boolean isSea(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C){//영역 안에 있는데
			if(map[nr][nc]=='.') {//바다면 true
				return true;
			}
		}else return true;//영역 밖이면 바다므로 
		return false;//둘다 아닌경우
	}
}
