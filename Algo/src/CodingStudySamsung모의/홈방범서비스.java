package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 마름모 영역 표현하기!!!
 * 
 * 운영비용 : K^2 + (K-1)^2
 * 보안회사의 이익 : 서비스제공받는 집들을 통해 얻는 수익 - 운영비용
 * 
 * 맵에서 가능한 모든 마름모 영역에 대해 최대이익 구하기 
 * N사이즈 최대 20이니까 
 * K는 최대 10 
 * 
 * 구현중..
1
8 3
0 0 0 0 0 1 0 0
0 1 0 1 0 0 0 1
0 0 0 0 0 0 0 0
0 0 0 1 0 1 0 0
0 0 1 1 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 1 0 1 0
1 0 0 0 0 0 0 0
 */
public class 홈방범서비스 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] city;
	static int maxServiceHouse;
	static int[][] diamond;
	static int manageCost;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int N,M;//맵사이즈,집당 비용
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			setDiamond();
			System.out.println("#"+testcase+" "+maxServiceHouse);
		}
	}
	private static void setDiamond() {
		for(int k=1;k<=(N+2);k++){
			
			manageCost = (int) (Math.pow(k,2)+Math.pow((k-1),2));//운영비용
			for(int row=0;row<N;row++) {
				for(int col=0;col<N;col++){
					paintDiamond(row,col,k);//현재 좌표와 K범위, 다이아 그리기 
					//view();
					countHouse();
				}
			}
		}
	}
	private static void view() {
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				System.out.print(diamond[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void countHouse(){
		int cnt=0;
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(diamond[row][col]==1 && city[row][col]==1) cnt++;	
			}
		}
		int profit = cnt*M - manageCost;
		if(profit>=0) maxServiceHouse = Math.max(maxServiceHouse,cnt);
	}
	private static void paintDiamond(int centerRow, int centerCol, int K){//마름모를 어떻게 표현할까..
		diamond = new int[N][N];
		int cnt=1;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {centerRow,centerCol});
		diamond[centerRow][centerCol] = 1;
		while(!q.isEmpty()){
			if(cnt==K) break;
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRC = q.poll();
				for(int dir = 0; dir<4;dir++) {
					int nr = curRC[0]+dr[dir];
					int nc = curRC[1]+dc[dir];
					if(rangCheck(nr,nc)){
						if(diamond[nr][nc]==0){
							q.add(new int[] {nr,nc});
							diamond[nr][nc] = 1; 
						}
					}
				}
			}
			cnt++;
		}
	}
	private static boolean rangCheck(int row, int col){
		if(row>=0 && row<N && col>=0 && col<N) return true;
		return false;
	}
	private static void setData() throws IOException {
		maxServiceHouse=0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		city = new int[N][N];
		for(int row=0;row<N;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				city[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
