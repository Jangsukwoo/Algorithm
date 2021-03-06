package SDS복습_그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 위상정렬 안보고 짜기. 큐 이용
1
5 4
10 10 10 100 10
1 2
2 3
4 3
4 5
3

1
5 4
10 10 10 100 10
1 2
2 3
4 5
4 3
3

 */
public class 위상정렬_ACMCraft {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N,K;
	static ArrayList<Integer>[] edgeList;
	static int[] buildTime;
	static int[] countTime;
	static int[] indegree;
	static int target;
	static Queue<Integer> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			setData();
			topologicalSort();
			bw.write(countTime[target]+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void topologicalSort(){
		while(!q.isEmpty()){
			int u = q.poll();
			for(Integer v : edgeList[u]){
				indegree[v]-=1;
				countTime[v] = Math.max(countTime[v], countTime[u]+buildTime[v]);
				if(indegree[v]==0){
					if(v==target) return;
					q.offer(v);
				}
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		buildTime = new int[N+1];
		indegree = new int[N+1];
		countTime = new int[N+1];
		edgeList = new ArrayList[N+1];

		for(int i=1;i<=N;i++) edgeList[i] = new ArrayList<Integer>();
		for(int i=1;i<=N;i++) buildTime[i] = Integer.parseInt(st.nextToken());
		for(int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			edgeList[u].add(v);
			indegree[v]+=1;//진입차수 작성
		}
		st = new StringTokenizer(br.readLine());
		target = Integer.parseInt(st.nextToken());
		q = new LinkedList<Integer>();
		for(int i=1;i<=N;i++) {
			countTime[i] = buildTime[i];
			if(indegree[i]==0) q.add(i);
		}


	}
}
