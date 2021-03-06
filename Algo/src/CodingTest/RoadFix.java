package CodingTest;
/*
 * 2020 상 라인
 */
public class RoadFix {
	public static void main(String[] args) {
		System.out.println(solution("111011110011111011111100011111",3));
	}


	public static int solution(String road, int n) {
		int answer = 0;
		int length = 0;
		int startIdx = 0; 
		int cnt=0;
		int used = 1;
		int k=0;
		for(int i = 0; i < road.length();) {
			if(i == used) {
				for(k = i; k < road.length() - 1; k++) {
					if(road.charAt(k) == '0' && road.charAt(k + 1) == '1') {
						i = k + 1;
						break;
					}
				}
				if(k==road.length() - 1) {
					break;
				}
			} else used = i;
			
			cnt = 0;
			length = 0;
			startIdx = Integer.MAX_VALUE;
			for(int j = i; j < road.length(); j++) {
				if(road.charAt(j) == '0') {
					if (cnt < n) {
						cnt++;
					} else {
						i = startIdx;
						break;
					}
				} else {
					if (j > 0 && road.charAt(j - 1) == '0' && startIdx == Integer.MAX_VALUE && i != j) {
						startIdx = j;
					}
					if(j == road.length() - 1) {
						i = Integer.MAX_VALUE;
					}
				}
				length++;
			}
			answer = Math.max(answer, length);
		}
		return answer;
	}
}

