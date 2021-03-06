package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 08:05 시작
 * 정수 X를 받고
 * N-1번의 연산을함
 * 적용하는 연산은 두가지임
 * 1. 3으로 나누되 3으로 나누어 떨어져야함
 * 2. 2를 곱함
 * 
 * 만든 수를 기록해나가면 수열 A를 만들 수 있다.
 * 수열 A의 순서를 섞은 수열 B를 입력으로 받았을 떄 
 * 수열 A를 구해보자.
 * 
 * 08:40 끝
 */
public class 나3곱2 {
	static long[] seriesB;
	static Set<Long> set = new HashSet<Long>();
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean find;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		for(int i=0;i<N;i++){
			if(find==false) dfs(1,Long.toString(seriesB[i])+" ",seriesB[i]);//depth, 만들어나가는 수열
			else break;
		}
	}
	private static void dfs(int depth, String series, long num){
		if(find) return;
		if(depth==N){//어떻게 만들어왔는진 몰라도 다만들었으면 끝냄 
			find = true;
			System.out.println(series);
			return;
		}
		if(set.contains(num*2)) dfs(depth+1,series+Long.toString(num*2)+" ",num*2);
		if(num%3 ==0 && set.contains(num/3)) dfs(depth+1,series+Long.toString(num/3)+" ",num/3);
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		seriesB = new long[N];
		for(int i=0;i<N;i++) {
			seriesB[i] = Long.parseLong(st.nextToken());
			set.add(seriesB[i]);
		}
	}
}
