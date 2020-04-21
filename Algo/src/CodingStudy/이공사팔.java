package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 18:13
 * 블록이 추가되는 경우는 없다.
 * 이동은 아래에서 위로 올리는것만 생각하고
 * 맵은 로테이트 시키면서 테스트하자.
 * 
 * 보드 크기 최대 20 by 20
 * 최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록??
 * 
 * 19:01 Pause
 * 
 * 19:22~

4
0 0 2 0 
0 0 0 0 
2 0 0 0 
0 0 0 0 

2
8 16
16 8

10
16 16 8 32 32 0 0 8 8 8
16 0 0 0 0 8 0 0 0 16
0 0 0 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0

7
2 2 2 2 2 2 2
2 0 2 2 2 2 2
2 0 2 2 2 2 2
2 0 2 2 2 2 2
2 2 2 0 2 2 2
2 2 2 2 2 2 0
2 2 2 2 2 2 0

10
8 8 4 16 32 0 0 8 8 8
8 8 4 0 0 8 0 0 0 0
16 0 0 16 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 16
0 0 0 0 0 0 0 0 0 2

20:12
 */
public class 이공사팔 {
	static int N;
	static int[][] originalGameBoard;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		dfs(0,originalGameBoard);
		System.out.println(answer);
	}
	private static void dfs(int depth, int[][] currentBorad){
		System.out.println("depth"+depth);
		System.out.println("받은 맵");
		view(currentBorad);
		if(depth==5){
			updateAnswer(currentBorad);
			return;
		}
		int[][] copyBoard = copy(currentBorad);
		for(int rotate=0;rotate<4;rotate++){
			int[][] reulstBoard = moveUp(copyBoard);
			dfs(depth+1,reulstBoard);
			copyBoard = rotateBoard(currentBorad);
		}
	}
	private static void updateAnswer(int[][] currentBorad) {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(currentBorad[row][col]>answer) answer=currentBorad[row][col];
			}
		}
	}
	private static int[][] moveUp(int[][] currentBorad){//위로 올린다.
		int[][] moveBoard = copy(currentBorad);
		System.out.println("합치기전");
		view(moveBoard);
		for(int col=0;col<N;col++){
			int currentNumber=0;
			int currentRow=0;
			boolean flag = false;
			for(int row=(N-1);row>=0;row--){
				if(moveBoard[row][col]==0) continue;
				else if(moveBoard[row][col]!=0 && flag==false) {
					currentNumber=moveBoard[row][col];
					currentRow= row;
					flag = true;
				}
				else if(moveBoard[row][col]==currentNumber && flag){
					moveBoard[row][col] = currentNumber*2;
					moveBoard[currentRow][col] = 0;
					flag = false;
				}else if(moveBoard[row][col]!=currentNumber&& flag){
					currentNumber = moveBoard[row][col];
					flag = false;
				}
			}
			for(int row=0;row<N;row++) {//숫자 몰아주기
				if(moveBoard[row][col]==0){
					int cr = row;
					while(true){
						cr+=1;
						if(cr<N) {
							if(moveBoard[cr][col]!=0) {
								moveBoard[row][col] = moveBoard[cr][col];
								moveBoard[cr][col] = 0;
								break;
							}
						}else break;
					}			
				}
			}
		}
		System.out.println("합치고 올린 후 ");
		view(moveBoard);
		//		System.out.println("합침");
		//		view(moveBoard);
		//		for(int col=0;col<N;col++) {
		//			for(int row=0;row<N;row++) {
		//				if(moveBoard[row][col]==0){
		//					int cr = row;
		//					System.out.println("cr값"+cr);
		//					while(true){
		//						cr+=1;
		//						if(cr<N) {
		//							if(moveBoard[cr][col]!=0) {
		//								moveBoard[row][col] = moveBoard[cr][col];
		//								moveBoard[cr][col] = 0;
		//								break;
		//							}
		//						}else break;
		//					}			
		//				}
		//
		//			}
		//		}
		//		System.out.println("올림");
		//		view(moveBoard);
//		System.out.println("합치고올림");
//		view(moveBoard);
		return moveBoard;
	}
	private static void view(int[][] board) {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(board[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static int[][] rotateBoard(int[][] board){
		int[][] rotateBoard = copy(board);
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				rotateBoard[row][col] = board[N-1-col][row];
			}
		}
		return rotateBoard;
	}
	private static int[][] copy(int[][] currentBorad){
		int[][] copyBoard = new int[N][N];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				copyBoard[row][col]=currentBorad[row][col];
			}
		}
		return copyBoard;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		originalGameBoard = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				originalGameBoard[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
