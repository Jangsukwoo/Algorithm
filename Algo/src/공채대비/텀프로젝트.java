package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	static boolean cycle;
	static boolean[] nonVisit;
	static int answer;
	static int team;
	static int start;
	static boolean newFlag;
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			logic();
			System.out.println(answer);
		}
	}
	private static void logic() {
		for(int i=1;i<=N;i++){
			newFlag = false;
			cycle = false;
			if(students[i]==i) {
				answer-=1;
				visit[i] = true;
				continue;
			}
			if(visit[i]==false)  {
				visit[i] = true;
				start=i;
				dfs(students[i],2);
				if(cycle==false) {
					visit[i] = false;
					nonVisit[i] = true;
				}
			}
		}
	}
	/*
1
7
7 6 7 6 6 7 3
	 */
	private static void dfs(int num,int depth){
		if(nonVisit[num]) return;
		if(visit[num]){
			if(newFlag==false) {
				start = students[num];
				newFlag = true;
			}
			else if(newFlag) {
				if(start==num) {
					cycle = true;
				}
			}
			return;//이미 방문한 번호면 return
		}
		
		if(visit[num]==false && students[num]==start){
			visit[num] = true;
			cycle = true;
			answer-=depth;
			return;
		}
		if(visit[num]==false){
			visit[num] = true;
			dfs(students[num],depth+1);
			
			
			if(cycle==false){
				visit[num] = false;
			}
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		students = new int[N+1];
		visit = new boolean[N+1];
		nonVisit = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) students[i] = Integer.parseInt(st.nextToken());
		answer=N;
	}
}







// 2 3 4 5 6 7 4
