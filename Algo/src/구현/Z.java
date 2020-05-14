package 구현;

import java.util.Scanner;

public class Z {
	static int N,r,c,entireSize;
	static int maxNumber;
	static int zCnt;
	static int answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		answer = -1;
		//2^N
		entireSize = (int) Math.pow(2,N);
		maxNumber = (int) Math.pow(entireSize,2);
		recursive(0,0,entireSize);
		System.out.println(answer);
	}
	private static void recursive(int ltr, int ltc, int size) {
		if(answer!=-1) return;
		if(zCnt==(maxNumber)) return;
		if(size==1){
			if(ltr==r && ltc==c) answer=zCnt;
			zCnt++;
			return;
		}
		recursive(ltr, ltc, size/2);
		recursive(ltr, ltc+(size/2), size/2);
		recursive(ltr+(size/2), ltc, size/2);
		recursive(ltr+(size/2), ltc+(size/2), size/2);
	}
}
