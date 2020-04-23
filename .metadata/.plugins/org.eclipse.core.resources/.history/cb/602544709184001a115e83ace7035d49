package CodingStudySamsungProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 집의 크기 N by N
 * 3<=N<=16
 * 빈칸은 0 벽은 1
 * 파이프를 옮기는 방법은 총 3가지 : →, ↘, ↓
 * 0,0에서 N,N으로 가는 모든 경우의 수를 구하자.
 * 
 * BFS로 하면 시간초과 
 * 
 */
public class 파이프옮기기1 {
	static int N;
	static int[][] house;
	static int[] dr = {0,1,1};
	static int[] dc = {1,1,0}; // 0:→ 우, 1: ↘ 우하 , 2: ↓ 하
	static Queue<int[]> q = new LinkedList<int[]>();
	static int caseCount;

	public static void main(String[] args) throws IOException {
		//입력
		input();
		//처리
		bfs();
		//출력
		System.out.println(caseCount);
	}
	private static void bfs() {
		//최초의 파이프 좌표, 누워있는 상태
		q.add(new int[] {0,1,0});//head의 row,col,누워있는 상태(우)
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] currentPipe = q.poll();//현재 파이프에 대해서
				push(currentPipe);
			}
		}
	}

	private static void push(int[] currentPipe) {
		int position = currentPipe[2];
		if(currentPipe[0]==(N-1) && currentPipe[1]==(N-1)) {//끝에 도달 했으면
			caseCount++;
			return;
		}
		switch (position) {//현재 파이프 상태가
		case 0://오른쪽으로 누워있는 상태
			pushRight(currentPipe);
			pushRightBottom(currentPipe);
			break;
		case 1://오른쪽아래로로 누워있는 상태
			pushRight(currentPipe);
			pushRightBottom(currentPipe);
			pushBottom(currentPipe);
			break;
		case 2://아래방향으로 누워있는 상태		
			pushBottom(currentPipe);
			pushRightBottom(currentPipe);
			break;
		}
	}
	private static void pushRight(int[] currentPipe) {//오른쪽으로 밀기
		int cr = currentPipe[0];
		int cc = currentPipe[1];
		int nr = cr+dr[0];
		int nc = cc+dc[0];
		if(rangeCheck(nr,nc) && house[nr][nc]!=1){//경계 만족하고 벽도 아니고
			q.add(new int[] {nr,nc,0});//다음칸으로 전진
		}		
	}
	private static void pushRightBottom(int[] currentPipe) { //대각선으로 밀기
		int cr = currentPipe[0];
		int cc = currentPipe[1];
		int nr = cr+dr[1];
		int nc = cc+dc[1];
		if(rangeCheck(nr,nc) && house[nr][nc]!=1){//경계 만족하고 벽도 아니면 
			if(house[cr+1][cc]!=1 && house[cr][cc+1]!=1){//오른쪽,아래가 벽이 아니면				
				q.add(new int[] {nr,nc,1});//다음칸으로 전진
			}
		}
	}
	private static void pushBottom(int[] currentPipe) { //아래방향으로 밀기
		int cr = currentPipe[0];
		int cc = currentPipe[1];
		int nr = cr+dr[2];
		int nc = cc+dc[2];
		if(rangeCheck(nr,nc) && house[nr][nc]!=1){//경계 만족하고 벽도 아니고
			q.add(new int[] {nr,nc,2});//다음칸으로 전진
		}	
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		house = new int[N][N];
		for(int row=0;row<N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				house[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
