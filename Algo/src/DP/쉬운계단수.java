package DP;

import java.util.Scanner;

public class 쉬운계단수 {
	static int N;
	static int[] num = new int[10];
	static int[] copynum = new int[10];
	static int sum;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		for(int i=1;i<10;i++) num[i]=1;//배열에 값 넣어주고

		for(int i=1;i<N;i++){
			copynum[0] = num[1]%1000000000;
			copynum[1] = (num[0]+num[2])%1000000000;
			copynum[2] = (num[1]+num[3])%1000000000;
			copynum[3] = (num[2]+num[4])%1000000000;
			copynum[4] = (num[3]+num[5])%1000000000;
			copynum[5] = (num[4]+num[6])%1000000000;
			copynum[6] = (num[5]+num[7])%1000000000;
			copynum[7] = (num[6]+num[8])%1000000000;
			copynum[8] = (num[7]+num[9])%1000000000;
			copynum[9] = num[8]%1000000000;
			for(int j=0;j<10;j++) num[j]=copynum[j];			
		}
		for(int j=0;j<10;j++) sum=(sum+num[j])%1000000000;	
		System.out.println(sum%1000000000);
		
	}
}
