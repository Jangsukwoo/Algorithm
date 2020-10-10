package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 러시아국기같은깃발 {
	static int N,M;
	static StringTokenizer st;
	static int answer;
	static char[][] colors;
	static int[] pickLine;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			answer = getMinimumPaintCount();
			System.out.println("#"+testcase+" "+answer);
		}
	}
	private static int getMinimumPaintCount() {
		answer = Integer.MAX_VALUE;
		nCr(0,0);
		return answer;
	}
	private static void nCr(int idx, int depth) {
		if(depth==3) {
			int paint = paint();
			answer = Math.min(answer,paint);
			return;
		}
		for(int i=idx;i<N;i++) {
			pickLine[depth] = i;
			nCr(i+1, depth+1);
		}
	}
	private static int paint() {
		int blueLine = pickLine[1];
		int redLine = pickLine[2];
		int paint = 0;
		//흰색
		for(int row=0;row<blueLine;row++) {
			for(int col=0;col<M;col++) {
				if(colors[row][col]!='W') paint++;
			}
		}
		
		//파란색
		for(int row=blueLine;row<redLine;row++) {
			for(int col=0;col<M;col++) {
				if(colors[row][col]!='B') paint++;
			}
		}
		//빨간색
		for(int row=redLine;row<N;row++) {
			for(int col=0;col<M;col++) {
				if(colors[row][col]!='R') paint++;
			}
		}
		return paint;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pickLine = new int[3];
		colors = new char[N][M];
		for(int row=0;row<N;row++) {
			String readLine= br.readLine();
			for(int col=0;col<M;col++) {
				colors[row][col] = readLine.charAt(col);
			}
		}
	}	
}
