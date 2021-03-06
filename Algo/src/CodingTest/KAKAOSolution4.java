package CodingTest;

import java.util.Arrays;

public class KAKAOSolution4 {
	static int INF = 1000000000;
	static int[][] adjMatrix;
	static boolean[] visit;
	static int sum=0;
	static boolean muzi,apeach,divide;
	public static void main(String[] args) {
		solution(6,4,6,2,new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
	}
    public static int solution(int n, int s, int a, int b, int[][] fares) {
    	adjMatrix = new int[n+1][n+1];
    	for(int[] list : adjMatrix) Arrays.fill(list,INF);
        int answer = Integer.MAX_VALUE;;
        for(int i=0,from,to,cost;i<fares.length;i++) {
        	from = fares[i][0];
			to = fares[i][1];
			cost = fares[i][2];
			adjMatrix[from][to] = adjMatrix[from][to]>cost ? cost:adjMatrix[from][to];
        }
    	for(int i=1;i<=n;i++) adjMatrix[i][i]=0;
    	
		for(int k=1;k<=n;k++)
			for(int row=1;row<=n;row++)
				for(int col=1;col<=n;col++) 
					adjMatrix[row][col] = Math.min(adjMatrix[row][col],adjMatrix[row][k]+adjMatrix[k][col]);
		for(int row=1;row<=n;row++) {
			for(int col=1;col<=n;col++) {
				adjMatrix[row][col] = adjMatrix[col][row];
			}
		}
		for(int point=1;point<=n;point++){
			sum = 0;
			visit = new boolean[n+1];
			visit[4] = true;
			muzi = false;
			apeach = false;
			divide = false;
			dfs(point,4,n,a,b);
			System.out.println(sum);
			answer = Math.min(answer,sum);
		}
		System.out.println(sum);

		for(int row=1;row<=n;row++) {
			for(int col=1;col<=n;col++) {
				System.out.print(adjMatrix[row][col]+" ");
			}
			System.out.println();
		}
		return answer;
    }
	private static void dfs(int point, int currentNode,int n,int a,int b){
		if(currentNode==point || divide) {//따로가기
			if(muzi==false) {
				for(int i=1;i<=n;i++) {
					if(visit[i]==false && adjMatrix[currentNode][i]!=INF){
						visit[i] = true;
						sum+=adjMatrix[currentNode][i];
						if(i==a) muzi = true;
						dfs(point, i, n, a, b);
					}
				}
			}
			if(apeach==false) {
				for(int i=1;i<=n;i++) {
					if(visit[i]==false && adjMatrix[currentNode][i]!=INF){
						visit[i] = true;
						sum+=adjMatrix[currentNode][i];
						if(i==b) apeach = true;
						dfs(point, i, n, a, b);
					}
				}
			}
		}else if(divide==false){//합승해서가기
			for(int i=1;i<=n;i++) {
				if(visit[i]==false && adjMatrix[currentNode][i]!=INF){
					visit[i] = true;
					sum+=adjMatrix[currentNode][i];
					if(i==a) muzi = true;
					if(i==b) apeach = true;
					dfs(point, i, n, a, b);
				}
			}
		}
	}
}
