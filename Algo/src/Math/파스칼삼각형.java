package Math;
/*
 * 이항정리
 */
import java.util.Scanner;
public class 파스칼삼각형 {
	public static void main(String[] args) {
		//입력
		Scanner sc = new Scanner(System.in);
		int sum=0;
		int R= sc.nextInt();
		int C = sc.nextInt();
		int W = sc.nextInt();
		int N = R+W-1;
		int[][] pascal = new int[N][N];
		pascal[0][0] = 1;
		//처리
		for(int row =1; row<N;row++) {
			for(int col=0; col<=row;col++) {
				if(col==0) pascal[row][col] =1;
				else if(row==col) pascal[row][col]=1;
				else pascal[row][col] = pascal[row-1][col-1]+pascal[row-1][col];
			}
		}
		for(int row=0;row<W;row++) {
			for(int col=0;col<=row;col++) {
				sum+=pascal[R-1+row][C-1+col];
			}
		}
		//출력
		System.out.println(sum);
	}
}