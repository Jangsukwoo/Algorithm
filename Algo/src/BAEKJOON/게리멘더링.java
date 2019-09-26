package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 처음 현장 역량테스트에서는 조금 고민과 설계 후 DFS로 탐색하면서 그루핑을 했다.
 * 첫 연합에 대한 그루핑은 잘 되었으나 두번째 그루핑 때 연합 체킹이 잘 안되었다.
 * 수정하며 DFS와 BFS를 혼합한 형태로 코딩을 시도했으나 시간이 부족해서 끝내 풀지 못했다.
 * 잘못된 로직은 아니였지만 절대 좋은 로직은 아니였음을 깨닫고 반성했다.
 * 
 * 후에 BFS로 문제를 재도전 했다.
 * 첫 노드에 대해서 BFS를 통해 탐색하며 그루핑 해주는 방식이였는데
 * 역시나 모든 연합을 검사하고 묶어주는 단계에서 막혔다.
 * 
 * 처음부터 어렵게 접근한 탓에 BFS를 쓰든 DFS를 쓰든 쉽게 풀리지 않았다. 
 * 결국 좋지 못한 방법으로 설계한 것이 문제라고 결론을 내렸다.  
 * 
 * 이 문제의 핵심은 그루핑을 먼저 한 후 DFS나 BFS를 통해 연합이 잘 되어있는지를 봐야했다. 
 * 어차피 N사이즈는 10까지이고
 * 연합이 가능한 모든 경우의 수를 미리 세팅해놓고
 * BFS를 쓰든 DFS를 쓰든 실제로 그 연합군에 대해
 * 서로가 인접해있는 노드인지, 연합되어있는게 맞는지를 봐주면 쉽게 해결되는 문제였다.
 * 기계적인 생각과 선입견을 경계해야함을 깨닫게 해준 문제다.
 * 
 * + 준희가 쓴 비트마스킹 방법이 너무 괜찮아서 비트마스킹 방법을 사용해서 풀자
 * 
 */
public class 게리멘더링 {
	static int N;
	static int[] people;
	static int visit;
	static int[][] area;
	static int min;
	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		people = new int[N];
		area = new int[N][N];
		min = (Integer.MAX_VALUE/2);
		for(int i=0;i<N;i++) people[i] = sc.nextInt();
	
		for(int i=0;i<N;i++) {
			int connect = sc.nextInt();
			for(int j=0; j<connect;j++) {
				int target = (sc.nextInt()-1);
				area[i][target] = 1;
			}
		}
		
		for(int caseNumber=1,size=1<<N;caseNumber<(size-1);caseNumber++){ // BitMasking
			//N이 4라면 비트는 4개이며
			//0001부터 1110까지 모든 연합 경우의수 본다.
			visit=0;//모든 비트 0
			//for(int areaNum=0;areaNum<)
			//풀다맘 다시 ㄱㄱ
		}
		view();
	}
	private static void view() {
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++) {
				System.out.print(area[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
