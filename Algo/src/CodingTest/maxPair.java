package CodingTest;
/*
 * est
 */
import java.util.ArrayList;
import java.util.Collections;

public class maxPair {
	static ArrayList<Integer> temp;
	public static void main(String[] args) {
		int answer = solution(new int[]{51,71,17,42});
		System.out.println(answer);
	}
    public static int solution(int[] A){
    	ArrayList<Integer>[] sumList;
    	ArrayList<Integer> ans = new ArrayList<Integer>();
    	sumList = new ArrayList[82];
    	for(int i=0;i<82;i++) sumList[i] = new ArrayList<Integer>();
    	for(int i=0;i<A.length;i++) {
    		String stringNumber = Integer.toString(A[i]);
    		int sum=0;
    		for(int j=0;j<stringNumber.length();j++) {
    			sum+=Character.getNumericValue((stringNumber.charAt(j)));
    		}
    		sumList[sum].add(A[i]);
    	}
    	for(int i=0;i<82;i++){
    		if(sumList[i].size()!=0){
    			if(sumList[i].size()==2){
    				int sum = 0;
    				for(int j=0;j<sumList[i].size();j++){
    					sum+=sumList[i].get(j);
    				}
    				ans.add(sum);
    			}
    			else if(sumList[i].size()>2){
    				 temp = new ArrayList<Integer>();
    				 for(int j=0;j<2;j++) {
    					 temp.add(1);
    				 }
    				 for(int k=0;k<(sumList[i].size()-2);k++) {
    					 temp.add(0);
    				 }
    				 Collections.sort(temp);
    				 dfs(0,0);
    			}
    		}
    	}
    	return 0;
    }
	private static void dfs(int idx, int depth) {
		if(depth==2) {
			
			return;
		}
		for(int i=idx;i<temp.size();i++) {
			
		}
	}
}
