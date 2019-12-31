package SDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
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
 * 자바의 우선순위큐로하면 터지는데
 * C++로 짜면 통과..
 * 자바로  로직을 바꿔보자..
 * 
 * 데크로 바꿔서 43%까지 퍼센트는 올렸지만
 * 43%에서 또 시간초과가 난다.....
 * c++로 통과해버리자
 * 
 * ----- 다시. 자바로 통과하는 코드를 만들어보자
 * 
 * 1. queue에 들어가는 int 2개를 1개로 바꾸고
 * 인덱스만 넣어서 처리
 * ->43% 시간초과
 * 
 * 2. bw.write를 반복문안에서 돌떄마다 해주지 않고 따로 정답배열을 만들어서 처리
 * ->43% 시간초과
 * 
 * 3. bw.write에서 append로 수정
 * ->43% 시간초과
 * 
 * 4. bufferedReader, Writer, Stringtokenizer 전부 static에서  메인내로 위치변경
 * ->43% 시간초과
 * 
 * 5.->Dequeue를 static에서 slidingWindow 메소드 내부로 선언 위치 변경 
 * ->43% 시간초과
 * 
 * 6. static으로 선언된 answerList를 지우고 slidingWindow 내에서 int[] new로 힙메모리로 잡고 return처리
 * ->43% 시간초과
 * 
 * 7.정답배열만드는 for(int i=1;i<=N;i++)를 for(int num : result)로 변경 및 1<=i<=N을 0<=i<N으로 변경
 * ->43% 시간초과
 * 
 * 8.불필요한 import 제거, slidingWindow에 throws IOException 제거
 * -> 43% 시간초과
 * 
 * 9.변수명 짧게 변경 
 * -> 43% 시간초과
 * 
 * 10.N,L,arr 변수 static에서 main 메소드 내로 선언 위치 변경 
 * -> 43% 시간초과
 * 
 * 11.주석제거
 * -> 43% 시간초과
 * 
 * 12.dq.poll에서 remove로 바꿔보기
 * ->43% 시간초과
 */
public class 시간복잡도_최솟값찾기_슬라이딩윈도우_데크 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
		//처리
		int[] res = sw(N,L,arr);
		//출력
		for(int i : res) bw.append(i+" ");
		bw.flush();
		bw.close();
	}	
	private static int[] sw(int N, int L, int[] arr) {
		//i는 0보다 커야한다.
		//i-2 ~ i 
		int[] rArr = new int[N];
		Deque<Integer> dq = new LinkedList<Integer>();
		for(int i=0;i<N;i++){ //i가 3일때부터 
			while(!dq.isEmpty() && dq.peekFirst() <(i-L+1)){//왼쪽 제거 
				//비어있으면 검사가 안되니까 isEmpty로 체크
				//i-L+1보다 작은 인덱스 값들에 대해 제거
				dq.removeFirst();
			}
			while(!dq.isEmpty() && arr[dq.peekLast()]>arr[i]){
				//비어있으면 검사가 안되니까 isEmpty로 체크
				dq.removeLast();
			}
			dq.offerLast(i);//인덱스를 넣는다.
			rArr[i] = arr[dq.peekFirst()];
		}
		return rArr;
	}

	//아래는 Queue에 Int 2개 넣은 코드 -> 43% 시간초과 
	//	private static void slidingWindow() throws IOException {
	//		//i는 0보다 커야한다.
	//		//i-2 ~ i 
	//		for(int i=1;i<=N;i++){ //i가 3일때부터 
	//			int value = Integer.parseInt(st.nextToken());
	//			while(!dq.isEmpty() && dq.peek()[1]<(i-L+1)){//왼쪽 제거 
	//				//비어있으면 검사가 안되니까 isEmpty로 체크
	//				//i-L+1보다 작은 인덱스 값들에 대해 제거
	//				dq.pollFirst();
	//			}
	//			while(!dq.isEmpty() && dq.peekLast()[0]>value){
	//				//비어있으면 검사가 안되니까 isEmpty로 체크
	//				dq.pollLast();
	//			}
	//			dq.offerLast((new int[] {value,i}));
	//			bw.write(dq.peekFirst()[0]+" ");
	//		}
	//	}	
}

