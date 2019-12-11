package CodingStudy;

import java.util.LinkedList;
import java.util.Queue;
/*
 * 시작 단어부터 변환가능한 모든 단어들에 대해 변환한다.
 * 가능한 모든 단어들을 변환할 것이고, 변환 시키는 최소의 방법을 구하는 것이므로
 * BFS 방식으로 구함
 * 
 */
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
        wordLength = begin.length();//단어의 길이
        visit = new boolean[wordSize]; //단어의 개수
        q.add(begin); //시작 단어 
        while(!q.isEmpty()){
        	int size = q.size(); //변환된 단어의 개수
        	for(int i=0;i<size;i++){
        		String currentWord = q.poll(); //변환된 단어
        		if(currentWord.equals(target)){ //변환된 단어가 타겟 단어와 같으면 끝
        			flag = true;
        			break;
        		}
        		for(int j=0;j<wordSize;j++){//모든 단어를 보면서
        			int changeCnt=0;
        			for(int c=0;c<wordLength;c++){//단어 비교
        				if(currentWord.charAt(c)==words[j].charAt(c)) changeCnt++; //같으면 카운팅
        			}
        			if(changeCnt==(wordLength-1) && visit[j]==false){//두 단어가 하나의 문자를 제외하고 나머지 문자들이 같으면 변환 가능함.
        				visit[j]=true;//변환했다는 플래그
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
