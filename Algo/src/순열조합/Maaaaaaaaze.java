package 순열조합;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 3:44~
 * 25X25 정육면체
 * 각 판을 쌓는 순서는 임의로 정할 수 있고
 * 각 판은 시계방향으로 회전할 수 있다.
 * 판을 쌓는 경우의수 5! = 120
 * 각 판 시계방향에 대해 4개씩의 경우의수가 존재하고
 * 각 층별로 가능한 경우의 수는 4^5
 * 120 x 4^2 = 122280
 * 최단경로를 구하는것이므로 각 판에 대해서 bfs 수행
 * 적절한 미로 정육면체를 먼저 만드는게 우선
 * boolean 4차원 맵을 생각했다.
 * 첫번째 인덱스는 층
 * 두번째 인덱스는 각 회전 경우의 수 
 * 세번재,네번째 인덱스는 row,col
 * 
 * 코드는 2시간정도만에 나왔으나
 * 답이 이상하게 나와서 계속 디버깅하다가 
 * 결국 찾은 문제점이 q.clear()를 안해준것이였다..
 * 맞왜틀만 계속하다가 큐를 초기화하지 않은 문제점 찾고 너무 속상했다 ㅠ 하아 
 */
public class Maaaaaaaaze {
	static int[][][][] mazeCase = new int[5][4][5][5];//모든 가능한 판의 집합
	static int[][][] maze = new int[5][5][5];
	static boolean[][][] mazeVisit = new boolean[5][5][5];
	static int[] floor = new int[5];
	static int[] spin = new int[5];
	static boolean[] visit = new boolean[5];
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static int[] dz = {-1,1};
	static int distance = Integer.MAX_VALUE;
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int board =0;board<5;board++) {
			for(int row=0;row<5;row++)
				for(int col=0;col<5;col++) mazeCase[board][0][row][col] = sc.nextInt();
		}
		setRotationBoard();//모든 가능한 정육면체 세팅
		entireFloorDFS(0);//5!
		if(distance==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(distance);
	}
	private static void entireFloorDFS(int cnt) {//5!
		if(cnt==5){ //판을 쌓는 한 경우의 수. 5!중에 한 경우
			entireRotateDFS(0);
			return;
		}
		for(int i=0;i<5;i++){
			if(visit[i]==false) {
				visit[i] = true;
				floor[cnt] = i;
				entireFloorDFS(cnt+1);
				visit[i] = false;
			}
		}
	}
	private static void entireRotateDFS(int cnt) {//4^2 = 1024
		if(cnt==5){ //각 판 층에 대해 회전에 대한 경우의수 1024개 중에 한 경우 
			setMaze();
			escapeBFS();
			return ;
		}
		for(int i=0;i<4;i++){
			spin[cnt] = i;
			entireRotateDFS(cnt+1);
		}
	}
	private static void escapeBFS() {
		if(maze[0][0][0]!=1) return;
		mazeVisit = new boolean[5][5][5];
		int cnt=0;
		q.clear();
		q.add(new int[]{0,0,0});//z,row,col
		mazeVisit[0][0][0] = true;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] curZRC = q.poll();
				if((curZRC[0]==4&&curZRC[1]==4&&curZRC[2]==4)){//도착했으면
					distance = Math.min(cnt,distance);
					return ;
				}
				for(int dir=0;dir<4;dir++){//현재 판에서 상우하좌
					int nr = curZRC[1]+dr[dir];
					int nc = curZRC[2]+dc[dir];
					if(rangeCheck(nr,nc)){
						if(mazeVisit[curZRC[0]][nr][nc]==false && maze[curZRC[0]][nr][nc]==1){//안가봤고 갈 수 있으면
							mazeVisit[curZRC[0]][nr][nc] = true;
							q.add(new int[] {curZRC[0],nr,nc});
						}
					}
				}
				for(int dir=0;dir<2;dir++){//Z축 2방향
					int nz = curZRC[0]+dz[dir];
					if(nz>=0 && nz<5){//영역 만족
						if(mazeVisit[nz][curZRC[1]][curZRC[2]]==false && maze[nz][curZRC[1]][curZRC[2]]==1){//안가봤고 갈 수 있으면
							mazeVisit[nz][curZRC[1]][curZRC[2]]=true;
							q.add(new int[] {nz,curZRC[1],curZRC[2]});
						}
					}
				}
			}
			cnt++;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<5 && nc>=0 && nc<5) return true;
		return false;
	}
	private static void setMaze() {
		for(int i=0;i<5;i++){
			for(int row=0;row<5;row++) {
				for(int col=0;col<5;col++){
					maze[i][row][col] = mazeCase[floor[i]][spin[i]][row][col];
				}
			}
		}
	}
	private static void setRotationBoard() {
		for(int board=0;board<5;board++){//각 판에대해서
			for(int spin=1;spin<4;spin++){//시계방향 회전을 한다.
				rotate(board,spin);
			}
		}
	}
	private static void rotate(int board,int spin) {
		for(int idx=0;idx<2;idx++){
			for(int col=idx;col<(5-idx);col++){
				mazeCase[board][spin][col][4-idx] = mazeCase[board][spin-1][idx][col];//상변이 우변으로
				mazeCase[board][spin][col][idx] = mazeCase[board][spin-1][4-idx][col];//하변이 좌변으로
			}
			for(int row=idx;row<(5-idx);row++){
				mazeCase[board][spin][4-idx][4-row] = mazeCase[board][spin-1][row][4-idx];//우변이 하변으로
				mazeCase[board][spin][idx][4-row] = mazeCase[board][spin-1][row][idx];//좌변이 상변으로
			}
		}
		mazeCase[board][spin][2][2] = mazeCase[board][spin-1][2][2];
	}
	private static void view() {
		for(int spin=0;spin<4;spin++) {
			for(int row=0;row<5;row++) {
				for(int col=0;col<5;col++) {
					System.out.print(mazeCase[0][spin][row][col]);
				}
				System.out.println();
			}	
			System.out.println();
		}
	}
}
