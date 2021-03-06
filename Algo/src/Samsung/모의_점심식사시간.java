package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 17:50
 * 점심식사시간
 * 차근차근짜자!!!
 * 
 * 2차원 격자에 1은 사람
 * S는 계단
 * 각 사람이 현재 위치에서 계단 입구까지 이동하는데 걸리는 시간은 
 * |PR-SR| + |PC-SC|
 * 
 * 이동 완료 시간은 모든 사람들이 계단을 내려가 아래층으로 이동 완료 했을 때임.
 * 계단을 내려가는 시간은 계단 입구에 도착한 후 부터 계단을 완전히 내려갈 때 까지의 시간
 * 입구 도착 후 1분 후 부터 아래칸으로 내려감
 * 계단 위에는 동시에 최대 3명 까지 올라가있음
 * 이미 계단을 3명이 내려가고 있는 경우 
 * 그 중 한명이 완전히 내려갈 때 까지 계단 입구에서 대기 해야함
 * 
 * 모든 사람들이 계단을 내려가 이동이 완료되는 시간이 최소가 되는 경우??
 * 
 * 사람수 P <=10
 * 계단 입구는 반드시 2개
 * 계단의 길이는 2~10
 * 
 * 각 사람들별로 계단 다 정해주고
 * 전수조사 해보자.
 * 
 * 조건
 * 계단 위에는 동시에 최대 3명만 올라가 있을 수 있다.
 * 계단은 반드시 두개
 * 
 * for(int i=0;i<personlist.size();i++) 떄문에 진짜 몇시간을 애먹었는지 모르겠다...
 * 
 * 사이즈만큼 돌리는데 i는 커지고 안에서 pq 데이터를 뺴니 당연히 pq에 잔존 데이터가 남지..................... 하 ㅡㅡ
 * 
 * 21:21 끝..
 * 

2
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0
5
0 0 1 1 0
0 0 1 0 2
0 0 0 1 0
0 1 0 0 0
1 0 5 0 0

1
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0

1
10
0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
3 0 1 0 1 0 0 0 0 2
1 1 0 0 1 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
 */
public class 모의_점심식사시간 {
	static int T;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] room;
	static int N;
	static int personCnt;
	static Person[] persons;
	static int[] pick;
	static Stair[] stairs;
	static Queue<Boolean>[] watingStair;
	static int minTime;
	static int donePersonCnt;
	static int cnt;
	static class Stair{ //계단
		int stairNum;
		int takeTime;
		int row;
		int col;
		ArrayList<Integer> downlist;
		public Stair(int stairNum, int takeTime, int row, int col) {
			this.stairNum = stairNum;
			this.takeTime = takeTime;
			this.row = row;
			this.col = col;
			this.downlist = new ArrayList<Integer>();
		}

	}
	static class Person implements Comparable<Person>{ //사람
		int row;
		int col;
		int pickStair;
		int pickArriveTime;
		int arriveTimeToStairA;
		int arriveTimeTostairB;
		public Person(int row, int col) {
			this.row = row;
			this.col = col;
		}
		public void setArriveTimeToStairA(int stairR,int stairC) {
			arriveTimeToStairA = Math.abs(row-stairR)+Math.abs(col-stairC);
		}
		public void setArriveTimeToStairB(int stairR,int stairC) {
			arriveTimeTostairB = Math.abs(row-stairR)+Math.abs(col-stairC);
		}
		public void setPickStairAndArriveTime(int pickStair) {
			switch (pickStair) {
			case 1:
				pickArriveTime = arriveTimeToStairA;
				break;
			case 2:
				pickArriveTime = arriveTimeTostairB;
				break;
			}
			this.pickStair = pickStair;
		}
		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.pickArriveTime,o.pickArriveTime);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());

		for(int testcase=1;testcase<=T;testcase++){
			setData();
			dfs(0);//가능한 모든 계단 조사 
			System.out.println("#"+testcase+" "+minTime);
		}
	}

	private static void dfs(int depth) {
		if(depth==personCnt){
			go_down_stairs();
			return;
		}
		for(int stair=1;stair<=2;stair++) {
			pick[depth] = stair;
			dfs(depth+1);
		}
	}

	private static void go_down_stairs(){//계단내려가기
		int time = 0; //1분 부터 시작
		PriorityQueue<Person> personList = new PriorityQueue<Person>();
		setPersonListPQandWatingQ(personList);
		donePersonCnt=0;
		while(true){//먼저 계단으로 전부 이동시킨다.
			if(stairs[1].downlist.size()>0 || stairs[2].downlist.size()>0){//계단에 사람이 있으면 내려가기
				down_stair();
			}
			//움직여야할 사람도 없고 계단에도 사람이 없으면 끝난거니까 여기서 최소값 갱신해준다.
			if(personList.isEmpty() && stairs[1].downlist.isEmpty() && stairs[2].downlist.isEmpty()){
				minTime = Math.min(minTime,time);
				return;
			}
			while(!personList.isEmpty()) {//아직 모든 사람이 계단에 도착하지 못함 
				if(personList.peek().pickArriveTime>time) break;
				else {
					if(personList.peek().pickArriveTime==time){//도착 시간이 된 경우
						Person arrivePerson = personList.poll();
						int stairNumber = arrivePerson.pickStair;
						stairCheck(stairNumber); //계단에 도착 처리 
					}
				}
			}
			time++;
		}
	}




	private static void down_stair() {
		for(int stairNumber=1;stairNumber<=2;stairNumber++){
			int arrive=0;
			for(int j=0;j<stairs[stairNumber].downlist.size();j++) {
				if(stairs[stairNumber].downlist.get(j)==0) {
					arrive++;
				}
			}
			for(int j=0;j<arrive;j++) {
				stairs[stairNumber].downlist.remove(0);
				if(!watingStair[stairNumber].isEmpty()){//기다리는 사람이 있는 경우
					while(stairs[stairNumber].downlist.size()<3) {
						watingStair[stairNumber].poll();
						stairs[stairNumber].downlist.add(stairs[stairNumber].takeTime);//추가
					}
				}
			}
		}
		for(int stairNumber=1;stairNumber<=2;stairNumber++){
			for(int j=0;j<stairs[stairNumber].downlist.size();j++) {
				stairs[stairNumber].downlist.set(j,stairs[stairNumber].downlist.get(j)-1);
			}
		}

	}


	private static void stairCheck(int stairNumber) {
		//만약 계단에 3명이 아직 내려가고 있는 중이면 웨이팅에 넣고
		if(stairs[stairNumber].downlist.size()==3) watingStair[stairNumber].add(true);
		else {//3명이 내려가고 있지 않고
			if(watingStair[stairNumber].isEmpty()){//기다리는 사람이 없다면
				stairs[stairNumber].downlist.add(stairs[stairNumber].takeTime);
			}else {//기다리는 사람이 있으면
				while(stairs[stairNumber].downlist.size()<3){
					watingStair[stairNumber].poll();
					stairs[stairNumber].downlist.add(stairs[stairNumber].takeTime);//추가
				}
			}
		}
	}

	private static void setPersonListPQandWatingQ(PriorityQueue<Person> personList) {
		stairs[1].downlist.clear();
		stairs[2].downlist.clear();//초기화 
		for(int i=1;i<=2;i++) watingStair[i] = new LinkedList<Boolean>();
		for(int i=0;i<personCnt;i++) {
			persons[i].setPickStairAndArriveTime(pick[i]);
			personList.add(persons[i]);
		}
	}

	//전처리 
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		room = new int[N][N];
		stairs = new Stair[3];
		int stairNumber = 1;
		personCnt=0;
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				room[row][col] = Integer.parseInt(st.nextToken());
				if(room[row][col]==1) personCnt++;
				else if(room[row][col]>1) {
					stairs[stairNumber] = new Stair(stairNumber,room[row][col],row,col);
					stairNumber++;
				}
			}
		}
		persons = new Person[personCnt];
		pick = new int[personCnt];
		watingStair = new LinkedList[3];
		minTime = Integer.MAX_VALUE;
		int personidx=0;
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(room[row][col]==1) {
					persons[personidx] = new Person(row, col);
					for(int i=1;i<=2;i++) {
						if(i==1) persons[personidx].setArriveTimeToStairA(stairs[i].row,stairs[i].col);
						else if(i==2) persons[personidx].setArriveTimeToStairB(stairs[i].row,stairs[i].col);
					}
					personidx++;
				}
			}
		}
	}
}

