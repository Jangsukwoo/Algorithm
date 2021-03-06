package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
4 1
1 2 3 4
2 3 4 5
3 4 5 6
4 5 6 7
1 1 4 4


2 1
1 2
3 4
1 1 2 2 
 * 
 */
public class 동적계획법_3일차_구간합구하기5 {
	static int N,M;
	static int[][] matrix;
	static int[][] colDP;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		input();
		output();
	}
	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		colDP = new int[N+1][N+1];

		matrix = new int[N+1][N+1];
		for(int row=1;row<=N;row++) {
			st =  new StringTokenizer(br.readLine());	
			for(int col=1;col<=N;col++) {
				matrix[row][col] = Integer.parseInt(st.nextToken());
				colDP[row][col]+=colDP[row-1][col]+matrix[row][col];
			}	
		}
		view();
	}
	private static void view() {
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++) {
				System.out.print (colDP[row][col]+" ");
			}	
			System.out.println();
		}
		System.out.println();
	}
	private static void output() throws IOException {
		for(int i=0,rs,re,cs,ce;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int sum=0;
			rs = Integer.parseInt(st.nextToken());
			cs = Integer.parseInt(st.nextToken());
			re = Integer.parseInt(st.nextToken());
			ce = Integer.parseInt(st.nextToken());
			for(int col=cs;col<=ce;col++) sum+= colDP[re][col]-colDP[rs-1][col];
			bw.write(sum+"\n");
		}
		bw.flush();
		bw.close();
	}
	
}

