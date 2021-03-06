package SWExpertAcademy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class RowCol{
    int row;
    int col;
    int shape;
    RowCol(int r,int c,int s){
        row=r;
        col=c;
        shape=s;
    }
}
public class 탈주범검거{
    static int[][] Map;
    static boolean[][] visit;
    static int N,M,R,C,L,Time,Ans;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            N = sc.nextInt();
            M = sc.nextInt();
            R = sc.nextInt();
            C = sc.nextInt();
            L = sc.nextInt();
            Ans=1;
            Time=1;
            Map = new int[N][M];
            visit = new boolean[N][M];
            visit[R][C] = true;
            for(int row=0;row<N;row++)
                for(int col=0;col<M;col++) Map[row][col] = sc.nextInt();
            bfs();  
            System.out.println("#"+testcase+" "+Ans);
        }
    }
 
    private static void bfs() {
        Queue<RowCol> q = new LinkedList<>();
        q.add(new RowCol(R,C,Map[R][C]));   
        while(!q.isEmpty()) {
            if(Time==L) break;
            Time++;
            int size = q.size();
            for(int i=0;i<size;i++) {
                RowCol RC = q.poll();
                for(int dir=0;dir<4;dir++) {
                    int nr = RC.row+dr[dir];
                    int nc = RC.col+dc[dir];
                    if(check(nr,nc)) {
                        switch (RC.shape){//현재모양
                        case 1:
                            switch (dir) {
                            case 0:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==2 ||Map[nr][nc]==5 ||Map[nr][nc]==6)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            case 1:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==3 ||Map[nr][nc]==6 ||Map[nr][nc]==7)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            case 2:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==2 ||Map[nr][nc]==4 ||Map[nr][nc]==7)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            case 3:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==3 ||Map[nr][nc]==4 ||Map[nr][nc]==5)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            }
                            break;
                        case 2:
                            switch (dir) {
                            case 0:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==2 ||Map[nr][nc]==5 ||Map[nr][nc]==6)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            case 2:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==2 ||Map[nr][nc]==4 ||Map[nr][nc]==7)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            }
                            break;
                        case 3:
                            switch (dir) {
                            case 1:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==3 ||Map[nr][nc]==6 ||Map[nr][nc]==7)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            case 3:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==3 ||Map[nr][nc]==4 ||Map[nr][nc]==5)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            }
                            break;
                        case 4:
                            switch (dir) {
                            case 0:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==2 ||Map[nr][nc]==5 ||Map[nr][nc]==6)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            case 1:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==3 ||Map[nr][nc]==6 ||Map[nr][nc]==7)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            }
                            break;
                        case 5:
                            switch (dir) {
                            case 1:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==3 ||Map[nr][nc]==6 ||Map[nr][nc]==7)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            case 2:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==2 ||Map[nr][nc]==4 ||Map[nr][nc]==7)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            }
                            break;
                        case 6:
                            switch (dir) {
                            case 3:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==3 ||Map[nr][nc]==4 ||Map[nr][nc]==5)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            case 2:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==2 ||Map[nr][nc]==4 ||Map[nr][nc]==7)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            }
                            break;
                        case 7:
                            switch (dir) {
                            case 0:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==2||Map[nr][nc]==5||Map[nr][nc]==6)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            case 3:
                                if(!visit[nr][nc] && (Map[nr][nc]==1||Map[nr][nc]==3 ||Map[nr][nc]==5 ||Map[nr][nc]==4)){
                                    visit[nr][nc] = true;
                                    q.add(new RowCol(nr, nc, Map[nr][nc]));
                                    Ans++;
                                }
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
 
    private static boolean check(int nr, int nc) {
        if((nr>=0&&nr<N) && (nc>=0&&nc<M)) return true;
        return false;
    }
}