package SWExpertAcademy;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class HW{
    int H;
    int W;
    HW(int h, int w){
        H=h;
        W=w;
    }
}
public class 벽돌깨기{
    public static int N,W,H;
    public static int[][] originalMap;
    public static int[][] copyMap;
    public static int[] Selected;
    public static int[] dw = {0,1,0,-1};
    public static int[] dh = {-1,0,1,0};//상우하좌
    public static int remainBlockMin=Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();
            Selected = new int[N];
            originalMap = new int[H][W];
            copyMap = new int[H][W];
            remainBlockMin=Integer.MAX_VALUE;
            for(int i=0;i<H;i++)
                for(int j=0;j<W;j++) originalMap[i][j] = sc.nextInt();
            duplicatePermutation(0);
            System.out.println("#"+testcase+" "+remainBlockMin);
        }
    }
     
    private static void duplicatePermutation(int depth) {//중복순열
        if(depth==N) {//모든 경우의수.        
            for(int i=0;i<H;i++)
                for(int j=0;j<W;j++) copyMap[i][j] = originalMap[i][j];  
            Play();
            return;
        }
        for(int i=0;i<W;i++) {
            Selected[depth] = i;
            duplicatePermutation(depth+1);
        }
    }
 
    private static void Play() {
        int remain=0;
        for(int shot=0; shot<N;shot++) {
            Shooting(Selected[shot]);//슛    
        }
        for(int i=0;i<H;i++)
            for(int j=0;j<W;j++) if(copyMap[i][j]!=0) remain++;
        remainBlockMin = Math.min(remain,remainBlockMin);
    }
     
    private static void Shooting(int pos) {
        Queue<HW> q = new LinkedList<>();
        for(int row=0;row<H;row++) {
            if(copyMap[row][pos]!=0){
                q.add(new HW(row,pos));
                while(!q.isEmpty()) {
                    int size = q.size();
                    for(int i=0;i<size;i++) {
                        HW tmp = q.poll();
                        if(copyMap[tmp.H][tmp.W]==1) {//하나면
                            copyMap[tmp.H][tmp.W]=0;
                        }
                        else if(copyMap[tmp.H][tmp.W]>1){ //폭발범위가 있으면
                            int range = copyMap[tmp.H][tmp.W];
                            copyMap[tmp.H][tmp.W]=0;
                            for(int dir=0;dir<4;dir++) {//방향을 볼건데
                                for(int d=1;d<range;d++){
                                    int nh = tmp.H+(d*dh[dir]);
                                    int nw = tmp.W+(d*dw[dir]);
                                    if(check(nh,nw)){
                                        q.add(new HW(nh,nw));
                                    }
                                }   
                            }                       
                        }
                    }
                }
                break;
            }
        }
        gravity();
    }
     
    private static void gravity() {
        for(int col=0;col<W;col++) {//다음열
            int blank=0;
            for(int row=(H-1);row>=0;row--){//
                if(copyMap[row][col]==0) {
                    blank=row;
                    for(int r = (blank-1); r>=0; r--) {
                        if(copyMap[r][col]!=0) {
                            int tmp = copyMap[r][col];
                            copyMap[r][col] = copyMap[blank][col];
                            copyMap[blank][col] = tmp;
                            break;
                        }
                    }
                }
                 
            }
        }
    }
     
    private static boolean check(int nrow, int ncol) {
        if((nrow>=0&&nrow<H) && (ncol<W&&ncol>=0)) return true;
        return false;
    }
}