package CodingTest;

import java.util.ArrayList;

public class Keypad{
	static ArrayList<int[]> keyPosition = new ArrayList<int[]>();
	static int[] right;
	static int[] left;
	public static void main(String[] args) {
		setKeypad();
	}
	private static void setKeypad() {
		keyPosition.add(new int[] {4,2});
		keyPosition.add(new int[] {1,1});
		keyPosition.add(new int[] {1,2});
		keyPosition.add(new int[] {1,3});
		keyPosition.add(new int[] {2,1});
		keyPosition.add(new int[] {2,2});
		keyPosition.add(new int[] {2,3});
		keyPosition.add(new int[] {3,1});
		keyPosition.add(new int[] {3,2});
		keyPosition.add(new int[] {3,3});
		right = new int[] {4,1};
		left = new int[] {4,3};
	}
	public String solution(int[] numbers, String hand) {
	        String answer = "";
	        for(int i=0;i<numbers.length;i++) {
	        	int[] currentNumberPosition =  keyPosition.get(numbers[i]);
	        	switch (numbers[i]) {
				case 1:case 4: case 7:
					answer+="L";
					left[0] = currentNumberPosition[0];
					left[1] = currentNumberPosition[1];
					break; 
				case 3:case 6: case 9:
					answer+="R";
				    right[0] = currentNumberPosition[0];
					right[1] = currentNumberPosition[1];
					break;
				case 2: case 5: case 8: case 0:
					int leftDist = Math.abs(left[0]-currentNumberPosition[0])+Math.abs(left[1]-currentNumberPosition[1]);
					int rightDist = Math.abs(right[0]-currentNumberPosition[0])+Math.abs(right[1]-currentNumberPosition[1]);
					if(leftDist==rightDist) {//거리가 같고
						if(hand.equals("right")){//오른손잡이면
							answer+="R";
						    right[0] = currentNumberPosition[0];
							right[1] = currentNumberPosition[1];
						}else {//왼손잡이면
							answer+="L";
							left[0] = currentNumberPosition[0];
							left[1] = currentNumberPosition[1];
						}
					}else if(leftDist>rightDist) {
						answer+="R";
					    right[0] = currentNumberPosition[0];
						right[1] = currentNumberPosition[1];
					}else {
						answer+="L";
						left[0] = currentNumberPosition[0];
						left[1] = currentNumberPosition[1];
					}
					break;
				}
	        }
	        return answer;
	}
}
