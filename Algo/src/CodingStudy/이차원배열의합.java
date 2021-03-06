package CodingStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 2차원 배열이 주어졌을 떄 
 * i,j부터 x,y까지의 합을 구하기
 * 
 * 1.int[][] memo 배열인데 행,열 정사각형 맵 총 3가지 버전을 만든다.
 * 2.memo[0][0] = array[0][0]
 * 3.
 */
public class 이차원배열의합 {
	static int[][] array;
	static int[][] memo;
	static int N,M,K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		setData();
		memoization();
	}
	private static void memoization(){
		//초기화
		memo[0][0] = array[0][0];
		
		//Memo

	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N+1][M+1];
		memo = new int[N+1][M+1];
		for(int row=1;row<=N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=1;col<=M;col++) {
				array[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
