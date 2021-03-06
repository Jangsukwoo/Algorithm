package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 다리만들기 2 크루스칼로 풀어보기
 * 
 * 푸는중
9 6
0 0 0 0 1 0 
0 0 0 0 0 0 
0 1 0 0 0 1 
0 0 0 0 0 0 
0 0 0 0 0 0 
0 1 0 0 1 1 
0 0 0 0 0 0 
0 0 0 0 0 0 
0 1 0 0 0 0
 */
public class 다리만들기2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N,M;
	static int[][] islandMap;
	static int islandSize;
	static int[][] map;
	static int[] height;
	static int[] parent;
	static int answer;
	static int edgeSize;
	static boolean[] vertax;
	static boolean possible;
	static ArrayList<Edge> edges;
	static ArrayList<Edge> pickEdges;
	static int INF = Integer.MAX_VALUE/2;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static class Edge{
		int from;
		int to;
		int cost;
		public Edge(int f,int t, int c) {
			from = f;
			to = t;
			cost = c;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		setIslandMap();
		getEdges();
		kruscal();
		mstCheck();
		if(possible) System.out.println(answer);
		else System.out.println("-1");
	}
	private static void mstCheck() {
		if(answer==0) possible = false;
		
		boolean[] visitIsland = new boolean[islandSize];
		int[][] testAdjMatrix = new int[islandSize][islandSize];
		for(int row=1;row<islandSize;row++) {
			for(int col=1;col<islandSize;col++){
				if(row!=col) testAdjMatrix[row][col] = INF;
			}
		}

	
		for(int i=0;i<pickEdges.size();i++){
			testAdjMatrix[pickEdges.get(i).from][pickEdges.get(i).to] = pickEdges.get(i).cost;
			testAdjMatrix[pickEdges.get(i).to][pickEdges.get(i).from] = pickEdges.get(i).cost;
		}

		if(pickEdges.size()>0) {//이부분에서 런타임에러가 났다.
			//고른 엣지가 있는 경우에만 처리를 해줬어야 하는데 없는 상태에서 get(0)을 해버리니 런타임에러가..흑흑
			int num = pickEdges.get(0).from;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(num);
			visitIsland[num]=true;
			while(!q.isEmpty()){
				num = q.poll();
				for(int col=1;col<=(islandSize-1);col++){
					if(testAdjMatrix[num][col]>0 && testAdjMatrix[num][col]<INF && visitIsland[col]==false){
						q.add(col);
						visitIsland[col] = true;
					}
				}
			}
			for(int i=1;i<=(islandSize-1);i++) {
				if(visitIsland[i]==false) {
					possible= false;
					return;
				}
			}
		}
	}
	private static void kruscal() {
		edges.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.cost,o2.cost);
			}
		});//코스트 기준으로 정렬 
		parent = new int[islandSize];
		height = new int[islandSize];
		vertax = new boolean[islandSize];
		//view();
		for(int i=1;i<=(islandSize-1);i++) parent[i] = i;
		for(int i=0,from,to;i<edges.size();i++){
			from = edges.get(i).from;
			to = edges.get(i).to;
			if(edgeSize==(islandSize-2)) return;
			if(parent[from]!=parent[to] && edges.get(i).cost>=2){
				pickEdges.add(edges.get(i));
				vertax[from] = true;
				vertax[to] = true;
				edgeSize++;
				union(from,to);
				answer+=edges.get(i).cost;
			}
		}
	}
	private static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		if(height[aParent]>height[bParent]) {
			int tmp = aParent;
			aParent = bParent;
			bParent = tmp;
		}
		parent[aParent] = bParent;
		if(height[aParent]==height[bParent]) height[bParent]++;
	}
	private static int find(int a) {
		if(a==parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	private static void getEdges(){
		boolean[][][] visit = new boolean[N][M][4];
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++){
				if(islandMap[row][col]!=0){//섬이면
					int fromNumber = islandMap[row][col];
					for(int dir=0;dir<4;dir++){
						int nr = row+dr[dir];
						int nc = col+dc[dir];
						int dist=0;
						visit[row][col][dir] = true;
						while(true){
							if(rangeCheck(nr, nc)){
								if(islandMap[nr][nc]!=fromNumber && islandMap[nr][nc]!=0 && visit[nr][nc][(dir+2)%4]==false){//자기 자신도 아니고 다른 섬이면
									edges.add(new Edge(fromNumber,islandMap[nr][nc],dist));
									visit[nr][nc][(dir+2)%4] = true;
									break;
								}else if(islandMap[nr][nc]==fromNumber) break;
							}else break;
							nr+=dr[dir];
							nc+=dc[dir];
							dist++;
						}
					}
				}
			}
		}
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				System.out.print(islandMap[row][col]);
			}
			System.out.println();
		}
	}
	private static void setIslandMap(){
		int islandNumber=1;
		boolean[][] visit = new boolean[N][M];
		for(int row=0;row<N;row++){
			for(int col=0;col<M;col++){
				if(map[row][col]==1 && visit[row][col]==false){
					Queue<int[]> q = new LinkedList<int[]>();
					q.add(new int[] {row,col});
					visit[row][col] = true;
					islandMap[row][col] = islandNumber;
					while(!q.isEmpty()){
						int[] current = q.poll();
						for(int dir=0;dir<4;dir++) {
							int nr = current[0]+dr[dir];
							int nc = current[1]+dc[dir];
							if(rangeCheck(nr,nc)) {
								if(map[nr][nc]==1 && visit[nr][nc]==false){
									islandMap[nr][nc] = islandNumber;
									visit[nr][nc] = true;
									q.add(new int[] {nr,nc});
								}
							}
						}
					}
					islandNumber++;
				}
			}
		}
		islandSize = islandNumber;
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		islandMap = new int[N][M];
		edges = new ArrayList<Edge>();
		pickEdges = new ArrayList<Edge>();
		possible = true;
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++){
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
