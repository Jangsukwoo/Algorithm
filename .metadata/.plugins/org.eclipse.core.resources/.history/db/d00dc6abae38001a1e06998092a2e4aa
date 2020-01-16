package SDS_WEEK2;

import java.util.Arrays;
import java.util.Scanner;

public class 그래프_2일차_플로이드{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int INF = 100000000;
		int[][] graph = new int[n][n];
		int a,b,c;
		for (int[] is : graph) Arrays.fill(is, INF);	
		for(int i=0;i<m;i++) {
			a= sc.nextInt()-1;
			b= sc.nextInt()-1;
			c= sc.nextInt();
			graph[a][b] = graph[a][b]>c ? c:graph[a][b];
		}
		for(int i=0;i<n;i++) graph[i][i]=0;
		for(int k=0;k<n;k++)
			for(int row=0;row<n;row++)
				for(int col=0;col<n;col++) 
					graph[row][col] = Math.min(graph[row][col],graph[row][k]+graph[k][col]);
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++) if(graph[i][j]==INF) graph[i][j]=0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
			
	}
}
