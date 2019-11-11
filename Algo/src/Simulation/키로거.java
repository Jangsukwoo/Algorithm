package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 3:00~
 * 처음에 링크드리스트로했다가
 * 시간이 터짐
 * 삽입,삭제시 선형탐색을 해야하는게 원인인듯 하다.
 * 스택 두개로 하니 풀렸다.
 * ~4:00
 */
public class 키로거 {
	static int cursorPoint;
	static StringBuilder result = new StringBuilder();
	static String input;
	static Stack<Character> leftStack,rightStack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			cursorPoint=0;
			leftStack = new Stack<Character>();
			rightStack = new Stack<Character>();
			input = br.readLine();
			detectPassword();
			makePassword();
		}
		System.out.println(result.toString());
	}
	private static void makePassword() {
		int cnt=0;
		while(rightStack.size()>0) leftStack.add(rightStack.pop());
		while(leftStack.size()>cnt) {
			result.append(leftStack.get(cnt++));
		}
		result.append('\n');
	}
	private static void detectPassword(){
		int size = input.length();
		for(int i=0;i<size;i++){
			switch (input.charAt(i)){
			case '<':	
				if(leftStack.size()>0) rightStack.add(leftStack.pop());
				break;
			case '>':	
				if(rightStack.size()>0) leftStack.add(rightStack.pop());
				break;
			case '-':
				if(leftStack.size()>0) leftStack.pop();
				break;
			default :
				leftStack.add(input.charAt(i));
				break;
			}
		}
	}
}
