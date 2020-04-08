package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 15:22~ 
 * Dijkstra Algorithm
 * pq를 활용해서 시작점부터 하나씩 확인하면서 구한다.
 * 모든 거리는 처음에 INF이고 시작노드부터 시작.
 * 이때 인접리스트를 쓴다.
 * 
 * 양방향이라고 생각하고
 * 인접리스트를 묶어주면서 from,to로 바인딩 해줬는데
 * 단방향 그래프 문제였다.
 * 
 * 16:00
 * 
 *
 * 
 */
public class 최소비용구하기 {
	static int N; //1<=N<=1000
	static int M;
	static int startNode;
	static int endNode;
	static int minDist;
	static int[] dist;
	static int INF = 987654321;
	static PriorityQueue<int[]> pq;
	static ArrayList<int[]>[] adjList; //인접리스트
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		setData();
		dijkstra();
		System.out.println(dist[endNode]);
	}
	private static void dijkstra(){
		
		dist[startNode] = 0;
		pq.add(new int[] {startNode,0});
		
		while(!pq.isEmpty()){
			
			int[] currentInfomation = pq.poll();
			
			int currentNodeNumber = currentInfomation[0];
			int currentCost = currentInfomation[1];
			
			for(int i=0;i<adjList[currentNodeNumber].size();i++){
				
				int[] nextInformation = adjList[currentNodeNumber].get(i); 
				
				int nextNodeNumber = nextInformation[0];
				int nextCost = nextInformation[1];
				
				if(dist[nextNodeNumber]>(currentCost+nextCost)){
					
					dist[nextNodeNumber] = currentCost+nextCost;
					pq.add(new int[] {nextNodeNumber,(currentCost+nextCost)});
					
				}
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N+1];
		adjList = new ArrayList[N+1];
		pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1[1],o2[1]);
			}
		});
		for(int i=1;i<=N;i++) {
			adjList[i] = new ArrayList<int[]>();
			dist[i] = INF; //초기화
		}
		for(int i=0,from,to,cost;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			adjList[from].add(new int[] {to,cost});
		}
		st = new StringTokenizer(br.readLine());
		startNode = Integer.parseInt(st.nextToken());
		endNode = Integer.parseInt(st.nextToken());
	}
}
