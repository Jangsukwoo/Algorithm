package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수삼각형 {
	static int[][] triangle;
	static int[][] memo;
	static int max;
	static int N;
	public static void main(String[] args) throws IOException {
		setData();
		memoization();
	}

	private static void memoization() {
		for(int row=1;row<N;row++) {
			for(int col=0;col<=row;col++) {
				if(col==0) memo[row][col]=triangle[row][col]+memo[row-1][col];
				else if(col>0&&col<row){
					memo[row][col] = Math.max(memo[row-1][col-1]+triangle[row][col],memo[row-1][col]+triangle[row][col]);
				}
				else if(col==row) memo[row][col] =memo[row-1][col-1]+triangle[row][col];
			}
		}	
		for(int col=0;col<N;col++) if(memo[N-1][col]>max) max = memo[N-1][col];
		System.out.print(max);
	}

	private static void setData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		triangle = new int[N][N];
		memo = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<=row;col++) {
				triangle[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		memo[0][0] = triangle[0][0];
		
	}
}
