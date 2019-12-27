package CodingStudy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/*
 * 컴퓨터 개수 : N (1<=N<=10000)
 * 컴픁 A가 컴퓨터 B를 신뢰할 때 (A->B)
 * B를 해킹하면 A가 해킹된다.
 * 즉, C->D->A->B면 
 * B를 해킹했을 때 C까지 총 4대의 컴퓨터를 해킹하는셈.
 * 
 * 한번에 가장 많은 컴퓨터를 해킹하는 컴퓨터 번호를 출력.
 * 번호가 여러개일 수 있으면 오름차순으로.
 * 각 컴퓨터에대해서 연결된 모든 컴퓨터를 본다 (BFS)
 * 이때 연결된 컴퓨터 대수를 카운팅하고 최대값을 가지는 컴퓨터에 대해서 오름차순으로 출력하기. 
 * 
 * --> 인접행렬로 선언하니까 10000 by 10000에 대한 케이스에서 메모리초과가 발생한다.
 * 4byte(int) x 10000 x 10000 = 400MB
 * 
 * 인접행렬을 인접리스트로 바꿔서 재도전 
 */
public class 효율적인해킹 {
	static int N,M;
	static int max;
	static int cnt;
	static int[] hackingCount;
	static boolean[] discover;
	static ArrayList<ArrayList<Integer>> realationAdjList = new ArrayList<ArrayList<Integer>>();
	static HashSet<Integer> rootSet = new LinkedHashSet<Integer>();
	static HashSet<Integer> indegreeSet = new LinkedHashSet<Integer>();
	static ArrayList<Integer> hackingCase = new ArrayList<Integer>();
	static ArrayList<Integer> answerList = new ArrayList<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		for(int i=0;i<=N;i++) realationAdjList.add(new ArrayList<Integer>());//인접리스트 생성
		hackingCount = new int[N+1];
		for(int relation=0;relation<M;relation++){
			int A = sc.nextInt();
			int B = sc.nextInt();
			realationAdjList.get(B).add(A);
			indegreeSet.add(A);
			if(hackingCase.contains(B)) continue;
			else {				
				if(indegreeSet.contains(B)) rootSet.remove(B);
				else {					
					rootSet.add(B);
					hackingCase.add(B);
				}
			}
		}
		hacking();
		setAnswerList();
		printAnswer();
	}
	private static void printAnswer() {//정답 출력 코드
		for(int i=0;i<answerList.size();i++) {
			System.out.print(answerList.get(i)+" ");
		}
	}
	private static void setAnswerList(){//해킹 가장 많이 하는 컴퓨터 리스트 뽑기
		for(int num=1;num<=N;num++) {
			if(hackingCount[num]==max) {
				answerList.add(num);
			}
		}
		Collections.sort(answerList);//오름차순 정렬
	}
	private static void hacking(){//BFS로 전부 탐색해본다.
		for(int i=0;i<hackingCase.size();i++) {
			discover = new boolean[N+1];
			int trycase = hackingCase.get(i);
			cnt=0;
			BFS(trycase);//전부해킹
			updateHackingCount(trycase);
		}
	}
	private static void updateHackingCount(int computerNumber) {
		hackingCount[computerNumber] = cnt;
		max = Math.max(cnt,max);
	}
	private static void BFS(int trycase){
		cnt=0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(trycase);
		discover[trycase]=true;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int currentComputerNumber = q.poll();
				for(int target=0;target<realationAdjList.get(currentComputerNumber).size();target++) {
					int targetNumber = realationAdjList.get(currentComputerNumber).get(target);
					if(discover[targetNumber]==false) {
						cnt++;
						q.add(targetNumber);
						discover[targetNumber] = true;
					}
				}
			}
		}//탐색 끝
	}
}
