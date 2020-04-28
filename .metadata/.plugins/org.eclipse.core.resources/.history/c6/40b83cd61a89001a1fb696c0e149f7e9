package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1:40 시작
 * 차근차근
 * 3:10 끝
 * 
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

1
10 17 46
7 5 724 2
7 7 464 3
2 2 827 2
2 4 942 4
4 5 604 4
7 2 382 1
6 5 895 3
8 7 538 4
6 1 299 4
4 7 811 4
3 6 664 2
6 8 868 2
7 6 859 2
4 6 778 2
5 4 842 3
1 3 942 1
1 1 805 3
3 2 350 3
2 5 623 2
5 3 840 1
7 1 308 4
1 8 323 3
2 3 82 3
2 6 115 2
8 3 930 1
6 2 72 1
2 1 290 3
4 8 574 4
8 5 150 3
8 2 287 2
2 8 909 2
2 7 588 2
7 3 30 3
5 8 655 3
3 8 537 1
4 2 350 3
5 6 199 1
5 5 734 2
3 3 788 1
8 4 893 1
1 4 421 4
6 3 616 2
1 2 556 4
7 8 8 1
5 2 702 2
4 4 503 3
 */
public class 미생물격리 {
	static int T;
	static class Microorganism implements Comparable<Microorganism>{
		int row;
		int col;
		int crowd;
		int dir;
		int idx;
		boolean alive;
		public Microorganism(int row, int col,int crowd, int dir, int idx, boolean alive) {
			this.row = row;
			this.col = col;
			this.crowd= crowd;
			this.dir = dir;
			this.idx = idx;
			this.alive = alive;
		}
		@Override
		public int compareTo(Microorganism o) {
			return -Integer.compare(this.crowd,o.crowd);//군집 이 큰 순서 - 내림차순
		}
	}
	static PriorityQueue<Microorganism>[][] cellMap;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,K;
	static int[][] map;
	static Microorganism[] micros;
	static int answer;
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,-1,1};//X,상,하,좌,우
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			timerON();
			getAliveMicroorganusims();
			System.out.println("#"+testcase+" "+answer);
		}
	}
	private static void getAliveMicroorganusims() {
		for(int i=0;i<K;i++) {
			if(micros[i].alive) answer+=micros[i].crowd;
		}
	}
	private static void timerON() {
		int time=1;
		while(time<=M){
			//1.군집 이동 
			moveMicroorganism();
			//2.cellMap에서 군집 체크 후 군집 병합
			mergeMicroorganism();
			time++;
			}
	}
	private static void mergeMicroorganism() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(cellMap[row][col].size()>1){//군집이 여러개 있으면
					Microorganism maxM = cellMap[row][col].poll(); //제일 큰 군집 먼저 뺌
					int sum = maxM.crowd;
					while(!cellMap[row][col].isEmpty()){//나머지 군집들 
						Microorganism nextM = cellMap[row][col].poll();
						sum+=nextM.crowd;//군집 더함
						micros[nextM.idx].crowd=0;
						micros[nextM.idx].alive = false;//없앰
					}
					maxM.crowd = sum;//군집 갱신 후 
					cellMap[row][col].add(micros[maxM.idx]);
				}
			}
		}
	}
	private static void moveMicroorganism() {
		for(int i=0;i<K;i++){
			if(micros[i].alive){//살아있는 군집만 이동 시킨다.
				Microorganism currentM=null;
				ArrayList<Microorganism> templist = new ArrayList<Microorganism>();
				while(!cellMap[micros[i].row][micros[i].col].isEmpty()) {
					Microorganism temp = cellMap[micros[i].row][micros[i].col].poll();
					if(temp.idx==micros[i].idx) {
						currentM = temp;
					}else templist.add(temp);
				}//빨간 약품 처리를 통해 우선순위큐의 순서가 바뀔 수 있는 부분 때문에 이러한 처리를 해줬다.
				// 이부분이 없으니 테스트케이스가 안맞았고
				// 이 코드를 추가하기 까지 30분정도 걸렸다.
				for(int j=0;j<templist.size();j++){
					cellMap[micros[i].row][micros[i].col].add(templist.get(j));
				}
				int cr = currentM.row;
				int cc = currentM.col;
				int ccrowd = currentM.crowd;
				int dir = currentM.dir;
				int nr = cr+dr[dir];
				int nc = cc+dc[dir];
				map[cr][cc] = 0;
				if(rangeCheck(nr,nc)) {//가려고하는 땅이 빨간 약품이면
					ccrowd/=2;
					if(ccrowd>0){//빨간 약품에 갔는데 살아 남는 경우만
						dir = reverseDirection(dir);
						micros[i].dir = dir;
						micros[i].row = nr;
						micros[i].col = nc;
						micros[i].crowd = ccrowd;
						cellMap[nr][nc].add(micros[i]);
					}else {
						micros[i].crowd=0;
						micros[i].alive = false;//죽음
					}
				}else {//가려고하는 땅이 빨간 약품이 아니면 일단 이동
					micros[i].dir = dir;
					micros[i].row = nr;
					micros[i].col = nc;
					micros[i].crowd = ccrowd;
					cellMap[nr][nc].add(micros[i]);
				}
				map[nr][nc]=1;
			}
		}
	}
	private static int reverseDirection(int dir) {
		int reverseDir = 0;
		if(dir%2==0) reverseDir = dir-1;
		if(dir%2!=0) reverseDir = dir+1;
		return reverseDir;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr==0 || nr==(N-1) || nc==0 || nc==(N-1)) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cellMap = new PriorityQueue[N][N];
		micros = new Microorganism[K];
		answer = 0;
		map = new int[N][N];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				cellMap[row][col] = new PriorityQueue<Microorganism>();
			}
		}
		for(int i=0,row,col,crowd,dir;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			crowd = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			map[row][col] = 1;
			micros[i] = new Microorganism(row, col, crowd, dir, i, true);
			cellMap[row][col].add(micros[i]); //cellMap에 투입
		}

	}
}
