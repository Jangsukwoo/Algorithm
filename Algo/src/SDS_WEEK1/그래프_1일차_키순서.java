package SDS_WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 플로이드 와샬로 풀기
 */

public class 그래프_1일차_키순서 {
	static int N,M;
	static int[] knowPeople;
	static int[][] adjMatrix;
	static int INF = 987654321;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjMatrix = new int[N+1][N+1];
		knowPeople = new int[N+1];
		initialize();
		
		for(int i=0;i<M;i++) {
			line = br.readLine();
			st = new StringTokenizer(line);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = 1;
		}
		
		floydWashall();
		setKnowPeople();
		getAnswer();
		System.out.println(cnt);
	}

	private static void getAnswer() {
		for(int i=1;i<=N;i++) {
			if(knowPeople[i]==N-1) cnt++;
		}
	}

	private static void setKnowPeople() {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(adjMatrix[i][j]<INF) {
					knowPeople[i]++;
					knowPeople[j]++;
				}
			}
		}
	}

	private static void floydWashall() {
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					adjMatrix[i][j] = Math.min(adjMatrix[i][k]+adjMatrix[k][j], adjMatrix[i][j]);
				}
			}
		}
	}

	private static void initialize() {
		for(int row=1;row<=N;row++)
			for(int col=1;col<=N;col++) adjMatrix[row][col] = INF;
	}

}
