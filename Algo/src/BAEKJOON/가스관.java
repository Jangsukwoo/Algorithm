package BAEKJOON;
import java.util.Scanner;

public class 가스관{
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1}; //상하좌우
	static char[][] Map;
	static int R,C,findR,findC;
	static boolean up,right,down,left,find;
	static char findBlock;
	
	public static void main(String[] args) {
		//입력
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		Map = new char[R][C];
		for(int row=0;row<R;row++) Map[row] = sc.next().toCharArray();
	
		//처리
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(Map[row][col]!='.') findSpot(row,col);
				if(find) break;
			}
			if(find) break;
		}
		checkDirection();
		findBlock();		
		
		//출력
		findR+=1;
		findC+=1;
		System.out.println(findR+" "+findC+" "+findBlock);
	
	}
	private static void findSpot(int row, int col) {
		switch(Map[row][col]) {
		case '|':
			if(Map[row+dr[0]][col]=='.') {
				findR = row+dr[0];
				findC = col;
				find = true;
			}
			else if(Map[row+dr[2]][col]=='.') {
				findR = row+dr[2];
				findC = col;
				find = true;
			}
			break;
		case '-':
			if(Map[row][col+dc[1]]=='.') {
				findR = row;
				findC = col+dc[1];
				find = true;
			}
			else if(Map[row][col+dc[3]]=='.') {
				findR = row;
				findC = col+dc[3];
				find = true;
			}
			break;
		case '+':
			for(int dir=0;dir<4;dir++) {
				if(Map[row+dr[dir]][col+dc[dir]]=='.') {
					findR = row+dr[dir];
					findC = col+dc[dir];
					find =true;
					break;
				}
			}
			break;
		case '1':
			if(Map[row+dr[2]][col]=='.') {
				findR = row+dr[2];
				findC = col;
				find = true;
			}
			else if(Map[row][col+dc[1]]=='.') {
				findR = row;
				findC = col+dc[1];
				find = true;
			}
			break;
		case '2':
			if(Map[row+dr[0]][col]=='.') {
				findR = row+dr[0];
				findC = col;
				find = true;
			}
			else if(Map[row][col+dc[1]]=='.') {
				findR = row;
				findC = col+dc[1];
				find = true;
			}
			break;
		case '3':
			if(Map[row+dr[0]][col]=='.') {
				findR = row+dr[0];
				findC = col;
				find = true;
			}
			else if(Map[row][col+dc[3]]=='.') {
				findR = row;
				findC = col+dc[3];
				find = true;
			}
			break;
		case '4':
			if(Map[row+dr[2]][col]=='.') {
				findR = row+dr[2];
				findC = col;
				find = true;
			}
			else if(Map[row][col+dc[3]]=='.') {
				findR = row;
				findC = col+dc[3];
				find = true;
			}
			break;
		case 'M':
			break;
		case 'Z':
			break;
		}
	}
	private static void checkDirection() {
		for(int dir=0;dir<4;dir++){
			int nr=findR+dr[dir];
			int nc=findC+dc[dir];
			if(area(nr,nc)) {
				if(Map[nr][nc]!='M'&&Map[nr][nc]!='.'&&Map[nr][nc]!='-'&&Map[nr][nc]!='2'&&Map[nr][nc]!='3'&&dir==0) up=true; 
				if(Map[nr][nc]!='M'&&Map[nr][nc]!='.'&&Map[nr][nc]!='|'&&Map[nr][nc]!='1'&&Map[nr][nc]!='2'&&dir==1) right=true; 
				if(Map[nr][nc]!='M'&&Map[nr][nc]!='.'&&Map[nr][nc]!='-'&&Map[nr][nc]!='1'&&Map[nr][nc]!='4'&&dir==2) down=true; 
				if(Map[nr][nc]!='M'&&Map[nr][nc]!='.'&&Map[nr][nc]!='|'&&Map[nr][nc]!='3'&&Map[nr][nc]!='4'&&dir==3) left=true; 
			}
		}
	}
	private static void findBlock() {
		if(up&&right&&down&&left) findBlock ='+';
		else if(up&&down) findBlock ='|';
		else if(right&&left) findBlock ='-';
		else if(right&&down) findBlock ='1';
		else if(up&&right) findBlock ='2';
		else if(up&&left) findBlock ='3';
		else if(down&&left) findBlock ='4';		
	}



	private static boolean area(int nr, int nc) {
		if((nr>=0&&nr<R) && (nc>=0&&nc<C)) return true;
		return false;
	}
}