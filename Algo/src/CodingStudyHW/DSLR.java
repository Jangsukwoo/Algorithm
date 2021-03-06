package CodingStudyHW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 15:40~
 * 백준 DSLR 
 * 레지스터 계산기가 하나 있음
 * 0이상의 네자리 십진수를 저장하는데 다음 4가지 명령어가 있음
 * 
 * D : n을 두배로 바꾼다.
 * 결과 값이 9999보다 크면 10000로 나눈 나머지를 취함. 결과값을 레지스터에 저장
 * 
 * S : n에서 1을 뺀 결괄르 저장. n이 0이라면 9999를 저장
 * 
 * L : n의 각 자리수를 왼쪽으로 회전시켜 그 결과를 저장 
 * d1 d2 d3 d4 -> d2 d3 d4 d1
 * 
 * R : n의 각 자리수를 오른쪽으로 회전시켜 그 결과를 저장
 * 
 * 주어진 서로 다른 두 정수 A,B에 대하여
 * A를 B로 바꾸는 최소한의 명령어를 생성하는 프로그램
 * 
1
1000 1 

1
1234 3412

~16:50 끝 
 */
public class DSLR {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static boolean[] visit;
	static int mod = 10000;
	static Queue<DSLRdata> q = new LinkedList<DSLRdata>();
	static String A,B;
	static class DSLRdata{
		int numInt;
		String numString;
		String command;
		public DSLRdata(int numInt, String numString, String command) {
			this.numInt = numInt;
			this.numString = numString;
			this.command = command;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			bfs();
		}
		bw.flush();
		bw.close();
	}
	private static void bfs() throws IOException{
		visit = new boolean[10000];
		A = fillZero(A);
		q.clear();
		insertQueue(Integer.parseInt(A),A,"","");
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				DSLRdata currentDSLR = q.poll();
				if(sameCheck(currentDSLR)) return; //변환했으면 끝내버림
				D(currentDSLR);
				S(currentDSLR);
				L(currentDSLR);
				R(currentDSLR);
			}
		}
	}

	private static String fillZero(String data) {
		while(data.length()<4){//4자리가 될때까지
			String tmp = "0"+data;//앞에 0붙임
			data = tmp;
		}
		return data;
	}
	private static void D(DSLRdata currentDSLR) {//2n mod 10000
		int nextNumber = (currentDSLR.numInt*2)%mod;
		if(visit[nextNumber]==false) { 
			String nextStringNumber = Integer.toString(nextNumber);
			nextStringNumber = fillZero(nextStringNumber);
			insertQueue(nextNumber,nextStringNumber,currentDSLR.command,"D");
//			System.out.println("D");
//			System.out.println("바꾼숫자"+nextNumber);
		}
	}
	private static void S(DSLRdata currentDSLR) {//n-1 (0~9999)

		int nextNumber = currentDSLR.numInt-1;
		if(nextNumber==-1) nextNumber = 9999; //-1이면 9999
		if(visit[nextNumber]==false) { 
			String nextStringNumber = Integer.toString(nextNumber);
			nextStringNumber = fillZero(nextStringNumber);
			insertQueue(nextNumber,nextStringNumber,currentDSLR.command,"S");
//			System.out.println("S");
//			System.out.println("바꾼숫자"+nextNumber);
		}
		
	}
	private static void L(DSLRdata currentDSLR) {//d2 d3 d4 d1
		char[] temp = new char[4];
		for(int i=1;i<4;i++) {
			temp[i-1] = currentDSLR.numString.charAt(i);			
		}
		temp[3] = currentDSLR.numString.charAt(0);
		String nextNumberString="";
		for(int i=0;i<4;i++) nextNumberString+=temp[i];
		int nextNumber = Integer.parseInt(nextNumberString);
		if(visit[nextNumber]==false) { 
			insertQueue(nextNumber,nextNumberString,currentDSLR.command,"L");
//			System.out.println("L");
//			System.out.println("바꾼숫자"+nextNumber);
		}
	}
	private static void R(DSLRdata currentDSLR) {//d4 d1 d2 d3
		char[] temp = new char[4];
		for(int i=1;i<4;i++) {
			temp[i] = currentDSLR.numString.charAt(i-1);			
		}
		temp[0] = currentDSLR.numString.charAt(3);
		String nextNumberString="";
		for(int i=0;i<4;i++) nextNumberString+=temp[i];
		int nextNumber = Integer.parseInt(nextNumberString);
		if(visit[nextNumber]==false) { 
			insertQueue(nextNumber,nextNumberString,currentDSLR.command,"R");
//			System.out.println("R");
//			System.out.println("바꾼숫자"+nextNumber);
		}
	}

	private static boolean sameCheck(DSLRdata currentDSLR) throws IOException {
		if(Integer.parseInt(B)==currentDSLR.numInt){ //같으면 
			bw.write(currentDSLR.command+"\n");
			return true;
		}
		return false;
	}
	private static void insertQueue(int nextNumberInt, String nextNumberString, String currentCommand, String nextCommand) {//스트링받아서 큐에 숫자로넣어줌
		q.add(new DSLRdata(nextNumberInt,nextNumberString,currentCommand+nextCommand));
		visit[nextNumberInt] = true;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		A = st.nextToken();
		B = st.nextToken();//A->B로 만들기
		
	}
}
