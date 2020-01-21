package SDS_WEEK1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
public class 최솟값찾기_2일차{
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());


		/*
		 * 시간초과 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		 */

		arr = new int[N+1]; //line 1
		st = new StringTokenizer(br.readLine());//line 2

		/*
		 * 여기서 line 1이랑 line 2 바꾸면 시간초과난다..!!!!!!!!;;;
		 */

		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
		int[] res = sw(N,L,arr);
		for(int i : res) bw.append(i+" ");
		bw.flush();
		bw.close();
	}	
	private static int[] sw(int N, int L, int[] arr) {
		int[] rArr = new int[N];
		Deque<Integer> dq = new LinkedList<Integer>();
		for(int i=0;i<N;i++){
			while(!dq.isEmpty() && dq.peekFirst() <(i-L+1)){
				dq.removeFirst();
			}
			while(!dq.isEmpty() && arr[dq.peekLast()]>arr[i]){
				dq.removeLast();
			}
			dq.offerLast(i);
			rArr[i] = arr[dq.peekFirst()];
		}
		return rArr;
	}
}