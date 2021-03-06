package BAEKJOON;

import java.util.Scanner;

/*
 * 조합
 * 
 * 4C2
 *
 * n!/(n-r)!*r!
 */
public class N과M_2{
	static int N;
	static int M;
	static int[] pick;
	static int cnt=0;
	public static void main(String[] args) {
		setData();
		dfs(1,0);
		System.out.println(cnt);
	}
	private static void dfs(int idx, int depth){
		if(depth==M){
			cnt++;
			for(Integer data : pick) {
				System.out.print(data+" ");
			}
			System.out.println();
			return;
		}

		for(int num=idx;num<=N;num++){ //1~4
			pick[depth] = num;
			dfs(num+1,depth+1);
		}
	}

	private static void setData() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		pick = new int[M];
	}
}
