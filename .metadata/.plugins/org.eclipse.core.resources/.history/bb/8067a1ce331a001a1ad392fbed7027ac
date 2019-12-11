package CodingStudy;

import java.util.LinkedList;
import java.util.Queue;

/*
 * computers는 인접행렬
 * n은 컴퓨터 개수(1<=n<=200)
 * 컴퓨터 : 정점, 연결 : 간선
 * 
 * 연결 관계가 다음과같이 두개 존재하면 (양방향)
 * A-B-C
 * D-F
 * 네트워크의 개수는 2
 * 
 * vertex,edge
 */
public class 네트워크 {
	static Queue<Integer> q = new LinkedList<Integer>();
	static int[][] adjMatrix;
	static boolean[] visit;
	static int vertexSize;
	static int answer;
	public static void main(String[] args) {
		solution(3,new int[][] {{1,1,0},{1,1,1},{0,1,1}});
	}
	public static int solution(int n, int[][] computers) {
		adjMatrix = computers.clone();
		vertexSize = computers.length;
		visit = new boolean[vertexSize];
		for(int vertex=0;vertex<vertexSize;vertex++) {
			if(visit[vertex]==false){//방문하지 않은 정점에 대해서
				answer++;//새로운 네트워크
				q.add(vertex);
				visit[vertex] = true;//방문처리
				BFS();
			}
		}
		System.out.println(answer);
		return answer;
	}
	private static void BFS() {
		while(!q.isEmpty()) {//BFS
			int size = q.size();
			for(int i=0;i<size;i++) {
				int currentVertex = q.poll();
				for(int vertex=0;vertex<vertexSize;vertex++){
					if(currentVertex!=vertex && adjMatrix[currentVertex][vertex]==1 && visit[vertex]==false) {
						q.add(vertex);
						visit[vertex] = true;
					}//자기 자신이 아니고, 인접해 있으면서, 방문하지 않은 정점은 연결되어있음 
				}
			}
		}
	}
}
