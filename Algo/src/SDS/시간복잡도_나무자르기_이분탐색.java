package SDS;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 나무의 수 N, 상근이가 집으로 가져가려고 하는 나무의 길이 M
 * 1<=N<=1000000
 * 1<=M<=2000000000
 * 
 * 이분탐색의 전제조건 -> 정렬 
 * int 최대값 : 21억
 * 
 * M미터의 나무를 가져가기 위해 설정해야하는 최대 절단점의 높이 구하기
 * 
 * 이분탐색으로 어떻게 접근할 것인지 생각해보기
 */
public class 시간복잡도_나무자르기_이분탐색 {
	static int N,M;
	static long[] tree;
	static long left,right;
	static long cutterMaxHeight;
	static long cutterMinHeight;
	static long cutterMiddleHeight;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt(); //맞춰야하는 값
		tree = new long[N];
		for(int i=0;i<N;i++) tree[i] = sc.nextLong();
		Arrays.sort(tree);
		cutterMaxHeight = tree[N-1];
		binarySearch();

		
	}
	private static void binarySearch() {
		while(cutterMaxHeight>=cutterMinHeight) {

			cutterMiddleHeight = (cutterMinHeight+cutterMaxHeight)/2; //중간값.절단기의 높이를  최대 높이와 최소 높이의 중간값으로 설정
			
			System.out.println("최대높이:"+cutterMaxHeight);
			System.out.println("최소높이:"+cutterMinHeight);
			System.out.println("중간값:"+cutterMiddleHeight);
			System.out.println();
			
			long remainTreeHeight = 0;
			long sumTreeHeight = 0;
			for(int i=0;i<N;i++){//커팅
				remainTreeHeight = tree[i]-cutterMiddleHeight;
				if(remainTreeHeight<0) remainTreeHeight = 0;//음수는 존재하지 X
				sumTreeHeight+=remainTreeHeight;//잘린 나무들의 합
			}
			if(sumTreeHeight>=M){//너무 많이 잘렸으면 
				cutterMinHeight = cutterMiddleHeight+1;
			}
			else if(sumTreeHeight<M){//덜잘렸으면
				cutterMaxHeight = cutterMiddleHeight-1;
			}

		}
	}
}
