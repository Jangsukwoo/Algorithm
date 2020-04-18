package CodingStudyHW;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 차량정비소_주석없이 {
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
			checkReception(time,customerID); 
			workReception(time);//접수처 일처리하기
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
