package CodingTest;

import java.util.LinkedList;
import java.util.Queue;

public class 영1 {
	static char[] alphabets = new char[27];
	static Queue<Character> shiftQueue;



	public static void main(String[] args) {
		String answer = solution("bcd", "aaa",-1);
		System.out.println(answer);
	}



	public static String solution(String encrypted_text, String key, int rotation) {
		String answer = "";

		init(); //알파벳, shift queue 선언

		int n = key.length();

		String reRotation_text = ""; //복호화 text가 담길 변수
		String decrypted_text_text = "";

		rotation = rotation % key.length();

		int idx = Math.abs(rotation);

		if(rotation>0) {
			reRotation_text = encrypted_text.substring(idx)+encrypted_text.substring(0, idx);
		}else {
			reRotation_text = encrypted_text.substring(key.length()-idx)+encrypted_text.substring(0,key.length()-idx);
		}


		for(int i=0;i<n;i++) { //복호화


			char alphabet1 = reRotation_text.charAt(i); 
			char alphabet2 = key.charAt(i);  

			int alphabet1_number = (int) (alphabet1-'a')+1;
			int alphabet2_number = (int) (alphabet2-'a')+1;

			int backJump = alphabet1_number - alphabet2_number;

			if(backJump<0) backJump = 26+backJump;
			else if(backJump==0) backJump=26;
			decrypted_text_text+=alphabets[backJump];

		}
		answer = decrypted_text_text;

		return answer;
	}

	private static void init() {
		for(int i=1;i<=26;i++) alphabets[i] = (char) ('a' +i-1);
		shiftQueue = new LinkedList<Character>();
	}
}
