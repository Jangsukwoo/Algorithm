package 최단경로알고리즘;
/*
 * 다익스트라 
 * 최단경로를 구하기 위한 알고리즘
 * 양의가중치로만 이루어져있어야한다. 음의가중치가 생기는순간 음의 사이클이 생김
 * 음의 사이클까지 해결할 수 있는 알고리즘은 벨만포드 알고리즘
 * 
 * 작동
 * 시작점을 정해주고
 * 이어진 간선들을 방문하면서 
 * 그 노드를 방문할 수 있는 최단 경로를 구하며 적어주고 갱신하는 방식
 * 
 */
public class 다익스트라 {
	static int[][] adjMatrix; //인접행렬 (노드의 연결관계 및 비용(가중치))
	static int[] dist;//출발지부터 최단거리 저장할 배열
	static int[] path;//경로
	static int[] visit;//방문체크
	static int N; //노드의 수
	static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Dijkstra();
	}

	private static void Dijkstra(){
		int start = 1;//시작점 노드를 1이라 하자.
		//즉, 1노드에서 시작했을 때 모든 노드를 가는 최단 경로를 찾고싶음
		
		for(int i=1;i<=N;i++) {
			dist[i] = INF;
		}//모든 거리는 일단 모르니까 INF
		dist[start]=0;//시작점에서 시작점 거리는 당연히 0
		//초기화 세팅 끝
		
		
		for(int i=1;i<N;i++){//각 노드에 대해서
			int minNode = INF;
			int minVal = INF;//최소값은 일단 INF

			for(int j=1; j<N; j++){//최소값 추출
				if(visit[j]==0 && minVal>dist[j]){ //방문 하지 않았고 알고 있는 경로중 가장 최단경로인 노드를 찾는다.
					minNode = j;
					minVal = dist[j];
				}
			}
			visit[minNode]=1; //방문하지 않았고 최단 경로인 노드를 방문 체크
			
			
			//Edge relaxation (간선 경감)
			//간선 경감? 시작점부터 도달하려는 목적지 노드에 걸리는 최단 경로 값을 
			//목적지 노드에 쓰며 업데이트 하는 연산을 일컫는다.
			//간선 경감식 : dv ← du+w(u,v); 
			
			for(int j=1;j<N;j++){//Edge relaxation
				if(dist[j]> (minVal+adjMatrix[minNode][j])){
					dist[j]=minVal+adjMatrix[minNode][j];
					path[j] = minNode;
				}
			}
			
		}
		
		
	}
}
