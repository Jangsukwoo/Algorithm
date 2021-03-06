package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 0은 땅
 * 1은 벽
 * 2는 그람
 * 
 * 성입구 0,0
 * 공주 위치 N-1,M-1
 * 
 * int[3]에서
 * 0,1은 row,col
 * 2는 그람 보유 유무
 * 
 * 
6 6 9
0 0 0 0 1 1
0 0 0 0 0 2
1 1 1 0 1 0
0 0 0 0 0 0
0 1 1 1 1 1
0 0 0 0 0 0

6 6 10
0 0 0 0 1 1
0 0 0 0 0 2
1 1 1 0 1 0
0 0 0 0 0 0
0 1 1 1 1 1
0 0 0 0 0 0

????????왜틀리지????

3 4 100
0 0 2 0
1 1 1 1
0 0 0 0

---

차음에 BFS로 한번의 탐색을 통해서 그람의 획득까지 처리해주면 된다고 생각했는데
그람 획득 후 왔던 길 통해서 다시 가도 최단경로로 가는 경우에 대한 처리를 고려하지 못했다.
왜냐면 visit으로 이미 갔던 칸임을 체크했기 때문에
그람을 먹고도 좋은 길로 다시 가지 못했다.
따라서
그람을 먹고 목적지를 가는경우와
그람을 먹든 안먹든 출발지부터 목적지까지 한칸씩 가는 경우
두가지의 경우를 봤어야 했다.
그람을 먹고 목적지까지 단숨에 가는 bfs 하나와
출발지에서 목적지까지 그냥 탐색해서 가는 bfs하나를 돌렸고
flag처리릁 통해 해결할 수 있었다.. 
구현보다는 접근이 잘못되어 오래걸린 문제다.
 */
public class 공주님을구해라 {
	static int N,M,T;//row,col,time
	static int second;
	static int useGram = Integer.MAX_VALUE;
	static int result = Integer.MAX_VALUE;
	static int[][] castle;
	static boolean[][] visit;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상우하좌
	static boolean gramPossible;
	static boolean nonGramPossible;
	static Queue<int[]> q = new LinkedList<int[]>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		castle = new int[N][M];
		visit= new boolean[N][M];
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				castle[row][col] = sc.nextInt();
			}
		}
		gramPossible = false;
		findGram();//그람을 찾고 목적지까지 거리계산해본다.
		result = Math.min(useGram,result);
		nonGramPossible = false;
		BFS();		
		if(gramPossible && nonGramPossible){
			result = Math.min(second,useGram);
			System.out.println(result);
		}else if (nonGramPossible) System.out.println(second);
		else if (gramPossible) System.out.println(useGram);
		else System.out.println("Fail");
	}
	private static void findGram(){ //그람찾고 목적지까지 바로가기
		visit = new boolean[N][M];
		q.add(new int[]{0,0,0});
		visit[0][0] = true;
		int cnt=0;
		while(!q.isEmpty()){ //그람찾기
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRC = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nr = curRC[0]+dr[dir];
					int nc = curRC[1]+dc[dir];
					if(rangeCheck(nr,nc)){//영역 만족하는데
						if(castle[nr][nc]==2 && visit[nr][nc]==false){//그람이 있고 획득할 수 있다.
							cnt++;
							gramPossible=true;//가능
							useGram = cnt+(M-1-nc)+(N-1-nr); //그람을 사용하면 무조건 갈 수 있으니 계산하고 끝낸다.
							if(useGram>T) gramPossible = false;
							return ;
						}else if(castle[nr][nc]==0 && visit[nr][nc]==false){
							visit[nr][nc] = true;
							q.add(new int[] {nr,nc});
						}
					}
				}
			}
			cnt++;
		}
		return;
	}
	private static void BFS(){//그냥 가보기
		q.clear();
		visit = new boolean[N][M];
		q.add(new int[]{0,0,0});
		visit[0][0] = true;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] curRC = q.poll();
				if(curRC[0]==(N-1) && curRC[1]==(M-1)){//공주면 제한시간내에 도달 가능
					nonGramPossible=true;
					return;//끝
				}
				for(int dir=0;dir<4;dir++) {
					int nr = curRC[0]+dr[dir];
					int nc = curRC[1]+dc[dir];
					if(rangeCheck(nr,nc)){//영역 만족하고
						if(castle[nr][nc]!=1 && visit[nr][nc]==false){
							visit[nr][nc] = true;
							q.add(new int[] {nr,nc});
						}
					}
				}
			}
			if(second==T) return;
			second++;
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) return true;
		return false;
	}
}
