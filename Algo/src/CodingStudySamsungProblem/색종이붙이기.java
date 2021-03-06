package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 10 by 10 종이가 있고
 * 1이라고 적힌 부분을 덮을 수 있는 최소의 색종이 개수 
 */
public class 색종이붙이기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] originalPaper = new int[10][10];
	static int[] colorPaper = new int[6];//색종이 분량
	static StringTokenizer st;
	static int answer=987654321;
	public static void main(String[] args) throws IOException {
		setData();
		bruteForce();
		if(answer==987654321) System.out.println("-1");
		else System.out.println(answer);
	}
	private static void bruteForce(){
		boolean[][] testPaper = new boolean[10][10];
		dfs(0,testPaper);
	}
	private static void dfs(int cnt, boolean[][] testPaper){
		if(endCheck(testPaper)){
			//다 붙인경우 갱신
			answer = Math.min(cnt,answer);
			return;
		}
		else {
			//아직 시도해봐야하는 경우
			boolean[][] copyPaper = copyPaper(testPaper);
			int sr=0;
			int sc=0;
			boolean find=false;
			for(int row=0;row<10;row++) {
				for(int col=0;col<10;col++) {
					if(originalPaper[row][col]==1 && copyPaper[row][col]==false) {
						//종이를 붙일 수 있는 자리면서 아직 안붙였다면 
						sr = row;
						sc = col;
						find = true;
						break;
					}
				}
				if(find) break;
			}
			if(find) {
				for(int paper=1;paper<=5;paper++){//종이 하나씩 대본다.
					if(rangeCheck(sr,sc,paper) && attachCheck(sr,sc,paper,copyPaper) && colorPaper[paper]>0){
						colorPaper[paper]--;
						attach(sr,sc,paper,copyPaper);
						dfs(cnt+1,copyPaper);
						colorPaper[paper]++;
						detach(sr,sc,paper,copyPaper);
					}
				}
			}
		}
	}

	private static boolean endCheck(boolean[][] copyPaper) {
		for(int row=0;row<10;row++) {
			for(int col=0;col<10;col++) {
				if(originalPaper[row][col]==1 && copyPaper[row][col]==false) return false;
				//종이를 붙일 수 있는 자리인데 아직 안붙어있으면 false
			}
		}
		return true;
	}
	private static void attach(int sr, int sc, int paper, boolean[][] copyPaper) {
		for(int row=sr;row<(sr+paper);row++){
			for(int col=sc;col<(sc+paper);col++){
				copyPaper[row][col]=true;
			}
		}
	}
	private static void detach(int sr, int sc, int paper, boolean[][] copyPaper) {
		for(int row=sr;row<(sr+paper);row++){
			for(int col=sc;col<(sc+paper);col++){
				copyPaper[row][col]=false;
			}
		}
	}
	private static boolean rangeCheck(int row, int col, int paper) {
		if(row+paper<=10 && col+paper<=10) return true;
		return false;
	}
	private static boolean attachCheck(int sr, int sc, int paper, boolean[][] copyPaper) {
		for(int row=sr;row<(sr+paper);row++){
			for(int col=sc;col<(sc+paper);col++){
				if(originalPaper[row][col]!=1 || copyPaper[row][col]==true) {
					return false;
				}
				//종이를 붙일 수 있는 자리가 아니거나 이미 붙여진 자리면 false
			}
		}
		return true;
	}
	private static boolean[][] copyPaper(boolean[][] testPaper) {
		boolean[][] copyPaper = new boolean[10][10];
		for(int row=0;row<10;row++) 
			for(int col=0;col<10;col++) 
				copyPaper[row][col] = testPaper[row][col];
		return copyPaper;
	}
	private static void setData() throws IOException {
		for(int row=0;row<10;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<10;col++) {
				originalPaper[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1;i<=5;i++) colorPaper[i]=5;//색종이 충전
	}
}
