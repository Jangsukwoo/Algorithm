package CodingTest;

import java.util.Arrays;

public class 이슷20_1 {
	public static void main(String[] args) {
		int[] answer = solution(new String[]{"0101-0101-0102-0102", "3285376499342453", "3285-3764-99342453", "328537649934245", "3285376499342459", "3285-3764-9934-2452"});
		System.out.println(Arrays.toString(answer));
	}
	public static int[] solution(String[] card_numbers) {
		int[] answer = new int[card_numbers.length];
		
		for(int test=0;test<card_numbers.length;test++) {
			String card_number_string = card_numbers[test];
			int[] numbers = new int[16];
			int luhnValue = 0;
			if(cardFormatVerification(card_number_string,numbers)){//카드 번호 형식 검증
				luhnValue = getLuhnValue(numbers);
				if(luhnValue%10==0) answer[test] = 1;
				else answer[test] = 0;
			}else {
				answer[test] = 0;//카드 번호 형식이 유효하지 않음
			}
		}
		return answer;
	}
	private static int getLuhnValue(int[] numbers) {
		/*
		 * Luhn 값 구하기
		 */
		int evenSum = 0;
		int oddSum = 0;
		int value=0;
		int luhnValue = 0;
		for(int i=0;i<numbers.length;i++) {
			if(i%2==0) { //짝수면 x2
				value = numbers[i]*2;
				if(value>=10) { //두자리면
					value = value/10 + value%10;
					evenSum+=value;
				}else evenSum+=value; //한자리면 그냥 더함
			}else { //홀수면 그냥 더함
				oddSum+=numbers[i];
			}
		}
		luhnValue = evenSum+oddSum;
		return luhnValue;
	}
	private static boolean cardFormatVerification(String card_number_string, int[] numbers) {
		/*
		 * 형식검증
		 */
		int idx=0;
		if(card_number_string.length()>19) return false; //19자리 넘어가면 형식에 안맞음
		else if(card_number_string.length()==19) { //19자리면 확인해본다.
			for(int i=0;i<card_number_string.length();i++) {
				if((i==4 || i==9 || i==14)) {
					if(card_number_string.charAt(i)=='-') continue;
					else return false;
				}
				else {
					if(card_number_string.charAt(i)>='0' && card_number_string.charAt(i)<='9') {
						numbers[idx] = Character.getNumericValue(card_number_string.charAt(i)); //담음
						idx+=1;
						continue;
					}
					else return false;
				}
			}	
			return true;
		}else if(card_number_string.length()==16){//16자리면 확인해본다.
			for(int i=0;i<card_number_string.length();i++) {
				if(card_number_string.charAt(i)>='0' && card_number_string.charAt(i)<='9') {
					numbers[idx] = Character.getNumericValue(card_number_string.charAt(i)); //담음
					idx+=1;
				}
				else return false;
			}
		}else return false; //19자리도 16자리도 아니면 false
		return true;
	}
}
