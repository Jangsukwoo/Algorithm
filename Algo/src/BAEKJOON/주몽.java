package BAEKJOON;

import java.util.Arrays;
import java.util.Scanner;

public class 주몽 {

	static int first;
	static int last;
	static int count;
	static int M;
	static int[] list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();// 6
		int M = sc.nextInt();// 9

		int[] list = new int[N];
		int first = 0;
		int last = N - 1;
		int count = 0;

		for (int i = 0; i < N; i++) {
			list[i] = sc.nextInt();
		}
		Arrays.sort(list);
		while (first < last) {
			if (list[first] + list[last] < M) {
				first++;
			} else if (list[first] + list[last] > M) {
				last--;
			} else if (list[first] + list[last] == M) {
				last--;
				count++;
				first++;
			}
		}
		System.out.println(count);
	}
}