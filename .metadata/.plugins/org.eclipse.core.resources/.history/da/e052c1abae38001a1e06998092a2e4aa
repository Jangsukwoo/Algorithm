package SDS_WEEK2;

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
 * N개의 도시 Vertax
 * N-1개의 도로  Edge
 *
 * 어떤 두 도시 D,E를 연결하는 경로 중 가장 짧은 도로의 길이와 가장 긴 도로의 길이.
 * 
 * 가장 짧은 도로의 길이는 가장 가까운 공통 조상을 찾고
 * 가장 먼 도로의 길이는 가장 먼 공통 조상을 찾으면 된다.
 * 
 */
public class 그래프_2일차_도로네트워크 {
	static final int binaryKmax = 18;//모든 노드의 수보다 많게
	static final int INF = 987654321;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static int K;
	static int M;
	static int[] depth;
	static int[][] parent;//binaryKSize,nodeSize 각 노드의 부모 정보들
	static Queue<Integer> q;
	
	static int[][] minCostTable;
	static int[][] maxCostTable;
	static ArrayList<int[]>[] adjList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		inputData();
		setParent();
		setLCADPTable();
		getAnswerUsingLCA();
		bw.flush();
		bw.close();
	}

	private static void inputData() throws NumberFormatException, IOException {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		declareDataAndInitialize();


		for(int i=0,from,to,cost;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			adjList[from].add(new int[] {to,cost});
			adjList[to].add(new int[] {from,cost});
		}//인접리스트 세팅			
	}
	private static void declareDataAndInitialize() {
		adjList = new ArrayList[N+1];
		depth = new int[N+1];//depth필요
		parent = new int[binaryKmax][N+1];
		minCostTable = new int[binaryKmax][N+1];//여기서 k는 노드의 첫째부모,둘쨰부모,셋째부모....
		//->0은 첫쨰부모, 1은 둘쨰부모..
		maxCostTable = new int[binaryKmax][N+1];
		
		for(int i=0;i<=N;i++) { //왜 0부터할까?
			depth[i]=-1;
			adjList[i] = new ArrayList<>();
			for(int k=0;k<binaryKmax;k++) {
				minCostTable[k][i] = INF;
			}
		}
	}
	private static void setParent() {
		q = new LinkedList<>();
		q.add(1);
		depth[1] = 0;//루트의 깊이는 0
		parent[0][1] = 1;
		
		while(!q.isEmpty()){//트리 정보 구성을 위한 탐색 
			int currentNode = q.poll();
			for (int[] child : adjList[currentNode]) {
				int childNum = child[0];
				int cost = child[1];
				if(depth[childNum]==-1){//방문하지 않은 노드에 대해서 
					depth[childNum]=depth[currentNode]+1;
					parent[0][childNum] = currentNode;
					minCostTable[0][childNum] = cost;
					maxCostTable[0][childNum] = cost;
					q.add(childNum);
					
				}
			}
		}
	}

	

	private static void setLCADPTable() {
		for(int k=1;k<binaryKmax;k++){//각 루트의 다음 층들에 대해서
			for(int node=1;node<=N;node++){//각각의 노드들에 대해
				parent[k][node] = parent[k-1][parent[k-1][node]];//부모의 부모값을 가리키도록 
				maxCostTable[k][node] = Math.max(maxCostTable[k-1][node],maxCostTable[k-1][parent[k-1][node]]);
				minCostTable[k][node] = Math.min(minCostTable[k-1][node],minCostTable[k-1][parent[k-1][node]]); //이게 왜 갱신이 되어주는건지..?
			}
		}
	}


	
	private static void getAnswerUsingLCA() throws NumberFormatException, IOException { //만들어진 LCA 테이블을 사용해서 답을 구한다.
		//깊이를 확인하면서 같은 부모를 가질 떄
		StringTokenizer st;
		M = Integer.parseInt(br.readLine());
		for(int i=0,a,b;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());//a랑 b가 공통 조상을 찾을 때 까지
			int min = INF;
			int max = -1;
			
			//depth 맞추기
			if(depth[b]>depth[a]) {
				int tmp = b;
				b = a;
				a = tmp;
			}
			if(depth[a]>depth[b]) {
				for(int k=binaryKmax-1;k>=0;k--){
					while(a!=b && depth[parent[k][a]]>=depth[b]){//depth가 맞을 때 까지
						min = Math.min(min,minCostTable[k][a]);
						max = Math.max(max,maxCostTable[k][a]);
						a = parent[k][a];//올라감 
					}
				}
			}
			for(int k=binaryKmax-1;k>=0;k--){
				while(a!=b && parent[k][a]!=parent[k][b]){//depth가 맞을 때 까지
					min = Math.min(min,Math.min(minCostTable[k][a],minCostTable[k][b]));
					max = Math.max(max,Math.max(maxCostTable[k][a],maxCostTable[k][b]));
					a = parent[k][a];
					b = parent[k][b];//올라감 
				}
			}
			if(a!=b) {
				min = Math.min(min,Math.min(minCostTable[0][a],minCostTable[0][b]));
				max = Math.max(max,Math.max(maxCostTable[0][a],maxCostTable[0][b]));
			}
			bw.write(min+" "+max+"\n");
		}
	}

}
