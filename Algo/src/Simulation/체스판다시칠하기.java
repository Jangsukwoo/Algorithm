package Simulation;

import java.util.Scanner;
/*
 * 체스판 샘플 A,B 만들어서 비교함
 */
public class 체스판다시칠하기 {
	static int N,M;//R,C size
	static char[][] chessBoard;
	static char[][] sampleBoardA;
	static char[][] sampleBoardB;
	static int minSquareValue = Integer.MAX_VALUE;
	static int tmpMinA;
	static int tmpMinB;
	static int tmpMin;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		chessBoard = new char[N][M];
		sampleBoardA = new char[8][8];
		sampleBoardB = new char[8][8];
		makeSampleBoard();
		sc.nextLine();
		for(int row=0;row<N;row++) {
			String readLine = sc.nextLine();
			chessBoard[row] = readLine.toCharArray();
		}
		for(int row=0;row<(N-7);row++) {
			for(int col=0;col<(M-7);col++){
				sliceBoardCheck(row,col);
				minSquareValue = Math.min(minSquareValue,tmpMin);
			}
		}
		System.out.println(minSquareValue);
	}
	private static void makeSampleBoard(){
		for(int row=0;row<8;row++){
			if(row%2==0) {//짝수 행
				for(int col=0;col<8;col+=2){
					sampleBoardA[row][col]='W';
					sampleBoardB[row][col]='B';
				}
				for(int col=1;col<8;col+=2){
					sampleBoardA[row][col]='B';
					sampleBoardB[row][col]='W';
				}
			}
			if(row%2!=0) { //홀수 행
				for(int col=0;col<8;col+=2){
					sampleBoardA[row][col]='B';
					sampleBoardB[row][col]='W';
				}
				for(int col=1;col<8;col+=2){
					sampleBoardA[row][col]='W';
					sampleBoardB[row][col]='B';
				}
			}
		}
	}
	private static void sliceBoardCheck(int sr, int sc){
		tmpMinA = tmpMinB = tmpMin = 0;
		int r=0,c=0;
		for(int row=sr;row<(sr+8);row++){
			c=0;
			for(int col=sc;col<(sc+8);col++){
				if(chessBoard[row][col]!=sampleBoardA[r][c]) tmpMinA++;
				if(chessBoard[row][col]!=sampleBoardB[r][c]) tmpMinB++;
				c++;
			}
			r++;
		}
		tmpMin = Math.min(tmpMinA,tmpMinB);
	}
}
