package SDS복습_그래프;

import java.util.ArrayList;
import java.util.Comparator;
/*
 * V : 6
 * E : 9
 */
import java.util.PriorityQueue;
public class 다익스트라짜보기 {
	static ArrayList<Edge>[] edgelist; //인접리스트
	static int[] dist;
	static boolean[] visit;
	static int INF = 987654321;
	static Comparator<int[]> cp = new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			return Integer.compare(o1[1],o2[1]);
		}
	};
	static PriorityQueue<int[]> pq = new PriorityQueue<int[]>(cp);
	static class Edge{
		int next;
		int cost;
		public Edge(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}

	}
	public static void main(String[] args) {
		edgelist = new ArrayList[7];
		dist = new int[7];
		for(int i=1;i<=6;i++) {
			edgelist[i] = new ArrayList<Edge>();
			dist[i] = INF;
		}

		edgelist[1].add(new Edge(2,10));
		edgelist[1].add(new Edge(3,2));

		edgelist[2].add(new Edge(1,10));
		edgelist[2].add(new Edge(4,6));
		edgelist[2].add(new Edge(5,5));

		edgelist[3].add(new Edge(1,2));
		edgelist[3].add(new Edge(4,7));
		edgelist[3].add(new Edge(6,2));

		edgelist[4].add(new Edge(2,6));
		edgelist[4].add(new Edge(3,7));
		edgelist[4].add(new Edge(6,1));
		edgelist[4].add(new Edge(5,12));

		edgelist[5].add(new Edge(2,5));
		edgelist[5].add(new Edge(4,12));
		edgelist[5].add(new Edge(6,15));

		edgelist[6].add(new Edge(5,15));
		edgelist[6].add(new Edge(4,1));
		edgelist[6].add(new Edge(3,2));

		dijstra();

		for(int i=1;i<=6;i++) {
			System.out.println("1에서 "+i+"번째로의 최단거리는");
			System.out.println(dist[i]);
		}
	}
	private static void dijstra(){

		pq.add(new int[] {1,0});
		dist[1] = 0;//시작

		while(!pq.isEmpty()){
			int[] current = pq.poll();
			int currentVertaxNumber = current[0];
			int cost = current[1];

			for(int i=0;i<edgelist[currentVertaxNumber].size();i++){
				Edge nextNode = edgelist[currentVertaxNumber].get(i);
				int distance=0;
				if(dist[nextNode.next]>cost+nextNode.cost) {
					dist[nextNode.next] = cost+nextNode.cost;//현재까지 온 값 + 해당 노드로가는 비용
					distance = Math.min(dist[nextNode.next],nextNode.cost+cost); //기존값 vs 지금 거리 거쳐서 가는 값
					dist[nextNode.next] = distance;//update
					pq.add(new int[] {nextNode.next,distance});//pq에 삽입
				}
			}
		}
	}
}

