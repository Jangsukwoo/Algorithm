package CodingStudyHW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/*
 * 계단이 300개
 * 계단은 한번에 한 계단 또는 두 계단 씩 오를 수 있다.
 * 단, 연속된 세개의 계단을 밟는것도 안된다.
 * 이 계단을 끝까지 오를 수 있는 총 점수의 최대값
 * 
 * 밟는다,안밟는다 -> 2의 300승-> 완탐으로 절대불가
 * 
 * 
 * D1 = A1
 * D2 = A1+A2
 * 
 * D3부턴 어떻게????
 * 
 * 바로 앞에 계단 안밟았다 , 바로 앞앞 계단 안밟았다.
 * D3 = A[3]+A[1] , A[3] +A[2] ->1,2중에 큰거 밟아야좋다
 * 
 * D4 = A[4] + D[2] , A[3]+D[1]
 * 
 * ->이걸 제너럴하게 나타내면?
 * D[i] =  max(D[i-2]+a[i], D[i-3]+a[i-1])
 * 
 * 1. memo 배열 int[]
 * 2. 초기화 d1 = a1, d2 = a1+a2
 * 3. 점화식 memo[i] =  max(memo[i-2]+a[i], memo[i-3]+a[i-1]+a[i]1)
 */
public class 계단오르기 {
	static int N;
	static int[] stairs;
	static int[] memo;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		if(N==1) System.out.println(stairs[1]);
		else output();
	}
	private static void output() throws IOException {
		memo[1] = stairs[1];
		memo[2] = stairs[1]+stairs[2];
		for(int i=3;i<=N;i++) {
			memo[i] = Math.max(stairs[i]+memo[i-2],stairs[i]+stairs[i-1]+memo[i-3]);
		}
		int answer = memo[N];
		System.out.println(answer);		
	}
	private static void input() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		stairs = new int[N+1];
		memo = new int[N+1];
		for(int i=1;i<=N;i++) stairs[i] = Integer.parseInt(br.readLine());
	}
}
