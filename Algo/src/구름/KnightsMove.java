package 구름;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class ChessPieces{
	int row,col,dist;
	ChessPieces(int r,int c,int d){
		row = r;
		col = c;
		dist = d;
	}
}
public class KnightsMove {
	static int rowSize,colSize;
	static int[][] chessBoard;
	static boolean[][] visit;
	static int[] dr = {-2,-1,1,2,2,1,-1,-2};
	static int[] dc = {1,2,2,1,-1,-2,-2,-1};//1시방향부터 회전
	static Queue<ChessPieces> q;
	static int distance;
	static boolean check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		rowSize = sc.nextInt();
		colSize = sc.nextInt();
		chessBoard = new int[rowSize][colSize];
		visit = new boolean[rowSize][colSize];
		q = new LinkedList<ChessPieces>();
		q.add(new ChessPieces(0,0,distance));
		
		while(!q.isEmpty()){
			distance++;
			int size = q.size();
			for(int i=0;i<size;i++) {
				ChessPieces cur = q.poll();
				visit[cur.row][cur.col]=true;
				for(int dir=0;dir<8;dir++){
					int nr = cur.row+dr[dir];
					int nc = cur.col+dc[dir];
					if(rangeCheck(nr,nc)){//영역만족
						if(visit[nr][nc]==false){
							q.add(new ChessPieces(nr,nc,distance));
						}
					}
				}
			}
		}
		for(int row=0;row<rowSize;row++) {
			for(int col=0;col<colSize;col++) {
				if(visit[row][col]==false){
					check = true;
					break;
				}
			}
		}
		if(check) System.out.println("F"+(distance-1));
		else System.out.println("T"+(distance-1));
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<rowSize && nc>=0 && nc<colSize) return true;
		return false;
	}
}
