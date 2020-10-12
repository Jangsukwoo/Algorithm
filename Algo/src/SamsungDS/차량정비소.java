package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
1
1 1 2 1 1
5
7
7 10

1
2 2 6 1 2
3 2
4 2
0 0 1 2 3 4

1
4 1 10 3 1
4 6 4 8
1
0 3 4 4 5 6 9 9 9 10
 */
public class 차량정비소 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,K,A,B;
	static Receipt[] receipts;
	static Repair[] repairs;
	static Customer[] customers;
	static PriorityQueue<Customer> receiptWatingQueue;
	static PriorityQueue<Customer> repairWatingQueue;
	static int answer;
	static class Receipt{
		int id;
		int needWorkTime;
		int remainWorkTime;
		int customerId;
		boolean working;
		public Receipt(int id, int needWorkTime, int remainWorkTime, int customerId, boolean working) {
			this.id = id;
			this.needWorkTime = needWorkTime;
			this.remainWorkTime = remainWorkTime;
			this.customerId = customerId;
			this.working = working;
		}
	}
	static class Repair{
		int id;
		int needWorkTime;
		int remainWorkTime;
		int customerId;
		boolean working;
		public Repair(int id, int needWorkTime, int remainWorkTime, int customerId, boolean working) {
			this.id = id;
			this.needWorkTime = needWorkTime;
			this.remainWorkTime = remainWorkTime;
			this.customerId = customerId;
			this.working = working;
		}
		
	}
	static class Customer{
		int id;
		int arrivalTime;
		int receiptId;
		int repairId;
		int repairArrivalTime;
		public Customer(int id, int arrivalTime, int receiptId, int repairId, int repairArrivalTime) {
			this.id = id;
			this.arrivalTime = arrivalTime;
			this.receiptId = receiptId;
			this.repairId = repairId;
			this.repairArrivalTime = repairArrivalTime;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			getAnswer();
			if(answer==0) answer=-1; 
			System.out.println("#"+testcase+" "+answer);
		}
	}
	private static void getAnswer() {
		int time=0;
		while(doneCheck()){//두 웨이팅 큐 다 끝날때 까지
			/*
			 * 접수처리
			 */
			
			//System.out.println("시간:"+time);
			checkReceiptDone(time);
			checkRepairDone();
		//	System.out.println(receiptWatingQueue.size());
			while(!receiptWatingQueue.isEmpty()) { //접수줄이 있다면
				//접수 가능하면
				if(receiptWatingQueue.peek().arrivalTime<=time) {
					if(possibleAssignReceipt()) assignReceipt();
					else break;//접수 불가능,할당 없이 끝냄
				}else break;
			}
		
			/*
			 * 정비처리
			 */
			while(!repairWatingQueue.isEmpty()) { //접수줄이 있다면
				//정비 가능하면
				if(possibleAssignRepair()) assignRepair();
				else break;//접수 불가능,할당 없이 끝냄
			}
	
			workReceipts();
			workRepairs();
//			System.out.println("시간 : "+time);
//			if(!repairWatingQueue.isEmpty()) {
//				System.out.println("기다리고있는 정비줄 번호");
//				System.out.println(repairWatingQueue.peek().id);
//			}
//			System.out.println("---");
//			for(int i=1;i<=K;i++) {
//				System.out.println("고객번호 "+i);
//				System.out.println("접수번호 "+customers[i].receiptId);
//				System.out.println("정비번호 "+customers[i].repairId);
//				System.out.println();
//			}
//			System.out.println("---");
			time++;
			
		}
		
		for(int id=1;id<=K;id++) {
			if(customers[id].receiptId==A && customers[id].repairId==B) {
				answer+=customers[id].id;
			}
		}
		
	}

	private static boolean doneCheck() {
		for(int i=1;i<=K;i++) {
			if(customers[i].repairId==0) return true;
		}
		return false;
	}
	private static void checkReceiptDone(int time) {
		for(int i=1;i<=N;i++) {
			if(receipts[i].working){
				if(receipts[i].remainWorkTime==0) {
					receipts[i].working=false;
					int assignCustomerId = receipts[i].customerId;
					customers[assignCustomerId].repairArrivalTime = time;//정비줄에 도착한 시간
					repairWatingQueue.add(customers[assignCustomerId]);//정비줄로 보냄
					receipts[i].customerId=0;
				}
			}
		}
	}
	private static void checkRepairDone() {
		for(int i=1;i<=M;i++) {
			if(repairs[i].working) {
				if(repairs[i].remainWorkTime==0) {
					repairs[i].working=false;
				}
			}
		}
	}
	private static void workReceipts() {
		for(int receiptId=1;receiptId<=N;receiptId++) {
			if(receipts[receiptId].working) {
				receipts[receiptId].remainWorkTime--;
			}
		}
	}
	private static void workRepairs() {
		for(int repairId=1;repairId<=M;repairId++) {
			if(repairs[repairId].working) {
				repairs[repairId].remainWorkTime--;
			}
		}
	}
	private static void assignReceipt() { //접수시키기
		int assignReceiptId = 0; //접수될 접수대
		for(int receiptId=1;receiptId<=N;receiptId++) {
			if(receipts[receiptId].working==false) {
				assignReceiptId = receiptId;
				break;
			}
		}
		
		Customer currentCustomer = receiptWatingQueue.poll();
		receipts[assignReceiptId].customerId = currentCustomer.id;
		receipts[assignReceiptId].working = true;
		receipts[assignReceiptId].remainWorkTime = receipts[assignReceiptId].needWorkTime;
		currentCustomer.receiptId = assignReceiptId;
		//System.out.println("담당 접수대: "+assignReceiptId);
		//System.out.println("담당 고객 번호: "+currentCustomer.id);
	}
	private static void assignRepair() {
		int assignRePairId = 0; //접수될 정비대
		for(int repairId=1;repairId<=N;repairId++) {
			if(repairs[repairId].working==false) {
				assignRePairId = repairId;
				break;
			}
		}
		Customer currentCustomer = repairWatingQueue.poll();
		repairs[assignRePairId].customerId = currentCustomer.id;
		//System.out.println("접수된 고객번호"+currentCustomer.id);
		repairs[assignRePairId].working = true;
		repairs[assignRePairId].remainWorkTime = repairs[assignRePairId].needWorkTime;
		currentCustomer.repairId = assignRePairId;
	}
	private static boolean possibleAssignReceipt(){
		for(int i=1;i<=N;i++) if(receipts[i].working==false) return true;	
		return false;
	}
	private static boolean possibleAssignRepair() {
		for(int i=1;i<=M;i++) if(repairs[i].working==false) return true;	
		return false;
	}
	private static void setData() throws IOException {
		answer = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //접수 창구 수
		M = Integer.parseInt(st.nextToken()); //정비 창구 수
		K = Integer.parseInt(st.nextToken()); //방문 고객 수
		A = Integer.parseInt(st.nextToken()); //지갑을 잃어버린 고객이 이용한 접수 창구 번호
		B = Integer.parseInt(st.nextToken()); //지갑을 잃어버린 고객이 이용한 정비 창구 번호 
		receipts = new Receipt[N+1];
		repairs = new Repair[M+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int needWorkTime = Integer.parseInt(st.nextToken());
			receipts[i] = new Receipt(i, needWorkTime, 0, 0, false);
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=M;i++) {
			int needWorkTime = Integer.parseInt(st.nextToken());
			repairs[i] = new Repair(i, needWorkTime, 0, 0, false);
		}
		
		receiptWatingQueue = new PriorityQueue<Customer>(new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				if(o1.arrivalTime==o2.arrivalTime) return Integer.compare(o1.id,o2.id);
				return Integer.compare(o1.arrivalTime,o2.arrivalTime);
			}
		});
	
		
		repairWatingQueue = new PriorityQueue<Customer>(new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				//먼저 기다린 시간이 같다면 접수 창구 아이디 기준
				if(o1.repairArrivalTime==o2.repairArrivalTime) return Integer.compare(o1.receiptId,o2.receiptId);
				return Integer.compare(o1.repairArrivalTime,o2.repairArrivalTime);//먼저 기다린 고객 우선
			}
		});
		
		
		customers = new Customer[K+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=K;i++) {
			int arriavalTime = Integer.parseInt(st.nextToken());
			customers[i] = new Customer(i, arriavalTime,0,0,0);
			receiptWatingQueue.add(customers[i]);
		}
	}
}
