package 공채대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 10:25~
 * A1->A2로의 최단경로는 여러가지가 나올 수 있다.
6 6
2 1
5 4
3 3
4 5


6 6
4 0
4 5
2 1
5 4

dfs로 선 긋기 
ㅠ ㅠ
 */
public class 연결2 {
	static int N,M;
	static int A1R,A1C;
	static int A2R,A2C;
	static int B1R,B1C;
	static int B2R,B2C;
	static int[][] circuit;
	static boolean[][] visitA;
	static boolean[][] visitB;
	static int Alength;
	static int Blength;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<int[]> q;
	static int end;
	static boolean flag;
	static int answer = Integer.MAX_VALUE;
	static int[][] dist;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	
	public static void main(String[] args) throws IOException {
		setData();
		
		connectA();
		reverseConnectA();
		connectB();
		
		
		
		if(end>0) {
			answer = Math.min(answer,Alength+Blength);
			end=0;
		}
		
		
		swap();
		
		
		connectA();
		reverseConnectA();
		connectB();
		
		
		
		if(end>0) {
			answer = Math.min(answer,Alength+Blength);
		}
		if(answer!=Integer.MAX_VALUE) System.out.println(answer);
		else System.out.println("IMPOSSIBLE");
	}
	private static void swap() {
		int temp1 = B1R;
		int temp2 = B1C;
		B1R = A1R;
		B1C = A1C;
		A1R = temp1;
		A1C = temp2;
		temp1 = B2R;
		temp2 = B2C;
		B2R = A2R;
		B2C = A2C;
		A2R = temp1;
		A2C = temp2;
	}
	private static void connectB(){
		visitB = new boolean[N+1][M+1];
		q.clear();
		q.add(new int[] {B1R,B1C,0});
		visitB[B1R][B1C] = true;
		int length = 1;
		boolean find = false;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				int cd = current[2];
				if(cr==B2R && cc==B2C){
					Blength = cd;
					end++;
					find = true;
					return;
				}
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(visitB[nr][nc]==false && circuit[nr][nc]!=3){
							q.add(new int[] {nr,nc,length});
							visitB[nr][nc] = true;
							circuit[nr][nc] = 2;
						}
					}
				}
			}
			length++;
			if(length>Blength && find) break;
		}
	}
	private static void view2() {
		for(int row=0;row<=N;row++) {
			for(int col=0;col<=M;col++) {
				System.out.print(circuit[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void connectA(){
		dist = new int[N+1][M+1];
		circuit = new int[N+1][M+1];
		visitA = new boolean[N+1][M+1];
		q = new LinkedList<int[]>();
		insertQueue(A1R,A1C,0);
		circuit[A1R][A1C] = 1;
		circuit[B1R][B1C] = 2;
		circuit[B2R][B2C] = 2;
		int length = 1;
		boolean find = false;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				int cd = current[2];
				if(cr==A2R && cc==A2C){
					Alength = cd;
					circuit[cr][cc] = 1;
					find = true;
					continue;
				}
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(visitA[nr][nc]==false && circuit[nr][nc]!=2){
							insertQueue(nr,nc,length);
						}
					}
				}
			}
			length++;
			if(length>Alength && find) break;
		}
	}
	private static void reverseConnectA() {
		q.clear();
		int length = Alength-1;
		q.add(new int[] {A2R,A2C});
		boolean[][] visit = new boolean[N+1][M+1];
		visit[A2R][A2C] = true;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr, nc)){
						if(dist[nr][nc]==length && visitA[nr][nc] && visit[nr][nc] ==false) {
							circuit[nr][nc] = 1;
							q.add(new int[] {nr,nc});
							visit[nr][nc] = true;
						}
					}
				}
			}
			length--;
		}
		
		circuit[A1R][A1C] = 3;
		flag = false;
		Alength=0;
		dfs(A1R,A1C,0);
		
		
		q.clear();
		q.add(new int[] {A2R,A2C});
		visit = new boolean[N+1][M+1];
		visit[A2R][A2C] = true;
		int depth=0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				if(cr==A1R && cc== A1C) {
					Alength = Math.min(depth,Alength);
					break;
				}
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr, nc)){
						if(circuit[nr][nc]==3 && visit[nr][nc] ==false) {
							q.add(new int[] {nr,nc});
							visit[nr][nc] = true;
						}
					}
				}
			}
			depth++;
		}
		for(int row=0;row<=N;row++) {
			for(int col=0;col<=M;col++) {
				if(circuit[row][col]==1) {
					circuit[row][col] = 0;
				}
			}
		}
	}
	private static void dfs(int cr, int cc, int depth){
		if(flag) return;
		
		if(cr==A2R && cc==A2C){
			Alength=depth;
			flag = true;
			return;
		}
		for(int dir=0;dir<4;dir++){
			int nr = cr+dr[dir];
			int nc = cc+dc[dir];
			if(flag) continue;
			if(rangeCheck(nr, nc)){
				if(circuit[nr][nc]==1 && circuit[nr][nc]!=2){
					circuit[nr][nc] = 3;
					dfs(nr,nc,depth+1);
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<=N && nc>=0 && nc<=M) return true;
		return false;
	}
	private static void setData() throws IOException{
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		A1C = Integer.parseInt(st.nextToken());
		A1R = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		A2C = Integer.parseInt(st.nextToken());
		A2R = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		B1C = Integer.parseInt(st.nextToken());
		B1R = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		B2C = Integer.parseInt(st.nextToken());
		B2R = Integer.parseInt(st.nextToken());
	}
	private static void insertQueue(int a1r, int a1c,int d){
		q.add(new int[] {a1r,a1c,d});
		visitA[a1r][a1c] = true;
		dist[a1r][a1c] = d;
	}
}
