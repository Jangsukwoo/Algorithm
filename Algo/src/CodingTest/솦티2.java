package CodingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 솦티2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		String s = "3*5-3/2+3";
		st = new StringTokenizer(s,"*-/+");
		int size = st.countTokens();
		String[] tokens = new String[size];
		for(int i=0;i<size;i++) {
			tokens[i] = st.nextToken();
		}
		System.out.println(tokens.length);
		for(String token : tokens) {
			System.out.println(token);
		}
		String s2 = "aaaabbbbbaaaababababababab";
		String replace_String = s2.replace('b', 'a');
		System.out.println(replace_String);
	}
}
