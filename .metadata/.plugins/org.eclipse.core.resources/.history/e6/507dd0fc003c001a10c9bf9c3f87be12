package SDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 슬라이딩 윈도우 !!!
 * 유튜브를 보니 구글 코딩 면접?에 나온 영상이 있었다.
 * Ex) Maximum Sum Subarray of Size K
 * 
 * https://www.youtube.com/watch?v=__guhvzO540
 * N개의 인트 배열과 양수 k가 주어진다.
 * K 사이즈를 갖는 인접,연속한 부분 수열중 최대 값을 찾기.
 * 즉 K개로 이루어진 부분 수열의 최대값은 얼마인지를 찾기
 * 
 * K=3 일 때
 * 2,1,5,1,3,2 는 5,1,3 으로 9가 최대값
 * 
 * K=4일 때
 * 1,9,-1,-2,7,3,-1,2 는 9,-1,-2,7로 13이 최대값
 * 
 * K Size를 Window라고 본다.
 * 
 * 
 * 두번째 예제에서
 * wSum과 mSum을 따로 선언해준 후 
 * 배열의 첫번째부의 window만큼 구해나간다.
 * wSum은 windowSum을 저장하고
 * mSum은 알고있는 최대값을 갱신,업데이트 한다.
 * 
 * 윈도우를 보는 반복은 
 * 
 * N사이즈에서 K(window 사이즈)를 뺀
 * n-k만큼 보면 된다.
 * 
 * 시간복잡도는 O(n*k)
 * 
 * 그러나 더 좋은 방법이 있다.
 * 
 * 다음 window와 현재 보고있는 window의 공통부분은 보지않는것.
 * 
 * 가장 첫번째 Window값만 구해주고 
 * windowSum += A[end] - A[end-k];로 하면
 * 시간복잡도는 O(n)이 된다..!
 * 
 * -----백준 11003 최솟값 찾기 문제-----
 * N개의 수와 L이 주어진다.
 * Di = Ai-L+1 ~Ai 중의 최소값이라고 할 때 
 * D에 저장된 수를 출력하는 프로그램을 작성
 * 
 * 1<=L<=N<=5000000
 * A사이즈 long
 * 
 * 안해ㅠㅠㅠㅠㅠ
 */
public class 시간복잡도_최솟값찾기_슬라이딩윈도우_우선순위큐{
	static int N,L;
	static int wMin,min;
	static int[] series;
	static PriorityQueue<int[]> pq;
	static BufferedReader br;
	static StringTokenizer st;
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		setPriorityQueue();

		//처리
		slidingWindow();
		//출력
		bw.flush();
		bw.close();
	}
	private static void slidingWindow() throws IOException {
		//i는 0보다 커야한다.
		//i-2 ~ i 
		for(int i=1;i<=N;i++){
			int value = Integer.parseInt(st.nextToken());
			pq.add(new int[] {value,i});
			while(pq.peek()[1]<(i-L+1)) pq.poll();
			bw.write(pq.peek()[0]+" ");
		}

	}	
	private static void setPriorityQueue() {
		pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1[0],o2[0]);
			}
		});
	}
}
