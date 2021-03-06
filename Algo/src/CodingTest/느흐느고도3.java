package CodingTest;

import java.util.HashMap;


public class 느흐느고도3 {
	static int minDiff = Integer.MAX_VALUE;
	public static void main(String[] args) {
		System.out.println(solution("gggggggooooooodddddddllllllluuuuuuuccckkk",5));
	}
	 public static int solution(String s, int n){
	    	minDiff = Integer.MAX_VALUE;
	    	HashMap<Character,Integer> countMap = new HashMap<Character, Integer>();
	    	for(int i=0;i<s.length();i++) {
	    		char character = s.charAt(i);
	    		if(countMap.containsKey(character)) {
	    			int count = countMap.get(character);
	    			countMap.put(character, count+1);
	    		}else countMap.put(character, 1);
	    	}
	    	for(int i=0;i<=n;i++) recursive(countMap,n);
	    	
	        return minDiff;
	    }
		private static void recursive(HashMap<Character, Integer> countMap, int n) {
			if(n==0) {////n만큼 소진 후 거친정도의 최소값 갱신해보기
				int min = Integer.MAX_VALUE;
				int max = 0;
		    	for(Character key : countMap.keySet()){
			    	int count = countMap.get(key);
			    	if(count>0) {
			    		max = Math.max(max,count);
			    		min = Math.min(min,count);
			    	}
			    }
		    	int diff = max-min;
		    	minDiff = Math.min(minDiff,diff);
				return;
			}
	    	for(Character key : countMap.keySet()) {//등장한 알파벳깎아보기
	    		int getCount = countMap.get(key);
	    		if(getCount>0) {
	    	    	int newN = n-getCount;
	    	    	int newCount = 0;
	    	    	if(newN<0) {
	    	    		newCount = Math.abs(newN);
	    	    		newN = 0;
	    	    	}
	    	    	countMap.put(key,newCount);
	    	    	recursive(countMap,newN);//dfs
	    	    	countMap.put(key,getCount);//원상복귀
	    		}
		    }
		}
}
