package 프로그래머스;


public class 스첵2_1 {
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}}));
		System.out.println(solution(new int[][] {{0,0,1,1},{1,1,1,1}}));
	}
    public static int solution(int [][]board)
    {
        int answer = 1234;
        int rowLength = board.length;
        int colLength = board[0].length;
        int minSide = Math.min(rowLength,colLength);
        boolean find = false;
        
        for(int side = minSide; side>=1 ;side--) {
        	for(int row=0;row<(row+side);row++) {
        		boolean impossible = false;
        		for(int col=0;col<(col+side);col++) {
            		if(board[row][col]==1) {
            			continue;
            		}else {
            			impossible = true;
            			break;
            		}
            	}
            	if(impossible) break;
            }
        }
        
        return answer;
    }
}
