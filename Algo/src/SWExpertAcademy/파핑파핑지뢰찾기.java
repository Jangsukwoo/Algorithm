package SWExpertAcademy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class coordination{
    int x;
    int y;
    coordination(int i,int j){
        x=i;
        y=j;
    }
}
public class 파핑파핑지뢰찾기{
    public static void main(String[] args) {
        int[] dx = {-1,-1,0,1,1,1,0,-1};
        int[] dy = {0,1,1,1,0,-1,-1,-1};//12시방향부터.시계방향 8칸조사
        int click=0;
        boolean find=false;
        Queue<coordination> q = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            int N = sc.nextInt();
            char[][] tempMap = new char[N][N];
            int[][] Map = new int[N][N];
            String tmp;
            for(int i=0;i<N;i++) {
                tmp = sc.next();
                tempMap[i] = tmp.toCharArray();
            }
            for(int i=0;i<N;i++)
                for(int j=0;j<N;j++) {
                    if(tempMap[i][j]=='*') {
                        Map[i][j] =-1;
                    }
                }
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(Map[i][j]==0) {
                        q.add(new coordination(i, j));
                        while(!q.isEmpty()) {
                            coordination t = q.poll();
                            int a = t.x;
                            int b = t.y;
                            for(int k=0;k<8;k++) {
                                int checkx = a+dx[k];
                                int checky = b+dy[k];
                                if((checkx>=0&&checkx<N)&&(checky>=0&&checky<N)) {
                                    if(Map[checkx][checky]==-1) { //지뢰면
                                        find = true;
                                        break;
                                    }
                                }
                            }
                            if(!find) {
                                if(Map[a][b]==0) {
                                    Map[a][b]=1;
                                    click++;
                                }
                                 
                                for(int k=0;k<8;k++) {
                                    int checkx = a+dx[k];
                                    int checky = b+dy[k];
                                    if((checkx>=0&&checkx<N)&&(checky>=0&&checky<N)) {
                                        if(Map[checkx][checky]==0) {
                                            Map[checkx][checky]=1;
                                            q.add(new coordination(checkx, checky));
                                        }
                                    }
                                }
                            }
                            find=false;
                        }//클릭
 
                    }
                }
            }
            for(int i=0;i<N;i++)
                for(int j=0;j<N;j++) 
                    if(Map[i][j]==0) click++;
            System.out.println("#"+testcase+" "+click);
            click=0;
        }
    }
}