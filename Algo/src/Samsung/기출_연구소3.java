package Samsung;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 비활성 바이러스는 신경쓰지 않고
 * 0인 애를 찾아가는 시간만 중요.
 */

class VirusCoordination{
	int row;
	int col;
	boolean activation;
	public VirusCoordination(int r,int c){
		row = r;
		col = c;
		activation = true;
	}
}
public class 기출_연구소3 {
	static int N;//연구소 크기
	static int M;//바이러스 개수
	static int[][] area;
	static int[][] testingArea;
	static ArrayList<VirusCoordination> virusList = new ArrayList<VirusCoordination>();
	static int[] vCoordination;
	static int[] virusCase;
	static int vCoordinationSize;
	static Queue<VirusCoordination> virusQueue = new LinkedList<VirusCoordination>();
	static int time = Integer.MAX_VALUE;
	static int second;
	static int zeroCount;
	static int zero;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		area = new int[N][N];
		testingArea = new int[N][N];
		for(int row=0;row<N;row++){
			for(int col=0;col<N;col++){
				area[row][col] = sc.nextInt();
				if(area[row][col]==2) virusList.add(new VirusCoordination(row,col));
				else if(area[row][col]==0) zeroCount++;
			}
		}
		
		virusCase = new int[M];
		vCoordinationSize = virusList.size();
		vCoordination = new int[vCoordinationSize];
	
		for(int num=0;num<vCoordinationSize;num++) vCoordination[num] = num;

		nCr(0,0);//모든 경우의 수 조사 n = vCoordinationSize , r = M
		if(time == Integer.MAX_VALUE)System.out.println("-1");
		else System.out.println(time);
	}
	private static void nCr(int idx, int cnt){
		if(cnt==M){//각 경우에 대해
			setTestingArea();//테스팅 맵 세팅
			setActiveVirus();//바이러스 활성화
			BFS();//바이러스 확산
			testingAreaCheck();
			return ;
		}
		for(int vNum=idx;vNum<vCoordinationSize;vNum++){
			virusCase[cnt] = vCoordination[vNum];
			nCr(vNum+1,cnt+1);
		}
	}


	private static void setTestingArea() {
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) testingArea[row][col] = area[row][col];
	}
	private static void setActiveVirus() {
		virusQueue.clear();
		for(int i=0;i<M;i++) {
			testingArea[virusList.get(virusCase[i]).row][virusList.get(virusCase[i]).col] = 3;
			virusQueue.add(new VirusCoordination(virusList.get(virusCase[i]).row, virusList.get(virusCase[i]).col));
		}
	}
	private static void BFS() {
		second=0;
		zero=0;
		
		while(!virusQueue.isEmpty()){ //체크 순서때문에 많이 틀렸음 코드 순서 중요성 명심하기
			int size = virusQueue.size();
			
			if(zero==zeroCount) break; 
			
			for(int i=0;i<size;i++){
				VirusCoordination curVirus = virusQueue.poll();
				for(int dir=0;dir<4;dir++){//4방향 
					
					int nr = curVirus.row+dr[dir];
					int nc = curVirus.col+dc[dir];
					if(rangeCheck(nr,nc)){//영역 체크
						
						if(testingArea[nr][nc]==0 || testingArea[nr][nc]==2){//맨땅 또는 비활성화 바이러스
							if(testingArea[nr][nc]==0) zero++;
							virusQueue.add(new VirusCoordination(nr,nc));
							testingArea[nr][nc] =3; //3은 활성화 바이러스 표시
						}
					}
				}
			}
			second++;		
		}
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(testingArea[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void testingAreaCheck(){
		if(zero==zeroCount) time = Math.min(time,second);
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
}
