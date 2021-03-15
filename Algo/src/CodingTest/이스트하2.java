package CodingTest;

import java.util.PriorityQueue;

public class 이스트하2 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {1,3,7,8,10,15},1));
	}
	public static int solution(int[] scores, int k) {
		int answer = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (int i = 0; i < scores.length - 1; i++) {
			pq.add(scores[i + 1] - scores[i]); //차이를 우선순위큐에 다 넣고
		}

		for (int i = 0; i < (scores.length-k); i++) {
			answer += pq.poll();
		}
		return answer;
	}

}
