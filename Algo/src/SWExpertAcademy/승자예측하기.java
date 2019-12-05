package SWExpertAcademy;


import java.util.Scanner;
public class 승자예측하기{
    public static long N;
    public static long num;
    public static boolean swit = true;
    public static int cnt;//false = bob, true = alice
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            N = sc.nextLong();
            swit=true;
            num=1;
            while(N>0) {
                N-=num;
                if(swit)num*=4;
                swit=!swit;
            }
            if(swit) System.out.println("#"+testcase+" "+"Alice");
            else System.out.println("#"+testcase+" "+"Bob");
        }
    }
}