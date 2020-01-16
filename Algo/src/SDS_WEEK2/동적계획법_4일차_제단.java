package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 조건문 ? a:b; 
 * 조건문이 참일 때 a, 거짓이면 b
 * 
-1인 경우는 제단을 세워보는 경우고

0인경우는 딱 한가지의 경우 밖에 없고

제단이 있는 경우는 해당 높이의 전에서 오는 3가지의 경우만 따지면 된다.

즉

dp[i][j] : i에서 j로 가는 경우의 수 에서

dp[i][j] = dp[i-1][j-1] + dp[i-1][j] + dp[i-1][j+1] 가 된다.

또한 제단으 최대 높이는 N/2이고

이 문제에서 그냥 메모리를 잡아놓고 풀면 메모리 제한에 걸리기 떄문에

슬라이딩 윈도우를 쳐서 구하면 된다..
 */
public class 동적계획법_4일차_제단 {
	static final int MOD = 1000000007;
	static int N;
	static int[] altar;
	static long[][] dp = new long[2][5003];//2만 선언하는 이유는 슬라이딩 윈도우를 치기 위해. WindowSize = 2
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		altar = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) altar[i] = Integer.parseInt(st.nextToken());
		dp[0][0] = altar[1] == -1 ? 1 : altar[1] == 0 ? 1 : 0;//초기값 설정
		//-1이면 1이고 
		//-1이 아닐 때 0이면 1이고 아니면 0 
		
		for(int i=2;i<=N;i++){//1부터 본다
			if(altar[i]==-1) {//제단의 높이를 세워보는 경우
				dp[1][0] = (dp[0][0]+dp[0][1])%MOD;
				for(int j=1;j<=N/2;j++) {
					dp[1][j] = (dp[0][j-1]+dp[0][j]+dp[0][j+1])%MOD;
				}
			}
			else if (altar[i]==0) { //맨 끝 
				dp[1][0] = (dp[0][0]+dp[0][1])%MOD;
			}else { //해당 높이에 대해서만 확인 
				dp[1][altar[i]] = (dp[0][altar[i]-1]+dp[0][altar[i]]+dp[0][altar[i]+1])%MOD;
			}
			
			for(int j=0;j<=N/2;j++) { //슬라이딩 윈도우 
				dp[0][j] = dp[1][j];
				dp[1][j] = 0;
			}
		}
		System.out.println(dp[0][0]);
	} 
	
}
