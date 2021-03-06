package SDS복습_그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
MST (최소신장트리)
모든 정점을 연결하면서 가장 최소의 비용이 드는 트리 (사이클이 없다)

MST를 만드는 로직은 두가지가 있다.
1.프림 , 2.크루스칼
이 두개는 시간복잡도의 차이가 있어서
주어진 문제의 간선의 개수, 정점의 개수에 따라 상황에 맞게 더 효율적인 알고리즘을 골라서 써주면 된다.

이 문제는 크루스칼로 푼다.

Kruscal Algorithm?
모든 엣지들을 가장 낮은 비용으로 정렬해둔 뒤
유니온 파인드로 묶어준다.
엣지를 M개만큼 연결하면 된다.

크루스칼 안보고 구현해보기

 */
public class 최소신장트리_크루스칼_네트워크연결 {
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
	static StringTokenizer st;
	static int N; //1<=N<=1000 <- 컴퓨터수
	static int M; //1<=M<=100000 <- 연결할 수 있는 선의 수
	static Edge[] edges;//간선들 
	static int[] parent;
	static long answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		kruscal();
	}
	private static void kruscal() {
		//Step 1. 가장 값싼 순으로 엣지를 확인하기 위해 오름차순 정렬
		Arrays.sort(edges);
		
		//Step 2.Union - Find로 묶어준다.
		for(int i=0;i<M;i++){ //정렬된 모든 간선을 앞에서부터 보면서  묶어준다
			if(find(edges[i].from)!=find(edges[i].to)){//부모가 같지 않으면 묶어준다.
				union(edges[i].from,edges[i].to);
				answer+=edges[i].cost;
			}
		}
		
		System.out.println(answer);
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
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		edges = new Edge[M];
		parent = new int[N+1];
		for(int i=1;i<=N;i++) parent[i] = i;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(from, to, cost); //엣지들
		}
	}
}
