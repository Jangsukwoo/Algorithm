package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 
 * 어떤 전구를 찍어서 색깔을 바꿀  때
 * 인접한 같은 색의 전구들은 모조리 다 같은 색으로 바뀐다.
 * 
 * dp배열,초기값,점화식
 * 
 * 이 문제에서 dp배열을 뭐로 잡을 것인가?
 * 전구색을 같게 하는데 드는 횟수 
 * 
 * 1 2 2         3 3 4 
 * 로 체크해도 가능한게 맞냐?
 * 
 * 아 직 이 해 안 됐 음~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
public class 동적계획법_4일차_전구 {
	static final int INF = 987654321;
	static int N,K; //1<=N<=100, 1<=K<=20
	static int[] light;
	static int[][] dp;//i에서 j까지 같게 하는데 드는 최소 횟수 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		setData();
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//전구 수
		K = Integer.parseInt(st.nextToken());//전구가 표현할 수 있는 색의 수 
		light = new int[N+1];
		dp = new int[N+1][N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) light[i] = Integer.parseInt(st.nextToken());
		for(int i=1;i<=N;i++) for(int j=i;j<=N;j++) dp[i][j] = i==j ? 0 : INF;
		
		for(int l=2;l<=N;l++){//길이 
			for(int i=1;i<=N-l+1;i++) {
				int j = i+l-1;
				for(int k=i;k<j;k++){
					int c = dp[i][k]+dp[k+1][j]+(light[i]==light[k+1]?0:1);
					dp[i][j] = Math.min(dp[i][j],c);
				}
			}
		}
		System.out.println(dp[1][N]);
	}
}
