package CodingTest;

import java.util.ArrayList;

public class 넷마1 {
	static ArrayList<Long> numberset;
	public static void main(String[] args) {
		
	}
    public long solution(int n) {
        long answer = 0;
        if(n==2) {
        	answer = 1;
        }
        else if(n>2 && n<=4) {
        	answer = get(3);
        }
        return answer;
    }
	private long get(int k){
		
		return 0;
	}
}
