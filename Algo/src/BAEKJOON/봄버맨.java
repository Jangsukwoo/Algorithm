package BAEKJOON;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Bomb{
	int row,col,second;
	Bomb(int r,int c, int s){
		row = r;
		col = c;
		second=s;
	}
}
/*
4 5 1000
.O..O
...O.
.....
.O...

2 2 8
O.
.O
 */
public class 봄버맨{
	
	static int dr[] = {-1,0,1,0};
	static int dc[] = {0,1,0,-1};
	static int R,C,N;
	static int second=1;
	static char[][] map;
	static Queue<Bomb> bomblist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		N = sc.nextInt();
		map = new char[R][C];
		bomblist = new LinkedList();
        
		for(int row=0;row<R;row++) map[row] = sc.next().toCharArray();
		
		for(int row=0;row<R;row++) 
			for(int col=0;col<C;col++) if(map[row][col]=='O') bomblist.add(new Bomb(row,col,3));
		
		play();
		
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				System.out.print(map[row][col]);
			}
			System.out.println();
		}	
	}
	
	
	private static void play() {
		while(second<=N) { //N초까지
			
			decrease(); //폭탄 감소
			explosion(); //폭발
			if(second%2==0) fill(); //짝수초면 빈칸은 폭탄채우기
			second++; //시간증가
            view();
		}
	}
	
    
	private static void view() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				System.out.print(map[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}


	private static void decrease() {
		int size = bomblist.size();
		Bomb temp;
		for(int i=0;i<size;i++) {
			temp = bomblist.poll();
			temp.second = (temp.second-1);
			bomblist.add(temp);
		}
	}
	
	
	private static void explosion() {
		int size = bomblist.size();
		boolean[][] checkMap = new boolean[R][C]; 
		int rr,cc;
		Bomb temp;
		for(int i=0;i<size;i++) {
			temp = bomblist.poll();
			if(temp.second==0) {
				checkMap[temp.row][temp.col]=true;
				for(int dir=0;dir<4;dir++) {
					rr = temp.row+dr[dir];
					cc = temp.col+dc[dir];
					if(check(rr,cc)) checkMap[rr][cc]=true;
				}
			}
			bomblist.add(temp);
		}
		for(int i=0;i<size;i++) {
			temp = bomblist.poll();
			if(!checkMap[temp.row][temp.col])bomblist.add(temp);
			else map[temp.row][temp.col]='.';
			
		}
		
	}
	
	
	
	private static boolean check(int rr, int cc) { //범위체크
		if((rr>=0&&rr<R) && (cc>=0&&cc<C)) return true;
		return false;
	}
	
	
	
	private static void fill() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(map[row][col]=='.') {
					map[row][col] ='O';
					bomblist.add(new Bomb(row,col,3));
				}
			}
		}
	}

}