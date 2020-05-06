package 구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 17:00~~
 * N by M
 * J : 정글
 * O : 바다
 * I : 얼음
 * DP?
 * 
 * 2차원 구간 합 문제
 * 
 * 구현문제인줄알았는데 dp였다..
 * 인터넷이랑 예전에 동적계획법 푼거 참고해서 풀었다.
 */

public class 행성탐사 {
	static int R,C,K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static char[][] map;
	static Planet planet;
	static class Planet{
		int[][] jungle;
		int[][] sea;
		int[][] ice;
		public Planet(int[][] jungle, int[][] sea, int[][] ice) {
			this.jungle = jungle;
			this.sea = sea;
			this.ice = ice;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		memoization();
		query();
		bw.flush();
		bw.close();
	}
	private static void query() throws IOException {
		for(int query=1,sr,sc,er,ec;query<=K;query++){
			st = new StringTokenizer(br.readLine());
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			er = Integer.parseInt(st.nextToken());
			ec = Integer.parseInt(st.nextToken());
			int jungleCnt = planet.jungle[er][ec] - planet.jungle[sr-1][ec] - planet.jungle[er][sc-1] + planet.jungle[sr-1][sc-1];
			int seaCnt = planet.sea[er][ec] - planet.sea[sr-1][ec] - planet.sea[er][sc-1] + planet.sea[sr-1][sc-1];
			int iceCnt = planet.ice[er][ec] - planet.ice[sr-1][ec] - planet.ice[er][sc-1] + planet.ice[sr-1][sc-1];
			bw.write(jungleCnt+" "+seaCnt+" "+iceCnt+"\n");
		}
	}
	private static void memoization() {
		for(int row=1;row<=R;row++) {
			for(int col=1;col<=C;col++) {
				planet.jungle[row][col] += planet.jungle[row][col-1];
				planet.sea[row][col] += planet.sea[row][col-1];
				planet.ice[row][col] += planet.ice[row][col-1];
			}
		}
		for(int row=1;row<=R;row++) {
			for(int col=1;col<=C;col++) {
				planet.jungle[row][col] += planet.jungle[row-1][col];
				planet.sea[row][col] += planet.sea[row-1][col];
				planet.ice[row][col] += planet.ice[row-1][col];
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R+1][C+1];
		planet = new Planet(new int[R+1][C+1],new int[R+1][C+1],new int[R+1][C+1]);
		K = Integer.parseInt(br.readLine());
		for(int row=1;row<=R;row++){
			String read = br.readLine();
			for(int col=1;col<=C;col++) {
				map[row][col] = read.charAt(col-1);
				switch (map[row][col]){
				case 'J':
					planet.jungle[row][col]++;
					break;
				case 'O':
					planet.sea[row][col]++;
					break;
				case 'I':
					planet.ice[row][col]++;
					break;
				}
			}
		}
		
	}
}
