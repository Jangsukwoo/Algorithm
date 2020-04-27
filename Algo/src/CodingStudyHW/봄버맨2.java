package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 18:54~
 * 형태가 반복되는것을 발견
2 2 7
O.
.O

모듈러를 잘못했다..
1~6까지 사이의 패턴을 발견 해놓은 후 
3=7=11
4=8=12
5=9=13
6=10=14 .. 의 패턴을 발견 했고
여기서 4로 모듈러할때 
사소한 인덱스 실수 때문에 계속 틀렸던거다... 시간만 낭비한 바보였다 ㅠ
 */
public class 봄버맨2 {
	static int R,C,N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] map;
	static int second=1;
	static int dr[] = {-1,0,1,0};
	static int dc[] = {0,1,0,-1};
	static char[][][] patternMap;
	static Queue<Bomb> bomblist;
	static class Bomb{
		int row,col,second;
		Bomb(int r,int c, int s){
			row = r;
			col = c;
			second=s;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		getAnswer();
	}
	
	private static void getAnswer() {
		if(N<3) {
			for(int row=0;row<R;row++) {
				for(int col=0;col<C;col++) {
					System.out.print(patternMap[row][col][N]);
				}
				System.out.println();
			}
		}else {
			if(N%4==0) {
				for(int row=0;row<R;row++) {
					for(int col=0;col<C;col++) {
						System.out.print(patternMap[row][col][4]);
					}
					System.out.println();
				}
			}else if(N%4==1){
				for(int row=0;row<R;row++) {
					for(int col=0;col<C;col++) {
						System.out.print(patternMap[row][col][5]);
					}
					System.out.println();
				}
			}else if(N%4==2){
				for(int row=0;row<R;row++) {
					for(int col=0;col<C;col++) {
						System.out.print(patternMap[row][col][6]);
					}
					System.out.println();
				}
			}
			else if(N%4==3){
				for(int row=0;row<R;row++) {
					for(int col=0;col<C;col++) {
						System.out.print(patternMap[row][col][3]);
					}
					System.out.println();
				}
			}
		
		}
	}


	private static void simulation() {
		while(second<=6) { //N초까지
			decrease(); //timer
			explosion(); //폭발
			if(second%2==0) fill(); //짝수초면 빈칸은 폭탄채우기
			setPatternMap();
			second++; //시간증가
			
		}
	}
	private static void setPatternMap() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				patternMap[row][col][second] = map[row][col];
			}
		}
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
		int nr,nc;
		Bomb temp;
		for(int i=0;i<size;i++) {
			temp = bomblist.poll();
			if(temp.second==0) {
				checkMap[temp.row][temp.col]=true;
				for(int dir=0;dir<4;dir++) {
					nr = temp.row+dr[dir];
					nc = temp.col+dc[dir];
					if(rangeCheck(nr,nc)) checkMap[nr][nc]=true;
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
	private static boolean rangeCheck(int nr, int nc) {
		if((nr>=0&&nr<R) && (nc>=0&&nc<C)) return true;
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
	private static void setData() throws IOException {
		st =  new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		patternMap = new char[R][C][7];
		bomblist = new LinkedList();
		for(int row=0;row<R;row++) {
			map[row] = br.readLine().toCharArray();
			for(int col=0;col<C;col++) {
				if(map[row][col]=='O') {
					bomblist.add(new Bomb(row,col,3));
				}
			}
		}
	}
}
