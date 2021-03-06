package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 벨만포드 알고리즘 (생각보다 많이 안나온다)
 * -> 음의 사이클 존재 여부를 확인 할 수 있는 알고리즘이다.
 * 
 * 시작 도시는 1번 
 */
public class 그래프_2일차_타임머신 {
	static final int INF = 987654321;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N,M;//도시의 개수, 버스 노선의 개수.
	static int[] dist;
	static boolean[] visit;
	static ArrayList<int[]> edgeList;//간선정보
	static boolean negativeCycle;
	public static void main(String[] args) throws IOException {
		inputData();
		bellmanFord();
		if(negativeCycle) System.out.println("-1");
		else {
			for(int vertax=2;vertax<=N;vertax++) {
				if(dist[vertax]==INF) System.out.println("-1");
				else System.out.println(dist[vertax]);
			}
		}
		
	}
	private static void inputData() throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		visit = new boolean[N+1];
		edgeList = new ArrayList<>();
		for(int i=1;i<=N;i++) {
			dist[i] = INF;
		}
		
		for(int i=0,from,to,cost;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			edgeList.add(new int[] {from,to,cost});
		}
		
		dist[1] = 0;
	}
	private static void bellmanFord() {
		//음의 사이클 판정을 어떻게할까?
		for(int i=1;i<=N;i++){//음수 사이클을 판정하기 위해 N번째도 수행한다고함 
			for (int j=0;j<M;j++){
				int[] currentEdge = edgeList.get(j);
				int from = currentEdge[0];
				int to = currentEdge[1];
				int cost = currentEdge[2];
				if(dist[from]!=INF && dist[to]>dist[from]+cost) {
					dist[to] = dist[from]+cost;
					if(i==N) {
						//N-1번까지 했을 때 최단거리여야 하는데 한번 더했는데 갱신되서 이 조건문에 걸렸으면
						//음의 사이클이 존재하는것임
						negativeCycle = true;
					
					}
				}
			}
		}
	}
}
