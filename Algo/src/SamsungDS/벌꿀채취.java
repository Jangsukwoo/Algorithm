package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 벌꿀채취 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N,M,C;
	static ArrayList<int[]> boxlist;
	static int[] pickBox;
	static int[] pickRoom;
	static int[][] map;
	static int maxProfit;
	static int boxProfit;
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			
			setBoxlist();
			
			getMaxProfit();
			
			System.out.println("#"+testcase+" "+maxProfit);
		}
	}
	private static void getMaxProfit() {
		nCrBox(0,0);//채취장 박스 2개 선택
	}
	private static void nCrBox(int idx, int depth) {
		if(depth==2) { //박스 2개 선정 완료
			if(overlap()) return; //겹치면 끝냄
			//안겹치면
			
			int box1 = getBoxMaxProfit(0); //
			int box2 = getBoxMaxProfit(1); //
			
			maxProfit = Math.max(maxProfit,box1+box2);
			return;
		}
		for(int i=idx;i<boxlist.size();i++) {
			pickBox[depth] = i;
			nCrBox(i+1, depth+1);
		}
	}
	private static int getBoxMaxProfit(int boxNumber){
		boxProfit = 0;
		
		for(int r=1;r<=M;r++) {
			pickRoom = new int[r];
			nCrRoom(0,0,r,boxNumber);
		}
		
		return boxProfit;
	}
	private static void nCrRoom(int idx, int depth, int r, int boxNumber) {
		if(r==depth){
			//칸선택
			int sum=0;
			int boxrow = boxlist.get(pickBox[boxNumber])[0];
			int boxcol = boxlist.get(pickBox[boxNumber])[1];
			int value = 0;
			for(int i=0;i<r;i++) {
				sum+=map[boxrow][boxcol+pickRoom[i]];
				value+=Math.pow(map[boxrow][boxcol+pickRoom[i]],2);
			}
			if(sum>C) return;
			boxProfit = Math.max(boxProfit,value);
			return;
		}
		for(int i=idx;i<M;i++) {
			pickRoom[depth]=i;
			nCrRoom(i+1, depth+1, r, boxNumber);
		}
	}
	private static boolean overlap() {
		int box_cr_1 = boxlist.get(pickBox[0])[0];
		int box_cc_1 = boxlist.get(pickBox[0])[1];
		int box_cr_2 = boxlist.get(pickBox[1])[0];
		int box_cc_2 = boxlist.get(pickBox[1])[1];
		if(box_cr_1!=box_cr_2) return false;
		else {
			boolean[] visit = new boolean[N];
			for(int col=box_cc_1;col<(box_cc_1+M);col++) visit[col] = true;
			for(int col=box_cc_2;col<(box_cc_2+M);col++) if(visit[col]) return true;
		}
		
		return false;
	}
	private static void setBoxlist() {
		for(int row=0;row<N;row++) {
			for(int col=0;col+(M-1)<N;col++) {
				boxlist.add(new int[] {row,col});
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		boxlist = new ArrayList<int[]>();
		pickBox = new int[2];
		maxProfit = 0;
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
/*
2
4 2 13
6 1 9 7    
9 8 5 8
3 4 5 3
8 2 6 7
3 3 10
7 2 9
6 6 6
5 5 7

1
3 3 10
7 2 9
6 6 6
5 5 7
*/