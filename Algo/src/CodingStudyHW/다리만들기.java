package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 18:25 ~
 * 섬과 섬을 잇는 다리는 딱 하나만 건설할 것임
 * 건설되는 다리중 가장 잛은 다리의 길이는?
 * 
 * 각 섬 bfs로 넘버링해주고
 * 각 자리마다 그냥 bfs로 전부 보면 될거같다.
 * 19:10 끝
 * 
 */
public class 다리만들기 {
	static int N;
	static int[][] map;
	static int shortPathLength = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		markingIslandNumber();//각 섬에 번호매기기
		getBestShortPath();
		System.out.println(shortPathLength-1);
	}
	private static void getBestShortPath() {
		int currentIslandNumber=0;
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visit;
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++){ //모든 자리에대해서 검사
				if(map[row][col]!=0){
					currentIslandNumber= map[row][col];
					visit = new boolean[N][N];
					q.clear();
					q.add(new int[] {row,col});
					visit[row][col] = true;
					boolean find=false;
					int path=0;
					while(!q.isEmpty()){
						path++;
						int size = q.size();
						for(int i=0;i<size;i++) {
							int[] curRC = q.poll();
							int cr = curRC[0];
							int cc = curRC[1];
							for(int dir=0;dir<4;dir++){
								int nr = cr+dr[dir];
								int nc = cc+dc[dir];
								if(rangeCheck(nr, nc) && visit[nr][nc]==false && map[nr][nc]!=currentIslandNumber){
									//일단 영역만족, 방문안함, 같은 섬 번호가 아니고
									if(map[nr][nc]!=0) {
										//0이 아니면 다른 섬을 만난 것임 bfs로 했으니 
										//현재 검사하는 자리에서 제일 가까운 섬을 만난 것이므로 여기서 더 보지않는다.
										shortPathLength = Math.min(shortPathLength,path);
										find = true;
										break;
									}else if(map[nr][nc]==0){
										q.add(new int[] {nr,nc});
										visit[nr][nc] = true;
									}
								}
							}
							if(find) break;
						}
						if(find) break;
					}
				}
			}
		}
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(map[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void markingIslandNumber() {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visit = new boolean[N][N];
		int islandNumber=1;
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(visit[row][col]==false && map[row][col]==1){//방문안한 섬이면
					q.add(new int[] {row,col});
					visit[row][col]=true;
					map[row][col] = islandNumber;
					while(!q.isEmpty()){
						int size = q.size();
						for(int i=0;i<size;i++){
							int[] curRC = q.poll();
							int cr = curRC[0];
							int cc = curRC[1];
							for(int dir=0;dir<4;dir++){
								int nr = cr+dr[dir];
								int nc = cc+dc[dir];
								if(rangeCheck(nr,nc) && visit[nr][nc]==false && map[nr][nc]==1){
									q.add(new int[]{nr,nc});
									visit[nr][nc] = true;
									map[nr][nc] = islandNumber;
								}
							}
						}
					}
					islandNumber++;
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
		map = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
