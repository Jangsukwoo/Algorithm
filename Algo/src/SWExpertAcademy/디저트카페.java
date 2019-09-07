package SWExpertAcademy;
import java.util.Scanner;

public class 디저트카페{
    public static int[] dx = {1,1,-1,-1};
    public static int[] dy = {1,-1,-1,1};
    public static int[][] Map;
    public static boolean[] Dessert = new boolean[101];
    public static int N,Result,starti,startj;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            N = sc.nextInt();
            Map = new int[N][N];
            Result=-1;
            for(int i=0;i<N;i++)
                for(int j=0;j<N;j++) Map[i][j] = sc.nextInt();
             
            for(int i=0;i<N;i++)
                for(int j=0;j<N;j++) {
                    starti = i;
                    startj = j;
                    dfs(i,j,0,0,1); //어디든지 갈 수만 있으면 go
                }
 
            System.out.println("#"+testcase+" "+Result);
        }
    }
    private static void dfs(int ci, int cj, int depth, int dir, int turn) {
        if(ci==starti && cj==startj && depth>0 && turn==4){
            Result = Math.max(Result,depth);
            return;
        }   
        // 꺾기
        int nx=ci+dx[(dir+1)%4];
        int ny=cj+dy[(dir+1)%4];
        if(check(nx, ny)&&!Dessert[Map[nx][ny]]) {
            Dessert[Map[nx][ny]]=true;
            dfs(nx, ny, depth+1, (dir+1)%4,turn+1);
            Dessert[Map[nx][ny]]=false;
        }
        // 그대로
        nx=ci+dx[dir];
        ny=cj+dy[dir];
        if(check(nx, ny)&&!Dessert[Map[nx][ny]]) {
            Dessert[Map[nx][ny]]=true;
            dfs(nx, ny, depth+1, dir,turn);
            Dessert[Map[nx][ny]]=false;
        }
    }
    private static boolean check(int ni, int nj) {
        if((ni>=0&&ni<N)&&(nj>=0&&nj<N))return true;
        return false;
    }
}
