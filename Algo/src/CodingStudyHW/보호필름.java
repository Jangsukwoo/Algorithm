package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 18:00
 */
public class 보호필름 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int D,W,K;//두께,가로,합격기준 K
	static int[][] film;
	static int[][] testFilm;
	static int drugAmount;
	static int[] pickCell;
	static int[] pickDrug;
	static boolean success;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			insertTest();
			System.out.println("#"+testcase+" "+drugAmount);
		}
	}
	private static void insertTest(){
		if(performanceTest(film)) return;
		//약품 투입 없이 성공 가능하면 끝내고 
		//아니면 약품 투입해본다.
		
		for(int drug=1;drug<=D;drug++){//약품 투입량
			pickCell = new int[drug];
			pickDrug= new int[drug];
			drugAmount = drug;
			dfs(0,0);
			if(success) return;
		}
	}
	private static void dfs(int idx, int depth){
		if(success) return;
		if(depth==drugAmount){ //다고름
			
			setTestfilm();

			insertDrug();
			
			if(performanceTest(testFilm)) success = true;
			return;
		}
		for(int i=idx;i<D;i++) {
			pickCell[depth] = i;
			for(int drug=0;drug<=1;drug++){
				pickDrug[depth] = drug;
				dfs(i+1,depth+1);
			}
		}
	}
	private static void insertDrug(){
		for(int i=0;i<drugAmount;i++){
			for(int col=0;col<W;col++) testFilm[pickCell[i]][col] = pickDrug[i];
		}
	}

	private static void setTestfilm() {
		for(int row=0;row<D;row++) {
			for(int col=0;col<W;col++) {
				testFilm[row][col] = film[row][col];
			}
		}
	}
	private static boolean performanceTest(int[][] testFilm){
		for(int col=0;col<W;col++){
			int same=1;
			int currentDrug = testFilm[0][col];
			boolean pass = false;
			for(int row=1;row<D;row++){
				if(same==K) {
					pass = true;
					break;
				}
				if(testFilm[row][col]==currentDrug) same++;
				else {
					same=1;
					currentDrug = testFilm[row][col];
				}
			}
			if(same==K) pass=true;
			if(pass==false) return false;
		}
		return true;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());	
		D = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		film = new int[D][W];
		testFilm = new int[D][W];
		drugAmount= 0;
		success = false;
		for(int row=0;row<D;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<W;col++) {
				film[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
