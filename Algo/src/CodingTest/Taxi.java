package CodingTest;
/*
 * hyundai
 */
public class Taxi {
	static int n = 10;
	static int[] P = {9,9,9,9,9,9,9,9,9,1};
	public static void main(String[] args) {
		int a = solution(n,P);
		System.out.println(a);
	}
	private static int solution(int n, int[] p) {
		int min = Integer.MAX_VALUE;
		int sum = 0;
		int cnt=0;
		int size = p.length;
		int[] dp = new int[size];
		for(int day=0;day<size;day++){
			if(p[day]<min){
				cnt++;
				dp[day] = 1;
				min = p[day];
			}
		}
		
		if(cnt==1) {
			sum = p[0]*size;
		}
		else {
			int mark=0;
			for(int i=1;i<size;i++){
				if(dp[i]==1){//1나오면
					sum+=(i-mark)*p[mark];
					mark = i;
				}
			}
			sum+=(10-mark)*p[mark];
		}

		return sum;
	}
}
