package 생각하게하는문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
10 1
1 2 3 4 5 -1 -2 -3 -4 -5
1 5 -3


-3 2 0 0 0 8 -2 0 0 0 -5
생각 깊이있게 해야함
 */
public class 태상이의훈련소생활 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M;
	static int[] heights;
	static int[] order;
	public static void main(String[] args) throws IOException {
		setData();
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		heights = new int[N+1];
		order = new int[N+2];
		st = new StringTokenizer(br.readLine());
		for(int num = 1 ; num<=N; num++) heights[num] = Integer.parseInt(st.nextToken());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			order[a]+=k; //판 깊이
			order[b+1]-=k; //파기 직전 높이 만큼 원상 복구량
		}
		System.out.println(Arrays.toString(order));
		int uphill = 0; //각각의 칸에서 파는 깊이,덮은 깊이 만큼 최종량 추가
		for(int num=1;num<=N;num++) {
			uphill+=order[num];
			heights[num]+=uphill;
		}
		for(int num=1;num<=N;num++) System.out.print(heights[num]+" ");
	}
}
