package SDS_WEEK1;

/*
 * N개의 줄에 0~9
 * 내려갈때 바로 아래의 수로 넘어가거나 
 * 아래의 수에 붙어있는 수로 넘어갈 수 있음
 * 1<=N<=100000
 * 
 * 얻을 수 있는 최대점수, 최소점수 츌력
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class 내려가기_2일차 {
 
    static int n;
    static int maxDP[][] = new int[100001][3];
    static int minDP[][] = new int[100001][3];
    static int map[][] = new int [100001][3];
    
    public static void main(String args[]) throws NumberFormatException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        
        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }
        
        maxDP[1][0]=map[1][0];
        maxDP[1][1]=map[1][1];
        maxDP[1][2]=map[1][2];
        
        minDP[1][0]=map[1][0];
        minDP[1][1]=map[1][1];
        minDP[1][2]=map[1][2];
        
        
        for(int i = 2 ; i <= n ; i++){
            maxDP[i][0]=map[i][0]+Math.max(maxDP[i-1][0], maxDP[i-1][1]);
            maxDP[i][1]=map[i][1]+Math.max(maxDP[i-1][0], Math.max(maxDP[i-1][1], maxDP[i-1][2]));
            maxDP[i][2]=map[i][2]+Math.max(maxDP[i-1][1], maxDP[i-1][2]);
            
            minDP[i][0]=map[i][0]+Math.min(minDP[i-1][0], minDP[i-1][1]);
            minDP[i][1]=map[i][1]+Math.min(minDP[i-1][0], Math.min(minDP[i-1][1], minDP[i-1][2]));
            minDP[i][2]=map[i][2]+Math.min(minDP[i-1][1], minDP[i-1][2]);
        }
        int max = Math.max(maxDP[n][0], Math.max(maxDP[n][1], maxDP[n][2]));
        int min = Math.min(minDP[n][0], Math.min(minDP[n][1], minDP[n][2]));
        System.out.println(max + " " + min);
    }
}
