package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 등산로는 높이가 가장 높은곳에서 낮은곳으로 흐름
 * 높이가 같은곳과 대각선은 갈 수 없다.
 * 가장 높은곳에서 시작한다.
 * 
 * 딱 한곳을 정해서 최대 1~5만큼 깎을 수 있음.
1
5 1
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
 */
public class 등산로조성 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static int N,K;
	static int[][] map;
	static int[][] testingMap;
	static int maxLength;
	static ArrayList<int[]> startList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			bruteForce();
			bw.write("#"+testcase+" "+maxLength+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void bruteForce() {
		for(int i=0,sr,sc;i<startList.size();i++){//모든 봉우리에 대해서 
			int[] src = startList.get(i);
			sr = src[0];
			sc = src[1];
			for(int k=0;k<=K;k++){//깎아본다.				
				for(int row=0;row<N;row++) {
					for(int col=0;col<N;col++){
						if(map[row][col]>=k){
								copyMap();
								testingMap[row][col]-=k;
								dfs(sr,sc,1);
						}
					}
				}
			}
		}
	}
	private static void dfs(int currentRow, int currentCol, int depth){
		for(int dir=0;dir<4;dir++){
			int nr = currentRow+dr[dir];
			int nc = currentCol+dc[dir];
			if(rangeCheck(nr,nc)) {
				if(testingMap[currentRow][currentCol]>testingMap[nr][nc]){
					dfs(nr,nc,depth+1);
				}
			}
		}
		maxLength=Math.max(maxLength,depth);//위에 갈 곳이 없었다면 max값 갱신
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc <N) return true;
		return false;
	}
	private static void copyMap() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				testingMap[row][col] = map[row][col];
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		testingMap = new int[N][N];
		startList = new ArrayList<int[]>();
		int max=0;
		maxLength=0;
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++){
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col]>max) max = map[row][col];
			}
		}
		for(int row=0;row<N;row++){
			for(int col=0;col<N;col++) {
				if(map[row][col]==max) startList.add(new int[] {row,col});
			}
		}
	}
}
