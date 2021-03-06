package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ABCDE {
	static int N,M; //사람 수 , 친구 관계 수 M
	static ArrayList<Integer>[] person;
	static boolean[] visit;
	static boolean find;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		person = new ArrayList[N];
		visit = new boolean[N];
		for(int i=0;i<N;i++) person[i] = new ArrayList<Integer>();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			person[p1].add(p2);
			person[p2].add(p1);
		}
		for(int i=0;i<N;i++) {
			if(find) break;
			visit[i] = true;
			dfs(i,1);
			visit[i] = false;
		}
		if(find) System.out.println("1");
		else System.out.println("0");
	}
	private static void dfs(int num, int depth) {
		if(depth==5) {
			find = true;
			return;
		}
		for(int i=0;i<person[num].size();i++) {
			if(find) return;
			int knowPerson = person[num].get(i);
			if(visit[knowPerson]==false) {
				visit[knowPerson]=true;
				dfs(knowPerson,depth+1);
				visit[knowPerson]=false;
			}
		}
	}
}