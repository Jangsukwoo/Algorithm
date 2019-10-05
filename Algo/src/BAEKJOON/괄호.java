package BAEKJOON;

import java.util.*;
public class 괄호 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        long[] d = new long[2501];
        long m = 1000000007L;
        d[0] = 1;
        for (int i=1; i<=2500; i++) {
            d[i] = 0;
            for (int j=0; j<i; j++) {
                d[i] += d[j] * d[i-1-j];
                d[i] %= m;
            }
            d[i] %= m;
        }
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            if (n%2 == 0) {
                System.out.println(d[n/2]);
            } else {
                System.out.println(0);
            }
        }
    }
}