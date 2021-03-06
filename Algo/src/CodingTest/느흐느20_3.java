package CodingTest;

import java.util.Scanner;
import java.util.Stack;

public class 느흐느20_3 {
	private static void solution(int numOfOrder, String[] orderArr) {
		Stack<Character> stack = new Stack<Character>();
		for(int order=0;order<numOfOrder;order++) {
			stack.clear();
			for(int i=orderArr[order].length()-1;i>=0;i--) {
				String temp = orderArr[order];
				stack.add(temp.charAt(i));
			}
			String answer = getAnswer(stack);
		}
	}

	private static String getAnswer(Stack<Character> stack) {
		String answer = "";
		String temp= "";
		while(!stack.isEmpty()){
			char popChar = stack.pop();
			int open = 0;
			System.out.println(popChar);
			if(popChar==')') {
				open++;
			}else if(popChar=='(') {
				open--;
			}else if('0'<= popChar && popChar<='9') {//숫자면
				for(int i=0;i<Character.getNumericValue(popChar);i++) {
					temp+=temp;
				}
			}else if('A'<= popChar && popChar<='Z') {
				char temp2 = popChar;
				temp = temp2+temp;
				System.out.println("만드는중");
				System.out.println(temp);
			}
			System.out.println(temp);
		}
		answer = temp;
		System.out.println("답");
		System.out.println(answer);
		return answer;
	}

	private static class InputData {
		int numOfOrder;
		String[] orderArr;
	}

	private static InputData processStdin() {
		InputData inputData = new InputData();

		try (Scanner scanner = new Scanner(System.in)) {
			inputData.numOfOrder = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

			inputData.orderArr = new String[inputData.numOfOrder];
			for (int i = 0; i < inputData.numOfOrder; i++) {
				inputData.orderArr[i] = scanner.nextLine().replaceAll("\\s+", "");
			}
		} catch (Exception e) {
			throw e;
		}

		return inputData;
	}

	public static void main(String[] args) throws Exception {
		InputData inputData = processStdin();

		solution(inputData.numOfOrder, inputData.orderArr);
	}
}