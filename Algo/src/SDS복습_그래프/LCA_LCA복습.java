package SDS복습_그래프;
/*
 * LCA 안보고 구현하기
 * 2^k에서 k를 결정하는 것은
 * 정점의 최대 개수를 넘어서는 k값
 * 
 * 트리가 주어지고 
 * 어떤 두 정점에 대한 공통 조상 찾기
 * 
 * 안보고 구현하되 최대한 깔끔하게 짜보자.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class LCA_LCA복습 {
	//필요한 데이터
	static ArrayList<Integer>[] adjList;//인접리스트
	static int[][] sparesTable;//조상정보 table
	static int[] depth;//트리에서 각 정점이 가지는 깊이
	static int N;//총 node 수 
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/*
	 * bufferedReader를 지역으로 
	 * new BufferedReader(new InputStreamReader(System.in));
	 * 라고 두개 선언하면 인풋을 제대로 받지 못하는 문제가 있었다..
	 * 왜그렇지?
	 */
	public static void main(String[] args) throws IOException {
		setData();
		setSparesTableAndDepth();
		makeSparesTable();
		excuteQuery();
	}
	
	
	private static void excuteQuery() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		for(int query=0,a,b;query<M;query++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			int lca = findLCA(a,b);
			System.out.println(lca);
		}
	}

	private static int findLCA(int a, int b) {
		if(depth[a]>depth[b]){//깊이 일관성있게 맞추기
			int tmp = a;
			a = b;
			b = tmp;
		}//항상 b가 더 깊도록.
		
		//깊이 맞추기
		for(int k=17;k>=0;k--){//깊이 맞추기
			if(depth[a]<=depth[sparesTable[k][b]]){//계속 더 깊거나 같은경우
				b = sparesTable[k][b];//같아졌을때 비로소 b를 할당
				//같아진 후부턴느 어차피 이 조건에 걸려서 스킵된다.
			}
		}
		//같은 깊이에 위치 시킨 후 부모를 조사
		for(int k=17;k>=0;k--) {
			if(sparesTable[k][a]!=sparesTable[k][b]){//만약 두 부모가 같지 않으면
				a = sparesTable[k][a];
				b = sparesTable[k][b];
			}
		}
		//부모가 같아졌을 시점에 이 for문은 끝나게되어있음 
		
		int lca=b;
		if(a!=b) lca = sparesTable[0][a];
		//같지 않으면 두 자식이 같은 부모를 가리키고 있는 것이고
		//같다면 찍은 두 정점중에 한 정점이 이미 더 깊은곳의 부모인 것이니 lca는 a라고 한다.
		//처음에 b가 더 깊은 곳으로 일관성있게 바꿔줬으므로 
		//아래에서 치고 올라온 데이터는 b이기 때문에 당연히 a가 더 높고 부모이자 조상이다.
		//그러나 어차피 a==b이므로 lca= a를 해주든 lca=b라고 해주든 상관은없다.
		return lca;
	}


	//BFS 탐색으로  바로 위 sparesTable 첫번째 줄, 각 노드의 바로 윗 부모 정보 세팅하기
	private static void setSparesTableAndDepth() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);//루트 삽입
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int parent = q.poll();
				for(int childIdx=0;childIdx<adjList[parent].size();childIdx++){
					int child = adjList[parent].get(childIdx);
					if(depth[child]==-1) {
						depth[child]=depth[parent]+1;
						sparesTable[0][child] = parent;		
						q.add(child);
					}
				}
			}
		}
	}

	//점화식으로 sparesTable 완성시키기
	private static void makeSparesTable() {
		for(int k=1;k<=17;k++) {
			for(int node=1;node<=N;node++){
				sparesTable[k][node] = sparesTable[k-1][sparesTable[k-1][node]];
			}
		}
	}


	//Input && Data Setting
	private static void setData() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N+1];
		depth = new int[N+1];
		sparesTable = new int[18][N+1];//조상,정점
		for(int node=1;node<=N;node++) adjList[node] = new ArrayList<Integer>();	
		for(int i=0,from,to;i<(N-1);i++){
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}
		Arrays.fill(depth,-1);
		depth[1]=0;
		sparesTable[0][1] = 0;
	}
}
