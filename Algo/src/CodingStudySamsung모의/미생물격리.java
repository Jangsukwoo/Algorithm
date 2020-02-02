package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 경계에 닿으면 미생물 수가 반띵
1
7 2 9
1 1 7 1
2 1 7 1
5 1 5 4
3 2 8 4
4 3 14 1
3 4 3 3
1 5 8 2
3 5 100 1
5 5 1 1
 */
public class 미생물격리 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] cell;
	static int N,M,K;
	static int microCnt;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};//상하좌우
	static PriorityQueue<Microorganism>[][] pq;
	static int[][] microMap;
	static class Microorganism{
		int row;
		int col;
		int clusterSize;
		int dir;
		public Microorganism(int row, int col, int clusterSize, int dir) {
			this.row = row;
			this.col = col;
			this.clusterSize = clusterSize;
			this.dir = dir;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			isolation();
			countMicro();
			bw.write("#"+testcase+" "+microCnt+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void countMicro(){
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(pq[row][col].size()!=0){
					microCnt+=pq[row][col].peek().clusterSize;
				}
			}
		}
	}
	private static void isolation() {
		for(int time=0;time<M;time++){//M 시간동안 시작
			move();
			check();
		}
	}
	private static void check() {//이동없이 그 자리검사 
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++){
				if(pq[row][col].size()>1){//여러 군집이 있을 경우 
					Microorganism maxMicro = pq[row][col].poll();
					while(!pq[row][col].isEmpty()){
						Microorganism nextMicro = pq[row][col].poll();
						maxMicro.clusterSize+=nextMicro.clusterSize;
					}//다빼서 더하고
					pq[row][col].add(maxMicro);//제일 큰 군집이였던 미생물 다시 삽입
				}else if(pq[row][col].size()==1){//혼자인 경우
					//경계값 검사
					Microorganism currentMicro = pq[row][col].poll();
					if(rangeCheck(currentMicro.row,currentMicro.col)) {
						//약품위에 있으면 
						currentMicro.clusterSize/=2;
						if(currentMicro.clusterSize>0){//살아있는 경우만 
							switch (currentMicro.dir) {//역방향처리
							case 0:
								currentMicro.dir=1;
								break;
							case 1:
								currentMicro.dir=0;
								break;
							case 2:					
								currentMicro.dir=3;
								break;
							case 3:					
								currentMicro.dir=2;
								break;
							}
							pq[row][col].add(currentMicro);
						}
					}else pq[row][col].add(currentMicro);//약품처리 안해도되니까 그냥 다시 넣는다.
				}
			}
		}
	}
	private static boolean rangeCheck(int row, int col) {
		if(row==0 || row==(N-1) || col==0 || col==(N-1)) return true;
		return false;
	}
	private static void move() {
		ArrayList<Microorganism>[][] temp = new ArrayList[N][N];
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) temp[row][col] = new ArrayList<>();

		
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++){
				if(microMap[row][col]==1 && pq[row][col].size()>0){//미생물이 있으면
					Microorganism currentMicro = pq[row][col].poll();
					currentMicro.row+=dr[currentMicro.dir];
					currentMicro.col+=dc[currentMicro.dir];//이동
					temp[currentMicro.row][currentMicro.col].add(currentMicro);
					microMap[row][col]=0;
				}
			}
		}
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(temp[row][col].size()>0) {
					microMap[row][col]=1;
					for(Microorganism m : temp[row][col]) pq[row][col].add(m);
				}
			}
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cell = new int[N][N];
		pq = new PriorityQueue[N][N];
		microMap = new int[N][N];
		microCnt=0;
		Comparator<Microorganism> cp = new Comparator<Microorganism>() {
			@Override
			public int compare(Microorganism o1, Microorganism o2) {
				return -Integer.compare(o1.clusterSize,o2.clusterSize);//내림차순
			}
		};
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				pq[row][col] = new PriorityQueue<Microorganism>(cp);
			}
		}
		for(int i=0,row,col,size,dir;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			size = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken())-1;
			pq[row][col].add(new Microorganism(row, col, size, dir));
			microMap[row][col]=1;
		}
	}
}
