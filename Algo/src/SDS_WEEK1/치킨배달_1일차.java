package SDS_WEEK1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 치킨거리는 집과 가장 가까운 치킨집 사이의 거리를 뜻함
 * 각자의 집은 치킨거리를 가지고 있고 
 * 도시의 치킨거리는 모든 집의 치킨 거리의 합
 * 
 * 치킨거리는 임의의 두칸 r1,c1, r2,c2에서
 * 절대값 r1-r2 + 절대값 c1-c2 
 * 
 * 2<=N<=50
 * 1<=M<=13
 * 
 * 폐업시키지 않을 치킨집의 최대 M개 
 * 도시의 치킨 거리의 최솟값
 * 
 * 0은 빈칸
 * 1은 집
 * 2는 치킨집
 */

public class 치킨배달_1일차 {
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
		for(int p=1;p<=M;p++) {
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
		for(int j=0;j<size;j++){
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
