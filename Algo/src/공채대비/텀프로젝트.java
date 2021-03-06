package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * 12:30~
1
8
8 7 6 5 4 3 2 1 

1
7
7 6 7 6 6 7 3

1
6
2 3 4 5 6 2
왜 답이 5?
 */
public class 텀프로젝트 {
	static int[] students;
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean[] visit;
	static int answer;
	static int start;
	static boolean cycle;
	static HashSet<Integer> set;
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			logic();
			getAnswer();
			System.out.println(answer);
		}
	}
	private static void getAnswer() {
		for(int i=1;i<=N;i++) {
			if(visit[i]==false) answer++;
		}
	}
	private static void logic() {
		for(int i=1;i<=N;i++){
			if(visit[i]==false){
				cycle = false;
				set = new HashSet<Integer>();
				dfs(i);
			}
		}
	}
	private static void dfs(int num){
		if(visit[num]) return; 
		if(num==students[num]){
			answer--;
			visit[num] = true;
		}
		visit[num] = true;//방문
		if(set.contains(students[num])){ //거쳐온 노드와 만나면 
			cycle = true;
			answer--;
			start = students[num];
			return;
		}
		set.add(num);
		dfs(students[num]);
		if(cycle) {
			answer--;
			if(num==start) cycle=false;
		}
		return;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		students = new int[N+1];
		visit = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) students[i] = Integer.parseInt(st.nextToken());
		answer=N;
	}
}







// 2 3 4 5 6 7 4
