package DP;

import java.util.Scanner;
//L I S
//최장증가부분수열 
//O(n^2) 풀이와 O(nlogn)풀이가 존재


public class 반도체설계 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            int[] data = new int[N];
            int[] l = new int[N];
            for(int i=0;i<N;i++) data[i] = sc.nextInt(); 
            int max =0;
            for(int i=0;i<N;i++) {
                l[i]=1;
                for(int j=0;j<=i;j++) {
                    if(data[j]<data[i] &&l[i]<l[j]+1) {
                        l[i]=l[j]+1;
                    }
                    if(max<l[i])max=l[i];
                }
            }
            System.out.println(max);
        
    }
}