package 알고리즘;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Traveling Salesman problem (TSP) 
 * 그냥 풀면 O(N!)
 * 어느 한 도시에서 출발하여 N개의 도시를 모두 거쳐 다시 원래의 도시로 되돌아 오는 순회 여행경로중
 * 최단 경로 구하기
 * 
 * 한 경로만 구하면 어느 도시에서 시작하든지 구한 경로로 가면되기 때문에 (사이클)
 * 0번 도시에 대해서만 조사를 하여 최소비용값을 구한다.
 * dfs는 최소값을 구하는 과정이고
 * 최초로 태운 dfs가 끝났을때 마지막의 min값엔 최종 최단경로의 최소값이 저장되어있음.
 * dfs과정은 최소값을 업데이트하는것 
 */
public class 외판원순회 {
	static int N; //(2<=N<=16) 도시의 개수
	static int[][] weightMap;//도시 i->j로 가는 경로 비용 정보
	static int[][] memo;
	static int INF = 100000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		weightMap = new int[N][N];
		memo = new int[N][1<<N]; 
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) weightMap[row][col] = sc.nextInt();
		
		//메모이제이션 배열 값 초기화 세팅
		for(int i=0;i<N;i++) Arrays.fill(memo[i],-1);
		
		//0번도시 시작 
		System.out.println(dfs(0,1));
		
	}
	//N이 4라면 0001부터 모두 1로 색칠하는 모든 방법에 대해서 조사를 한다. 순서가있다. 
	private static int dfs(int current, int visit) {
		if(memo[current][visit]!=-1) return memo[current][visit]; //이미 구한 경로면 return
		if(visit==((1<<N)-1)){//전부 1111~~ 이면 모든 마을 다 방문한 것
			if(weightMap[current][0]!=0){//복귀 가능하면
				return weightMap[current][0];//복귀값 return. 최종경로
			}else {//복귀 불가능하면
				return INF;
			}
		}
		int min = INF;
		for(int city=0;city<N;city++){//다음 방문할 지점을 고름. 어느 지역이 들어오든 0부터 볼거니까 다 가겠다는뜻. 
			//도시번호 마스킹
			//방문하지 않았고 자기 자신이 아니면
			if((visit &(1<<city))==0 && weightMap[current][city]!=0) {//방문 안했고 경로가 존재하면
				int dist = weightMap[current][city]+dfs(city,(visit|(1<<city)));//방문체크 (하나씩 1로 만드는 모든 과정)
				//총 경로를 구하는 과정이 끝나면 아래 min값이 업데이트 
				if(dist<min) min = dist;//최소값 갱신. 복귀까지 끝낸 경로값이므로 min값을 업데이트 할 수 있다.
			}
		}
		memo[current][visit] = min; //위 과정으로부터 경로 값 저장
		return min;
	}
}
