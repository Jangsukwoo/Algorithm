package SDS복습_그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class 유니온파인드_집합의표현 {
	/*
	 * 유니온파인드 안보고 짜기
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] parent;
	static int N,M;
	public static void main(String[] args) throws IOException {
		setData();
		unionFind();
		bw.flush();
		bw.close();
	}
	private static void unionFind() throws IOException {
		for(int i=0,command,a,b;i<M;i++){
			st = new StringTokenizer(br.readLine());
			command = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			switch (command) {
			case 0://합치기
				union(a,b);
				break;
			case 1://같은 집합인가?
				int aParent = find(a);
				int bParent = find(b);
				if(aParent==bParent) bw.write("YES"+"\n");
				else bw.write("NO"+"\n");
				break;

			}
		}
	}
	private static void union(int a, int b) {
		int aParent = find(a); //a의 부모
		int bParent = find(b); //b의 부모
		parent[aParent] = bParent; //a 부모의 부모로 만들어버림 
	}
	private static int find(int a) {
		if(a==parent[a]) return a;
		return parent[a] = find(parent[a]);//부모를 리턴 
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); 
		parent = new int[N+1];
		for(int i=1;i<=N;i++) parent[i] = i;
	}
}
