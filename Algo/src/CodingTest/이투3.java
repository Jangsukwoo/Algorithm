package CodingTest;

import java.util.LinkedList;
import java.util.Queue;

public class 이투3 {
	public static void main(String[] args) {
		solution(new int[] {1,2,3,0,6,5,4}, new int[] {1,2,3,4,5,6,0});
	}
	private static int solution(int[] s1, int[] s2) {
		int answer = 0;
		Queue<int[]> q = new LinkedList<>();
		q.add(s1);
		int turn = 0;

		while(answer ==0) {

			int size = q.size();
			for (int i = 0; i < size; i++) {

				int[] check = q.poll();

				boolean flag = true;

				int length = check.length;

				for (int j = 0; j < length; j++) {
					if(s2[j] != check[j]) {
						flag = false; //같지않은경우
						break;
					}
				}
				if (flag) { //같은 경우임
					answer = turn;
					break;
				}

				int zero = 0;
				for (int j = 0; j < length; j++) {
					if(check[j] == 0) {
						zero = j;
						break;
					}
				}

				for (int j = 0; j < check.length; j++) {
					if(zero != j) {
						int tmp = check[j];
						check[j] = 0;
						check[zero] = tmp;
						q.add(new int[] {check[0],check[1],check[2],check[3],check[4],check[5],check[6]});
						check[j] = tmp;
						check[zero] = 0;
					}
				}
			}
			turn++;
		}
		return answer;
	}
}
