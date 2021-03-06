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
 * !<=Vertax<=1000
 * 1<=Edge<=2백만
 * 1<=k번쨰<=100
 * 
 * 다익스트라 시간복잡도 = O(ElogV) or O(V^2)
 * 
 * 다익스트라의 응용이고
 * 
 * 다익스트라 구하면서 
 * 갱신할 때 값들을 버리면 안되고 따로 큐에 저장한다
 * 
 * step1. 일단 다익스트라 구현 
 * 
 * pq를 쓸려면
 * 1번부터 시작해서
 * 1번에 매달린 애들 pq에 넣고 가장 비용 작은에 튀어나오는 방식 
 * 
 * ↓ 답 안나오는 코드..
 */
public class 그래프_2일차_K번째최단경로찾기 {
	static final int INF = 987654321;
	static int N,M,K;
	static int[][] pathMatrix;
	static int[] dist;
	static PriorityQueue<int[]> pq;

	//문제를 위한 변수 선언
	static PriorityQueue<Integer>[] distQueue;
	public static void main(String[] args) throws IOException {
		input();
		dijkstra();
		output();
	}
	private static void output() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=1;i<=N;i++) {
			if(distQueue[i].size()==K){
				bw.write(distQueue[i].peek()+"\n");
			}else {
				bw.write("-1"+"\n");
			}
		}
		bw.flush();
		bw.close();
	}
	private static void dijkstra(){
		distQueue = new PriorityQueue[N+1];

		Comparator<Integer> mycompare = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return -Integer.compare(o1, o2);
			}
		};
		//K를 찾기 위한 부분
		for(int i=1;i<=N;i++) {
			distQueue[i] = new PriorityQueue<>(K,mycompare); //K는 왜넣지 
			//pq 생성자 첫번째 파라미터가 pq의 캐파를 뜻한다고함 ㄷㄷ
		}

		pq = new PriorityQueue<>(new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});


		pq.add(new int[] {1,0});//시작은 1, cost는 0
		distQueue[1].add(0);//1번의 비용 0 

		while(!pq.isEmpty()) {

			int[] currentVertax = pq.poll();
			int currentVertaxNum = currentVertax[0];
			int currentVertaxCost = currentVertax[1];

			for(int next=1;next<=N;next++){
				if(distQueue[next].size()<K) {//저장된 경로가 K보다 작을 떄
					distQueue[next].add(dist[currentVertaxNum]+currentVertaxCost);
					pq.add(new int[] {next, dist[currentVertaxNum]+currentVertaxCost});
				}//저장 된 경로가 K개면서 현재 가장 큰 값보다 작으면?
				else if(distQueue[next].peek()>dist[currentVertaxNum]+currentVertaxCost) {
					distQueue[next].poll();
					distQueue[next].add(dist[currentVertaxNum]+currentVertaxCost);
					pq.add(new int[] {next, dist[currentVertaxNum]+currentVertaxCost});
				}
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		pathMatrix = new int[N+1][N+1];
		dist = new int[N+1];
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++) {
				if(row!=col) pathMatrix[row][col] =INF;
			}
		}//인접행렬 세팅
		for(int i=0,from,to,cost;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			pathMatrix[from][to] = cost;
		}//인접행렬 만들기

	}
}
