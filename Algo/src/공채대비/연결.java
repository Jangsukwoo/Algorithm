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
 */
public class 연결 {
	static int N,M;
	static int A1R,A1C;
	static int A2R,A2C;
	static int B1R,B1C;
	static int B2R,B2C;
	static int[][] circuit;
	static boolean[][] visit;
	static boolean[][] visitB;
	static int[][] dist;
	static int Alength;
	static int Blength;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<int[]> q;
	static int end;
	static int answer = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		setData();
		connectA();
		reverseConnectA();
		connectB();
		if(end==1) {
			answer = Alength+Blength;
			System.out.println("성공1");
			System.out.println(answer);
		}
		swap();
		connectA();
		reverseConnectA();
		connectB();
		if(end>=1) {
			answer = Math.min(answer,Alength+Blength);
			System.out.println("성공2");
		}
		if(end==0) System.out.println("IMPOSSIBLE");
		else System.out.println(answer);
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
		q.clear();
		visit = new boolean[N+1][M+1];
		int length = 1;
		boolean find = false;
		insertQueue(B1R,B1C,0);
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
					//System.out.println("b연결");
					//System.out.println(Blength);
					find = true;
					continue;
				}
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(visit[nr][nc]==false){
							insertQueue(nr,nc,length);
						}
					}
				}
			}
			length++;
			if(length>Blength && find) break;
		}

	}

	private static void reverseConnectA() {
		q.clear();
		visit = new boolean[N+1][M+1];
		int length = Alength-1;
		q.add(new int[] {A2R,A2C});
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
						if(dist[nr][nc]==length && visit[nr][nc]==false) {
							circuit[nr][nc] = 1;
							q.add(new int[] {nr,nc});
							visit[nr][nc] = true;
						}
					}
				}
			}
			length--;
		}
		view2();
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
		circuit = new int[N+1][M+1];
		visit = new boolean[N+1][M+1];
		dist = new int[N+1][M+1];
		circuit[A1R][A1C] = 1;
		q = new LinkedList<int[]>();
		insertQueue(A1R,A1C,0);
		circuit[A2R][A2C] = 1;
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
					//System.out.println(cr+" "+cc);
					Alength = cd;
					//System.out.println(Alength);
					find = true;
					continue;
				}
				for(int dir=0;dir<4;dir++){
					int nr = cr+dr[dir];
					int nc = cc+dc[dir];
					if(rangeCheck(nr,nc)) {
						if(visit[nr][nc]==false){
							insertQueue(nr,nc,length);
						}
					}
				}
			}
			length++;
			if(length>Alength && find) break;
		}
		view();
	}
	private static void view() {
		for(int row=0;row<=N;row++) {
			for(int col=0;col<=M;col++) {
				System.out.print(dist[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<=N && nc>=0 && nc<=M) return true;
		return false;
	}
	private static void setData() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
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
		visit[a1r][a1c] = true;
		dist[a1r][a1c] = d;
	}
}
