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
				if(paperMap[row][col]==1) paperSize++;
			}

		}

		if(paperSize==0) {
			System.out.println("0");
			return;
		}
		dfs(0);

		if(minimum!=(Integer.MAX_VALUE/2)) System.out.println(minimum);//최소값에 변동이 있는 경우만
		else System.out.println("-1"); //아니면 0

	}
	private static void dfs(int idx) {
		if(usePaper>minimum) return;
		if(attachPaperSize==paperSize){//다 붙인 경우
//			int min=0;
//			for(int i=1;i<=5;i++) min+=paperCount[i];
			minimum = Math.min(usePaper,minimum);
//			System.out.println("사이즈같은경우");
//			view();
//			for(int i=1;i<=5;i++) System.out.print("사이즈"+i+"갯수"+paperCount[i]+" ");
//			System.out.println("미니멈값"+minimum);
			return;
		}

		for(int row=0;row<10;row++){
			for(int col=0;col<10;col++){ //맵검사


				if(paperMap[row][col]==1){//색종이면


					for(int paper=1;paper<=5;paper++){
						if(paperCount[paper]<5) {//아직 5개 미만이고

							if(attach(paper,row,col)){//붙였을때 성공했으면 
								System.out.println("붙임");
								view();
								paperCount[paper]++;
								usePaper++;
								dfs(idx+1);      
								paperCount[paper]--;
								usePaper--;
								detach(paper,row,col);
								System.out.println("똄");
								view();
							}

						}//5개 아직 다 못썼으면 
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
				paperMap[row][col]=1;//다시 1로
				attachPaperSize--;
			}
		}

	}
	private static boolean attach(int paper,int curRow,int curCol) {//붙여보기
		int endRow = curRow+paper;
		int endCol = curCol+paper;
		if(rangeCheck(endRow,endCol)) {//영역안쪽이고
			for(int row=curRow;row<endRow;row++) {
				for(int col=curCol;col<endCol;col++) {
					if(paperMap[row][col]==0){//0이 있으면
						return false;//끝내버림
					}
				}
			}
			//안 끝났다면 붙일 수 있는 것이므로
			//붙임
			for(int row=curRow;row<endRow;row++) {
				for(int col=curCol;col<endCol;col++) {
					paperMap[row][col]=0;//0으로 만들어버림
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