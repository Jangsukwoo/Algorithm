package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분수열의합 {
	static int N;
	static int[] series;
	static int[] pick;
	static int answer=0;
	static boolean[] visit = new boolean[2000001];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		nCrAll();
		getAnswer();
		System.out.println(answer);
	}
	private static void getAnswer() {
		for(int i=1;i<visit.length;i++) {
			if(visit[i]==false) {
				answer = i;
				return;
			}
		}
	}
	private static void nCrAll() {
		for(int r=1;r<=N;r++){
			pick = new int[r];
			nCr(0,0,r);
		}
	}
	private static void nCr(int idx, int depth, int r) {
		if(depth==r){
			int sum=0;
			for(int i=0;i<r;i++) sum+=series[pick[i]];
			visit[sum] = true;
			return;
		}
		for(int i=idx;i<N;i++) {
			pick[depth] = i;
			nCr(i+1, depth+1, r);
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		series = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) series[i] = Integer.parseInt(st.nextToken());
	}
}
