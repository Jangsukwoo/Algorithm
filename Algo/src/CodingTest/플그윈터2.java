package CodingTest;

import java.util.LinkedList;
import java.util.Queue;

public class 플그윈터2 {
	static boolean flag = false;
	public static void main(String[] args) {
		//solution("z","y",0);
		String a = "";
		String b = "";
        char[] alphabetSet = new char[27];
        for(int i=1;i<=26;i++) alphabetSet[i] = (char) ('a'+ i-1);
		for(int i=1;i<=1000;i++){
			int c = (i%25)+1;
			a+=(char)('a'+c);
			b+=(char)('a'+c);
			a+="a";
			b+="b";
		}
		System.out.println(a);
		System.out.println(b);
		for(int i=-1000;i<=1000;i++) {
			solution(a,b,i);
			if(flag) break;
		}
		System.out.println("끝끝");
	}
    public static String solution(String a, String b, int rotation) {
    	String encrypted_text = String.copyValueOf(a.toCharArray());
    	String key = String.copyValueOf(b.toCharArray());
    	//System.out.println("?");
        String answer = "";
        String rerotation_text = "";
        char[] alphabetSet = new char[27];
        for(int i=1;i<=26;i++) alphabetSet[i] = (char) ('a'+ i-1);
        int shiftCnt = Math.abs(rotation);
        Queue<Character> q = new LinkedList<Character>();
        if(rotation<0) {//음수였으니 오른쪽으로
            for(int i=encrypted_text.length()-1; i>=0;i--){
            	q.add(encrypted_text.charAt(i));
            }
    		for(int i=0;i<shiftCnt;i++) {
    			char popChar = q.poll();
    			q.add(popChar);
    		}
    		
    		while(!q.isEmpty()) rerotation_text+=q.poll();
    	}else if(rotation==0) {
    		rerotation_text = String.copyValueOf(encrypted_text.toCharArray());
    	}else if(rotation>0) {//양수였으니 왼쪽으로
            for(int i=0; i<encrypted_text.length();i++){
            	q.add(encrypted_text.charAt(i));
            }
    		for(int i=0;i<shiftCnt;i++) {
    			char popChar = q.poll();
    			q.add(popChar);
    		}
    		while(!q.isEmpty()) rerotation_text+=q.poll();
    	}
        System.out.println(rerotation_text);
        System.out.println(key);
        for(int i=0;i<key.length();i++) {
        	int decrptedKey = key.charAt(i)-'a' + 1;
        	//System.out.println(decrptedKey+" "+"번칸 뜀");
        	int getASCII = rerotation_text.charAt(i)-'a' - decrptedKey;
        	//System.out.println(key.charAt(i)+" "+rerotation_text.charAt(i));
        	if(getASCII<0) getASCII = 27+getASCII;
        	else getASCII+=1;
        	if(getASCII==0) {
        		//System.out.println(key.charAt(i)+" "+rerotation_text.charAt(i));
        		flag = true;
        		System.out.println("도중에끝남");
        		return "";
        	}
        	answer+= alphabetSet[getASCII];
        }
        System.out.println(answer);
        return answer;
    }
}
