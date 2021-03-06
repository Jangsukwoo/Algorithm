package Samsung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * 연산자 : +,/,*
 * 수 0~9
 * 연산자가 1~2개면 최대 1묶음 
 * 연산자가 3~4개면  최대 2묶음
 * 연산자가 5~6개면 최대 3묶음
 */

public class 상시_괄호추가하기 {
	static char[] formula;
	static int N;
	static long max = Long.MIN_VALUE;
	static int operatorSize;
	static int formulaSize;
	static int setSize;
	static int r;
	static boolean [] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		String readLine = sc.next();
		formula = readLine.toCharArray();
		formulaSize = formula.length;
		visit = new boolean[formulaSize];
		operatorSize = formulaSize-1;
		if(operatorSize%2==0) setSize = operatorSize/2;
		else setSize = (operatorSize/2)+1;
		//입력 끝
		//1개 이상씩 묶어보는 처리
		for(int s=0;s<=setSize;s++){
			r = s;
			nCr(0,0);
		}
		System.out.println(max);

	}
	private static void nCr(int idx, int cnt) {
		if(cnt==r){//다 묶음 
			caculate();
			return;
		}
		for(int i=idx;i<(formulaSize-2);i+=2){
			if(visit[i]==false){//방문 안해봄
				visit[i] = true;
				visit[i+1] = true;
				visit[i+2] = true;
				nCr(i+2,cnt+1);
				visit[i] = false;
				visit[i+1] = false;
				visit[i+2] = false;
			}
		}
	}
	private static void caculate(){
		long parenthesis=0;
		long leftNum=0;
		long rightNum=0;
		char operator='0';
		for(int i=0;i<N;i++){
			if(visit[i]==true && i<(N-2)){//괄호의 숫자면
				long A = Character.getNumericValue(formula[i]);
				long B = Character.getNumericValue(formula[i+2]);
				parenthesis = calculator(A,B,formula[i+1]);
				if(i>0){
					leftNum = calculator(leftNum,parenthesis,operator);
				}else if(i==0) {
					leftNum = parenthesis;
				}
				i+=2;
			}else{ //괄호 아닐 때
				if(i==0) leftNum = Character.getNumericValue(formula[i]);
				else{
					if(i%2!=0){//연산자면
						operator = formula[i];
					}else {
						rightNum = Character.getNumericValue(formula[i]);
						leftNum = calculator(leftNum,rightNum,operator);
					}
				}
			}
		}
		max = Math.max(max,leftNum);
	}
	private static long calculator(long a, long b, char c) {
		long ans=0;
		switch (c) {
		case '+':
			ans = a+b;
			break;
		case '-':		
			ans = a-b;
			break;
		case '*':		
			ans = a*b;
			break;
		}
		return ans;
	}
}
