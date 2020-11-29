package CodingTest;

public class 느흐느고도4 {
	public static void main(String[] args) {
		System.out.println(solution("26227174957722514961366"));
	}
	public static String solution(String card_number) {
		String type = "";
		String answer= "";
		int[] numbers = new int[card_number.length()];
		for(int i=0;i<card_number.length();i++) {
			numbers[i] = Character.getNumericValue(card_number.charAt(i));
		}
		if(card_number.length()%2==0) type = "even";
		else type = "odd";
		switch (type) {
		case "even":
			for(int i=0;i<card_number.length();i+=2) numbers[i]*=2;
			break;
		case "odd":
			for(int i=1;i<card_number.length();i+=2) numbers[i]*=2;
			break;
		}
		int sum = getLuhnValue(numbers);
		if(sum%10==0) answer="VALID";
		else answer = "INVALID";
		return answer;
	}
	private static int getLuhnValue(int[] numbers) {
		/*
		 * Luhn 값 구하기
		 */
		int luhnValue = 0;
		for(int i=0;i<numbers.length;i++) {
			if(numbers[i]>=10) {//두자리면
				luhnValue+=numbers[i]/10 + numbers[i]%10;
			}else luhnValue+=numbers[i];
		}
		return luhnValue;
	}
}
