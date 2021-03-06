package codingtest20하;

import java.util.Stack;

public class 쿠1 {
	public static void main(String[] args) {
		solution(10);
	}
	public static int[] solution(int N) {
		int[] answer = {};
		int maxMultiValue = 0;
		int maxBase = 0;
		for(int num=1;num<=10;num++){
			int multivalue = 1;
			String baseNotation ="";
			if(num==1) {
				baseNotation = Integer.toString(N);
			}else {
				baseNotation = getBaseNotation(N,num);
			}
			for(int i=0;i<baseNotation.length();i++) {
				if(baseNotation.charAt(i)!='0'){
					multivalue*=Character.getNumericValue(baseNotation.charAt(i));
				}
			}
			if(maxMultiValue<=multivalue) {
				maxMultiValue=multivalue;
				maxBase=num;
			}
		}
		answer = new int[] {maxBase,maxMultiValue};
		return answer;
	}
	private static String getBaseNotation(int N, int num) {
		Stack<Object> stack= new Stack<Object>();
		String baseNotation ="";
		while(N>0) {
			if(N%num>9) {
				stack.add((char)(N%num+55));
				System.out.println((char)(N%num+55));
			}
			else stack.add((N%num));
			N/=num;
		}
		while(!stack.isEmpty()) baseNotation+=stack.pop();
		if(baseNotation.equals("")) baseNotation="0";
		return baseNotation;
	}
}
