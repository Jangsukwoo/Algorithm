package 알고리즘;

import java.util.Arrays;
import java.util.Scanner;
/*
 * LIS는 할때마다 까먹어서 시간이 오래걸린다.. 
 * 전형적인 LIS 문제인데 푸는데 10분정도 소요..
 * 메모이제이션은 길이로 한다.
 * 
 * 알고있는 숫자보다 큰 것과
 * 알고있는 길이보다 큰 것의 차이는 뭘까?
 */
public class 민균이의계략_LIS_최장증가부분수열 {
	static int N;
	static long[] dp1;
	static long[] dp2;
	static long[] cards;
	static long result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp1 = new long[N];
		dp2 = new long[N];
		cards = new long[N];
		Arrays.fill(dp1,1);
		Arrays.fill(dp2,1);
		for(int i=0;i<N;i++) cards[i] = sc.nextLong();
		for(int i=0;i<N;i++){
			long check=0;
			long check2=0;
			for(int j=0;j<i;j++){
				if(cards[j]<cards[i] && check<cards[j]) {
					//보려고 하는 숫자보다 크고, 알고있는 숫자보다 크면 
					check = cards[j];
					dp1[i]+=1;
				}
				if(cards[j]<cards[i] && check2<dp2[j]) {
					//보려고 하는 숫자보다 크고, 알고있는 길이보다 크면 
					check2 = dp2[j];
					dp2[i]=dp2[j]+1;
				}
			}
		}
		for(int i=0;i<N;i++) {
			if(result<dp2[i]) result = dp2[i];
		}
		System.out.println(Arrays.toString(dp1));//숫자 기준 메모이제이션 배열
		System.out.println(Arrays.toString(dp2));//길이 기준 메모이제이션 배열
		System.out.println(result);
	}
}
