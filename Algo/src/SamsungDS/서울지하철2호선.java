package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 18:50~
 */
public class 서울지하철2호선 {
	static int N;
	static ArrayList<Integer>[] station;
	static boolean[] cycleStation;
	static boolean[] visit;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		getCycleStation(); //순환선 구하기
	}
	private static void getCycleStation() {
		//for(int stationNumber=1;stationNumber<=N;stationNumber++) {
		
	//	dfs(1,0);
		//}
	}
//	private static void dfs(int stationNumber, int beforeStationNumber){
//		visit[stationNumber]=true;	
//		for(int i=0;i<station[stationNumber].size();i++) {
//			int nextStation = station[stationNumber].get(i);
//			if(visit[nextStation]==false && beforeStationNumber!=nextStation) {
//				cycleStation[stationNumber]=dfs(nextStation,stationNumber);
//			}else if(visit[nextStation] && beforeStationNumber!=nextStation) {
//				
//			}
//		}
//	}
	private static void setData() throws NumberFormatException, IOException {
		N= Integer.parseInt(br.readLine());
		station = new ArrayList[N+1];
		visit = new boolean[N+1];
		cycleStation = new boolean[N+1];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			station[to].add(from);
			station[from].add(to);
		}
	}
}
