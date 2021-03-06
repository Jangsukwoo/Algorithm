package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
/*
 * 차근차근
 */
public class 숫자고르기 {
	static int[] child;
	static boolean[] visit;
	static boolean cycle;
	static int start;
	static HashSet<Integer> set;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static PriorityQueue<Integer> answer = new PriorityQueue<Integer>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		dfsAll();
		getAnswer();
		System.out.println(sb.toString());
	}
	private static void getAnswer() {
		sb.append(answer.size()+"\n");
		while(!answer.isEmpty()){
			sb.append(answer.poll()+"\n");
		}
	}
	private static void dfsAll() {
		for(int i=1;i<=N;i++) {
			if(visit[i]==false) {
				cycle = false;
				start = 0;
				set = new HashSet<Integer>();
				dfs(i);
			}
		}
	}
	private static void dfs(int num) {
		if(visit[num]) return;
		visit[num] = true;
		if(num==child[num]) {
			answer.add(num);
			return;
		}
		set.add(num);
		if(set.contains(child[num])) {
			cycle = true;
			start = child[num];
			answer.add(num);
			return;
		}
		dfs(child[num]);
		if(cycle) {
			answer.add(num);
			if(start==num) {
				cycle=false;
			}
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		child = new int[N+1];
		visit = new boolean[N+1];
		for(int i=1;i<=N;i++) {
			child[i] = Integer.parseInt(br.readLine());
		}
	}
}
