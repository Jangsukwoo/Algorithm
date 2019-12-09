package CodingStudy;

import java.util.LinkedList;
import java.util.Queue;

public class 단어변환 {
	static boolean[] visit;
	static Queue<String> q = new LinkedList<String>();
	static int answer;
	static int wordSize;
	static int wordLength;
	static boolean flag;
	public static void main(String[] args) {
		solution("hit","hhh",new String[]{"hhh","hht"});
	}
    public static int solution(String begin, String target, String[] words) {
        wordSize = words.length;
        wordLength = begin.length();
        visit = new boolean[wordSize];
        q.add(begin);
        while(!q.isEmpty()){
        	int size = q.size();
        	for(int i=0;i<size;i++){
        		String currentWord = q.poll();
        		if(currentWord.equals(target)) {
        			flag = true;
        			break;
        		}
        		for(int j=0;j<wordSize;j++){
        			int changeCnt=0;
        			for(int c=0;c<wordLength;c++) {
        				if(currentWord.charAt(c)==words[j].charAt(c)) changeCnt++;
        			}
        			if(changeCnt==(wordLength-1) && visit[j]==false){//두 문자가 같으면 변환 가능함.
        				visit[j]=true;
        				q.add(words[j]);
        			}
        		}
        	}
        	if(flag) break;
        	answer++;
        }
        if(!flag) answer=0;
        System.out.println(answer);
        return answer;
    }
}
