package CodingTest;

import java.util.Stack;

/* 라인2020상
 * 괄호 
 */
public class Parentheses {
	public static void main(String[] args) {
		System.out.println(solution("<><"));
	}
    public static int solution(String inputString) {
        int answer = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0;i<inputString.length();i++){
        	if(inputString.charAt(i)=='{' 
        		|| inputString.charAt(i)=='<' 
        		|| inputString.charAt(i)=='[' 
        		|| inputString.charAt(i)=='(') {
        		stack.add(-1);
        	}else if(inputString.charAt(i)=='}' 
            		|| inputString.charAt(i)=='>' 
            		|| inputString.charAt(i)==']' 
            		|| inputString.charAt(i)==')'){
            	if(stack.empty() || stack.peek()>=0){
            		return -1;
            	}
            	else{
            		stack.pop();
            		answer++;
            	}
            }
        }
        return answer;
    }
}
