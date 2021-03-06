package 탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리의부모찾기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static ArrayList<Integer>[] adjList;
	static int[] parent;
	static int nodeSize;
	static boolean[] visit;
	static Queue<Integer> queue = new LinkedList<Integer>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		bfs();
		getData();
	}

	private static void getData() throws IOException {
		for(int node=2;node<=nodeSize;node++) {
			bw.write(parent[node]+"\n");
		}
		bw.flush();
		bw.close();
	}

	private static void bfs() {
		visit[1] = true; //root는 true
		queue.add(1);
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i=0;i<size;i++){
				int currentNode = queue.poll();
				for(int j=0;j<adjList[currentNode].size();j++) {
					int childNode = adjList[currentNode].get(j);
					if(visit[childNode]==false) {
						parent[childNode] = currentNode;
						visit[childNode] = true;
						queue.add(childNode);
					}
				}
			}
		}
	}

	private static void setData() throws NumberFormatException, IOException {
		nodeSize = Integer.parseInt(br.readLine());
		adjList = new ArrayList[nodeSize+1];
		visit = new boolean[nodeSize+1];
		parent = new int[nodeSize+1];
		for(int node = 1; node<=nodeSize; node++) adjList[node] = new ArrayList<Integer>();
		for(int i=0;i<(nodeSize-1);i++){
			st = new StringTokenizer(br.readLine());
			int from  = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}
	}
}
