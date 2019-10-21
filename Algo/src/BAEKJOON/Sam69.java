package BAEKJOON;

import java.util.Scanner;

public class Sam69 {
	static int N;
	static int clap;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for(int i=1;i<=N;i++){
			String num = Integer.toString(i);
			char[] data = num.toCharArray();
			for(int j=0;j<data.length;j++) {
				if(data[j]=='3' || data[j]=='6' || data[j]=='9') clap++;
			}
		}
		System.out.println(clap);
	}
}
