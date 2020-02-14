package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N by M 배열이 있음
 * 
 * 배열 A의 최소값은 각 행 별로의 합들 중에 최소값
 * 
 * 돌리는건 시계방향이고
 * 회전정보가 주어질 때 
 * 회전 연산을 k회 수행 후 최종 최소값 출력
 */

public class 배열돌리기4 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] A;
	static int[][] originalA;
	static int[][] rcs;
	static int N,M,K;
	static boolean[] visit;
	static int[] pickSpin;
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		setData();
		dfs(0);
		System.out.println(min);
	}
	private static void dfs(int depth) {
		if(depth==K){
			copyArray();
			rotate();
			getMinFromA();
			return;
		}
		for(int i=0;i<K;i++){
			if(visit[i]==false){
				visit[i] = true;
				pickSpin[depth] = i;
				dfs(depth+1);
				visit[i] = false;
			}
		}
	}
	private static void copyArray() {
		A = new int[N][M];
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				A[row][col] = originalA[row][col];
			}
		}
	}
	private static void getMinFromA() {
		for(int row=0;row<N;row++) {
			int minValue=0;	
			for(int col=0;col<M;col++) {
				minValue+=A[row][col];
			}
			min = Math.min(min,minValue);
		}
	}
	private static void rotate() {
		for(int spin=0;spin<K;spin++){
			rotateArray(rcs[pickSpin[spin]][0],rcs[pickSpin[spin]][1],rcs[pickSpin[spin]][2]);
		}
	}

	private static void rotateArray(int r, int c, int s){
		//가장 왼쪽위가(r-s,c-s)
		//가장 오른쪽 아래가(r+s,c+s)
		for(int dist=1;dist<=s;dist++){//돌리기
			int ltr = r-dist;
			int ltc = c-dist;
			int rtr = r-dist;
			int rtc = c+dist;
			int lbr = r+dist;
			int lbc = c-dist;
			int rbr = r+dist;
			int rbc = c+dist;//꼭지점
			clockWise(ltr,ltc,rtr,rtc,lbr,lbc,rbr,rbc);//꼭지점 네개 줌
		}
	}
	private static void clockWise(int ltr, int ltc, int rtr, int rtc, int lbr, int lbc, int rbr, int rbc) {
		int save = A[ltr][ltc];//좌측상단값 저장
		for(int row=ltr;row<lbr;row++){
			A[row][ltc]= A[row+1][ltc];
		}
		for(int col=lbc;col<rbc;col++){
			A[lbr][col] = A[lbr][col+1];
		}
		for(int row=rbr;row>ltr;row--){
			A[row][rbc]= A[row-1][rbc];
		}
		for(int col=rtc;col>(ltc+1);col--){
			A[ltr][col]= A[ltr][col-1];
		}
		A[ltr][ltc+1] = save;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//행
		M = Integer.parseInt(st.nextToken());//열
		K = Integer.parseInt(st.nextToken());
		originalA = new int[N][M];
		rcs = new int[K][3];
		visit = new boolean[K];
		pickSpin = new int[K];
		for(int row=0;row<N;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				originalA[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<K;i++){
			st = new StringTokenizer(br.readLine());
			rcs[i][0] = Integer.parseInt(st.nextToken())-1;
			rcs[i][1] = Integer.parseInt(st.nextToken())-1;
			rcs[i][2] = Integer.parseInt(st.nextToken());
		}
	}
}
