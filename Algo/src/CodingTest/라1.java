package CodingTest;

public class 라1 {
	static int[] numCount = new int[1000002]; 
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) {
	}
	public int solution(int[][] boxes) {
		int size = boxes.length; 
		int answer = size;
		for(int i = 0; i<size; i++) {
			numCount[boxes[i][0]]++; 
			numCount[boxes[i][1]]++;
			if(boxes[i][0]>max) max = boxes[i][0]; 
			if(boxes[i][1]>max) max = boxes[i][1];
		}
		for(int i = 1; i<=max; i++) { 
			if(numCount[i]>=2) {
				answer -= (numCount[i]/2); 
			}
		}
		return answer;
	}
}
