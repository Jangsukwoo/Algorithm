package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 16:35 시작
 * 
 * 숫자로 방문 판단
 */
public class 레이저통신 {
	static int W,H;
	static char[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] memo;
	static Queue<int[]> q = new LinkedList<int[]>();
	static int endR,endC;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		System.out.println(answer);
	}

	private static void bfs(){
		int depth = 0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] currentInfo = q.poll();
				int cr = currentInfo[0];
				int cc = currentInfo[1];
				int cd = currentInfo[2];
				int currentInstall = currentInfo[3];
				if(endCheck(cr,cc)) {
					//System.out.println("끝"+currentInstall);
					answer = Math.min(currentInstall,answer);
				}
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(memo[nr][nc]>currentInstall && map[nr][nc]!='*'){
							if(depth==0){//처음이면
								insertQueue(nr,nc,dir,currentInstall);
							}else if(depth>0){//두번째부터는 방향 검사
								if(cd==dir){//직진 
									insertQueue(nr,nc, dir, currentInstall);
								}else if((cd+2)%4!=dir){//방향 꺾기
									//System.out.println(dir+" "+cd);
									insertQueue(nr,nc, dir, currentInstall+1);
								}
							}
						}
					}
				}
			}
			depth++;
		}
	}

	private static void view() {
		for(int row=0;row<H;row++) {
			for(int col=0;col<W;col++) {
				System.out.print(map[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static boolean endCheck(int cr, int cc) {
		if(cr==endR && cc==endC) return true;
		return false;
	}

	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<H && nc>=0 && nc<W) return true;
		return false;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		memo = new int[H][W];
		boolean flag=false;
		for(int row=0;row<H;row++){
			map[row] = br.readLine().toCharArray();
			for(int col=0;col<W;col++) {
				memo[row][col] = 987654321;
				if(map[row][col]=='C' && flag==false) {
					flag = true;
					insertQueue(row,col,0,0);
				}else if(map[row][col]=='C' && flag) {
					endR = row;
					endC = col;
				}
			}
		}
	}

	private static void insertQueue(int row, int col, int dir, int install){
		q.add(new int[] {row,col,dir,install});
		memo[row][col]=install;
	}
}
