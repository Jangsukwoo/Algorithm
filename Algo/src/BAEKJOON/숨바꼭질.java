package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질 {
	static Scanner sc = new Scanner(System.in);
	static int currentSubin,target;
	static boolean[] visit = new boolean[100001];
	static Queue<Integer> q = new LinkedList<Integer>();
	static int answer;
	public static void main(String[] args) {
		currentSubin = sc.nextInt();
		target = sc.nextInt();
		insertQueue(currentSubin);
		bfs();
		System.out.println(answer);
	}
	private static void bfs() {
		int time=0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int currentPosition = q.poll();
				if(currentPosition==target){
					answer = time;
					return;
				}else {
					int nextPosition = currentPosition-1;
					if(rangeCheck(nextPosition) && visit[nextPosition]==false){
						insertQueue(nextPosition);
					}
					nextPosition = currentPosition+1;
					if(rangeCheck(nextPosition) && visit[nextPosition]==false) {
						insertQueue(nextPosition);
					}
					nextPosition = currentPosition*2;
					if(rangeCheck(nextPosition) && visit[nextPosition]==false) {
						insertQueue(nextPosition);
					}
				}
			}
			time++;
		}
	}
	private static boolean rangeCheck(int nextPosition) {
		if(nextPosition>=0 && nextPosition<=100000) return true;
		return false;
	}
	private static void insertQueue(int currentSubin) {
		q.add(currentSubin);
		visit[currentSubin] = true;
	}
}
