package CodingTest;

public class 네하상코딜3 {
	public static void main(String[] args) {
		//solution(new int[] {2,6,8,5});
		solution(new int[] {1,1});
		int[] block1 = new int[200001];
		int[] block2 = new int[200001];
		int[] block3 = new int[200001];
		for(int i=0;i<=200000;i++) {
			block1[i]=200001-i;
		}
		for(int i=0;i<=200000;i++) {
			block2[i]=i+1;
		}
		for(int i=0;i<=100000;i++) {
			block3[i]=200001-i;
		}
		for(int i=1000001;i<=200000;i++) {
			block3[i]=i+1;
		}
		System.out.println(solution(new int[] {1}));
		System.out.println(solution(new int[] {5,4,3,2,1}));
		System.out.println(solution(block1));
		System.out.println(solution(block2));
		System.out.println(solution(block3));
	}
    public static int solution(int[] blocks) {
    	
    	/*
    	 * 투포인터
    	 */
    	
    	int leftFrog = 0;
    	int rightFrog = 0;
    	int endIdx = blocks.length-1;
    	int maxDistance = 0;
    	
    	while(true){
    		
    		int rigthJump = rightFrog;
    		int leftJump = leftFrog;
    		int distance = 0;

    		while((rigthJump+1<=endIdx) && (blocks[rigthJump]<=blocks[rigthJump+1])){
    			rigthJump++;
    		}
    		while((leftJump-1>=0) && (blocks[leftJump]<=blocks[leftJump-1])) {
    			leftJump--;
    		}
    		
    		distance = rigthJump-leftJump+1;
    		
    		maxDistance = Math.max(distance,maxDistance);
    		
    		rigthJump++;

    		if(rigthJump>endIdx) break;
    		
    		if(blocks[leftJump]<blocks[leftJump+1]) { //왼쪽 개구리도 옮기기
    			rightFrog = rigthJump;
    			leftFrog = rigthJump;
    		}else {
    			rightFrog = rigthJump;
    			leftFrog = leftJump;
    		}
    	}
    	return maxDistance;
    }
}
