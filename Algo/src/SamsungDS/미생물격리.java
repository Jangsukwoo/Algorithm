package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 19:00 시작
 */
public class 미생물격리 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	//셀 사이즈 , 격리 시간, 미생물 군집 개수
	static int N,M,K;
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,-1,1};//상 하 좌 우 
	static ArrayList<MicroOrganism>[][] cell;
	static MicroOrganism[] microOrganisms;
	static class MicroOrganism implements Comparable<MicroOrganism>{
		int row;
		int col;
		int population;
		int dir;
		int idx;
		boolean life;
		public MicroOrganism(int row, int col, int population, int dir, boolean life, int idx) {
			this.row = row;
			this.col = col;
			this.population = population;
			this.dir = dir;
			this.life = life;
			this.idx = idx;
		}
		@Override
		public int compareTo(MicroOrganism o) {
			return -Integer.compare(this.population,o.population);
		}
	}
	/*
1
7 2 9   
1 1 7 1 
2 1 7 1
5 1 5 4
3 2 8 4 
4 3 14 1
3 4 3 3 
1 5 8 2 
3 5 100 1
5 5 1 1
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			isolation();
			System.out.println("#"+testcase+" "+getAnswer());
		}
	}
	private static int getAnswer() {
		int sum = 0;
		for(int i=0;i<K;i++) {
			if(microOrganisms[i].life) {
				sum+=microOrganisms[i].population;
			}
		}
		return sum;
	}
	private static void isolation() {
		for(int move=1;move<=M;move++){
			for(int i=0;i<K;i++) moveMicroOrganism(i);
			combineMicroOrganism();
		}
	}
	private static void combineMicroOrganism() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(cell[row][col].size()>1){//하나 이상의 군집이 모여있다면
					Collections.sort(cell[row][col]);//정렬
					int sumPopulation=0;
					for(int j=cell[row][col].size()-1;j>0;j--) {
						sumPopulation+=cell[row][col].get(j).population;
						cell[row][col].get(j).life = false;
						microOrganisms[cell[row][col].get(j).idx].life = false;
						cell[row][col].remove(j);
					}
					cell[row][col].get(0).population+=sumPopulation;
				}
			}
		}
	}
	private static void moveMicroOrganism(int i) {
		if(microOrganisms[i].life) {
			int cr = microOrganisms[i].row;
			int cc = microOrganisms[i].col;
			int cidx = microOrganisms[i].idx;
			int dir = microOrganisms[i].dir;
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(isDrugZone(nr,nc)) { //약품처리 영역인지?
				//약품 처리 영역이면
				microOrganisms[i].population/=2;//반띵
				if(microOrganisms[i].population==0) microOrganisms[i].life = false;
				else {
					if(dir%2==0) dir-=1;
					else dir+=1; //방향 reverse
					microOrganisms[i].dir = dir;
				}
			}
			
			microOrganisms[i].row = nr;
			microOrganisms[i].col = nc;
			if(microOrganisms[i].life) {//살아 있다면
				//이동처리
				for(int idx=0;idx<cell[cr][cc].size();idx++) {
					if(cell[cr][cc].get(idx).idx==cidx) {
						cell[cr][cc].remove(idx);
						break;
					}
				}
				cell[nr][nc].add(microOrganisms[i]);
			}
		}
	}
	private static boolean isDrugZone(int nr, int nc) {
		if(nr==0 || nr==(N-1)|| nc==0 || nc==(N-1)) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cell = new ArrayList[N][N];
		for(int row=0;row<N;row++)
			for(int col=0;col<N;col++) cell[row][col] = new ArrayList<MicroOrganism>();
		microOrganisms = new MicroOrganism[K];
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int population = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			microOrganisms[i] = new MicroOrganism(row, col, population, dir, true,i);
			cell[row][col].add(microOrganisms[i]);
		}
	}
}
