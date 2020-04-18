package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 17:30
 * 고객의 설문지에
 * 접수 창구 번호와
 * 정비 창구 번호가 존재
 * 
 * 차량 정비소에는 N개의 접수창구, M개의 정비 창구가 있음
 * 
 * 첫단계 : 접수 창구에서 고장 접수
 * 두번째 단계 : 정비 창구에서 차량 정비
 * 
 * 접수 창구와 정비 창구 각각은 담당자 별 처리 시간이 다름
 * 
 * 접수 창구 i의 고장 접수,처리 시간 ai
 * 정비 창구 j의 정비 처리 시간 bj
 * 
 * 방문 고객 수 K
 * 
 * 도착하는대로 1번부터 고객 번호 부여 받음
 * 고객 번호 k의 고객이 차량 정비소에 도착하는 시간 tk
 * 정비소에 도착하면
 * 빈 접수 창구가 있는 경우
 * 빈 접수 창구로 가서 고장 접수
 * 접수 창구가 없으면 빈 접수 창구가 생길 때 까지 기다림
 * 
 * 접수 창구 우선순위
 * ->여러 고객이 기다리고있으면
 * 고객 번호가 낮은 순서대로 우선 접수
 * 여러개의 빈 접수 창구-> 가장 번호 작은 창구
 * 
 * 정비 창구 우선순위
 * 먼저 기다린 고객
 * 두 명 이상의 고객이 접수 창구에서 동시 접수 후 정비로 이동
 * 이용했던 접수 창구가 작은 고객이 우선처리
 * 빈 창구가 여러개면 정비 창구가 작은 곳으로 이동.
 * 
 * 시키는대로 구현해야할것같다.
 * 
 * 접수 먼저 구현하고
 * 정비 구현하자 차근차근.
 * 
 * 19:15 끝
 * 1시간 45분..
1
2 2 6 1 2
3 2
4 2
0 0 1 2 3 4

2
1 1 2 1 1
5
7
7 10
2 2 6 1 2
3 2
4 2
0 0 1 2 3 4
 */
public class 차량정비소 {
	static int T;
	static int N,M,K,A,B;
	static int[] reception; //접수처
	static int[] garage; //정비소
	static int[][] receptionTime;
	static int[][] garageTime;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Customer[] customers;
	static PriorityQueue<Customer> receptionWating;
	static PriorityQueue<Customer> garageWating;
	static int needCarePerson;
	static int sumOfCustomerNumber;
	static boolean possible;
	static class Customer{
		int arriveTime;
		int customerNumber;
		int receptionHistory;
		int garageHistory;
		int garageNumberTicket;
		int idx;
		public Customer(int arriveTime,int idx) {
			this.arriveTime = arriveTime;
			this.idx = idx;
		}
		public void setCustomerNumber(int number) {//고객 번호 부여
			this.customerNumber = number;
		}
		public void setReciptionHistory(int number) {//접수 창구 번호 부여
			this.receptionHistory = number;
		}
		public void setgarageHistoryHistory(int number) {//정비 창구 번호 부여
			this.garageHistory = number;
		}
		public void setGarageNumberTicket(int number) {
			this.garageNumberTicket=number;
		}
		public int[] getCareInfomation() {
			int[] info = new int[] {this.receptionHistory,this.garageHistory};
			return info;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			garageSimulation();
			getCandidate();
			if(possible) System.out.println("#"+testcase+" "+sumOfCustomerNumber);
			else System.out.println("#"+testcase+" "+"-1");
		}
	}
	private static void getCandidate() {
		for(int i=0;i<customers.length;i++) {
			int[] info = customers[i].getCareInfomation();
			int receptionNo = info[0];
			int garageIDNo = info[1];
			if(A==receptionNo && B==garageIDNo) {
				possible=true;
				sumOfCustomerNumber+=customers[i].customerNumber;
			}
		}
	}
	private static void garageSimulation() {
		int time=0;
		int customerID = 1;
		while(needCarePerson!=K){
			/*
			 * 	접수 창구의 우선순위는 아래와 같다.
   				① 여러 고객이 기다리고 있는 경우 고객번호가 낮은 순서대로 우선 접수한다.
   				② 빈 창구가 여러 곳인 경우 접수 창구번호가 작은 곳으로 간다.
			 */
			checkReception(time,customerID); 
			workReception(time);//접수처 일처리하기
			/*
			 * 	정비 창구의 우선순위는 아래와 같다.
   				① 먼저 기다리는 고객이 우선한다.
   				② 두 명 이상의 고객들이 접수 창구에서 동시에 접수를 완료하고 정비 창구로 이동한 경우,이용했던 접수 창구번호가 작은 고객이 우선한다.
   				③ 빈 창구가 여러 곳인 경우 정비 창구번호가 작은 곳으로 간다.
			 */
			checkGarage();
			workGarage();//정비소 일처리하기
			
			time++;//시간 증가 
		}
	}
	private static void checkGarage() {
		for(int i=1;i<=M;i++){//정비소 확인
			if(garageTime[i][0]==0){ //정비소에서 놀고 있는 정비원 있으면
				if(garageWating.peek()!=null){//기다리고 있는 고객이 있으면
					int currentCustomerIdx = garageWating.poll().idx;
					garageTime[i][0] = garage[i];//처리 시간 부여
					garageTime[i][1] = currentCustomerIdx; //담당 고객
					customers[currentCustomerIdx].setgarageHistoryHistory(i);
				}
			}
		}
	}
	private static void checkReception(int time, int customerID) {
		for(int i=1;i<=N;i++){//접수처 확인
			if(receptionTime[i][0]==0){ //접수처에서 놀고 있는 상담원
				if(receptionWating.peek()!=null && time>=receptionWating.peek().arriveTime){//도착했거나 미리 도착해있는 고객이 있으면 
					int currentCustomerIdx = receptionWating.poll().idx;
					receptionTime[i][0] = reception[i];//처리 시간 부여
					receptionTime[i][1] = currentCustomerIdx; //담당 고객
					customers[currentCustomerIdx].setCustomerNumber(customerID);//고객 번호 부여 
					customers[currentCustomerIdx].setReciptionHistory(i);
					customerID++;
				}
			}
		}
	}
	private static void workGarage() {
		for(int i=1;i<=N;i++){//접수처 확인
			if(garageTime[i][0]>0){ //일중이면 일한거로 1초 까줌  
				garageTime[i][0]--;
				if(garageTime[i][0]==0){//정비 끝났으면
					needCarePerson++;
				}
			}
		}
	}
	private static void workReception(int time) {
		for(int i=1;i<=N;i++){//접수처 확인
			if(receptionTime[i][0]>0){ //일중이면 일한거로 1초 까줌  
				receptionTime[i][0]--;
				if(receptionTime[i][0]==0){//만약 접수 처리 끝났으면
					customers[receptionTime[i][1]].setGarageNumberTicket(time);//번호표 주고
					garageWating.add(customers[receptionTime[i][1]]);//상담끝난 고객 정비소 웨이팅으로
				}
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		reception = new int[N+1];
		receptionTime = new int[N+1][2];
		garageTime = new int[N+1][2];
		garage = new int[M+1];
		customers = new Customer[K];
		needCarePerson = 0;
		sumOfCustomerNumber = 0;
		possible = false;
		receptionWating = new PriorityQueue<Customer>(new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				return Integer.compare(o1.arriveTime,o2.arriveTime);
			}
		});//기다린 시간 기준으로.
		
		garageWating = new PriorityQueue<Customer>(new Comparator<Customer>(){
			@Override
			public int compare(Customer o1, Customer o2) {
				if(o1.garageNumberTicket==o2.garageNumberTicket) {//정비소에 똑같이 도착한 경우면
					return Integer.compare(o1.receptionHistory,o2.receptionHistory);//접수 창구 기준
				}else {
					//아니면 정비소 대기줄에 먼저 도착한 순서 
					return Integer.compare(o1.garageNumberTicket,o2.garageNumberTicket);
				}
			}
		});//먼저 도착한순으로 , 도착한 시간이 같은 경우는 접수 창구 기준으로.
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) reception[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=M;i++) garage[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			customers[i] = new Customer(Integer.parseInt(st.nextToken()),i);
			receptionWating.add(customers[i]);
		}
		
	}
}
