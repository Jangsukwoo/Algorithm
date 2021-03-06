package Samsung;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 시작 1225
 * 다리의 길 2이상
 * 넘버링 안된 섬은 7 
 * 130
 * 
 */
class RC17472{
	int row;
	int col;
	public RC17472(int r, int c) {
		row = r;
		col = c;
	}
}
class EdgeInfo{
	int from;
	int to;
	int dist;
	public EdgeInfo(int f,int t,int d){
		from = f;
		to = t;
		dist = d;
	}
}
public class 상시_다리만들기2 {
	static int N,M;
	static int[][] nation;
	static boolean[] visitIsland;
	static int min;
	static int islandSize;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static int[][] adjMatrix;
	static int INF = Integer.MAX_VALUE;
	static ArrayList<EdgeInfo> EdgeInfoList = new ArrayList<EdgeInfo>();
	static int dataSize;
	static int[] data;
	static EdgeInfo[] tryCase;
	static Queue<RC17472> q = new LinkedList<RC17472>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		nation = new int[N][M];
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				nation[row][col] = sc.nextInt();
				if(nation[row][col]==1) nation[row][col] = 7;//넘버링 안된 섬
			}
		}
		min = INF;
		setIsland();
		setAdjMatrix();
		setForCombinationData();
		combination(0,0);
		if(min==INF) System.out.println("-1");
		else System.out.println(min);
	}

	private static void setForCombinationData() {
		dataSize= EdgeInfoList.size();
		data = new int[dataSize];
		tryCase = new EdgeInfo[islandSize-2];
	}
	private static void combination(int idx,int cnt) {
		if(cnt==(islandSize-2)){//간선 개수만큼
			updateMinimum();
			return;
		}
		for(int i=idx;i<dataSize;i++){
			tryCase[cnt] = EdgeInfoList.get(i);
			combination(i+1,cnt+1);
		}
	}
	private static void updateMinimum() {
		//현재 엣지를 두고 최소값 갱신
		visitIsland = new boolean[islandSize];
		int sum=0;

		int[][] testAdjMatrix = new int[islandSize][islandSize];
		for(int row=1;row<islandSize;row++) {
			for(int col=1;col<islandSize;col++){
				if(row!=col) testAdjMatrix[row][col] = INF;
			}
		}

		int num = tryCase[0].from;
		for(int i=0;i<(islandSize-2);i++){
			testAdjMatrix[tryCase[i].from][tryCase[i].to] = tryCase[i].dist;
			testAdjMatrix[tryCase[i].to][tryCase[i].from] = tryCase[i].dist;
			sum+=tryCase[i].dist;
		}



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
			if(visitIsland[i]==false) return;
		}

		min = Math.min(sum,min);
		return ;
	}
	private static void setAdjMatrix(){
		/*
		 * 각 칸에대해서 다른 노드로 가보면서 
		 * 출발노드 -> 목적지 노드의 가중치의 정보를 인접행렬에 쓰는데
		 * 최소값을 쓴다.
		 */
		for(int i=1;i<islandSize;i++){//초기 인접행렬 세팅
			for(int j=1;j<islandSize;j++){
				if(i==j) adjMatrix[i][j]=0;
				else adjMatrix[i][j] = INF;
			}
		}
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				if(nation[row][col]!=0){//섬이면
					for(int dir=0;dir<4;dir++){
						int from = nation[row][col];
						EdgeInfo Edge = getEdge(row,col,from,0,dir);
						if(Edge!=null){//엣지가 구해졌고
							if(Edge.dist>=2){//거리가 2이상이라면
								adjMatrix[Edge.from][Edge.to] = Math.min(Edge.dist,adjMatrix[Edge.from][Edge.to]);
								adjMatrix[Edge.to][Edge.from] = Math.min(Edge.dist,adjMatrix[Edge.to][Edge.from]);
							}
						}
					}
				}
			}
		}
	}
	private static EdgeInfo getEdge(int row, int col, int from, int cnt, int dir) {
		int nr = row+dr[dir];
		int nc = col+dc[dir];
		while(true){
			if(rangeCheck(nr, nc)){//가지면
				if(nation[nr][nc]==0){
					cnt++;
					nr+=dr[dir];
					nc+=dc[dir];
					continue;
				}else if(nation[nr][nc]==from) {
					return null;
				}else if(nation[nr][nc]!=0 && nation[nr][nc]!=from){//다른 노드를 만났다면
					if(cnt>=2) {
						EdgeInfo Edge= new EdgeInfo(from,nation[nr][nc],cnt);
						EdgeInfoList.add(Edge);
						return Edge;
					}else return null;
				}
			}else return null;
		}
	}
	private static void setIsland() {
		int island = 1;
		//맵에 섬 넘버링
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++){
				if(nation[row][col]==7){
					nation[row][col] = island;
					q.add(new RC17472(row, col));
					BFS(island);//BFS로 섬 마킹
					island++;
				}
			}
		}
		islandSize = island;
		adjMatrix = new int[islandSize][islandSize];		
	}
	private static void BFS(int island){
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				RC17472 curRC = q.poll();
				for(int dir=0;dir<4;dir++){
					int nr = curRC.row+dr[dir];
					int nc = curRC.col+dc[dir];
					if(rangeCheck(nr,nc)){
						if(nation[nr][nc]==7) {
							nation[nr][nc] = island;
							q.add(new RC17472(nr,nc));	
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}

}
