package SWExpertAcademy;
import java.util.Scanner;
public class 혁진이의프로그래검증{
    public static int[] dx = {-1,0,1,0};
    public static int[] dy = {0,1,0,-1};//상우하좌
    public static char[][] Map;
    public static int R;
    public static int C;
    public static int Memory;
    public static boolean[][][][] visit; //R,C,Memory,Direct
    public static boolean find; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            R = sc.nextInt();
            C = sc.nextInt();
            Map = new char[R][C];
            visit= new boolean[R][C][16][4];
            sc.nextLine();
            for(int row=0;row<R;row++) {
                String tmp = sc.nextLine();
                for(int col=0;col<C;col++) {
                    Map[row] = tmp.toCharArray();
                }
            }
            dfs(0,0,0,1);       
            if(!find) System.out.println("#"+testcase+" NO");
            else System.out.println("#"+testcase+" YES");
            find = false;
            Memory =0;
        }
    }
 
    private static void dfs(int curi, int curj,int m,int f) {
        if(find) return;
        if(curi==-1) curi = R-1;
        if(curi==R) curi =0;
        if(curj==-1) curj = C-1;
        if(curj==C) curj =0;
        if(Map[curi][curj]=='@') {
            find=true;
            return;
        }
        else if(visit[curi][curj][m][f]==false) {
            visit[curi][curj][m][f] = true;
            switch (Map[curi][curj]) {
            case '<':
                dfs((curi+dx[3]),(curj+dy[3]),m,3);
                break;
            case '>':
                dfs((curi+dx[1]),(curj+dy[1]),m,1);
                break;
            case '^':
                dfs((curi+dx[0]),(curj+dy[0]),m,0);
                break;
            case 'v':
                dfs((curi+dx[2]),(curj+dy[2]),m,2);
                break;
            case '_':
                if(m==0) {
                    dfs((curi+dx[1]),(curj+dy[1]),m,1);
                }
                else {
                    dfs((curi+dx[3]),(curj+dy[3]),m,3);
                }
                break;
            case '|':
                if(m==0) {
                    dfs((curi+dx[2]),(curj+dy[2]),m,2);
                }
                else {
                    dfs((curi+dx[0]),(curj+dy[0]),m,0);
                }
                break;
            case '?':
                for(int i=0;i<4;i++) {   
                    int ii = curi+dx[i];
                    int jj = curj+dy[i];
                    if((ii>=0&&ii<R) && (jj>=0&&jj<C)) {
                        if(!find) {
                            if(visit[ii][jj][m][f]==false) dfs((curi+dx[i]),(curj+dy[i]),m,i);//다보내봄
                        }
                        else return;
                    }
                }
                break;
            case '.':
                dfs((curi+dx[f]),(curj+dy[f]),m,f);
                break;
            case '+':
                if(m==15) m =0;
                else m++;
                dfs((curi+dx[f]),(curj+dy[f]),m,f);
                break;
            case '-':
                if(m==0) m =15;
                else m--;
                dfs((curi+dx[f]),(curj+dy[f]),m,f);
                break;
                default:
                m = (int)(Map[curi][curj]-48);
                dfs((curi+dx[f]),(curj+dy[f]),m,f);
                break;
 
            }
        }
        else return;
    }
}