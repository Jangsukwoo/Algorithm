package CodingTest;

public class 현카하2 {

	public static void main(String[] args) {
		System.out.println(solution(6,10,0));
		System.out.println(solution(11,20,210));
		System.out.println(solution(16,30,5108));
	}
	public static String solution(int left, int right, int offset) {
		String answer = "";
		StringBuilder sb = new StringBuilder();
		while(true) {
			String offset_string = Integer.toString(offset);
			int offset_string_size = offset_string.length();
			String number_string = "";
			if(offset_string_size==1) {
				number_string = getString(offset);
				sb.append(number_string);
			}else if(offset_string_size==2) {
				if(offset>19){ //19보다 크면
					int number = Character.getNumericValue(number_string.charAt(0));
					number_string = getString(number);
					sb.append(number_string);
					number = Character.getNumericValue(number_string.charAt(1));
					number_string = getString(number);
					sb.append(number_string);
				}else{
					number_string = getString(offset);
					sb.append(number_string);
				}
			}else {
				int number = 0;
				int firstNumber = 0;
				int secondNumber = 0;
				for(int i=0;i<=(offset_string_size-1);i++) {
					firstNumber = Character.getNumericValue(offset_string.charAt(i));
					if(firstNumber>=2){//숫자가 2보다 크면 그냥 때려넣음
						sb.append(getString(firstNumber));
					}else if(firstNumber==1){ //숫자가 1이면
						secondNumber = Character.getNumericValue(offset_string.charAt(i+1));
						number = firstNumber*10 + secondNumber;
						sb.append(getString(number));
						i++;
					}else if(firstNumber==0) {
						sb.append(0);
					}
				}
			}
			if(sb.toString().length()>=right) break;
			offset++;
		}
		answer = sb.toString().substring(left-1,right).toString();
		return answer;
	}
	private static String getString(int number) {
		String number_string = "";
		switch (number) {
		case 0:
			number_string = "zero";
			break;
		case 1:
			number_string = "one";
			break;
		case 2:
			number_string = "two";
			break;
		case 3:
			number_string = "three";
			break;
		case 4:
			number_string = "four";
			break;
		case 5:
			number_string = "five";
			break;
		case 6:
			number_string = "six";
			break;
		case 7:
			number_string = "seven";
			break;
		case 8:
			number_string = "eight";
			break;
		case 9:
			number_string = "nine";
			break;
		case 10:
			number_string = "ten";
			break;
		case 11:
			number_string = "eleven";
			break;
		case 12:
			number_string = "twelve";
			break;
		case 13:
			number_string = "thirteen";
			break;
		case 14:
			number_string = "fourteen";
			break;
		case 15:
			number_string = "fifteen";
			break;
		case 16:
			number_string = "sixteen";
			break;
		case 17:
			number_string = "seventeen";
			break;
		case 18:
			number_string = "eighteen";
			break;
		case 19:
			number_string = "nineteen";
			break;
		}
		return number_string;
	}
}
