package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 미세먼지는 각 자리에서 확산하는데 
 * 인접한 네방향으로 확산함 
 * 확산량 : Arc/5
 * Arc에 남는 미세먼지의 양은 확산량x확산된 방향 개수
7 8 0
0 0 0 0 0 0 0 9
0 0 0 0 3 0 0 8
-1 0 5 0 0 0 22 0
-1 8 0 0 0 0 0 0
0 0 0 0 0 10 43 0
0 0 5 0 15 0 0 0
0 0 40 0 0 0 20 0
 */
public class 미세먼지안녕 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int R,C,T;
	static int remainDust;
	static int[][] room;
	static int[][] spreadAmountMap;
	static int cleanerR1,cleanerC1;
	static int cleanerR2,cleanerC2;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<Dust> q;
	static public class Dust{
		int row;
		int col;
		int amount;
		public Dust(int row, int col, int amount) {
			super();
			this.row = row;
			this.col = col;
			this.amount = amount;
		}
		public static void main(String[] args) throws IOException {
			setData();
			spreadSimulation();
			countDust();
			System.out.println(remainDust);
		}
		private static void spreadSimulation() {
			int time=0;
			while(!q.isEmpty()){
				if(time==T) return;
				spreadAmountMap = new int[R][C];
				time++;
				int size = q.size();
				for(int i=0;i<size;i++){//먼지 하나씩 뺸다.
					Dust currentDust = q.poll();
					int adjCnt=0;
					int spreadAmount = currentDust.amount/5;
					for(int dir=0;dir<4;dir++){
						int nr = currentDust.row+dr[dir];
						int nc = currentDust.col+dc[dir];
						if(rangeCheck(nr,nc)){//영역 만족
							if(room[nr][nc]!=-1) {
								adjCnt++;
								spreadAmountMap[nr][nc]+=spreadAmount;
							}
						}
					}
					spreadAmountMap[currentDust.row][currentDust.col]-=(spreadAmount*adjCnt);
				}
				setDust();
				cleaner();
				insertDustToQueue();

			}
		}
		private static void insertDustToQueue() {
			for(int row=0;row<R;row++) {
				for(int col=0;col<C;col++) {
					if(room[row][col]>0) q.add(new Dust(row,col,room[row][col]));
				}
			}
		}
		private static void cleaner(){
			//cleaner1
			for(int row=cleanerR1-1;row>0;row--) room[row][0]= room[row-1][0];
			for(int col=0;col<(C-1);col++) room[0][col] = room[0][col+1];
			for(int row=0;row<cleanerR1;row++) room[row][C-1] = room[row+1][C-1];
			for(int col=C-1;col>1;col--) room[cleanerR1][col]= room[cleanerR1][col-1];
			room[cleanerR1][1]=0;

			//cleaner2
			for(int row=cleanerR2+1;row<(R-1);row++) room[row][0]= room[row+1][0];
			for(int col=0;col<(C-1);col++) room[R-1][col] = room[R-1][col+1];
			for(int row=(R-1);row>cleanerR2;row--) room[row][C-1] = room[row-1][C-1];
			for(int col=C-1;col>1;col--) room[cleanerR2][col]= room[cleanerR2][col-1];
			room[cleanerR2][1]=0;

		}
		private static void setDust() {
			for(int row=0;row<R;row++) {
				for(int col=0;col<C;col++){
					room[row][col]+=spreadAmountMap[row][col];
				}
			}
		}
		private static boolean rangeCheck(int nr, int nc) {
			if(nr>=0 && nr<R && nc>=0 && nc <C) return true;
			return false;
		}
		private static void countDust() {
			for(int row=0;row<R;row++) {
				for(int col=0;col<C;col++) {
					if(room[row][col]>0) remainDust+=room[row][col];
				}
			}
		}
		private static void setData() throws IOException {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			room = new int[R][C];
			q = new LinkedList<Dust>();
			boolean firstCleaner = false;
			for(int row=0;row<R;row++) {
				st = new StringTokenizer(br.readLine());
				for(int col=0;col<C;col++) {
					room[row][col] = Integer.parseInt(st.nextToken());
					if(room[row][col]>0) q.add(new Dust(row, col, room[row][col]));
					if(room[row][col]==-1 && firstCleaner==false) {
						cleanerR1 = row;
						cleanerC1 = col;
						firstCleaner = true;
					}else if(room[row][col]==-1 && firstCleaner==true){
						cleanerR2 = row;
						cleanerC2 = col;
					}
				}
			}
		}
	}
}
