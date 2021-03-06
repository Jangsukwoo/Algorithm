package CodingStudyHW;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 14:51~
 * 스도쿠 빈칸채우기
 * 가로, 세로 , 박스권 검사
 * 16:20
 */
public class 스도쿠 {
	static int[][] sudoku = new int[9][9];
	static int[][] areaNumber = new int[9][9];
	static ArrayList<ZeroPosition> zeroList =  new ArrayList<ZeroPosition>();
	static int zeroCount;
	static boolean find = false;
	static Box[] boxs = new Box[10];
	static class ZeroPosition{
		int row;
		int col;
		public ZeroPosition(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	static class Box{
		int row;
		int col;
		public Box(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
	}
	public static void main(String[] args) {
		setData();
		setAreaNumberMapAndBoxInformation();
		dfs(0,0);
	}

	private static void printSudokuMap() {
		for(int row=0;row<9;row++) {
			for(int col=0;col<9;col++) {
				System.out.print(sudoku[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void dfs(int idx, int cnt){
		if(find) return;
		if(cnt==zeroCount){//아래 for문에서 가능성을 조사하면서 유망한 애를 넣어왔는데 모든 제로 포지션에 대해 다 넣었으면 끝내버린다.
			find = true;
			printSudokuMap();
			return;
		}
		for(int num=1;num<=9;num++){//zero 위치에 1부터 9까지 하나씩 넣어볼건데
			if(find) return;
			ZeroPosition currentZeroPosition = zeroList.get(idx);//현재 빈칸에대해
			if(rowCheck(currentZeroPosition,num) && colCheck(currentZeroPosition,num) && boxCheck(currentZeroPosition,num)){
				//가로,세로,박스권 내에 없는 숫자면 한번 넣어본다.
				sudoku[currentZeroPosition.row][currentZeroPosition.col] = num;//넣음
				dfs(idx+1,cnt+1);
				sudoku[currentZeroPosition.row][currentZeroPosition.col] = 0;//0으로 원상복귀
			}
		}
	};

	private static boolean rowCheck(ZeroPosition currentZeroPosition, int num) {
		int[] numberCnt = new int[10];
		int currentRow = currentZeroPosition.row;
		for(int col=0;col<9;col++) numberCnt[sudoku[currentRow][col]]+=1;
		//검사했는데 0이면 이 숫자는 없는 숫자니까 return true;
		if(numberCnt[num]==0) return true;
		return false;
	}

	private static boolean colCheck(ZeroPosition currentZeroPosition, int num) {
		int[] numberCnt = new int[10];
		int currentCol = currentZeroPosition.col;
		for(int row=0;row<9;row++) numberCnt[sudoku[row][currentCol]]+=1;
		//검사했는데 0이면 이 숫자는 없는 숫자니까 return true;
		if(numberCnt[num]==0) return true;
		return false;
	}

	private static boolean boxCheck(ZeroPosition currentZeroPosition, int num) {
		int[] numberCnt = new int[10];
		int currentAreaNumber = areaNumber[currentZeroPosition.row][currentZeroPosition.col];
		int boxStartRow = boxs[currentAreaNumber].row;
		int boxStartCol = boxs[currentAreaNumber].col;
		for(int row = boxStartRow;row<(boxStartRow+3);row++){
			for(int col=boxStartCol;col<(boxStartCol+3);col++){
				numberCnt[sudoku[row][col]]+=1;
			}
		}
		//검사했는데 0이면 이 숫자는 없는 숫자니까 return true;
		if(numberCnt[num]==0) return true;
		return false;
	}
	private static void setData(){
		Scanner sc = new Scanner(System.in);
		for(int row=0;row<9;row++) {
			for(int col=0;col<9;col++) {
				sudoku[row][col] = sc.nextInt();
				if(sudoku[row][col]==0){
					zeroCount++;
					zeroList.add(new ZeroPosition(row, col));
				}
			}
		}
	}
	private static void setAreaNumberMapAndBoxInformation(){
		boolean nextRow = false;
		int startRow=0;
		int startCol=0;
		for(int number=1;number<=9;number++){
			int check = number%3;
			if(nextRow) {
				nextRow=false;
				startRow+=3;
				startCol=0;
			}
			boxs[number] = new Box(startRow,startCol);
			if(check==0) nextRow=true;
			for(int row=startRow;row<(startRow+3);row++){
				for(int col=startCol;col<(startCol+3);col++){
					areaNumber[row][col]=number;
				}
			}
			startCol+=3;
		}
	}
}
