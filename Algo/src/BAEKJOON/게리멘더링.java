package BAEKJOON;

import java.util.ArrayList;
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
 */
public class 게리멘더링 {
	static int N;
	static int[] people;
	static int visit;
	static int[][] area;
	static int min;
	static int sumA,sumB;
	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //노드
		people = new int[N]; //각 노드의 인구수 
		area = new int[N][N]; //인접행렬
		min = (Integer.MAX_VALUE/2); //최소값
		for(int i=0;i<N;i++) people[i] = sc.nextInt();

		for(int i=0;i<N;i++) {
			int connect = sc.nextInt();
			for(int j=0; j<connect;j++) {
				int target = (sc.nextInt()-1);
				area[i][target] = 1;
			}
		}

		for(int caseNumber=1,size=1<<N;caseNumber<(size-1);caseNumber++){ //BitMasking
			//N이 4라면 비트는 4개이며
			//0001부터 1110까지 모든 연합 경우의수 본다.
			visit=0;//모든 비트 0
			sumA = 0;
			sumB = 0;
			
			//각 경우의수에 대해서
			for(int node=0;node<N;node++){//0부터 N번째 노드 검사. if문에 걸릴 때 까지 돌것
				if((caseNumber&(1<<node))==0 && ((1<<node)&visit)==0){//0인 연합을 발견했고 
					q.add(node);//발견된 노드를 큐에 넣은 뒤 
					visit|=1<<node;
					while(!q.isEmpty()){ //BFS를 돈다
						int nodeNum = q.poll(); //노드넘버를 꺼내고 
						sumA += people[nodeNum]; //0연합의 인구수로 집계 하고 
						for(int i=0;i<N;i++) {//인접 노드를 찾는다.
							if(area[nodeNum][i]==1 && ((1<<i)&visit)==0&&((1<<i)&caseNumber)==0){//인접해있고 방문안했고 연합이 0이면 
								q.add(i); //큐에 들어간다.
								visit|=1<<i;//들어가면서 1로 방문했음을 마킹
							}
						}
					}
					break;
				}
			}
			for(int node=0;node<N;node++) { //1인 연합에 대한 조사. 위의 BFS와 동일
				if((caseNumber&(1<<node))!=0 && ((1<<node)&visit)==0){//1인 연합. !=0 인 이유를 이 코드를 다시 볼때마다 상기하길
					q.add(node);
					visit|=1<<node;
					while(!q.isEmpty()){
						int nodeNum = q.poll();
						sumB += people[nodeNum];
						for(int i=0;i<N;i++) {
							if(area[nodeNum][i]==1 && ((1<<i)&visit)==0&&((1<<i)&caseNumber)!=0){//인접해있고 방문안했고 연합이 1이면 
								q.add(i);
								visit|=1<<i;
							}
						}
					}
					break;
				}
			}
			if(visit==((1<<N)-1)){//visit이 11111.... 이면 전부 방문했음을 뜻함 
				min = Math.min(Math.abs(sumA-sumB),min);
			}
		}
		if(min==(Integer.MAX_VALUE/2)) System.out.println("-1");
		else System.out.println(min);
		//view();
	}
}
