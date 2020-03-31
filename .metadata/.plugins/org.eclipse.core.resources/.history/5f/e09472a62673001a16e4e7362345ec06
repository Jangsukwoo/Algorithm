package CodingTest;

import java.util.Scanner;

public class goToOffice {
	static int[] seat;
	static int N;
	static int distance;
	static int oneSize;
	static int leftMax,rightMax,betweenMax;
	static int max;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		seat = new int[N];
		for(int i=0;i<N;i++) {
			seat[i] = sc.nextInt();
			if(seat[i]==1) oneSize++;
		}

		for(int i=0;i<N;i++){//leftMax 탐색 시작
			if(seat[i]==1){
				leftMax=i;
				break;
			}		
		}
		for(int i=(N-1);i>=0;i--){//rightMax 탐색 시작
			if(seat[i]==1){
				rightMax = (N-1)-i;
				break;
			}		
		}	

		if(oneSize>=2){//1이 두개 이상이면 betweenMax 구하기
			int start=0;
			betweenMax =0;
			for(int i=0;i<N;i++) {
				if(seat[i]==1){//1발견
					if(start==0){
						start = i;
					}
					else if(i>start){//start보다 클때 1발견
						betweenMax =Math.max(betweenMax,((i-start)/2));
						start = i;
					}		
				}
			}
		}
		max = Math.max(leftMax,betweenMax);
		max = Math.max(max,rightMax);
		System.out.println(max);
		
	}
}
