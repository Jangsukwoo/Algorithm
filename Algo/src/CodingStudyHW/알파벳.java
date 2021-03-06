package CodingStudyHW;

import java.util.Scanner;



/*
 * 19:36
 * 말이 최대한 몇칸 지나갈 수 있는지?
 * 좌측 상단에서 시작함
 * 
 * 20:03 끝
 * 
 */
public class 알파벳 {
	static int R,C;
	static int maxPathLength;
	static char[][] alphabet;
	static boolean[] alphabetVisit = new boolean[26];
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) {
		setData();
		
		int ascii = alphabet[0][0]-'A';
		alphabetVisit[ascii] = true;
		dfs(0,0,1);
		System.out.println(maxPathLength);
	}
	private static void dfs(int cr, int cc, int length) {
		
		for(int dir=0;dir<4;dir++){
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(rangeCheck(nr,nc)){
				int ascii = alphabet[nr][nc]-'A';
				if(alphabetVisit[ascii]==false) {
					alphabetVisit[ascii] = true;
					dfs(nr,nc,length+1);
					alphabetVisit[ascii] = false;
				}
			}
		}//네방향 다 검사 했는데 갈 수 없다면 종착지일테니 이후 length값 갱신
		
		maxPathLength = Math.max(maxPathLength,length);
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) return true;
		return false;
	}
	private static void setData() {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		alphabet = new char[R][C];
		sc.nextLine();
		for(int row=0;row<R;row++) alphabet[row] = sc.nextLine().toCharArray();
		
	}
}
