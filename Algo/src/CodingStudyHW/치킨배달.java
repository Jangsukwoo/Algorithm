package CodingStudyHW;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 폐업시키지 않을 치킨집의 최대 M개 
 * 도시의 치킨 거리의 최솟값
 * 
 * 0은 빈칸
 * 1은 집
 * 2는 치킨집
 * 
 * 치킨집 고르고 
 * 집들이랑 거리계산 
 */

public class 치킨배달 {
	static int N;
	static int M;//최대 M개 고른다
	static int[][] city;
	static int minDistance = Integer.MAX_VALUE;
	static ArrayList<int[]> houseList = new ArrayList<>();
	static ArrayList<int[]> chikenHouseList = new ArrayList<>();
	static int n;
	static int r;
	static int[] pick;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		city = new int[N][N];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				city[row][col] = sc.nextInt();
				if(city[row][col]==1) houseList.add(new int[] {row,col});
				if(city[row][col]==2) chikenHouseList.add(new int[] {row,col});
			}
		}
		n = chikenHouseList.size();
		for(int p=1;p<=M;p++) { //치킨집 1개고르기~M개 고르기
			r = p;
			pick = new int[r];
			nCr(0,0);
		}
		System.out.println(minDistance);
	}
	private static void nCr(int depth, int idx) {
		if(depth==r) {//치킨집 pick
			updateDistance();
			return;
		}
		for(int i=idx;i<n;i++) {
			pick[depth] = i;
			nCr(depth+1,i+1);
		}
	}
	private static void updateDistance(){
		int size = houseList.size();
		int cityDist=0;
		for(int j=0;j<size;j++){ //모든 집들과 거리를 계산
			int[] houseRC = houseList.get(j);
			int dist = Integer.MAX_VALUE;
			for(int i=0;i<r;i++) {
				int[] chikenRC = chikenHouseList.get(pick[i]);	
				dist = Math.min(dist, Math.abs(houseRC[0]-chikenRC[0])+Math.abs(houseRC[1]-chikenRC[1]));
			}
			cityDist+=dist;
		}
		minDistance = Math.min(cityDist, minDistance);
	}

}
