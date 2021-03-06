package SDS복습_그래프;

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
 * 다익스트라 안보고 구현하기
 * 
 * 특정 지점에서 다른 목적 지점까지 가는 최소비용으로 가는 최단경로를 찾는다.
 * 특정 정점에서 모든 정점까지 최단거리만을 아는 것임.
 * 
 * 특정 정점을 pq에 넣은 후 연결된 엣지들중 가장 비용이 싼 다음 정점이 나올 수 있도록
 * pq를 쓴다.
 * 
 * ElogV
 * 
 * 
 */
public class 다익스트라_최단경로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int V,E;
	static int startVertax;
	static ArrayList<int[]>[] edgeList;//각 정점에 대해 
	static int[] dist;
	static boolean[] visit;
	static int INF = 987654321;
	public static void main(String[] args) throws IOException {
		setData();
		dijkstra();
		getAnswer();
	}
	private static void getAnswer() throws IOException {
		for(int i=1;i<=V;i++) {
			if(dist[i]==INF) bw.write("INF"+"\n");
			else bw.write(dist[i]+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void dijkstra() {
		PriorityQueue<int[]> pq= new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});//cost 기준 pq
		dist[startVertax] = 0;
		pq.add(new int[] {startVertax,0});//시작정점

		while(!pq.isEmpty()){//한 정점씩 알아가면서 제일 가까운 거리로만감 -> logV
			int[] currentVertaxInfo = pq.poll();
			int currentVertaxNumber = currentVertaxInfo[0];
			if(visit[currentVertaxNumber]==false){
				visit[currentVertaxNumber] = true; //이제 이 정점에 대한 최단거리는 안다.
				for(int[] nextVertaxInfo : edgeList[currentVertaxNumber]){//매달려있는 정점들에 대해 - E
					dist[nextVertaxInfo[0]] = Math.min(dist[nextVertaxInfo[0]],dist[currentVertaxNumber]+nextVertaxInfo[1]);
					//알고있는 거리보다 지금 거리에서 가는게 더 짧을 때 
					pq.add(new int[] {nextVertaxInfo[0],dist[nextVertaxInfo[0]]});//지금까지 온 거리?
				}
			}
		} // O(ElogV)
	}
	private static void setData() throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dist = new int[V+1];
		visit = new boolean[V+1];
		edgeList = new ArrayList[V+1]; //엣지리스트 

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
			edgeList[from].add(new int[] {to,cost}); //cost기준으로 정렬된다.
		}//edge정보 구성 


	}
}
