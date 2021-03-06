package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 15:30~
 * 지뢰찾기
 * 
5
12321
2###2
3###3
2###2
12321

3
111
1#1
111

2
##
##

7
000001*
0#####1
1#####1
2#####1
3#####1
2#####0
1110000

17:30

숫자에 대해서 주변에 지뢰를 설치하는 방식으로 하니 계속 틀렸다.
지뢰가 설치 가능한 지점에서 인접한 숫자를 보고 0이 아니면 하나씩 까주는 방식으로 하니 통과되었다 흑
 */
public class 지뢰찾기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] board;
	static int N;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		setBomb();
		getAnswer();
		System.out.println(answer);
	}
	private static void getAnswer() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(board[row][col]=='#') answer++;
			}
		}
	}
	private static void setBomb() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(board[row][col]=='#'){
					boolean zero = false;
					boolean number = false;
					for(int dir=0;dir<8;dir++) {
						int nr = row+dr[dir];
						int nc = col+dc[dir];
						if(rangeCheck(nr, nc)){
							if(board[nr][nc]=='0'){//0이 하나라도 있는 자리면 그자리는 지뢰가 아님
								zero=true;
								break;
							}
							else if(board[nr][nc]>='1' && board[nr][nc]<='3') {
								number = true;
							}
						}
					}
					if(zero) board[row][col]=' ';
					else if(number){
						for(int dir=0;dir<8;dir++) {
							int nr = row+dr[dir];
							int nc = col+dc[dir];
							if(rangeCheck(nr, nc)){
								if(board[nr][nc]>='1' && board[nr][nc]<='3'){
									int ascii = board[nr][nc];
									board[nr][nc]= (char)(ascii-1);
								}
							}
						}
					}
				}
			}
		}
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		for(int row=0;row<N;row++) board[row] = br.readLine().toCharArray();		
	}
}
