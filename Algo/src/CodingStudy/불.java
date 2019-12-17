package CodingStudy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/*
 * RxC의 맵이 주어짐 
 * 1<=R,C<=1000
 * 맵을 구성하는 문자에서 
 * # : 벽
 * . : 갈 수 있는 공간
 * J : 지훈이의 최초 위치
 * F : 블
 * 
 * 지훈이는 인접한 4방향으로 이동하고
 * 불은 인접한 4방향으로 번진다 
 * 지훈이와 불은 벽은 통과하지 못함
 * 
 * 이동은 불이먼저 지훈이가먼저?
 * 지훈이가 먼저 이동하도록 id 변수를 두어서
 * 지훈이를 맨앞으로함 
 * 
 * 
4 4
####
#.F#
#J.#
..F#
 */
class FireAndJihun implements Comparable<FireAndJihun>{
	int fireRow,fireCol;
	int jihunRow,jihunCol;
	int ID;
	public FireAndJihun(int fr,int fc, int jr, int jc, int id) {
		fireRow = fr;
		fireCol = fc;
		jihunRow = jr;
		jihunCol = jc;
		ID = id;
	}
	@Override
	public int compareTo(FireAndJihun o) {
		return Integer.compare(this.ID,o.ID);//ID 정렬 
	}
}
public class 불 {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상하좌우
	static char[][] maze;
	static boolean[][] jihunVisit;
	static boolean[][] fireVisit;
	static Queue<FireAndJihun> q = new LinkedList<FireAndJihun>();
	static int R,C; //(1<=R,C<=1000)
	static int time;
	static boolean escape;//탈출 여부 플래그 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		maze = new char[R][C];
		jihunVisit = new boolean[R][C];
		fireVisit= new boolean[R][C];
		sc.nextLine();//뉴라인 먹고
		for(int row=0;row<R;row++) {
			String readLine = sc.nextLine();
			maze[row] = readLine.toCharArray();
			for(int col=0;col<C;col++) {
				if(maze[row][col]=='J') {
					q.add(new FireAndJihun(0,0,row,col,1));//지훈,지훈을 뜻하는 아이디 2
					jihunVisit[row][col]=true;
				}
				if(maze[row][col]=='F') {
					q.add(new FireAndJihun(row,col,0,0,2));//불,불을 뜻하는 아이디 1
					fireVisit[row][col]=true;
				}
			}
		}
		Collections.sort((List<FireAndJihun>) q); //id기준으로 정렬하면 지훈의 아이디가 맨 앞으로감 
		BFS();
		if(escape) System.out.println(time);
		else System.out.println("IMPOSSIBLE");
	}
	private static void BFS() {
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				FireAndJihun currentFireAndJihun = q.poll();
				switch (currentFireAndJihun.ID){
				case 1://아이디가 1이면 지훈
					if(aliveCheck(currentFireAndJihun)){//지훈이가 살아있는 경우만
						moveJihun(currentFireAndJihun);//지훈이 이동
						if(escape) return;//탈출 플래그가 true면 종료
					}
					break;
				case 2://아이디가 2면 불 
					burn(currentFireAndJihun);//불이 인접한 방향으로 확산 
					break;
				}
			}
			time++;
		}
	}
	private static boolean aliveCheck(FireAndJihun currentFireAndJihun) {
		if(maze[currentFireAndJihun.jihunRow][currentFireAndJihun.jihunCol]=='J') return true;
		return false;
	}
	private static void moveJihun(FireAndJihun currentFireAndJihun) {
		for(int dir=0;dir<4;dir++) {
			int nr = currentFireAndJihun.jihunRow+dr[dir];
			int nc = currentFireAndJihun.jihunCol+dc[dir];
			if(rangeCheck(nr,nc)) {//아직 영역 내에 있으면
				if(maze[nr][nc]=='.' && jihunVisit[nr][nc]==false){
					//땅이면서 방문하지 않은 점이면 
					q.add(new FireAndJihun(0,0,nr,nc,1));
					jihunVisit[nr][nc]=true;
					maze[currentFireAndJihun.jihunRow][currentFireAndJihun.jihunCol]='.';
					maze[nr][nc] = 'J';
				}
				
			}else { //지훈의 경우 영역 밖이라면 탈출한것이므로
				time++;
				escape = true;
				return;
			}
		}
	}
	private static void burn(FireAndJihun currentFireAndJihun) {
		for(int dir=0;dir<4;dir++){//인접한 방향으로 불지른다 
			int nr = currentFireAndJihun.fireRow+dr[dir];
			int nc = currentFireAndJihun.fireCol+dc[dir];
			if(rangeCheck(nr, nc)){//영역 만족하고
				if(maze[nr][nc]!='#' && fireVisit[nr][nc]==false){//벽만 아니면 다 태움 
					q.add(new FireAndJihun(nr,nc,0,0,2));
					fireVisit[nr][nc]=true;
					maze[nr][nc]='F';
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}

}
