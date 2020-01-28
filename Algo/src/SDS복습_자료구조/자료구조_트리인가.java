package SDS복습_자료구조;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 트리의 조건
 * 1. 들어오는 간선이 하나도 없는 단 하나의 루트가 존재
 * 2. 루트 노드를 제외한 모든 노드는 반드시 단 하나의 들어오는 간선이 존재
 * 3. 루트에서 다른노드로 가는 경로는 반드시 가능, 유일. 루트를 제외한 모든 노드에 성립해야함 
 * 
 * 간선의 개수 +1 = 노드의 개수 
 */
public class 자료구조_트리인가 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int testcase=1;
	static boolean caseEnd;
	static boolean isTree;
	static boolean end;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		while(true){
			caseEnd = false;
			isTree = true;
			int edge=0;
			Set<Integer> node = new LinkedHashSet<Integer>();
			while(true){
				int u,v;
				u = sc.nextInt();
				v = sc.nextInt();
				if(u==-1 && v==-1) {
					end=true;
					break;
				}
				if(u==0 && v==0) {
					if(edge==0 || edge+1 == node.size()) isTree = true;
					else isTree = false;
					break;
				}
				else {
					node.add(u);
					node.add(v);
					edge++;
				}
			}
			if(end) break;
			if(isTree) bw.write("Case "+testcase+" is a tree."+"\n");
			else bw.write("Case "+testcase+" is not a tree."+"\n");
			testcase++;
		}
		bw.flush();
		bw.close();
	}
}
