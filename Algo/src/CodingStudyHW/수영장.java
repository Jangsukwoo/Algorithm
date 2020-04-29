package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * dfs
 */
public class 수영장{
    static int[] Month = new int[12];
    static int[] Fee = new int[4];
    static int Sum;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T;
    public static void main(String[] args) throws NumberFormatException, IOException {
        T= Integer.parseInt(br.readLine());
        for(int testcase=1;testcase<=T;testcase++) {
        	setData();
            dfs(0,0);
            System.out.println("#"+testcase+" "+Sum);
        }
    }
    private static void setData() throws IOException {
    	st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++) Fee[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<12;i++) Month[i] = Integer.parseInt(st.nextToken());
        Sum=Fee[3];
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