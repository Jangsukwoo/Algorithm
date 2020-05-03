package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 이 성에 있는 방의 개수
 * 가장 넓은 방의 넓이
 * 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방 크기
 * 
 */
public class 성곽 {
	static int R,C;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] castle;
	static boolean[][] visit;
	static int answer1,answer2,answer3;
	static Queue<int[]> q; 
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Direction[] directionlist; 
	static int[][] area;
	static boolean[] areaVisit;
	static int[] areaSize;
	static class Direction{
		int[] dr;
		int[] dc;
		public Direction(int[] dr, int[] dc) {
			this.dr = dr;
			this.dc = dc;
		}
		public Direction() {
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		makeDirectionList();
		simulation();
		System.out.println(answer1);
		System.out.println(answer2);
		System.out.println(answer3);
	}



	private static void simulation() {
		//getAnswer1, getAnswer2
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(visit[row][col]==false){
					answer1++;
					area[row][col] = answer1;
					q.clear();
					insertQueue(row,col);
					bfs();
				}
			}
		}
		//getAnswer3
		areaVisit= new boolean[answer1+1];
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(areaVisit[area[row][col]]==false){
					areaVisit[area[row][col]]=true;
					q.clear();
					visit = new boolean[R][C];
					insertQueue2(row,col,area[row][col],1);
					bfs2(area[row][col]);
				}
			}
		}
	}

	private static void bfs2(int areaNumber){
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				int cn = current[2];
				int ca = current[3];
				for(int dir=0;dir<4;dir++) {
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr, nc)){
						if(area[nr][nc]==cn){//area 넘버 같으면
							insertQueue2(nr,nc,cn,ca);
						}
						else if(area[nr][nc]!=cn && ca>0){//넘버가 다른경우 건너뛸 수 있는지?
							answer3 = Math.max(answer3,areaSize[cn]+areaSize[area[nr][nc]]);
						}
					}
				}
			}
		}
	}



	private static void insertQueue2(int row,int col,int num,int ability){
		q.add(new int[]{row,col,num,ability});
		visit[row][col] = true;
	}
	private static void bfs(){
		int roomsize=1;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] curRC = q.poll();
				int cr = curRC[0];
				int cc = curRC[1];
				for(int dir=0;dir<directionlist[castle[cr][cc]].dr.length;dir++) {
					int nr = cr+directionlist[castle[cr][cc]].dr[dir];
					int nc = cc+directionlist[castle[cr][cc]].dc[dir];
					if(rangeCheck(nr,nc)) {
						area[nr][nc] =answer1;
						insertQueue(nr,nc);
						roomsize++;
					}
				}
			}
		}
		areaSize[answer1] = roomsize;
		answer2 = Math.max(roomsize,answer2);
	}


	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) {
			if(visit[nr][nc]==false) {
				return true;
			}
		}
		return false;
	}
	private static void insertQueue(int row, int col) {
		q.add(new int[] {row,col});
		visit[row][col] = true;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		castle = new int[R][C];
		visit = new boolean[R][C];
		for(int row=0;row<R;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<C;col++){
				castle[row][col] = Integer.parseInt(st.nextToken());
				if(row==0) castle[row][col]-=2;
				if(col==0) castle[row][col]-=1;
				if(row==R-1) castle[row][col]-=8;
				if(col==C-1) castle[row][col]-=4;
			}
		}
		area = new int[R][C];
		q = new LinkedList<int[]>();
		areaSize = new int[3000];
	}
	private static void makeDirectionList() {
		directionlist = new Direction[16];
		for(int i=0;i<16;i++) directionlist[i] = new Direction();
		directionlist[0].dr = new int[] {-1,0,1,0}; //상우하좌
		directionlist[0].dc = new int[] {0,1,0,-1};
		directionlist[1].dr = new int[] {-1,0,1};
		directionlist[1].dc = new int[] {0,1,0};
		directionlist[2].dr = new int[] {0,1,0};
		directionlist[2].dc = new int[] {1,0,-1};
		directionlist[3].dr = new int[] {0,1};
		directionlist[3].dc = new int[] {1,0};
		directionlist[4].dr = new int[] {-1,1,0};
		directionlist[4].dc = new int[] {0,0,-1};
		directionlist[5].dr = new int[] {-1,1};
		directionlist[5].dc = new int[] {0,0};
		directionlist[6].dr = new int[] {1,0};
		directionlist[6].dc = new int[] {0,-1};
		directionlist[7].dr = new int[] {1};
		directionlist[7].dc = new int[] {0};
		directionlist[8].dr = new int[] {-1,0,0};
		directionlist[8].dc = new int[] {0,1,-1};
		directionlist[9].dr = new int[] {-1,0};
		directionlist[9].dc = new int[] {0,1};
		directionlist[10].dr = new int[] {0,0};
		directionlist[10].dc = new int[] {1,-1};
		directionlist[11].dr = new int[] {0};
		directionlist[11].dc = new int[] {1};
		directionlist[12].dr = new int[] {-1,0};
		directionlist[12].dc = new int[] {0,-1};
		directionlist[13].dr = new int[] {-1};
		directionlist[13].dc = new int[] {0};

		directionlist[14].dr = new int[] {0};
		directionlist[14].dc = new int[] {-1};

		directionlist[15].dr = new int[] {};
		directionlist[15].dc = new int[] {};
	}
}
