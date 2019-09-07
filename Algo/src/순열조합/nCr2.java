package 순열조합;

import java.util.Arrays;
import java.util.Scanner;

public class nCr2{
	public static int M;
	public static int N;	
	public static int[] data;
	public static int[] result;
	public static boolean[] visit;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		data = new int[N];
		result = new int[N];
		visit = new boolean[N];
		for(int i=0;i<N;i++) data[i]=sc.nextInt(); 
		Arrays.sort(data);
		dfs(0);
		System.out.println(sb.toString());
	}

	private static void dfs(int cur) {
		if(cur==M) {
			for(int i=0;i<(M-1);i++) {
				if(result[i]>=result[i+1]) return;
			}
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