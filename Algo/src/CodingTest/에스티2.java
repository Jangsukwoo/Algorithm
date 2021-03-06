package CodingTest;

import java.util.PriorityQueue;

public class 에스티2 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {3,1,2,4}));
		System.out.println(solution(new int[] {2,2,2,2}));
	}
	public static int solution(int[] bombs) {
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for(int i=0;i<bombs.length;i++) pq.add(bombs[i]);
		
		int time = 1;
		int removeCount = 0;
		
		while(!pq.isEmpty()) {
			int bomb = pq.poll();
			if(bomb<time) break;
			removeCount++;
			time++;
		}
		
		return removeCount;
	}
}
