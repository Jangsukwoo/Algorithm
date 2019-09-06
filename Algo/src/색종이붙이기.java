import java.util.Scanner;


//코드 출처 https://baelanche.tistory.com/176


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
				if(paperMap[row][col]==1) paperSize++;
			}

		}

		if(paperSize==0) {
			System.out.println("0");
			return;
		}
		dfs(0);

		if(minimum!=(Integer.MAX_VALUE/2)) System.out.println(minimum);
		else System.out.println("-1"); 

	}
	private static void dfs(int idx) {
		if(usePaper>minimum) return;
		if(attachPaperSize==paperSize){


			minimum = Math.min(usePaper,minimum);




			return;
		}

		for(int row=0;row<10;row++){
			for(int col=0;col<10;col++){ 


				if(paperMap[row][col]==1){


					for(int paper=1;paper<=5;paper++){
						if(paperCount[paper]<5) {

							if(attach(paper,row,col)){
								System.out.println("����");
								view();
								paperCount[paper]++;
								usePaper++;
								dfs(idx+1);      
								paperCount[paper]--;
								usePaper--;
								detach(paper,row,col);
								System.out.println("�E");
								view();
							}

						}
					}         
				}
			}
		}
	}
	private static void view() {
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