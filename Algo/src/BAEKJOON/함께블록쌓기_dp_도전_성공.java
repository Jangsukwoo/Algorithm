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
 * 1. dp배열 int[학생번호][높이]
 * 2. dp배열 초기화 dp[1][높이] = 학생이 가진 블록 높이 1
 * 3. dp[i][height] = dp[i-1][height]+currentStudentHasBlockHeight (단,<=H,높이를 만족할 때)
 */
public class 함께블록쌓기_dp_도전_성공 {
	static int N,M,H;
	static int mod = 10007;
	static ArrayList<Integer>[] blocklistOfStudent;//인덱스 : 학생번호, 노드 : 블록들
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		setData();
		memoization();
		System.out.println(dp[N][H]);
	}
	private static void memoization() {

		//1.memo 배열
		dp = new int[N+1][H+1]; 		

		//2. 초기화 	
		for(Integer height : blocklistOfStudent[1]) dp[1][height] = 1;

		//3. dp[i][height] = dp[i-1][height]+blocklistOfStudent[i] has BlockHeight (단,<=H,높이를 만족할 때)
		for(int i=2;i<=N;i++){
			for(int j=0;j<=H;j++) {
				if(dp[i-1][j]>=1){//만들어진 높이가 있는 경우
					for(int k=0;k<blocklistOfStudent[i].size();k++){
						int height = j+blocklistOfStudent[i].get(k); //블록을 쌓아보고
						if(height<=H){ //만들기 가능하면
							dp[i][height]+=dp[i-1][j]; //이전에 만들어온 경우의수 누적
							dp[i][height]%=mod; // % 10007
						}
					}
				}
			}
		}
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
			blocklistOfStudent[i].add(0);//안쌓는 경우
			for(int j=0,height;j<tokenSize;j++){
				height = Integer.parseInt(st.nextToken());
				blocklistOfStudent[i].add(height);
			}
		}
	}
}
