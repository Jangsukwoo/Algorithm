package CodingTest;

public class 네하상코딜2 {
	public static void main(String[] args) {
		solution(98765);
		solution(670);
		solution(0);
		solution(-1234);
	}
	public static int solution(int N) {
		String result="";

		String num_string = Integer.toString(N);

		boolean insertFlag = false;

		if(N>=0) { //N이 0또는 양수면
			for(int i = 0; i<num_string.length(); i++) { //길이만큼
				if(insertFlag){//삽입 했으면 그냥 이어 붙이기
					result+=num_string.charAt(i);
					continue;
				}
				if(num_string.charAt(i) < '5') { //5보다 작은 경우
					result+='5';
					insertFlag = true;
					result+=num_string.charAt(i);
				}else result+=num_string.charAt(i); //5랑 같거나 크면
			}
		}else {
			result += '-';
			for(int i = 1; i<num_string.length(); i++) {
				if(insertFlag){//삽입 했으면 그냥 이어 붙이기
					result+=num_string.charAt(i);
					continue;
				}
				if(num_string.charAt(i) > '5') { //5보다 큰경우
					result+='5';
					insertFlag = true;
					result+=num_string.charAt(i);
				}else result+=num_string.charAt(i); //5랑 같거나 작으면

			}
		}
		if(insertFlag==false) { //삽입 못했으면
			result+='5';
		}
		int ans = Integer.parseInt(result);
		System.out.println(ans);
		return ans;
	}
}