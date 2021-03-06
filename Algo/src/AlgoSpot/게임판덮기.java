package AlgoSpot;

import java.util.Scanner;
/*

너무 고통받은 문제 ..
1
8 10 
########## 
#........# 
#........# 
#........# 
#........# 
#........# 
#........# 
########## 



1
3 7 
#.....# 
#.....# 
##..### 

 */
public class 게임판덮기 {

	static char[][] gameMap;
	static int rowSize,colSize;
	static int possibleCase;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++){
			possibleCase=0;
			rowSize = sc.nextInt();
			colSize = sc.nextInt();
			gameMap = new char[rowSize][colSize];
			sc.nextLine();

			for(int r=0;r<rowSize;r++) {
				String temp = sc.nextLine();
				gameMap[r] = temp.toCharArray();
			}//입력

			dfs(0,0);

			System.out.println(possibleCase);
		}
	}



	private static void dfs(int curRow, int curCol) {

		//view();
		for(int row=curRow;row<rowSize;row++){
			for(int col=curCol;col<colSize;col++) {
				if(gameMap[row][col]=='.'){//맨땅이고

					for(int blockNumber=1;blockNumber<=4;blockNumber++){
						//둘수 있는 경우만 볼거임.채워나가기만 할것
						
						
						if(isPossible(blockNumber,row,col)){//가능하면

							attach(blockNumber,row,col);
							
							int nr = 0;
							
							switch(blockNumber) {
							case 1://우우하
								nr = col+dc[1]+dc[1];
								if(!colcheck(nr)) dfs(row+1,0);
								else dfs(row,col);
								break;
								
							case 2:
								nr = col+dc[1];
								if(!colcheck(nr)) dfs(row+1,0);
								else dfs(row,col);
								break;
								
							case 3:
								nr = col+dc[1];
								if(!colcheck(nr)) dfs(row+1,0);
								else dfs(row,col);
								break;
								
							case 4:
								nr = col+dc[1]+dc[1];
								if(!colcheck(nr)) dfs(row+1,0);
								else dfs(row,col);
								break;
							}
							detach(blockNumber,row,col);
						}
					}
					return ;
				}
				else if(row==rowSize-1 && col==colSize-1){
					possibleCase++;
				}
			}
			curCol=0;
		}
	}

	private static boolean colcheck(int nr) {
		if(nr>=0 && nr<colSize) return true;
		return false;
	}

	private static void detach(int blockNumber, int row, int col) {
		switch(blockNumber) {
		case 1: //우우하
			gameMap[row][col]='.';
			gameMap[row+dr[1]][col+dc[1]]='.';
			gameMap[row+dr[1]+dr[2]][col+dc[1]+dc[2]]='.';
			break;
		case 2://하하우
			gameMap[row][col]='.';
			gameMap[row+dr[2]][col+dc[2]]='.';
			gameMap[row+dr[2]+dr[1]][col+dc[2]+dc[1]]='.';
			break;
		case 3://하하좌
			gameMap[row][col]='.';
			gameMap[row+dr[2]][col+dc[2]]='.';
			gameMap[row+dr[2]+dr[3]][col+dc[2]+dc[3]]='.';
			break;
		case 4://하우
			gameMap[row][col]='.';
			gameMap[row+dr[2]][col+dc[2]]='.';
			gameMap[row+dr[1]][col+dc[1]]='.';
			break;
		}
	}



	private static void attach(int blockNumber, int row, int col) {
		switch(blockNumber) {
		case 1: //우우하
			gameMap[row][col]='#';
			gameMap[row+dr[1]][col+dc[1]]='#';
			gameMap[row+dr[1]+dr[2]][col+dc[1]+dc[2]]='#';
			break;
		case 2://하하우
			gameMap[row][col]='#';
			gameMap[row+dr[2]][col+dc[2]]='#';
			gameMap[row+dr[2]+dr[1]][col+dc[2]+dc[1]]='#';
			break;
		case 3://하하좌
			gameMap[row][col]='#';
			gameMap[row+dr[2]][col+dc[2]]='#';
			gameMap[row+dr[2]+dr[3]][col+dc[2]+dc[3]]='#';
			break;
		case 4://하우
			gameMap[row][col]='#';
			gameMap[row+dr[2]][col+dc[2]]='#';
			gameMap[row+dr[1]][col+dc[1]]='#';
			break;
		}
	}



	private static boolean isPossible(int blockNumber, int row, int col) {
		switch(blockNumber) {
		case 1: //우우하
			if(Check(row+dr[1],col+dc[1]) && Check(row+dr[1]+dr[2],col+dc[1]+dc[2])) return true;
			else return false;
		case 2://하하우
			if(Check(row+dr[2],col+dc[2]) && Check(row+dr[2]+dr[1],col+dc[2]+dc[1])) return true;
			else return false;
		case 3://하하좌
			if(Check(row+dr[2],col+dc[2]) && Check(row+dr[2]+dr[3],col+dc[2]+dc[3])) return true;
			else return false;
		case 4://하우
			if(Check(row+dr[2],col+dc[2]) && Check(row+dr[1],col+dc[1])) return true;
			else return false;   
		}
		return false;
	}



	private static boolean Check(int row, int col) {
		if(row>=0 && row<rowSize && col>=0 && col<colSize) {//영역 만족하고
			if(gameMap[row][col]!='#') return true;//#이 아니면 true
			else return false;//#이면 false;
		}
		return false;//영역 만족 못하면 false
	}



	private static void view() {
		for(int row=0;row<rowSize;row++){
			for(int col=0;col<colSize;col++) {
				System.out.print(gameMap[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
