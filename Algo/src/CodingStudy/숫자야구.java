package CodingStudy;
/*
 * 숫자 야구
 * 숫자는 맞는데 위치가 틀렸을 떄 볼
 * 숫자, 위치가 모두 맞을 때 스트라이크
 * 숫자,위치 모두 틀렸을 때 아웃
 * 숫자는 1~9
 * 111부터 999까지 Data set을 만들고
 * 지워나가는 형식으로 생각
 * 
 * 으로 접근 했으나 풀이법이 잘 떠오르지 않아서
 * 인터넷 참고해서 해결..ㅠ
 * 
 */
public class 숫자야구 {
	public static void main(String[] args) {
		solution(new int[][]{{123,1,1},{356,1,0},{327,2,0},{489,0,1}});
	}
	public static int solution(int[][] baseball) {
		int answer = 0;
		int querySize = baseball.length;
		for(int num=111;num<=999;num++){
			String number = Integer.toString(num);
			if(	number.charAt(0)==number.charAt(1)
					|| number.charAt(1)==number.charAt(2)
					|| number.charAt(2)==number.charAt(0)
					|| number.charAt(0)=='0'
					|| number.charAt(1)=='0'
					|| number.charAt(2)=='0') continue;
			
			for(int query=0;query<querySize;query++) {
				String tryNumber = Integer.toString(baseball[query][0]);
				int strike=0;
				int ball=0;
				if(tryNumber.charAt(0)==number.charAt(0)) strike++;
				if(tryNumber.charAt(1)==number.charAt(1)) strike++;
				if(tryNumber.charAt(2)==number.charAt(2)) strike++; 
				if(strike!=baseball[query][1]) break;//스트라이크 가능성이 없으면 break;
				//스트라이크 가능성이 있음. 
				if(tryNumber.charAt(0)==number.charAt(1)|| tryNumber.charAt(0)==number.charAt(2)) ball++;
				if(tryNumber.charAt(1)==number.charAt(2)|| tryNumber.charAt(1)==number.charAt(0)) ball++;
				if(tryNumber.charAt(2)==number.charAt(1)|| tryNumber.charAt(2)==number.charAt(0)) ball++;
				if(ball!=baseball[query][2]) break;
				if(query==(querySize-1)) answer++;//끝까지 다봄 
			}
		}
		return answer;
	}
}
