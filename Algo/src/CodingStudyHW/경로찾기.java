package CodingStudyHW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 가중치 없는 방향그래프 G
 * 모든 경로에 대해서 i->j로 가는 경로가 있는지 없는지 구하기
 * 정점의 개수 N
 * 1<=N<=100
 * 
 * 인접행렬이 주어지고 
 * i->j로 가는 간선이 존재하는 경우 1, 없는 경우 0을 뜻한다.
 * 
 * 모든 경로를 확인하고싶다 -> 플로이드 와샬 
 */
public class 경로찾기 {
	static int N;
	static int[][] adjMatrix;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		adjMatrix = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				adjMatrix[row][col] = Integer.parseInt(st.nextToken());
			}
		}//입력
		floydWarshall();
		
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				bw.write(adjMatrix[row][col]+" ");
			}
			bw.write("\n");
		}//입력
		bw.flush();
		bw.close();
	}
	private static void floydWarshall() {
		for(int k=0;k<N;k++){//플로이드 와샬 O(n^3)
			for(int row=0;row<N;row++) {
				for(int col=0;col<N;col++) {
					int adjCheck = adjMatrix[row][k] + adjMatrix[k][col];
					if(adjCheck==2) adjMatrix[row][col]=1;
				}
			}
		}
	}	
}
