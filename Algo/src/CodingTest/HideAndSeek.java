package CodingTest;
/*
 * line
 */
import java.util.Scanner;

public class HideAndSeek {
	static long[][] memoMap;
	static int rowSize,colSize,connieRow,connieCol;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		rowSize = (sc.nextInt()+1);
		colSize = (sc.nextInt()+1);
		memoMap = new long[rowSize][colSize];
		connieRow = sc.nextInt();
		connieCol = sc.nextInt();
	
		writeMap();
		
		if(rangeCheck(connieRow,connieCol)){//코니가 영역 안쪽에 있으면 
			System.out.println(connieRow+connieCol);
			System.out.println(memoMap[connieRow][connieCol]);//경우의 수
		}else {//영역 밖에있으면
			System.out.println("fail");
		}
	}
	private static void writeMap() {
		for(int row=0;row<rowSize;row++) memoMap[row][0]=1;
		for(int col=0;col<colSize;col++) memoMap[0][col]=1;
		for(int row=1;row<rowSize;row++) {
			for(int col=1;col<colSize;col++){
				memoMap[row][col] = memoMap[row][col-1]+memoMap[row-1][col];
			}
		}
	}
	private static boolean rangeCheck(int connieRow, int connieCol) {
		if(connieRow>=0 && connieRow<rowSize && connieCol>=0 && connieCol<colSize) return true;
		return false;
	}
}
