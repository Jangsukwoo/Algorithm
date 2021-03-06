package 구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class 문자열폭발 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static String bombString;
	static String targetString;
	static String answer;
	static boolean bomb = false;
	public static void main(String[] args) throws NumberFormatException, IOException {
		targetString = br.readLine();
		bombString = br.readLine();
		Stack<Character> stack = new Stack<Character>();
		for(int i=targetString.length()-1;i>=0;i--){ //끝부터 확인
			bomb = false;
			char character = targetString.charAt(i);
			stack.add(character);
			if(check(character,stack)) {
				bomb = true;
				int lastIdx = stack.size()-1;
				for(int j=0;j<bombString.length();j++) {//폭발되는지 매칭 검증
					if(bombString.charAt(j)!=stack.get(lastIdx-j)) {
						bomb = false;
						break;
					}
				}
				if(bomb) for(int p=0;p<bombString.length();p++) stack.pop();
			}
		}
		if(stack.isEmpty()) bw.write("FRULA");
		else {
			while(!stack.isEmpty()) {
				bw.write(stack.pop());
			}
		}
		bw.flush();
		bw.close();
	}
	private static boolean check(char character, Stack<Character> stack) {
		if(character==bombString.charAt(0) && stack.size()>=bombString.length()) return true;
		return false;
	}
}
