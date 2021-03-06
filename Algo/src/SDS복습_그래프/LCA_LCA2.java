package SDS복습_그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 최소공통조상찾기(lca)란 ?
 * 
 * 어떤 트리가 주어졌을 때
 * 선택한 두 노드의 조상이 처음으로 같아지는 노드를 말한다.
 * 
 * 주어진 트리를 간선리스트로 저장을 한다.
 * 

 *  
 *  
 * Step 1
 *  
 * 각 노드의 깊이를 저장한다.
 * BFS방식으로 1번을 큐에 넣고
 * 1번 간선부터 확인하면서
 * 각 노드의 깊이를 저장한다.
 *  
 *  
 * Step 2
 * 
 * 먼저 각 노드에 대해서 조상 정보를 Spares Table로 구성한다.
 * 구성하는 공식은 k는 대충 트리의 높이만큼 
 * Parent[k][v] = parent[k-1][parent[k-1][v]]
 * 
 * 여기서 k는 k번째 조상이고 v는 정점번호
 * 
 * Step 3
 *  
 * 확인하려고 하는 두 노드에 대해서
 * 깊이 정보를 통해 깊이를 맞추고
 * Spares Table을 참조해서 공통 조상을 찾아간다.
 * 그래프+DP+이분탐색 외우기
 */
public class LCA_LCA2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] parent;//부모 정보 = Spares Table
	static ArrayList<Integer>[] adjList;//인접 리스트 (간선리스트,인접행렬 X)
	static Queue<Integer> q = new LinkedList<Integer>();
	static int[] depth;
	static int N,M;
	static int a,b;

	public static void main(String[] args) throws NumberFormatException, IOException {
		//데이터 입력
		setData();
		//Step 1, Step 2: Spares Table 및 Depth정보 Setting
		setDepthAndSparesTable();
		//Step 3 : Spares Table과 Depth 정보를 활용해서 query 처리
		excuteQuery();
	}

	private static void excuteQuery() throws NumberFormatException, IOException {
		M = Integer.parseInt(br.readLine());
		for(int i=0,a,b;i<M;i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			//a와 b의 공통 조상 찾기 
			int lca = findLca(a,b);
			bw.write(lca+"\n");
		}
		bw.flush();
		bw.close();
	}

	private static int findLca(int a, int b){
		int lca = 0;
		if(depth[a]>depth[b]){//a가 b보다 더 깊은 곳에 있으면
			int tmp = a;
			a = b;
			b = tmp;
		}//Swap을 통해 더 깊은 정점은 항상 b가되고 더 높은 정점은 a가 된다.
		
		for (int k = 17; k >= 0; k--) { //깊이 맞추기. 더 깊은 b를 a까지 끌어올려 맞춘다. 
			if (depth[a] <= depth[parent[k][b]]){//b가 더 깊다면
				b = parent[k][b];//b의 부모로
			}
		}
		//이 처리가 끝나면 a와 b는 같은 깊이에 위치한 정점이 된다.
		
		for (int k = 17; k >= 0; k--) { //부모 찾기. 부모가 같아질 때 까지 찾는다  
			if (parent[k][a]!=parent[k][b]){//부모가 같지 않으면 계속 타고 올라간다.
				a = parent[k][a];
				b = parent[k][b];
			}
		}//조상찾기 끝
		
		//조상찾을 때 좀 더 시간을 줄이고 싶다면 이분탐색으로 찾으면된다.
		lca = a;
		if(a!=b) lca = parent[0][a];
		return lca;
	}

	private static void setDepthAndSparesTable() {
		depth[1]=0; //1번은 루트노드니까 깊이 0
		parent[0][1] = 1;//1번의 조상은 없으니 0
		q.offer(1);//1번노드부터 시작
		
		//깊이 정보 만들기
		while(!q.isEmpty()){
			int currentVertax = q.poll();
			for(Integer nextVertax : adjList[currentVertax]){
				if(depth[nextVertax]==-1){//Depth 정보가 없다면
					depth[nextVertax] = depth[currentVertax]+1;//지금 정점보다 한칸 더 밑에 있다.
					parent[0][nextVertax] = currentVertax;//부모 정보 갱신 
					q.add(nextVertax);
				}
			}
		}
		
		//Spares Table 만들기
		for(int k=1;k<=17;k++) {
			for(int v=1;v<=N;v++) {
				parent[k][v] = parent[k-1][parent[k-1][v]];
			}
		}
	}

	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		depth = new int[N+1];
		adjList = new ArrayList[N+1];
		parent = new int[18][N+1];//2^17승 정도면 모든 노드에 대한 표현이 가능 

		for(int i=0;i<=N;i++) {
			depth[i] = -1;//depth정보 -1로 초기화 
			adjList[i] = new ArrayList<>();
		}//초기화

		for(int i=0;i<(N-1);i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}//인접리스트 정보 set
	}
}
