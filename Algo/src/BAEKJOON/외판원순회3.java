package BAEKJOON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 외판원 순회 1문제에서 비용 계산이 추가됌
 * 이 역시 비트필드를 이용하여 문제를 푼다.
 */
public class 외판원순회3 {
	static int N;
	static double[][] weightMap;
	static double[][] memo;
	static double INF = (Double.MAX_VALUE/100);
	static ArrayList<int[]> coordinationList = new ArrayList<int[]>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		weightMap = new double[N][N];
		memo = new double[N][1<<N];
		for(int i=0;i<N;i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			coordinationList.add(new int[] {x,y});//리스트의 인덱스는 도시번호, 값은 좌표
		}
		setWeightMap();
		setMemoTable();
		System.out.println(dfs(0,1));
	}
	private static void setMemoTable() {
		for(int i=0;i<N;i++) Arrays.fill(memo[i],-1);
	}
	private static void setWeightMap() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(i!=j){//거리구하기
					int[] cityA  = coordinationList.get(i);
					int[] cityB = coordinationList.get(j);
					int x1 = cityA[0];
					int y1 = cityA[1];
					int x2 = cityB[0];
					int y2 = cityB[1];
					double xDiff = Math.pow((x2-x1),2);
					double yDiff = Math.pow((y2-y1),2);
					double distance = Math.sqrt(xDiff+yDiff);
					weightMap[i][j] = distance;
				}
			}
		}
	}
	private static double dfs(int current, int visit){//visit은 방문정보 비트
		if(memo[current][visit]!=-1) return memo[current][visit];//이미 구한 경로면 
		if(visit==(1<<N)-1){//다 방문했으며
			if(weightMap[current][0]!=0){//경로가 존재하면
				return weightMap[current][0];//마지막 경로값부터 리턴
			}else return INF;//아니면 불가능
		}
		double min = INF;
		for(int city=0;city<N;city++){//각 시티 번호 비트 마스킹
			if((visit&(1<<city))==0 && weightMap[current][city]!=0){ //방문 안했고 자기 자신이 아니면
				double dist = weightMap[current][city]+dfs(city,(visit|(1<<city)));
				if(dist<min) min = dist;
			}	
		}
		memo[current][visit] = min;
		return min;
	}
}
