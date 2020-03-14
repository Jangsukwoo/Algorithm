package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 함께 볼록 쌓기 
 * 
 * N명의 학생
 * M개의 최대 보유 블록
 * H는 목표 높이
 * 
 * dfs로하니 터짐 
 * ->dp로 도전해보기
 * 
 * 
 * 
 */
public class 함께블록쌓기_dp_도전 {
	static int N,M,H;
	static int possibleCase;
	static int mod = 10007;
	static ArrayList<Integer>[] blocklistOfStudent;//인덱스 : 학생번호, 노드 : 블록들
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		setData();
		System.out.println(possibleCase);
	}
	private static void setData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		blocklistOfStudent = new ArrayList[N+1];
		for(int i=1;i<=N;i++) blocklistOfStudent[i] = new ArrayList<Integer>();
		
		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine());
			int tokenSize = st.countTokens();
			for(int j=0,height;j<tokenSize;j++){
				height = Integer.parseInt(st.nextToken());
				blocklistOfStudent[i].add(height);
			}
		}
	}
}
