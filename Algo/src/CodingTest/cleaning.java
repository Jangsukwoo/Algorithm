package CodingTest;

public class cleaning {
	
	static int[] dr = {0,1};
	static int[] dc = {1,0};//우 하
	static int[][] memo;
	static int R,C;
	public static void main(String[] args) {
		System.out.println(solution(3,3,new int[][] {{2,0,0},{2,0,0},{2,2,2}}));
		System.out.println(solution(1,2,new int[][] {{0,1}}));
	}

	private static int solution(int N, int M, int[][] map) {
		memo = new int[N][M];
		R = N;
		C = M;
		memo[0][0]+=map[0][0];//초항
		dfs(0,0,map);
		//view();
		return memo[R-1][C-1];
	}

//	private static void view() {
//		for(int row=0;row<R;row++) {
//			for(int col=0;col<C;col++) {
//				System.out.print(memo[row][col]+" ");
//			}
//			System.out.println();
//		}
//	}

	private static void dfs(int cr, int cc, int[][] map) {
		if(cr==(R-1) && cc==(C-1)) return ; 
		int nr = cr;
		int nc = cc;
		
		for(int dir=0;dir<2;dir++) {
			nr = cr+dr[dir];
			nc = cc+dc[dir];
			if(rangeCheck(nr,nc)) {
				if(memo[cr][cc]+map[nr][nc]>memo[nr][nc]) {
					memo[nr][nc] = memo[cr][cc]+map[nr][nc];
					dfs(nr,nc,map);
				}
			}
		}
		
		
	}

	private static boolean rangeCheck(int nr, int nc) {
		if(nr<R && nc<C) return true;
		return false;
	}
}
