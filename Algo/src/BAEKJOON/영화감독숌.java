package BAEKJOON;

import java.util.Scanner;
/*
 * 666, 1666, 2666,3666,, 6666, 7666, 8666,9666 10666, 11666
 */
public class 영화감독숌 {
	static int n;
	static String answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int num = 1;
		int cnt=1;
		while(true){
			String stringNumber = Integer.toString(num);
			if(stringNumber.contains("666")){
				if(cnt==n) {
					System.out.println(stringNumber);
					return;
				}else cnt++;
			}
			num++;
		}
	}
}
