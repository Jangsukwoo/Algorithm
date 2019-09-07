package SWExpertAcademy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
/*
스캇 -> A,W: O / F,X: X  (W는 K시간동안 가능)
불 -> 벽에 X, 스캇이 도망치기 가능
악당 -> A,F: O / W,X : X 스캇과 마주친경우 아무일 없이 지나감
스캇이 최단거리로 탈출한 수.
악당이 먼저 도착하거나 동시에 도착하면 결과는 -1
 */
class Second implements Comparable<Second>{
    int ScottX;
    int ScottY;
    int ScottAbility;
    int VillainX;
    int VillainY;
    int FireX;
    int FireY;
    int ID; //1:불 2:스캇 3:악당
    Second(int sx, int sy, int k, int vx, int vy, int fx, int fy,int id){
        ScottX=sx;
        ScottY=sy;
        ScottAbility=k;
        VillainX=vx;
        VillainY=vy;
        FireX=fx;
        FireY=fy;
        ID = id;
    }
    @Override
    public int compareTo(Second o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.ID,o.ID);
    }
}
public class 탈출 {
    public static int[] dx = {-1,0,1,0};
    public static int[] dy = {0,1,0,-1};
    public static int N,M,K;
    public static char[][] Map;
    public static int Time;
    public static boolean[][] ScottVisit;
    public static boolean[][] VillainVisit;
    public static boolean ScottComplete;
    public static boolean VillainComplete;
    public static Queue<Second> q;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();   
         
        for(int testcase=1;testcase<=T;testcase++) {
            q = new LinkedList<>();
            N=sc.nextInt();
            M=sc.nextInt();
            K=sc.nextInt();
            Map = new char[N][M];
            ScottVisit = new boolean[N][M];
            VillainVisit = new boolean[N][M];
            for(int i=0;i<N;i++) {
                String tmp = sc.next();
                Map[i] = tmp.toCharArray();
            }
             
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    switch (Map[i][j]) {
                    case 'F':
                        q.add(new Second(0,0,0,0,0,i,j,1));//Fire
                        break;
                    case 'S':
                        q.add(new Second(i,j,K,0,0,0,0,2));//Scott
                        break;
                    case 'V':
                        q.add(new Second(0,0,0,i,j,0,0,3));//Villain
                        break;
                    }
                }
            }
            Collections.sort((List<Second>) q);   
            while(!q.isEmpty()) {
                int size = q.size();
                for(int i=0;i<size;i++) {
                    Second check = q.poll();
                    switch (check.ID) {
                    case 1://Fire
                        Burn(check.FireX,check.FireY); //먼저 불지름
                        break;
                    case 2: //Scott
                        MoveScott(check.ScottX,check.ScottY,check.ScottAbility);
                        break;
                    case 3://Villain
                        MoveVillain(check.VillainX,check.VillainY);
                        break;
                    }
                }
                if(ScottComplete && VillainComplete==false) break;
                else if(ScottComplete && VillainComplete) {
                    Time=-1;
                    break;
                }
                else if(ScottComplete==false && VillainComplete) {
                    Time=-1;
                    break;
                }
                Collections.sort((List<Second>) q);   
                Time++;
            }
            System.out.println("#"+testcase+" "+Time);
            Time=0;
            ScottComplete = false;
            VillainComplete = false;
        }
    }
    private static void Burn(int curX, int curY) { //불지름
        for(int i=0;i<4;i++) {
            int nx = curX+dx[i];
            int ny = curY+dy[i];
            if((nx>=0&&nx<N)&&(ny>=0&&ny<M)) {
                if(Map[nx][ny]=='A') {
                    Map[nx][ny]='F';
                    q.add(new Second(0,0,0,0,0,nx,ny,1)); //Fire
                }
            }
        }
    }
    private static void MoveScott(int curX,int curY, int Ability) {
        if(Map[curX][curY]=='E') {
            ScottComplete = true;
            return;
        }
        ScottVisit[curX][curY] = true;
        for(int i=0;i<4;i++) {
            int nx = curX+dx[i];
            int ny = curY+dy[i];
            if((nx>=0&&nx<N)&&(ny>=0&&ny<M)) {
                if((Map[nx][ny]=='A' || Map[nx][ny] =='E') && ScottVisit[nx][ny]==false) {
                    ScottVisit[nx][ny] = true;
                    q.add(new Second(nx,ny,K,0,0,0,0,2));//Scott
                }
                if(Map[nx][ny]=='W' && Ability>0) {//벽이고 능력이 있으면
                    ScottVisit[nx][ny] = true;
                    q.add(new Second(nx,ny,(Ability-1),0,0,0,0,2));//Scott
                }
            }
        }
    }
    private static void MoveVillain(int curX, int curY) {
        if(Map[curX][curY]=='E') {
            VillainComplete = true;
            return;
        }
        VillainVisit[curX][curY] = true;
        for(int i=0;i<4;i++) {
            int nx = curX+dx[i];
            int ny = curY+dy[i];
            if((nx>=0&&nx<N)&&(ny>=0&&ny<M)) {
                if((Map[nx][ny]=='A' || Map[nx][ny]=='F'|| Map[nx][ny]=='E') && VillainVisit[nx][ny]==false) {
                    VillainVisit[nx][ny] = true;
                    q.add(new Second(0,0,0,nx,ny,0,0,3));//Villain
                }
            }
        }
    }
}