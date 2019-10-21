package BAEKJOON;

import java.util.ArrayList;
import java.util.Scanner;

public class 에너지모으기 {
	static int N;
	static ArrayList<Integer> W = new ArrayList<Integer>();
	static int max;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for(int i=0;i<N;i++) W.add(sc.nextInt());
		dfs(W,0);
		System.out.println(max);
	}
	private static void dfs(ArrayList<Integer> w, int value) {
		int save=0;
		int result=0;
		if(w.size()==2){
			max = Math.max(max,value);
			return ;
		}
		for(int i = 1;i<(w.size()-1);i++){
			result= w.get(i-1)*w.get(i+1);
			value+=result;
			save = w.get(i);
			w.remove(i);
			dfs(w,value);
			w.add(i,save);
			value-=result;
		}
	}
}