package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 15:10~
 * 컴퓨터 대수 N (1<=N<=1000)
 * 
 * 컴퓨터는 직접 연결되어있거나 거쳐서 연결되어있는 경우가 있는데
 * 컴퓨터와 컴퓨터를 연결하는 회선의 성능은 각각 차이가 있다.
 * 
 * 복구할 회선의 개수 K
 * 
 * 16:50
 * 
 * 값이 갱신되는 최종 간선들만 기억하면되므로 
 * fromTo 배열을 통해 갱신되는 회선 정보들을 담고 출력..
 * 구글링해서 참고했다
4 5
1 2 1
1 3 2
1 4 3
2 3 1
3 4 1
 */
public class 네트워크복구 {
	static int N,M;//컴퓨터수, 연결정보수 
	static ArrayList<int[]>[] adjList;
	static int INF = 987654321;
	static int[] dist;
	static int[] fromTo;
	public static void main(String[] args) throws IOException {
		setData();
		dijkstra();
		System.out.println((N-1));
		for(int i=2;i<=N;i++) System.out.println(i+" "+fromTo[i]);
		
	}
	private static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});
		pq.add(new int[]{1,0});
		dist[1] = 0;
		while(!pq.isEmpty()){
			int[] currentInfomation = pq.poll();
			int currentVertax = currentInfomation[0];
			int currentCost = currentInfomation[1];
			for(int i=0;i<adjList[currentVertax].size();i++){
				int nextVertax = adjList[currentVertax].get(i)[0];
				int costToNextVertax = adjList[currentVertax].get(i)[1];
				if(dist[nextVertax]>(currentCost+costToNextVertax)){
					dist[nextVertax] = currentCost+costToNextVertax;
					pq.add(new int[] {nextVertax,dist[nextVertax]});
					fromTo[nextVertax] = currentVertax;//갱신되는 회선 
				}
			}
		}
	}
	private static void setData() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N+1];
		fromTo= new int[N+1];
		dist = new int[N+1];
		for(int i=1;i<=N;i++) adjList[i] = new ArrayList<int[]>();
		for(int i=1;i<=N;i++) dist[i] = INF;
		for(int i=0,from,to,cost;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			adjList[from].add(new int[] {to,cost});
			adjList[to].add(new int[] {from,cost});
		}
		
	}
}
