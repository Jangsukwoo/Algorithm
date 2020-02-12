package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 각 격자에 있는 줄기세포들은 생명력 수치를 가지고 있고
 * 각 생명력 수치는 X이다.
 * 
 * X시간 동안 비활성화 상태에서 X시간 후 활성화 상태가 됌 
 * 또한, 활성화된 세포는 X시간 후 죽음
 * 세포가 죽어도 소멸되는 것은 아니고 그 격자판 칸을 계속 차지하게된다.
 * 세포가 활성화되면 주변에 번식을 한다.
 * 
 * 배양 시간이 주어졌을 때
 * 살아있는 세포들 (활성+비활성)의 총 개수는?
 * 배양용기의 크기는 무한하며 가장자리에 닿아서 번식할 수 없는 경우는 없다.
 * 만약 두개 이상의 세포가 동일한 칸에 번식하려 하는 경우 생명력 수치가 가장 높은 세포가 차지함 <- 어떻게 처리?
1
2 2 10
1 1
0 2
5 5 19
3 2 0 3 0
0 3 0 0 0
0 0 0 0 0
0 0 1 0 0
0 0 0 0 2
 */
public class 줄기세포배양_시간초과코드해결 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,K;
	static int[][] cellMap;
	static int[][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static int[][] lifeMap;
	static PriorityQueue<Cell>[][] celllist;
	static int lifeCell=0;
	static Queue<Cell> q;
	static Comparator<Cell> cp;
	static Comparator<Cell> cp2;
	static PriorityQueue<Cell > cellPQ;
	static class Cell{
		int row;
		int col;
		int X;
		int activeTime;
		int inActiveTime;
		boolean active;
		boolean die;
		public Cell(int row, int col, int x, int activeTime, int inActiveTime, boolean active, boolean die) {
			this.row = row;
			this.col = col;
			this.X = x;
			this.activeTime = activeTime;
			this.inActiveTime = inActiveTime;
			this.active = active;
			this.die = die;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			bfsSpread();
			checkLifeCell();
			System.out.println("#"+testcase+" "+lifeCell);
		}
	}

	private static void checkLifeCell(){
		lifeCell = q.size();
	}

	private static void bfsSpread(){
		int time=0;
		celllist = new PriorityQueue[N+(2*K)][M+(2*K)];
		cp2 = new Comparator<Cell>() {
			@Override
			public int compare(Cell o1, Cell o2) {
				if(o1.row==o2.row && o1.col==o2.col) return -Integer.compare(o1.X,o2.X);
				else return -Integer.compare(o1.X,o2.X);
			}
		};
		cellPQ = new PriorityQueue<Cell>(cp2);
		for(int row=0;row<N+(2*K);row++)
			for(int col=0;col<M+(2*K);col++) celllist[row][col] = new PriorityQueue<Cell>(cp);
		while(!q.isEmpty()){
			int size = q.size();
			time++;
			for(int i=0;i<size;i++){
				Cell currentCell = q.poll();
				if(currentCell.active){//활성화상태라면
					activeTimeUpdate(currentCell);
				}else{//비활성화 상태라면
					inactiveTimeUpdate(currentCell);
				}	
			}
			setCell();//생명력이 가장 높은 세포가 튀어나온다.
			if(time==K) return;
		}
	}
	private static void setCell() {
//		for(int row=0;row<N+(K*2);row++) {
//			for(int col=0;col<M+(K*2);col++) {
//				if(celllist[row][col].size()>=1) {
//					q.add(celllist[row][col].poll());//제일 생명력이 높은 세포를 넣어주고
//					celllist[row][col].clear();//임시 우선운위 큐 초기화 
//					visit[row][col] = 1;
//				}else continue;
//			}
//		}//각 자리에 대해서 리스트로 하는 것 보다 
		//pq로 동시에 같은 자리에 가려고 하는 세포들만 따로 pq에 모아서 처리해주니 훨씬 빠르게 처리되었다.
		while(!cellPQ.isEmpty()){
			Cell cell = cellPQ.poll();
			if(visit[cell.row][cell.col]==0){
				visit[cell.row][cell.col] = 1;
				q.add(cell);
			}
		}
	}

	private static void activeTimeUpdate(Cell currentCell) {//증식
		currentCell.activeTime+=1;//activeTime++
		for(int dir=0;dir<4;dir++){//증식
			int nr = currentCell.row+dr[dir];
			int nc = currentCell.col+dc[dir];//4방향
			//여기서 같은 칸처리 필요
			if(visit[nr][nc]==0) {//빈칸이면 증식하는데 여러 세포가 위치할 수 있으므로 후에 결정처리를 위해 celllist에 임시로 넣어둔다.
				//celllist[nr][nc].add(new Cell(nr, nc, currentCell.X, 0,0, false, false));
				cellPQ.add(new Cell(nr, nc, currentCell.X, 0,0, false, false));
			}
		}
		if(currentCell.activeTime==currentCell.X){//현재 세포에 대해서 활성화 시간이 끝났으면 현재 확인한 세포는 큐에 넣지 않고 die 처리 
			currentCell.active=false;
			currentCell.die = true;
			//죽었으니 큐에 안넣는다.
		}else q.add(currentCell);
		
		

	}
	private static void inactiveTimeUpdate(Cell currentCell) {
		currentCell.inActiveTime+=1;//inactiveTime++
		if(currentCell.inActiveTime==currentCell.X){//시간이 됐으면 
			currentCell.active=true;//활성화시켜서 큐에 넣는다.
			q.add(currentCell);
		}else q.add(currentCell);
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//행
		M = Integer.parseInt(st.nextToken());//열
		K = Integer.parseInt(st.nextToken());
		cellMap = new int[N+(2*K)][M+(2*K)];
		visit = new int[N+(2*K)][M+(2*K)];
		lifeMap= new int[N+(2*K)][M+(2*K)];
		lifeCell=0;
		cp = new Comparator<Cell>(){
			@Override
			public int compare(Cell o1, Cell o2) {
				return -Integer.compare(o1.X,o2.X);//생명력 기준 정렬
			}
		};
		q = new LinkedList<Cell>();
		for(int row=0;row<N;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++){
				cellMap[row+((K+N)/2)][col+((K+N)/2)] = Integer.parseInt(st.nextToken());
				if(cellMap[row+((K+N)/2)][col+((K+N)/2)]!=0){//세포면
					q.add(new Cell(row+((K+N)/2),col+((K+N)/2),cellMap[row+((K+N)/2)][col+((K+N)/2)],0,0,false,false));//최초데이터
					visit[row+((K+N)/2)][col+((K+N)/2)] = 1;
				}
			}
		}
	}
}
