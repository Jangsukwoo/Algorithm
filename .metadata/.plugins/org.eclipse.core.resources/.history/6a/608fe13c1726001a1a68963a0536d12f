package CodingStudy;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 동전의 종류 n
 * 동전을 활용해서 가치의 합이 k가 되도록하는 모든 경우의수 
 * 1<=n<=100
 * 1<=k<=10000
 * 
 * k가 1원부터 시작한다고 생각하고 1원 만드는 경우의수 2원만드는 경우의수...k원 만드는 경우의수 따져보기
 */
public class 동전1 {
	static int n;
	static int k;
	static int[] coin;
	static int[] dp;
	static long cnt;
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		coin = new int[n];
		dp= new int[k+1];
		for(int i=0;i<n;i++)  coin[i] = sc.nextInt();
		dp[0] = 1;
		for(int i=0;i<n;i++){//각 동전에 대해서 
			for(int value=1;value<=k;value++){//1원부터 k원까지 만드는 모든 경우의 수 
				if(value-coin[i]>=0){//만들 수 있으면 
					dp[value]+=dp[value-coin[i]];
				}
			}	
		}
		System.out.println(Arrays.toString(dp));
		System.out.println(dp[k]);
	}
}
