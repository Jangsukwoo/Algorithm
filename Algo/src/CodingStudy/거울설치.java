package CodingStudy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 집에는 문이 두개 있음
 * 한쪽 문에서 다른 쪽 문을 볼 수 있도록 거울을 설치
 * 
 * N by N 
 * 1<=N<=50
 * 
 * #은 문
 * .은 빛이 지나가는 곳
 * *은 빛이 지나갈 수 없는 곳
 * !은 거울을 설치 할 수 있는 위치
 * 
 * 다시
 */
public class 거울설치 {
	static int N;
	static char[][] house;
	static boolean[][] visit;
	static int[] dr = {-1,1,1,-1};
	static int[] dc = {1,1,-1,-1,};//우상,우하,좌하,좌상
	static Queue<int[]> q = new LinkedList<int[]>();
	static int[] entranceDoor = new int[2];
	static int[] exitDoor = new int[2];
	static boolean doorCheck;
	static int answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		house = new char[N][N];
		visit = new boolean[N][N];
		for(int row=0;row<5;row++) {
			String readLine = sc.nextLine();
			house[row] = readLine.toCharArray();
			setDoorCoordination(row);

		}
		//처리
		BFS();
		
	}
	private static void setDoorCoordination(int row) {//문의 좌표값 넣기
		for(int col=0;col<N;col++) {	
			if(house[row][col]=='#' && doorCheck==false){
				entranceDoor[0] = row;
				entranceDoor[1] = col;
				q.add(new int[] {row,col});//좌표,거울여부,방향
				break;
			}
			else if(house[row][col]=='#'&& doorCheck==true) {
				exitDoor[0] = row;
				exitDoor[1] = col;
				break;
			}
		}
	}
	private static void BFS() {
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRC = q.poll();
				//현재 거울에서 출구가 보이는지 확인
				if(possibleCheck()) {
					
				}
				for(int dir=0;dir<4;dir++) {
					int nr = curRC[0]+dr[dir];
					int nc = curRC[1]+dc[dir];
					if(rangeCheck(nr,nc)){//영역 만족하고 
						if(visit[nr][nc]==false && house[nr][nc]=='!'){//방문하지 않았고 거울이면
							visit[nr][nc]=true;
							q.add(new int[] {nr,nc});
						}
					}
				}
			}
			answer++;
		}
	}
	private static boolean possibleCheck() {
		// TODO Auto-generated method stub
		return false;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
}
