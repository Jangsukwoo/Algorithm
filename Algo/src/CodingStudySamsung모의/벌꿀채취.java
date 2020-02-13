package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 벌통사이즈 N
 * 꿀을 채취할 수 있는 벌통의 수 M
 * 두 명의 일꾼, 연속된 M개의 벌통 선택
1
4 2 13
6 1 9 7
9 8 5 8
3 4 5 3
8 2 6 7

1
3 3 10
7 2 9
6 6 6
5 5 7
 */
public class 벌꿀채취 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] hive;
	static ArrayList<Comb> gatherHiveDPlist;
	static int N,M,C;
	static int n;
	static int[] pick;
	static int pickR;
	static int[] pickComb;
	static int[][] visit;
	static int maxHoney;
	static int maxProfit;
	static class Comb{
		int startRow;
		int startCol;
		int value;
		public Comb(int startRow, int startCol, int value) {
			this.startRow = startRow;
			this.startCol = startCol;
			this.value = value;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			getHiveDPlist();
			pickHive();
			System.out.println("#"+testcase+" "+maxProfit);
		}
	}
	private static void view() {
		for(int i=0;i<gatherHiveDPlist.size();i++) {
			System.out.println(gatherHiveDPlist.get(i).value);
		}
	}
	private static void pickHive(){
		n = gatherHiveDPlist.size();
		dfs(0,0);
	}
	private static void dfs(int idx, int depth) {
		if(depth==2){
			maxProfit = Math.max(maxProfit,(gatherHiveDPlist.get(pick[0]).value+gatherHiveDPlist.get(pick[1]).value));
			return;
		}
		for(int i=idx;i<n;i++){
			if(visit[gatherHiveDPlist.get(i).startRow][gatherHiveDPlist.get(i).startCol]==0){
				//방문 안한 꿀통이면
				for(int col=gatherHiveDPlist.get(i).startCol;col<(gatherHiveDPlist.get(i).startCol+M);col++) {
					visit[gatherHiveDPlist.get(i).startRow][col]=1;
				}
				pick[depth] = i;
				dfs(i+1,depth+1);
				for(int col=gatherHiveDPlist.get(i).startCol;col<(gatherHiveDPlist.get(i).startCol+M);col++) {
					visit[gatherHiveDPlist.get(i).startRow][col]=0;
				}
			}
		}
	}
	private static void getHiveDPlist() {
		int[] honeyData = new int[M];
		for(int row=0;row<N;row++){
			for(int col=0;col<N;col++){
				int end = (col+(M-1));
				if(end<N){//채취가능한 범위면
					honeyData = new int[M];
					int Midx = 0;
					int max=0;
					for(int start=col;start<=end;start++) honeyData[Midx++] = hive[row][start];
					getCombMaxValue(row,col,honeyData);
//					getMaxHoney(row,col,honeyData,max);//수식으로 해보려니 안된다.
				}
			}
		}
	}
	private static void getCombMaxValue(int sr, int sc,int[] honeyData) {
		maxHoney = 0;
		for(int r=1;r<=M;r++){
			pickR=r;
			pickComb = new int[pickR];
			dfs2(0,0,honeyData);
		}
		if(maxHoney!=0) gatherHiveDPlist.add(new Comb(sr,sc,maxHoney));
		
	}
	private static void dfs2(int idx, int depth,int[] honeyData){
		if(depth==pickR){
			int amount=0;
			int value = 0;
			for(int i=0;i<pickR;i++) {
				amount+=honeyData[pickComb[i]];
				value+=Math.pow(honeyData[pickComb[i]],2);
			}
			if(amount<=C){//갱신
				maxHoney = Math.max(maxHoney,value);
			}else return;
			return;
		}
		for(int i=idx;i<M;i++){
			pickComb[depth]=i;
			dfs2(i+1,depth+1,honeyData);
		}
	}
	private static void getMaxHoney(int sr,int sc,int[] honeyData, int max){		
		Arrays.sort(honeyData);//정렬 
		int honeyDataIdx = honeyData.length-1;
		int value=0;
		while(true){
			if(max<=C){
				value = 0;
				for(int i=0;i<=honeyDataIdx;i++){
					value+=Math.pow(honeyData[i],2);
				}//제곱수 
				break;
			}
			max -=honeyData[honeyDataIdx--];//가장 큰값을 빼본다.
			if(honeyDataIdx==-1) break;;//맨끝에꺼 빼면 의미가 없다	
		}
		int value2=0;
		for(int i=0;i<honeyData.length;i++){
			if(honeyData[i]<=C){
				value2 = (int) Math.max(value2,Math.pow(honeyData[i],2));
			}
		}
		
		value = Math.max(value,value2);
		if(value!=0) gatherHiveDPlist.add(new Comb(sr,sc,value));
	}
	private static void setData() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//벌통사이즈
		M = Integer.parseInt(st.nextToken());//채취용기
		C = Integer.parseInt(st.nextToken());//최대벌꿀
		hive = new int[N][N];
		pick = new int[2];
		visit = new int[N][N];
		maxProfit=0;
		gatherHiveDPlist = new ArrayList<Comb>();
		for(int row=0;row<N;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++){
				hive[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
