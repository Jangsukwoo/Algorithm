package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 제일 크게 지나갈 수 있는 다리만 지나가야함
 * 가능한 모든 중량에서 중량을 하나 선택하고
 * bfs로 탐색이 가능한지 검증
 * 중량의 범위는 1부터 10억이니 이분탐색으로 정한다.
 */
public class 중량제한 {
	static int N,M;
	static int maxWeight;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean[] visit;
	static ArrayList<int[]>[] adjList;
	static int start,end;
	static int answer = 0;
	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		setData();
		binarySearch();
		System.out.println(answer);
	}
	private static void binarySearch() {
		int lowWeight = 1;
		int heightWeight = maxWeight;
		boolean possible = false;
		while(lowWeight<=heightWeight){
			int mid = (lowWeight+heightWeight)/2;
			possible = false;
			possible = bfs(mid);
			if(possible) lowWeight = mid+1;//가능하면 중량을 올려본다.
			else heightWeight = mid-1; //불가능하면 중량을 낮춰본다.
		}//가능한 중량을 전부 조사
		answer = heightWeight;
	}
	private static boolean bfs(int mid){
		queueInitialization();
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int currentNodeNumber = q.poll();
				if(currentNodeNumber==end) return true;//도착 노드면 끝냄
				for(int j=0;j<adjList[currentNodeNumber].size();j++) {
					int[] nextNode = adjList[currentNodeNumber].get(j);
					int nextNodeNumber = nextNode[0];
					int nextNodeCost = nextNode[1];
					if(nextNodeCost>=mid && visit[nextNodeNumber]==false) insertQueue(nextNodeNumber);
				}
			}
		}
		return false;
	}
	private static void queueInitialization() {
		visit = new boolean[N+1];
		q.clear();
		insertQueue(start);
	}
	private static void insertQueue(int nodeNumber) {
		q.add(nodeNumber);
		visit[nodeNumber] = true;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N+1];
		for(int island=1;island<=N;++island) adjList[island] = new ArrayList<int[]>();
		for(int i=0,from,to,cost;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			adjList[from].add(new int[] {to,cost});
			adjList[to].add(new int[] {from,cost});
			maxWeight= Math.max(maxWeight,cost);
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
	}
}
