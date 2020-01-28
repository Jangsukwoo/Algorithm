package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 가장 많이 먹을 수 있는 디저트의 종류.
 * 직진하는 경우, 꺾는 경우 

1
4
9 8 9 8
4 6 9 4
8 7 7 8
4 5 3 5

1
5
8 2 9 6 6
1 9 3 3 4
8 2 3 3 6
4 3 4 4 9
7 4 6 3 5
 */
public class 디저트카페 {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] cafe;
	static int[] dr = {1,1,-1,-1};
	static int[] dc = {-1,1,1,-1};//좌하, 우하, 우상, 좌상 
	static int startRow, startCol;
	static int maxDist;
	static boolean[] eat;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			bruteForce();
			if(maxDist==0) System.out.println("#"+testcase+" "+"-1");
			else System.out.println("#"+testcase+" "+maxDist);
		}
	}
	private static void bruteForce() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++){
				startRow = row;
				startCol = col;
				int nr = row+dr[0];
				int nc = col+dc[0];
				//영역 만족하면
				if(rangeCheck(nr,nc)) {	
					if(cafe[row][col]!=cafe[nr][nc]){
						eat[cafe[row][col]] = true;
						eat[cafe[nr][nc]] = true; //먹은걸로 체크 
						dfs(nr,nc,0,1);							
						eat[cafe[row][col]] = false;
						eat[cafe[nr][nc]] = false;
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc <N) return true;
		return false;
	}
	private static void dfs(int currentRow, int currentCol, int direction, int depth){
		//현재 좌표, 깊이, 간 거리, 왼쪽아래로 움직인 거리 
		if(currentRow == startRow && currentCol == startCol){
			maxDist = Math.max(depth, maxDist);
			return;
		}
		
		else if(direction==3){//복귀하는 턴이면  꺾지말고 직진 
			int nr = currentRow+dr[direction];
			int nc = currentCol+dc[direction];//직진하는 경우 
			if(rangeCheck(nr, nc)){
				if(eat[cafe[nr][nc]]==false){
					eat[cafe[nr][nc]]= true;
					dfs(nr,nc,direction,depth+1);
					eat[cafe[nr][nc]] = false;
				}else if(nr==startRow && nc==startCol){//쭉 직진하다가 스타드지점 만나면 먹었든 안먹었든 그냥 감 
					dfs(nr,nc,direction,depth+1);
				}
			}
		}
		else{//직진하는 경우랑 꺾는 경우 
	
			//직진하는 경우 
			int nr = currentRow+dr[direction];
			int nc = currentCol+dc[direction];
			if(rangeCheck(nr, nc)){
				if(eat[cafe[nr][nc]]==false){//안먹은 곳이면
					eat[cafe[nr][nc]]= true;
					dfs(nr,nc,direction,depth+1);
					eat[cafe[nr][nc]] = false;
				}
			}
			
			//꺾는 경우
			nr = currentRow+dr[direction+1];
			nc = currentCol+dc[direction+1];
			
			if(rangeCheck(nr, nc)){
				if(eat[cafe[nr][nc]]==false){//안먹은 곳이면
					eat[cafe[nr][nc]]= true;
					dfs(nr,nc,direction+1,depth+1);
					eat[cafe[nr][nc]] = false;
				}else if(nr==startRow && nc==startCol){//꺾는데 바로 스타트지점이면 먹었든 안먹었든 그냥 감 
					dfs(nr,nc,direction+1,depth+1);
				}
			}
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		cafe = new int[N][N];
		eat = new boolean[101];
		maxDist = 0;
		for(int row=0;row<N;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++){
				cafe[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}

