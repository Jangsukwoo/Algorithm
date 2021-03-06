package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class RC3184{
	int row;
	int col;
	public RC3184(int r, int c) {
		this.row = r;
		this.col = c;
	}
}
public class 양 {
	static int R,C;
	static int wolf,sheep;
	static char[][] garden;
	static boolean[][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<RC3184> q = new LinkedList<RC3184>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		garden = new char[R][C];
		visit = new boolean[R][C];
		sc.nextLine();
		for(int r=0;r<R;r++) {
			String line = sc.nextLine();
			garden[r] = line.toCharArray();
		}
		for(int row=0;row<R;row++){
			for(int col=0;col<C;col++) {
				if((garden[row][col]=='v'||garden[row][col]=='o')&&visit[row][col]==false){
					q.add(new RC3184(row,col));
					visit[row][col] = true;
					BFS(row,col);
				}
			}
		}
		System.out.println(sheep+" "+wolf);
	}
	private static void BFS(int row, int col){
		int s=0;
		int w=0;
		if(garden[row][col]=='v') w++;
		else if(garden[row][col]=='o') s++;
		while(!q.isEmpty()){
			int size=q.size();
			for(int i=0;i<size;i++){
				RC3184 RC = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = RC.row+dr[dir];
					int nc = RC.col+dc[dir];
					if(rangeCheck(nr,nc)){
						if(areaCheck(nr,nc)) {
							switch (garden[nr][nc]) {
							case 'v':
								w++;
								q.add(new RC3184(nr,nc));
								visit[nr][nc] = true;
								break;
							case 'o':
								s++;
								q.add(new RC3184(nr,nc));
								visit[nr][nc] = true;
								break;
							case '.':
								q.add(new RC3184(nr,nc));
								visit[nr][nc] = true;
								break;
							}
						}
					}
				}
			}
		}
		//양이 더많으면 전체 양에 더해주고 아니면 늑대 수 집계
		if(s>w) sheep+=s;
		else wolf+=w;
		
	}
	private static boolean areaCheck(int nr, int nc) {
		if(garden[nr][nc] !='#' && visit[nr][nc] !=true) return true;
		return false;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
}
