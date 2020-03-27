package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 세로선 개수 N, 가로선 개수 H
 * M개의 줄에는 가로선의 정보
 * i번 세로선의 결과가 i가 나오도록
 * 추가해야하는 가로선의 개수의 최소값
 */
public class 사다리조작 {
	static int N,M,H;//세로,가로,위치
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] ladder;
	static int[] pickLadder;
	static int min = Integer.MAX_VALUE;
	static ArrayList<int[]> ladderList;
	static boolean possible;
	public static void main(String[] args) throws IOException {
		setData();
		for(int i=0;i<=3;i++){//설치릉 안해보거나 최대 3개씩 설치해본다.
			pickLadder = new int[i];
			if(possible==false) dfs(0,0,i);
		}
		
		if(min==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(min);
//		for(int[] a : ladderList) {
//			System.out.println(a[0]+" "+a[1]);
//		}
	}
	private static void dfs(int idx, int depth, int cnt) {
		if(possible) return;//찾았으면 끝냄 
		if(depth==cnt){//설치했으니 확인
			check();
			return;
		}
		for(int i=idx;i<ladderList.size();i++) {
			pickLadder[depth] = i;
			dfs(i+1,depth+1,cnt);
		}
	}
	private static void check(){
		int[][] testLadder = copyLadder();
		//고른 사다리 설치
		for(int i=0;i<pickLadder.length;i++) {
			int[] pick = ladderList.get(pickLadder[i]);
			int row = pick[0];
			int col = pick[1];
			testLadder[row][col] = 1;
			testLadder[row][col+1] = 1;
		}
		
		for(int row=1;row<=H;row++) {
			for(int col=1;col<=N;col++) {
				System.out.print(testLadder[row][col]);
			}
			System.out.println();
		}
		System.out.println();
		for(int i=1;i<=N;i++){//i번째부터 N번째 까지
			int col=i;
			for(int row=1;row<=H;row++){
				if(testLadder[row][col]==0) continue;
				else{//1인 경우 사다리가 있는 경우이므로
					if(col==1) {
						col+=1;
					}else if(col==N) {
						col-=1;
					}else {
						if(testLadder[row][col-1]==1) col-=1;
						else if(testLadder[row][col+1]==1) col+=1;
					}
				}
			}
			if(col==i) continue;
			else return;
		}
		
		//위 포문에서 끝났으면
		possible = true;
		min = pickLadder.length;
		return;
	}
	private static int[][] copyLadder() {
		int[][] testLadder = new int[H+1][N+1];
		for(int row=1;row<=H;row++) {
			for(int col=1;col<=N;col++){
				testLadder[row][col] = ladder[row][col];
			}
		}
		return testLadder;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		ladder = new int[H+1][N+1];
		ladderList = new ArrayList<int[]>();
		for(int i=0,a,b;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			ladder[a][b] = 1;
			ladder[a][b+1]=1;
		}
		for(int row=1;row<=H;row++) {
			for(int col=2;col<=N;col++){
				if(ladder[row][col]==0 && ladder[row][col-1]!=1 && ladder[row][col-2]!=1) {
					ladderList.add(new int[] {row,col-1});
				}
			}
		}
	}
}
