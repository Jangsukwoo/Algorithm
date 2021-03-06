package Samsung;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 645
 * 침착하고 집중하자
 * 상황파악 ->시뮬?완탐?
 * ->시뮬 -> 구현
 * ->완탐 -> 내부처리 자료구조 고민 + 구현방법 고민
 * 
 * ->구현
 */
public class 기출_사다리조작 {
	static int N,M,H;//N:세로선 개수 , M은 가로선 개수 , H 가로선 놓을 수 있는 위치 개수
	static int[][] ladder;
	static int min, swp;
	static int[] arr, tmp;
	static boolean check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		ladder = new int[H + 1][N];
		for(int i=0;i<M;i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			ladder[a][b]=1;
		}//입력받는 부분 끝
		arr = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = i;
		}
		min = Integer.MAX_VALUE;
		dfs(1, 1, 0);
		if(min > 3) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}

	}

	private static void dfs(int idxR, int idxC, int cnt){
		if(cnt >= min) {
			return;
		}
		tmp = arr.clone();
		for(int row = 1; row <= H; row++) {
			for(int col = 1; col < N; col++) {
				if(ladder[row][col] == 1) {
					swp = tmp[col];
					tmp[col] = tmp[col + 1];
					tmp[col + 1] = swp;
				}
			}
		}
		check = true;
		for(int num = 1; num < N; num++) {
			if(tmp[num] != num) {
				check = false;
				break;
			}
		}
		if(check) {
			min = Math.min(min, cnt);
			return;
		}
		if(cnt == 3) {
			return;
		}
		for(int row = idxR; row <= H; row++) {
			for(int col = idxC; col < N; col++) {
				if(ladder[row][col] == 0 && (col - 1 == 0 || ladder[row][col - 1] == 0) && (col + 1 == N || ladder[row][col + 1] == 0)) {
					ladder[row][col] = 1;
					dfs(row, col + 1, cnt + 1);
					ladder[row][col] = 0;
				}
			}
			idxC = 1;
		}
	}
	private static void view() {
		for(int row=1;row<=H;row++) {
			for(int col=1;col<N;col++) {
				System.out.print(ladder[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
