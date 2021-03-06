package SDS_WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * N개의 단어
 * K개의 후보
 * 
 * 1. 방문
 * 2. 체크인
 * 3.도착했는지
 * 4.다음이동장소방문
 * 5.체크아웃
 * 
 * 무조건 가르쳐야 하는 a n t i c 
3 0 
antarctica
antahellotica
antacartica

2 6
antaxtica
antaytica
 */
public class 가르침_1일차 {
	static boolean[] alphabetVisit = new boolean[21];
	static int[] pick;
	static char[] alphabetlist = {'b','d','e','f','g','h','j','k','l','m'
			,'o','p','q','r','s','u','v','w','x','y','z'};
	static int N,K;
	static ArrayList<String> words = new ArrayList<>();
	static HashSet<Character> pickSet;
	static int maxCnt;
	static int r;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		r = K-5;
		if(r>0) pick = new int[r];//a n t i c 포함
		else if(K<5){
			System.out.println("0");
			return;
		}else if(K==26) {
			System.out.println(N);
			return;
		}
		for(int i=0;i<N;i++) words.add(br.readLine());
		
		//nCk -> n: 21, k 는 입력값
		pickSet = new HashSet<>();
		nCk(0,0);
		System.out.println(maxCnt);
	}
	private static void nCk(int depth, int idx) {
		if(depth==r) {
			pickSet.clear();
			for(int i=0;i<r;i++) pickSet.add(alphabetlist[pick[i]]);
			check();
			return;
		}
		for(int i=idx;i<21;i++) {
			if(alphabetVisit[i]==false) {
				alphabetVisit[i] = true;
				pick[depth] = i;
				nCk(depth+1,i+1);
				alphabetVisit[i]=false;
			}
		}
	}
	private static void check() {
		int cnt=0;
		for(int i=0;i<N;i++) {//모든 단어에 대해서 
			int length = words.get(i).length();
			boolean flag = false;
			for(int j=4;j<(length-4);j++){ //가운데 부분만 확인한다.
				if(words.get(i).charAt(j)=='a' || words.get(i).charAt(j)=='n' || words.get(i).charAt(j)=='t' || words.get(i).charAt(j)=='i'|| words.get(i).charAt(j)=='c' || pickSet.contains(words.get(i).charAt(j))) continue;
				else {
					flag = true;
					break;
				}
			}
			if(!flag) cnt++;
		}
		maxCnt = Math.max(cnt,maxCnt);
	}
}
