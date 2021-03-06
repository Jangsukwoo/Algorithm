package Samsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 5:02~
 * 어렵다.. 식세워서 식대로 구현하는거랑
 * 인구수 차이 조건 제대로 읽고 풀어야했다.
 * 7:22
 */
public class 기출_게리맨더링2 {
	static int N;
	static int[][] A;
	static int[][] aliance;
	static int min = Integer.MAX_VALUE;
	static int maxValue = Integer.MIN_VALUE;
	static int minValue = Integer.MAX_VALUE;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new int[N][N];
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) A[row][col] = sc.nextInt();
		
		//criteria. 모든 기준점에 대해서 조사
		for(int row=0;row<=(N-3);row++) {
			for(int col=1;col<=(N-2);col++){
				division(row,col);
			}
		}
		System.out.println(min);
	}
	private static void division(int x, int y) {//x는 행, y는 열
		for(int d1=1;(d1+x)<=(N-2) && (y-d1)>=0;d1++){//d1 결정
			for(int d2=1;(d1+d2+x)<N && (d2+y)<N;d2++){//d2결정
				//d1,d2 결정 되었고
				aliance = new int[N][N];//분할맵 초기화
				
				for(int i=0;i<=d1;i++) aliance[x+i][y-i]=5;//1번경계
				for(int i=0;i<=d1;i++) aliance[x+d2+i][y+d2-i]=5; //4번경계
				for(int i=0;i<=d2;i++) aliance[x+i][y+i]=5;//2번경계
				for(int i=0;i<=d2;i++) aliance[x+d1+i][y-d1+i]=5; //3번경계
				for(int row=0;row<x;row++) aliance[row][y]=1;//1번구역경계
				for(int row=(x+d1+d2+1);row<N;row++) aliance[row][y-d1+d2]=4;//4번구역경계
				for(int col=0;col<(y-d1);col++) aliance[x+d1][col]=3;
				for(int col=(y+d2+1);col<N;col++) aliance[x+d2][col]=2;
				setNumberBFS(0,0,1);//1번선거구
				setNumberBFS(0,(N-1),2); //2번선거구
				setNumberBFS((N-1),0,3); //3번선거구
				setNumberBFS((N-1),(N-1),4); //4번선거구
				//남은 지역구에대해서는 전부 5
				for(int row=0;row<N;row++) {
					for(int col=0;col<N;col++) {
						if(aliance[row][col]==0) aliance[row][col] = 5;
					}
				}
				countPeople();
			}
		}
	}

	private static void countPeople() {
		int[] people= new int[6];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				people[aliance[row][col]]+=A[row][col];
			}
		}
		maxValue = Integer.MIN_VALUE;
		minValue = Integer.MAX_VALUE;
		for(int i=1;i<=5;i++) {
			if(maxValue<people[i]) maxValue = people[i];
			if(minValue>people[i]) minValue = people[i];
		}
		min = Math.min((maxValue-minValue),min);
	}
	private static void setNumberBFS(int row, int col, int num){
		q.add(new int[]{row,col});
		aliance[row][col]=num;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] curRC = q.poll();
				for(int dir=0;dir<4;dir++){
					int nr = curRC[0]+dr[dir];
					int nc = curRC[1]+dc[dir];
					if(rangeCheck(nr,nc)){//영역만족하고
						if(aliance[nr][nc]==0){//0인 땅이면
							q.add(new int[] {nr,nc});
							aliance[nr][nc] = num;
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0&&nr<N&&nc>=0&&nc<N) return true;
		return false;
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(aliance[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
