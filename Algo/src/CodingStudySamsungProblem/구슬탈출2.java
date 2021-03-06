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
 * 빨간 구슬을 구멍을 통해 빼낸다.
 * 단, 파란구슬은 구멍에 빠지면 안된다.
 * 구슬을 움직이는 방법은
 * 판을 상,하,좌,우로 기울인다
 * 
 * 성공 기준은
 * 빨간 구슬만 구멍에 빠지는 것이고
 * 파란 구슬이 빠지거나 
 * 빨강,파랑 구슬이 동시 에 빠지면 실패이다.
 * 구슬이 더이상 움직이지 않을 때 게임은 멈추고
 * 보드의 상태가 주어졌을 때 
 * 최소 몇 번 만에 빨간 구슬을 빼낼 수 있는지 구하라
 * 단, 10번의 시도로 구슬을 꺼낼 수 없으면
 * -1을 출력하고 프로그램을 끝낸다.
 * 
 * 무조건 벽에 둘러쌓여있다.
 * BFS로 풀어보기.
 * 
 * 최소몇번?
 * 
 * 키보드ㅗ장나ㅓ내이ㅟ ㅜㅜㅜㅜㅜㅜㅜㅜ
 */
public class 구슬탈출2{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static char[][] board;
	static boolean[][][][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int N,M;//행,열
	static Queue<BeadInfo> q = new LinkedList<BeadInfo>();
	static int ans;
	static class BeadInfo{ //구슬에 대한 정보 
		//y = 행, x = 여
		int ry; 
		int rx;
		int by;
		int bx;
		int count;
		public BeadInfo(int ry, int rx, int by, int bx, int count) {
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
			this.count = count;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //세로 
		M = Integer.parseInt(st.nextToken()); //가로
		board = new char[N][M];
		visit = new boolean[N][M][N][M];
		BeadInfo bead = new BeadInfo(0,0,0,0,0);
		for(int row=0;row<N;row++) {
			board[row] = br.readLine().toCharArray();
			for(int col=0;col<M;col++) {
				if(board[row][col]=='R') {
					bead.ry = row;
					bead.rx = col;
				}else if(board[row][col]=='B') {
					bead.by = row;
					bead.bx = col;
				}
			}
		}
		visit[bead.ry][bead.rx][bead.by][bead.bx] = true;
		q.add(bead);
		ans = bfs();
		if(ans==0) System.out.println("-1");
		else System.out.println(ans);
	}
	private static int bfs(){
		while(!q.isEmpty()){
			BeadInfo cureentBeadInfo = q.poll();
			if(cureentBeadInfo.count>10) break;
			if(board[cureentBeadInfo.ry][cureentBeadInfo.rx]=='O' && board[cureentBeadInfo.by][cureentBeadInfo.bx]!='O') {
				return cureentBeadInfo.count;
			}
			for(int dir=0;dir<4;dir++){
				int nry = cureentBeadInfo.ry;
				int nrx = cureentBeadInfo.rx;
				int nby = cureentBeadInfo.by;
				int nbx = cureentBeadInfo.bx;
				//빨간구슬 이동
				while(true) {
					if(board[nry][nrx]!='#' && board[nry][nrx]!='O'){//구멍도 아니고 벽도 아니면 일단 굴림 
						nry+=dr[dir];
						nrx+=dc[dir];
					}else {//밟은 땅이  O 또는 #일 경우임 
						if(board[nry][nrx]=='#'){
							nry-=dr[dir];
							nrx-=dc[dir];
						}
						break;//벽이면 뒤로 한칸 후진
					}
				}
				//파란구슬 이동
				while(true) {
					if(board[nby][nbx]!='#' && board[nby][nbx]!='O'){//구멍도 아니고 벽도 아니면 일단 굴림 
						nby+=dr[dir];
						nbx+=dc[dir];
					}else {
						if(board[nby][nbx]=='#'){
							nby-=dr[dir];
							nbx-=dc[dir];
						}
						break;//벽이면 뒤로 한칸 후진
					}
				}

				//만약 빨간 구슬이랑 파랑 구슬이 같은 위치에 도달해있다면 더 멀리서온 구슬은 한칸 뒤로 후진시킨다.
				if(nry==nby && nrx==nbx){
					if(board[nry][nrx]!='O') {
						int redDist = Math.abs(nry-cureentBeadInfo.ry) + Math.abs(nrx-cureentBeadInfo.rx);
						int blueDist = Math.abs(nby-cureentBeadInfo.by) + Math.abs(nbx-cureentBeadInfo.bx);
						if(redDist>blueDist) {
							nry-=dr[dir];
							nrx-=dc[dir];
						}else {
							nby-=dr[dir];
							nbx-=dc[dir];
						}
					}
				}
				if(visit[nry][nrx][nby][nbx]==false){
					visit[nry][nrx][nby][nbx] = true;
					q.add(new BeadInfo(nry,nrx,nby,nbx,cureentBeadInfo.count+1));
				}
			}
		}
		return 0;
	}
}
