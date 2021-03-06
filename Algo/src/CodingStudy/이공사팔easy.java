package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이공사팔easy {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] firstBoard;
	static int N;
	static int maxValue;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		dfs(0,firstBoard);
		System.out.println(maxValue);
	}
	private static void dfs(int depth, int[][] beforeBoard){
		if(depth==5) {
			getMaxValue(beforeBoard);
			return;
		}
		int[][] copyBoard = copy(beforeBoard);
		for(int i=0;i<4;i++){//현재보드에서 올리고 3번 돌려서 올리기. 세번만 돌려된다.
			int[][] currentBoard = up(copyBoard);
			dfs(depth+1,currentBoard);
			copyBoard = rotate(copyBoard);
		}
	}
	private static void getMaxValue(int[][] board) {
		
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(board[row][col]>maxValue) maxValue = board[row][col];
			}
		}
	}
	private static int[][] rotate(int[][] board) {
		int[][] rotateBoard = copy(board);
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				rotateBoard[row][col] = board[N-1-col][row];
			}
		}
		return rotateBoard;
	}
	private static int[][] up(int[][] board) {
		int[][] currentBoard = copy(board);
		for(int col=0;col<N;col++){
			int number = 0;
			int rowIdx = 0;
			
			//곱계산
			for(int row=0;row<N;row++) {
				if(currentBoard[row][col]!=0) {
					if(currentBoard[row][col]==number) { 
					currentBoard[row][col] = 0;
					currentBoard[rowIdx][col] *=2;
					number = 0;
					}
					else if(currentBoard[row][col]!=number) {
						//알고있는 수랑 같지 않으면 갱신필요
						number = currentBoard[row][col];
						rowIdx = row;
					}
				}
			}
			
			//올리기처리
			for(int row=0;row<N;row++){
				if(currentBoard[row][col]==0) {
					int zeroIdx =row;
					int nextIdx = zeroIdx+1;
					while(nextIdx<N) {
						if(currentBoard[nextIdx][col]!=0){//0이 아닌 숫자 만나면  swap
							currentBoard[zeroIdx][col] = currentBoard[nextIdx][col];
							currentBoard[nextIdx][col]=0;
							break;//올렸으니 끝냄
						}else nextIdx++;
					}
				}
			}
		}
		return currentBoard;
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
		firstBoard = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				firstBoard[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
