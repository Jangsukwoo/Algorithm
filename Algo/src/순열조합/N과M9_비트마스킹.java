package 순열조합;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 링크드 해시셋 이용
 */
public class N과M9_비트마스킹 {
	static int N,M;
	static int[] data;
	static int[] result;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		data = new int[N];
		result = new int[M];
		for(int i=0;i<N;i++) data[i] = sc.nextInt();
		Arrays.sort(data);//오름차순
		dfs(0,0);
		System.out.println(sb.toString());
	}
	private static void dfs(int cnt, int visit) {
		if(cnt==M){
			for(int i=0;i<M;i++) sb.append(result[i]+" ");
			sb.append("\n");
			return ;
		}
		for(int i=0;i<N;i++) {
			if((visit&(1<<i))==0){//방문 안했으면
				if(result[cnt]!=data[i]) {
					result[cnt] = data[i];
					dfs(cnt+1,(visit|(1<<i)));
				}
			}
		}
		result[cnt]=0;
	}
}
