package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 엘리베이터 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M;
	static Elevator[] elevators;
	static int A,B;
	static ArrayList<Integer>[] elevatorList;
	static boolean[] visit;
	static int[] beforeElevatorNumber;
	static Queue<Integer> q = new LinkedList<>();
	static int answer;
	static int lastElevatorNumber;
	static class Elevator{
		int startFloor;
		int stopPeriod;
		public Elevator(int startFloor, int stopPeriod) {
			this.startFloor = startFloor;
			this.stopPeriod = stopPeriod;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		int currentElevatorNumber = lastElevatorNumber;
		if(lastElevatorNumber==0) {
			System.out.println("-1");
		}else {
			StringBuilder sb = new StringBuilder();
			while(true) {
				sb.append(currentElevatorNumber+"\n");
				currentElevatorNumber = beforeElevatorNumber[currentElevatorNumber];
				if(currentElevatorNumber==0) break;
			}
			System.out.println(answer);
			System.out.println(sb.toString());
		}
		
		
		
	}
	private static void bfs() {
		/*
		 * 최소 두번 엘리베이터를 타야함	
		 */
		int tranlation=1;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int currentElevatorNumber = q.poll();
				//String currentElevatorNumber_string = current[0]; //현재 엘리베이터 번호
				int start = elevators[currentElevatorNumber].startFloor;
				int period = elevators[currentElevatorNumber].stopPeriod;
				int result = A-elevators[currentElevatorNumber].startFloor;
				if(result==0 || (result>0 && result%elevators[currentElevatorNumber].stopPeriod==0)) {
					answer = tranlation;
					lastElevatorNumber= currentElevatorNumber;
					return;
				}
				for(int floor=start;floor<=N;floor+=period) {
					for(int j=0;j<elevatorList[floor].size();j++) {
						int nextElevator = elevatorList[floor].get(j);
						if(currentElevatorNumber==nextElevator) continue;
						if(visit[nextElevator]==false) {
							q.add(nextElevator);
							visit[nextElevator] = true;
							beforeElevatorNumber[nextElevator] = currentElevatorNumber; 
						}
					}
				}
			}
			tranlation++;
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		elevators = new Elevator[M+1];
		elevatorList = new ArrayList[N+1];
		visit = new boolean[M+1];
		beforeElevatorNumber = new int[M+1];
		for(int floor=1;floor<=N;floor++) elevatorList[floor] = new ArrayList<Integer>();
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int period = Integer.parseInt(st.nextToken());
			elevators[i] =new Elevator(start, period);
			for(int floor=start;floor<=N;floor+=period) elevatorList[floor].add(i);
		}

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		for(int i=0;i<elevatorList[B].size();i++) {
			q.add(elevatorList[B].get(i));
			visit[elevatorList[B].get(i)] = true;
		}
	}
}
