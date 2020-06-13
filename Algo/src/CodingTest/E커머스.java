package CodingTest;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
/*
 * 예라이
 */
public class E커머스 {
	static class DepthInfo{
		String word;
		int depth;
		public DepthInfo(String word, int depth) {
			this.word = word;
			this.depth = depth;
		}
	}
	public static void main(String[] args) {
		System.out.println(solution("((아디다스) 무료 (나이키 (풋살화)) 배송) 강남점 (축구)(잔디)"));
	}

	private static String solution(String text) {
		String answer = "";
		Stack<String> stack = new Stack<String>();
		PriorityQueue<DepthInfo> pq = new PriorityQueue<DepthInfo>(new Comparator<DepthInfo>() {
			@Override
			public int compare(DepthInfo o1, DepthInfo o2) {
				return -Integer.compare(o1.depth,o2.depth);
			}
		});
		StringBuilder sb = new StringBuilder();
		int depth=0;
		for(int i=0;i<text.length();i++){
			if(text.charAt(i)=='(' || text.charAt(i)=='[' || text.charAt(i)=='{'){//여는 괄호면
				stack.push(Character.toString(text.charAt(i)));
				depth++;
			}
			else if(text.charAt(i)==')' || text.charAt(i)==']' || text.charAt(i)=='}'){//닫는 괄호면
				
				System.out.println("닫는괄호에서 스택"+ stack.toString());
				String word = sb.toString();
				String word2 = stack.pop();
				if(!word2.equals("(") && !word2.equals("{") && !word2.equals("[")) {
					pq.add(new DepthInfo(word2,depth));
					System.out.println("넣는단어2"+word2);
					System.out.println("넣는단어2의워드투"+word);
					System.out.println(stack.toString());
				}else {
					pq.add(new DepthInfo(word,depth));
					System.out.println("넣는단어"+word);
				}
				sb = new StringBuilder();
				depth--;
			}else if(text.charAt(i)==' '){//공백이면
				String word = "";
				if(!stack.isEmpty() && !stack.peek().equals("(") && !stack.peek().equals("[") && !stack.peek().equals("{")){
					word = stack.pop();
					System.out.println("공백일떄 꺼낸 워드"+word);
				}
				if(!word.equals("(")||!word.equals("[")||!word.equals("{")||!word.equals(" ")){
					stack.push(word+sb.toString());
				}
				sb = new StringBuilder();
			}else{
				sb.append(Character.toString(text.charAt(i))); //단어 붙이기
			}
			System.out.println("i값"+i);
			System.out.println(stack.toString());
		}
		while(!stack.isEmpty()) {
			pq.add(new DepthInfo(stack.pop(),0));
		}
		sb = new StringBuilder();
		while(!pq.isEmpty()) {
			sb.append(pq.poll().word+",");
		}
		sb.deleteCharAt(sb.toString().length()-1);
		answer = sb.toString();
		return answer;
	}
}
