package Samsung;

/*
 * 시작 415
 * 끝 515
 * 걸린시간 1시간
 */
import java.util.Scanner;
class Pipe{
	int headRow;
	int headCol;
	int tailRow;
	int tailCol;
	int set;
	public Pipe(int hr,int hc, int tr, int tc, int s) {
		headRow = hr;
		headCol = hc;
		tailRow = tr;
		tailCol = tc;
		set = s; //0 우 1 대각 2 하
	}
}
public class 상시_파이프옮기기 {
	static int N;
	static int[][] house;
	static int[] dr = {0,1,1};
	static int[] dc = {1,1,0}; // 우,우하,하
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		house = new int[N][N];
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) house[row][col] = sc.nextInt();
		Pipe curpipe= new Pipe(0,1,0,0,0);
		dfs(curpipe);
		System.out.println(cnt);
	}
	
	
	private static void dfs(Pipe curpipe){
		if(curpipe.headRow==(N-1)&& curpipe.headCol==(N-1)){//도착
			cnt++;
			return;
		}
		int nhr = 0;
		int nhc = 0;
		int ntr = 0;
		int ntc = 0;
		switch (curpipe.set){
		case 0: // 파이프 상태 우
			nhr = curpipe.headRow +dr[1];
			nhc = curpipe.headCol +dc[1];
			ntr = curpipe.tailRow +dr[0];
			ntc = curpipe.tailCol +dc[0];
			if(rangeCheck(nhr,nhc)){//일단 가보려고 하는 장소가 만족하고
				//대각
				if(possibleRC(nhr,nhc) && possibleRC(curpipe.headRow+1,curpipe.headCol) && possibleRC(curpipe.headRow,curpipe.headCol+1)){
					//놓을 수 있다면
					dfs(new Pipe(nhr,nhc, ntr, ntc,1));
				}
			}
			nhr = curpipe.headRow +dr[0];
			nhc = curpipe.headCol +dc[0];
			ntr = curpipe.tailRow +dr[0];
			ntc = curpipe.tailCol +dc[0];
			if(rangeCheck(nhr,nhc)){
				if(possibleRC(nhr,nhc)) dfs(new Pipe(nhr,nhc, ntr, ntc,0));	
			}
			break;
		case 1: //파이프 상태 대각
			for(int dir=0;dir<3;dir++) {
				nhr = curpipe.headRow +dr[dir];
				nhc = curpipe.headCol +dc[dir];
				ntr = curpipe.tailRow+dr[1];
				ntc = curpipe.tailCol+dc[1];//꼬리는 무조건 대각으로 감 
				if(rangeCheck(nhr,nhc)){
					if(dir==0) {//우면
						if(possibleRC(nhr,nhc)) {
							dfs(new Pipe(nhr,nhc, ntr, ntc,0));	
						}
					}
					else if(dir==1){ //대각선이면
						if(possibleRC(nhr,nhc) && possibleRC(curpipe.headRow+1,curpipe.headCol) && possibleRC(curpipe.headRow,curpipe.headCol+1)){
							//놓을 수 있다면
							dfs(new Pipe(nhr,nhc, ntr, ntc,1));
						}
					}
					else if(dir==2){//하면
						if(possibleRC(nhr,nhc)) {
							dfs(new Pipe(nhr,nhc, ntr, ntc,2));	
						}
					}
				}
			}
			break;
		case 2: //파이프 상태 하
			nhr = curpipe.headRow +dr[1];
			nhc = curpipe.headCol +dc[1];
			ntr = curpipe.tailRow +dr[2];
			ntc = curpipe.tailCol +dc[2];
			if(rangeCheck(nhr,nhc)){//일단 가보려고 하는 장소가 만족하고
				if(possibleRC(nhr,nhc) && possibleRC(curpipe.headRow+1,curpipe.headCol) && possibleRC(curpipe.headRow,curpipe.headCol+1)){
					//대각으로 놓을 수 있다면
					dfs(new Pipe(nhr,nhc, ntr, ntc,1));
				}
			}
			nhr = curpipe.headRow +dr[2];
			nhc = curpipe.headCol +dc[2];
			ntr = curpipe.tailRow +dr[2];
			ntc = curpipe.tailCol +dc[2];
			if(rangeCheck(nhr,nhc)){
				if(possibleRC(nhr,nhc)) dfs(new Pipe(nhr,nhc, ntr, ntc,2));	
			}
			break;
		}	
	}
	private static boolean possibleRC(int r, int c) {
		if(house[r][c]!=1) return true;
		return false;
	}
	private static boolean rangeCheck(int nhr, int nhc){
		if(nhr>=0 && nhr<N && nhc>=0 && nhc<N) return true;
		return false;
	}

}
