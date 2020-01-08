package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 소수의 곱  
 * 주어진 소수가 2,5,7이면
 * 2,5,7로 만들 수 있는 소수의 곱들 중에 N번째의 소수값은?
 * 
 * 소수의 개수 K
 * 1<=K<=100
 * 
 * N번째 숫자 찾기
 * 1<=N<=100000
 * 
 * 곱해나가면서
 * 어차피 N번째 숫자에만 관심있고
 * 연산결과의 첫번째들은 다 지나갈거다
 * 
 * 또한 어떤 곱셈, 즉 2 4 8 처럼 2를 이용한 곱셈들에 대해서는
 * 새로 작은 2로만들어진 수에 대한 결과 값을 넣어준후
 * 굳이 더 볼 필요 없다
 * 
 * ->사탕상자 꼭 풀어보시라고함
 * 세그트리 이용하면 몇번쨰인지도 알 수 가 있다
 * 
 */
public class 소수의곱_3일차 {
	static int K;
	static int N;
	static HashSet<Long> multiNumSet = new HashSet<>();
	static PriorityQueue<Long> pq = new PriorityQueue<>();
	static long[] prime;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		prime = new long[K];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++){
			prime[i] = Long.parseLong((st.nextToken()));
			multiNumSet.add(prime[i]);
			pq.add(prime[i]);
		}
		for(int i=0;i<N;i++) {
			long checkPrime = pq.poll(); //제일 작은 소수 빼서
			for(int j=0;j<K;j++){//곱해서 넣음
				if(!multiNumSet.contains(prime[j]*checkPrime)){//곱셈 안해본케이스면
					multiNumSet.add(prime[j]*checkPrime);//케이스에 추가
					pq.offer(prime[j]*checkPrime);
					if(checkPrime%prime[j]==0) break;//어차피 이전부터 곱셈 해온 숫자면 굳이 안봐되 된다.
				}
			}
			if(i==N-1) System.out.println(checkPrime);
		}
	}
}