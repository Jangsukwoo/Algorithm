package CodingTest;

public class 라4 {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1}; //상 좌 하 우 
	static int[][] globalMaze;
	static int answer;
	static int N;
	public static int solution(int[][] maze) {
		globalMaze = maze.clone();
		N = maze.length;
		if(globalMaze[1][0] ==0) dfs(0,0,0,0,3); //아래로 출발
		else if(globalMaze[0][1] == 0) dfs(0,0,0,3,0);//오른쪽으로 출발
		return answer;
	}
	private static void dfs(int cr, int cc, int move, int currentDir, int left) {
		if(cr== N-1 && cc == N-1) {//맨 끝에 도달했으면
			answer = move;
			return ;
		}
		int nr = cr + dr[left];
		int nc = cc + dc[left]; //왼쪽 체크
		if(rangeCheck(nr,nc) || (!rangeCheck(nr, nc) && globalMaze[nr][nc]==1)){//영역밖(벽) 또는 영역 안쪽에있는데 벽이면
			nr = cr+dr[currentDir];
			nc = cc+dc[currentDir];
			while(true){//제자리 돌기
				if(!rangeCheck(nr, nc) && globalMaze[nr][nc]==0) break; //영역 안쪽, 갈 수 있는 땅
				currentDir--; //오른쪽
				if(currentDir==-1) currentDir = 3;
				nr = cr+dr[currentDir];
				nc = cc+dc[currentDir];
			}
			left = (currentDir+1)%4;
			dfs(nr,nc,move+1,currentDir,left);
		}else {//영역 안쪽이고 갈수 있으면 
			dfs(nr,nc,move+1,left,(left+1)%4);
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=0 && nc>=0 && nr<N && nc<N) return false; //영역 안쪽에있으면
		return true; //벽
	}
	
	public static void main(String[] args) {
		int[][] maze = {{0, 1, 0, 0, 0, 0}, {0, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 1, 0}, {0, 1, 1, 1, 1, 0}, {0, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 0}};
		solution(maze);
		System.out.println(answer);
	}
}