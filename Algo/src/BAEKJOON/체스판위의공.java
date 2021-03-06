package BAEKJOON;
import java.util.Arrays;
/*
 *
 * 체스판 사이즈 RxC (행x열)
 * 각 칸 위에 공이 하나씩 놓여진다.
 * 각 공은 인접한 8방향에 적힌 모든 정수가 
 * 현재 칸에 적힌 수보다 크면 이동을 멈춘다.
 * 그 외의 경우에는 가장 작은 정수가 있는 칸으로 공이 이동한다.
 * 
 * 한 칸 위에 여러 개의 공이 있을 수 있다.
 * 공이 더 이상 움직이지 않을 때 각 칸에 공이 몇 개 있는지
 * 체스판에 적어서 출력
 * 
 * 공을 모두 큐에 담고 
 * 공을 하나씩 꺼내면서 이동 가능성을 체크 후 
 * 다시 큐에 넣는 방식
 * 
 * ↑ 위에 코드대로 설계, 구현 했으나 시간초과가 남
 * 다른 좋은 방법이 의도된 문제.
 * 
 * -----
 * 각 칸의 값은 변하지 않고
 * 그 칸에서 갈 수 있는 길은 1방향으로 정해져있다.
 * 각 공 중에 
 * 가장 큰값부터 내림차순으로 정렬해서
 * 가장 큰값부터 자신보다 작은 값으로 내려가기 시작하면된다.
 * 예를 들어 가장 큰값이 한칸 내려갔으면
 * 내려간 그 칸에 적힌 숫자의 차례가 왔을 때 
 * 같이 내려가주면 된다.
 * 이렇게 푸니까 통과했다..
 */
import java.util.Scanner;
class ChessBall implements Comparable<ChessBall>{
	int row;
	int col;
	int value;
	public ChessBall(int r, int c, int v){
		row = r;
		col = c;
		value = v;
	}
	@Override
	public int compareTo(ChessBall o) {
		return -Integer.compare(this.value,o.value);
	}

}
public class 체스판위의공 {
	static int R,C;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int[][] chessBoard;
	static int[][] ballCountMap;
	static ChessBall[] balls;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		chessBoard = new int[R][C];
		ballCountMap = new int[R][C];
		balls = new ChessBall[R*C];
		int cnt=0;
		for(int row=0;row<R;row++){
			for(int col=0;col<C;col++){
				chessBoard[row][col] = sc.nextInt();
				balls[cnt] = new ChessBall(row, col, chessBoard[row][col]);
				cnt++;
			}
		}
		Arrays.sort(balls);
		move();
		result();
	}
	private static void result() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				System.out.print(ballCountMap[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void move() {
		for(int i=0;i<(R*C);i++) {
			int[] RC = {balls[i].row,balls[i].col};
			ballCountMap[RC[0]][RC[1]]+=1;
			int min = balls[i].value;
			int[] minRC = null;
			for(int dir=0;dir<8;dir++){//8방향 탐색
				int nr = RC[0]+dr[dir];
				int nc = RC[1]+dc[dir];
				if(rangeCheck(nr,nc)){//영역 만족하고
					if(chessBoard[nr][nc]<chessBoard[RC[0]][RC[1]]){
						//값이 더 작은 경우
						if(chessBoard[nr][nc]<min){//min값 보다 더 작으면
							minRC = new int[]{nr,nc};
							min = chessBoard[nr][nc];
						}
					}
				}
			}//탐색이 끝난 후
			if(minRC!=null){//이동이 가능하면
				ballCountMap[minRC[0]][minRC[1]]+=ballCountMap[RC[0]][RC[1]];
				ballCountMap[RC[0]][RC[1]]-=ballCountMap[RC[0]][RC[1]];//기존 자리 공 회수
			}
		}

	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
}
