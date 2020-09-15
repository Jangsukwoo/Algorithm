package CodingTest;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class 라2 {
	static Deque<Integer> dq = new LinkedList<Integer>();
	public static int[] solution(int[] ball, int[] order) {
		int[] answer = new int[ball.length];

		for (int i = 0; i < ball.length; i++)  dq.add(ball[i]);//데크에 삽입 
		int answerPointer= 0;
		while (!dq.isEmpty()) {
			for(int i=0;i<order.length;i++) {
				int target = order[i];
				if (dq.peekFirst() == target){//앞에 꺼내려는 볼 번호면 
					answer[answerPointer++] = target;
					dq.pollFirst(); //꺼냄
					break;
					
				} else if (dq.peekLast() == target) {//뒤에 꺼내려는 볼 번호가 있으면
					answer[answerPointer++] = target;
					dq.pollLast();//꺼냄
					break;
				} 
			}
		}
		return answer;
	}
	public static void main(String[] args) {
		int[] result = solution(new int[] {1,2,3,4,5,6}, new int[] {6,2,5,1,4,3});
		System.out.println(Arrays.toString(result));
	}
}