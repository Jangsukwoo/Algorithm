package 알고리즘문제해결전략_AlgoSpot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 위상정렬 ? : 정점을 배열하여 방향이 있는 비순환 그래프를 만든다.
 * 
 * dfs로 위상정렬을 할 때 
 * 모든 정점에 대해서 dfs를 한다.(dfsAll)
 * 이때 각 정점의 dfs가 종료할 때 마다 현재 정점의 번호를 기록한다.
 * 
 * dfsAll이 끝났을 때
 * 기록된 정점의 순서를 뒤집으면 위상 정렬 결과를 얻을 수 있다.
 * dfs가 늦게 종료한 정점일수록 정렬 결과의 앞에 위치한다.
 * 
 *  AlgoSpot 고대어 사전 문제
 *  접근 및 풀이 
 *  의존관계를 위해 방향을 정해줘야한다.
 *  이를 방향 그래프로 만들어주고 싶다.
 *  ..푸는중 
 *  선후관계 단방향 인접행렬 만든 후 어떻게 처리할지 좀 더 고민해보기.
 *  dfs가 왜 위상정렬을 보장하는지 확실해질 때 까지 이해하기 
 */
public class dfs_고대어사전_위상정렬 {
	static int T;
	static String[] words;
	static int wordsize;
	static int[][] alphabetGraph = new int[26][26]; // a-z에서 각 노드는 알파벳
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			wordsize = Integer.parseInt(br.readLine());
			alphabetGraph = new int[26][26];//알파벳 그래프 초기화 
			words = new String[wordsize];
			for(int i=0;i<wordsize;i++) {
				words[i] = br.readLine();//단어 입력 받고
			}
			makeAlphabetGraph();//알파벳의 선후 관계 (인접행렬)을 만든다.
		}
	}
	private static void makeAlphabetGraph() {
		for(int i=0;i<(wordsize-1);i++){
			for(int j=0;j<words[i].length();j++) {
			}
		}
	}
}
