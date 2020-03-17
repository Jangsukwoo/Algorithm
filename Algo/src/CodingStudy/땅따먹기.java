package CodingStudy;
/*
 * N행 4열 
 */
public class 땅따먹기 {
	static int[][] dp;
	static int N;
	public static void main(String[] args) {
		int[][] table = new int[][] {{1,2,3,5},{5,6,7,8},{4,3,2,1}};
		solution(table);
		view();
	}
    private static void view() {
		for(int i=0;i<N;i++){
			for(int j=0;j<4;j++){
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static int solution(int[][] land) {
    	int answer = 0;
    	N = land.length;
    	dp = new int[N][4];
    	dp[0][0] = land[0][0];
    	dp[0][1] = land[0][1];
    	dp[0][2] = land[0][2];
    	dp[0][3] = land[0][3];
    	for(int i=1;i<N;i++){
    		for(int j=0;j<4;j++){//첫번째 자리값부터 두번쨰 자리값
    			for(int k=0;k<4;k++) {
    				if(j!=k){//j랑k가 다른 경우(같은 열에서 내려오지 않은 경우에 대해서
    					dp[i][j] = Math.max(land[i][j]+dp[i-1][k],dp[i][j]);
    				}
    			}
    		}
    	}
    	for(int i=0;i<4;i++) {
    		if(answer<dp[N-1][i]) answer=dp[N-1][i];
    	}
        return answer;
    }
}
