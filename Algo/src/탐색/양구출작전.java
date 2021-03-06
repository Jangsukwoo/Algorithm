package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 원래는 리프노드까지 간 후 ,리프 노드와 각 섬의 부모 노드를 메모 전부 메모 (dfs1)
 * 이 후 구한 각각의 리프에대해 1번까지 dfs2를 돌림
 * -> 시간초과
 * 
 * 한번의 dfs로 해결할 방안을 생각하느라 오래 걸렸으며
 * return 값과 각섬에 대해 양,늑대의 정보를 멤버 변수로 추가 후 조건을 걸어서 해결함 
 */
public class 양구출작전 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static boolean[] visit;
	static Island[] islands;
	static ArrayList<Integer>[] adjList;
	static long aliveSheep;
	static class Island{
		long sheeps;
		long wolfs;
		char type;
		public Island(long sheeps, long wolfs, char type) {
			this.sheeps = sheeps;
			this.wolfs = wolfs;
			this.type = type;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		visit[1] = true;
		dfs(1);//부모정보 및 리프정보 구하러
		System.out.println(islands[1].sheeps);
	}
	private static long dfs(int currentNumber) {
		long getSheeps = 0;
		
		for(int i=0;i<adjList[currentNumber].size();i++) {
			
			int nextNumber = adjList[currentNumber].get(i);
			
			if(visit[nextNumber]==false){
				
				visit[nextNumber]= true;
				
				getSheeps = dfs(nextNumber);//거쳐왔을 떄 유입된 양의 량 
				
				if(getSheeps>0){//유입된 양이 한마리 이상이라도 있으면 갱신 해봐야함
					
					switch (islands[currentNumber].type) { //현재 섬이 
					
					case 'S'://양이면
						islands[currentNumber].sheeps+=getSheeps;
						break;
					case 'W'://늑대면
						if(getSheeps>=islands[currentNumber].wolfs){//유입된 양이 현재 섬에 존재하는 늑대보다 많다면
							getSheeps = getSheeps-islands[currentNumber].wolfs;
							islands[currentNumber].sheeps+=getSheeps;
							islands[currentNumber].wolfs = 0;
						}
						else islands[currentNumber].wolfs -= getSheeps;
						break;
					}
				}
			}
			
			if(currentNumber==1) islands[1].sheeps+=getSheeps; //1번 섬이면 합산 집계
			
		}
		return islands[currentNumber].sheeps;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		islands = new Island[N+1];
		adjList = new ArrayList[N+1];
		visit = new boolean[N+1];
		islands[1] = new Island(0,0,'R');
		for(int i=1;i<=N;i++) adjList[i] = new ArrayList<Integer>();
		
 		for(int i=2;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			char type = st.nextToken().charAt(0);
			long popular = Long.parseLong(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			if(type=='S') islands[i] = new Island(popular,0,type);//양,늑대,타입
			else islands[i] = new Island(0,popular,type);//양,늑대,타입
			adjList[i].add(number);
			adjList[number].add(i);
		}
	}
}
