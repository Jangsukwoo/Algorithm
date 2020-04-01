package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 부모 자식의 관계가 주어졌을 때 주어진 두 사람의 촌수를 계산
 * 사람들은 1~100
 * 전체 사람수 n
 * 부모 자식의 관계 m개 
 */
public class 촌수계산 {
	static int n;
	static int personA,personB;
	static int m;
	static int blood;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Integer>[] adjList;
	static boolean find;
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		if(find) System.out.println(blood);
		else System.out.println("-1");
	}
	private static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visit = new boolean[n+1];
		q.add(personA);
		visit[personA] = true;
		while(!q.isEmpty()){
			blood++;
			int size = q.size();
			for(int i=0;i<size;i++){
				int currentNum = q.poll();
				for(int j=0;j<adjList[currentNum].size();j++) {
					int nextFamily = adjList[currentNum].get(j);
					if(nextFamily==personB){//찾았으면
						find = true;
						return;
					}
					if(visit[nextFamily]==false) {
						q.add(nextFamily);
						visit[nextFamily]= true;
					}
				}
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		personA = Integer.parseInt(st.nextToken());
		personB = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[n+1];
		for(int i=1;i<=n;i++) adjList[i] = new ArrayList<Integer>();
		for(int i=0,from,to;i<m;i++){
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}
	}
}
