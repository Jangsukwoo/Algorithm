import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class ICE{
	int row;
	int col;
	int height;

	ICE(int row,int col,int height){
		this.row = row;
		this.col = col;
		this.height = height;
	}
}

public class 빙산 {
	static int N,M,year;//N행 M열
	static int[][] sea;
	static int[][] copyMap;
	static int[][] zeroCountMap;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static Queue<ICE> iceQueue = new LinkedList<ICE>();
	static boolean devideCheck;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sea = new int[N][M];
		zeroCountMap = new int[N][M];
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				sea[row][col] = sc.nextInt();
				if(sea[row][col]!=0) iceQueue.add(new ICE(row, col, sea[row][col])); //빙산들 큐에 넣기
			}
		}
		
		
		while(!iceQueue.isEmpty()){//BFS		
			int iceSize = iceQueue.size();
			for(int i=0; i<iceSize;i++) {//pop
				ICE curICE = iceQueue.poll();
			
				for(int dir=0;dir<4;dir++) {//4방향 검사
					int nr = curICE.row+dr[dir];
					int nc = curICE.col+dc[dir];	
					if(rangeCheck(nr,nc)){//영역 만족하고
						if(sea[nr][nc]==0) { //바닷물이면
							zeroCountMap[curICE.row][curICE.col]++;
						}
					}
				}//4면 검사 끝	
			}//모든 빙산에 대해 조사끝
			
			for(int row=0;row<N;row++) {//수위차 처리
				for(int col=0;col<M;col++) {
					if(sea[row][col]-zeroCountMap[row][col]>0){
						sea[row][col] = sea[row][col]-zeroCountMap[row][col];
						iceQueue.add(new ICE(row,col,(sea[row][col]-zeroCountMap[row][col])));
 						zeroCountMap[row][col]=0;
					}
					else {
						sea[row][col]=0;
						zeroCountMap[row][col]=0;
					}
				}
			}
			
			year++;
			copyMap = new int[N][M];
			//copyMap = sea.clone(); //클론하면 왜?????????????왜안돼
			for(int row=0;row<N;row++ ) {
				for(int col=0;col<M;col++) {
					copyMap[row][col] = sea[row][col];
				}
			}
			
			int count=0;
			for(int row=0;row<N;row++){//DFS로 덩어리 조사
				for(int col=0;col<M;col++) {
					if(copyMap[row][col]>0) {//한번 다녀왔는데 
						count++;
						if(count==2) { //또있으면
							//System.out.println("두덩이가있다");
							devideCheck=true;
							break;
						}
						dfs(row,col);//출발 
					}
				}
				if(devideCheck) break;
			}
			if(devideCheck) break;
			
		}
		if(!devideCheck) System.out.println(0);
		else System.out.println(year);
	}
	private static void dfs(int row, int col) {
		for(int dir=0;dir<4;dir++) {
			int nr = row+dr[dir];
			int nc = col+dc[dir];
			if(rangeCheck(nr, nc)) {
				if(copyMap[nr][nc]>0) {
					copyMap[nr][nc]=0;
					dfs(nr,nc);
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if((nr>=0 && nr<N) && (nc>=0 && nc<M)) return true;
		return false;
	}
}