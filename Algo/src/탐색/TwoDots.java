package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoDots {
	static int R,C;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static char[][] gameMap;
	static boolean[][] visit;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean cycle;
	static int sr,sc;
	public static void main(String[] args) throws IOException {
		setData();
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++){
				sr = row;
				sc = col;
				dfs(row,col,1);
				if(cycle) break;
			}
			if(cycle) break;
		}
		if(cycle) System.out.println("Yes");
		else System.out.println("No");
	}
	private static void dfs(int cr, int cc, int dist) {
		
		if(cycle) return;
		visit[cr][cc] = true; //현재 cr,cc 방문처리
		
		for(int dir=0;dir<4;dir++){//현재 밟은 땅에서 4방향을 볼건데
			
			int nr = cr+dr[dir];
			int nc = cc+dc[dir]; 
			
			if(rangeCheck(nr,nc) && sameCheck(cr,cc,nr,nc)){ //가려는 방향의 다음 땅이 영역 안에있고, 같은 알파벳인 경우
				
				if(visit[nr][nc]==false) { //방문을 하지 않은 땅이면
					visit[nr][nc] = true; //방문 처리 후 
					dfs(nr,nc,dist+1); //이동
					visit[nr][nc] = false; //방문 처리 취소
				}else if(dist>=4 && nr==sr && nc==sc){ //4번 이상 움직였고  시작 지점과 같으면 cycle
					cycle = true; //cycle true
					break;
				}
				
			}
		}
	}
	private static boolean sameCheck(int nr, int nc, int cr, int cc) {
		if(gameMap[nr][nc]==gameMap[cr][cc]) return true;
		return false;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		gameMap = new char[R][C];
		visit = new boolean[R][C];
		for(int row=0;row<R;row++) gameMap[row] = br.readLine().toCharArray();
	}
}
