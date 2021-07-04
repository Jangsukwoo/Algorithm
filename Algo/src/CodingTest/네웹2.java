package CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 네웹2 {
	static ArrayList<String> maxTokens;
	static int maxTokensize = 0;
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution("abcxyasdfasdfxyabc")));
		System.out.println(Arrays.toString(solution("abcxyqwertyxyabc")));
		System.out.println(Arrays.toString(solution("abcabcabcabc")));
		System.out.println(Arrays.toString(solution("llttaattll")));
		System.out.println(Arrays.toString(solution("zzzzzz")));
		System.out.println(Arrays.toString(solution("abcdef")));
		System.out.println(Arrays.toString(solution("llllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll")));
	}
	public static String[] solution(String s) {
		String[] answer = {};
		ArrayList<String> tokens = new ArrayList<String>();
		maxTokensize = 0;
		maxTokens = new ArrayList<String>();
		dfs(s,tokens);
		answer = maxTokens.toArray(new String[maxTokens.size()]);
		return answer;
	}
	private static void dfs(String s, ArrayList<String> tokens) {
		if(s.length()==0) {
			if(maxTokensize < tokens.size()*2) {
				maxTokensize = tokens.size()*2;
				maxTokens = new ArrayList<String>();
				for(String token : tokens) maxTokens.add(token);
				Collections.reverse(tokens);
				for(String token : tokens) maxTokens.add(token);
			}
			return;
		}
		if(s.length()==1) {
			if(maxTokensize < tokens.size()*2+1) {
				maxTokensize = tokens.size()*2+1;
				maxTokens = new ArrayList<String>();
				for(String token : tokens) maxTokens.add(token);
				maxTokens.add(s);
				Collections.reverse(tokens);
				for(String token : tokens) maxTokens.add(token);
			}
			return;
		}
		boolean possible = false;
		for(int slice = 1; slice<=s.length()/2;slice++) {
			String fronttWord = ""; 
			String backWord = "";
			for(int cut = 0; cut<slice; cut++) fronttWord+=s.charAt(cut);
			for(int cut = 0; cut<slice; cut++) backWord+=s.charAt(s.length()-slice+cut);
			if(fronttWord.equals(backWord)) {
				possible = true;
				String slicedString = s.substring(slice, s.length()-slice);	
				ArrayList<String> newTokens = new ArrayList<String>(tokens);
				newTokens.add(fronttWord);
				dfs(slicedString,newTokens);
				break;
			}
		}
		if(possible==false) {
			if(maxTokensize < (tokens.size()*2+1)) {
				maxTokensize = tokens.size()*2+1;
				maxTokens = new ArrayList<String>();
				for(String token : tokens) maxTokens.add(token);
				maxTokens.add(s);
				Collections.reverse(tokens);
				for(String token : tokens) maxTokens.add(token);
			}
		}
	}
}
