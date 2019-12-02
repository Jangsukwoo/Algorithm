package CodingStudy;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 최장증가부분수열
 * dp 배열에 메모이제이션하면서
 * 가장 앞쪽 자리부터 구해나간다.  
 * 구하면서 각 자리의 최대 길이를 메모
 */
public class 상자넣기 {
	static int N;
	static int[] dp;
	static int[] box; //수열이라고 생각
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		box = new int[N];
		dp = new int[N];
		Arrays.fill(dp,1); //1로 채워주는 이유는
		//기준 박스보다더 큰 값을 못찾았어도 
		//그 기준 값 자체 상자가 1개 있으니까 1로 채워야한다.
		for(int i=0;i<N;i++)box[i]=sc.nextInt();
		for(int i=1;i<N;i++){//기준이 되는 박스 인덱스
			int check=0;//기준 값보다 작으면서 체크 시점의 최대값
			for(int j=0;j<i;j++){//맨 앞 박스부터 기준값 직전까지 스캔해보다가
				if(box[j]<box[i]){//기준값보다 작은 값이 나타났을 때
					if(dp[j]>check){//알고있는 길이 값보다 큰 길이라면 
						check = dp[j];//알고 있는 길이 값 업데이트
						dp[i]=dp[j]+1; //기준 값에 길이 값 업데이트
					}
				}
			}
		}
		int max=0;
		for(int i=0;i<N;i++) { //가장 긴 길이 출력
			if(dp[i]>max) max = dp[i];
		}
		System.out.println(max);
	}
}
