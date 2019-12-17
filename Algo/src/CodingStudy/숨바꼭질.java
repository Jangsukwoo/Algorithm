package CodingStudy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 이동 방법에 대한 경우의수는 
 * -1,+1,2X
 * 수빈이가 동생을 만나는 가장 빠른 시간.
 * -> BFS
 * 
 */
public class 숨바꼭질 {
	static Queue<Integer> q = new LinkedList<Integer>();
	static int N,K;// N : 수빈, K : 동생  
	static boolean[] visit = new boolean[100001];
	static int time;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		insertQueue(N);
		BFS();
		System.out.println(time);
	}
	private static void BFS() {
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int current = q.poll();
				if(current==K) return;
				if(rangeCheck(current+1)) insertQueue(current+1);
				if(rangeCheck(current-1)) insertQueue(current-1);
				if(rangeCheck(current*2)) insertQueue(current*2);
			}
			time++;
		}
	}

	private static boolean rangeCheck(int next) {
		if(next>=0 & next<=100000 && visit[next]==false) return true;
		return false;
	}
	private static void insertQueue(int next) {//BFS용 enqueue
		visit[next] = true;
		q.add(next);
	}
}
