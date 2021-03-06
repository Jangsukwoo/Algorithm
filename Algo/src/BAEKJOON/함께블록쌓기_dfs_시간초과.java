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
 * 
 */
public class 함께블록쌓기_dfs_시간초과 {
	static int N,M,H;
	static int possibleCase;
	static int mod = 10007;
	static ArrayList<Integer>[] blocklistOfStudent;//인덱스 : 학생번호, 노드 : 블록들
	public static void main(String[] args) throws IOException {
		setData();
		dfs(1,0);
		System.out.println(possibleCase);
	}
	
	
	
	private static void dfs(int studentNo, int sum){
		//모든 학생을 끝까지 검사하는데
		//블록 사용을 안하는 경우와 사용하는 경우로 나누기.
		if(sum>H) return;
		if(studentNo==(N+1)){//모든 학생을 끝까지 다 조사한 경우
			if(sum==H) {
				possibleCase%=mod;
				possibleCase++;
			}
			return;
		}
		dfs(studentNo+1,sum);//안더하고 그냥 넘어가는 경우
		for(int block = 0; block<blocklistOfStudent[studentNo].size();block++){
			int currentBlockHeight = blocklistOfStudent[studentNo].get(block);
			int trySum = sum+currentBlockHeight;
			dfs(studentNo+1,trySum);
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
			for(int j=0,height;j<tokenSize;j++){
				height = Integer.parseInt(st.nextToken());
				blocklistOfStudent[i].add(height);
			}
		}
	}
}
