package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * N 받고 (<=100)
 * 문자열에서 숫자 골라서 
 * 숫자를 비내림차순
 * 
4
43silos0
zita002
le2sim
231233

문자열이 최대 100글자라함
int 21억
long도 100자리 숫자 불가능
-> BigInteger라는건 문자열인데 마치 숫자처럼 쓰라고 자바에서 만든 자료구조
 */
public class 수학숙제 {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<BigInteger> numberlist = new ArrayList<BigInteger>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		logic();
		//로직
		//출력
		for (BigInteger num : numberlist) {
			System.out.println(num);
		}
	}
	private static void logic() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine()); //문자열 개수
		
		for(int i=0;i<N;i++){ //문자열 개수만큼 본다.
			
			String readline = br.readLine(); //문자열 읽고
			String tempNumber = ""; //문자열 숫자 만들기
			
			for(int j=0;j<readline.length();j++) {
				
				char ch = readline.charAt(j); //한글자씩 꺼내는데
				
				if(ch >='0' && ch <='9'){//숫자다. 알파벳이 아니면
					tempNumber+=ch; //문자열 붙임
				}else { //알파벳인경우. 여태까지 쌓인 숫자를 빅인트져로 만들어야 함
					if(tempNumber.length()>0){
						
						BigInteger number = new BigInteger(tempNumber);
						
						numberlist.add(number); //빅인트저 리스트에 담아
						
						tempNumber=""; //초기화. 다시 숫자를 만들어야 하니깐.
					}
				}
			}
			
			if(tempNumber.length()>0) {//알파벳을 못만났는데 숫자가 쌓여있는경우
				BigInteger number = new BigInteger(tempNumber);
				numberlist.add(number);
				tempNumber="";
			}
		}
		Collections.sort(numberlist);//솔팅
	}

}
