package 순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * for문 중첩으로 푸는게 더 빠르지만 dfs버전 코드도 구현해보자.
 */

public class nPIEr{
	public static int M;
	public static int N;	
	public static int[] data;
	public static int[] result;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		data = new int[N];
		result = new int[N];
		for(int i=0;i<N;i++) data[i]=i+1; 
		dfs(0);
		System.out.println(sb.toString());
	}

	private static void dfs(int cur) {
		if(cur==M) {
			for(int i=0;i<M;i++) {
				sb.append(String.valueOf(result[i])+" ");
			}
			sb.append("\n");	
			return;
		}
		for(int i=0;i<N;i++) {
			result[cur] = data[i];
			dfs(cur+1);
		}
	}
}
