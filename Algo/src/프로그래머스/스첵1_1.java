package 프로그래머스;

import java.util.PriorityQueue;

public class 스첵1_1 {
	public static void main(String[] args) {
		
	}
    public static String solution(String s) {
        String answer = "";
        PriorityQueue<Character> pq = new PriorityQueue<Character>();
        for(int i=0;i<s.length();i++) pq.add(s.charAt(i));
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) sb.append(pq.poll());
        StringBuffer sf = new StringBuffer(sb.toString());
        answer = sf.reverse().toString();
        return answer;
    }
}
