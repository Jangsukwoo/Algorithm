package CodingTest;

public class FindX{
	static int[] dr = {-1,-1,1,1};
	static int[] dc = {-1,1,1,-1};
	static int N;
	static int[][] visit;
	public static void main(String[] args) {
		String[] board2 = {"ABCBA","DABAG","EBABH","FAJAI","AKLMA"};
		solution(board2);
	}
    public static int solution(String[] board) {  	
    	N = board.length;
    	int maxXlength = 0;
    	int multi=1;
    	boolean possible = false;
    	char[][] map = new char[N][N];
    	for(int i=0;i<N;i++) map[i] = board[i].toCharArray();
    	for(int row=0;row<N;row++) {
    		for(int col=0;col<N;col++){
    			multi=1;
    			visit = new int[N][N];
    			while(true){
    				boolean isPossibleX = true;
    				for(int dir=0;dir<4;dir++){
    					int armR = row+dr[dir]*multi;
    					int armC = col+dc[dir]*multi;
    					if(rangeCheck(armR,armC)){//영역 만족하고
    						if(map[armR][armC]==map[row][col]){//같으면
    							visit[armR][armC]=9;
    							continue;
    						}else{//같지 않으면
    							isPossibleX = false;
    							break;
    						}
    					}else{//영역 만족하지 않으면 
    						isPossibleX=false;
    						break;
    					}
    				}
    				if(isPossibleX){//4방향 봤는데 true면
    					multi++;
    					possible=true;
    				}else{//아니면 x를 못만든 것이므로 
    					break;
    				}
    			}
    			maxXlength = Math.max(maxXlength,multi);
    		}
    	}
        int answer = 1+((maxXlength-1)*2);
        System.out.println(+answer);
        return answer;
    }
	private static boolean rangeCheck(int armR, int armC) {
		if(armR>=0 && armR<N && armC>=0 && armC<N) return true;
		return false;
	}
}
