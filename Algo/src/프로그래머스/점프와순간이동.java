package 프로그래머스;

public class 점프와순간이동 {
	public static void main(String[] args) {
		System.out.println(solution(5000));
	}
    public static int solution(int n) {
        int ans = 0;
        while(n!=0){
        	//짝수면
        	if(n%2==0)n/=2;
        	else {
        		n-=1; //홀수면 -1
        		ans++;
        	}
        	
        }
        return ans;
    }
}
