package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class 미세먼지오랜만에안녕 {
	static class AirCleaner{
		int row,col;
		public AirCleaner(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R,C,T;
	static int[][] room;
	static AirCleaner[] airCleaner;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static ArrayList<int[]> dustList;
	static int[][] diffusionDustMap;
	static int answer;
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		getAnswer();
	}

	private static void simulation() {
		for(int time = 1; time <= T; time++){//T초동안
			spreadDust();//확산
			updateRoom(); //먼지량 갱신
			operateAirCleaner(); //공기청정기 가동
			updateDustList();//먼지 리스트 갱신
		}
	}

	private static void spreadDust(){
		
		diffusionDustMap = new int[R][C];
		
		int size = dustList.size();
		for(int i=0;i<size;i++){
			
			int[] currentDust = dustList.get(i);
			
			int cr = currentDust[0];
			int cc = currentDust[1]; //현재 먼지
			int cd = room[cr][cc];
			int directionCount = 0;
			
			for(int dir=0;dir<4;dir++) { //인접칸 확산
				int nr = cr+dr[dir];
				int nc = cc+dc[dir];
				if(rangeCheck(nr,nc) && room[nr][nc]!=-1) {
					directionCount++;
					diffusionDustMap[nr][nc]+=cd/5;//확산
				}
			}
		
			room[cr][cc] -= ((room[cr][cc]/5) * directionCount); 
		}
	}

	private static void updateRoom(){
		
		for(int row=0;row<R;row++){
			for(int col=0;col<C;col++){
				room[row][col]+=diffusionDustMap[row][col];//먼지량 갱신
			}
		}
	}

	private static void operateAirCleaner() { 
	
		//첫번째 청정기
		for(int row=airCleaner[0].row-1; row>0; row--){
			room[row][0] = room[row-1][0];
		}
		for(int col=0;col<(C-1);col++) {
			room[0][col] = room[0][col+1];
		}
		for(int row=0;row<airCleaner[0].row;row++) {
			room[row][C-1] = room[row+1][C-1];
		}
		for(int col=(C-1); col>(airCleaner[0].col+1);col--){
			room[airCleaner[0].row][col] = room[airCleaner[0].row][col-1];
		}
		room[airCleaner[0].row][airCleaner[0].col+1] = 0;
		
		//두번째 청정기
		for(int row=airCleaner[1].row+1; row<(R-1); row++){
			room[row][0] = room[row+1][0];
		}
		for(int col=0;col<(C-1);col++) {
			room[R-1][col] = room[R-1][col+1];
		}
		for(int row=(R-1);row>airCleaner[1].row;row--) {
			room[row][C-1] = room[row-1][C-1];
		}
		for(int col=(C-1); col>(airCleaner[1].col+1);col--){
			room[airCleaner[1].row][col] = room[airCleaner[1].row][col-1];
		}
		room[airCleaner[1].row][airCleaner[1].col+1] = 0;
	}

	
	
	private static void updateDustList() {
		dustList.clear();
		for(int row=0;row<R;row++){
			for(int col=0;col<C;col++){
				if(room[row][col]>0) {
					dustList.add(new int[] {row,col});
				}
			}
		}
	}
	
	
	private static void getAnswer() {
		for(int row=0;row<R;row++){
			for(int col=0;col<C;col++){
				if(room[row][col]>0) {
					answer+=room[row][col];
				}
			}
		}
		
		System.out.println(answer);
	}

	private static void setData() throws IOException {

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		airCleaner = new AirCleaner[2];
		dustList = new ArrayList<int[]>();
		
		int aircleanerNumber = 0;
		
		for(int row=0;row<R;row++){
			
			st = new StringTokenizer(br.readLine());
			
			for(int col=0;col<C;col++) {
				
				room[row][col] = Integer.parseInt(st.nextToken());
				
				if(room[row][col]==-1){
					if(aircleanerNumber==0) {
						airCleaner[aircleanerNumber++] = new AirCleaner(row, col);
					}
					else {
						airCleaner[aircleanerNumber] = new AirCleaner(row, col);
					}
				}else if(room[row][col]>0){//먼지가 있으면
					dustList.add(new int[] {row,col});
				}
			}
		}
	}
	
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}

}
