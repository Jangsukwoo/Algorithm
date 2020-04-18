package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 
 */
public class 문자판 {
	static int N,M,K;
	static char[][] letterBoard;
	static boolean[][][] memo;
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
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				if(target.charAt(0)==letterBoard[row][col]) {
//					q.clear();
//					q.add(new int[] {row,col});
					//bfs();
					memo[row][col][1]=true;
					dfs(row,col,1);
					
				}
			}
		}
//		for(int row=0;row<N;row++) {
//			for(int col=0;col<M;col++) {
//				System.out.print(memo[row][col]);
//			}
//			System.out.println();
//		}
		System.out.println(path);
	}
	private static void dfs(int cr, int cc, int depth){
		if(depth==target.length()) {
			path++;
			return;
		}
		for(int k=1;k<=K;k++){
			for(int dir=0;dir<4;dir++) {
				int nr= cr+dr[dir]*k;
				int nc= cc+dc[dir]*k;
				if(rangeCheck(nr,nc)){//영역 만족
					if(letterBoard[nr][nc]==target.charAt(depth)){//같은 글자면
						dfs(nr,nc,depth+1);
					}
				}
			}
		}
	}
	private static void bfs(){
		int letterPointer = 1;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRC = q.poll();
				int cr = curRC[0];
				int cc = curRC[1];
				if(letterPointer==target.length()){//target 길이와 같으면
					path++;
					continue;
				}
				for(int k=1;k<=K;k++){
					for(int dir=0;dir<4;dir++) {
						int nr= cr+dr[dir]*k;
						int nc= cc+dc[dir]*k;
						if(rangeCheck(nr,nc)){//영역 만족
							if(letterBoard[nr][nc]==target.charAt(letterPointer)){//같은 글자면
								q.add(new int[] {nr,nc});
							}
						}
					}
				}
			}
			letterPointer++;
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
		memo = new boolean[N][M][target.length()+1];
//		q = new LinkedList<int[]>();
	}
}
