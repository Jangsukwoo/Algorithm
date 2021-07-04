package CodingTest;

import java.util.LinkedList;
import java.util.Queue;

public class 카21상2 {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][] visit;
	public static void main(String[] args) {
		solution(new String[][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}});
	}
	public static int[] solution(String[][] places) {
		
		int[] answer = new int[5];
		
		for(int i=0;i<places.length;i++) { 
			
			char[][] waitRoom = new char[5][5];
			
			boolean safeFlag = true;
			
			for(int j=0;j<places[i].length;j++) waitRoom[j] = places[i][j].toCharArray();
						
			for(int row=0;row<5;row++) {
				for(int col=0;col<5;col++) {
					if(waitRoom[row][col]=='P') {
						if(!bfs(waitRoom,row,col)) {
							safeFlag = false;
							break;
						}
					}
				}
				if(safeFlag==false) break;
			}
			
			if(safeFlag) answer[i] = 1;
			else answer[i] = 0;
		}
		
		return answer;
	}
	private static boolean bfs(char[][] waitRoom, int row, int col) {
		q.clear();
		visit= new boolean[5][5];
		insertQueue(row,col);
		int manhattanDist = 0;
		while(!q.isEmpty()) {
			if(manhattanDist==2) break;
			int size = q.size();
			for(int k=0;k<size;k++) {
				int[] currentRC = q.poll();
				int cr = currentRC[0];
				int cc= currentRC[1];
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(waitRoom[nr][nc]=='O' && visit[nr][nc]==false) insertQueue(nr,nc);
						else if(waitRoom[nr][nc]=='P' && visit[nr][nc]==false) {
							return false;
						}
					}
				}
			}
			manhattanDist++;
			
		}
		return true;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<5 && nc>=0 && nc<5) return true;
		return false;
	}
	private static void insertQueue(int row, int col) {
		int[] RC = new int[] {row,col};
		q.add(RC);
		visit[row][col] = true;
	}
}
