package 프로그래머스;

public class 스첵2_2 {
	static boolean[] nonPrimes = new boolean[3001];
	static int[] pick = new int[3];
	static int primeCount = 0;
	public static void main(String[] args) {
	  
	     solution(new int[] {1,2,7,6,4});
	}
    public static int solution(int[] nums) {
        int answer = -1;
        makeEratosthenes();
   
        nCr(0,nums,0);
    
        answer = primeCount;
        return answer;
    }
	private static void nCr(int idx, int[] nums, int depth) {
		if(depth==3) {
			int value = pick[0]+pick[1]+pick[2];
			if(!nonPrimes[value]) primeCount++;
			return;
		}
		for(int i=idx;i<nums.length;i++) {
			pick[depth] = nums[i];
			nCr(i+1,nums,depth+1);
		}
	}
	private static void makeEratosthenes() {
		nonPrimes[0] = true;
		nonPrimes[1] = true;
		for(int i=2;i<3001;i++){
			if(i%2==0 && i>3) continue;
			for(int j=i+i;j<3001;j+=i) {
				nonPrimes[j] = true;
			}
		}
	}
}
