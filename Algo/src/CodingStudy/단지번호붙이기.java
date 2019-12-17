package CodingStudy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * N by N 
 * 5<=N<=25
 * 1로 인접한 영역은 한 영역으로 취급하고
 * 각 칸의 수가 가구의 수 
 * 각 영역의 가구의 수를 오름차순으로 출력하기
 * dfs로 풀어보기
 */
public class 단지번호붙이기 {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static char[][] village; //단지 정보
	static boolean[][] areaVisit;//방문 정보
	static LinkedList<Integer> areaCount = new LinkedList<Integer>();
	static int N;
	static int cnt;//가구수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		village = new char[N][N];
		areaVisit = new boolean[N][N];
		sc.nextLine();
		for(int row=0;row<N;row++) {
			String readLine = sc.nextLine();
			village[row] = readLine.toCharArray();
		}

		//처리
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(isNewArea(row,col)) {//방문하지 못한 가구면서 새로운 영역
					cnt=1;
					dfs(row,col);//인접한 모든 집 방문
					areaCount.add(cnt);
				}
			}
		}
		Collections.sort(areaCount); //오름차순 정렬
		System.out.println(areaCount.size());//총 단지 수 
		for (Integer house : areaCount) System.out.println(house); //각 단지의 가구 수
	}

	private static void dfs(int row, int col) {
		for(int dir=0;dir<4;dir++) {//인접조사
			int nr = row+dr[dir];
			int nc = col+dc[dir];
			if(!rangeCheck(nr,nc)) continue; //영역 만족하지 못하면 continue
			if(isNewArea(nr,nc)) dfs(nr,nc); //방문하지 못한 가구면서 새로운 영역
		}
	}

	private static boolean rangeCheck(int nr, int nc) {//경계검사 
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static boolean isNewArea(int row, int col) {
		if(village[row][col]=='1' && areaVisit[row][col]==false) {//방문해보지 않은 가구
			areaVisit[row][col] = true;//방문처리
			cnt++;//방문카운트 ++
			return true;
		}
		return false;
	}
}
