package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 테스트 케이스 최대 200
 * 7자리 문자 받아서 
 * 7자리 문자 적절히 섞어서
 * 만들 수 있는 소수의 개수 출력하기
1
011

1276543
 */
public class 산업스파이의편지 {
	static int T;
	static int sum;
	static String number;
	static int[] pick;
	static int n,r;
	static boolean[] pickVisit;
	static boolean[] prime = new boolean[10000000];
	static boolean[] visit;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		makePrimeSetByEra();
		T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			sum=0;
			number = br.readLine();
			n = number.length();
			pickVisit = new boolean[n];
			visit = new boolean[(int) Math.pow(10,(n+1))];
			for(int i=1;i<=number.length();i++){
				r = i;
				dfs(0,"");
			}
			System.out.println(sum);
		}
	}
	private static void dfs(int depth,String makeNumber){
		if(depth==r){
			if(Integer.parseInt(makeNumber)>1 && !prime[Integer.parseInt(makeNumber)] && visit[Integer.parseInt(makeNumber)]==false){ 
				visit[Integer.parseInt(makeNumber)] = true;
				sum++;
			}
			return;
		}
		for(int i=0;i<n;i++){
			if(pickVisit[i]==false) {
				pickVisit[i] = true;
				dfs(depth+1,makeNumber+number.charAt(i));
				pickVisit[i] = false;
			}
		}
	}
	private static void makePrimeSetByEra() {//소수 집합 만들기
		for(int i=2;i<=9999999;i++){
			if(i%2==0 && i>2) continue;//짝수는 건너뜀
			for(int j=2;(j*i)<=9999999;j++){
				prime[j*i] = true;
			}
		}
	}
}
