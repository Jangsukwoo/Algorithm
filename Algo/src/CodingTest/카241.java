package CodingTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 카241 {
	static int M,N;
	static StringTokenizer st;
	static char[][] map;
	static int[][] bombConutMap;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws Exception {
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[M][N];
		bombConutMap = new int[M][N];
		for(int row=0;row<M;row++) map[row] = br.readLine().toCharArray();
		
		
		for(int row=0;row<M;row++) {
			for(int col=0;col<N;col++) {
				int bombCount = 0;
				if(map[row][col]=='.') { //지뢰가 아닌 땅인 경우
					for(int dir=0;dir<8;dir++) { //주변 8방향 확인
						int nr = row+dr[dir];
						int nc = col+dc[dir];
						if(rangeCheck(nr,nc) && map[nr][nc]=='*') { //영역 내에 있고 지뢰면 카운트
							bombCount++;
						}
					}
					map[row][col] = Integer.toString(bombCount).charAt(0);
				}
			}
		}
		
		for(int row=0;row<M;row++) {
			for(int col=0;col<N;col++) {
				bw.write(map[row][col]);
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<M && nc>=0 && nc<N) return true;
		return false;
	}
}
