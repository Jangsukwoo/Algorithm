package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 말은 장애물을 뛰어넘을 수 있다.
 * 원숭이는 k번만 나이트처럼 이동하고 나머지는 인접한 방향으로 이동할 수 있다.
 * 원숭이의 시작위치는 좌측상단이고
 * 목표는 우측 하단까지 가는것임
 * 최소의 방법으로 좌측상단에서 우측 하단으로 가기 출력
 * 
 * 100%에서 틀렸습니다가 뜨길래 한참 찾다가
 * 애초에 시작부터 도착지점인 경우를 넣어주니 통과함...ㅠ
 */
public class 말이되고픈원숭이 {
	static int W,H,K;
	static int[][] map;
	static int ans;
	static boolean success;
	static int[] dr = {-1,0,1,0,-2,-1,1,2,2,1,-1,-2};
	static int[] dc = {0,1,0,-1,1,2,2,1,-1,-2,-2,-1};//0~3:상우하좌,4~11나이트 8방향
	static class Monkey{
		int row;
		int col;
		int k;
		public Monkey(int row, int col, int k) {
			this.row = row;
			this.col = col;
			this.k = k;
		}
	}
	public static void main(String[] args) throws IOException{
		setData();
		bfs();
		if(success==false) System.out.println("-1");
		else System.out.println(ans);
	}
	private static void bfs(){
		Queue<Monkey> q = new LinkedList<>();
		boolean[][][][] visit = new boolean[H][W][12][K+1];
		q.add(new Monkey(0,0,K));
		int move=0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				Monkey currentMonkey = q.poll();
				int cr = currentMonkey.row;
				int cc = currentMonkey.col;
				int ck = currentMonkey.k;
				if(currentMonkey.row==(H-1) && currentMonkey.col==(W-1)) {
					ans = move;
					success = true;
					return;
				}
				for(int dir=0;dir<4;dir++){
					int nr = currentMonkey.row+dr[dir];
					int nc = currentMonkey.col+dc[dir];
					if(rangeCheck(nr,nc) && visit[cr][cc][dir][ck]==false && map[nr][nc]!=1){
						//영역을 만족하고, 현재 땅에서 가보지 못한 방향이면서, 가려는 땅이 장애물이 아니면
						visit[cr][cc][dir][ck]=true;
						q.add(new Monkey(nr,nc,currentMonkey.k));
					}
				}
				if(currentMonkey.k>0){
					for(int dir=4;dir<12;dir++){
						int nr = currentMonkey.row+dr[dir];
						int nc = currentMonkey.col+dc[dir];
						if(rangeCheck(nr,nc) && visit[cr][cc][dir][ck]==false && map[nr][nc]!=1){
							//영역을 만족하고, 현재 땅에서 가보지 못한 방향이면서, 가려는 땅이 장애물이 아니면
							visit[cr][cc][dir][ck]=true;
							q.add(new Monkey(nr,nc,(currentMonkey.k-1)));
						}
					}
				}
			}
			move++;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<H && nc>=0 && nc<W) return true;
		return false;
	}
	private static void setData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for(int row=0;row<H;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<W;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
