package SWExpertAcademy;
import java.util.Scanner;
/*
 문자 의미 
. 평지(전차가 들어갈 수 있다.) 
 * 벽돌로 만들어진 벽 
# 강철로 만들어진 벽 
- 물(전차는 들어갈 수 없다.) 
^ 위쪽을 바라보는 전차(아래는 평지이다.) 
v 아래쪽을 바라보는 전차(아래는 평지이다.) 
< 왼쪽을 바라보는 전차(아래는 평지이다.) 
> 오른쪽을 바라보는 전차(아래는 평지이다.) 
문자 동작
U Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다. 
D Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다. 
L Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다. 
R Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다. 
S Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다. 
 */
public class 상호의배틀필드{
    public static int H; //Height
    public static int W; //Width
    public static String tmp;
    public static char[][] Map; 
    public static int operationSize; //Manipulation
    public static char[] operation; //Manipulation
    public static int[] dx = {-1,0,1,0};
    public static int[] dy = {0,1,0,-1}; //Index 0,1,2,3 각각의 번호 = U R D L (시계방향)
    public static int curX;
    public static int curY;
    public static char curDirection;
    private static void move(int moveX, int moveY) {
        Map[curX][curY]= curDirection;
        if((curX+moveX<H && curX+moveX>=0) && (curY+moveY<W && curY+moveY>=0)){
            if(Map[curX+moveX][curY+moveY] =='.') {
                Map[curX][curY]='.';
                Map[curX+moveX][curY+moveY] =curDirection;
                curX+=moveX;
                curY+=moveY;
            }
        }
    }
    private static void shoot() {
        if(curDirection=='^') {
            for(int x=curX;x>=0;x--) {
                if(Map[x][curY]!='#') {
                    if( Map[x][curY]=='*') {
                        Map[x][curY]='.';
                        break;  
                    }
                }
                else break;
            }
        }
        else if(curDirection=='>') {
            for(int y=curY;y<W;y++) {
                if(Map[curX][y]!='#') {
                    if(Map[curX][y]=='*') {
                        Map[curX][y]='.';
                        break;
                    }   
                }
                else break;
            }
        }
        else if(curDirection=='v') {
            for(int x=curX;x<H;x++) {
                if(Map[x][curY]!='#') {
                    if( Map[x][curY]=='*') {
                        Map[x][curY]='.';
                        break;  
                    }
                }
                else break;
            }
        }
        else if(curDirection=='<') {
            for(int y=curY;y>=0;y--) {
                if(Map[curX][y]!='#') {
                    if(Map[curX][y]=='*') {
                        Map[curX][y]='.';
                        break;
                    }   
                }
                else break;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            //입력
            H=sc.nextInt();
            W=sc.nextInt();
            Map = new char[H][W];
            for(int i = 0;i<H;i++) {
                tmp = sc.next();
                for(int j=0;j<W;j++) {
                    Map[i][j] = tmp.charAt(j);
                    if(tmp.charAt(j) == '>' || tmp.charAt(j) == '^' || tmp.charAt(j) == '<' || tmp.charAt(j) == 'v') {
                        curX=i;
                        curY=j; //초기 전차 위치
                        curDirection = tmp.charAt(j);
                    }
                }
            }
            operationSize = sc.nextInt();
            tmp = sc.next();
            operation = new char[operationSize];
            for(int i=0;i<operationSize;i++) operation[i]=tmp.charAt(i);
            //처리
            for(int move=0;move<operationSize;move++) {
                switch(operation[move]) {
                case 'U':
                    curDirection ='^';
                    move(dx[0],dy[0]);
                    break;
                case 'R':
                    curDirection ='>';
                    move(dx[1],dy[1]);
                    break;
                case 'D':
                    curDirection ='v';
                    move(dx[2],dy[2]);
                    break;
                case 'L':
                    curDirection ='<';
                    move(dx[3],dy[3]);
                    break;
                case 'S':
                    shoot();
                    break;
                }
            }   
            //출력
            System.out.print("#"+testcase+" ");
            for(int i=0; i<H;i++) {
                for(int j=0; j<W; j++) {
                    System.out.print(Map[i][j]);
                }
                System.out.println();
            }
        }
    }
}