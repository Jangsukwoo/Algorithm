package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * daltonism(적록색약)
 * 
 * 문자 R,G,B로 채워진 N by N Map
 * R = 빨강
 * G = 초록
 * B = 파랑
 * 1<=N<=100
 * 같은색으로 인접해있는 구역은 같은 구역으로 취급한다.
 * 
 * 문제 조건이 명확하게 명시 되어있지 않고
 * 추측상 적색과 녹색을 같은색이라 판단한다 생각하고 문제 풀이 시작함
 * 
 * 일반 사람이 보는 색깔 영역의 개수와
 * 적록색약인 사람이 보는 색깔 영역의 개수를 구하는 문제
 * dfs와 bfs 둘 다 사용하고 싶다는 생각이 들어서
 * 일반 사람은 dfs로 찾고 
 * 적록색약인 사람은 bfs로 찾는다.
 */
public class 적록색약 {
	static int N;
	static char[][] colorMap;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상 우 하 좌
	static int personAreaOfNormal;
	static int personAreaOfDaltonism;
	static boolean[][] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//입력
		N = sc.nextInt();
		colorMap = new char[N][N];
		sc.nextLine();
		for(int line=0;line<N;line++){
			String readLine = sc.nextLine();
			colorMap[line] = readLine.toCharArray();
		}
		//처리
		getNormalPerson();
		getDaltonismPerson();
		//출력
		System.out.println(personAreaOfNormal+" "+personAreaOfDaltonism);
	}
	private static void getNormalPerson() {//use DFS
		visit = new boolean[N][N];
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++){
				if(visit[row][col]==false){//방문하지 못한 점에 대해서 
					visit[row][col]=true;
					personAreaOfNormal++;//새 영역
					dfs(row,col,colorMap[row][col]);
				}
			}
		}
	}
	private static void dfs(int row, int col, char c) {
		for(int dir=0;dir<4;dir++) {
			int nr = row+dr[dir];
			int nc = col+dc[dir];
			if(rangeCheck(nr,nc)){//영역 만족하고
				if(visit[nr][nc]==false && colorMap[nr][nc]==c){//방문 안했고 검사하는 문자와 같으면
					visit[nr][nc]=true;
					dfs(nr,nc,c);
				}
			}
		}
	}
	private static void getDaltonismPerson() {//use BFS
		visit = new boolean[N][N];
		Queue<int[]> q = new LinkedList<int[]>();
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(visit[row][col]==false){//방문하지 못한 점에 대해서
					personAreaOfDaltonism++;//새 영역 
					char color = colorMap[row][col];//색깔
					q.add(new int[] {row,col});
					visit[row][col] = true;
					while(!q.isEmpty()){
						int size = q.size();
						for(int i=0;i<size;i++){
							int[] curRC = q.poll();
							for(int dir=0;dir<4;dir++){
								int nr = curRC[0]+dr[dir];
								int nc = curRC[1]+dc[dir];
								if(rangeCheck(nr, nc)) {//영역 만족하는데
									if(color=='B'){//파랑인 경우에는 파랑만 
										if(visit[nr][nc]==false && colorMap[nr][nc]=='B'){//방문안했고 파랑이면
											q.add(new int[] {nr,nc});
											visit[nr][nc]=true;
										}
									}else {//아닌 경우에는 빨강,초록 둘 다 
										if(visit[nr][nc]==false && colorMap[nr][nc]!='B'){//방문안했고 파랑이 아니면
											q.add(new int[] {nr,nc});
											visit[nr][nc]=true;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<N) return true;
		return false;
	}
	
}
