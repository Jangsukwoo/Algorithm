package CodingTest;

import java.util.LinkedList;
import java.util.Queue;

public class 이스트하3 {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int n;
	static boolean[][] visit;
	static Queue<int[]> q;
	static int max;
	public static void main(String[] args) {
		System.out.println(solution(new int[][]
				{{1, 28, 41, 22, 25, 79, 4}, 
			{39, 20, 10, 17, 19, 18, 8},
			{21, 4, 13, 12, 9, 29, 19}, 
			{58, 1, 20, 5, 8, 16, 9},
			{5, 6, 15, 2, 39, 8, 29},
			{39, 7, 17, 5, 4, 49, 5},
			{74, 46, 8, 11, 25, 2, 11}}	
		, 19, 6));
	}
	private static int solution(int[][] map, int p, int r) {
		int answer = 0;

		q = new LinkedList<>();
		n = map.length;
		answer = getMaxSumBybfsAll(map,p,r);

		return answer;
	}
	private static int getMaxSumBybfsAll(int[][] map, int power, int radius) {
		int maxSum = 0;
		for (int row=0; row< n-1; row++) { //모든 점에 대해서
			for (int col = 0; col < n-1; col++){

				queueInitialization(row,col); //bfs 시작 사각형 세팅 및 큐 초기화

				int sum = 0;

				sum = bfs(map,power,radius);

				maxSum = Math.max(maxSum, sum);

			}
		}
		return maxSum;
	}
	private static int bfs(int[][] map, int power, int radius) {
		int sum = 0;
		int turn = 1;
		int end = radius/2;
		while(turn<=end) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] currentRC = q.poll();
				int cr = currentRC[0];
				int cc = currentRC[1];
				if(turn != end) {//경계선이 아닌경우 
					if(map[cr][cc] <= power) sum++;
				}else {//경계선인 경우
					if(map[cr][cc] <= (power/2)) sum++; 
				}
				for (int dir = 0; dir < 4; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];
					if(rangeCheck(nr,nc)) {
						visit[nr][nc] = true;
						q.add(new int[] {nr,nc});
					}
				}
			}
			turn++;
		}
		return sum;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr< n && nc>=0 && nc<n && !visit[nr][nc]) return true;
		return false;
	}
	private static void queueInitialization(int row, int col) { //4개의 사각형은 반드시 존재함.
		q.clear();
		visit = new boolean[n][n];
        q.add(new int[] {row,col});
        q.add(new int[] {row+1,col});
        q.add(new int[] {row,col+1});
        q.add(new int[] {row+1,col+1});
        visit[row][col] = true;
        visit[row+1][col] = true;
        visit[row][col+1] = true;
        visit[row+1][col+1] = true;
	}
}


