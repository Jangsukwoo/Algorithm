package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 14:25~
 * N,N까지 이동시키는 방법의 수?
 * 빈칸은 0 ,벽은 1
 * 0,1에서 시작하고
 * 우, 우하, 하
 * 
 * 3방향으로 memo해야하는걸 몰랐다.
 * 
 * dfs+ dp 연습 더하자!! ㅠㅠ
 */
public class 파이프옮기기2{
	static int N;
	static int[][] house;
	static long[][][] memo;
	static long answer;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static class Pipe{
		int row;
		int col;
		char state;
		public Pipe(int row, int col, char state) {
			this.row = row;
			this.col = col;
			this.state = state;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		answer = dfs(new Pipe(0,1,'-'),0);
		System.out.println(answer);
	}
	private static long dfs(Pipe currentPipe, int dir){
		if(memo[currentPipe.row][currentPipe.col][dir]!=0){ //뭔가 숫자가 적혀있으면 그 땅에 적힌 값 리턴 
			return memo[currentPipe.row][currentPipe.col][dir];
		}
		if(currentPipe.row==(N-1) && currentPipe.col==(N-1)){
			return 1;
		}
		switch (currentPipe.state) {
		case '-':
			if(rightCheck(currentPipe)) {
				memo[currentPipe.row][currentPipe.col][dir] +=dfs(new Pipe(currentPipe.row,currentPipe.col+1,'-'),0);
			}
			if(rightDownCheck(currentPipe)) {
				memo[currentPipe.row][currentPipe.col][dir] +=dfs(new Pipe(currentPipe.row+1,currentPipe.col+1,'/'),1);
			}
			break;
		case '|':
			if(downCheck(currentPipe)) {
				memo[currentPipe.row][currentPipe.col][dir] +=dfs(new Pipe(currentPipe.row+1,currentPipe.col,'|'),2);
			}
			if(rightDownCheck(currentPipe)) {
				memo[currentPipe.row][currentPipe.col][dir] +=dfs(new Pipe(currentPipe.row+1,currentPipe.col+1,'/'),1);
			}
			break;
		case '/':
			if(rightCheck(currentPipe)) {
				memo[currentPipe.row][currentPipe.col][dir] +=dfs(new Pipe(currentPipe.row,currentPipe.col+1,'-'),0);
			}
			if(rightDownCheck(currentPipe)) {
				memo[currentPipe.row][currentPipe.col][dir] +=dfs(new Pipe(currentPipe.row+1,currentPipe.col+1,'/'),1);
			}
			if(downCheck(currentPipe)) {
				memo[currentPipe.row][currentPipe.col][dir] +=dfs(new Pipe(currentPipe.row+1,currentPipe.col,'|'),2);
			}
			break;
		}
		return memo[currentPipe.row][currentPipe.col][dir];
	}
	private static boolean downCheck(Pipe currentPipe){
		int cr = currentPipe.row;
		int cc = currentPipe.col;
		if(rangeCheck(cr+1,cc)) return true;
		return false;
	}
	private static boolean rightDownCheck(Pipe currentPipe){
		int cr = currentPipe.row;
		int cc = currentPipe.col;
		if(rangeCheck(cr+1,cc) && rangeCheck(cr,cc+1) && rangeCheck(cr+1,cc+1)) return true;
		return false;
	}
	private static boolean rightCheck(Pipe currentPipe) {
		int cr = currentPipe.row;
		int cc = currentPipe.col;
		if(rangeCheck(cr,cc+1)) return true;
		return false;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) {
			if(house[nr][nc]!=1) {
				return true;
			}
		}
		return false;
	}
	private static void setData() throws IOException {
		N = Integer.parseInt(br.readLine());
		house = new int[N][N];
		memo = new long[N][N][3];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				house[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
