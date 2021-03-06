package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 17:15~
 * N By M 격자판
 * . : 이동 가능
 * # : 벽
 * 열쇠 : a~f 열쇠
 * 문 : A~F 
 * 0 : 민식이 위치
 * 1 : 출구
 * 
 ##1##
 ##B##
 ##A##
 ##F##
 ##f##
 ##f.b
 ##f##
 ##0##
 ##a##
 
 6C1 ~ 6C6
 6 + 15 + 20 + 15 + 6 + 1 
 63
 boolean[][][] 50 50 63 
 
 18:28 끝
 
 */
public class 달이차오른다가자 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M;
	static char[][] maze;
	static int answer;
	static boolean flag;
	static Queue<Minsik> q = new LinkedList<Minsik>();
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean[][][][][][][][] visit;
	static class Minsik{
		int row;
		int col;
		boolean[] keys;
		int move;
		public Minsik(int row, int col,int move) {
			this.row = row;
			this.col = col;
			this.move = move;
			this.keys = new boolean[6];
		}
		public void setKey(char key){
			this.keys[key-'a'] = true;
		}
		public boolean isContain(char key) {
			return this.keys[key-'a'];
		}
		public String getKeyInformation(){
			String keyset = "";
			for(int i=0;i<6;i++) {
				if(keys[i]) keyset+="1";
				else keyset+="0";
			}
			return keyset;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		if(flag) System.out.println(answer);
		else System.out.println("-1");
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++){
				Minsik currentMinsik = q.poll();
				if(maze[currentMinsik.row][currentMinsik.col]=='1') {
					answer = currentMinsik.move;
					flag = true;
					return;
				}
				for(int dir=0;dir<4;dir++) {
					int nr = currentMinsik.row+dr[dir];
					int nc = currentMinsik.col+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(maze[nr][nc]!='#'){
							if((maze[nr][nc]>='a' && maze[nr][nc]<='f') || maze[nr][nc]=='.' || maze[nr][nc]=='1'){
								if(visitCheck(nr,nc,currentMinsik)){
									Minsik nextMinsik = new Minsik(nr,nc,currentMinsik.move+1);
									for(int j=0;j<6;j++) nextMinsik.keys[j] = currentMinsik.keys[j];//열쇠 복사
									if(maze[nr][nc]>='a' && maze[nr][nc]<='f') nextMinsik.setKey(maze[nr][nc]);
									insertQueue(nextMinsik);
								}
							}
							else if((maze[nr][nc]>='A' && maze[nr][nc]<='F')){//문이면
								char currentDoor = Character.toLowerCase(maze[nr][nc]);
								if(currentMinsik.isContain(currentDoor)){
									if(visitCheck(nr,nc,currentMinsik)){
										Minsik nextMinsik = new Minsik(nr,nc,currentMinsik.move+1);
										for(int j=0;j<6;j++) nextMinsik.keys[j] = currentMinsik.keys[j];//열쇠 복사
										insertQueue(nextMinsik);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private static boolean visitCheck(int nr, int nc, Minsik currentMinsik){
		String currentKeyset = currentMinsik.getKeyInformation();
		if(visit[nr][nc][Character.getNumericValue(currentKeyset.charAt(0))][Character.getNumericValue(currentKeyset.charAt(1))][Character.getNumericValue(currentKeyset.charAt(2))][Character.getNumericValue(currentKeyset.charAt(3))][Character.getNumericValue(currentKeyset.charAt(4))][Character.getNumericValue(currentKeyset.charAt(5))]==false) {
			return true;
		}
		return false;
	}

	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}

	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		visit = new boolean[N][M][2][2][2][2][2][2];
		for(int row=0;row<N;row++) {
			maze[row] = br.readLine().toCharArray();
			for(int col=0;col<M;col++) {
				if(maze[row][col]=='0'){
					maze[row][col]='.';
					Minsik minsik = new Minsik(row,col,0);
					insertQueue(minsik);
				}
			}
		}
	}

	private static void insertQueue(Minsik minsik) {
		String currentKeyset = minsik.getKeyInformation();
		q.add(minsik);
		visit[minsik.row][minsik.col][Character.getNumericValue(currentKeyset.charAt(0))][Character.getNumericValue(currentKeyset.charAt(1))][Character.getNumericValue(currentKeyset.charAt(2))][Character.getNumericValue(currentKeyset.charAt(3))][Character.getNumericValue(currentKeyset.charAt(4))][Character.getNumericValue(currentKeyset.charAt(5))] = true;
	}
}
