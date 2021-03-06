package 브루트포스;

import java.util.Scanner;

/*
 * 한수?
 * 어떤 양의 정수 X의 자리수가 등차수열인 것을 말함
 * 입력값 N에 대해
 * 1보다 크거나 같고 N보다 작거나 같은 한수의 개수 출력
 * N Size <= 1000 
 */
public class 한수 {
	static int N;
	static int answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for(int i=1;i<=N;i++){
			boolean flag = false;
			if(i>10) {//두자리수부터 탐색의 대상
				String series = Integer.toString(i); //수열
				//공차 d를 초항과 다음항으로부터 먼저 구한다.
				int d = Character.getNumericValue(series.charAt(1))-Character.getNumericValue(series.charAt(0));
				
				for(int j=0;j<(series.length()-1);j++) {
					int tmp = Character.getNumericValue(series.charAt(j+1))-Character.getNumericValue(series.charAt(j));
					if(tmp==d)continue;//공차랑 같으면 진행
					else {//공차랑 다르면 멈추고 flag state 변경 
						flag = true;
						break;//그만보기 
					}
				}
				if(flag==false) answer++;//flag가 그대로면 등차수열을 만족하는 경우로 answer 카운팅
			}
			else answer++;//한자리수는 무조건 카운팅
		}
		System.out.println(answer);
	}
}
