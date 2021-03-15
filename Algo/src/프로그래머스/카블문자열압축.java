package 프로그래머스;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 카블문자열압축 {
	static int minLength = Integer.MAX_VALUE;
	static Queue<Character> q;
	public static void main(String[] args) {
//		solution("aabbaccc");
//		solution("ababcdcdababcdcd");
		solution("abcabcdede");
	}
    public static int solution(String s) {
    	/*
    	 * 1<=s<=1000
    	 */
    	
        minLength = s.length();
        int length = s.length();
        q = new LinkedList<Character>();
        ArrayList<String> wordList = new ArrayList<String>();
        
        for(int cut=1;cut<=length;cut++) {
        	queueInit(s);
        	wordList.clear();
        	int popCount = 0;
        	String word = "";
        	
        	while(!q.isEmpty()) {
        		popCount++;
        		char popChar = q.poll();
        		word+=popChar;
        		if(popCount == cut) {
        			wordList.add(word);
        			word="";
        			popCount=0;
        		}
        	}
        	if(word!="") wordList.add(word);
        	//System.out.println("cut개수 : "+cut);
        	//for(String data : wordList) System.out.println(data);
        	int wordListLength = wordList.size();
        	int cnt=1;
        	String compactionString = "";
        	
        	for(int i=0;i<wordListLength-1;i++) {
      
        		if(i==wordListLength-2){ //맨 마지막 케이스의 경우
        			if(wordList.get(i).equals(wordList.get(i+1))){//같으면
        				cnt++;
            			String num_string = Integer.toString(cnt);
        				compactionString+=num_string;
        				compactionString+=wordList.get(i);
            		}else {//같지 않은경우
            			if(cnt==1) {
            				compactionString+=wordList.get(i);
            				compactionString+=wordList.get(i+1);
            			}else if(cnt>1) {
                  			String num_string = Integer.toString(cnt);
            				compactionString+=num_string;
            				compactionString+=wordList.get(i);
            				compactionString+=wordList.get(i+1);
            			}
            		}
        			//System.out.println(compactionString+" 길이는 "+compactionString.length());
        			minLength = Math.min(minLength,compactionString.length());
        		}else {
        	  		if(wordList.get(i).equals(wordList.get(i+1))){//같으면
            			cnt++;
            		}else {//같지 않은 경우
            			if(cnt==1) {//한개짜리면
            				compactionString+=wordList.get(i);
            			}else {//한개가 아니면
            				String num_string = Integer.toString(cnt);
            				compactionString+=num_string;
            				compactionString+=wordList.get(i);
            				cnt=1;
            			}
            		}
        		}
        	}
        
        	
        }
    	//System.out.println(minLength);
        return minLength;
    }
	private static void queueInit(String s) {
    	q.clear();
    	for(int i=0;i<s.length();i++) q.add(s.charAt(i));
	}
}
