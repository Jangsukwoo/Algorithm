package CodingStudyHW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
4 4
####
#J.#
#.F#
#..#
 */
public class 불 {
	static int R,C;
	static char[][] maze;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Queue<JF> q = new LinkedList<JF>();
	static boolean[][] jihunvisit;
	static boolean[][] firevisit;
	static int answer;
	static boolean escape;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static class JF{
		int jihunrow;
		int jihuncol;
		int firerow;
		int firecol;
		int id;
		public JF(int jihunrow, int jihuncol, int firerow, int firecol, int id) {
			this.jihunrow = jihunrow;
			this.jihuncol = jihuncol;
			this.firerow = firerow;
			this.firecol = firecol;
			this.id = id;
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		bfs();
		if(escape) System.out.println(answer);
		else System.out.println("IMPOSSIBLE");
	}
	private static void bfs(){
		int time=0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				JF currentJF = q.poll();
				switch (currentJF.id) {
				case 1: //지훈
					if(firevisit[currentJF.jihunrow][currentJF.jihuncol]==false) {
						moveJihun(currentJF);
						if(escape){
							time++;
							answer = time;
							return;
						}
					}
					break;
				case 2: //불
					burn(currentJF);
					break;
				}
			}
			time++;
		}
	}
	private static void burn(JF currentJF) {
		int currentR = currentJF.firerow;
		int currentC = currentJF.firecol;
		for(int dir=0;dir<4;dir++) {
			int nr = currentR+dr[dir];
			int nc = currentC+dc[dir];
			if(rangeCheck(nr,nc)) {
				if(maze[nr][nc]!='#' && firevisit[nr][nc]==false){
					q.add(new JF(-1,-1,nr,nc,2));
					firevisit[nr][nc] = true;
				}
			}
		}
	}
	private static void moveJihun(JF currentJF) {
		int currentR = currentJF.jihunrow;
		int currentC = currentJF.jihuncol;
		for(int dir=0;dir<4;dir++) {
			int nr = currentR+dr[dir];
			int nc = currentC+dc[dir];
			if(rangeCheck(nr,nc)) {
				if(maze[nr][nc]!='F' && maze[nr][nc]!='#' && jihunvisit[nr][nc]==false){
					q.add(new JF(nr,nc,-1,-1,1));
					jihunvisit[nr][nc] = true;
				}
			}else {
				escape = true;
				return;
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maze = new char[R][C];
		jihunvisit = new boolean[R][C];
		firevisit = new boolean[R][C];

		ArrayList<JF> templist = new ArrayList<JF>();
		for(int row=0;row<R;row++) {
			maze[row] = br.readLine().toCharArray();
			for(int col=0;col<C;col++) {
				if(maze[row][col]=='J'){
					jihunvisit[row][col] = true;
					templist.add(new JF(row,col,-1,-1,1));
				}else if(maze[row][col]=='F'){
					firevisit[row][col] = true;
					templist.add(new JF(-1,-1,row,col,2));
				}
			}
		}

		Collections.sort(templist, new Comparator<JF>() {
			@Override
			public int compare(JF o1, JF o2) {
				return Integer.compare(o1.id,o2.id);
			}
		});
		for(int i=0;i<templist.size();i++) q.add(templist.get(i));
	}
}
