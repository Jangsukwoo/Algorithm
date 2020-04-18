package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * bfs-> 큐 메모리초과로 실패
 * dfs-> 시간초과로 실패
 * bfs , dfs + memo -> 어떤 방식으로 메모이제이션할지 몰랐다.
 * 
 * dp-> 성공
 * memo[현재위치][현재위치][길이]로 해서 경우의수를 누적.  
 * BREAK라면
 * B를 찾아서 길이 0에 경우의수 1 씀
 * B를 통해 갈 수 있는 R에 대한 경우의 수들을 조사하면서
 * B가 가지고 있는 경우의 수를 더함
 * 즉, memo[R위치][R위치][1번째] += memo[B위치][B위치][0번째] 
 * (직전 경우의수에서 다음 경우의 수로 누적되는 방식)
 * 
 * 반복작업을 통해 K위치에 도달했으면
 * path값 구한 후 종료
 * 
 * 
 *
 * 
 */
public class 문자판 {
	static int N,M,K;
	static char[][] letterBoard;
	static int[][][] memo;
	static Queue<int[]> q;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static String target;
	static int length;
	static int targetLength;
	static int path;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException{
		setData();
		memoization();
		System.out.println(path);
	}
	private static void memoization() {
		//step1 . dp 초기화
		initailize();
		//step2 . memo
		for(int letter=0;letter<(targetLength-1);letter++){
			for(int row=0;row<N;row++) {
				for(int col=0;col<M;col++) {
					if(target.charAt(letter)==letterBoard[row][col]){
						int cr = row;
						int cc = col;
						for(int k=1;k<=K;k++){
							for(int dir=0;dir<4;dir++) {
								int nr= cr+dr[dir]*k;
								int nc= cc+dc[dir]*k;
								if(rangeCheck(nr,nc)){//영역 만족
									if(letterBoard[nr][nc]==target.charAt(letter+1)){//같은 글자면
										memo[nr][nc][letter+1]+=memo[cr][cc][letter];
										if((letter+1)==targetLength-1) {
											path+=memo[cr][cc][letter];
										}
									}
								}
							}
						}
					}
				}
			}
			
		}
	}
	private static void initailize() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				if(letterBoard[row][col]==target.charAt(0)) {
					memo[row][col][0] = 1;
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		letterBoard = new char[N][M];
		for(int row=0;row<N;row++) {
			letterBoard[row] = br.readLine().toCharArray();
		}
		target = br.readLine();
		targetLength = target.length();
		memo = new int[N][M][target.length()+1];
	}
}
