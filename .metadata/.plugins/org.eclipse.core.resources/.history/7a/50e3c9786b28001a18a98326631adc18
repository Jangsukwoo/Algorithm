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
 */
public class 효율적인해킹 {
	static int N,M;
	static int[][] adjHackingMatrix;
	static int max;
	static int cnt;
	static int[] hackingCount;
	static boolean[] discover;
	static HashSet<Integer> hackingSet = new LinkedHashSet<Integer>();
	static ArrayList<Integer> hackingCase = new ArrayList<Integer>();
	static ArrayList<Integer> answerList = new ArrayList<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		adjHackingMatrix = new int[N+1][N+1];
		hackingCount = new int[N+1];
		for(int relation=0;relation<M;relation++){
			int A = sc.nextInt();
			int B = sc.nextInt();
			adjHackingMatrix[B][A]=1;
			if(hackingCase.contains(B)) continue;
			else {				
				hackingSet.add(B);
				hackingCase.add(B);
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
				for(int target=1;target<=N;target++) {
					if(adjHackingMatrix[currentComputerNumber][target]==1 && discover[target]==false) {
						cnt++;
						q.add(target);
						discover[target] = true;
					}
				}
			}
		}//탐색 끝
	}
}
