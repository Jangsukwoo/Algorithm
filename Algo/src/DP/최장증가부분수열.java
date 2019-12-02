package DP;

import java.util.Scanner;
/*
 * N개로 주어진 수열에 대해서
 * a0부터 aN 까지 앞에서부터 읽어나갈 때
 * 가장 긴 증가하는 부분 수열을 구하기.
 * 
 * Ex) 1 6 2 3 7 5 면
 * 앞에서부터 읽었을 때 
 * 1 2 3 5가 길이가 4인 가장 긴 증가하는 부분 수열이다.
 * 
 *  보통 각 자리의 최대 길이값을 메모이제이션 하며 DP로 푼다. 
 *  즉 dp[i]의 값은
 *  i번째가 알고있는 i번쨰까지의 최대 길이
 */
public class 최장증가부분수열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max = 0;
        int N=0;
        int check=0;
        int[] series; //수열
        int[] DP; //메모이제이션
        int T = sc.nextInt();
        for(int test_case =1; test_case<=T; test_case++) {
            //입력
            N = sc.nextInt();
            series = new int[N];
            DP = new int[N];
            for(int i=0;i<N;i++) series[i]=sc.nextInt();
            //처리
            for(int i=1;i<N;i++) {
                for(int j=0;j<i;j++) {
                    if(series[i]>=series[j]){//작거나 같은 수면서  
                        if(DP[j]>=check) {//check된 수보다 크거나 같으면 
                            check =DP[j];//check값 업데이트 
                            DP[i]= DP[j]+1;//DP[i]값 하나 증가 
                        }
                    }
                }
                check =0;
            }
            for(int i=0;i<N;i++) if(DP[i]>max) max = DP[i];//가장 긴 것 
            max = max+1;
             
            System.out.println("#"+test_case+" "+max);//종료
            max =0;
        }
    }
}