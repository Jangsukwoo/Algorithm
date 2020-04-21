package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 12:03
 * 이동은 동 서 남 북 상 하로 이동 가능
 */
public class 상범빌딩 {
	static int L,R,C;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][][] building;
	static boolean[][][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[] dl = {-1,1};
	static Queue<int[]> q; 
	static boolean end;
	static boolean escape;
	static int escapeTime;
	public static void main(String[] args) throws NumberFormatException, IOException {
		while(true) {
			setData();
			if(end) break;
			bfs();
			if(escape) System.out.println("Escaped in "+escapeTime+" minute(s).");	
			else System.out.println("Trapped!");
		}
	}
	private static void bfs(){
		int time=0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] currentSangbum = q.poll();
				int cr = currentSangbum[0];
				int cc = currentSangbum[1];
				int cl = currentSangbum[2];
				if(building[cr][cc][cl]=='E') {
					escape = true;
					escapeTime = time;
					return;
				}
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc,cl)) insertQueue(nr,nc,cl);
				}
				for(int dir=0;dir<2;dir++) {
					int nl = cl+dl[dir];
					if(rangeCheck(cr, cc, nl)) insertQueue(cr, cc, nl);
				}
			}
			time++;
		}
	}
	private static boolean rangeCheck(int nr, int nc, int nl) {
		if(nr>=0 && nr<R && nc>=0 && nc<C && nl>=0 && nl<L 
				&& visit[nr][nc][nl]==false
				&& building[nr][nc][nl]!='#') return true;
		return false;
	}
	private static void setData() throws NumberFormatException, IOException{
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		if(L==0 && R==0 && C==0) {
			end = true;
			return;
		}
		building = new char[R][C][L];
		visit = new boolean[R][C][L];
		q = new LinkedList<int[]>();
		escapeTime=0;
		escape = false;
		for(int floor=0;floor<L;floor++){
			for(int row=0;row<R;row++){
				String read = br.readLine();
				for(int col=0;col<C;col++) {
					building[row][col][floor]=read.charAt(col);
					if(building[row][col][floor]=='S'){
						insertQueue(row,col,floor);
					}
				}
			}
			 br.readLine();
		}
	}
	private static void insertQueue(int row, int col, int floor) {
		q.add(new int[] {row,col,floor});
		visit[row][col][floor] = true;
	}
}
