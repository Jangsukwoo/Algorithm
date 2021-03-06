package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 */
public class 원판돌리기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] circleBoard;
	static int N,M,T;//반지름,적힌숫자크기,회전수
	static int[][] spinInfo;
	static int x,d,k;//x의 배수인 원판을 d방향으로 k칸 회전
	//0은 시계방향 1은 반시계방향
	//인접하면서 수가 같은 것은 모두 지운다.
	//없는 경우는 원판에 적힌 수의 평균을 구한 뒤 평균보다 큰 수에서 1을 빼고 작은수에는 1을 더함
	static StringTokenizer st;
	static int circleBoardSum;
	static int[][] adjoinPosition;
	public static void main(String[] args) throws IOException {
		setData();
		rotate();
		System.out.println(circleBoardSum);
	}
	private static void rotate() {
		for(int spin=0;spin<T;spin++) {//T번 회전
			x = spinInfo[spin][0];
			d = spinInfo[spin][1];
			k = spinInfo[spin][2];
			multipleXNumberRotate();//배수들 전부 돌린 뒤
			if(adjoinCheck()) removeAdjoinPosition();//인접하면서 같은 수가 있으면 해당 위치에 적힌 숫자들을 지워주고
			else updateCircleBoard();//평균을 구해서 원판 값 업데이트 처리  	
		}
		getCircleBoardSum();
	}
	private static void getCircleBoardSum() {
		for(int radius=1;radius<=N;radius++){
			for(int i=0;i<M;i++){
				if(circleBoard[radius][i]>0) {
					circleBoardSum+=circleBoard[radius][i];
				}
			}
		}
	}
	private static void updateCircleBoard(){
		double sum=0;
		double cnt=0;
		double average=0;
		for(int radius=1;radius<=N;radius++) {
			for(int i=0;i<M;i++){
				if(circleBoard[radius][i]>0) {
					cnt++;
					sum+=circleBoard[radius][i];
				}
			}
		}
		average= sum/cnt;
		for(int radius=1;radius<=N;radius++) {
			for(int i=0;i<M;i++){
				if(circleBoard[radius][i]>average) circleBoard[radius][i]-=1;//큰수는 -1
				else if(circleBoard[radius][i]<average && circleBoard[radius][i]!=0) circleBoard[radius][i]+=1;//작은수는 +1
				
			}
		}
		
	}
	private static void removeAdjoinPosition() {
		for(int radius=1;radius<=N;radius++) {
			for(int i=0;i<M;i++){
				if(adjoinPosition[radius][i]==1) circleBoard[radius][i]=0;
			}
		}
	}
	private static boolean adjoinCheck() {
		boolean adjoinFlag = false;
		adjoinPosition = new int[N+1][M];
		for(int radius=2;radius<=N;radius++){//판과 판의 인접 조사
			for(int i=0;i<M;i++){
				if(circleBoard[radius-1][i]==circleBoard[radius][i] && circleBoard[radius][i]!=0) {
					adjoinFlag=true;
					adjoinPosition[radius-1][i]=1;
					adjoinPosition[radius][i]=1;
				} 
			}
		}
		for(int radius=1;radius<=N;radius++){//각 판에 적힌 숫자의 양 옆 인접 조사
			for(int i=0;i<(M-1);i++){
				if(i==0) {
					if(circleBoard[radius][i]==circleBoard[radius][M-1] && circleBoard[radius][i]!=0) {
						adjoinFlag=true;
						adjoinPosition[radius][i]=1;
						adjoinPosition[radius][M-1]=1;
					}
					if(circleBoard[radius][i]==circleBoard[radius][1] && circleBoard[radius][i]!=0) {
						adjoinFlag=true;
						adjoinPosition[radius][i]=1;
						adjoinPosition[radius][1]=1;
					}	
				}else if(i>0 && i<M-1) {
					if(circleBoard[radius][i]==circleBoard[radius][i+1] && circleBoard[radius][i]!=0) {
						adjoinFlag=true;
						adjoinPosition[radius][i] = 1;
						adjoinPosition[radius][i+1] = 1;
					}
				}
			}
		}
		return adjoinFlag;
	}
	private static void multipleXNumberRotate() {
		for(int radius=1;radius<=N;radius++) {
			if(radius%x==0){//x의 배수면
				for(int i=0;i<k;i++){//k칸 회전 
					if(d==0) {//시계
						clockWise(radius);
					}else if(d==1){//반시계
						counterClockWise(radius);
					}
				}
			}
		}

	}
	private static void clockWise(int radius) {
		int temp = circleBoard[radius][M-1];
		for(int i=M-1;i>0;i--) circleBoard[radius][i] = circleBoard[radius][i-1];
		circleBoard[radius][0]=temp;
	}
	private static void counterClockWise(int radius) {
		int temp = circleBoard[radius][0];
		for(int i=0;i<(M-1);i++) circleBoard[radius][i] = circleBoard[radius][i+1];
		circleBoard[radius][M-1]=temp;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		circleBoard = new int[N+1][M];
		spinInfo = new int[T][3];
		for(int radius=1;radius<=N;radius++){
			st = new StringTokenizer(br.readLine());
			for(int position =0; position<M;position++) {
				circleBoard[radius][position] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine());
			spinInfo[i][0] = Integer.parseInt(st.nextToken());
			spinInfo[i][1] = Integer.parseInt(st.nextToken());
			spinInfo[i][2] = Integer.parseInt(st.nextToken());
		}
	}
}
