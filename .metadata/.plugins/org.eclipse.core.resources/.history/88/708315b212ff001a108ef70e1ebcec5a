package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 현수막 {
	static int[][] banner;
	static boolean[][] visit;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int answer;
	static int R,C;
	static StringTokenizer st;
	static Queue<int[]> q;
	static int[] dr = {-1,-1,-1,0,1,1,1,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1};
	static int character;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		banner = new int[R][C];
		visit = new boolean[R][C];
		q = new LinkedList<int[]>();
		for(int row=0;row<R;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<C;col++) {
				banner[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		for(int row=0;row<R;row++) {
			for(int col=0;col<C;col++) {
				if(banner[row][col]==1 && visit[row][col] == false) {
					character++;
					q.add(new int[] {row,col});
					visit[row][col] = true;
					while(!q.isEmpty()) {
						int[] current = q.poll();
						int nr = 0;
						int nc = 0;
						for(int dir=0;dir<8;dir++) {
							nr = current[0]+dr[dir];
							nc = current[1]+dc[dir];
							if(nr>=0 && nr<R && nc>=0 && nc<C) {
								if(banner[nr][nc]==1 && visit[nr][nc]==false) {
									q.add(new int[] {nr,nc});
									visit[nr][nc] = true;
								}
							}
						}
					}
				}
			}
		}
		System.out.println(character);
	}
}
