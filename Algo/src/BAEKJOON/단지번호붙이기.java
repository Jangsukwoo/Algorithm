package BAEKJOON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class RC2667{
	int row;
	int col;
	public RC2667(int r,int c) {
		this.row = r;
		this.col = c;
	}

}
public class 단지번호붙이기 {
	static char[][] industrialMap;
	static int N;
	static int entireIndustrialSize;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean[][] visit;
	static ArrayList<Integer> industrialList = new ArrayList<Integer>();
	static Queue<RC2667> q = new LinkedList<RC2667>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		industrialMap = new char[N][N];
		sc.nextLine();
		visit = new boolean[N][N];
		for(int line=0;line<N;line++) {
			String readLine = sc.nextLine();
			industrialMap[line] = readLine.toCharArray();
		}
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++){
				if(industrialMap[row][col]=='1' && visit[row][col] ==false){
					entireIndustrialSize++;
					int cnt=1;
					q.add(new RC2667(row,col));
					visit[row][col] = true;
					while(!q.isEmpty()){
						int size = q.size();
						for(int i=0;i<size;i++){
							RC2667 curRC = q.poll();
							for(int dir=0;dir<4;dir++){
								int nr = curRC.row+dr[dir];
								int nc = curRC.col+dc[dir];
								if(rangeCheck(nr,nc)) {
									if(industrialMap[nr][nc]=='1' && visit[nr][nc]==false) {
										cnt++;
										q.add(new RC2667(nr,nc));
										visit[nr][nc] = true;
									}
								}
							}
						}
					}
					industrialList.add(cnt);
				}
			}
		}
		Collections.sort(industrialList);
		System.out.println(entireIndustrialSize);
		for(int i=0;i<industrialList.size();i++) System.out.println(industrialList.get(i));
 	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
}
