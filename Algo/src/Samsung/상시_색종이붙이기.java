package Samsung;

import java.util.Scanner;

/* 
 * 모든 1을 덮는데 필요한 종이의 최소 개수
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 0 0 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 0 0 0 0  

1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0

완벽하게 체화시키자 
 */
class startRC{
	int row;
	int col;
	public startRC(int r, int c) {
		row=r;
		col=c;
	}
}
public class 상시_색종이붙이기 {
	static int[][] paper;
	static int[] papersize = new int[6];
	static int[][] painted;
	//static int startR,startC;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		paper = new int[10][10];
		painted = new int[10][10];
		for(int i=1;i<=5;i++) papersize[i] = 5;
		for(int row=0;row<10;row++)
			for(int col=0;col<10;col++) paper[row][col] = sc.nextInt();
		dfs(0);
		if(min==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(min);
	}
	private static void dfs(int use){
		if(use>=min) return;
		if(paperCheck()){
			view();
			//System.out.println("사용한종이"+use);
			min = Math.min(min,use);
			return ;
		}
		startRC sRC = getStartRC();
		int startR = 0;
		int startC = 0;
		if(sRC != null) {
			startR = sRC.row;
			startC = sRC.col;
		}
		for(int p=5;p>=1;p--){
			if(papersize[p]>0){
				if(attachPossible(startR,startC,p)){
					attach(startR,startC,p);
					//System.out.println("붙임");
					//view();		
					papersize[p]-=1;
					dfs(use+1);
					//System.out.println("뗌");
					detach(startR,startC,p);
					//view();
					papersize[p]+=1;
				}
			}
		}
	}

private static void view() {
		for(int row=0;row<10;row++) {
			for(int col=0;col<10;col++) {
				System.out.print(painted[row][col]+" ");
			}
			System.out.print("\n");
		}
		System.out.println();
	}
	private static startRC getStartRC() {
		for(int row=0;row<10;row++) {
			for(int col=0;col<10;col++) {
				if(paper[row][col]==1 && painted[row][col]==0) {
					return new startRC(row,col);
				}
			}
		}
		return null;
	}
	private static boolean attachPossible(int sR, int sC, int p){
		if((sR+p)>10 || (sC+p)>10) return false;
		for(int row = sR;row<(sR+p);row++) {
			for(int col=sC;col<(sC+p);col++){
				if(paper[row][col]!=1) return false;
				else { //1인데 누군가 방문한 상태면
					if(painted[row][col]!=0) return false;
				}
			}
		}
		return true;
	}
	private static void attach(int sR, int sC, int p) {
		for(int row = sR;row<(sR+p);row++) {
			for(int col=sC;col<(sC+p);col++){
				painted[row][col]=p;
			}
		}
	}
	private static void detach(int sR, int sC, int p) {
		for(int row = sR;row<(sR+p);row++) {
			for(int col=sC;col<(sC+p);col++){
				painted[row][col]=0;
			}
		}
	}
	

	private static boolean paperCheck() {
		for(int row=0;row<10;row++) {
			for(int col=0;col<10;col++) {
				if(paper[row][col]==1 && painted[row][col]==0) return false;
			}
		}
		return true;
	}
}
