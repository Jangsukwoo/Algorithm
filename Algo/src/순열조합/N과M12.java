package 순열조합;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/*
 * 링크드 해시셋 이용
 */
public class N과M12 {
	static int N,M;
	static int[] data;
	static int[] result;
	static boolean[] visit;
	static LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		data = new int[N];
		visit = new boolean[N];
		result = new int[M];
		for(int i=0;i<N;i++) data[i] = sc.nextInt();
		Arrays.sort(data);//오름차순
		dfs(0,0);
		printAnswer();
	}
	private static void printAnswer() {
		for(String a : linkedHashSet) {
			System.out.print(a);
		}
	}
	private static void dfs(int idx,int cnt) {
		if(cnt==M){
			String answer="";
			for(int i=0;i<M;i++) answer+=Integer.toString(result[i])+" ";
			answer+="\n";
			linkedHashSet.add(answer);
			return ;
		}
		for(int i=idx;i<N;i++) {
			result[cnt] = data[i];
			dfs(i, cnt+1);
		}
	}
}

