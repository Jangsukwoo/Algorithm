package CodingStudyHW;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
 * Kruscal 복습
 *	
 * Kruscal -> MST 만들기
 * MST? -> 최소 신장 트리
 * 이를 위해 Union - Find 개념을 쓴다.
 * 자료구조는 간선리스트를 이용 
 * 
 * Step 1. 비용을 기준으로 가장 최소의 값부터 조회할 수 있도록 정렬
 * Step 2. Union-Find로 묶어준다. -> 부모가 같지 않은 경우에 묶어줌.
 * 
 */
public class 별자리만들기 {
	static class Star{ //별 노드
		int number;
		double x;
		double y;
		public Star(int number, double x, double y) {
			this.number = number;
			this.x = x;
			this.y = y;
		}
	}
	static class EdgeBetweenStar{ //별과 별 사이의 간선정보 
		int from;
		int to;
		double dist;
		public EdgeBetweenStar(int from, int to, double dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}
	static Star[] stars;
	static int n;
	static List<EdgeBetweenStar> edges;
	static int[] parent;
	static double answer;
	public static void main(String[] args) {
		//전처리
		setData();
		
		//엣지리스트 집합 만들기
		makeEdgeList();
		
		//Kruscal 
		kruscal();
		
		//상대 오차 10^-2
		answer *= 100;//100 곱해주고
		answer = Math.floor(answer); //소수점 다 버리고
		answer /=100;//100으로 다시 나눔
		System.out.println(answer);
	}
	
	
	private static void kruscal() {
		//step 1. 정렬
		Collections.sort(edges, new Comparator<EdgeBetweenStar>() {
			@Override
			public int compare(EdgeBetweenStar o1, EdgeBetweenStar o2) {
				return Double.compare(o1.dist,o2.dist);
			}
		});//nLogn
		
		int edgeSize = edges.size();
		//step 2. union - find
		for(int i=0;i<edgeSize;i++) {
			if(find(edges.get(i).from)!=find(edges.get(i).to)){//부모가 같지 않으면 묶어준다.
				union(edges.get(i).from,edges.get(i).to);
				answer+=edges.get(i).dist;
			}
		}
	}
	//union
	private static void union(int a, int b) {
		int aParent = find(a);
		int bParetn = find(b);
		parent[bParetn] = aParent;
	}

	//find
	private static int find(int child) {
		if(child==parent[child]) return parent[child];//부모 
		return parent[child] = find(parent[child]);
	}


	private static void makeEdgeList() {
		boolean[][] fromTovisit = new boolean[n+1][n+1];
		for(int from=1;from<=n;from++){//n^2 (n<=100) 
			for(int to=1;to<=n;to++) {
				if(from!=to && fromTovisit[from][to]==false){//자기 외의 다른 별들에 대한 거리 계산하며 edges 리스트에 추가 
					double fromX = stars[from].x;
					double fromY = stars[from].y;
					double toX = stars[to].x;
					double toY = stars[to].y;
					double xDist = fromX-toX;
					double yDist= fromY-toY;
					xDist = Math.pow(xDist,2);
					yDist = Math.pow(yDist,2);
					double dist = Math.sqrt(xDist+yDist);
					edges.add(new EdgeBetweenStar(from, to, dist));
				}
			}
		}
	}
	
	
	private static void setData() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		stars = new Star[n+1];
		parent = new int[n+1];
		for(int no=1;no<=n;no++){
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			stars[no] = new Star(no, x, y);
		}
		edges = new ArrayList<EdgeBetweenStar>();
		for(int i=1;i<=n;i++) parent[i] = i;//parent 배열 초기화 
	}
}
