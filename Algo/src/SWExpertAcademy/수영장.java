package SWExpertAcademy;
import java.util.Scanner;

public class 수영장{
    static int[] Month = new int[12];
    static int[] Fee = new int[4];
    static int Sum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T= sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            for(int i=0;i<4;i++) Fee[i] = sc.nextInt();
            for(int i=0;i<12;i++) Month[i] = sc.nextInt();
            Sum=Fee[3];
            dfs(0,0);
            System.out.println("#"+testcase+" "+Sum);
        }
    }
    private static void dfs(int month, int fee) {
        if(month>11) {
            Sum = Math.min(Sum,fee);
            return;
        }
        dfs(month+1,fee+(Month[month]*Fee[0]));//1일권
        dfs(month+1, fee+Fee[1]);
        dfs(month+3, fee+Fee[2]);
        dfs(month+12, fee+Fee[3]);
    }
}