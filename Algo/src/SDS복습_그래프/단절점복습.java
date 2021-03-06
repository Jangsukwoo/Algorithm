package SDS복습_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 단절점은 두개의 컴포넌트로 나뉘는데
 * dfs 탐색할 때 생기는 dfs 스패닝 트리를 이용하면
 * 단절점을 빠른 시간 내에 효과적으로 찾아낼 수 있다.
 * 
 * step 1. 먼저 dfs로 탐색하면서 탐색되는 순서대로 넘버링.
 * step 2. 현재 탐색하는 정점 A에서 연결된 정점들 중
 * 정점 A보다 늦게 탐색되는 정점들에 대해 
 * 정점 A보다 먼저 탐색되는 정점으로 가는 경로가 없는 경우
 * 정점 A는 단절점이 된다.
 * 
 * 구현은
 * dfs에서 탐색되는 순서대로 discover 번호를 매겨 주면서
 * 아직 탐색이 안된 경우 해당 정점에서 dfs를 탐색하여
 * 나오는 정점 중 discover 번호가 가장 적은 정점에 대해
 * 그 정점의 discover 번호만 비교하면서
 * 가장 작은 discover 번호가 나의 discover 번호보다
 * 크거나 같다면 그 정점은 단절점이 된다.
 * 
 * 한번의 dfs로 단절점을 찾아낼 수 있다.
 */
public class 단절점복습 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int V,E;
	static int[] visit;
	static int[] breakPointVertax;
	static int breakPointSize;
	static int visitNumber;
	static ArrayList<Integer> breakPointList;
	static ArrayList<Integer>[] adjList;
	public static void main(String[] args) throws IOException {
		setData();
		breakPoint();//dfs로 단절점 찾기
		getBreakPoint();
		System.out.println("방문순서");
		System.out.println(Arrays.toString(visit));
		System.out.println(breakPointSize);
		for(int i=0;i<breakPointList.size();i++) {
			System.out.print(breakPointList.get(i)+" ");
		}
	}
	private static void getBreakPoint() {
		for(int i=1;i<=V;i++){
			if(breakPointVertax[i]==1) {
				breakPointSize++;
				breakPointList.add(i);
			}
		}
	}
	private static void breakPoint() {
		for(int vertax=1;vertax<=V;vertax++) {
			if(visit[vertax]==0){//방문을 아직 못했다면 다른 컴포넌트.
				//dfs를 통해 정점탐색. dfs를 통해 이 정점이 속한 컴포넌트 내에 모든 정점들을 다 방문하게된다.
				dfs(vertax,true);
			}
		}
	}
	private static int dfs(int currentVertax, boolean isRoot){
		System.out.println("방문순서"+currentVertax);
		visit[currentVertax]=++visitNumber;//방문 순서 넣기
		int child=0;//루트에서 시작해서 어떤 정점들을 탐색한 뒤에 다시 이 루트를 거쳐서 정점들을 탐색하게 되면 child가 2가 된다.
		//즉 이 루트를 다시 거치지않고서는 이후 정점들은 탐색할 수 없게되는 것이라
		//child가 2라면 루트가 단절점이 되버린다.
	
		
		//현재 정점에서
		//방문 순서를 기록해 둘건데 
		//알고 있는 가장 작은 방문순서번호를 저장할 것임
		//일단은 현재 정점의 방문순서를 기록해놓고
		//같은 컴포넌트 내에서 가장 작은 방문번호를 가진 
		int minVisitNumber = visit[currentVertax];
	

		int previousMinVisitNumber=0;
		for(int i=0;i<adjList[currentVertax].size();i++){//현재 정점에서 매달린 자식들을 확인한다.
			int nextVerTaxNumber = adjList[currentVertax].get(i); //매달린 정점을 하나씩 확인
	
			if(visit[nextVerTaxNumber]!=0){ 
				//이미 방문 한 정점이면, 즉 거쳐온 점이면 안보겠다.
								
				//지금 방문한 정점의 방문 순서 번호와 이전에 방문 된 방문 정점 순서 번호를 비교해서 더 작은 것을 선택
				//누가누가 더 작나 
				
				minVisitNumber = Math.min(minVisitNumber,visit[nextVerTaxNumber]);
				
				
				//만약 1번 루트먼저 시작했다면 방문번호는 1이고 min update를 통해  이 값은 계속해서 1이 될것임.
			}
			else{
				//방문 안해본 점이라면. 즉 아직 안가본 정점이면
				
				child++;//자식 컴포넌트 시작. 이 child값은 root일때만 의미있게 쓰인다. 
				
				//루트 이후 탐색되는 정점들은 isRoot가 false이므로 child값은 무의미.
				
				previousMinVisitNumber = dfs(nextVerTaxNumber,false);//루트 이후로서 방문해본다.
				//한 컴포넌트 내에서 제일 방문 번호가 작은 값을 뱉어 냈는데 
				//지금 정점보다 더 큰값이 나왔다면
				//지금 정점을 거치지 않고서는 갈 수 없는 정점이라는 뜻으로 해석..중이다
				//따라서 현재 정점은 단절점이 된다.
				if(!isRoot && (previousMinVisitNumber>=visit[currentVertax])){
					//지금 방문 해본것 보다 더 크다면?
					System.out.println("루트가 아니지만 단절점"+currentVertax);
					breakPointVertax[currentVertax]=1;
				}
				minVisitNumber = Math.min(previousMinVisitNumber,minVisitNumber);
			}
		}
		if(isRoot){//루트인데
			if(child>=2){//자식 컴포넌트가 두개 이상이라면 단절점이므로
				System.out.println("루트로써 단절점"+currentVertax);
				breakPointVertax[currentVertax] = 1;
			}
		}
		return minVisitNumber;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		visit = new int[V+1];
		adjList = new ArrayList[V+1];
		breakPointVertax = new int[V+1];
		breakPointList = new ArrayList<Integer>();
		for(int i=1;i<=V;i++) adjList[i] = new ArrayList<Integer>();
		for(int i=1;i<=E;i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}

	}
}
