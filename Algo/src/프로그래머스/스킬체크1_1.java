package 프로그래머스;

public class 스킬체크1_1 {
	public static void main(String[] args) {
		System.out.println(solution(626331));
	}
    public static int solution(long num) {
        int answer = 0;
        while(answer<=500){
        	if(num==1){
        		return answer;
        	}
        	if(num%2==0) {
        		num/=2;
        	}
        	else if(num%2!=0) {
        		num*=3;
        		num+=1;
        	
        	}
        	answer++;
        }
        if(answer==501) answer=-1;
        return answer;
    }
}
