package codingtest20하;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class 쿠3 {
	/*
	 * 기준반복회수k
	 * 내림차순score
	 * 조작되지 않은 점수의 개수?
	 */
	static int[] differentMemo;
	public static void main(String[] args) {
		solution(3,new int[] {24,22,20,10,5,3,2,1});
		solution(2, new int[] {1300000000,700000000,668239490,618239490,568239490,568239486,518239486,157658638,157658634,100000000,100});
	}
	public static int solution(int k, int[] score) {
		differentMemo = new int[score.length];
		int[] newScore = new int[score.length];
		newScore[0] = score[0];
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		HashSet<Integer> cheatingSet = new HashSet<Integer>();
		int answer = 0;
		
		for(int i=1;i<score.length;i++) {
			newScore[i] = score[i];
			differentMemo[i] = score[i-1]-score[i];
			if(hashMap.containsKey(differentMemo[i])){
				int cnt = hashMap.get(differentMemo[i]);
				hashMap.put(differentMemo[i],cnt+1);
				if(hashMap.get(differentMemo[i])>=k) cheatingSet.add(differentMemo[i]);
			}else hashMap.put(differentMemo[i],1);
		}
		
		for(int i=1;i<score.length;i++) {
			if(cheatingSet.contains(differentMemo[i])){//조작 기준 차이 점수에 포함 될시 
				newScore[i-1] = 0;
				newScore[i] = 0;
			}
		}
		
		for(int i=0;i<score.length;i++) {
			if(newScore[i]!=0) answer++;
		}
		System.out.println(Arrays.toString(newScore));
		return answer;
	}
}
