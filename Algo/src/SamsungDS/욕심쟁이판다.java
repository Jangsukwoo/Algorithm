package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 18:00 ~ 19:47
 */
public class 욕심쟁이판다 {
	static int[][] memo; //dp 배열 
	static int[][] forest;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		dfsAll();
		getAnswer();
	}
	private static void getAnswer() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				answer = Math.max(memo[row][col],answer);
			}
		}
		System.out.println(answer);
	}
	private static void dfsAll() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(memo[row][col]==0) dfs(row,col);
			}
		}
	}
	private static int dfs(int cr, int cc) {
		
		memo[cr][cc]=1; //1일은 무조건
		
		boolean end = true; //끝에 도달했는가
		
		int maxDay = 1; //현재 칸에서 최장 생존일
		int getDay = 0; //현재 칸에서 어떤 방향으로 진행했을 때 얻은 생존일
		for(int dir=0;dir<4;dir++) { //현재 칸에서 가능한 방향 조사
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(rangeCheck(nr,nc)) { //영역 체크
				int currentTree = forest[cr][cc]; //현재 칸의 대나무량
				int nextTree = forest[nr][nc]; //다음 칸의 대나무량
				if(nextTree>currentTree) { //대나무가 더 많다면 갈 수 있는 칸
					end = false; //현재 칸이 끝칸은 아님
					if(memo[nr][nc]>0) getDay = memo[cr][cc]+memo[nr][nc]; //메모되어있으면
					else getDay = memo[cr][cc]+dfs(nr,nc); //메모 안되어있으면 가봐야함
					maxDay = Math.max(getDay,maxDay); //얻은 생존기간 중 최대 기간
				}
			}
		}
		if(end) return 1; //끝에 도달한거면 여기서부터는 1
		memo[cr][cc] = Math.max(memo[cr][cc], maxDay); //도달 안된 중간칸이므로 현재 칸에서 최대 생존량 갱신
		return memo[cr][cc]; //갱신된 현재 칸의 최대 생존기간
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		forest = new int[N][N];
		memo = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				forest[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
