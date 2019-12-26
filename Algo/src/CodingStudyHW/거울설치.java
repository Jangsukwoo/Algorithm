package CodingStudyHW;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 집에는 문이 두개 있음
 * 한쪽 문에서 다른 쪽 문을 볼 수 있도록 거울을 설치
 * 
 * N by N 
 * 1<=N<=50
 * 
 * #은 문
 * .은 빛이 지나가는 곳
 * *은 빛이 지나갈 수 없는 곳
 * !은 거울을 설치 할 수 있는 위치
 * 
 * 다시
 * 
 * 재시도 
 * -> 진행방향으로 가는 도중에 거울을 만나면 90도 양방향으로 꺾인 상태의 진행방향을 새로 넣어준다. 
 * 
 * 
 * 입력받을때 문 하나 받으면 입구 플래그 체크 넣고 break 걸었는데
 * 한줄에 문이 두개 있을 수 있다는 사실때문에 계속 틀렸습니다가 떴다...
 * ㅠㅠ하 
 */
public class 거울설치 {
	static int N;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static int entranceRow,entranceCol;
	static boolean entranceCheck;
	static int exitRow,exitCol;
	static char[][] house;
	static int[][][] mirrorCountMap;
	static int mirror=987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		house = new char[N][N];
		mirrorCountMap = new int[N][N][4];
		for(int row=0;row<N;row++) {
			String read = sc.nextLine();
			house[row] = read.toCharArray();
			for(int col=0;col<N;col++) {
				if(house[row][col]=='#' && entranceCheck==false) {
					entranceRow = row;
					entranceCol = col;
					entranceCheck = true;
					//break;  <- 이거 ㅠㅠㅠㅠㅠㅠㅠㅠ
					//여기에 브레이크 걸어서 계속 틀리고 난리도 아니였다...눈물 ㅠㅠㅠ
				}else if(house[row][col]=='#' && entranceCheck) {
					exitRow = row;
					exitCol = col;	
					//break;  <- 이거 ㅠㅠㅠㅠㅠㅠㅠㅠ
				}
			}
		}
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++)
				for(int dir=0;dir<4;dir++) mirrorCountMap[row][col][dir]=987654321; //이동맵
		BFS();

		System.out.println(mirror);
	}

	private static void findMinimum() {
		for(int dir=0;dir<4;dir++) {
			mirror = Math.min(mirrorCountMap[exitRow][exitCol][dir],mirror);
		}
	}

	private static void BFS() {
		Queue<int[]> q = new LinkedList<int[]>();
		setStartDirection(q);
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRCD = q.poll();
				int dir = curRCD[2];
				int nr = curRCD[0]+dr[dir];
				int nc = curRCD[1]+dc[dir];
				if(nr == exitRow && nc== exitCol) {
					if(updateCountMap(nr,nc,curRCD[0],curRCD[1],dir)) continue;	//직진
				}
				if(rangeCheck(nr, nc)){//영역 만족 
					if(house[nr][nc]!='*'){//가보지 않은 케이스
						switch (house[nr][nc]) {
						case '.'://맨땅이면 직진
							if(updateCountMap(nr,nc,curRCD[0],curRCD[1],dir)) {
								q.add(new int[] {nr,nc,dir});
								continue;
							}
							break;
						case '!'://거울이면 반사처리, 그냥 직진
							if(updateCountMap(nr,nc,curRCD[0],curRCD[1],dir)) q.add(new int[] {nr,nc,dir});
							reflection(q,nr,nc,curRCD[0],curRCD[1],dir);//반사		
							break;
						}
					}
				}
			}
		}
		findMinimum(); //최소값 찾기
	}

	private static boolean updateCountMap(int nr, int nc, int row, int col, int dir){//아는 길보다 작으면 
		if(mirrorCountMap[nr][nc][dir]>mirrorCountMap[row][col][dir]) {
			mirrorCountMap[nr][nc][dir] = mirrorCountMap[row][col][dir];
			return true;
		}
		return false;
	}

	private static boolean updateCountMapUsingMirror(int nr, int nc, int row, int col, int dir, int RL) {
		//반사처리
		if(mirrorCountMap[nr][nc][(dir+RL)%4]>(mirrorCountMap[row][col][dir]+1)) {
			mirrorCountMap[nr][nc][(dir+RL)%4] = mirrorCountMap[row][col][dir]+1;
			return true;
		}
		return false;
	}
	private static void setStartDirection(Queue<int[]> q) {
		for(int dir=0;dir<4;dir++) {
			mirrorCountMap[entranceRow][entranceCol][dir] = 0;
			q.add(new int[] {entranceRow,entranceCol,dir});//시작좌표에서 4방향 
		}
	}
	private static void reflection(Queue<int[]> q,int nr, int nc, int row, int col, int dir) {
		if(updateCountMapUsingMirror(nr,nc,row,col,dir,1)) q.add(new int[] {nr,nc,(dir+1)%4});
		if(updateCountMapUsingMirror(nr,nc,row,col,dir,3)) q.add(new int[] {nr,nc,(dir+3)%4});
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
}
