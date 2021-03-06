package CodingTest;

import java.util.Arrays;

public class BlackAndWhiteCap {

	public static void main(String[] args) {
		solution(new int[] {1,1,2,1,1});
	}
	public static int[] solution(int[] black_caps) {
		int[] result = new int[black_caps.length];
		//흰색은 2, 검정은 1
		if(black_caps[0]==0) {
			result[1] = 2;
		}else {
			result[1] = 1;
		}
		if(black_caps[black_caps.length-1]==0) {
			result[black_caps.length-2] = 2;
		}else {
			result[black_caps.length-2] = 1;
		}
		markingResultArray(black_caps,result);
		
		for(int i=1;i<=(black_caps.length-2);i++) {
			if(black_caps[i]==1){//1을 외친 경우
				if(result[i-1]==2 && result[i+1]==0){
					result[i+1] = 1;
				}else if(result[i-1]==0 && result[i+1]==2) {
					result[i-1] = 1;					
				}
			}
		}
		for(int i=1;i<=(black_caps.length-2);i++) {
			if(black_caps[i]==1){//1을 외친 경우
				if(result[i-1]==1 && result[i+1]==0){
					result[i+1] = 2;
				}else if(result[i-1]==0 && result[i+1]==1) {
					result[i-1] = 2;					
				}
			}
		}
		
		System.out.println(Arrays.toString(result));
		return result;
	}
	private static void markingResultArray(int[] black_caps,int[] result) {
		for(int i=1;i<=(black_caps.length-2);i++) {
			if(black_caps[i]==2) {
				result[i-1] = 1;
				result[i+1] = 1;
			}
		}
		for(int i=1;i<=(black_caps.length-2);i++) {
			if(black_caps[i]==0) {
				result[i-1] = 2;
				result[i+1] = 2;
			}
		}		
	}
}
