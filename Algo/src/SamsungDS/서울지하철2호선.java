package SamsungDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 양방향 간선에서 사이클 찾는 방법을 어떻게 하면 좋을지?
 * 
 */
public class 서울지하철2호선 {
	static int N;
	static ArrayList<Integer>[] station;
	static boolean[] cycleStation;
	static boolean[] visit;
	static int[] distToCycle;
	static Queue<Integer> q;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		getCycleStation(); //순환선 구하기
		getDistToCycleStation();
		bw.flush();
		bw.close();
	}
	private static void getDistToCycleStation() throws IOException {
		for(int stationNumber=1;stationNumber<=N;stationNumber++) {
			if(!cycleStation[stationNumber]){//사이클이 아닌 정거장에 대해서
				initialization();
				insertQueue(stationNumber);
				distToCycle[stationNumber] = bfs();
			}else distToCycle[stationNumber] = 0; //사이클인 정거장은 0
			bw.write(distToCycle[stationNumber]+" ");
		}
	}
	private static int bfs() {
		int dist = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int currentStation = q.poll();
				if(cycleStation[currentStation]) return dist;//사이클 정거장이면 여기서 끝냄
				for(int next=0;next<station[currentStation].size();next++){
					int nextStation = station[currentStation].get(next);
					if(visit[nextStation]==false) insertQueue(nextStation);
				}
			}
			dist++;
		}
		return 0;
	}
	private static void initialization() {
		q.clear();	
		visit = new boolean[N+1];
	}
	private static void insertQueue(int stationNumber) {
		q.add(stationNumber);
		visit[stationNumber] = true;
	}
	private static void getCycleStation() {
		visit[1] =true; //출발
		for(int stationNumber=1;stationNumber<=N;stationNumber++) {
			if(!cycleStation[stationNumber]){//사이클이 아닌 정거장에 대해서
				visit = new boolean[N+1];
				visit[stationNumber] = true;
				cycleStation[stationNumber] = dfs(stationNumber,0);
			}
		}
		//System.out.println(Arrays.toString(cycleStation));
	}

	private static boolean dfs(int currentStation, int beforeStation) {
		for(int idx=0;idx<station[currentStation].size();idx++){
			int nextStation = station[currentStation].get(idx);
			if(visit[nextStation]==false){//안가봤으면 go
				visit[nextStation] = true;
				dfs(nextStation,currentStation);
			}
			else if(visit[nextStation] && beforeStation!=nextStation) {
				return true;
			}
		}
		
		return false;
	}
	private static void setData() throws NumberFormatException, IOException {
		N= Integer.parseInt(br.readLine());
		station = new ArrayList[N+1];
		visit = new boolean[N+1];
		cycleStation = new boolean[N+1];
		distToCycle= new int[N+1];
		q = new LinkedList<Integer>();
		for(int i=1;i<=N;i++) station[i] = new ArrayList<Integer>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			station[to].add(from);
			station[from].add(to);
		}
	}
}
