package SDS_WEEK1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * K개의 도시쌍중
 * 가장 짧은 도로의 길이와 가장 긴 도로의 길이를 구하자
 * 
 * N개의 도시와 N-1개의 도로라는 것에서 트리라는 것을 캐치해야함
 * 
 * 
 * N<=100000
 * N-1개의 줄에
 * A,B,C
 * A와B사이에 길이가 C인 도로가 있다는 의미
 * 도로의 길이<=1000000
 * 쌍 K<=10만
 * K의 쌍의 정보 입력
 * 
 */
public class 그래프_1일차_도로네트워크 {
	static int N,M;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static ArrayList<Integer>[] adjList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
	}
	private static void input() throws NumberFormatException, IOException {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N+1];
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			
		}
		
	}
}
