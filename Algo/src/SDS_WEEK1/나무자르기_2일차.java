package SDS_WEEK1;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 나무의 수 N
 * 나무의 길이 M
 * 1<=N<=1000000
 * 1<=M<=2000000000
 * M미터의 나무를 집에 가져가기 위해 절단기의 최대 높이값 찾기.
 * 
 * -> 탐색 -> 이분탐색
 */
public class 나무자르기_2일차 {
	static int N; //나무의 수
	static int target; //타겟
	static long[] tree;
	static long cutterMaxHeight;
	static long maxCutHeight;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		target = sc.nextInt();
		tree = new long[N];
		for(int i=0;i<N;i++) tree[i] = sc.nextLong();
		Arrays.sort(tree);
		binarySearch();
		System.out.println(cutterMaxHeight);
	}
	private static void binarySearch() {
		long minHeight = 0;
		cutterMaxHeight = tree[N-1];
		while(minHeight<=cutterMaxHeight) {
			maxCutHeight = (minHeight+cutterMaxHeight)/2;//10, 
			long cuttingHeight=0;
			long sumHeight=0;
			for(int i=0;i<=N-1;i++) {
				cuttingHeight = tree[i]-maxCutHeight;
				if(cuttingHeight<0) cuttingHeight=0;
				sumHeight+=cuttingHeight;
			}
			if(sumHeight>=target){//너무많이 잘림
				minHeight = maxCutHeight+1;
			}
			else if(sumHeight<target) {
				cutterMaxHeight = maxCutHeight-1;
			}
		}
	}
}
