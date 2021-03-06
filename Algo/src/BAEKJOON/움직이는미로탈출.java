package BAEKJOON;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
/*
 * 죽제의 위치는 가장 최좌하단
 * 목적는 가장 최우상단
 * 벽은 1초마다 한칸씩 내려온다.
 * 벽이 욱제를 잡는다고 생각하자
 * 벽이 다 내려갔는데 욱제를 못잡았으면
 * 욱제는 탈출 할 수 있는것.
 * 
 * 벽은 제일 밑에있는 벽부터 내려오도록 처리함
 * 위에있는 벽부터 내려오면 아래에있는 벽을 덮어씌우기때문에 문제가 생기니
 * 벽을 행기준으로 정렬함
 */
public class 움직이는미로탈출 {
	static char[][] maze;
	static boolean [][] wookJae;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1}; //12시방향부터 시계방향
	static Queue<int[]> wallQ = new LinkedList<int[]>();
	static Queue<int[]> wookJaeQ = new LinkedList<int[]>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		maze = new char[8][8];
		wookJae = new boolean[8][8];
		for(int i=0;i<8;i++) {
			maze[i] = sc.nextLine().toCharArray();
			for(int j=0;j<8;j++) {
				if(maze[i][j]=='#') wallQ.add(new int[]{i,j});
			}
		}
		Collections.sort((List<int[]>)wallQ, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return -Integer.compare(o1[0],o2[0]);
			}
		}); //행기준으로 밑에있는 벽들 부터 먼저 떨어져야하니까 내림차순 정렬
		wookJaeQ.add(new int[]{7,0});
		wookJae[7][0] = true;
		simulation();
		if(isWookJaeAlive()) System.out.println("1");
		else System.out.println("0");
	}
	private static boolean isWookJaeAlive() {
		for(int row=0;row<8;row++) {
			for(int col=0;col<8;col++) {
				if(wookJae[row][col]==true) return true; 
			}
		}
		return false;
	}
	private static void simulation() {
		while(!wallQ.isEmpty()){
			int size = wookJaeQ.size();
			for(int i=0;i<size;i++) {
				int[] curRC = wookJaeQ.poll();
				if(wookJae[curRC[0]][curRC[1]]==true){//살아있으면 
					wookJaeQ.add(new int[] {curRC[0],curRC[1]});
					for(int dir=0;dir<8;dir++){
						int nr = curRC[0]+dr[dir];
						int nc = curRC[1]+dc[dir];
						if(rangeCheck(nr,nc)){
							if(wookJae[nr][nc]==false && maze[nr][nc]!='#') {
								wookJaeQ.add(new int[] {nr,nc});
								wookJae[nr][nc]=true;
							}
						}
					}
				}
			}//욱제 이동 끝
			downWalls();
			//벽 중력처리
		}
	}
	private static void downWalls() {
		int size = wallQ.size();
		for(int i=0;i<size;i++){
			int[] curRC = wallQ.poll();
			maze[curRC[0]][curRC[1]]='.';
			int nr = curRC[0]+1;
			if(rangeCheck(nr,curRC[1])){//영역 만족하면
				maze[nr][curRC[1]]='#';
				wookJae[nr][curRC[1]] = false;//욱제 true로 만들었어도 false;
				wallQ.add(new int[] {nr,curRC[1]});
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<8 && nc>=0 && nc<8) return true;
		return false;
	}
}
