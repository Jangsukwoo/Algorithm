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
 * Step1
 * 
 * 먼저 각 노드에 대해서 조상 정보를 Spares Table로 구성한다.
 * 구성하는 공식은 k는 대충 트리의 높이만큼 
 * Parent[k][v] = parent[k-1][parent[k-1][v]]
 * 
 * 여기서 k는 k번째 조상이고 v는 정점번호
 *  
 *  
 *  Step 2
 *  
 *  각 노드의 깊이를 저장한다.
 *  BFS방식으로 1번을 큐에 넣고
 *  1번 간선부터 확인하면서
 *  각 노드의 깊이를 저장한다.
 *  
 *  Step 3
 *  
 *  확인하려고 하는 두 노드에 대해서
 *  깊이 정보를 통해 깊이를 맞추고
 *  Spares Table을 참조해서 공통 조상을 찾아간다.
 * 그래프+DP+이분탐색 외우기
 */
public class LCA_최소공통조상찾기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] parent;//부모 정보 = Spares Table
	static ArrayList<Integer>[] adjList;//인접 리스트 (간선리스트,인접행렬 X)
	static Queue<Integer> q = new LinkedList<Integer>();
	static int[] depth;
	static int N,M;
	static int a,b;

	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
	}

	private static void setData() throws NumberFormatException, IOException {
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
	}
}
