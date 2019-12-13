package BAEKJOON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * M x N의 격자 모눈종이 (M: 행, N: 열)
 * K개의 직사각형
 * 
 * 단, 1<=M,N,K<=100
 * 
 * 모눈종이의 좌측하단 좌표는 0,0,우측상단의 좌표는 N,M
 *  
 * K개의 직사각형은 좌측하단,우측상단의 좌표가 주어짐
 * K개의 직사각형이 모눈종이 전체를 덮는 경우는 없다.
 * 
 * K개의 직사각형만큼 먼저 영역을 색칠하고
 * 빈 영역을 찾으면서 dfs를 각각의 영역 넓이 파악하고 답 출력하기.
 * 답은 리스트로 구성하고 마지막에 오름차순해서 출력한다.
 */
public class 영역구하기 {
	static int M,N,K;
	static boolean[][] graphPaper;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상,우,하,좌
	static ArrayList<Integer> areaList = new ArrayList<Integer>();
	static int areaSize;
	static int areaCount;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		K = sc.nextInt();
		graphPaper = new boolean[M][N];//행,열
		for(int i=0;i<K;i++){
			int leftBottomX = sc.nextInt();
			int leftBottomY = sc.nextInt();
			int rightTopX = sc.nextInt();
			int rightTopY = sc.nextInt();
			paint(leftBottomX,leftBottomY,rightTopX,rightTopY);
		}
		for(int row=0;row<M;row++) {//빈 영역을 찾고, 찾았을 때 인접한 모든 빈영역에 대해 true
			for(int col=0;col<N;col++) {
				if(graphPaper[row][col]==false) {//아직 체크안된 영역이면 
					areaCount++;//새 영역 카운팅
					areaSize=1; //새로운 영역의 빈공간이므로 사이즈 1
					graphPaper[row][col]=true;
					dfs(row,col);
					areaList.add(areaSize);
				}
			}
		}
		Collections.sort(areaList);//넓이 오름차순 정렬
		System.out.println(areaCount);//카운팅된 구분된 영역의 개수
		for (Integer area : areaList){//오름차순된 영역의 넓이 출력 
			System.out.print(area+" ");
		}
	}
	private static void dfs(int row, int col) {
		for(int dir=0;dir<4;dir++){
			int nr = row+dr[dir];
			int nc = col+dc[dir];
			if(rangeCheck(nr,nc)){//영역 만족하고
				if(graphPaper[nr][nc]==false){//같은 영역안에 있으면
					graphPaper[nr][nc]=true; //방문처리 후 
					areaSize++;//넓이++
					dfs(nr,nc);
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<M && nc>=0 && nc<N) return true;
		return false;
	}
	private static void paint(int leftBottomX, int leftBottomY, int rightTopX, int rightTopY) {
		for(int height = leftBottomY;height<rightTopY;height++){//세로
			for(int width = leftBottomX;width<rightTopX;width++){//가로
				graphPaper[height][width]=true;
			}
		}
	}
}
