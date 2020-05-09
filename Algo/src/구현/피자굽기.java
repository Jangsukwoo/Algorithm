package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
7 3
1 1 1 1 1 1 1
1 1 1

10 5
1 2 1 2 1 2 1 2 1 2 
1 2 1 2 1 
 */
public class 피자굽기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int D,N;
	static Stack<Oven> stack = new Stack<Oven>();
	static long[] dough;
	static Oven[] oven;
	static int doughIdx;
	static int answer;
	static class Oven{
		long radius;
		int idx;
		public Oven(long radius, int idx) {
			this.radius = radius;
			this.idx = idx;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		setStack();
		getAnswer();
		if(doughIdx==N) System.out.println(answer+1);
		else System.out.println("0");
	}
	private static void getAnswer() {
		while(!stack.isEmpty()) {
			if(stack.peek().radius>=dough[doughIdx]){
				doughIdx++;
				if(doughIdx==N) {
					answer = stack.peek().idx;
					return;
				}
				stack.pop();
			}else {
				stack.pop();
			}
		}
	}
	private static void setStack() {
		for(int i=0;i<D;i++) {
			if(oven[i].radius>=dough[doughIdx]) {
				stack.add(oven[i]);
			}
			else{
				if(stack.isEmpty()) {
					return;
				}
				stack.pop();
				doughIdx++;
				break;
			}
		}
	}
	private static void setData() throws IOException{
		st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		oven = new Oven[D];
		dough = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<D;i++) {
			long radius = Long.parseLong(st.nextToken());
			oven[i] = new Oven(radius ,i);
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) dough[i] = Long.parseLong(st.nextToken());
	}
}
