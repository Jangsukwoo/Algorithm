package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 다음 수가 있을 때는 다음 수를 출력
 * 다음 수가 여러개일 때 A
 * 다음 수를 구할 수 없을 때 
 * 
다른 사람 풀이 보고 참고해서 풀었음.....연립방정식으로 나타날 수 있는 해의 종류로 세부조건을 나눠서 풀어야했다.
스스로 풀 때 확신을 가지고 구현하기 위한 수학적인 증명을 못해서 풀 수 없었다.
 */

public class IQTest {
	static int N;
	static int[] series;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Set<Integer> nextNumberSet;
	static int nextNumber;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		series = new int[N];
		st = new StringTokenizer(br.readLine());
		nextNumberSet = new HashSet<Integer>();
		for(int i=0;i<N;i++) series[i] = Integer.parseInt(st.nextToken()); 

		if(isManyRecurrenceFormula()) System.out.println("A");
		else if(nextNumberSet.size()==1) System.out.println(nextNumber);
		else if(nextNumberSet.size()==0) System.out.println("B");
	}
	private static boolean isManyRecurrenceFormula(){
		if(N==1) return true; //하나의 항일 때
		//a,b결정 
		if(N==2) { //항 두개일 때 
			if(series[0]==series[1]){ //초항이랑 두번째 항 같으면 같은수
				nextNumberSet.add(series[1]);
				nextNumber = series[1];
				return false;
			}else {
				
				return true; //항이 다르면 여러가지 쌍이 존재할 수 있다.
			}
		}
		if(N>2){//항 3개부터는 세부조건확인
			
			//1. 모든 항이 같은 경우
			if(series[0]==series[1]){ //초항이랑 두번째 항 같으면 같은수
				for(int i=1;i<=N-1;i++) {
					if(series[i]!=series[i-1]) return false;
				}
				nextNumberSet.add(series[0]);
				nextNumber = series[0];
				return false;
			}
			
			//2. 정수로 표현이 불가능한 a,b 쌍이 나오는  경우
			int temp = series[2]-series[1];
			int temp2 = series[1]-series[0];
			if(temp%temp2!=0) return false; //배수로 표현할 수 없으면 해가 존재할 수 없음
			
			int a = temp/temp2;
			int b = series[1]-series[0]*a;
			for(int i=1;i<=(N-1);i++) {
				if(series[i]==(series[i-1]*a+b)) continue;
				else return false;
			}
			nextNumberSet.add(series[N-1]*a+b);
			nextNumber = series[N-1]*a+b;
		}
		return false;
	}
}
