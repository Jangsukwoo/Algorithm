package CodingTest;

public class twoPointer {
	static int startPointer;
	static int endPointer = 1;
	static int MaxLength=1;
	static int startIndex;
	public static void main(String[] args) {
//		int[] A = new int[]{2,2,2,2,1,2,-1,2,1,3,4};
//		int[] B = new int[] {30,20,10}; 
//		int[] C = new int[] {10,20,30,40,10,2,3};
//		soulution(A);
//		System.out.println(startIndex);
	}
	private static void soulution(int[] A) {
		int size = A.length;
		int length=1;
		while(endPointer<size) {
			if(A[endPointer]>A[endPointer-1]) {
				endPointer++;
				length++;
			}
			else { //바뀌는 시점 
				MaxLength = Math.max(length,MaxLength);
				if(length==MaxLength) {
					startIndex = startPointer;
				}
				startPointer = endPointer;
				endPointer++;
				length=1;
			}
			if(endPointer==size-1) {
				MaxLength = Math.max(length,MaxLength);
				if(length==MaxLength) {
					startIndex = startPointer;
				}
			}
		}
	
	}
}
