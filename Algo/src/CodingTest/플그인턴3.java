package CodingTest;

import java.util.LinkedList;
import java.util.Queue;


public class 플그인턴3 {
	static int N,M;
	static Queue<Integer>[] graph;//큐배열
	static Queue<Integer> q = new LinkedList<>();
	static int[] indegree;
	public static void main(String[] args) {
		System.out.println(solution(121,new int[][]{{1,2},{1,3},{3,6},{3,4},{3,5}}));
	}
	private static void topological(){
		while(!q.isEmpty()){
			int zeroIndegree = q.poll();
			 for (int node : graph[zeroIndegree]) {
	                indegree[node]--;
	                if(indegree[node]==0){
	                    q.add(node);
	                    System.out.print(node+" "+"11");
	                    System.out.println();
	                }
	            }
		}
	}
	public static int[] solution(int total_sp, int[][] skills) {
		int skillPoint = skills.length*2+1;
		int maxNodenumber=0;
		for(int row=0;row<skills.length;row++) {
			for(int col=0;col<skills[row].length;col++) {
				if(maxNodenumber<skills[row][col]) maxNodenumber = skills[row][col];
			}
		}
		int[] answer = {};
		graph = new LinkedList[maxNodenumber+1];
		indegree= new int[maxNodenumber+1];
		for(int i=1;i<=maxNodenumber;i++) graph[i] = new LinkedList<>();//간선리스트
		
		for(int i=0;i<skills.length;i++){
			int from = skills[i][0];
			int to = skills[i][1];
			graph[from].add(to);//Edge List 
			indegree[to]++;//Indegree Count
		}
		
		for(int i=1;i<=maxNodenumber;i++) {
			if(indegree[i]==0){//진입 차수가 0인 정점들만
				q.offer(i);
				System.out.println(i);
			}
		}
		topological();
		return answer;
	}
}
