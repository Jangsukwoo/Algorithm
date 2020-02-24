package CodingStudy;

import java.util.Scanner;

/*
 * N 최대 10억
 * N을 입력받으면
 * 1부터 N페이지 까지 보면서
 * 0~9 숫자가 각각 몇번 나오는지 출력..훔
 * 
 * 1 2 3 4 5 6 7 8 9 10 
 * 
 * 1 2 3 4 5 6 7 8 9 
 * 0 1 1 1 1 1 1 1 1 1 
 * 
 * 10 11 12 13 14 15 16 17 18 19 
 * 1 12 2 2 2 2 2 2 2 2 
 * -> 
 * 20 21 22 23 24 25 26 27 28 29 
 * 
 * 2 13 13 3 3 3 3 3 3 3
 * 
 * 30 31 32 33 34 35 36 37 38 39
 * 3 14 14 14 4 4 4 4 4 4 
 * 
 * 40 41 42 43 44 45 46 47 48 49 
 * 4 15 15 15 15 5 5 5 5 5
 * 
 * 
 * 
 */
public class 책페이지 {
	static int N;
	static int[] numberFrequency;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
	}
}
