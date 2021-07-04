package CodingTest;

/*
 * ㄴㅇㅂ ㅇㅌ 코드챌린지 3
 */
public class 네웹3 {
	public static void main(String[] args) {
		System.out.println(solution("aabcbcd","abc"));
		System.out.println(solution("aaaaabbbbb","ab"));
	}
    public static int solution(String s, String t) {
        int result = -1;
        int slice = 0;
        while(true) {
        	if(s.contains(t)) {
        		int idx = s.indexOf(t);
        		String firstString = s.substring(0,idx);
        		String secondString = s.substring(idx+t.length(),s.length());
        		String nextString = firstString+secondString;
        		s = nextString;
        		slice++;
        		result = slice;
        	}else break;
        }
        return result;
    }
}
