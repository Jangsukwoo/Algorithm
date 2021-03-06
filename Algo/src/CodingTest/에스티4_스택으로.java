package CodingTest;

import java.util.Stack;

public class 에스티4_스택으로 {
	public static void main(String[] args) {
		System.out.println(solution("abcde",new int[][]{{1,3},{1,4},{4,5}}));
		System.out.println(solution("abcde",new int[][]{{4,5},{1,2},{3,3}}));
	}
	public static String solution(String S, int[][] interval) {
		String answer = "";
		char[] characterArray = S.toCharArray();
		Stack<Character> stack = new Stack<Character>();
		
		for(int i=0;i<interval.length;i++) {

			int startIdx = interval[i][0]-1;
			int endIdx = interval[i][1]-1;

			for(int j=startIdx;j<=endIdx;j++) {
				stack.add(characterArray[j]);
			}
			
			while(!stack.isEmpty()) {
				characterArray[startIdx++] = stack.pop();
			}
		}
		answer = String.copyValueOf(characterArray);
		return answer;
	}
}
