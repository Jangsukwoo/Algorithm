package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 19:58~
 * 21:00
 */
public class 두동전 {
	static int R,C;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] map;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<CoinInformation> q = new LinkedList<CoinInformation>();
	static int answer;
	static class CoinInformation{
		 int coin1R,coin1C;
		 int coin2R,coin2C;
		 boolean coin1, coin2;
		public CoinInformation(int coin1r, int coin1c, int coin2r, int coin2c, boolean coin1, boolean coin2) {
			coin1R = coin1r;
			coin1C = coin1c;
			coin2R = coin2r;
			coin2C = coin2c;
			this.coin1 = coin1;
			this.coin2 = coin2;
		}
		 
	}
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		if(answer==11 || answer==0) System.out.println("-1");
		else System.out.println(answer);
	}
	private static void bfs(){
		int button = 0;
		while(!q.isEmpty()){
			if(button>10) {
				answer = button;
				return;
			}
			int size = q.size();
			for(int i=0;i<size;i++){
				CoinInformation currentInfo = q.poll();
				int cr1 = currentInfo.coin1R;
				int cc1 = currentInfo.coin1C;
				int cr2 = currentInfo.coin2R;
				int cc2 = currentInfo.coin2C;
				boolean coin1 = currentInfo.coin1;
				boolean coin2 = currentInfo.coin2;
			
				if(coin1 && coin2) { //코인 둘다 살아있으면
					for(int dir=0;dir<4;dir++){
						int nr1 = cr1+dr[dir];
						int nc1 = cc1+dc[dir];
						int nr2 = cr2+dr[dir];
						int nc2 = cc2+dc[dir];
						coin1 = currentInfo.coin1;
						coin2 = currentInfo.coin2;
						if(rangCheck(nr1,nc1)) {
							if(map[nr1][nc1]=='#'){//벽이라면
								nr1-=dr[dir];
								nc1-=dc[dir];//그대로	
							}
						}else{//영역 벗어나면 
							nr1-=dr[dir];
							nc1-=dc[dir];//그대로	
							coin1 = false;
						}
						if(rangCheck(nr2,nc2)) {
							if(map[nr2][nc2]=='#'){//벽이라면
								nr2-=dr[dir];
								nc2-=dc[dir];//그대로	
							}
						}else{//영역 벗어나면 
							nr2-=dr[dir];
							nc2-=dc[dir];//그대로	
							coin2 = false;
						}
						//if(visit[nr1][nc1][nr2][nc2]==false) {
							insertQueue(nr1,nc1,nr2,nc2,coin1,coin2);
						//}
					}
				}
				else if(coin1==false && coin2==false) continue;//둘다 떨어진건 무시
				else {
					answer = button;
					return;
				}
			}
			button++;
		}
	}
	private static boolean rangCheck(int nr, int nc){
		if(nr>=0 && nr <R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		boolean flag = false;
		int coin1r = 0,coin1c = 0,coin2r = 0,coin2c = 0;
		for(int row=0;row<R;row++) {
			map[row] = br.readLine().toCharArray();
			for(int col=0;col<C;col++){
				if(map[row][col]=='o' && flag == false) {
					coin1r = row;
					coin1c = col;
					flag = true;
				}else if(map[row][col]=='o' && flag) {
					coin2r=row;
					coin2c=col;
				}
			}
		}
		insertQueue(coin1r, coin1c, coin2r, coin2c, true, true);
	}
	private static void insertQueue(int coin1r, int coin1c, int coin2r, int coin2c, boolean coin1, boolean coin2) {
		q.add(new CoinInformation(coin1r,coin1c,coin2r,coin2c,coin1,coin2));
	}
}
