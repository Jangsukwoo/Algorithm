package Samsung;

import java.util.Scanner;

/*
 *시작 956
 *오래 고민하다가 그냥 하드코딩함.. 
 *끝 1111
 *걸린시간 1시간 15분
 */
public class 기출_테트로미노 {
	static int N,M;// N:세로, M:가로
	static int[][] paper;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int max;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		paper = new int[N][M];
		for(int row=0;row<N;row++)
			for(int col=0;col<M;col++) paper[row][col] = sc.nextInt();
		setTetromino();
		System.out.println(max);
	}
	private static void setTetromino() {
		int sum=0;
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++){
				if(col+3<M){ // ㅡ
					sum=0;
					sum += paper[row][col]+paper[row][col+1]+paper[row][col+2]+paper[row][col+3];
					max = Math.max(sum,max);
				}
				if(row+3<N) { // ㅣ
					sum=0;
					sum += paper[row][col]+paper[row+1][col]+paper[row+2][col]+paper[row+3][col];	
					max = Math.max(sum,max);
				}
				if((row+1)<N && (col+1)<M){ // ㅁ
					sum=0;
					sum += paper[row][col]+paper[row+1][col]+paper[row][col+1]+paper[row+1][col+1];
					max = Math.max(sum,max);
				}
				if((row+2)<N && (col+1)<M) { //ㄴ 시계방향
					sum=0;
					sum+= paper[row][col]+paper[row+1][col]+paper[row+2][col]+paper[row+2][col+1];
					max = Math.max(sum,max);
				}
				if((row+1)<N && (col+2)<M) {
					sum=0;
					sum+= paper[row][col]+paper[row+1][col]+paper[row][col+1]+paper[row][col+2];
					max = Math.max(sum,max);			
				}
				if((row+2)<N && (col-1)>=0) {
					sum=0;
					sum+= paper[row][col]+paper[row+1][col]+paper[row+2][col]+paper[row][col-1];
					max = Math.max(sum,max);			
				}
				if((row-1)>=0 && (col-2)>=0) {
					sum=0;
					sum+= paper[row][col]+paper[row-1][col]+paper[row][col-1]+paper[row][col-2];
					max = Math.max(sum,max);			
				}
				if((row-2)>=0 && (col-1)>=0){//ㄱ 시계방향
					sum=0;
					sum+= paper[row][col]+paper[row-1][col]+paper[row-2][col]+paper[row][col-1];
					max = Math.max(sum,max);			
				}
				if((row-1)>=0 && (col+2)<M){
					sum=0;
					sum+= paper[row][col]+paper[row-1][col]+paper[row][col+1]+paper[row][col+2];
					max = Math.max(sum,max);			
				}
				if((row+2)<N && (col+1)<M){
					sum=0;
					sum+= paper[row][col]+paper[row+1][col]+paper[row+2][col]+paper[row][col+1];
					max = Math.max(sum,max);			
				}
				if((row+1)<N && (col-2)>=0){
					sum=0;
					sum+= paper[row][col]+paper[row+1][col]+paper[row][col-1]+paper[row][col-2];
					max = Math.max(sum,max);			
				}
				if((row+2)<N && (col+1)<M){//ㄹ
					sum=0;
					sum+= paper[row][col]+paper[row+1][col]+paper[row+1][col+1]+paper[row+2][col+1];
					max = Math.max(sum,max);
				}
				if((row-1)>=0 && (col+2)<M){
					sum=0;
					sum+= paper[row][col]+paper[row][col+1]+paper[row-1][col+1]+paper[row-1][col+2];
					max = Math.max(sum,max);
				}
				if((row+2)<N && (col-1)>=0){//ㄹ 대칭
					sum=0;
					sum+= paper[row][col]+paper[row+1][col]+paper[row+1][col-1]+paper[row+2][col-1];
					max = Math.max(sum,max);
				}
				if((row+1)<N && (col+2)<M){
					sum=0;
					sum+= paper[row][col]+paper[row][col+1]+paper[row+1][col+1]+paper[row+1][col+2];
					max = Math.max(sum,max);
				}
				if((row-1)>=0 && (col+2)<M){ // ㅗ 시계방향
					sum=0;
					sum+= paper[row][col]+paper[row-1][col+1]+paper[row][col+1]+paper[row][col+2];
					max = Math.max(sum,max);
				}
				if((row+1)<N && (col+2)<M){
					sum=0;
					sum+= paper[row][col]+paper[row+1][col+1]+paper[row][col+1]+paper[row][col+2];
					max = Math.max(sum,max);
				}
				if((row+2)<N && (col-1)>=0){
					sum=0;
					sum+= paper[row][col]+paper[row+1][col]+paper[row+2][col]+paper[row+1][col-1];
					max = Math.max(sum,max);
				}
				if((row+2)<N && (col+1)<M){
					sum=0;
					sum+= paper[row][col]+paper[row+1][col]+paper[row+2][col]+paper[row+1][col+1];
					max = Math.max(sum,max);
				}
			}
		}
	}
}
