package CodingTest;

import java.util.ArrayList;
import java.util.HashSet;

public class 카21상4 {
	static int count = 0;
	static class Node{
		int nodeNumber;
		int cost;
		public Node(int nodeNumber, int cost) {
			this.nodeNumber = nodeNumber;
			this.cost = cost;
		}
	}
	static int minCost = Integer.MAX_VALUE;
	static int endNode;
	static Node[] nodes;
	static HashSet<Integer> trapSet = new HashSet<Integer>();
	static ArrayList<Node>[] graph; 
	static HashSet<Integer>[] inbound;
	static HashSet<Integer>[] outbound;
	static int[] trapCount;
	public static void main(String[] args) {
		//solution(3, 1, 3, new int[][] {{1,2,2},{3,2,3}}, new int[] {2});
		solution(4, 1, 4, new int[][] {{1,2,1},{3,2,1},{2,4,1}}, new int[] {2,3});
	}
	public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
		int answer = 0;
		for(int i=0;i<traps.length;i++) trapSet.add(traps[i]);
		graph = new ArrayList[n+1];
		inbound = new HashSet[n+1];
		outbound = new HashSet[n+1];
		nodes = new Node[n+1];
		trapCount = new int[n+1];
		for(int num=1;num<=n;num++) {
			graph[num] = new ArrayList<Node>();
			inbound[num] = new HashSet<Integer>();
			outbound[num] = new HashSet<Integer>();
		}
		
		for(int road=0;road<roads.length;road++) {
			int from = roads[road][0];
			int to = roads[road][1];
			int cost = roads[road][2];
					
			outbound[from].add(to);
			inbound[to].add(from);
			
			graph[from].add(new Node(to,cost));
			graph[to].add(new Node(from,cost));
		}
		
		endNode = end;
		
		dfs(start,0,Integer.toString(start));
		
		answer = minCost;
		System.out.println("답"+answer);
		return answer;
	}
	private static void dfs(int currentNode,int sumCost ,String path) {
		
		
		System.out.println(path);

		
		if(currentNode == endNode) {
			System.out.println("마지막노드임!!!!!!!!!!!!!!!!!!!");
			minCost = Math.min(minCost,sumCost);
			System.out.println(minCost);
			return;
		}
		if(trapSet.contains(currentNode)){
			
			System.out.println("함정임");
			/*
			 * 함정인경우
			 */
			trapCount[currentNode]++;
			
			//역방향 처리
			System.out.println("현재함정번호"+currentNode);
			System.out.println(graph[currentNode].size());
			System.out.println("2번의 인바운드"+inbound[2].toString());
			System.out.println("2번의 아웃바운드"+outbound[2].toString());
			System.out.println("3번의 인바운드"+inbound[3].toString());
			System.out.println("3번의 아웃바운드"+outbound[3].toString());
			for(int adj=0;adj<graph[currentNode].size();adj++) {
				int adjNumber = graph[currentNode].get(adj).nodeNumber;
				if(inbound[currentNode].contains(adjNumber) && outbound[adjNumber].contains(currentNode)){//함정으로 들어오는 선이면
					inbound[currentNode].remove(adjNumber);
					outbound[currentNode].add(adjNumber);
					inbound[adjNumber].add(currentNode);
					outbound[adjNumber].remove(currentNode);
				}else if(outbound[currentNode].contains(adjNumber)  && inbound[adjNumber].contains(currentNode)) {//함정에서 나가는 선이면
					outbound[currentNode].remove(adjNumber);
					inbound[currentNode].add(adjNumber);
					outbound[adjNumber].add(currentNode);
					inbound[adjNumber].remove(currentNode);
				}
			}
			System.out.println("2번의 인바운드"+inbound[2].toString());
			System.out.println("2번의 아웃바운드"+outbound[2].toString());
			System.out.println("3번의 인바운드"+inbound[3].toString());
			System.out.println("3번의 아웃바운드"+outbound[3].toString());
		}



		for(int next=0;next<graph[currentNode].size();next++) {
			Node nextNode = graph[currentNode].get(next);
			
			int nextNodeNumber = nextNode.nodeNumber;
			System.out.println("아웃바운드리스트"+outbound[currentNode].toString());
			if(outbound[currentNode].contains(nextNodeNumber)) { //노드에서나가는 선만
				System.out.println("현재 " + currentNode+"에서 나가는 선 번호는"+nextNodeNumber);
				int cost = nextNode.cost;
				if(trapSet.contains(nextNodeNumber)) {//함정이면서 밟은 회수가 2회까지만
					if(trapCount[nextNodeNumber]<3) {
						dfs(nextNodeNumber,sumCost+cost,path+" "+Integer.toString(nextNodeNumber));
					}
				}else {//함정이 아닌경우 그냥 보냄
					dfs(nextNodeNumber,sumCost+cost,path+" "+Integer.toString(nextNodeNumber));
				}
			}
		}
	}
}
