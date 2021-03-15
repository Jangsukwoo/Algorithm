package 순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N과M2 {
	static int N,M;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] pick;
	static int[] numbers;
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pick = new int[M];
		
		nCr(0,0);
		
		System.out.println(sb.toString());
	}
	private static void nCr(int idx, int depth) {
		if(depth==M) {
			for(int i=0;i<M;i++) {
				sb.append(pick[i]+" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=idx;i<N;i++) {
			pick[depth] = i+1;
			nCr(i+1,depth+1);
		}
	}
}
