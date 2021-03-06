package BAEKJOON;
import java.util.Scanner;


public class 색종이붙이기 {
	static int[][] paperMap = new int[10][10];
	static int[] paperCount = new int[6];
	static int minimum = (Integer.MAX_VALUE/2);
	static int paperSize;
	static int usePaper;
	static int attachPaperSize;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int row=0;row<10;row++) {
			for(int col=0;col<10;col++) {
				paperMap[row][col] = sc.nextInt();
				if(paperMap[row][col]==1) paperSize++; //1인것들 일단 저장
			}

		}

		if(paperSize==0) {
			System.out.println("0");
			return;
		}
		dfs();

		if(minimum!=(Integer.MAX_VALUE/2)) System.out.println(minimum);
		else System.out.println("-1"); 

	}
	private static void dfs() {
		if(usePaper>minimum) return;
		//백트랙킹: 장수가 이미 알고있는 장수보다 크면 끝내버림 
		if(attachPaperSize==paperSize){//1로 모두 채웠으면


			minimum = Math.min(usePaper,minimum);//최소값 비교 후 갱신
			return;
		}

		for(int row=0;row<10;row++){//처음 좌표부터 확인할거고
			for(int col=0;col<10;col++){ 


				if(paperMap[row][col]==1){//1이면


					for(int paper=1;paper<=5;paper++){//1부터 5까지 가능한것중에
						if(paperCount[paper]<5) {

							if(attach(paper,row,col)){//붙여본다. 못붙이면 false
								System.out.println("붙임");
								view();//확인 메소드
								paperCount[paper]++;
								usePaper++;
								dfs();      
								//원상복구
								paperCount[paper]--;
								usePaper--;
								detach(paper,row,col);
								System.out.println("뗌");
								view();
							}

						}
					}         
				}
			}
		}
	}
	private static void view() {//디버깅용
		for(int row=0;row<10;row++) {
			for(int col=0;col<10;col++) {
				System.out.print(paperMap[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void detach(int paper, int curRow, int curCol) {
		int endRow = curRow+paper;
		int endCol = curCol+paper;
		for(int row=curRow;row<endRow;row++) {
			for(int col=curCol;col<endCol;col++) {
				paperMap[row][col]=1;
				attachPaperSize--;
			}
		}

	}
	private static boolean attach(int paper,int curRow,int curCol) {
		int endRow = curRow+paper;
		int endCol = curCol+paper;
		if(rangeCheck(endRow,endCol)) {
			for(int row=curRow;row<endRow;row++) {
				for(int col=curCol;col<endCol;col++) {
					if(paperMap[row][col]==0){
						return false;
					}
				}
			}


			for(int row=curRow;row<endRow;row++) {
				for(int col=curCol;col<endCol;col++) {
					paperMap[row][col]=0;
					attachPaperSize++;
				}
			}
			return true;
		}

		else return false;
	}


	private static boolean rangeCheck(int endRow, int endCol) {
		if(endRow<=10 && endCol<=10) return true;
		return false;
	}


}