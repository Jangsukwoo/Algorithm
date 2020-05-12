package CodingStudyHW;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 12*6의 문자가 주어짐
 * 
 * bfs로 같은 색들 조사하면서 전부 터뜨리고 (그룹이 몇개인지는 중요치 않음)
 * 중력처리
 * 
 * 40분컷~
 */
public class PuyoPuyo {
	static char[][] field = new char[12][6];
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][] visit;
	static int adjCount;
	static boolean cascadeExplosionFlag;
	static int cascade;
	public static void main(String[] args) {
		setData();
		play();
		System.out.println(cascade);
	}
	private static void play() {
		while(true){
			cascadeExplosionFlag=false;
			explosion();//터짐. 여기서 여러 그룹이 터질 수 도있음
			if(cascadeExplosionFlag){//터졌으면 내림 처리
				//여러그룹이 터졌든 한 그룹이 터졌든 폭발이 일어났므로 cascade++;
				cascade++;
				gravity();//중력 처리
			}else{//터진게 없으면 여기서 끝낸다.
				return;
			}
		}
	}
	private static void gravity() {
		for(int col=0;col<6;col++){//한 열씩 검사
			for(int row=11;row>=0;row--){
				if(field[row][col]=='.'){//빈공간이면 위에 떨어질 뿌요가 있는지 조사하러 올라감
					int nr = row-1;
					while(nr>=0){
						if(field[nr][col]=='.') nr-=1;
						else{
							field[row][col]=field[nr][col];
							field[nr][col]='.';//위치스왑
							break;
						}
					}
				}
			}
		}
	}
	private static void explosion() {
		for(int row=0;row<12;row++){
			for(int col=0;col<6;col++){
				if(field[row][col]!='.'){//알파벳이면
					bfs(row,col,field[row][col]);
					if(adjCount>=4){//인접개수가 4이상이면 터져야함~
						cascadeExplosionFlag=true;
						cascadeExplosion();
					}
				}
			}
		}
	}
	private static void view() {
		for(int row=0;row<12;row++) {
			for(int col=0;col<6;col++) {
				System.out.print(field[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void cascadeExplosion() {
		for(int row=0;row<12;row++) {
			for(int col=0;col<6;col++) {
				if(visit[row][col]) field[row][col]='.';
			}
		}
	}
	private static void bfs(int startRow, int startCol, char currentPuyo) {//각자리에서 무조건 확인
		visit = new boolean[12][6];
		adjCount=0;//인접개수 초기화
		insertQueue(startRow,startCol);
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] curRC = q.poll();
				int cr = curRC[0];
				int cc = curRC[1];
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)){//영역 만족하면서
						if(field[nr][nc]==currentPuyo && visit[nr][nc]==false){//같은 뿌요면서 방문 안했으면
							insertQueue(nr,nc);
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<12 && nc>=0 && nc<6) return true;
		return false;
	}
	private static void insertQueue(int startRow, int startCol) {
		q.add(new int[] {startRow,startCol});
		visit[startRow][startCol] = true;
		adjCount++;
	}
	private static void setData() {
		Scanner sc = new Scanner(System.in);
		for(int row=0;row<12;row++) {
			String readLine = sc.nextLine();
			field[row] = readLine.toCharArray();
		}
	}
}
