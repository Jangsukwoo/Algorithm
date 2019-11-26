package SWExpertAcademy;

import java.util.Scanner;
/*

 */
public class 지도칠하기{
    public static int[][] graph;
    public static int N;
    public static int[] dx = {-1,0,1,0};
    public static int[] dy = {0,1,0,-1};
    public static boolean[] visit;
    public static int[] color;
    public static int[] paint= {1,2,3,4};
    public static int ans;
 
    public static boolean check() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(graph[i][j]==0) continue;
                if(color[i]==color[j]) return false;
            }
        }//n^2스캔하면서 같은색이 있으면  false.
        // 없으면 true
        return true;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            N = sc.nextInt();
            color = new int[N];
            graph = new int[N][N];
            visit = new boolean[N];
            for(int i=0;i<N;i++) color[i] = sc.nextInt();
            for(int i=0;i<N;i++)
                for(int j=0;j<N;j++) graph[i][j] = sc.nextInt();
            ans=Integer.MAX_VALUE;
            for(int i=0;i<N;i++) {
                visit[i]=true;
                dfs(i, 0);
                visit[i]=false;
            }
            System.out.println("#"+testcase+" "+ans);
        }
    }
    private static void dfs(int cur, int cnt) {
        if(check()) {
            ans=Math.min(ans, cnt);
            return;
        }
        for(int i=0;i<N;i++) {
            if(graph[cur][i]==0||visit[i]==true) continue;
            boolean flag=false;
            if(color[cur]==color[i]) {
                color[i]=(color[cur]+1)%4;
                cnt++;
                flag=true;
            }
            visit[i]=true;
            dfs(i, cnt);
            if(flag) color[i]=color[cur];
            visit[i]=false;
        }
    }
}