package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 이분그래프
 */
public class 이분그래프_실패 {
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
		for(int startNumber=1;startNumber<=V;startNumber++) {
			visitInfo = new int[V+1];
			visitInfo[startNumber] = 1;//1번 색은 1
			dfs(startNumber,1);//1번 정점에서 1번 색깔
			if(bipartiteGraph==false) return bipartiteGraph;
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
