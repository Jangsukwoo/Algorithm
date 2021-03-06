package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기1 {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] house;
	static int possibleWay;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		dfs(0,1,1);
		System.out.println(possibleWay);
	}
	private static void dfs(int hr, int hc, int state) {
		if(hr == (N-1) && hc == (N-1)) {
			possibleWay++;
			return;
		}
		switch (state) {
		
		case 1: //가로로 놓여있는 경우 
		
			if(rightCheck(hr,hc)) dfs(hr,hc+1,1);
			if(rightAndDownCheck(hr,hc)) dfs(hr+1,hc+1,3);
			
			break;
		case 2: //세로로 놓여있는 경우
			
			if(downCheck(hr,hc)) dfs(hr+1,hc,2);
			if(rightAndDownCheck(hr,hc)) dfs(hr+1,hc+1,3);
			
			break;
		case 3: //대각선으로 놓여있는 경우
			
			if(rightCheck(hr,hc)) dfs(hr,hc+1,1);
			if(downCheck(hr,hc)) dfs(hr+1,hc,2);
			if(rightAndDownCheck(hr,hc)) dfs(hr+1,hc+1,3);
			
			break;
		}
	}
	private static boolean downCheck(int hr, int hc) {
		if(check(hr+1, hc)) return true;
		return false;
	}
	private static boolean rightAndDownCheck(int hr, int hc) {
		if(check(hr+1,hc+1) && check(hr+1, hc) && check(hr, hc+1)) return true;
		return false;
	}
	private static boolean rightCheck(int hr, int hc) {
		if(check(hr,hc+1)) return true;
		return false;
	}
	private static boolean check(int nr, int nc) {
		if(nr>=0 && nr <N && nc>=0 && nc<N && house[nr][nc]==0) return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		house = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				house[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
