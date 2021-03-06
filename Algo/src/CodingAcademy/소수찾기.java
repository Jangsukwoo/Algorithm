package CodingAcademy;

import java.util.HashSet;
import java.util.Scanner;

/*
 * 에라토스테네스의 체
 * 유성재, 장지윤 학생
 */
public class 소수찾기 {
	static int N;
	static int answer;
	static boolean[] Eratosthenes = new boolean[3001];
	static HashSet<Integer> primeSet = new HashSet<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		makeEratosthenes();
		N = sc.nextInt();
		for(int i=0;i<N;i++) {
			int number = sc.nextInt();
			if(primeSet.contains(number))answer++;
		}
		System.out.println(answer);
	}
	private static void makeEratosthenes() {
		for(int i=2;i<=3000;i++){
			if(Eratosthenes[i]) continue; //소수면 무시
			for(int j=i+i;j<=3000;j+=i){
				Eratosthenes[j] = true;//배수는 전부 true
			}
		}
		for(int i=2;i<=3000;i++) {
			if(Eratosthenes[i]==false) primeSet.add(i);
		}
	}
}
