package Samsung;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 시작 822
 * 
 * 국경선을 공유하는 두 나라의 인구 차이가 L<=인구수차이<=R 라면 국경선 하루 OPEN
 * 열어야하는 모든 국경선 열었으면 인구 이동 시작
 * 국경선이 열려있으며 인접한 국가들을 연합이라 칭한다.
 * 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수)/(연합을 이루고 있는 칸의 개수)
 * 이 후 연합을 해체하고 모든 국경선을 닫는다.
 * 이 과정이 몇번 일어나는지?
 * 
 * 끝 930
 * 
 * 걸린시간 1시간 10분
 */
class Alliance{
	int allianceID;
	int people;
	int allianceCount;
	public Alliance(int a,int p, int ac) {
		allianceID = a;
		people = p;
		allianceCount = ac;
	}
}
class Nation{
	int row;
	int col; 
	public Nation(int r,int c){
		row = r;
		col = c;
	}
}
public class 기출_인구이동 {
	static int N,L,R; // NxN, L~R
	static int move;
	static int allianceID;
	static boolean flag;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] people;
	static int[][] allianceMap;
	static ArrayList<Alliance> allianceList = new ArrayList<Alliance>();
	static Queue<Nation> q = new LinkedList<Nation>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		people = new int[N][N];
		L = sc.nextInt();
		R = sc.nextInt();
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) people[row][col] =sc.nextInt();	
		while(true) {
			flag = true;
			allianceID = 1;
			allianceList.clear();
			allianceMap = new int[N][N];
			makeAlliance();
			movePeople();
			viewPeople();
			if(flag)break;
			move++;
		}
		System.out.println(move);
	}

	private static void viewPeople() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(people[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void makeAlliance() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(allianceMap[row][col]==0){//소속이 없으면
					q.add(new Nation(row,col));
					allianceList.add(new Alliance(allianceID,people[row][col],1));
					allianceMap[row][col]=allianceID;
					bindUsingBFS();
					allianceID++;			
				}
			}
		}
	}
	private static void bindUsingBFS() {
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				Nation curNation = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = curNation.row + dr[dir];
					int nc = curNation.col + dc[dir];
					if(rangeCheck(nr,nc)){//영역 만족하고
						if(allianceMap[nr][nc]==0){//연합에 소속이 안되어있는 국가면서
							int gap = Math.abs(people[curNation.row][curNation.col] - people[nr][nc]);
							if((gap >= L) && (gap <= R)){//조건에 맞으면 
								if(flag) flag = false;
								q.add(new Nation(nr,nc));
								allianceMap[nr][nc] = allianceID;
								allianceList.get((allianceID-1)).people+=people[nr][nc];
								allianceList.get((allianceID-1)).allianceCount+=1;
							}
						}		
					}
				}
			}
		}
	}
	
	private static void movePeople() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++){
				int alliancePeople = allianceList.get((allianceMap[row][col]-1)).people;
				int allianceCount = allianceList.get((allianceMap[row][col]-1)).allianceCount;
				int newPeople = alliancePeople/allianceCount;
				people[row][col] = newPeople;
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void view() {
		for(int row=0; row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(allianceMap[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
