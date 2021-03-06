package Samsung;

import java.util.Scanner;


/*
 * 4:25~
 * 번호가 Xi의 배수인 원판을
 * di 방향으로
 * ki칸 회전
 * di = 0 : 시계방향
 * di = 1: 반시계방향
 * 
 * 회전후 
 * 인접하면서 수가 같은 것 을 모두 찾고 지운다.
 * 없는 경우에는 원판에 적힌 수의 평균을 구한 후 
 * 평균보다 큰 수에서 1을 빼고
 * 작은 수에는 1을 더한다.
 * 
 * 보류
 * 어디가 틀린지 모르겠음..ㅠ
 * 
 * ->
 * 평균을 int가아닌 double로 하니까 풀렸다 ㅠ
 */
public class 기출_원판돌리기 {
	static int N,M,T;//원판 N개, 원판에 적힌 숫자 M개, 회전 T번
	static int[][] circleBoard;
	static int[][] command;
	static int x,d,k;
	static int result;
	//x의 배수인 원판들을 대상으로
	//d 방향 ( 0: 시계 , 1 : 반시계)
	//k 칸만큼 회전
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		circleBoard = new int[N+1][M];
		command = new int[T][3];
		for(int i=1;i<=N;i++)
			for(int j=0;j<M;j++) circleBoard[i][j] = sc.nextInt();
		for(int t=0;t<T;t++) {
			command[t][0] = sc.nextInt();//x
			command[t][1] = sc.nextInt();//d
			command[t][2] = sc.nextInt();//k
		}
		rotate();
		sum();
		System.out.println(result);
	}
	private static void sum(){
		for(int i=1;i<=N;i++){
			for(int j=0;j<M;j++) {
				result+=circleBoard[i][j];
			}
		}
	}
	private static void rotate() {
		for(int spin=0;spin<T;spin++){//회전
			x = command[spin][0];
			d = command[spin][1];
			k = command[spin][2];
			for(int num=1;num<=N;num++){
				if(num%x==0){//배수면
					shift(num);//회전 
				}
			}
			adJoinCheck();
		}
	}
	private static void view() {
		for(int i=1;i<=N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(circleBoard[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void adJoinCheck() {
		boolean[][] visit = new boolean[N+1][M];
		boolean exist = false;
		for(int row=1;row<=N;row++){
			for(int col=1;col<M;col++){
				if(col==(M-1)) {
					if(circleBoard[row][0]==circleBoard[row][M-1] && circleBoard[row][0]!=0) {
						exist= true;
						visit[row][0]= true;
						visit[row][M-1] = true;
					}
				}else {
					if(circleBoard[row][col-1]==circleBoard[row][col] && circleBoard[row][col]!=0) {
						exist= true;
						visit[row][col-1] = true;
						visit[row][col] = true;
					}
					if(circleBoard[row][col]==circleBoard[row][col+1] && circleBoard[row][col]!=0){
						exist= true;
						visit[row][col]=true;
						visit[row][col+1]=true;
					}
				}
				if(row<N){//위아래
					if(col==1) {
						if(circleBoard[row][0]==circleBoard[row+1][0] && circleBoard[row][0]!=0){
							exist= true;
							visit[row][0] = true;
							visit[row+1][0] = true;
						}
					}
					if(circleBoard[row][col]==circleBoard[row+1][col] && circleBoard[row][col]!=0){
						exist= true;
						visit[row][col] = true;
						visit[row+1][col] = true;
					}
				}
			}
		}
		if(exist) removeAdjoin(visit);
		else averageCalculate();
	}

	private static void averageCalculate() {
		double cnt=0;
		double sum=0;
		for(int i=1;i<=N;i++) {
			for(int j=0;j<M;j++) {
				if(circleBoard[i][j]!=0) {
					sum+=circleBoard[i][j];
					cnt++;
				}
			}
		}
		double av = sum/cnt;
		for(int i=1;i<=N;i++) {
			for(int j=0;j<M;j++) {
				if(circleBoard[i][j]>av){
					circleBoard[i][j]-=1;
				}
				else if(circleBoard[i][j]<av && circleBoard[i][j]!=0){
					circleBoard[i][j]+=1;
				}
			}
		}
	}
	private static void removeAdjoin(boolean[][] visit) {
		for(int i=1;i<=N;i++) {
			for(int j=0;j<M;j++){
				if(visit[i][j]==true){
					circleBoard[i][j] = 0;
				}
			}
		}
	}
	private static void shift(int num) {
		int[] tmp = new int[M];
		switch (d){
		case 0:	//시계방향
			for(int i=0;i<M;i++){
				int value = i+k;
				if(value>(M-1)){
					int mod = value%M;
					tmp[mod] = circleBoard[num][i];
				}else{//범위 넘지 않으면 그대로 대입
					tmp[value] = circleBoard[num][i];
				}
			}
			break;
		case 1: //반시계방향
			for(int i=0;i<M;i++){
				int value = i-k;
				if(value<0){//음수면
					int mod = (value%M);
					if(mod==0){//나머지가 0이면
						tmp[0] = circleBoard[num][i];
					}else{//나머지가 0이 아니면
						tmp[M+mod] = circleBoard[num][i];
					}
				}else{//음수가 아니면 그냥 대입
					tmp[value] = circleBoard[num][i];
				}
			}
			break;
		}
		for(int i=0;i<M;i++) circleBoard[num][i] = tmp[i];
	}
}
