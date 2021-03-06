package CodingTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class 라3 {
	
	static HashMap<String, Boolean> map;
	static Queue<Data> q = new LinkedList<Data>();
	static class Data{
		String stringNum;
		int num;
		public Data(String stringNum, int num) {
			this.stringNum = stringNum;
			this.num = num;
		}
	}
	public static int[] bfs(String stringNumber){
	    q.add(new Data(stringNumber,0));
	    map = new HashMap<String, Boolean>();
	    map.put(stringNumber,true);
	    int[] answer = new int[2];
	    while(!q.isEmpty()){
	    	Data currentData = q.poll();
	    	
	    	String currentString = currentData.stringNum;
	    	int currentNumber = currentData.num;
	    	
	        if(currentString.length()==1){
	        	answer[0] = currentData.num;
	        	answer[1] = Integer.parseInt(currentString);
	        	return answer;
	        }
	        
	        for(int i=1; i<currentString.length(); i++){
	            String leftString;
	            String rightString;
	            leftString = currentString.substring(0, i);
	            rightString = currentString.substring(i);
	            
	            if(rightString.charAt(0) == '0') continue;
	            
	            int first, second, sum;
	          
	            first = Integer.parseInt(leftString);
	            second = Integer.parseInt(rightString);
	            sum = first + second;
	            String sumString = Integer.toString(sum);
	            
	            if(map.containsKey(sumString)) continue;
	            
	            map.put(sumString , true);
	            
	            q.add(new Data(sumString,currentNumber+1));
	        }
	    }
	    return answer;
	}

	public static int[] solution(int n){
	    String stringNumber = Integer.toString(n);
	    int[] answer = bfs(stringNumber);
	    return answer;
	}
	
	public static void main(String[] args) {

	}
}
