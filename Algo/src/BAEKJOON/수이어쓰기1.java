package BAEKJOON;

import java.util.Scanner;
/*
 * 처음 코드로해보니
 * 메모리초과가 남..ㄷㄷ
 */
public class 수이어쓰기1 {
	static int N;
	static int size;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int cnt=0;
		int temp = N;
		while(temp>0) {
			temp = temp/10;
			cnt++;
		}
		int nine=9;
		temp = 1;
		for(int i=1;i<=cnt;i++) {
			if(i<cnt) size+=nine*i;
			else size+=(N-temp+1)*i;
			nine*=10;
			temp*=10;
			System.out.println(size);
		}
		System.out.println(size);
	}
}
