package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * n보다 크고 2n보다 작거나 같은 소수의 개수
 * 에라토스테네스의 체로 소수 다 구한후 범위 만족하는 숫자 카운팅
 */
public class 베르트랑공준 {
	static StringBuilder sb = new StringBuilder();
	static boolean[] Eratosthenes = new boolean[250001];
	static ArrayList<Integer> primeList = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		makeEratosthenes();
		while(true){
			String readLine = br.readLine();
			int N = Integer.parseInt(readLine);
			if(N==0) break;
			int i=1;
			int cnt=0;
			while(true){//첫번째 소수부터 검사
				if((N < primeList.get(i)) && (primeList.get(i)<=(N*2))){//문제 요구조건
					//소수 prime이
					// n<prime<=2n 을 만족하면
					cnt++;//카운팅
				}
				if(primeList.get(i)>(N*2)) break;//범위 넘어가면 break
				i++;//다음 소수값 인덱스
			}
			sb.append(cnt+"\n");
		}
		System.out.println(sb.toString());
	}
	private static void makeEratosthenes() {//에라토스테네스의 체
		for(int i=2;i<=250000;i++){
			if(Eratosthenes[i]) continue; //소수가 아니면 무시한다.
			for(int j=i+i;j<=250000;j+=i){
				Eratosthenes[j] = true;//배수는 전부 true
			}
		}
		for(int i=1;i<=250000;i++) {//false(소수)에 대해 소수리스트 집합 생성
			if(Eratosthenes[i]==false) primeList.add(i);
		}
	}
}
