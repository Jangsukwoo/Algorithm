package SDS복습_그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 0,0에서 시작
 * N-1,N-1까지 이동해야한다 
 * 각 칸에 숫자만큼 소지금을 잃게 되는데 잃는 금액을 최소로 하여
 * 동굴 건너편까지 이동해야하고 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.
 * 
 */
public class 녹색옷입은애가젤다지 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] cave;
	static int[][] dist;
	static int programNumber=1;
	static int INF = 987654321;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static Comparator<int[]> cp = new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			return Integer.compare(o1[2],o2[2]);
		}
	};
	static PriorityQueue<int[]> pq;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		bw.flush();
		bw.close();
	}
	private static void dijkstra() throws IOException {
		while(!pq.isEmpty()){
			int[] current = pq.poll();
			int currentRow = current[0];
			int currentCol = current[1];
			int currentDist = current[2];
			for(int dir=0;dir<4;dir++){
				int nr =  currentRow+dr[dir];
				int nc = currentCol+dc[dir];
				if(rangCheck(nr,nc)){//범위 내에 있고 
					if(dist[nr][nc]>cave[nr][nc]+currentDist){
						dist[nr][nc] = cave[nr][nc]+currentDist;
						pq.add(new int[] {nr,nc,dist[nr][nc]});
					}
				}
			}
		}
		int answer = dist[N-1][N-1];
		bw.write("Problem "+programNumber+": "+answer+"\n");
		programNumber++;
	}
	private static boolean rangCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		while(true){
			N = Integer.parseInt(br.readLine());	
			pq = new PriorityQueue<int[]>(cp);
			cave = new int[N][N];
			dist = new int[N][N];
			if(N==0) return;
			for(int row=0;row<N;row++) {
				for(int col=0;col<N;col++){
					dist[row][col] = INF;
				}
			}
			for(int row=0;row<N;row++){
				st = new StringTokenizer(br.readLine());
				for(int col=0;col<N;col++){
					cave[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			dist[0][0]=0;
			pq.add(new int[] {0,0,cave[0][0]});
			dijkstra();
		}
	}
}
