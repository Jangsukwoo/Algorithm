package CodingStudy;

import java.util.HashSet;
import java.util.LinkedHashSet;

/*
 * String인  numbers 문자의 최대 사이즈 7
 * 17,71 처럼 순서가 있으니 Permutation nPr 
 * 소수인경우 Set에 넣어서 중복검사
 */
public class 소수찾기 {
	static int prime;
	static int n,r;
	static boolean[] visit;
	static char[] number;
	static char[] data;
	static HashSet<Integer> primeSet = new LinkedHashSet<Integer>(); //확인된 소수값
	public static void main(String[] args) {
		solution("0002");
		System.out.println(prime);
	}

	public static int solution(String numbers) {
		data = numbers.toCharArray();
		n = numbers.length();
		for(int i=1;i<=n;i++){//nPr, nP1 부터 nPn까지 전부 조사
			visit = new boolean[n];
			number = new char[i];
			r = i; 
			permutation(0);
		}
		return prime;
	}
	private static void permutation(int cnt) {//DFS
		if(cnt==r){
			String stringNumber="";
			for(int i=0;i<r;i++){
				stringNumber+=number[i];
			}
			int num = Integer.parseInt(stringNumber);
			if(num==2 && !primeSet.contains(num)){//2인경우만 봐준다.
				primeSet.add(2);
				prime++;
				return;
			}
			if(num==1 || num==0 || num%2==0 || primeSet.contains(num)) return; //짝수나 1,0,이미 카운팅한 소수는 거른다 
			isPrime(num);//위 조건에 안걸렸으면 소수인지 검사
			return;
		}
		for(int i=0;i<n;i++){
			if(visit[i]==false){
				visit[i] = true;
				number[cnt] = data[i];
				permutation(cnt+1);
				visit[i] = false;
			}
		}
	}
	private static void isPrime(int num) {
		for(int i=3;i<num;i+=2){	
			if((num%i)==0) {
				return; //어떤 수와 나눠지면 소수가 아니니까 끝낸다.
			}
		}
		//위 포문에서 안걸렸으면 카운팅
		primeSet.add(num); //소수 집합에 추가
		prime++; //카운팅
	}
}
