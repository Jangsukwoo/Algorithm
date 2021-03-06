package CodingTest;

import java.util.Arrays;

public class 영6 {

	static int INF = 987654321;
	static int[][] adjMatrix;

	public static void main(String[] args) {
		System.out.println(solution(5,new int[][] {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}},3));
		System.out.println(solution(6,new int[][] {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}},4));
	}
	public static int solution(int N, int[][] road, int K) {
		int answer = 0;
		
		adjMatrix = new int[N+1][N+1];
		
		for(int[] array : adjMatrix) Arrays.fill(array, INF); //초기 값은 전부 무한대

		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(i==j) {
					adjMatrix[i][j] = 0;
					adjMatrix[j][i] = 0;
				}
			}
		}
		
		for(int i=0;i<road.length;i++) {
			int v1 = road[i][0];
			int v2 = road[i][1];
			int cost = road[i][2];
			if(adjMatrix[v1][v2]!=INF) {//이미 기록된 도로면 더 최소의 도로를 이어준다.
				adjMatrix[v1][v2] = Math.min(adjMatrix[v1][v2],cost);
				adjMatrix[v2][v1] = Math.min(adjMatrix[v2][v1],cost);
			}
			else {
				adjMatrix[v1][v2] = cost;
				adjMatrix[v2][v1] = cost;	
			}
			
		}

		answer = getAnswerByFloydWarshall(K,N); //플로이드와샬로 1번부터 가장 최소시간내에 도달할 수 있는 마을 카운트
		
		return answer;
	}
	private static int getAnswerByFloydWarshall(int K, int N) {
		
		int answer = 0;
		
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					adjMatrix[i][j] = Math.min(adjMatrix[i][j],adjMatrix[i][k]+adjMatrix[k][j]);
				}
			}
		}
		
		for(int vertax=1;vertax<=N;vertax++) {
			if(adjMatrix[1][vertax]<=K) answer++; //K시간 이내로 갈 수 있는 곳이면 카운팅
		}
		return answer;
	}

}
