package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 11:22 시작
 * 12:44 끝
 */
public class 통나무옮기기 {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean[][][] visit;
	static char[][] plain;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[] rotationR = {-1,-1,0,1,1,1,0,-1};
	static int[] rotationC = {0,1,1,1,0,-1,-1,-1};
	static Log log;
	static Queue<Log> q;
	static int dist;
	static class Log{
		int hr,hc;
		int mr,mc;
		int tr,tc;
		int tranformation;
		public Log(int hr, int hc, int mr, int mc, int tr, int tc, int t) {
			this.hr = hr;
			this.hc = hc;
			this.mr = mr;
			this.mc = mc;
			this.tr = tr;
			this.tc = tc;
			this.tranformation = t;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		bfs();
		System.out.println(dist);
	}
	private static void bfs() {
		q = new LinkedList<Log>();
		insertQueue(log);
		
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				Log currentLog = q.poll(); // 통나무 꺼냄
				if(plain[currentLog.hr][currentLog.hc]=='E'
						&& plain[currentLog.mr][currentLog.mc]=='E'
						&& plain[currentLog.tr][currentLog.tc]=='E') {
					dist = currentLog.tranformation;
					return;
				}
				for(int dir=0;dir<4;dir++){ //4방향
					int nhr = currentLog.hr+dr[dir];
					int nhc = currentLog.hc+dc[dir];
					int nmr = currentLog.mr+dr[dir];
					int nmc = currentLog.mc+dc[dir];
					int ntr = currentLog.tr+dr[dir];
					int ntc = currentLog.tc+dc[dir];
					if(rangeCheck(nhr,nhc,ntr,ntc)) {//머리랑 꼬리만 확인하면됌
						if(nhr==ntr && visit[nmr][nmc][0]==false) {
							//안가봤으면
							if(plain[nhr][nhc]!='1' && plain[nmr][nmc]!='1' && plain[ntr][ntc]!='1') {
								insertQueue(new Log(nhr,nhc,nmr,nmc,ntr,ntc,currentLog.tranformation+1));								
							}
						}else if(nhc == ntc && visit[nmr][nmc][1]==false){
							if(plain[nhr][nhc]!='1' && plain[nmr][nmc]!='1' && plain[ntr][ntc]!='1') {
								insertQueue(new Log(nhr,nhc,nmr,nmc,ntr,ntc,currentLog.tranformation+1));								
							}
						}
					}
 				}
				boolean rotationPossible = true;
				for(int dir=0;dir<8;dir++){
					int nr = currentLog.mr+rotationR[dir];
					int nc = currentLog.mc+rotationC[dir];
					if(rangeCheck(nr,nc)){//일단 영역체크
						if(plain[nr][nc]!='1') continue;
						else {
							rotationPossible = false;
							break;
						}
					}else {
						rotationPossible= false;
						break;
					}
				}
				if(rotationPossible){
					if(currentLog.hr==currentLog.mr && currentLog.mr==currentLog.tr) {
						//가로로 누워있는거
						int nhr = currentLog.mr-1;
						int nhc = currentLog.mc;
						int ntr = currentLog.mr+1;
						int ntc = currentLog.mc;
						if(visit[currentLog.mr][currentLog.mc][1]==false) {
							insertQueue(new Log(nhr,nhc,currentLog.mr,currentLog.mc,ntr,ntc,currentLog.tranformation+1));
						}
					}else {
						//세로로 누워있는거
						int nhr = currentLog.mr;
						int nhc = currentLog.mc-1;
						int ntr = currentLog.mr;
						int ntc = currentLog.mc+1;
						if(visit[currentLog.mr][currentLog.mc][0]==false) {
							insertQueue(new Log(nhr,nhc,currentLog.mr,currentLog.mc,ntr,ntc,currentLog.tranformation+1));
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static boolean rangeCheck(int nhr, int nhc, int ntr, int ntc) {
		if(nhr>=0 && nhr<N
				&& nhc>=0 && nhc<N
				&& ntr>=0 && ntr<N
				&& ntc>=0 && ntc<N) return true;
		return false;
	}
	private static void insertQueue(Log log) {
		//가로는 0 , 세로는 1
		q.add(log);
		if(log.hr==log.tr) { //가로로 누워있는거
			visit[log.mr][log.mc][0] = true;
		}else {
			visit[log.mr][log.mc][1] = true;
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		plain = new char[N][N];
		for(int row=0;row<N;row++) {
			plain[row] = br.readLine().toCharArray();
		}
		int cnt=0;
		visit = new boolean[N][N][2];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(plain[row][col]=='B') {
					if(cnt==0) {
						log = new Log(row,col,0,0,0,0,0);
						cnt++;
					}
					else if(cnt==1) {
						log.mr = row;
						log.mc = col;
						cnt++;
					}
					else if(cnt==2) {
						log.tr = row;
						log.tc = col;
					}
				}
			}
		}
	}
}
