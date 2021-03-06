package SDS_WEEK1;

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
 * 트리가 주어졌을 때 선택한 두 노드의 가장 가까운 공통 조상을 찾자
 * LCA(Lowest Common Ancestor)
 * 
 * MST랑 엮어서 나오기도 한다.
 * 
 * N개의 정점으로 이루어진 트리가 있음 (2<=N<=100000)
 * 두 노드의 쌍 M개 (1<=M<=100000)
 * 
 * 그래프+dp+이분탐색 문제
 * 
 */
public class 그래프_1일차_LCA2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] parent;//부모 정보

	static ArrayList<Integer>[] adjList;//인접 리스트 (간선리스트,인접행렬 X)

	static Queue<Integer> q = new LinkedList<Integer>();

	static int[] depth;

	static int N,M;

	static int a,b;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
	}
	private static void input() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		//최상위 정점 조상인걸 어떻게 알지?
		//트리를 어떤 방식으로 저정하지? -> 인접리스트
		StringTokenizer st;
		depth = new int[N+1];
		adjList = new ArrayList[N+1];
		parent = new int[18][N+1];//17인 이유 ?

		for(int i=0;i<=N;i++) {
			depth[i] = -1;
			adjList[i] = new ArrayList<>();
		}//초기화

		for(int i=0;i<(N-1);i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}//인접리스트 정보 set

		depth[1]=0;
		parent[0][1] = 0;
		q.offer(1);//sds 133page 표 참조

		while(!q.isEmpty()) {
			int current = q.poll();
			for (int node : adjList[current]){//현재 노드에 대해서. 최초 노드 번호는 1이 될거고
				if(depth[node]==-1) {
					depth[node]=depth[current]+1;//깊이가 하나 더 있다.라고 깊이 마킹
					parent[0][node] = current;//sds 133page 표의 첫번째줄
					q.offer(node);
				}
			}
		} //자식과 깊이 정보 전부 세팅 끝


		//점화식 사용해서 parent배열 Setting
		//parent[K][V] = parent[k-1][parent[k-1][V]] 점화식.
		//-> LCA 빠르게 구하기. 각 정점의 부모 뿐 아니라 2^k 조상까지 저장한다.
		System.out.println();
		for(int k=1;k<=17;k++) {
			for(int n=1;n<=N;n++) {
				parent[k][n]=parent[k-1][parent[k-1][n]];
			}
		}//모든 조상 정보 세팅 끝
		
		for(int k=0;k<=17;k++) {
			for(int n=1;n<=N;n++) {
				System.out.print(parent[k][n]+" ");
			}
			System.out.println();
		}
		
		//노드와 노드의 관계 
		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) { //공통 조상의 쌍을 알고싶다.
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()); //a라는 노드와
			b = Integer.parseInt(st.nextToken()); //b라는 노드 

			if (depth[a] > depth[b]) {//깊이 스왑 .a가 더 작도록 
				//어떻게 들어오든 b가 가진 depth가 더 깊도록
				int tmp = a;
				a = b;
				b = tmp;
			}

			for (int k = 17; k >= 0; k--) {
				if (depth[a] <= depth[parent[k][b]]) {//두 depth가 같거나 크다면
					b = parent[k][b]; //해당 조상 
				}
			}

			for (int k = 17; k >= 0 && a != b; k--) {
				if (parent[k][a] != parent[k][b]) { //조상이 같지 않으면
					a = parent[k][a]; 
					b = parent[k][b];
				}
			}
			int lca = a; 
			if(a!=b) lca = parent[0][lca]; //조상이 같지 않은경우 lca 조상..?
			bw.write(lca+"\n");         
		}
		br.close(); 
		bw.flush(); 
	}

	static int lca(int a, int b) { //이건  lca를 빠르게 구하는 이분탐색코드임 
		// depth 가 a가 더 낮으면 더 깊은것으로 swap
		if (depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		} // 높이 차이 계산
		int diff = depth[a] - depth[b];
		// ex) diff = 11 // 11 = 2^3 + 2^1 + 2^0
		int k = 0;
		while (diff >= 1) {
			if (diff % 2 == 1) {
				a = parent[k][a];
			}
			diff /= 2;
			k++;
		}
		// 위로 올라가 b와 동일한 값이 나오면 a는 LCA 임
		if (a == b) {
			return a;
		}
		// 남은 부분은 남은 값으로 점프
		for (k = 17 - 1; k > -1; k--) {
			if (parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		return parent[0][a];
	}
}

