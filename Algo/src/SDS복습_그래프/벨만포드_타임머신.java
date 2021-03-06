package SDS복습_그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 벨만포드 알고리즘으로 음의 가중치가 있을 때 최단거리를 구하기는 힘들고
 * 음의 사이클의 존재 유무를 알 수 있게된다.
 * 
 * 간선의 값이 음수가 있을 때 
 * 음의 사이클이 있는 경우 최단거리가 무한히 갱신되므로
 * 
 * 최단거리를 구하는 알고리즘을 수행한 뒤
 * 딱 한번 더 돌렸을 때 어떤 최단거리 값이 갱신이 되어버린다면
 * 음의 사이클이 있다 라고 판단한다.
 * 
 * 즉 정점의 개수가 V이고
 * 모든 정점에대해 모든 간선을 대보면서 
 * 시간복잡도는 O(VE)
 * 
 * 일단 dist는 모르니까 전부 INF고
 * 1번 노드부터 출발하면서
 * 모든 정점에 대해서 Relaxation 수행 
 * 
 * 자동으로 Import하는 단축키 ctrl+shift+O
 * 
 */
public class 벨만포드_타임머신 {
	static final int INF = 987654321;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N,M;//도시의 개수, 버스 노선의 개수.
	static int[] dist;
	static boolean[] visit;
	static ArrayList<int[]> edgeList;//간선정보
	static boolean negativeCycle;
	public static void main(String[] args) throws IOException {
		setData();
		bellmanFord();
		getAnswer();
	}
	private static void getAnswer() {
		if(negativeCycle) System.out.println("-1");
		else {
			for(int vertax=2;vertax<=N;vertax++) {
				if(dist[vertax]==INF) System.out.println("-1");
				else System.out.println(dist[vertax]);
			}
		}
	}
	private static void bellmanFord() {
		for(int i=1;i<=N;i++){//Relaxation 간선경감?
			//정점이 추가될 때마다 모든 엣지에 대해서 조사. 즉 한 정점씩 알아가는 것 
			for (int j=0;j<M;j++){//간선 하나를 뽑아서 
				int[] currentEdge = edgeList.get(j);
				int from = currentEdge[0];
				int to = currentEdge[1];
				int cost = currentEdge[2];
				if(dist[from]!=INF && dist[to]>dist[from]+cost){//아는 거리면서 더 짧은 거리를 알았다면
					dist[to] = dist[from]+cost;//갱신 
					if(i==N) {
						//N번째 했을 때 갱신이 없어야하는데 갱신되서 이 조건문에 걸렸으면
						//음의 사이클이 존재하는것임
						negativeCycle = true;
						return ; //더 볼 필요 X
					}
				}
			}
		}
	}
	private static void setData() throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		visit = new boolean[N+1];
		edgeList = new ArrayList<>(); //간선리스트
		for(int i=1;i<=N;i++) dist[i] = INF;
		
		
		for(int i=0,from,to,cost;i<M;i++){//간선리스트. 인접리스트,인접행렬이 아님 
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			edgeList.add(new int[] {from,to,cost});
		}
		
		dist[1] = 0;
	}
}
