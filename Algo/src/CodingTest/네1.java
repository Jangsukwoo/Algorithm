package CodingTest;

public class 네1{
	public static void main(String[] args) {
		System.out.println("답 :"+solution("dabc","abc"));
	}
    public static String solution(String m, String k){
    	StringBuilder sb = new StringBuilder();
    	int mLength = m.length();
    	int kLength = k.length();
    	int pointer = 0;
    	for(int i=0;i<mLength;i++) {
    		if(pointer==kLength) sb.append(m.charAt(i));
    		else {
    			if(m.charAt(i)==k.charAt(pointer)) pointer++;
        		else sb.append(m.charAt(i));
    		}
    	}
          String answer = sb.toString();
          return answer;
  }
}