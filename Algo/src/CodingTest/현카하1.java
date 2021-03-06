package CodingTest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 현카하1 {
	public static void main(String[] args) {
		int[] answer = solution(4,3,new int[][] {{1,1,2,4,3},{3,2,1,2,3},{4,1,1,4,3},{2,2,1,3,3}});
		System.out.println(Arrays.toString(answer));
	}
	public static int[] solution(int rows, int columns, int[][] swipes) {

		int[] dr = {0,1,-1,0,0};
		int[] dc = {0,0,0,1,-1};
	
		int[][] board = getBoard(rows,columns);

		int swipeSize = swipes.length;

		int[] answer = new int[swipeSize];
		
		int answerIdx = 0;
		
		Queue<Integer> q = new LinkedList<Integer>();

		for(int swipe = 0;swipe<swipeSize;swipe++) {

			int dir = swipes[swipe][0];
			int ltr = swipes[swipe][1];
			int ltc = swipes[swipe][2];
			int rbr = swipes[swipe][3];
			int rbc = swipes[swipe][4];
			
			int[][] newBoard = new int[rows+1][columns+1];			
			
			for(int row = ltr; row<= rbr; row++) {
				for(int col=ltc;col<=rbc ;col++) {
					int nr = row+dr[dir];
					int nc = col+dc[dir]; //다음칸
					if(rangeCheck(nr,nc,ltr,ltc,rbr,rbc)) {//영역안에있으면
						newBoard[nr][nc] = board[row][col];
					}else {//영역 벗어나면
						if(nr>rbr) newBoard[ltr][col] = board[row][col];
						else if(nr<ltr) newBoard[rbr][col] = board[row][col];
						else if(nc>rbc)  newBoard[row][ltc] = board[row][col];
						else if(nc<ltc)  newBoard[row][rbc] = board[row][col];
						q.add(board[row][col]);
					}
				}
			}
			
			int sum = 0;
			
	
			for(int row=1;row<=rows;row++) {
				for(int col=1;col<=columns;col++) {
					if(newBoard[row][col]==0) newBoard[row][col] = board[row][col];
				}
			}
			
			board = copyBoad(newBoard,rows,columns);
			
			while(!q.isEmpty()) {
				sum+=q.poll();
			}
			
			answer[answerIdx] = sum;
			
			answerIdx++;
			
		}

		return answer;
	}
	private static int[][] copyBoad(int[][] newBoard, int R, int C) {
		int[][] board = new int[R+1][C+1];
		for(int row=1;row<=R;row++) {
			for(int col=1;col<=C;col++) {
				board[row][col] = newBoard[row][col];
			}
		}
		return board;
	}
	private static boolean rangeCheck(int nr, int nc, int ltr, int ltc, int rbr, int rbc) {
		if(nr>=ltr && nr<=rbr && nc>=ltc && nc<=rbc) return true;//주어진 영역내에있으면
		return false;
	}
	private static int[][] getBoard(int R, int C) {
		int[][] board = new int[R+1][C+1];
		int num = 1;
		for(int row=1;row<=R;row++) {
			for(int col=1;col<=C;col++) {
				board[row][col] = num;
				num++;
			}
		}
		return board;
	}
}
