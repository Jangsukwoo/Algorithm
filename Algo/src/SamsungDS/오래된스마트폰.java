package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 오래된스마트폰 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,O,M,W;
	static int[] possibleNumber;
	static int[] possibleOperator;
	static boolean[][] visit;
	static boolean[] permutationVisit;
	static ArrayList<String> possibleNumberSet;
	static PriorityQueue<PushValue> q;
	static class PushValue{
		String number_string;
		int push;
		boolean operation;
		public PushValue(String number_string, int push, boolean operation) {
			this.number_string = number_string;
			this.push = push;
			this.operation = operation;
		}
	}
	static int answer;
	/*
	 * 최소 터치 회수 
	 * 1 = +
	 * 2 + -
	 * 3 = *
	 * 4 = /
	 */

	/*
1
6 4 5
0 1 2 3 4 7
1 2 3 4
5

1

1
7 3 6 
1 8 0 2 6 7 9
2 1 4
91
	 */
	public static void main(String[] args) throws NumberFormatException, IOException{
		q = new PriorityQueue<>(new Comparator<PushValue>() {
			@Override
			public int compare(PushValue o1, PushValue o2) {
				return Integer.compare(o1.push,o2.push);
			}
		});

		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {

			setData();

			possibleNumberSetting(); //Permutaion 

			//for(String data : possibleNumberSet) System.out.println(data);

			initialization(); //초기 큐 세팅

			answer = bfs();
			System.out.println(answer);
		}
	}

	private static int bfs() {
		while(!q.isEmpty()){ //각 레벨에서 가능한 모든 경우를 넣는다.
			int size = q.size();
			for(int i=0;i<size;i++){

				PushValue currentPushValue = q.poll();
				if(currentPushValue.push>M) continue;//터치 회수가 M이 넘으면 Skip
				String currentNumber_string =currentPushValue.number_string;

				int currentNumber = Integer.parseInt(currentNumber_string);

				if(currentNumber==W) {

					if(currentPushValue.operation) return currentPushValue.push+1; //발견 즉시 리턴
					else return currentPushValue.push;
				}

				if(currentNumber_string.charAt(0)=='0') continue; //앞자리 0은 건너뜀

				nextCase(currentPushValue);//뒤에 숫자 붙여보는 경우

			}

		}
		return -1;
	}

	private static void nextCase(PushValue currentPushValue){

		int size = possibleNumberSet.size();

		for(int i=0;i<size;i++) {	

			String currentNumber_String = currentPushValue.number_string;
			String rightNumber = possibleNumberSet.get(i);
			String pasteNumber_String = currentNumber_String+rightNumber;


			///1. 붙여보기
			//한번더 확인하지 못한 숫자인지, 범위를 벗어나지 않는지 유효성을 검증하고 큐에 담는다.

			if((pasteNumber_String.length()<=3 
					&& currentPushValue.push+rightNumber.length()<=20
					&& visitCheck(pasteNumber_String,currentPushValue.push+rightNumber.length()))){
				PushValue nextPushValue = new PushValue(pasteNumber_String,
						(currentPushValue.push+rightNumber.length()),
						currentPushValue.operation);
				insertQueue(nextPushValue,currentPushValue.push+rightNumber.length());
			}

			/*
			 * 1 = +
			 * 2 = -
			 * 3 = *
			 * 4 = /
			 */
			//연산해보기
			for(int j=0;j<O;j++){
				String operatedNumber_String = "";
				int leftValue = Integer.parseInt(currentNumber_String);
				int rightValue = Integer.parseInt(rightNumber);
				int result = 0;
				switch (possibleOperator[j]) {
				case 1: // +
					result = leftValue+rightValue;
					break;
				case 2: // -
					result = leftValue-rightValue;
					break;
				case 3: // *
					result = leftValue*rightValue;
					break;
				case 4: // /
					result = leftValue/rightValue;
					break;
				}
				operatedNumber_String = Integer.toString(result);
				if(result>=0 && operatedNumber_String.length()<=3 
						&& currentPushValue.push+rightNumber.length()<=19
						&& visitCheck(operatedNumber_String,currentPushValue.push+rightNumber.length()+1)) {
					PushValue nextPushValue = new PushValue(operatedNumber_String,
							(currentPushValue.push+rightNumber.length()+1),
							true);
					insertQueue(nextPushValue,currentPushValue.push+rightNumber.length()+1);
				}
			}
		}
	}
	private static boolean visitCheck(String number_string, int push) {
		int number = Integer.parseInt(number_string);
		if(visit[number][push]==false) return true;
		return false;
	}
	private static void insertQueue(PushValue nextPushValue, int push) {
		q.add(nextPushValue);
		visit[Integer.parseInt(nextPushValue.number_string)][push] = true;
	}
	private static void setData() throws IOException {
		q.clear();
		visit = new boolean[1000][21]; //999x 9 => 넉넉히 9000
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		O = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		possibleNumber = new int[N];
		possibleOperator = new int[O];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) possibleNumber[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<O;i++) possibleOperator[i] = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(br.readLine());
		possibleNumberSet = new ArrayList<String>();
	}

	private static void possibleNumberSetting() {
		for(int r=1;r<=3;r++){
			permutationVisit = new boolean[N];
			nPr("",r,0);
		}
	}
	private static void nPr(String currentNumber_String, int r, int depth) {
		if(depth==r){
			possibleNumberSet.add(currentNumber_String);
			return;
		}
		for(int i=0;i<N;i++) {
			if(permutationVisit[i]==false){
				String nextNumber_String = currentNumber_String+Integer.toString(possibleNumber[i]);
				if(nextNumber_String.charAt(0)!='0'){
					permutationVisit[i] = true;
					nPr(nextNumber_String, r, depth+1);
					permutationVisit[i] = false;
				}
			}
		}
	}
	private static void initialization() {
		for(int i=0;i<N;i++) {
			String rootNumber = Integer.toString(possibleNumber[i]);
			q.add(new PushValue(rootNumber, 1, false));
		}
	}
}
