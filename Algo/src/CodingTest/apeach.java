package CodingTest;

import java.util.HashMap;
import java.util.HashSet;

public class apeach {
	static HashSet<String> gemSet;
	static HashMap<String,Integer> gemMap;
	static int maxsize;
	public static void main(String[] args) {
		//solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
		solution(new String[]{"AA", "AB", "AC", "AA", "AC"});
		//solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
	}
	public static int[] solution(String[] gems){
		gemSet = new HashSet<String>();
		gemMap = new HashMap<String, Integer>();
		for(String data : gems) {
			gemSet.add(data);
			gemMap.put(data,0);
		}
		maxsize= gemSet.size();

		int[] answer =new int[2];
		int size=0;
		int startIdx = 0;
		int endIdx = 0;
		int minLength = Integer.MAX_VALUE;
		/*
		 * 적어도 한개 이상 포함하는 가장 짧은 구간 
		 */
		 while(true){
			 if(size==maxsize){
//				 System.out.println(size);
//				 System.out.println("같은지점");
//				 System.out.println(startIdx+" "+endIdx);
				 while(true){//스타트 포인터 앞으로 당겨본다
					 String data2 = gems[startIdx];
					 int value2 = gemMap.get(data2);
//					 System.out.println("꺼낸 데이터랑 밸류");
//					 System.out.println(data2+" "+value2);
					 if(value2==1){//1이면 다시 찾으로 뒤로 가야함 
						gemMap.put(data2, value2-1);
						size--;
						if((endIdx-startIdx-1)<minLength) {
							answer[0] = startIdx;
							answer[1] = endIdx;
						}
						minLength = Math.min(minLength,(endIdx-startIdx-1));
						startIdx++;
						break;
					 }else {			 
						gemMap.put(data2, value2-1);
						minLength = Math.min(minLength,(endIdx-startIdx-1));
						if((endIdx-startIdx-1)<minLength) {
							answer[0] = startIdx;
							answer[1] = endIdx;
						}
						//System.out.println(startIdx+" "+endIdx);
						startIdx++;
					 }
				 }
			 }
			 if(endIdx==gems.length) {
				 break;
			 }
			// System.out.println("최소길이"+minLength);
			 String data = gems[endIdx];
			 int value = gemMap.get(data);
			 gemMap.put(data,value+1);
			 endIdx++;
			 if(value==0) size++;
		 }
		 answer[0]+=1;
		// System.out.println((answer[0]+1)+" "+(answer[1]));
		 return answer;
	}
}
