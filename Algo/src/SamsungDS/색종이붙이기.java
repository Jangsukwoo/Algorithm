package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1이 적힌 칸을 모두 덮어야 하는데 필요한 색종이의 최소 개수
 */
public class 색종이붙이기 {
	static int[][] paper;
	static BufferedReader br;
	static StringTokenizer st;
	static int possible = Integer.MAX_VALUE;
	static int[] colorPaper;
	public static void main(String[] args) throws IOException {
		setData();
		dfs(0);
		if(possible==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(possible);
	}

	private static void dfs(int use) {
		//붙여야할 곳이 있는지에 대한 flag 
		boolean done = true;
	
		//붙여야할 좌표
		int findR = 0;
		int findC = 0;
	
		//붙여야할 곳 찾기
		for(int row=0;row<10;row++) {
			for(int col=0;col<10;col++) {
				if(paper[row][col]==1) {
					done = false;
					findR = row;
					findC = col;
					break;
				}
			}
			if(!done) break;
		}
		
		if(!done) { //안끝났다면 붙여야함
			for(int length=5;length>=1;length--) { //종이 하나씩 대보는데
				if(colorPaper[length]!=0){//종이가 있다면
					if(attachPossible(findR,findC,length)){ //가능한지?
						
						colorPaper[length]--; //종이 사용
						attach(findR,findC,length); //붙이기
						
						dfs(use+1); 
						
						detach(findR,findC,length); //종이 사용 취소
						colorPaper[length]++; //떼기
					}
				}
			}
		}
		else possible = Math.min(use,possible); //이미 더이상 붙일 곳이 없다면 이 시점에서 갱신
		
		
	}
	private static void detach(int cr, int cc, int length) {
		for(int row=cr;row<(cr+length);row++) {
			for(int col=cc;col<(cc+length);col++) {
				paper[row][col]=1;
			}
		}
	}
	private static void attach(int cr, int cc, int length) {
		for(int row=cr;row<(cr+length);row++) {
			for(int col=cc;col<(cc+length);col++) {
				paper[row][col]=0;
			}
		}
	}
	private static boolean attachPossible(int cr, int cc, int length){
		if((cr+length)>10 || (cc+length)>10) return false;
		for(int row=cr;row<(cr+length);row++) {
			for(int col=cc;col<(cc+length);col++) {
				if(paper[row][col]==0) return false;
			}
		}
		return true;
	}
	private static void setData() throws IOException {
		paper = new int[10][10];
		br = new BufferedReader(new InputStreamReader(System.in));
		colorPaper = new int[6];
		Arrays.fill(colorPaper,5);//5장씩 충전
		for(int row=0;row<10;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<10;col++) {
				paper[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
