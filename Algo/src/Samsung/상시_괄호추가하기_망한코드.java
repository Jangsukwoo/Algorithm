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
 * 
 * 쓸데없이 큐랑 스택써서 망해버린 코드
 * 반례잡기에는 로직이 어디서 꼬인지 몰라서 그냥 갈아엎었다.
 */
class DATA{
	long value;
	char op;
	public DATA(long value, char op){
		this.value = value;
		this.op = op;
	}
}
public class 상시_괄호추가하기_망한코드 {
	static DATA[] formula;
	static char[] d;
	static int N;
	static long max = Long.MIN_VALUE;
	static int operatorSize;
	static int formulaSize;
	static int setSize;
	static int r;
	static int ccc;
	static boolean [] visit;
	static Stack<DATA> stack;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		formula = new DATA[N];
		String readLine = sc.next();
		d = readLine.toCharArray();
		for(int i=0;i<N;i++){
			if(d[i]>='0' && d[i]<='9'){//숫자면
				int a = Character.getNumericValue(d[i]);
				formula[i] = new DATA((long)a,'@');
			}else formula[i] = new DATA(0,d[i]);
		}

		formulaSize = formula.length;
		visit = new boolean[formulaSize];
		stack = new Stack<DATA>();
		operatorSize = formulaSize-1;
		if(operatorSize%2==0) setSize = operatorSize/2;
		else setSize = (operatorSize/2)+1;
		//입력 끝
		//1개 이상씩 묶어보는 처리
		stack = new Stack<DATA>();
		for(int s=1;s<=setSize;s++){
			LinkedList<DATA> queue = new LinkedList<DATA>();
			r = s;
			nCr(0,0,queue);
		}
		//한개도 안하고 그냥 처리
		for(int i=(formulaSize-1);i>=0;i--) stack.add(formula[i]);
		calculate();
		System.out.println(max);

	}
	private static void nCr(int idx, int cnt, LinkedList<DATA> queue) {
		if(idx>formulaSize) return;
		if(cnt==r){//다 묶음 
			ccc++;
			boolean find = true;
			boolean[] v = new boolean[17];
			for(int i=0;i<17;i++) {
				if(i==2 || i==3 || i==4 || i==8 || i==9 || i==10 || i==14 || i==13 || i==12) {
					v[i] = true;
				}
			}
			for(int i=0;i<17;i++) {
				if(visit[i]!=v[i]) find = false;
			}
			if(find) System.out.println(ccc);
			if(ccc==43) {
				makeStack(queue);
				System.out.println("완성된스택");
				view();
				calculate();
			}
			
			return;
		}
		for(int i=idx;i<(formulaSize-2);i+=2){
			if(visit[i]==false){//방문 안해봄
				visit[i] = true;
				visit[i+1] = true;
				visit[i+2] = true;
				long a = formula[i].value;
				long b = formula[i+2].value;
				long answer = calculator(a,b,formula[i+1].op);
				queue.add(new DATA(answer,'@'));
				nCr(i+2,cnt+1,queue);
				queue.poll();
				visit[i] = false;
				visit[i+1] = false;
				visit[i+2] = false;
			}
		}
	}
	private static void calculate() {
		char operator = '0';
		boolean a = false;
		boolean b = false;
		long A = 0;
		long B = 0;
		while(!stack.isEmpty()){
			DATA data = stack.pop();
			if(data.op=='@'){
				//숫자고
				if(a==false){//a면
					A =data.value;
					a = true;
				}
				else if(b==false){
					B = data.value;
					A = calculator(A,B,operator);
					stack.add(new DATA(A,'@'));
					a = false;
				}
			}
			else {
				operator = data.op;
			}
		}
		max = Math.max(A,max);
	}
	private static void view() {
		System.out.println("스택");
		for(DATA a : stack) {
			if(a.op=='@') System.out.println(a.value);
			else System.out.println(a.op);
		}
		System.out.println();
	}
	private static void makeStack(LinkedList<DATA> queue) {
		int i= formulaSize-1;
		Collections.reverse(queue);
		System.out.println("true는");
		System.out.println(Arrays.toString(visit));
		System.out.println("큐사이즈"+queue.size());
		System.out.println("큐");
		for(DATA a : queue) {
			if(a.op=='@') System.out.println(a.value);
			else System.out.println(a.op);
		}
		System.out.println();
		
		while(i>=0) {
			if(visit[i]==true && queue.size()>0){//먼저 계산해야하는 부분이면
				DATA num = queue.poll();
				stack.add(num);
				queue.add(num);
				i -=3;
			}else {
				stack.add(formula[i]);
				i--;
			}	
		}
		Collections.reverse(queue);
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
