package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미친로봇 {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};//동서남북
	static double[] probability = new double[4];
	static double result;
	static double simpleCase=0;
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		setData();
		visit[20][20] = true;
		dfs(0,20,20,1);
		System.out.println(1-simpleCase);
	}
	private static void dfs(int move, int cr, int cc, double pro) {
		if(move==N){
			return;
		}
		for(int dir=0;dir<4;dir++) {
			int nr = cr + dr[dir];
			int nc = cc + dc[dir];
			double np = pro*probability[dir];
			if(visit[nr][nc]==false){
				visit[nr][nc]=true;
				dfs(move+1,nr,nc,np);
				visit[nr][nc]=false;
			}else{//단순
				simpleCase+=np;
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		visit = new boolean[40][40];
		for(int dir=0;dir<4;dir++) probability[dir] = Double.parseDouble(st.nextToken())/100;
		result = 0;
	}
}
