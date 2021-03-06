package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
3 5
16661
65166
16661
 */
public class 수영장만들기 {
	static int R,C;
	static int[][] ground;
	static int[][] swimmingPool;
	static int maxHeight = 1;
	static int minHeight = 9;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][] visit;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean leak;
	static int answer;
	public static void main(String[] args) throws IOException {
		setData();
		bfsAll();
		getAnswer();
		System.out.println(answer);
	}
	private static void getAnswer() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				answer+=(swimmingPool[row][col]-ground[row][col]);
			}
		}
	}
	private static void bfsAll() {
		for(int height=maxHeight;height>minHeight;height--){
			for(int row=0;row<R;row++) {
				for(int col=0;col<C;col++) {
					if(height==swimmingPool[row][col] && ground[row][col]!=height){//확인대상
						q.clear();
						visit = new boolean[R][C];
						leak=false;
						insertQueue(row,col);
						bfs(height);
						if(leak){//영역밖 누수가 발생했으면 누수가 발생한 부분들에 대해 전부 -1
							leakWater();
						}
						if(insideLeakCheck(height)) leakWater();
					}
				}
			}
		}
		//view();
	}
	private static boolean insideLeakCheck(int height){
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(visit[row][col]){//채운부분에 대해서 
					for(int dir=0;dir<4;dir++) {
						int nr = row+dr[dir];
						int nc = col+dc[dir];
						if(rangeCheck(nr, nc)) {
							if(visit[nr][nc]==false && ground[nr][nc]<height) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	private static void leakWater() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(visit[row][col]) swimmingPool[row][col]-=1;
			}
		}
	}
	private static void bfs(int height) {
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentInfo = q.poll();
				int cr = currentInfo[0];
				int cc = currentInfo[1];
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(visit[nr][nc]==false && swimmingPool[nr][nc]==height && ground[nr][nc]!=height) {
							insertQueue(nr,nc);
						}
					}else{//영역 밖을 넘어가버리면 누수 true
						leak = true;
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void insertQueue(int row, int col) {
		q.add(new int[] {row,col});
		visit[row][col] = true;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		ground =  new int[R][C];
		swimmingPool = new int[R][C];
		for(int row=0;row<R;row++) {
			char[] line = br.readLine().toCharArray();
			for(int col=0;col<C;col++) {
				ground[row][col] = Character.getNumericValue(line[col]);
				if(ground[row][col]>maxHeight) maxHeight=ground[row][col];
				if(ground[row][col]<minHeight) minHeight=ground[row][col];
			}
		}
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				swimmingPool[row][col] = maxHeight;//물 다 채움
			}
		}
	}
}
