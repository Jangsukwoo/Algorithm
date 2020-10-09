package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 보급로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] area;
	static int[][] memo;
	static int answer;
	static PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			return Integer.compare(o1[2],o2[2]);
		}
	});
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			/*
			 * 복구 잡업이 가장 짧게 걸리는 경로 구하기 - 다익스트라로구하기
			 */
			dijkstar();
			System.out.println("#"+testcase+" "+memo[N-1][N-1]);
		}
	}
	private static void dijkstar() {
		memo[0][0] = 0;
		pq.add(new int[] {0,0,0});
		while(!pq.isEmpty()){
			int[] current = pq.poll();
			for(int dir=0;dir<4;dir++) {
				int nr = current[0]+dr[dir];
				int nc = current[1]+dc[dir];
				int currentdist = current[2];
				if(rangeCheck(nr,nc) && memo[nr][nc] > currentdist+area[nr][nc]){
					memo[nr][nc] = currentdist+area[nr][nc];
					pq.add(new int[] {nr,nc,(currentdist+area[nr][nc])});
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		area = new int[N][N];
		memo = new int[N][N];
		for(int row=0;row<N;row++) {
			String readline = br.readLine();
			for(int col=0;col<N;col++) {
				area[row][col] = Character.getNumericValue(readline.charAt(col));
				memo[row][col] = Integer.MAX_VALUE/2;
			}
		}
		answer = Integer.MAX_VALUE;
	}
}
