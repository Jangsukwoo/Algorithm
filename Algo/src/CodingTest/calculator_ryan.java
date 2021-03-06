package CodingTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class calculator_ryan {
	static char[] cal = new char[] {'*','-','+'};
	static boolean[] visit;
	static int[] pick;
	static long answer;
	static ArrayList<String> series = new ArrayList<String>();
	static Queue<String> q = new LinkedList<String>();
	public static void main(String[] args) {
		System.out.println(solution("100-200*300-500+20"));
		//solution("50*6-3*2");
	}
    public static long solution(String expression){
    	answer = 0;
    	pick = new int[3];
    	visit = new boolean[3];
    	for(int i=0;i<expression.length();i++) {
    		if(expression.charAt(i)=='+' || expression.charAt(i)=='-' || expression.charAt(i)=='*') {
    			q.add(String.valueOf(expression.charAt(i)));
    		}
    	}
    	StringTokenizer st = new StringTokenizer(expression,"+|-|*");
    	while(st.hasMoreTokens()) {
    		String num = st.nextToken();
    		series.add(num);
    		if(!q.isEmpty()) {
    			series.add(q.poll());
    		}
    	}
    	//list=  expression.split("+|-|*");
    	nPr(0,0);
        return answer;
    }
	private static void nPr(int idx, int depth) {
		if(depth==3){
			ArrayList<String> temp = copyArray(series);
			Stack<String> stack = null;
			for(int i=0;i<3;i++) {
				stack = new Stack<String>();
				boolean flag = false;
				for(int j=0;j<temp.size();j++){
					stack.add(temp.get(j));
					if(flag){
						long rightValue = Long.parseLong(stack.pop());
						stack.pop();
						long leftValue = Long.parseLong(stack.pop());
						long value = 0;
						switch (cal[pick[i]]) {
						case '*':
							value = leftValue*rightValue;
							break;
						case '+':
							value = leftValue+rightValue;
							break;
						case '-':
							value = leftValue-rightValue;
							break;
						}
						flag = false;
						stack.add(Long.toString(value));
					}
					if(temp.get(j).equals(String.valueOf(cal[pick[i]]))){ //연산자 찾음
						flag=true;
					}
				}
				temp = copy(stack);
			}
			answer = Math.max(answer, Math.abs(Long.parseLong(stack.pop())));
			return;
		}
		for(int i=0;i<3;i++){
			if(visit[i]==false) {
				pick[depth] = i;
				visit[i] = true;
				nPr(i+1,depth+1);
				visit[i] = false;
			}
		}
	}
	private static ArrayList<String> copyArray(ArrayList<String> series) {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i=0;i<series.size();i++) {
			temp.add(series.get(i));
		}
		return temp;
	}
	private static ArrayList<String> copy(Stack<String> stack){
		ArrayList<String> temp = new ArrayList<String>();
		for(int i=0;i<stack.size();i++) {
			temp.add(stack.get(i));
		}
		return temp;
	}
}

