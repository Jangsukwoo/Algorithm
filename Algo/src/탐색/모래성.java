package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 모래성 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] sandCatsleHeight;
	static Queue<int[]> q = new LinkedList<int[]>();
	static int R,C;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int waveRound;
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		System.out.println(waveRound-1);
	}
	private static void bfs() {
		bfsInitailization();
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentWave = q.poll();
				int cr = currentWave[0];
				int cc = currentWave[1];
				for(int dir=0;dir<8;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(sandCatsleHeight[nr][nc]>0){//성이면
							sandCatsleHeight[nr][nc]--;
							if(sandCatsleHeight[nr][nc]==0) q.add(new int[] {nr,nc});
						}
					}
				}
			}
			waveRound++;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void bfsInitailization() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(sandCatsleHeight[row][col]==0) {
					q.add(new int[] {row,col});
				}
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sandCatsleHeight = new int[R][C];
		for(int row=0;row<R;row++) {
			String readLine = br.readLine();
			for(int col=0;col<C;col++){
				if(readLine.charAt(col)=='.') sandCatsleHeight[row][col]=0;
				else sandCatsleHeight[row][col] = Character.getNumericValue(readLine.charAt(col));
			}
		}
	}
}
