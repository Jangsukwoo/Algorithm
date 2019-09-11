package BAEKJOON;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Second implements Comparable<Second>{
	int sangGuenRow;
	int sangGuenCol;
	int fireRow;
	int fireCol;
	int ID; //1은 불, 2는 상근
	Second(int sr,int sc,int fr, int fc, int id){
		sangGuenRow =sr;
		sangGuenCol =sc;
		fireRow=fr;
		fireCol=fc;
		ID =id;
	}
	@Override
	public int compareTo(Second o) {
		return Integer.compare(this.ID,o.ID);
	}
}
public class 불{
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static int Time,W,H;
	static char[][] Map;
	static boolean[][] Fire;
	static boolean[][] visit;
	static boolean Success;
	static Queue<Second> q;
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testase=1;testase<=T;testase++) {
			W = sc.nextInt();
			H = sc.nextInt();
			Map = new char[H+2][W+2];
			Fire = new boolean[H+2][W+2];
			visit = new boolean[H+2][W+2];
			q = new LinkedList<>();
			Success=false;
			Time=0;
			for(int row=0;row<(H+2);row++) {
				for(int col=0;col<(W+2);col++) {
					if(row==0||row==(H+1)||col==0||col==(W+1)) Map[row][col]='!';
				}
			}
			for(int row=1;row<=H;row++) {
				String line = sc.next();
				for(int col=1;col<=W;col++) {
					Map[row][col] = line.charAt((col-1));
					if(Map[row][col]=='@') {
						visit[row][col]=true;
						q.add(new Second(row,col,0,0,2)); //상근
					}
					if(Map[row][col]=='*') {
						Fire[row][col]=true;
						q.add(new Second(0,0,row,col,1)); //불
					}
				}
			}
			//입력 끝
			Collections.sort((List<Second>) q);//불기준 정렬
			while(!q.isEmpty()) {
				int size = q.size();
				Time++;
				for(int i=0;i<size;i++) {
					Second second = q.poll();
					switch (second.ID) {
					case 1:
						burn(second.fireRow,second.fireCol);
						break;
					case 2:
						sanggeunMove(second.sangGuenRow,second.sangGuenCol);
						break;
					}
					if(Success) break;
				}
				if(Success) break;
			}
			//처리	
			//출력
			if(Success) System.out.println(Time);
			else System.out.println("IMPOSSIBLE");
		}
	}

	private static void burn(int fireRow, int fireCol) {
		for(int dir=0;dir<4;dir++) {
			int nr = fireRow+dr[dir];
			int nc = fireCol+dc[dir];
			if(range(nr,nc)) {
				if(Map[nr][nc]!='#' && Map[nr][nc]!='!' && !Fire[nr][nc]) {
					Map[nr][nc]='*';
					Fire[nr][nc]=true;
					q.add(new Second(0,0,nr,nc,1)); //불
				}
			}
		}
	}
	private static void sanggeunMove(int sangGuenRow, int sangGuenCol) {
		for(int dir=0;dir<4;dir++) {
			int nr = sangGuenRow+dr[dir];
			int nc = sangGuenCol+dc[dir];
			if(range(nr,nc)) {
				if(Map[nr][nc]=='!') {
					Success=true;
					return;
				}
				if(Map[nr][nc]=='.' && !visit[nr][nc]) {
					visit[nr][nc]=true;
					Map[nr][nc] ='@';
					q.add(new Second(nr,nc,0,0,2)); 
				}
			}
		}
	}
	private static boolean range(int nr, int nc) {
		if((nr>=0&&nr<=(H+1))&&(nc>=0&&nc<=(W+1))) return true;
		return false;
	}



}