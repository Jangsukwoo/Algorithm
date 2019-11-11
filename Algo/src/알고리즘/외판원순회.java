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
 * 
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
	private static int dfs(int current, int visit) {

		if(memo[current][visit]!=-1) return memo[current][visit];
		if(visit==((1<<N)-1)){//전부 1111~~ 이면 모든 마을 다 방문한 것
			if(weightMap[current][0]!=0){//복귀 가능하면
				return weightMap[current][0];//복귀값 return
			}else {//복귀 불가능하면
				return INF;
			}
		}
		int min = INF;
		for(int city=0;city<N;city++){//다음 방문할 지점을 고름. 어느 지역이 들어오든 0부터 볼거니까 다 가겠다는뜻. 
			//도시번호 마스킹
			//방문하지 않았고 자기 자신이 아니면 
			if((visit &(1<<city))==0 && weightMap[current][city]!=0) {//방문 안했고 경로가 존재하면
				int dist = weightMap[current][city]+dfs(city,(visit|(1<<city)));//방문체크
				if(dist<min) min = dist;//최소값 갱신
			}
		}
		memo[current][visit] = min;
		return min;
	}
}
