package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 미로탈출하기
 * 17:00 ~
 * 탈출 가능한 칸의 수 ?
 * dfs로 가면서 탈출 성공 하면 그 경로는 전부 가능하다고 메모 후 
 * 성공한 길에 대해서는 백트랙킹하기
 * 
 * 각 칸에서의 방향은 정해져있다.
 * 
 */
public class 미로탈출하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int R,C;
	static char[][] maze;
	static boolean[][] visit;
	static int[][] memo; //1: 갈 수 있는 경로, -1: 갈 수 없는 경로
	static int answer;
	public static void main(String[] args) throws IOException {
		setData();
		dfsAll();
		System.out.println(answer);
	}

	private static void dfsAll() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++){
				answer+=dfs(row,col);
			}
		}
	}

	private static int dfs(int cr, int cc) {
		if(rangeCheck(cr,cc)){//탈출 성공
			return 1;
		}
		if(memo[cr][cc]!=-1) return memo[cr][cc];
		memo[cr][cc] = 0; //0이라고 밟음
		int dir = getDirection(maze[cr][cc]);
		int nr = cr+dr[dir];
		int nc = cc+dc[dir];
		memo[cr][cc]= dfs(nr,nc);
		
		return memo[cr][cc];
	}

	private static int getDirection(char direction) {
		int dir=0;
		switch (direction){
		case 'U':
			dir=0;
			break;
		case 'R':
			dir=1;
			break;
		case 'D':
			dir=2;
			break;
		case 'L':
			dir=3;
			break;
		}
		return dir;
	}

	private static boolean rangeCheck(int cr, int cc) {
		if(cr<0 || cr>=R || cc<0 || cc>=C) return true;
		return false;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maze = new char [R][C];
		visit = new boolean[R][C];
		memo = new int[R][C];
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				memo[row][col]=-1;
			}
		}
		for(int row=0;row<R;row++) maze[row] = br.readLine().toCharArray();
	}
}
