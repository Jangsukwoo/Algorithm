package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 체스 {
	static int n,m;
	static char[][] chessMap;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Queen[] queens;
	static Knight[] knights;
	static Pawn[] pawns;
	static int safeCnt;
	static class Queen{
		int row;
		int col;
		public Queen(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	static class Knight{
		int row;
		int col;
		public Knight(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	static class Pawn{
		int row;
		int col;
		public Pawn(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	//8방향
	static int[] queendr = {-1,-1,0,1,1,1,0,-1};
	static int[] queendc = {0,1,1,1,0,-1,-1,-1};
	static int[] knightdr = {-2,-1,1,2,2,1,-1,-2};
	static int[] knightdc = {1,2,2,1,-1,-2,-2,-1};

	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		System.out.println(safeCnt);
	}
	private static void simulation() {
		int cr = 0;
		int cc = 0;
		int nr = 0;
		int nc = 0;
		//Queen
		for(int i=0;i<queens.length;i++) {
			cr = queens[i].row;
			cc = queens[i].col;
			for(int dir=0;dir<8;dir++) {
				nr = cr+queendr[dir];
				nc = cc+queendc[dir];
				while(true){
					if(check(nr,nc)){
						chessMap[nr][nc] = 'N';
						nr += queendr[dir];
						nc += queendc[dir];
					}
					else break;
				}
			}
		}

		for(int i=0;i<knights.length;i++) {
			cr = knights[i].row;
			cc = knights[i].col;
			for(int dir=0;dir<8;dir++) {
				nr = cr+knightdr[dir];
				nc = cc+knightdc[dir];
				if(check(nr,nc)){
					chessMap[nr][nc] = 'N';
					nr += knightdr[dir];
					nc += knightdc[dir];
				}

			}
		}

		for(int row=1;row<=n;row++) {
			for(int col=1;col<=m;col++) {
				if(chessMap[row][col]=='S') safeCnt++;
			}
		}
	}
	private static boolean check(int nr, int nc) {
		if(nr>=1 && nr<=n && nc>=1 && nc<=m) {
			if(chessMap[nr][nc]=='S' ||  chessMap[nr][nc]=='N') return true;
		}
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		chessMap = new char[n+1][m+1];
		for(int row=1;row<=n;row++) {
			for(int col=1;col<=m;col++) {
				chessMap[row][col] = 'S';
			}
		}
		st = new StringTokenizer(br.readLine());
		int queenCnt = Integer.parseInt(st.nextToken());
		queens = new Queen[queenCnt];
		for(int i=0,row,col;i<queenCnt;i++) {
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			queens[i] = new Queen(row, col);
			chessMap[row][col] = 'Q';
		}

		st = new StringTokenizer(br.readLine());
		int knightCnt = Integer.parseInt(st.nextToken());
		knights = new Knight[knightCnt];
		for(int i=0,row,col;i<knightCnt;i++) {
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			knights[i] = new Knight(row, col);
			chessMap[row][col] = 'K';
		}

		st = new StringTokenizer(br.readLine());
		int pawnCnt = Integer.parseInt(st.nextToken());
		pawns = new Pawn[pawnCnt];
		for(int i=0,row,col;i<pawnCnt;i++) {
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			pawns[i] = new Pawn(row, col);
			chessMap[row][col] = 'P';
		}
	}
}
