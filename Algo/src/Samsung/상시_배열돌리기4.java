package Samsung;

import java.util.Scanner;
class RotateOperation{
	int r;
	int c;
	int s;
	public RotateOperation(int r,int c,int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}
public class 상시_배열돌리기4 {
	static int N,M,K;//row,col,회전연산개수
	static int[][] matrix;
	static int[][] testingmatrix;
	static RotateOperation[] rotateoperation;
	static boolean[] visit;
	static int[] rotateCase;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		matrix = new int[N][M];
		testingmatrix = new int[N][M];
		rotateoperation = new RotateOperation[K];
		rotateCase = new int[K];
		visit = new boolean[K];
		for(int row=0;row<N;row++)
			for(int col=0;col<M;col++) matrix[row][col] = sc.nextInt();
		for(int i=0;i<K;i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();
			rotateoperation[i] = new RotateOperation(r, c, s);
		}
		permutaion(0);
		System.out.println(min);
	}
	private static void permutaion(int cnt) {
		if(cnt==K){
			setTestingMatrix();
			rotateOperator();
			updateMinimumValue();
			return ;
		}
		for(int i=0;i<K;i++){
			if(visit[i]==false) {
				visit[i] = true;
				rotateCase[cnt] = i;
				permutaion(cnt+1);
				visit[i] = false;
			}
		}
	}
	private static void updateMinimumValue(){
		int sum=0;
		for(int row=0;row<N;row++) {
			sum=0;
			for(int col=0;col<M;col++) sum+=testingmatrix[row][col];
			min = Math.min(min,sum);
		}
	}
	private static void rotateOperator() {
		for(int i=0;i<K;i++){
			int leftTopRow = rotateoperation[rotateCase[i]].r-rotateoperation[rotateCase[i]].s-1;
			int leftTopCol = rotateoperation[rotateCase[i]].c-rotateoperation[rotateCase[i]].s-1;
			int rightBottomRow = rotateoperation[rotateCase[i]].r+rotateoperation[rotateCase[i]].s-1;
			int rightBottomCol = rotateoperation[rotateCase[i]].c+rotateoperation[rotateCase[i]].s-1;
			while(true){
				if(leftTopRow==rightBottomRow && leftTopCol==rightBottomCol) break;
				else rotate(leftTopRow,leftTopCol,rightBottomRow,rightBottomCol);	
				leftTopRow+=1;
				leftTopCol+=1;
				rightBottomRow-=1;
				rightBottomCol-=1;
			}			
		}
	}
	private static void rotate(int ltR, int ltC, int rbR, int rbC){
		int rtR = ltR;
		int rtC = rbC;
		int lbR = rbR;
		int lbC = ltC;
		int savelt=0;
		int savert=0;
		int savelb=0;
		int saverb=0;

		//배열 돌리는 구현테크닉 또는 구현력 필요
		
		//Top
		savert = testingmatrix[rtR][rtC];
		for(int col=rtC;col>ltC;col--) testingmatrix[ltR][col] = testingmatrix[ltR][col-1];

		//Right	
		saverb = testingmatrix[rbR][rbC];
		for(int row=rbR;row>(rtR+1);row--) testingmatrix[row][rtC] = testingmatrix[row-1][rtC]; 
		testingmatrix[rtR+1][rtC] = savert;

		//Bottom
		savelb = testingmatrix[lbR][lbC];
		for(int col=lbC;col<(rbC-1);col++)testingmatrix[lbR][col] = testingmatrix[lbR][col+1];
		testingmatrix[rbR][rbC-1] = saverb;

		//Left		
		savelt = testingmatrix[ltR][ltC];
		for(int row=ltR;row<(lbR-1);row++) testingmatrix[row][ltC] = testingmatrix[row+1][ltC];
		testingmatrix[lbR-1][lbC] = savelb;

	}
	private static void setTestingMatrix() {
		for(int row=0;row<N;row++)
			for(int col=0;col<M;col++) testingmatrix[row][col] = matrix[row][col];
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				System.out.print(testingmatrix[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
