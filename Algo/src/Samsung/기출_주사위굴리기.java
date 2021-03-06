package Samsung;

import java.util.Scanner;
/*시작 1100
 *    윗 : 1 밑 : 6
 *     2
 *   4 1 3
 *     5
 *     6 
 * 명령
 * 동 : 1, 서 : 2, 북 : 3, 남 : 4
 *
 * y,x 바꿔서 받는 실수...
 * 끝 200
 * 
 */
public class 기출_주사위굴리기 {
	static int N,M; //세로, 가로
	static int x,y;
	static int K;
	static int[][] map;
	static int[][] dice;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		y = sc.nextInt();
		x = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][M];
		dice = new int[4][3];//가장 처음 주사위에는 모든면이 0으로 적혀있음
		for(int row=0;row<N;row++)
			for(int col=0;col<M;col++) map[row][col] = sc.nextInt();
		for(int c=0;c<K;c++) {
			int command = sc.nextInt();
			int ny =0;
			int nx =0;
			boolean possible = true;
			switch (command){
			case 1: //동
				ny = y+dr[1];
				nx = x+dc[1];
				if(rangeCheck(ny,nx)){//영역 만족
					moveEast();//일단 구른다
					if(map[ny][nx]!=0){//구른땅이 0이 아님
						dice[3][1] = map[ny][nx]; //땅값이 바닥으로
						map[ny][nx]=0; //땅값 0
					}
					else{
						map[ny][nx] = dice[3][1]; //바닥값이 땅값으로
					}
					y =ny;
					x =nx;
				}else possible = false;
				break; 
			case 2: //서
				ny = y+dr[3];
				nx = x+dc[3];
				if(rangeCheck(ny,nx)){//영역 만족
					moveWest();//일단 구른다
					if(map[ny][nx]!=0){//구른땅이 0이 아님
						dice[3][1] = map[ny][nx]; //땅값이 바닥으로
						map[ny][nx]=0; //땅값 0
					}
					else{
						map[ny][nx] = dice[3][1]; //바닥값이 땅값으로
					}
					y =ny;
					x =nx;
				}else possible = false;
				break;
			case 3: //북
				ny = y+dr[0];
				nx = x+dc[0];
				if(rangeCheck(ny,nx)){//영역 만족
					moveNorth();//일단 구른다
					if(map[ny][nx]!=0){//구른땅이 0이 아님
						dice[3][1] = map[ny][nx]; //땅값이 바닥으로
						map[ny][nx]=0; //땅값 0
					}
					else{
						map[ny][nx] = dice[3][1]; //바닥값이 땅값으로
					}
					y =ny;
					x =nx;
				}else possible = false;
				break;
			case 4: //남
				ny = y+dr[2];
				nx = x+dc[2];
				if(rangeCheck(ny,nx)){//영역 만족
					moveSouth();//일단 구른다
					if(map[ny][nx]!=0){//구른땅이 0이 아님
						dice[3][1] = map[ny][nx]; //땅값이 바닥으로
						map[ny][nx]=0; //땅값 0
					}
					else{
						map[ny][nx] = dice[3][1]; //바닥값이 땅값으로
					}
					y =ny;
					x =nx;
				}else possible = false;
				break;		
			}
			if(possible) System.out.println(dice[1][1]);
			else continue;
		}


	}
	private static void moveSouth() {
		int tmp = dice[3][1];
		dice[3][1] = dice[2][1];
		dice[2][1] = dice[1][1];
		dice[1][1] = dice[0][1];
		dice[0][1] = tmp;
	}
	private static void moveNorth() {
		int tmp = dice[0][1];
		dice[0][1] = dice[1][1];
		dice[1][1] = dice[2][1];
		dice[2][1] = dice[3][1];
		dice[3][1] = tmp;
	}
	private static void moveWest() {
		int tmp = dice[1][0];
		dice[1][0] = dice[1][1];
		dice[1][1] = dice[1][2];
		dice[1][2] = dice[3][1];
		dice[3][1] = tmp;
	}
	private static void moveEast() {
		int tmp = dice[1][2];
		dice[1][2] = dice[1][1];
		dice[1][1] = dice[1][0];
		dice[1][0] = dice[3][1];
		dice[3][1] = tmp;
	}
	private static boolean rangeCheck(int ny, int nx) {
		if(ny>=0 && ny<N && nx>=0 && nx<M) return true;
		return false;
	}
	private static void viewDice() {
		for(int row=0;row<4;row++) {
			for(int col=0;col<3;col++) {
				System.out.print(dice[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
