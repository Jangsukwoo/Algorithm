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
 * 19:22~.. 로테이션 코드는 잘 작동했는데 올리기 코드에서 문제가 많았고
 * 숫자 곱, 숫자 몰아주기 액션 두개로 나눠서 코드 만드니까 통과됐다

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


4
8 16 0 0
0 0 16 8
0 0 0 0
0 0 0 0
20:12

4
0 2 0 2
0 0 0 0
0 0 0 0
0 0 0 0

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
-> 128

4
0 2 0 2
0 0 0 0
0 0 0 0
0 0 0 0
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
		if(depth==5){
			updateAnswer(currentBorad);
			return;
		}
		int[][] copyBoard = copy(currentBorad);
		for(int rotate=0;rotate<4;rotate++){
			copyBoard = rotateBoard(copyBoard);
			int[][] reulstBoard = moveUp(copyBoard);
			dfs(depth+1,reulstBoard);
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
		//올리기 코드 다시!!!!!!!!!!!!!
		for(int col=0;col<N;col++){
			int currentNumber=0;
			int currentIdx=0;
			for(int row=0;row<=(N-1);row++){ //곱계산
				if(moveBoard[row][col]!=0 && moveBoard[row][col]!=currentNumber){
					currentNumber = moveBoard[row][col];
					currentIdx = row;
				}else if(moveBoard[row][col]!=0 && moveBoard[row][col]==currentNumber){
					moveBoard[row][col]=0;
					moveBoard[currentIdx][col] = currentNumber*2;
					currentNumber = 0;
				}
			}
			for(int row=0;row<N;row++) {//숫자 몰기 
				if(moveBoard[row][col]==0){
					int currentZeroIdx = row;
					int nextIdx = currentZeroIdx+1;
					while(nextIdx<N){
						if(moveBoard[nextIdx][col]!=0){
							moveBoard[currentZeroIdx][col] = moveBoard[nextIdx][col];
							moveBoard[nextIdx][col]=0;
							break;
						}else nextIdx++;
					}
				}
			}
		}


		return moveBoard;
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
