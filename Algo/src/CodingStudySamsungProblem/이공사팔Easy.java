package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 *  판을 기울여서 전체 블록을 이동시키는데
 *  같은 값을 갖는 두 블록이 충돌하면
 *  하나로 합쳐진다.
 *  한 번의 이동에서 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다.
 * 
 * 	최대 5번 이동시켜서 만들 수 있는 최대값?
 * 
 * 4가지 방향으로 구현할 수 있지만
 * 한가지 방향만 구현해서
 * 맵을 90도씩 돌려서 처리한다 
 * 
 * 
 * 왜안돼???! 맞는데왜틀려~~
 */
public class 이공사팔Easy {
	static int[][] board;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		dfs(board,0);
		System.out.println(max);
	}
	private static void dfs(int[][] board, int depth) {
		if(depth==5){
			//view(board);
			updateMax(board);
			return;
		}
		int[][] copyBoard = copy(board);//맵 복사 
		for(int dir=0;dir<4;dir++){
			copyBoard = up(copyBoard); //이동 
			dfs(copyBoard,depth+1);
			copyBoard = rotate(board);//복사된 맵의 4가지 버전 
		}
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
	private static int[][] copy(int[][] board) {
		int[][] copyBoard = new int[N][N];
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) copyBoard[row][col] = board[row][col];
		return copyBoard;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
	private static int[][] rotate(int[][] copyBoard){
		int[][] rotateMap = new int[N][N];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				rotateMap[row][col] = copyBoard[N-1-col][row]; 
			}
		} //90도 회전 
		return rotateMap;
	}
	private static void updateMax(int[][] board){
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(max<board[row][col]) max = board[row][col];
			}
		} //90도 회전 
	}
	private static int[][] up(int[][] copyBoard){
		int[][] temp = new int[N][N];
		int[][] copyMap = new int[N][N];
		for(int col=0;col<N;col++) {
			int flag = 0;
			int target = -1;
			for(int row=0;row<N;row++){
				if(copyBoard[row][col]==0) continue;//값이 없으면 그냥 건너뜀
				if(flag==1 && copyBoard[row][col]==temp[target][col]) {
					//이전값과 같으면 
					temp[target][col]*=2;
					flag=0;
				}else {//숫자가 있으면 발견한 좌표 저장  
					temp[++target][col] = copyBoard[row][col];
					flag = 1;//수가 있음 
				}
			}
		} //위로 다 올림
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) copyMap[row][col] = temp[row][col];

		return copyMap;
	}
}
