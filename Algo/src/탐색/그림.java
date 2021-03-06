package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 그림 {
	static int N,M;
	static int[][] paper;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int picture;
	static int pictureSize;
	static int maxPictureSize;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		paper = new int[N][M];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				paper[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				if(paper[row][col]==1) {
					pictureSize=1;
					picture++;
					paper[row][col]=0;
					dfs(row,col);
					//System.out.println(pictureSize);
					maxPictureSize = Math.max(pictureSize,maxPictureSize);
				}
			}
		}
		System.out.println(picture);
		System.out.println(maxPictureSize);
	}
	private static void dfs(int cr, int cc) {
		for(int dir=0;dir<4;dir++) {
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(rangeCheck(nr,nc) && paper[nr][nc]==1) {
				paper[nr][nc] = 0;
				pictureSize++;
				dfs(nr,nc);
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
}
