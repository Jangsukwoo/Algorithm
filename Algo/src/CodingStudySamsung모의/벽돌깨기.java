package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 구슬을 떨어뜨리는 위치를 바꿔가면서 
 * 최대한 많은 벽돌 깨는 경우를 찾아서
 * 남은 벽돌의 개수를 구하자 
1
3 10 10
0 0 0 0 0 0 0 0 0 0
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1
 */
public class 벽돌깨기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N,W,H;
	static int[] pick;
	static int[][] bricks;
	static int[][] testBricksMap;
	static int remainBricks;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int cnt=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			duplicationPermutation(0);
			System.out.println("#"+testcase+" "+remainBricks);
		}
	}
	private static void duplicationPermutation(int depth) {
		if(depth==N){//공을 떨어뜨릴 위치 선정 
			shootBall();
			cnt++;
			return;
		}
		for(int i=0;i<W;i++) {
			pick[depth] = i;
			duplicationPermutation(depth+1);
		}
	}
	private static void shootBall(){
		copyBricks();
		for(int shoot=0;shoot<N;shoot++) bfsCrush(pick[shoot]);
		updateRemainBricksValue();
	}
	private static void updateRemainBricksValue(){
		int cnt=0;
		for(int row=0;row<H;row++) {
			for(int col=0;col<W;col++) {
				if(testBricksMap[row][col]>0) cnt++;
			}
		}
		remainBricks = Math.min(cnt,remainBricks);
	}
	private static void bfsCrush(int shoot) {	
		boolean[][] markingCrushTarget = new boolean[H][W];
		boolean[][] visit = new boolean[H][W];
		Queue<int[]> q = new LinkedList<int[]>();
		for(int row=0;row<H;row++){
			if(testBricksMap[row][shoot]==1){ //한개짜리
				testBricksMap[row][shoot] = 0;
				break;
			}
			else if(testBricksMap[row][shoot]>1){//폭발 범위가 있을 때는 
				q.add(new int[] {row,shoot,testBricksMap[row][shoot]});
				markingCrushTarget[row][shoot] = true;
				visit[row][shoot] = true;
				while(!q.isEmpty()){//폭발 범위가 있는 벽들만 넣자
					int[] curRC = q.poll();
					for(int dir=0;dir<4;dir++){//4가지 방향 
						int nr = curRC[0];
						int nc = curRC[1];
						for(int explosion = 1; explosion<curRC[2];explosion++){
							nr = nr+dr[dir];
							nc = nc+dc[dir];
							if(rangeCheck(nr,nc)){//영역검사
								markingCrushTarget[nr][nc] = true;
								if(testBricksMap[nr][nc]>1 && visit[nr][nc]==false){//폭발 범위가 있으면
									q.add(new int[] {nr,nc,testBricksMap[nr][nc]});
									visit[nr][nc] = true;
								}
							}
						}
					}
				}
				break;
			}		
		}
		removeBrick(markingCrushTarget);
		fallBricks();
	}
	//2:하
	private static void fallBricks(){//벽돌 낙하 처리 
		for(int col=0;col<W;col++) {
			for(int row=H-1;row>=0;row--){
				if(testBricksMap[row][col]>0){
					int nr = row;
					while(true){
						nr +=dr[2];
						if(rangeCheck(nr,col)){
							if(testBricksMap[nr][col]==0){
								testBricksMap[nr][col] = testBricksMap[nr-dr[2]][col];
								testBricksMap[nr-dr[2]][col] = 0;
							}else break;
						}else break;
					}
				}
			}
		}
	}
	private static void removeBrick(boolean[][] markingCrushTarget) {
		for(int row=0;row<H;row++){
			for(int col=0;col<W;col++) {
				if(markingCrushTarget[row][col]==true) {
					testBricksMap[row][col] = 0;
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<H && nc>=0 && nc<W) return true;
		return false;
	}
	private static void copyBricks() {
		testBricksMap = new int[H][W];
		for(int row=0;row<H;row++) 
			for(int col=0;col<W;col++) testBricksMap[row][col] = bricks[row][col]; 

	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());//열
		H = Integer.parseInt(st.nextToken());//행
		bricks = new int[H][W];
		pick = new int[N];
		remainBricks = Integer.MAX_VALUE;
		for(int row=0;row<H;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<W;col++) {
				bricks[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
