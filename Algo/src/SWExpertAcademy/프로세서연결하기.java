package SWExpertAcademy;
import java.util.ArrayList;
import java.util.Scanner;
class Core{
    int row;
    int col;
 
    Core(int r, int c){
        row = r;
        col = c;
    }
}
public class 프로세서연결하기 {
    static int N;
    static int[][] Maxinos;
    static int[] processor;
    static int cableLength;
    static int MaxCore;
    static int corelistSize;
    static ArrayList<Core> corelist = new ArrayList<>();
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};//상우하좌 
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
             
            MaxCore = 0; 
            N = sc.nextInt();
            Maxinos = new int[N+2][N+2];//가장자리는 전륜
            cableLength = (Integer.MAX_VALUE/2);//전선길이의 합
            corelist.clear();
            for(int row=0;row<=N+1;row++) {
                for(int col=0;col<=N+1;col++) {          
                    if(col==0 || col==N+1) {
                        Maxinos[row][col] =3;
                    }
                    else if(row==0 || row==N+1){
                        Maxinos[row][col]=3;
                    }
                    else{
                        Maxinos[row][col] = sc.nextInt();
                        if(Maxinos[row][col]==1){//프로세서고
                            if(row!=1 && col!=1 && row!=N && col!=N) { //벽에 붙어있지않은경우만 넣는다.
                                corelist.add(new Core(row,col));
                                //System.out.println("row:"+row+" "+"col"+col);
                            }
                        }
                    }
                }
            }
             
            corelistSize = corelist.size();
            processor = new int[corelistSize];
             
            //System.out.println("코어리스트사이즈"+corelist.size());
            //입력끝
            //전선길이의 합이 최소, 최대한 많은 코어 
             
             
            inspectionDFS(0);//코어넘버
             
             
             
            System.out.println("#"+testcase+" "+cableLength);
            //출력
        }
    }
    private static void inspectionDFS(int coreNumber) {
        if(coreNumber==corelistSize){//끝까지 다 조사 했을 때 
            int maxProcessor=0;
            int minCable = 0;
            for(int i=0;i<corelistSize;i++) {
                if(processor[i]==1) maxProcessor++;
            }
            //MaxCore
            for(int row=1;row<=N;row++) {
                for(int col=1;col<=N;col++) {
                    if(Maxinos[row][col]==2) minCable++;
                }
            }
            if(maxProcessor > MaxCore) {
                MaxCore = maxProcessor;
                cableLength = minCable;
            }else if(maxProcessor == MaxCore) {
                cableLength = Math.min(cableLength, minCable);
            }
 
            return; 
        }
         
        //백트랙킹
        //남아있는 프로세스 해봤자 의미 없을 때 
        ////
        int currentProcessor=0;
        int remainProcessor = corelistSize-coreNumber;
        for(int i=0;i<corelistSize;i++) {
            if(processor[i]==1) currentProcessor++;
        }
        if(MaxCore>(currentProcessor+remainProcessor)) {
            return;
        }
        ////////
        for(int dir=0;dir<4;dir++){//4방향
 
            int cr = corelist.get(coreNumber).row;
            int cc = corelist.get(coreNumber).col;
            int nr = cr;
            int nc = cc;
            boolean escapeCheck = false;
 
 
             
            while(true){//가본다.
 
                nr += dr[dir];      //상우하좌
                nc += dc[dir];
 
 
                if(rangeCheck(nr,nc)) {//영역은 일단 만족하는데 
 
                    if(Maxinos[nr][nc]==1 || Maxinos[nr][nc]==2) {//1다른 코어가 있거나 
                        inspectionDFS(coreNumber+1);
                        escapeCheck =true;
                    }
 
 
                    else if(Maxinos[nr][nc]==3){//3이면 연결 만족
                        processor[coreNumber]=1;//연결됐음
 
                        switch (dir) {
                        case 0:
 
                            for(int row=(cr-1);row>0;row--) Maxinos[row][cc] = 2;
                            inspectionDFS(coreNumber+1);
                            processor[coreNumber]=0;//연결풀기
                            for(int row=1;row<cr;row++) Maxinos[row][cc] = 0;
                            escapeCheck =true;
                            break;
 
                        case 1:
                            for(int col=(cc+1);col<=N;col++) Maxinos[cr][col] = 2;
                            inspectionDFS(coreNumber+1);
                            processor[coreNumber]=0;
                            for(int col=N;col>cc;col--) Maxinos[cr][col] = 0;
                            escapeCheck =true;
                            break;
                        case 2:
                            for(int row=(cr+1);row<=N;row++) Maxinos[row][cc] = 2;
                            inspectionDFS(coreNumber+1);
                            processor[coreNumber]=0;
                            for(int row=N;row>cr;row--) Maxinos[row][cc] = 0;
                            escapeCheck =true;
                            break;
                        case 3:
                            for(int col=cc-1;col>0;col--) Maxinos[cr][col] = 2;
                            inspectionDFS(coreNumber+1);
                            processor[coreNumber]=0;
                            for(int col=1;col<cc;col++) Maxinos[cr][col] = 0;
                            escapeCheck =true;
                            break;
                        }
                    }
                    if(escapeCheck)break; 
                }else break;
            }
        }
    }
    private static boolean rangeCheck(int cr, int cc) {
        if((cr>=0 && cr<=N+1) && (cc>=0 && cc<=N+1)) return true;
        return false;
    }
}