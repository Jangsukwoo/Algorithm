package 순열조합;

import java.util.Scanner;
public class nPr{
	public static int N;
	public static int M;
	public static int[] data;
	public static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		data=new int[N];
		visited = new boolean[N];
		dfs(0);
	}
	private static void dfs(int cur) {
		if(cur==M) {
			for(int i=0;i<M;i++) System.out.print(data[i]+" ");
			System.out.println();
			return;
		}
		for(int j=0;j<N;j++) {
			if(visited[j]==false) {
				visited[j]=true;
				data[cur]=j+1;
				dfs(cur+1);
				visited[j]=false;
			}
				
		}
	}
}
