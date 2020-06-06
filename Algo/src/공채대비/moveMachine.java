package 공채대비;

public class moveMachine {
	/*
	 * 한방향 최대 500
	 * 
	 * 강한연결요소 ..?
	 */
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};//상 우 하 좌 
	static boolean[][] map;
	public static void main(String[] args) {
		System.out.println(solution(new String[]{"U", "U", "R", "D", "L", "L", "L", "U", "R", "D", "D", "D", "L", "U", "R", "R", "R", "D", "L", "U"}));

		for(int i=1;i<=25;i++) {
			long result=1;
			System.out.println(i);
			for(int j=i;j>=1;j--) {
				result*=j;
			}
			System.out.println(result);
		}
	}
    public static int solution(String[] moves) {
        int answer = 0;
        map = new boolean[1001][1001];
        StringBuilder sb = new StringBuilder();
        int currentRow = 500;
        int currentCol = 500;//첫 좌표 500으로 지정
        int nr = currentRow;
        int nc = currentCol;
        map[currentRow][currentCol] = true;
        for(int move=0;move<moves.length;move++){
        	sb.append(moves[move]);
        	switch (moves[move]){
			case "U":
				nr+=dr[0];
				nc+=dc[0];
				break;
			case "R":
				nr+=dr[1];
				nc+=dc[1];
				break;
			case "D":
				nr+=dr[2];
				nc+=dc[2];
				break;
			case "L":
				nr+=dr[3];
				nc+=dc[3];
				break;
        	}
        	map[nr][nc] = true;
        }
        for(int row=0;row<1000;row++) {
        	for(int col=0;col<1000;col++) {
        		if(map[row][col] && map[row+1][col] && map[row][col+1] && map[row+1][col+1]) answer++;
        	}
        }
        int ans = 0;
        
        return answer;
    }
}
