package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 행렬의 개수 1<= N <=500
 * NxM 행렬과 MxK 행렬을 곱할 때 연산의 수는 총 NxMxK임 
 * 행렬 N개를 곱하는데 필요한 곱셈 연산의 수는 행렬을 곱하는 순서에 따라 달라지게 된다.
 * 
 * A: 5 3
 * B: 3 2
 * C: 2 6 일 경우
 * ABC를 곱하는 경우를 생각해 봤을 때
 * 
 * (AB)C 는 90
 * A(BC) 는 126
 * 
 * ->교환은 안되지만 결합은 된다.같은 결과값을 내주지만 연산 횟수는 다르다.
 * 
 * 행렬 N개의 크기가 주어 졌을 때 
 * 모든 행렬을 곱하는 데 필요한 곱셈 연산의 횟수의 최솟값은?
 * 
 * 입력으로 주어진 행렬의 순서를 바꾸면 안된다.
 * 
 * 출력값은 최악인 경우에도 2^31-1보다 크지않다.
 * 초기값, 점화식 필요
 * 
 * dp[i][j] 뜻 : i행렬부터 j행렬까지 곱했을 때 필요한 최소 연산 회수
 * 
 * D[1][1] = 0;
 * D[2][2] = 0;
 * 
 * diagonal : 대각선
 */
public class 동적계획법_3일차_행렬곱셈순서 {
	static int N;
	static int[][] dp;//i부터 j까지 행렬을 곱했을 때 필요한 최소 연산 횟수 
	static int[][] rc;
	static int INF = 987654321;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		input();
		output();
	}

	private static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][N+1];
		rc = new int[N+1][2];
		for(int i=1,r,c;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			rc[i][0] = r;
			rc[i][1] = c;
		}
	}
	private static void output() throws IOException {	
		int m,v;
		for(int i=1;i<N;i++) {
			for(int j=1,from,to;j<N;j++) {
				from = j;
				to = j+i;
				if(to>N) break;
				m = 1<<30;
				for(int k=from+1;k<=to;k++) {
					v = dp[from][k-1]+dp[k][to]+rc[from][0]*rc[k][0]*rc[to][1];
					if(v<m) m=v;
				}
				dp[from][to] = m;
			}
		}
		System.out.println(dp[1][N]);
	}
}
