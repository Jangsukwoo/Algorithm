package 공채대비;

public class FactorialZeroTailCount {
	public static void main(String[] args) {
		System.out.println(solution(125));
	}

	private static int solution(int n){
		int answer=0;
		int temp = 0;
		int div = 5;
		while(true) {
			temp=n/div;
			if(temp!=0) answer+=temp;
			else break;
			div*=5;
		}
		return answer;
	}
}
