package BAEKJOON;
/*
 * 구슬을 하나 뺴면
 * 빠진 위치의 양옆이 붙으면서 새롭게 선형구조가 생기고
 * 그 선형 구조에 대해 다시 넘버링이 된다는 점에서 리스트 형태 자료구조를 떠올렸음
 * 문제는 dfs를 태울건데
 * 구슬을 뺀 위치에 다시 그 값을 넣어주는 처리를 어떻게 할까.. 
 * 고민하다가
 * 어레이리스트의 메소드를 보니 
 * add(위치,값)이라는 좋은 메소드가 있었음
 * 
 * dfs가 끝나고 돌아오면 다시 복원을 해줘야하기 떄문에
 * result, save 변수를 두었다.
 * 
 * 어레이리스트 STL 쓰지말고
 * 링크드리스트를 직접 구현해서 풀어보면 좋은 문제인것같다.
 * 특정위치에 값을 넣고 지우는 처리가 필요하기 떄문.
 */
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