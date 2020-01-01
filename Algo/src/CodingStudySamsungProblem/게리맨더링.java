package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 구역의 개수 N (1<=N<=10)
 * 1<=각 구역의 인구수<=100
 * 
 * 구역을 두 구역으로 나누고 그 두 역의 인구 수의 차이의 가장 최소값을 뽑아낸다.
 * 단, 구역의 정의:서로 인접해있어야한다.
 * 
 * 모든 가능한 경우의 수 
 * nC1, nC2, nC3......nCn-1 인데
 * nCn-1로 뽑은 경우의 수나 nC1로 뽑은 경우의 수가 같으니
 * nC(n/2)만큼 봐도 되지않을까??
 * 
 * 
 */
public class 게리맨더링 {
	static int N; //combination n
	static int R; //combination r
	static int[] areaPeople;
	static int minPeopleGap = Integer.MAX_VALUE;
	static boolean[] pick;
	static int[] pickArray;
	static int[] nonPickArray;
	static boolean[] pickVisit;
	static boolean[] nonPickVisit;
	
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		areaPeople = new int[N+1]; //각 구역의 인구량
		st = new StringTokenizer(br.readLine());
		for(int area=1;area<=N;area++) areaPeople[area] = Integer.parseInt(st.nextToken());//각 구역의 인구량 입력
		for(int area=0;area<=N;area++) adjList.add(new ArrayList<Integer>());//인접리스트 생성
		for(int area=1;area<=N;area++) {//각 구역에 대해서 
			st = new StringTokenizer(br.readLine());
			int adjSize = Integer.parseInt(st.nextToken());
			adjList.get(area).add(area);//자기자신
			for(int areaNum=1;areaNum<=adjSize;areaNum++) adjList.get(area).add(Integer.parseInt(st.nextToken()));			
		}
		
		for(int r=1;r<=(N/2);r++){
			R=r;
			pickArray = new int[R];
			nonPickArray = new int[N-R];
			pick = new boolean[N+1];
			nCr(0,1);
		}//nC1, nC2, nC3 ......... nC(n/2)
		
		if(minPeopleGap==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(minPeopleGap);
		
	}
	private static void nCr(int depth, int idx){ 
		if(depth==R){//nCr 경우에 대해 
			setNonPickArray();//안골라진 도시배열 만들고 
			if(isAdj()){//고른 것들이 서로 인접해있는지?
				//두 구역이 잘 나눠지는 경우 
				int sumA = 0;
				int sumB = 0;
				for(int i=0;i<R;i++) sumA+=areaPeople[pickArray[i]];
				for(int i=0;i<(N-R);i++) sumB+=areaPeople[nonPickArray[i]];
				minPeopleGap = Math.min(minPeopleGap, Math.abs(sumA-sumB)); //최소값 업데이트
			}
			return;
		}
		for(int i=idx;i<=N;i++) {
			if(pick[i]==false) {
				pickArray[depth] = i;
				pick[i] = true;
				nCr(depth+1,i+1);
				pick[i] = false;
			}
		}
	}
	private static boolean isAdj() { //선택된 도시들과 선택되지 않은 도시들이 각각 구역이 맞는지
		HashSet<Integer> pickSet = new LinkedHashSet<Integer>(); //고른 도시 Set
		HashSet<Integer> nonPickSet = new LinkedHashSet<Integer>();//안고른 도시 Set
		boolean[] pickVisit = new boolean[N+1];
		boolean[] nonPickVisit = new boolean[N+1];
		for(int i=1;i<=N;i++) nonPickVisit[i] = true;
		Queue<Integer> q = new LinkedList<Integer>();//BFS
		
		for(int i=0;i<R;i++) pickSet.add(pickArray[i]); //고른 도시 Set 
		for(int i=0;i<(N-R);i++) nonPickSet.add(nonPickArray[i]); //안고른 도시 Set

		q.add(pickArray[0]);//고른도시의 첫 정점부터 출발
		pickVisit[pickArray[0]] = true;

		while(!q.isEmpty()) {//BFS
			int currentNumber = q.poll();
			int size = adjList.get(currentNumber).size();
			for(int i=0;i<size;i++) {
				int child = adjList.get(currentNumber).get(i);//현재 정점의 인접한 자식 정점이 
				if(pickVisit[child]==false && pickSet.contains(child)){//가보지 않은 곳이면서 고른 정점에 속하면
					pickVisit[child] = true;//true로 색칠
					q.add(child);
				}
			}
		}
		
		q.add(nonPickArray[0]); //안고른 정점들에 대해 BFS 탐색. 위와 동일 
		nonPickVisit[nonPickArray[0]] = false;
		while(!q.isEmpty()) {
			int currentNumber = q.poll();
			int size = adjList.get(currentNumber).size();
			for(int i=0;i<size;i++) {
				int child = adjList.get(currentNumber).get(i);//현재 정점의 인접한 자식 정점이 
				if(nonPickVisit[child]==true && nonPickSet.contains(child)){///가보지 않은 곳이면서 안고른 정점에 속하면
					nonPickVisit[child] = false;
					q.add(child);
				}
			}
		}
		//고른도시,안고른도시 전부 탐색 끝
		
		for(int i=1;i<=N;i++) {//두 visit이 같지 않으면 인접 관계 조건에 어긋나는 것이므로 return false
			if(pickVisit[i]!=nonPickVisit[i]) {
				return false;
			}
		}//두 visit이 같으면 통과
		return true;
	}
	private static void setNonPickArray() {
		int idx=0;
		for(int i=1;i<=N;i++) {
			if(pick[i]==false) {
				nonPickArray[idx] = i;
				idx++;
			}
		}
	}
}
