package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 구역개수 N
 * 1번~N번 구역에 인구가 주어짐
 * 
 * 영역을 두개로 나누고
 * 그 나눈 두영역 인구 차이의 최소값?
 * 
6
2 2 2 2 2 2
1 3
1 4
1 1
1 2
1 6
1 5
 */
public class 게리맨더링 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int minDiffPeople = Integer.MAX_VALUE;
	static int[][] adjMatrix;
	static int[] teamA;
	static int[] teamB;
	static HashSet<Integer> A;
	static HashSet<Integer> B;
	static boolean[] pickVisit;
	static int[] peoples;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		bruteForce();
		if(minDiffPeople==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(minDiffPeople);
	}
	private static void bruteForce() {
		for(int r=1;r<=(N/2);r++){
			teamA = new int[r];
			teamB = new int[N-r];
			pickVisit = new boolean[N+1];
			dfs(1,0,r);
		}
	}
	private static void dfs(int idx,int depth, int r) {
		if(depth==r){
			pickTeamB(r);
			if(adjCheckA() && adjCheckB()) {//서로 잘 인접해 있는 관계인지 
				//잘인접해있으면 최소값 갱신
				int sumA = 0;
				int sumB = 0;
				for(int i=1;i<=N;i++) {
					if(pickVisit[i]) sumA+=peoples[i];
					else sumB+=peoples[i];
				}
				minDiffPeople = Math.min(minDiffPeople,Math.abs(sumA-sumB));
			}
			return;
		}
		for(int i=idx;i<=N;i++){
			teamA[depth] = i;
			pickVisit[i] = true;
			dfs(i+1,depth+1,r);
			pickVisit[i]=false;
		}
	}
	private static boolean adjCheckA() {
		boolean[] teamAVisit = new boolean[N+1];
		int startVertax=0;
		for(int i=1;i<=N;i++){
			if(pickVisit[i]) {
				startVertax = i;
				break;
			}
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		teamAVisit[startVertax] = true;
		q.add(startVertax);
		while(!q.isEmpty()){
			int currentVertax = q.poll();
			for(int i=1;i<=N;i++){
				if(adjMatrix[currentVertax][i]==1 && !B.contains(i) && teamAVisit[i]==false){
					teamAVisit[i] = true;
					q.add(i);
				}
			}
		}
		for(int i=1;i<=N;i++) {
			if(pickVisit[i]!=teamAVisit[i]) {
				return false;
			}
		}//이거 통과했으면 잘 연결되어있는 것임 
		
		return true;
	}
	private static boolean adjCheckB() {
		boolean[] teamBVisit = new boolean[N+1];
		for(int i=1;i<=N;i++) teamBVisit[i]=true;
		int startVertax=0;
		for(int i=1;i<=N;i++){
			if(pickVisit[i]==false) {
				startVertax = i;
				break;
			}
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		teamBVisit[startVertax] = false;
		q.add(startVertax);
		while(!q.isEmpty()){
			int currentVertax = q.poll();
			for(int i=1;i<=N;i++){
				if(adjMatrix[currentVertax][i]==1 && !A.contains(i) && teamBVisit[i]==true){
					teamBVisit[i] = false;
					q.add(i);
				}
			}
		}
		for(int i=1;i<=N;i++) {
			if(pickVisit[i]!=teamBVisit[i]) {
				return false;
			}
		}//이거 통과했으면 잘 연결되어있는 것임 
		
		return true;
	}
	private static void pickTeamB(int r) {
		A = new LinkedHashSet<Integer>();
		B = new LinkedHashSet<Integer>();
		for(int i=0;i<r;i++) pickVisit[teamA[i]]=true;
		int teamBIdx = 0;
		for(int i=1;i<=N;i++){
			if(pickVisit[i]==false) {
				teamB[teamBIdx++]=i;
				B.add(i);
			}else A.add(i);
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		peoples = new int[N+1];
		adjMatrix = new int[N+1][N+1];
		for(int i=1;i<=N;i++) peoples[i] = Integer.parseInt(st.nextToken());
		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine());
			int adjSize = Integer.parseInt(st.nextToken());
			for(int j=0;j<adjSize;j++){
				int adjNumber = Integer.parseInt(st.nextToken());
				adjMatrix[i][adjNumber]=1;
				adjMatrix[adjNumber][i]=1;
			}//인접 
		}
	}
}
