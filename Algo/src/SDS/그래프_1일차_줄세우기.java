package SDS;
/*
 * 위상정렬 큐로 구현하는게 코드 만드는게 훨씬 빠르고 쉽다 
 */
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
 * 
 * 건수에 주목한다.
 * N명의 학생과 M번의 키 비교
 * 1<=N<=32000
 * 1<=M<=100000
 * 
 * M개의 줄에는 A,B가 오는데
 * A가 B보다 앞에 서야한다는 의미의 순서로 입력이 주어짐
 * 위상정렬?
 * 
 * 
 */

public class 그래프_1일차_줄세우기 {
	static int N,M;
	static Queue<Integer>[] graph;//큐배열
	static Queue<Integer> q = new LinkedList<>();
	static int[] indegree;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		input();
		topological();
		bw.flush();
	}
	private static void topological() throws IOException {
		while(!q.isEmpty()){
			int zeroIndegree = q.poll();
			 for (int node : graph[zeroIndegree]) {
	                indegree[node]--;
	                if(indegree[node]==0){
	                    q.add(node);
	                    bw.write(node+" ");
	                }
	            }
		}
	}
	private static void input() throws IOException {
		String readLine = br.readLine();
		StringTokenizer st = new StringTokenizer(readLine);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new LinkedList[N+1];//간선리스트
		indegree= new int[N+1];
		for(int i=1;i<=N;i++) graph[i] = new LinkedList<>();//간선리스트
		
		for(int i=0;i<M;i++) {
			readLine = br.readLine();
			st = new StringTokenizer(readLine);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);//Edge List 
			indegree[to]++;//Indegree Count
		}
		
		for(int i=1;i<=N;i++) {
			if(indegree[i]==0){//진입 차수가 0인 정점들만
				q.offer(i);
				bw.write(i+" ");
			}
		}
		
		
	}
}
