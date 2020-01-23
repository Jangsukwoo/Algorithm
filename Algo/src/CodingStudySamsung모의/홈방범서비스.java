package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 게리맨더링..
 * 마름모 영역 표현하기!!!
 * 
 * 운영비용 : K^2 + (K-1)^2
 * 보안회사의 이익 : 서비스제공받는 집들을 통해 얻는 수익 - 운영비용
 * 
 * 맵에서 가능한 모든 마름모 영역에 대해 최대이익 구하기 
 * 
 */
public class 홈방범서비스 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] city;
	static int maxProfit;
	static int N,M;//맵사이즈,집당 비용
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			System.out.println("#"+testcase+" ");
		}
	}
	private static void setData() throws IOException {
		maxProfit = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		city = new int[N][N];
		
	}
}
