package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 유저 수 N
 * 친구 관게 수  M
 * 
 * 2<=N<=100
 * 1<=M<=5000
 * 
 * 친구의 관계는 A,B로 들어온다
 * 각 유저가 모든 유저에 대해 몇다리 건너 관계인지를 모두 더한 값이
 * 가장 작은 사람을 구하기.
 * 여러명일 경우 가장 번호가 작은 사람 출력
 */
public class 케빈베이컨의6단계법칙 {
	static int N,M;
	static int min = Integer.MAX_VALUE;
	static int cnt;
	static int[] kevinBaconCount;
	static ArrayList<ArrayList<Integer>> relationAdjList = new ArrayList<ArrayList<Integer>>();//인접리스트
	static boolean[] visit;
	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		kevinBaconCount = new int[N+1];
		for(int i=0;i<=N;i++) relationAdjList.add(new ArrayList<Integer>());
		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			relationAdjList.get(A).add(B);
			relationAdjList.get(B).add(A);//양방향
		}
		
		//처리
		getKevinBacon();
		
		//출력
		printAnswer();
	}
	private static void printAnswer() {
		for(int num=1;num<=N;num++) {
			if(min==kevinBaconCount[num]) {
				System.out.println(num);
				return;
			}
		}
	}
	private static void getKevinBacon() {
		for(int num=1;num<=N;num++) {
			cnt=0;
			BFS(num);
			updateMinValue(num);
		}
	}
		
	private static void updateMinValue(int num) {
		min = Math.min(min,kevinBaconCount[num]);
	}
	private static void BFS(int num) {
		visit = new boolean[N+1];
		visit[num] = true;
		q.add(num);
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			for(int i=0;i<size;i++){
				int current = q.poll();
				int currentRelationSize = relationAdjList.get(current).size();
				for(int j=0;j<currentRelationSize;j++) {
					int knowPeople = relationAdjList.get(current).get(j);
					if(visit[knowPeople]==false){
						kevinBaconCount[num]+=cnt;
						visit[knowPeople] = true;
						q.add(knowPeople);
					}
				}
			}
		}
	}
}
