package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 5719번 거의최단경로
 * Step 1 . 다익스트라로 처음 노드와 간선에 대해 최단경로 정보를 세팅한다.
 * Step 2 . 도착 지점에서  BFS로 최단경로인 정점들의 vertax값을 전부 INF로 바꿔서 없애버린다.
 * Step 3 . 최단경로를 INF로 지워버린 그래프에 대해 다시 다익스트라를 수행하면 두번째 최단경로가 튀어나온다!
 * 
 * 구현 ~ING
 */
public class 그래프_2일차_거의최단경로 {
	static final int INF = 987654321;
	static int N,M;//vertax,edge
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] dist;
	static int[][] pathMatrix;
	static int startVertax,destinationVertax;
	public static void main(String[] args) throws IOException {
		input();
	}
	private static void input() throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pathMatrix = new int[N][N];
		
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(row!=col) pathMatrix[row][col] = INF;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		startVertax = Integer.parseInt(st.nextToken());
		destinationVertax = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N;i++) dist[i] = INF;
		dist[startVertax] = 0;
	}
}
