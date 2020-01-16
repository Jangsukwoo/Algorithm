package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*

단절점 (Articulation Point)?
단절점의 정의는 그래프에서 어떤 정점을 제거 했을 때 그 그래프가 두개 또는 그 이상으로 나누어지게 되는 정점을 말한다.
즉, 제거했을 때 그래프의 Connected Component의 개수가 증가하는 정점을 말함 

A가 시작정점이 아닌 경우
orderA<=lowB => A는 단절점
orderA>lowB =>A는 단절점이 아님

A가 시작정점인 경우
2<=childA => A는 단절점
2>childA =>A는 단절점이 아님

두 정수 
V <= 1만
E <=10만 

->그래프 V개의 정점과 E개의 간선 


Output 
단절점의 개수와
단절점의 번호를 공백으로 구분해 오름차순으로 출력 

잘 이해가 안된다..


--------
단절점에 바로 인접해 있는 정점들의 쌍은 단절점이 없으면 우회로 돌아가는 길조차 없어야 한다.

특정 정점 A의 자식노드들이
정점 A를 거치지 않고 
정점 A보다 빠른 방문번호를 가진 정점으로 갈 수 없다면
단절점이다.

쉽게말하면 어떤 정점 찍었을 떄 
이전에 이미 방문했던 정점으로 갈 수 있으면 단절점이 아니라는 것. 갈수 없어야 단절점이라는 것

단, Root의 경우 자식의 수가 2개 이상이면 무조건 단절점임.


low의 개념

 */
public class 그래프_3일차_단절점 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int V,E;
	static int[] order;//방문순서
	static int[] lows;//초기 값은 자신의 order이고 V 이후에 방문하는 정점들 중 V를 거치지 않고 방문 가능한 정점들의 order중 가장 작은값
	static boolean[] cutVertax;//단절점 체크
	static ArrayList<Integer>[] edgeList; //엣지리스트. 엣지정보 다 받음
	static int count=1;
	public static void main(String[] args) throws IOException {
		inputAndInitialize();//Input 및 초기 세팅
		findCutVertax();
		printAnswer();
	}
	private static void printAnswer() throws IOException {
		int cnt=0;
		for(int i=1;i<=V;i++) if(cutVertax[i]) cnt++;
		bw.write(cnt+"\n");

		for(int i=1;i<=V;i++) if(cutVertax[i]) bw.write(i+" ");

		bw.flush();
		bw.close();
	}
	private static void findCutVertax() {
		for(int v=1;v<=V;v++){//dfsAll
			if(order[v]==0){//아직 방문 못한 정점에 대해서
				dfs(v,true);
			}
		}
	}

	private static int dfs(int vertax, boolean isRoot) {
		/* 자기보다 앞에 탐색할수 있는 경우가 있으면 단절점이 되지 않는다. */
		/* DFS스패닝트리를 만들면서 기존 트리는 그대로 사용됨 없어지는 것이 아님*/
		/* DFS스패닝 트리의 역할은 순서를 지정해 주는 것과 
		 * DFS스패닝 트리에서 루트가 자식을 2개 가지는지 체크 */

		order[vertax] = count++;
		int orderResult = order[vertax]; //방문번호 return

		for(int i=0,to;i<edgeList[vertax].size();i++){//매달려있는 자식들에 대해 
			to = edgeList[vertax].get(i);
			if(order[to]==0){//방문 안했으면
				//자식 노드가 갈 수 있는 노드 중 가장 일찍 방문한 노드의 방문 순번
				int low = dfs(to,false);
				lows[to] = low;//이게 도대체 머지..머하는거지?!

				//low가 자기 방문 순서보다 늦거나 같은 경우 자기보다 앞에 있는 경로는 자기를 통해서 밖에 못간다 -> 단절점
				//root가 아니면서 orderA<=lowB의 경우임 
				if(!isRoot && low>=order[vertax]) {
					cutVertax[vertax] = true;
				}
				orderResult = Math.min(orderResult, low);
			}else{//방문 한 정점이면
				/* 
	        	만약 이미 DFS에서 탐색된 정점이라면 
	        	현재 정점의 방문순서와 탐색된 정점의 방문 순서중 min값을 찾는다.
	        	이 방법을 이용해야 
	        	" A번 정점에서 자식 노드들이 정점 A를 거치지 않고 
	        	정점 A보다 빠른 방문번호를 가진 정점으로 갈 수 없다면 단절점이다. "
	        	를 찾을 수 있다.
			 */

				orderResult = Math.min(orderResult, order[to]);
			}
		}
		//루트인 경우 스패닝 트리에서 자식이 두개 있는 경우 단절점
		//A가 루트일 때 childA>=2의 경우 

		return orderResult;
	}
	private static void inputAndInitialize() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeList = new ArrayList[V+1];
		order = new int[V+1];
		lows = new int[V+1];
		cutVertax = new boolean[V+1];
		for(int i=1;i<=V;i++) edgeList[i] = new ArrayList<>();
		for(int i=0,a,b;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			edgeList[a].add(b);
			edgeList[b].add(a);//양방향
		}
	}
}
