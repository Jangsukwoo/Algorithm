package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 다익스트라로 구현하되 pq로 구현
 * 
 * 정점 개수 V, 간선개수 E
 * V<=2만
 * E<=30만
 * 
 * pq를 써서 ElogV로 푼다.
 * 
 * 출력해야할 것은 
 * i->j로 가는 모든 최단 경로 값 
 * 
 * 제일 빠른길만 그리디하게 찾아서 간다.
 */
public class 그래프_2일차_최단경로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int V,E;
	static int startVertax;
	static ArrayList<int[]>[] edgeList;//각 정점에 대해 

	static int[] dist;
	static boolean[] visit;
	static int INF = 987654321;
	public static void main(String[] args) throws IOException {
		inputData();
		dijkstra();
		bw.flush();
		bw.close();
	}

	private static void inputData() throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dist = new int[V+1];
		visit = new boolean[V+1];
		edgeList = new ArrayList[V+1]; //각 정점에 매딜린 자식과 비용 정보 
		
		for(int vertax=1;vertax<=V;vertax++) {
			dist[vertax]=INF;
			edgeList[vertax] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		startVertax = Integer.parseInt(st.nextToken());
	
		for(int i=0,from,to,cost;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			edgeList[from].add(new int[] {to,cost}); //cost기준으로 정렬할것임.
		}//edge정보 구성 
		

	}
	private static void dijkstra() throws IOException {
		PriorityQueue<int[]> pq= new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});//cost 기준 pq

		
		//경로 찾는 로직 ↓↓↓
		dist[startVertax] = 0;//시작 정점 자기 자신은 0 
		pq.add(new int[] {startVertax,0});//시작 정점과 비용
		while(!pq.isEmpty()) {
			int[] currentVerTax = pq.poll();//가장 처음 시작된다면 시작 정점이 반환된다. 즉, 자식들을 검사해야할 정점이 나오는 것 
			
			if(visit[currentVerTax[0]]==true) continue;//가봤으면 그냥 진행

			visit[currentVerTax[0]] = true;//검사해보지않은 정점에 대해서 
			
			for (int[] nextVertax : edgeList[currentVerTax[0]]){//현재 정점에 매달린 자식들을 볼텐데 
				if(visit[nextVertax[0]]==false){//안가본 곳이면
					//가는길을 알게되었는데
					//디플토 세팅값들은 전무 INF니까 비용갱신은 모두 하게됌.
					dist[nextVertax[0]] = Math.min(dist[nextVertax[0]], dist[currentVerTax[0]]+nextVertax[1]);//가려는 정점이 원래 알고있는것보다 현재에서 가는게 더 가까우면 최단경로값 업데이트
					pq.add(new int[] {nextVertax[0],dist[nextVertax[0]]});
				}
			}
		}
		//경로 찾는 로직 ↑↑↑
		
		//printDataSetting
		for(int node=1;node<=V;node++) {
			if(dist[node]==INF) bw.write("INF"+"\n");
			else bw.write(dist[node]+"\n");
		}
	}
}