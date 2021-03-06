package 자료구조연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
public class 괄호_스택 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int inputNum = Integer.parseInt(br.readLine());

		Stack<Character> stack = new Stack<Character>();
		for(int i=0; i<inputNum; i++) {
			String inputStr = br.readLine();
			boolean ok = true;
			stack.clear();//스택 초기화
			for(int j=0;j<inputStr.length();j++) {
				char ch = inputStr.charAt(j);
				if(ch == '(' ){//글자를 꺼내서 보니 ( 라면
					stack.push(ch); //그냥 넣는다.
				} else if(ch==')') {
					//) 면
					if(stack.isEmpty()){//스택이 비어있는 경우는 넣으면 안되니까
						ok = false; //비어있는 상태에서 )를 넣으려 했으므로 ok = false
						break;
					}
					else if(stack.peek()=='('){
						//짝이 맞으므로 뺀다
						stack.pop();
					}
				}
			}
			if(stack.isEmpty() && ok) { //stack이 비어있고 ok다. 
				System.out.println("YES");
			}else
				System.out.println("NO");
		}
	}

}