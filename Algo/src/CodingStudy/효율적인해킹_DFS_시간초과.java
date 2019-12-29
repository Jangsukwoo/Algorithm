package CodingStudy;

/*
 * 이코드 그대로 c++로 바꾸니까 통과했다...................................................
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;


public class 효율적인해킹_DFS_시간초과 {
	static int N,M;
	static int max;
	static int cnt;
	static int[] hackingCount;
	static int[] visit;
	static int startNumber;
	static ArrayList<Integer>[] relationAdjList;
	static StringBuilder sb = new StringBuilder();
	static HashSet<Integer> hackingCase = new LinkedHashSet<Integer>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		N = sc.nextInt();
		M = sc.nextInt();
		relationAdjList = new ArrayList[N+1];//인접리스트 생성
		for(int i=0;i<=N;i++) relationAdjList[i] = new ArrayList<Integer>();
		hackingCount = new int[N+1];
		for(int relation=0;relation<M;relation++){
			int A = sc.nextInt();
			int B = sc.nextInt();
			relationAdjList[B].add(A);//From->To
			hackingCase.add(B);
		}
		//처리
		hacking();
		//출력
		printAnswer();
	}
	private static void hacking(){
		visit = new int[N+1];
		for (Integer startcase : hackingCase) {
			cnt=0;
			startNumber = startcase;
			visit[startcase] = startcase;
			dfs(startcase);
			updateHackingCount(startcase);
		}
	}
	private static void printAnswer(){//해킹 가장 많이 하는 컴퓨터들 출력
		for(int num=1;num<=N;num++) {
			if(hackingCount[num]==max) {
				sb.append(num+" ");
			}
		}
		System.out.println(sb.toString());
	}
	private static void updateHackingCount(int computerNumber) {
		hackingCount[computerNumber] = cnt;
		max = Math.max(cnt,max);
	}
	private static void dfs(int currentComputerNumber) {
		int caseSize = relationAdjList[currentComputerNumber].size();
		for(int target=0;target<caseSize;target++){
			int targetNumber = relationAdjList[currentComputerNumber].get(target);
			if(visit[targetNumber]!=startNumber){
				cnt++;
				visit[targetNumber] = startNumber;
				dfs(targetNumber);
			}
		}
	}
}
