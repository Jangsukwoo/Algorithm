package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미세먼지또안녕 {
	/*
	 * 확산량은 그 칸의 5를 나눈 값이고
	 * 확산 후 그 칸에 남는 미세먼지 양은 확산량x확산된 칸개수
	 */
	static int R,C,T;//data size
	static int remainDust;//남은 미세먼지량
	static int[][] room;//방
	static int cleanerR1,cleanerC1;
	static int cleanerR2,cleanerC2; //공기청정기 위치 
	static Queue<int[]> q;//먼지 넣을 큐
	
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		conutDust();
		System.out.println(remainDust);
	}
	private static void conutDust() {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(room[row][col]>0) {
					remainDust+=room[row][col];
				}
			}
		}
	}
	private static void simulation() {
		int[] dr = {-1,0,1,0};
		int[] dc = {0,1,0,-1};
		int time=0;
		
		while(!q.isEmpty()){
			if(time==T) return;
			int[][] dustMap = new int[R][C];
			int size = q.size();
			time++;
			for(int i=0;i<size;i++){
				int[] dust = q.poll();
				int dustRow = dust[0];
				int dustCol = dust[1];
				int dustAmount = dust[2];
				int adjCnt=0;
				int diffusionAmount = dustAmount/5;
				for(int dir=0;dir<4;dir++) {
					int nr = dustRow+dr[dir];
					int nc = dustCol+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(room[nr][nc]!=-1){
							adjCnt++;
							dustMap[nr][nc]+=diffusionAmount;
						}
					}
				}//다 봤고
				dustMap[dustRow][dustCol]-=(diffusionAmount*adjCnt);
			}
			setRoom(dustMap);
			operationCleaner();
			for(int row=0;row<R;row++) {
				for(int col=0;col<C;col++) {
					if(room[row][col]>0) q.add(new int[] {row,col,room[row][col]});
				}
			}
			
		}
	}
	private static void operationCleaner() {
		//cleaner1
		for(int row=cleanerR1-1;row>0;row--) room[row][0]= room[row-1][0];
		for(int col=0;col<(C-1);col++) room[0][col] = room[0][col+1];
		for(int row=0;row<cleanerR1;row++) room[row][C-1] = room[row+1][C-1];
		for(int col=C-1;col>1;col--) room[cleanerR1][col]= room[cleanerR1][col-1];
		room[cleanerR1][1]=0;

		//cleaner2
		for(int row=cleanerR2+1;row<(R-1);row++) room[row][0]= room[row+1][0];
		for(int col=0;col<(C-1);col++) room[R-1][col] = room[R-1][col+1];
		for(int row=(R-1);row>cleanerR2;row--) room[row][C-1] = room[row-1][C-1];
		for(int col=C-1;col>1;col--) room[cleanerR2][col]= room[cleanerR2][col-1];
		room[cleanerR2][1]=0;
	}
	private static void setRoom(int[][] dustMap) {
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++){
				room[row][col]+=dustMap[row][col];
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		q = new LinkedList<int[]>();
		boolean cleanerInstall = false;
		for(int row=0;row<R;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<C;col++) {
				room[row][col] = Integer.parseInt(st.nextToken());
				if(room[row][col]>0) q.add(new int[] {row,col,room[row][col]});
				if(room[row][col]==-1 && cleanerInstall==false) {
					cleanerInstall = true;
					cleanerR1 = row;
					cleanerC1 = col;
				}else if(room[row][col]==-1 && cleanerInstall) {
					cleanerR2 = row;
					cleanerC2 = col;
				}
			}
		}
	}
}
