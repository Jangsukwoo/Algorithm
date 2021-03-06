package CodingTest;
/*
 * 2020상 라인
 */
public class Cheating {
	public static void main(String[] args) {
		System.out.println(solution("11111", new String[] {"33333", "11111", "24553", "24553"}));
	}
	public static int solution(String answer_sheet, String[] sheets) {
		int answer = 0;
		//가장 높은 부정행위 지수
		//지수 = 의심문항수 + 가장 긴 연속 의심문항수^2
		//응시자는 둘 
		for(int i=0;i<sheets.length;i++){
			for(int j=0;j<sheets.length;j++){
				if(i!=j){//자기 와 다른 학생에 대해서 
					int doubt=0;
					int continuityDoubt = 0;
					int maxCotinuityDoubt = 0;
					int cheatingValue=0;
					for(int k=0;k<answer_sheet.length();k++){
						if(sheets[i].charAt(k)==sheets[j].charAt(k) 
								&& sheets[i].charAt(k)!=answer_sheet.charAt(k)
								&& sheets[j].charAt(k)!=answer_sheet.charAt(k)){
							//답을 똑같이 썼는데 오답이다.
							doubt++;//의심문항
							continuityDoubt++;//연속된 의심문항 ++;
						}else {
							maxCotinuityDoubt = Math.max(continuityDoubt,maxCotinuityDoubt);
							continuityDoubt=0;//연속의심문항 초기화
						}
					}
					maxCotinuityDoubt = Math.max(continuityDoubt,maxCotinuityDoubt);
					cheatingValue = (int) (doubt + Math.pow(maxCotinuityDoubt,2));
					answer = Math.max(cheatingValue,answer);
				}
			}
		}
		return answer;
	}
}
