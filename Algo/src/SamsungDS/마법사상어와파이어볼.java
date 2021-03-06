package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 20하DS2
 */
public class 마법사상어와파이어볼 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,K;
	static ArrayList<Mass> massList;
	static ArrayList<Mass>[][] space;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int answer;
	static class Mass{
		int row;
		int col;
		int mass;
		int speed;
		int dir;
		public Mass(int row, int col, int mass, int speed, int dir) {
			this.row = row;
			this.col = col;
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		System.out.println(answer);
	}
	private static int getAnswer() {
		int sum=0;
		for(int i=0;i<massList.size();i++) {
			sum+=massList.get(i).mass;
		}
		return sum;
	}
	private static void simulation() {
		int year=1;
		while(year<=K) {
			initailaztionSpaceMap();
			setNextCoordination();
			getNewMassList();
			year++;
		}
		answer = getAnswer();
	}
	private static void getNewMassList() {
		massList.clear();
		for(int row=0;row<N;row++){
			for(int col=0;col<N;col++) {
				if(space[row][col].size()>1) {
					int newMass = 0;
					int newSpeed=0;
					int massCount = space[row][col].size();
					int odd=0;
					int even=0;
					for(int i=0;i<space[row][col].size();i++) {
						newMass+=space[row][col].get(i).mass;
						newSpeed+=space[row][col].get(i).speed;
						if(space[row][col].get(i).dir%2==0) even++;
						else odd++;
					}
					newMass/=5;
					newSpeed/=massCount;
					if(newMass>0) {
						if(odd==0 || even==0) {
							for(int dir=0;dir<8;dir+=2) {
								massList.add(new Mass(row, col, newMass, newSpeed, dir));
							}
						}else {
							for(int dir=1;dir<8;dir+=2) {
								massList.add(new Mass(row, col, newMass, newSpeed, dir));
							}
						}
					}
				}else if(space[row][col].size()==1) {
					massList.add(space[row][col].get(0));
				}
			}
		}
	}
	private static void initailaztionSpaceMap() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				space[row][col] = new ArrayList<Mass>();
			}
		}
	}
	private static void setNextCoordination() {
		for(int i=0;i<massList.size();i++) {
			Mass currentMass = massList.get(i);
			int cr = currentMass.row;
			int cc = currentMass.col;
			int cs = currentMass.speed;
			int dir = currentMass.dir;
			int nr = cr;
			int nc = cc;
			for(int s=0;s<cs;s++) {
				nr+=dr[dir];
				nc+=dc[dir];
				if(nr==N) nr=0;
				if(nc==N) nc=0;
				if(nr==-1) nr=(N-1);
				if(nc==-1) nc=(N-1);
			}
			currentMass.row = nr;
			currentMass.col = nc;
			space[nr][nc].add(currentMass);
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		massList = new ArrayList<Mass>();
		space = new ArrayList[N][N];
		for(int i=0;i<M;i++) {
			 st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken())-1;
			int col = Integer.parseInt(st.nextToken())-1;
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			massList.add(new Mass(row, col, mass, speed, dir));
		}
	}
}
