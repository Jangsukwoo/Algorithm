package SDS_WEEK1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 네트워크연결
 * 사이클이 생기면 포기 
 * 
 * Kruskal
 */
public class 그래프_1일차_네트워크연결 {
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int cost;
		public Edge(int f, int t, int c){
			from = f;
			to = t;
			cost = c;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost,o.cost); //비용기준 정렬 
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N; //1<=N<=1000 <- 컴퓨터수
	static int M; //1<=M<=100000 <- 연결할 수 있는 선의 수
	static Edge[] edges;
	static int[] parent;
	static long answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
	}
	private static void input() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		edges = new Edge[M];
		parent = new int[N+1];
		for(int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(from, to, cost); //엣지들
		}
		kruscal();
		System.out.println(answer);
		
	}
	private static void kruscal() {
		for(int vertax=1;vertax<=N;vertax++) parent[vertax]=vertax; 
		Arrays.sort(edges);//엣지들을 가장 낮은 비용순으로 정렬 
		
		for(int i=0;i<M;i++) {
			if(find(edges[i].from)!=find(edges[i].to)) {//부모가 같지 않다?
				union(edges[i].from,edges[i].to); //연결 
				answer+=edges[i].cost;
			}
		}
		
	}
	private static void union(int from, int to) {
		int fromParent = find(from);
		int toParent = find(to);
		parent[toParent] = fromParent;
		return;
	}
	private static int find(int node) {
		if(parent[node]==node) return node;
		return parent[node] = find(parent[node]);
	}
	
}
