package CodingTest;

import java.util.LinkedList;
import java.util.Queue;

public class 라게인21 {
	static int[][] dr;
	static int[][] dc;
	static int catR;
	static int catC;
	static int n;
	static Queue<int[]> q;
	static ValueMap[][] valueMap;
	static boolean[][] visit;
	static class ValueMap{
		int R;
		int D;
		int S;
		public ValueMap(int r, int d, int s) {
			R = r;
			D = d;
			S = s;
		}
	}
	public static void main(String[] args) {
		solution(new String[]{"..XXX..", "X....X.", "..X....", "X.C..X.", ".X.....", "....XX.", "..X.X.."},new  int[][] {{0, 0},{0, 6}});
	}
	public static int[] solution(String[] board, int[][] choices) {
		/*
		 * 고양이의 마지막 위치 리턴
		 */
		int[] answer = {};
		setValue(board);

		/*
		 * step1 . 가장자리랑 닫힌칸 제외하고 모두 구하기
		 */

		bfsAll(board);

		for(int row=1;row<n-1;row++) {
			for(int col=1;col<n-1;col++) {
				System.out.print("D:"+valueMap[row][col].D+" R:"+valueMap[row][col].R+" ");
			}
			System.out.println();
		}

		return answer;
	}
	private static void bfsAll(String[] board) {
		valueMap = new ValueMap[n][n];

		for(int row=0;row<n;row++) {
			for(int col=0;col<n;col++) {
				valueMap[row][col] = new ValueMap(0,0,0);
			}
		}

		/*
		 * step1 . 가장자리랑 닫힌칸 제외하고 모두 구하기
		 */
		
		
		bfsSideAll(board);
		getAllRoute(board);

	}
	private static void getAllRoute(String[] board) {
		int route = 0;
		for(int row=2;row<(n-2);row++) {
			for(int col=2;col<(n-2);col++) {
				int cr = row;
				int cc = col;
				int currentLine = cr%2;
				if(board[cr].charAt(cc)!='X') { //X가 아닌 자리면 최단거리 형성해보고
					visit = new boolean[n][n];
					bfs(board,cr,cc);
					if(cr==3 && cc==2) {
						System.out.println("확인");
						System.out.println(valueMap[cr][cc].D);
					}
					for(int dir=0;dir<6;dir++) {
						int nr = cr+dr[currentLine][dir];
						int nc = cc+dc[currentLine][dir];
						if(board[nr].charAt(nc)!='X'){//벽이 아닌 경우
							if(valueMap[cr][cc].D>valueMap[nr][nc].D) {
								route+=valueMap[nr][nc].R;
							}
						}
					}
					valueMap[row][col].R = route;
				}else continue;
			}
		}
	}
	private static void bfsSideAll(String[] board) {
		for(int row=1;row<(n-1);row++) {
			if(board[row].charAt(1)!='X'){//.이면
				visit = new boolean[n][n];
				bfs(board,row,1);	
			}
			if(board[row].charAt(n-2)!='X'){//.이면
				visit = new boolean[n][n];
				bfs(board,row,n-2);	
			}
		}
		for(int col=1;col<(n-1);col++) {
			if(board[1].charAt(col)!='X'){//.이면
				visit = new boolean[n][n];
				bfs(board,1,col);	
			}
			if(board[n-2].charAt(col)!='X'){//.이면
				visit = new boolean[n][n];
				bfs(board,n-2,col);	
			}
		}
	}
	private static void bfs(String[] board, int startR, int startC) {
		q.add(new int[] {startR,startC});
		visit[startR][startC] = true;
		int route = 0;
		int dist = 0;
		boolean minRouteFind = false;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentRC = q.poll();
				int cr = currentRC[0];
				int cc = currentRC[1];
				int currentLine = cr%2;
				for(int dir=0;dir<6;dir++) {
					int nr = cr+dr[currentLine][dir];
					int nc = cc+dc[currentLine][dir];
					if(rangeCheck(nr,nc)) { //영역 안
						if(board[nr].charAt(nc)=='.' && visit[nr][nc]==false){//.인 경우
							if(nr==0 || nr==(n-1) || nc==0 || nc==(n-1)) {
								minRouteFind = true;
								route++; //가장자리면 루트 추가
							}
							else { //아니면 그냥 감
								q.add(new int[] {nr,nc});
								visit[nr][nc] = true;
							}
						}
					}
				}
			}
			dist++;
			if(minRouteFind) {
				break;
			}
		}
		q.clear();
		if(minRouteFind) {
			valueMap[startR][startC].D = dist;
			valueMap[startR][startC].R = route;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<n && nc>=0 && nc<n) return true;
		return false;
	}
	private static void setValue(String[] board) {
		dr = new int[2][6];
		dc = new int[2][6];
		dr[0] = new int[] {-1,-1,0,0,1,1};
		dc[0] = new int[] {-1,0,-1,1,-1,0};
		dr[1] = new int[] {-1,-1,0,0,1,1};
		dc[1] = new int[] {0,1,-1,1,0,1};
		n = board.length;
		boolean findCatRC = false;
		for(int row=0;row<n;row++) {
			for(int col=0;col<n;col++) {
				if(board[row].charAt(col)=='C') {
					catR = row;
					catC = col;
					findCatRC = true;
					break;
				}
			}
			if(findCatRC) break;
		}
		q = new LinkedList<int[]>();
	}
}
