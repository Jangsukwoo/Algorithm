package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 1<<3 은 뒤에 0이 세개
 * 
 * YNN을 비트로 표현
 * 
 * 특정 비트값 확인하기
 * num & (1<<3) 3번재 비트값 
 * 특정 비트값 1만들기
 * num | (1<<3) 3번째 비트값 1
 * 특정 비트 토글시키기
 * num^(1<<3) 3번쨰 비트값 토글
 */
public class 동적계획법_5일차_발전소 {
	static final int INF = 987654321;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static String onoff;
	static int P;//적어도 P개가 고장나있지 않도록
	static int[][] adjMatrix;
	static int yesCount;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		dp = new int[1<<N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				adjMatrix[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		onoff = br.readLine();
		int bit =0; //Y,N에 대한 비트 -> Y=1, N=0
		for(int i=0;i<onoff.length();i++){
			if(onoff.charAt(i)=='Y') {
				bit |= (1<<i);
				yesCount++;
			}
		}
		
		P = Integer.parseInt(br.readLine());
		for(int i=0;i<(1<<N);i++) dp[i] = INF;
		if(yesCount==0) {
			if(P==0) System.out.println("0");
			else System.out.println("-1");
		} else if( yesCount>=P) System.out.println("0");
		else {
			int result = dfs(yesCount,bit);
			System.out.println(result);
		}
		
	}
	private static int dfs(int depth, int bit) {
		if(depth==P) return 0;//yesCount를 P개만큼 찾았으면 끝내고
		if(dp[bit]!=INF) return dp[bit];//기존에 dp값을 구했으면 그대로 리턴
		//dp에 메모이제이션 해야하는 경우
		for(int i=0;i<N;i++) {
			if((bit&(1<<i))>0){//0보다 크다는건 무슨뜻?
				for(int j=0;j<N;j++) {//인접조사
					if(i==j) continue;
					if((bit&(1<<j))==0){//0바꿀 수 있다면
						dp[bit] = Math.min(dp[bit],dfs(depth+1,bit|(1<<j))+adjMatrix[i][j]);
					}
				}
			}
		}
		return dp[bit];
	}	
}
