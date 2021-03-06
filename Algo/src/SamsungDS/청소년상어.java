package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 15:30~17:10
 */
public class 청소년상어 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] startSpace;
	static int[] dr = {0,-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,0,-1,-1,-1,0,1,1,1};
	static Fish[] startfishs;
	static int maxSum=0;
	static class Fish{
		int id;
		int row;
		int col;
		int dir;
		boolean life;
		public Fish(int id, int row, int col, int dir, boolean life) {
			this.id = id;
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.life = life;
		}
	}
	/*
	 * 상어가 먹을 수 있는 물고기 번호 합의 최대 값
	 */
	public static void main(String[] args) throws IOException {
		setData();
		dfs(0,0,startSpace,startfishs,0);//좌표,맵,합산값
		System.out.println(maxSum);
	}
	private static void dfs(int sr, int sc, int[][] currentSpace, Fish[] currentFishs, int sum) {
		//step1.상어 도착
		int getFishId = currentSpace[sr][sc];
		currentSpace[sr][sc] = -1; //상어 위치
		int getDir = currentFishs[getFishId].dir;
		currentFishs[getFishId].life=false;
		
		//step2.물고기들 이동
		for(int id=1;id<=16;id++) {
			if(currentFishs[id].life){//살아 있는 물고기만가는데, 빈칸이나 다른 물고기여야함 
				int cr = currentFishs[id].row;
				int cc = currentFishs[id].col;
				int cd = currentFishs[id].dir;
				int sd = cd;
				while(true){//방향 꺾기
					int nr = cr+dr[cd];
					int nc = cc+dc[cd];
					if(rangeCheck(nr,nc) && currentSpace[nr][nc]!=-1) { //영역안에 있고 상어가 아님
						if(currentSpace[nr][nc]>0){//다른 물고기라면 Swap
							int swapTargetFishId = currentSpace[nr][nc];
							currentFishs[id].row = nr;
							currentFishs[id].col = nc;
							currentFishs[swapTargetFishId].row = cr;
							currentFishs[swapTargetFishId].col = cc;
							currentSpace[cr][cc] = swapTargetFishId;
							currentSpace[nr][nc] = id;
						}else if(currentSpace[nr][nc]==0){//빈 땅이면 그냥 감
							currentSpace[cr][cc]=0;
							currentSpace[nr][nc]=id;
							currentFishs[id].row = nr;
							currentFishs[id].col = nc;
						}
						currentFishs[id].dir = cd;
						break;
					}
					cd++;
					if(cd==9) cd=1;
					if(sd == cd) {
						currentFishs[id].dir = cd;
						break;//한바퀴돌았는데도 이동할 곳 못찾았으면 끝냄
					}
				}
			}
		}
		
		//step3.상어 보내보기
		boolean end = true;
		for(int multi=1;multi<=3;multi++) {

			int nr = sr+dr[getDir]*multi;
			int nc = sc+dc[getDir]*multi; //점프 해본 칸에 대해
			if(rangeCheck(nr,nc) && currentSpace[nr][nc]>0){//물고기가 있다면 가볼 대상임
				end = false;
				currentSpace[sr][sc] = 0;
				Fish[] nextFishs = copyFishs(currentFishs);
				int[][] nextSpace = copySpace(currentSpace); 
				dfs(nr, nc, nextSpace, nextFishs, sum+getFishId);
			}
		}
		if(end){
			maxSum = Math.max(sum+getFishId,maxSum);
			return;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<4 && nc>=0 && nc<4) return true;
		return false;
	}
	private static int[][] copySpace(int[][] currentSpace) {
		int[][] space = new int[4][4];
		for(int row=0;row<4;row++) {
			for(int col=0;col<4;col++) {
				space[row][col] = currentSpace[row][col];
			}
		}
		return space;
	}
	private static Fish[] copyFishs(Fish[] currentFishs) {
		Fish[] fishs = new Fish[17];
		for(int id=1;id<=16;id++){
			fishs[id] = new Fish(id, currentFishs[id].row, currentFishs[id].col, currentFishs[id].dir, currentFishs[id].life); 
		}
		return fishs;
	}
	private static void setData() throws IOException {
		startSpace = new int[4][4];
		startfishs = new Fish[17];
		for(int row=0;row<4;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<4;col++) {
				int id = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				startSpace[row][col] = id;
				startfishs[id] = new Fish(id, row, col, dir, true);
				id++;
			}
		}
	}
}
