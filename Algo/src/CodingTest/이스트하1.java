package CodingTest;

import java.util.Arrays;

public class 이스트하1 {
	public static void main(String[] args) {
		int[] answer = solution(new String[]{"00-0000000000000","0000-0000-0000-0000","3285-3764-9934-2453", "3285376499342453", "3285-3764-99342453", "328537649934245", "3285376499342459", "3285-3764-9934-2452"});
		System.out.println(Arrays.toString(answer));
	}
	public static int[] solution(String[] card_numbers) {
		int[] answer = new int[card_numbers.length];
		for(int test=0;test<card_numbers.length;test++) {
			String card_number_string = card_numbers[test];
		
			int[] numbers = new int[16];
			
			int luhnValue = 0;
			
			if(cardNumberFormatVerification(card_number_string,numbers)){//카드 번호 형식 검증
				
				luhnValue = getLuhnValue(numbers);
			
				if(luhnValue%10==0) answer[test] = 1;
				else answer[test] = 0;
				
			}else answer[test] = 0;//카드 번호 형식이 유효하지 않음
		}
		return answer;
	}
	private static boolean cardNumberFormatVerification(String card_number_string, int[] numbers) {
		/*
		 * 형식검증
		 * 각 자리의 숫자는 0~9
		 * 올바른 형식
		 * 첫번째 형식
		 * XXXX-XXXX-XXXX-XXXX : 19자리
		 * 두번재 형식
		 * XXXXXXXXXXXXXXXX : 16자리
		 */
	
		if(card_number_string.length()==19) { //19자리면 하이픈이 맞는지 검사 

			int saveIdx=0;
			
			if(card_number_string.charAt(4)!='-' 
					&& card_number_string.charAt(9)!='-'
					&& card_number_string.charAt(14)!='-') return false; //하이픈 자리에 하이픈이 없다면 false
			
			for(int i=0;i<card_number_string.length();i++) {
				//하이픈 자리
				if(i==4 || i==9 || i==14) continue;
				if(card_number_string.charAt(i)=='-') return false; //하이픈 자리가 아닌데 하이픈이면 false
				numbers[saveIdx] = Character.getNumericValue(card_number_string.charAt(i)); //담음
				saveIdx++;
			}
			return true;
		}
		
		if(card_number_string.length()==16){//16자리면 하이픈이 없어야한다.
			
			if(card_number_string.contains("-")) return false; //하이픈을 포함하고 있으면 false
			
			for(int i=0;i<card_number_string.length();i++) {
				numbers[i] = Character.getNumericValue(card_number_string.charAt(i)); //담음
			}
			
			return true;
			
		}else return false; //19자리도 16자리도 아니인 경우는 전부 false
	}
	private static int getLuhnValue(int[] numbers) {
		/*
		 * Luhn 값 구하기
		 */
		int evenSum = 0;
		int oddSum = 0;
		int value=0;
		int luhnValue = 0;
		for(int i=numbers.length-1;i>=0;i--) { //가장 오른쪽부터라고 했으니 뒤에서부터 검사해본다.
			if(i%2==0) { //짝수번째 자리는 곱하기 2
 				
				value = numbers[i]*2;
				
 				if(value>=10) { //두자리면
					value = value/10 + value%10; //각 자리수의 값을 더함
					evenSum+=value;
				}else evenSum+=value; //한자리면 그냥 더함
			
			}else { //홀수면 그냥 더함
				oddSum+=numbers[i];
			}
		}
		luhnValue = evenSum+oddSum;
		return luhnValue;
	}
}
