package 프로그래머스;

import java.util.ArrayList;
import java.util.Collections;

public class 스첵1_2 {
	public static void main(String[] args) {
		solution(118372);
	}
    public static long solution(long n) {
        String answer_string = "";
        String n_string = Long.toString(n);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<n_string.length();i++) {
        	int number = -Character.getNumericValue(n_string.charAt(i));
        	list.add(number);
        }
        Collections.sort(list);
        System.out.println(list.toString());
        for(int i=0;i<list.size();i++) {
        	long number = -list.get(i);
        	answer_string+=Long.toString(number);
        }
        long answer = Long.parseLong(answer_string);
        return answer;
    }
}
