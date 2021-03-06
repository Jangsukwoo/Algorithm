package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 20하DS1
 */
public class 컨베이어벨트위의로봇 {
	static class Robot{
		int RobotId;
		int movingWorkId;
		public Robot(int robotId, int movingWorkId) {
			RobotId = robotId;
			this.movingWorkId = movingWorkId;
		}
	}
	static class MovingWork{
		int robotId;
		int capacity;
		int id;
		int idx;
		public MovingWork(int robotId, int capacity, int id, int idx) {
			this.robotId = robotId;
			this.capacity = capacity;
			this.id = id;
			this.idx = idx;
		}
		@Override
		public String toString() {
			return "MovingWork [robotId=" + robotId + ", capacity=" + capacity + ", id=" + id + ", idx=" + idx + "]";
		}
	}
	static int robotId;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Robot> robotList;
	static MovingWork[] movingWorks;
	static int[] movingWorkId;
	static int N,K;
	static int answer;
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		System.out.println(answer);
	}

	private static void simulation() {
		int time=1;
		while(true) {
			moveMovingWork();
			movingDoneCheck();
			if(robotList.size()>0) {//이동할로봇이있으면
				moveRobots();
			}
			if(possibleAddRobot()) {//첫번째 컨베이어확인
				movingWorks[movingWorkId[1]].robotId=robotId;
				movingWorks[movingWorkId[1]].capacity--;
				robotList.add(new Robot(robotId,movingWorks[movingWorkId[1]].id));
				robotId++;
			}
			if(doneCheck()) {
				answer = time;
				break;
			}
			time++;
		}
	}
	private static void movingDoneCheck() {
		if(movingWorks[movingWorkId[N]].robotId>0) {
			int robotId = movingWorks[movingWorkId[N]].robotId;
			int idx = 0;
			movingWorks[movingWorkId[N]].robotId = 0;
			for(int i=0;i<robotList.size();i++) {
				if(robotId==robotList.get(i).RobotId) {
					idx= i;
					break;
				}
			}
			robotList.remove(idx);
		}
	}

	private static boolean doneCheck() {
		int zeroCount = 0;
		for(int id=1;id<=N*2;id++) {
			if(movingWorks[id].capacity==0) zeroCount++;
		}
		if(zeroCount>=K) return true;
		return false;
	}


	private static boolean possibleAddRobot() {
		if(movingWorks[movingWorkId[1]].robotId==0 && movingWorks[movingWorkId[1]].capacity>=1) return true;
		return false;
	}

	private static void moveRobots() {
		boolean escape = false;
		for(int i=0;i<robotList.size();i++) {
			Robot currentRobot = robotList.get(i);
			int currentIdx = movingWorks[currentRobot.movingWorkId].idx;
			int nextIdx = currentIdx+1;
			if(movingWorks[movingWorkId[nextIdx]].robotId==0 && movingWorks[movingWorkId[nextIdx]].capacity>=1) {
				movingWorks[movingWorkId[currentIdx]].robotId=0;
				movingWorks[movingWorkId[nextIdx]].capacity--;
				if(nextIdx==N) {
					escape= true;
					movingWorks[movingWorkId[nextIdx]].robotId=0;
				}else {
					movingWorks[movingWorkId[nextIdx]].robotId=currentRobot.RobotId;
					currentRobot.movingWorkId = movingWorks[movingWorkId[nextIdx]].id;
				}
			}
		}
		if(escape) robotList.remove(0);
	}

	private static void moveMovingWork() {
		int saveId = movingWorkId[2*N];
		for(int i=N*2;i>=2;i--) movingWorkId[i] = movingWorkId[i-1];
		for(int id=1;id<=2*N;id++) {
			movingWorks[id].idx++;
			if(movingWorks[id].idx==2*N+1) movingWorks[id].idx=1;
		}
		movingWorkId[1] =saveId;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		movingWorkId =new int[N*2+1];
		movingWorks = new MovingWork[N*2+1];
		robotList= new ArrayList<Robot>();
		st = new StringTokenizer(br.readLine());
		for(int id=1;id<=N*2;id++) {
			int capa = Integer.parseInt(st.nextToken());
			movingWorkId[id] = id;
			movingWorks[id] = new MovingWork(0,capa,id,id);
		}
		robotId=1;
	}
}
