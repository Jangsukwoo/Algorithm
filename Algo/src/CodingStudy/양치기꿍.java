package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 양의 숫자가 늑대보다 많을 경우 양이 이긴다.
 * 늑대가 더 많거나 같으면 양이 전부 잡아먹힌다.
 * 늑대 v
 * 양 k
 * 빈공간 .
 * 울타리 #
 * 
 */
public class 양치기꿍 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R,C;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static char[][] fence;
	static int[][] visit;
	static int wolf,sheep;
	static Queue<int[]> q;
	public static void main(String[] args) throws IOException {
		setData();
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(visit[row][col]==0 && fence[row][col]!='#'){//아직 방문 못한 울타리 영역이면
					insertQueue(row,col);
					bfs();
				}
			}
		}
		System.out.println(sheep+" "+wolf);
	}
	private static void insertQueue(int row, int col) {
		visit[row][col] = 1;
		q.add(new int[]{row,col});
	}
	private static void bfs() {
		int wolfCnt=0;
		int sheepCnt=0;
		while(!q.isEmpty()){
			int[] curRC = q.poll();
			int cr = curRC[0];
			int cc = curRC[1];
			switch (fence[cr][cc]){//펜스의 종류
			case '.':
				break;
			case 'v':
				wolfCnt++;
				break;
			case 'k':
				sheepCnt++;
				break;
			}
			for(int dir=0;dir<4;dir++){
				int nr = curRC[0]+dr[dir];
				int nc = curRC[1]+dc[dir];
				if(rangeCheck(nr,nc)){//영역내에 있다면
					if(visit[nr][nc]==0 && fence[nr][nc]!='#'){//방문 안했으면서 울타리가 아니라면
							insertQueue(nr,nc);
						}
					}
				}
			}
		if(wolfCnt>=sheepCnt) wolf+=wolfCnt;
		else sheep+=sheepCnt;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		fence = new char[R][C];
		visit = new int[R][C];
		q = new LinkedList<int[]>();
		for(int row=0;row<R;row++){
			st = new StringTokenizer(br.readLine());
			fence[row] = st.nextToken().toCharArray();
		}
	}
}
