package BAEKJOON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 보물섬 {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static char[][] map;
	static int R,C;
	static int maxDistance;
	static Queue<int[]> q = new LinkedList<int[]>();//0:Row,1:Col
	static ArrayList<int[]> startList = new ArrayList<int[]>();
	static boolean[][] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		sc.nextLine();
		for(int i=0;i<R;i++) {
			String line = sc.nextLine();
			map[i] = line.toCharArray();
			for(int j=0;j<C;j++){
				if(map[i][j]=='L') startList.add(new int[] {i,j});
			}
		}
		for(int i=0;i<startList.size();i++){
			visit = new boolean[R][C];
			int[] curRC = startList.get(i);
			q.add(new int[]{curRC[0],curRC[1]});
			visit[curRC[0]][curRC[1]] = true;
			BFS();
		}
		System.out.println(maxDistance-1);
	}
	private static void BFS() {
		int dist=0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] curRC = q.poll();
				for(int dir=0;dir<4;dir++){
					int nr = curRC[0]+dr[dir];
					int nc = curRC[1]+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(map[nr][nc]=='L' && visit[nr][nc] ==false){
							q.add(new int[] {nr,nc});
							visit[nr][nc] = true;
						}
					}
				}
			}
			dist++;
		}
		maxDistance = Math.max(maxDistance,dist);
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc <C) return true;
		return false;
	}
}
