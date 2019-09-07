package SWExpertAcademy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
1
80 7
2 2 2 2 2 2 0 2 2 0 4 0 2 3 3 2 3 3 0 3 3 3 4 3 3 2 1 1 1 0 4 4 4 1 0 2 2 2 1 1 4 1 2 3 4 4 3 0 1 1 0 3 4 0 1 2 2 2 1 1 3 4 4 4 4 4 4 3 2 1 4 4 4 4 3 3 3 0 3 3 
4 4 1 1 2 1 2 3 3 3 4 4 4 4 4 1 1 1 1 1 1 1 1 0 3 3 2 0 4 0 1 3 3 3 2 2 1 0 3 2 3 4 1 0 1 2 2 3 2 0 4 0 3 4 1 1 0 0 3 2 0 0 4 3 3 4 0 4 4 4 4 0 3 0 1 1 4 4 3 0 
4 3 1 170
10 1 3 240
10 5 3 360
10 9 3 350
9 6 2 10
5 1 4 350
1 8 2 450
 */
class BC implements Comparable<BC>{
    int X,Y,C,P,ID;
    BC(int x,int y,int c,int p,int id){
        X=x;
        Y=y;
        C=c;
        P=p;
        ID = id;
    }
    @Override
    public int compareTo(BC o) {
        return -Integer.compare(this.P,o.P);
    }
     
}
public class 무선충전 {
    public static int[] dx = {0,0,1,0,-1};
    public static int[] dy = {0,-1,0,1,0};//상 우 하 좌 
    public static BC[] BC;
    public static int[][] Map = new int[11][11]; 
    public static char[][] c = new char[11][11];
    public static int[] MoveA,MoveB;
    public static int M,A,AX,AY,BX,BY,Sum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            M = sc.nextInt();
            A = sc.nextInt();
            BC = new BC[A];
            MoveA = new int[M];
            MoveB = new int[M];
            AX=1;
            AY=1;
            BX=10;
            BY=10;
            Sum=0;
            for(int i=0;i<M;i++) MoveA[i] = sc.nextInt();
            for(int i=0;i<M;i++) MoveB[i] = sc.nextInt();
            for(int i=0;i<A;i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int c = sc.nextInt();
                int p = sc.nextInt();
                BC[i] = new BC(x,y,c,p,i);
            }
            Arrays.sort(BC);
            for(int Time=0;Time<=M;Time++) {
                 
                ArrayList<BC> BCA = new ArrayList<>();
                ArrayList<BC> BCB = new ArrayList<>();
                 
                if(Time>0) {
                    AX += dx[MoveA[Time-1]];
                    AY += dy[MoveA[Time-1]];
                    BX += dx[MoveB[Time-1]];
                    BY += dy[MoveB[Time-1]];
                }
                for(int check=0;check<A;check++) {
                    if(BC[check].C>=(Math.abs(AX-BC[check].X)+Math.abs(AY-BC[check].Y))) BCA.add(BC[check]);
                    if(BC[check].C>=(Math.abs(BX-BC[check].X)+Math.abs(BY-BC[check].Y))) BCB.add(BC[check]);
                }
                if(BCA.size()==0 && BCB.size()==0) continue;
                else if(BCA.size()>0 && BCB.size()==0) Sum+=BCA.get(0).P;
                else if(BCA.size()==0 && BCB.size()>0) Sum+=BCB.get(0).P;
                else if(BCA.size()>0 && BCB.size()>0) {
                    if((BCA.get(0).P==BCB.get(0).P) && BCA.get(0).ID==BCB.get(0).ID){
                        if(BCA.size()>1 && BCB.size()==1) Sum+=BCA.get(1).P+BCB.get(0).P;
                        else if(BCA.size()==1 && BCB.size()>1) Sum+=BCA.get(0).P+BCB.get(1).P;
                        else if(BCA.size()>1 && BCB.size()>1) Sum+=Math.max((BCA.get(0).P+BCB.get(1).P),(BCA.get(1).P+BCB.get(0).P));
                        else if(BCA.size()==1 && BCB.size()==1) Sum+=BCA.get(0).P; 
                    }
                    else Sum+=BCA.get(0).P+BCB.get(0).P;
                }
            }
            System.out.println("#"+testcase+" "+Sum);
        }
    }
}