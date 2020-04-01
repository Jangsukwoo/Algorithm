package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 *  모든 집을 방문 할 수 있는 최소의 길을 깐다.
 *  Mst 만들기
 *  
 *  집 2<=N<=100000
 *  길 1<=M<=1000000
 *  
 */
public class 도시분할계획 {
	static int N;
	static int M;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int min;
	static StringTokenizer st;
	static Edge[] edgeList;
	static int[] parent;
	static int lastHouseCost;
	static class Edge{
		int from;
		int to;
		int cost;
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		kruscalAlgorithm();
		System.out.println(min-lastHouseCost);
	}
	private static void kruscalAlgorithm() {
		Arrays.sort(edgeList, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.cost,o2.cost);
			}
		});//정렬 후 
		for(int i=0;i<edgeList.length;i++){
			if(find(edgeList[i].from)!=find(edgeList[i].to)){//부모가 같지 않으면 union 해줄 필요가 있음
				union(edgeList[i].from,edgeList[i].to);
				//System.out.println(edgeList[i].from+" "+edgeList[i].to+"연결"+" 가중치는"+edgeList[i].cost);
				min+=edgeList[i].cost;
				lastHouseCost=edgeList[i].cost;
			}
		}
	}
	private static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		parent[bParent] = aParent;
	}
	private static int find(int child){
		if(child==parent[child]) return parent[child];
		return parent[child] = find(parent[child]);
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		edgeList = new Edge[M];
		for(int i=0,from,to,cost;i<M;i++){
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, cost);
		}
		
		for(int i=1;i<=N;i++) parent[i] = i;
	}
}
