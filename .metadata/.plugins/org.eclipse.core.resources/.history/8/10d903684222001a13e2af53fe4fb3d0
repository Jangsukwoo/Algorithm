package 알고리즘문제해결전략_AlgoSpot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

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
 *  
 *  결국은 위상정렬이 되는가를 확인하기 위한 그래프(인접행렬)을 만든 후 
 *  위상정렬 정당성 확인 
 *  
 *  위상정렬을 구한 후 
 *  나머지 알파벳들에 대해 어떻게 처리해야할지 하루종일 고민했는데
 *  정말로 그냥 나머지 알파벳에 대해서는 아무렇게나 붙여서 출력해주면 풀리는 문제였다....허탈하다 ㅠ
 *  선,후관계를 가진 알파벳들에 대해서만 위상정렬 해주면 되는 문제였다........! ㅠㅠ
 */
public class dfs_고대어사전_위상정렬 {
	static int T;
	static String[] words;
	static int wordsize;
	static int[][] alphabetGraph;// a-z에서 각 노드는 알파벳
	static boolean[] visit;
	static ArrayList<Integer> order;
	static boolean possible;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++){
			wordsize = Integer.parseInt(br.readLine());
			alphabetGraph = new int[26][26];//알파벳 그래프 초기화
			visit = new boolean[26];//방문정보 초기화
			words = new String[wordsize];
			order = new ArrayList<Integer>();
			possible = false;
			for(int i=0;i<wordsize;i++) {
				words[i] = br.readLine();//단어 입력 받고
			}
			//처리
			makeAlphabetGraph();//step 1: 알파벳의 선후 관계 (인접행렬)그래프를 만든다.
			possible = isTopologicalSort();//step 2 : 만들어진 그래프가 위상정렬인지 확인
			//출력
			if(possible) {
				for (Integer alphabet : order) {
					System.out.print((char)(alphabet+'a'));
				}
				for (int i=0;i<26;i++) {
					if(visit[i]==false) System.out.print((char)(i+'a'));
				}
				System.out.println();
			}
			else System.out.println("INVALID HYPOTHESIS");
			
		}
	}
	private static void makeAlphabetGraph() { //step 1. 그래프 인접행렬 만들기
		for(int i=0;i<(wordsize-1);i++){//단어 vs 단어 비교
			int minLength = Math.min(words[i].length(),words[i+1].length());
			//두 단어를 비교할 때 단어의 길이가 짧은 길이만큼 볼 수 있으니 더 짧은 단어의 길이를 구해준다.
			
			for(int j=0;j<minLength;j++) {
				if(words[i].charAt(j)!=words[i+1].charAt(j)){
					//서로 다른 문자가 나타났을 때 
					int antecedent = words[i].charAt(j)-'a'; //선
					int trailing = words[i+1].charAt(j)-'a'; //후
					alphabetGraph[antecedent][trailing] = 1;
					break;//선후관계 찾았으니 break
				}
			}
		}
	}
	private static boolean isTopologicalSort() { //step 2. 위상정렬인지 확인하기 
		//만들어진 그래프가 위상정렬이 되는지 dfs로 확인해본다. 선후관계가 정당한지?
		for(int antecedent=0;antecedent<26;antecedent++) {
			for(int trailing=0;trailing<26;trailing++) {
				if(visit[antecedent]==false && alphabetGraph[antecedent][trailing]==1){//방향이 있으면서 방문하지 않은
					dfs(antecedent); //dfsAll
				}
			}	
		}
		
		if(reverseDirectionCheck()) return true; //step 3. 위상정렬이 정당한가?
		else return false;
	}
	
	private static void dfs(int antecedent){
		visit[antecedent] = true;//방문처리
		for(int trailing=0;trailing<26;trailing++) {
			if(alphabetGraph[antecedent][trailing]==1 && visit[trailing]==false) dfs(trailing);
		}
		order.add(antecedent);//dfs가 끝날 때 마다 현재 정점을 기록
	}
	
	private static boolean reverseDirectionCheck() { //step 3.위상정렬 시킨 순서에 역방향의 간선이 있는지 확인
		
		Collections.reverse(order);//위상 정렬된 순서를 뒤집는다.
		
		for(int i=0;i<order.size();i++) {//만약 뒤집힌 선,후 관계에 대해 방향이 존재하면 기존 위상 정렬의 역방향을 뜻하므로 false return
			for(int j=i+1;j<order.size();j++) {
				if(alphabetGraph[order.get(j)][order.get(i)]==1){
					return false;
				}
			}
		}
		return true;//통과되면 위상정렬이 정당하므로 true 
	}
}
