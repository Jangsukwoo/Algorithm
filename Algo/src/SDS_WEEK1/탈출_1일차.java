package SDS_WEEK1;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
class Time implements Comparable<Time>{
	int sR;
	int sC;
	int waterR;
	int waterC;
	int ID; 
	Time(int SR,int SC,int WR,int WC,int id){
		sR = SR;
		sC = SC;
		waterR = WR;
		waterC = WC;
		ID = id;
	}
	@Override
	public int compareTo(Time o) {
		return Integer.compare(this.ID,o.ID);
	}
}
public class 탈출_1일차{
	public static int[] dr = {-1,0,1,0};
	public static int[] dc = {0,1,0,-1};
	public static char[][] Map;
	public static boolean[][] Visit;
	public static int R,C,Time;
	public static boolean Success; 
	public static Queue<Time> q = new LinkedList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		Map = new char[R][C];
		Visit = new boolean[R][C];
		for(int row=0;row<R;row++) Map[row] = sc.next().toCharArray();
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(Map[row][col]=='S') {
					Visit[row][col] = true;
					q.add(new Time(row,col,0,0,2));
				}
				if(Map[row][col]=='*') q.add(new Time(0,0,row,col,1));
			}
		}
		Collections.sort((List<Time>) q);
		while(!q.isEmpty()) {
			Time++;
			int size = q.size();
			for(int i=0;i<size;i++) {
				Time tmp = q.poll();
				switch (tmp.ID) {
				case 1:
					water(tmp.waterR,tmp.waterC);
					break;
				case 2:
					Escape(tmp.sR,tmp.sC);
					break;
				}
				if(Success) break;
			}
			if(Success) break;
		}
		if(Success) System.out.println(Time);
		else System.out.println("KAKTUS");
	}
	private static void Escape(int CR, int CC) {
		for(int dir=0;dir<4;dir++) {
			int NR = CR+dr[dir];
			int NC = CC+dc[dir];
			if(check(NR,NC)) {
				if(Map[NR][NC]=='.' && Visit[NR][NC]==false) {
					Visit[NR][NC]=true;
					Map[CR][CC]='.';
					Map[NR][NC]='S';
					q.add(new Time(NR,NC,0,0,2));
				}
				else if(Map[NR][NC]=='D') {
					Success = true;
					return;
				}
			}
		}
	}
	private static void water(int CR, int CC) {
		for(int dir=0;dir<4;dir++) {
			int NR = CR+dr[dir];
			int NC = CC+dc[dir];
			if(check(NR,NC)) {
				if(Map[NR][NC]=='.') {
					Map[NR][NC] = '*';
					q.add(new Time(0,0,NR,NC,1));
				}
			}
		}
	}
	private static boolean check(int nR, int nC) {
		if((nR>=0&&nR<R)&&(nC>=0 &&nC<C)) return true;
		return false;
	}

}
