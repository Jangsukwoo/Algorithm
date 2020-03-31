package CodingTest;
/*
 * est
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class numberCount {
	public static void main(String[] args) {
		int answer = solution(new int[] {1,2,3,3,3,3});
	}
    public static int solution(int[] A){
    	List<Integer> tempList = new ArrayList<Integer>();
    	for(int i=0;i<A.length;i++) tempList.add(A[i]);
    	Collections.sort(tempList);//Collections.sort는 mergeSort로 시간복잡도 NlogN
    	for(int i=0;i<A.length;i++) A[i] = tempList.get(i);
    	
    	//정렬 끝
    	int max = 0;
    	int cnt=1;
    	for(int i=0;i<(A.length-1);i++){
    		if(A[i]==A[i+1]){
    			cnt++;
    			if(cnt==A[i]) max = Math.max(max,A[i]);
    		}
    	}
    	System.out.println(max);
    	return max;
    }
}
