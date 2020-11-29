package CodingTest;

public class 에스티4 {
	public static void main(String[] args) {
		System.out.println(solution("abcde",new int[][]{{1,3},{1,4},{4,5}}));
		System.out.println(solution("abcde",new int[][]{{4,5},{1,2},{3,3}}));
	}
    public static String solution(String S, int[][] interval) {
        String answer = "";
        
        char[] characterArray = S.toCharArray();
        
        for(int i=0;i<interval.length;i++) {
        	
        	int startIdx = interval[i][0]-1;
        	int endIdx = interval[i][1]-1;
        	
        	char[] intervalArray = getIntervalArray(characterArray,startIdx,endIdx); //구간 배열 get
        	
        	for(int j=intervalArray.length-1;j>=0;j--) {//뒤에서부터 넣어준다.
        		
        		char swapCharacter = intervalArray[j];
        		characterArray[startIdx++] = swapCharacter;
        	}
        	
        }
        
        answer = String.copyValueOf(characterArray);
        
        return answer;
    }
	private static char[] getIntervalArray(char[] characterArray, int startIdx, int endIdx) {
	
		char[] intervalArray = 	new char[endIdx-startIdx+1];
		
		int idx = 0;
		
		for(int i=startIdx;i<=endIdx;i++) {
			intervalArray[idx] = characterArray[i];
			idx++;
		}
		return intervalArray;
	}
}
