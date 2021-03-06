package SDS복습_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 단절선_단절선복습 {
	/*
	 * 단절점 구현과 유사하며 구현량은 단절점보다 적다.
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] visit;
	static int visitOrder;
	static StringTokenizer st;
	static int V,E;
	static ArrayList<Integer>[] adjList;
	static ArrayList<int[]> breakLine;
	static int breaLineCnt;
	public static void main(String[] args) throws IOException {
		setData();
		getBreakLineList();
		Collections.sort(breakLine, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) return Integer.compare(o1[1],o2[1]);
				else return Integer.compare(o1[0],o2[0]);
			}
		});
		System.out.println(breakLine.size());
		for(int i=0;i<breakLine.size();i++) {
			int[] breakLineEdge = breakLine.get(i);
			System.out.println(breakLineEdge[0]+" "+breakLineEdge[1]);
		}
	}
	private static void getBreakLineList() {
		for(int vertax=1;vertax<=V;vertax++) {
			if(visit[vertax]==0){//아직 방문 안되어있으면 다른 컴포넌트이므로
				dfs(vertax,0);//정점 번호와 parent
			}
		}
	}
	private static int dfs(int currentVertax, int parent) {
		visit[currentVertax]=++visitOrder;
		int ret = visit[currentVertax];//현재 정점의 방문번호를 넣어주는데 이 ret값은 속한 컴포넌트의 가장 작은 값으로 계속 update될 것임.
		int previousRet=0;
		for(int i=0;i<adjList[currentVertax].size();i++){//매달 정점들을 확인한다.
			int nextVertax = adjList[currentVertax].get(i);
			if(nextVertax==parent) continue;//바로 직전, 즉 부모면 이미 방문한 것이니 확인하지 않고 넘어감
			if(visit[nextVertax]!=0){
				//직전에서 넘어오지 않았는데 뭔가 방문 기록이 되어있는 정점이라면 지금 정점보다는 빠르게 방문했던 정점이므로
				//알고있는 방분번호의 최소값을 갱신해주면서 다음 매달린 정점을 확인한다.
				ret = Math.min(visit[nextVertax],ret);
				continue;
			}
			//만약 위 두 조건에 해당하 지않으면 방문하지 않은 정점인 것이니 
			previousRet = dfs(nextVertax,currentVertax);
			if(previousRet>visit[currentVertax]){//지금 알고있는 ret값보다 크다면 단절선
				int from,to;
				if(nextVertax>currentVertax){
					to = nextVertax;
					from = currentVertax;
				}else {
					to =currentVertax;
					from = nextVertax;
				}
				breakLine.add(new int[]{from,to});
				breaLineCnt++;
			}
			ret = Math.min(previousRet,ret);
		}
		
		return ret;
		
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[V+1];
		breakLine = new ArrayList<int[]>();
		visit = new int[V+1];
		for(int i=1;i<=V;i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		for(int i=0,from,to;i<E;i++){
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}
	}
}
