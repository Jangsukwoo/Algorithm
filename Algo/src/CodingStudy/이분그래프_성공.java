package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 이분그래프
 * 지그재그로 서로 인접한 모든 다른 정점이 서로 다른 색깔로 인접해있어야함. -> dfs, bfs 로 풀이 가능
 * 
 * -> 틀렸던 이유
 * 1번 정점부터 시작했는데
 * 1번 간선리스트를 타고 가다가 나머지 확인하지 못하는 정점도 있다는 사실을 발견
 * 즉, 모든 정점에 대해 검증을 해야하는데
 * 검증하지 못한 정점이 있어서 이분그래프로서 정당함이 판명나지 않았는데도
 * 답을 도출했던게 문제였다. 끝! ㅎ0ㅎ
 * 
 * bfs로는 한번의 와일문이 돌 때가 lever, depth가 될테니 그때마다 색깔을 스위칭 시키면서 찍어보면 된다. 진짜 끝!
 */
public class 이분그래프_성공 {
	static int K;
	static int V,E;
	static ArrayList<Integer>[] adjList;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean bipartiteGraph;
	static int[] visitInfo;
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		K = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=K;testcase++){
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[V+1];
			for(int i=1;i<=V;i++) adjList[i] = new ArrayList<Integer>();
			for(int i=0,from,to;i<E;i++){
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				adjList[from].add(to);
				adjList[to].add(from);
			}
			if(isBipartiteGraph()) System.out.println("YES");
			else System.out.println("NO");
		}
	}
	private static boolean isBipartiteGraph(){
		bipartiteGraph = true;
		visitInfo = new int[V+1];
		for(int startNumber=1;startNumber<=V;startNumber++) {
			if(visitInfo[startNumber]==0) {
				visitInfo[startNumber] = 1;//1번 색은 1
				dfs(startNumber,1);//1번 정점에서 1번 색깔
				if(bipartiteGraph==false) return bipartiteGraph;
			}
		}
		return bipartiteGraph;
	}
	private static void dfs(int vertax, int color){
	
		
		if(bipartiteGraph==false)return;
		//이분그래프가 아니라면 더이상 볼 필요 없으므로
		
		
		for(int i=0;i<adjList[vertax].size();i++){
			int adjVertax = adjList[vertax].get(i);
			if(visitInfo[adjVertax]==0){//아직 방문안했고
				if(color==1) {
					visitInfo[adjVertax] = 2;
					dfs(adjVertax,2);
				}else if(color==2) {
					visitInfo[adjVertax] = 1;
					dfs(adjVertax,1);
				}
			}else {//방문한 정점인데
				if(visitInfo[adjVertax]==color){//현재 색이랑 같으면 
					bipartiteGraph = false;//이분그래프가 아니고 끝내버린다.
					return;
				}
			}
		}
	}
}
