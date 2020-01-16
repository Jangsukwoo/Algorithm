package SDS_WEEK2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * DP
 * 1. DP배열
 * 2. 초기화
 * 3. 점화식
 * 
 * -> DP배열을 무엇으로 잡을 것인가?
 * -> 기준을 비용으로 둔다.
 * -> 비용 i를 들였을 때 얻을 수 있는 최대 메모리양
 * 
 * ->아직 잘 이해 안됐음..
 */
public class 동적계획법_4일차_앱 {
	static int N,M;//앱의수, 필요한 메모리 1<=N<=100, 1<=M<=10000000
	static App[] apps;
	static int[] dp;
	static int sum;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static class App{
		int id;
		int memory;
		int cost; //0<=C<=100
		public App(int i,int m,int c){
			id = i;
			memory = m;
			cost = c;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		getCostForMemory();
	}

	private static void setData() throws IOException {
		StringTokenizer st;
		StringTokenizer st2;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		apps = new App[N];
		st = new StringTokenizer(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		for(int i=0,m,c;i<N;i++) {
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st2.nextToken());
			sum+=c;//최대비용
			apps[i] = new App(i, m, c);
		}
	}

	private static void getCostForMemory() {
		//메모리 X Byte만큼 확보하기위해 최소의 비용으로 앱을 비활성화 해야한다.
		//비용 1에 대한 최대 메모리양을 메모이제이션한다.
		//즉, dp i 배열은 i만큼 비용을 들였을 때 최대 메모리 
		dp = new int[sum+1];
		for(int i=0;i<N;i++){//각 앱에 대해서
			
			for(int j=sum;j>=apps[i].cost;j--){
				dp[j] = Math.max(dp[j],dp[j-apps[i].cost]+apps[i].memory);
			}

		}
		int m;
		for(m=0;m<sum&& dp[m]<M;m++);
		System.out.println(m);
	}

}
