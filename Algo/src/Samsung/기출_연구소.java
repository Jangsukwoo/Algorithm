package Samsung;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class BlankArea{
	int row;
	int col;
	public BlankArea(int r, int c){
		row = r;
		col = c;
	}
}
class Virus{
	int row;
	int col;
	public Virus(int r, int c) {
		row = r;
		col = c;
	}
}
public class 기출_연구소 {
	static int rowSize; //세로
	static int colSize; //가로
	static int[][] area; //영역
	static int[][] testingArea;
	static int maxSafeArea =0; //최대값
	static ArrayList<BlankArea> blankList = new ArrayList<BlankArea>();// 0의 개수
	static ArrayList<Virus> virusList = new ArrayList<Virus>(); //바이러스 리스트
	static Queue<Virus> virusQueue = new LinkedList<Virus>(); //BFS를 위한 바이러스 큐 
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		rowSize = sc.nextInt();
		colSize = sc.nextInt();
		area = new int[rowSize][colSize];
		testingArea = new int[rowSize][colSize];
		
		for(int row=0;row<rowSize;row++) {
			for(int col=0;col<colSize;col++) {
				area[row][col] = sc.nextInt();
				if(area[row][col]==0) blankList.add(new BlankArea(row,col));
				if(area[row][col]==2) virusList.add(new Virus(row,col));
			}
		}//입력 끝

		int blankSize = blankList.size();//n
		
		//문제에 의해 r = 3
		//nCr 모든 경우의수 (n = blankSize, r = 3) 탐색
		int cnt=0;
		for(int i=0;i<(blankSize-2);i++){
			for(int j=(i+1);j<(blankSize-1);j++){
				for(int k=(j+1);k<blankSize;k++){
					setTestingArea();
					setWall(i,j,k);
					setQueue();
					virusBFS();
					countSafeArea();
				}
			}
		}
		System.out.println(maxSafeArea);
	}

	private static void setTestingArea() {	
		for(int row=0;row<rowSize;row++)
			for(int col=0;col<colSize;col++) testingArea[row][col] = area[row][col];
	}
	
	private static void setWall(int i, int j, int k) {
		testingArea[blankList.get(i).row][blankList.get(i).col]=1;
		testingArea[blankList.get(j).row][blankList.get(j).col]=1;
		testingArea[blankList.get(k).row][blankList.get(k).col]=1;
	}
	
	private static void setQueue() {
		for(int i=0;i<virusList.size();i++) virusQueue.add(virusList.get(i));
	}
	private static void virusBFS() {
		while(!virusQueue.isEmpty()){
			int virusQueueSize = virusQueue.size();
			for(int i=0;i<virusQueueSize;i++){
				Virus curV = virusQueue.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = curV.row+dr[dir];
					int nc = curV.col+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(testingArea[nr][nc]==0) {
							virusQueue.add(new Virus(nr,nc));
							testingArea[nr][nc]=2;
						}
					}
				}
			}
		}
	}
	private static void countSafeArea() {
		int cnt =0;
		for(int row=0;row<rowSize;row++)
			for(int col=0;col<colSize;col++) if(testingArea[row][col]==0) cnt++;
		
		maxSafeArea = Math.max(maxSafeArea,cnt);
	}
	private static boolean rangeCheck(int nr, int nc){
		if(nr>=0 && nr<rowSize && nc>=0 && nc<colSize) return true;
		return false;
	}
}


