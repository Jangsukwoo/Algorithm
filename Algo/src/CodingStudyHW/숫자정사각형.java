package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 숫자정사각형 {
	static int N,M;//행,열
	static int maxLength;
	static char[][] rectangle;
	public static void main(String[] args) throws IOException {
		setData();
		getMaxsquare();
		System.out.println((int)Math.pow((maxLength+1),2));
	}
	private static void getMaxsquare() {
		for(int row=0;row<N;row++){
			for(int col=0;col<M;col++){
				int length=1;
				while(checkSquare(row,col,length)) length++;
			}
		}
	}
	private static boolean checkSquare(int row, int col, int length){
		if(length>maxLength) {
			if(rangeCheck(row,col,length)){//영역 만족하면 꼭지점 확인
				char currentNumber = rectangle[row][col]; 
				if(currentNumber==rectangle[row+length][col]
						&& currentNumber==rectangle[row][col+length]
						&& currentNumber==rectangle[row+length][col+length]) {
					//네 꼭지점이 다 같은경우 maxLength 갱신
					maxLength = Math.max(maxLength,length);		
				}
				return true;
			}
		}
		return false;
	}
	private static boolean rangeCheck(int row, int col, int length) {
		if((row+length)<N && (col+length)<M) return true;//영역 내에있다.
		return false;
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				System.out.print(rectangle[row][col]);
			}
			System.out.println();
		}
	}
	private static void setData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		rectangle = new char[N][M];
		for(int row=0;row<N;row++) rectangle[row] = br.readLine().toCharArray();
		
	}
}
