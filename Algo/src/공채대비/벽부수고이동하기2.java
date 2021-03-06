package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 21:02~
 * 좌측 상단에서 우측 하단까지 가는 최단거리
 * 3차원 비짓
 */
public class 벽부수고이동하기2 {
	static int R,C,K;
	static char[][] map;
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int answer = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		if(answer==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(answer);
	}
	private static void bfs(){
		int dist = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] currentInfo = q.poll();
				int cr = currentInfo[0];
				int cc = currentInfo[1];
				int ck = currentInfo[2];
				if(cr==R-1 && cc==C-1) {
					answer = dist;
					return;
				}
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(map[nr][nc]=='0'){//벽이 아니면
							if(visit[nr][nc][ck]==false) {
								insertQueue(new int[] {nr,nc,ck});
							}
						}else if(map[nr][nc]=='1'){//벽인 경우
							if(ck>0) {
								if(visit[nr][nc][ck-1]==false) {
									insertQueue(new int[] {nr,nc,ck-1});
								}
							}
						}
					}
				}
			}
			dist++;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C][K+1];
		for(int row=0;row<R;row++) {
			map[row] = br.readLine().toCharArray();
		}
		insertQueue(new int[] {0,0,K});
	}
	private static void insertQueue(int[] info) {
		q.add(info);
		visit[info[0]][info[1]][info[2]] = true;
	}
}
