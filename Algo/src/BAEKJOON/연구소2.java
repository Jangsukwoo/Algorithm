package BAEKJOON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class VirusRC{
	int row;
	int col;
	public VirusRC(int r, int c){
		row = r;
		col = c;
	}
}
public class 연구소2 {
	static int N;
	static int M;
	static int time = Integer.MAX_VALUE;
	static int second;
	static int zero;
	static int zeroSize;
	static int virusRClistSize;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1}; //상우하좌
	static int[] virusCase;
	static int[][] area;
	static int[][] testArea;
	static int sum=0;
	static ArrayList<VirusRC> virusRClist = new ArrayList<VirusRC>();
	static Queue<VirusRC> virusQueue = new LinkedList<VirusRC>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		area = new int[N][N];
		testArea = new int[N][N];
		virusCase = new int[M];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				area[row][col] = sc.nextInt();
				if(area[row][col]==0) zeroSize++;
				if(area[row][col]==2) {
					virusRClist.add(new VirusRC(row,col));
					area[row][col] = 0;
				}
			}	
		}
		virusRClistSize = virusRClist.size(); //바이러스 수
		zeroSize=zeroSize+virusRClistSize-M;//구하려는 zezeroSize
		
		//n = virusRClistSize, r = M
		nCr(0,0);
		
		if(time == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(time);
	}
	private static void nCr(int idx, int cnt){
		if(cnt==M){//각 경우의 수에 대해서 
			setTestMap(); //테스팅 맵 세팅 
			setQueue(); //큐에 넣기 
			BFS(); //확산
			if(zero==zeroSize) time = Math.min(time,second); //최소값 갱신
			return;
		}
		for(int num=idx;num<virusRClistSize;num++){
			virusCase[cnt] = num;
			nCr(num+1,cnt+1);
		}
	}
	private static void setTestMap(){
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) testArea[row][col] = area[row][col];
	}
	private static void setQueue(){//큐에 넣기
		virusQueue.clear(); //큐비우고
		for(int i=0;i<M;i++){
			virusQueue.add(new VirusRC(virusRClist.get(virusCase[i]).row,virusRClist.get(virusCase[i]).col));
			testArea[virusRClist.get(virusCase[i]).row][virusRClist.get(virusCase[i]).col]=2;
		}
	}
	private static void BFS() {
		second=0;
		zero=0;
		while(!virusQueue.isEmpty()){
			int size = virusQueue.size();
			if(zero==zeroSize) break; //다 구했으면 break
			for(int i=0;i<size;i++){
				VirusRC curVirus = virusQueue.poll();
				for(int dir=0;dir<4;dir++){
					int nr = curVirus.row+dr[dir];
					int nc = curVirus.col+dc[dir];
					if(rangeCheck(nr,nc)){
						if(testArea[nr][nc]==0){
							zero++;
							virusQueue.add(new VirusRC(nr,nc));
							testArea[nr][nc]=2;
						}
					}
				}
			}
			second++;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc <N) return true;
		return false;
	}
}
