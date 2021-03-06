package BAEKJOON;

import java.util.Scanner;

/*
 * 순열 
 */
public class N과M_1{
	static int N;
	static int M;
	static int[] pick;
	static boolean[] visit;
	static int cnt=0;
	public static void main(String[] args) {
		setData();
		dfs(0);
		System.out.println(cnt);
	}
	private static void dfs(int depth){
		if(depth==M){
			cnt++;
			for(Integer data : pick) {
				System.out.print(data+" ");
			}
			System.out.println();
			return;
		}
		
		for(int num=1;num<=N;num++){ //1~4
			if(visit[num]==false) {
				visit[num] = true;
				pick[depth] = num;
				dfs(depth+1);
				visit[num] = false;
			}
		}
	}
	private static void setData() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		pick = new int[M];
		visit = new boolean[N+1];
	}
}
