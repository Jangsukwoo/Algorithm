package CodingTest;

public class 느흐느고도1 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {50,62,93}));
		System.out.println(solution(new int[] {5,31,15}));
		System.out.println(solution(new int[] {5,3,15}));
		System.out.println(solution(new int[] {50,50,1}));
	}
    public static int solution(int[] goods){
    	int minCost = Integer.MAX_VALUE;
    	int sum = 0;
    	for(int i=0;i<3; i++) { //하나씩 구매해보기
    		sum+=goods[i];
    		if(goods[i]>=50) {
    			sum-=10;
    		}
    	}
    	minCost = Math.min(minCost,sum);

    	for(int i=0;i<3;i++) { //두개씩 구매해보기
    		sum = 0;
    		for(int j=i+1;j<3;j++) {
    			sum = goods[i]+goods[j];
    			int idx = 0;
    			for(int k=0;k<3;k++) {
    				if(k!=i && k!=j) {
    					idx = k;
    					break;
    				}
    			}
    			if(sum>=50) sum-=10;
    			sum+=goods[idx];
    			if(goods[idx]>=50) sum-=10;
    			minCost = Math.min(minCost,sum);
    		}
    	}
    	sum = 0;
    	for(int i=0;i<3;i++) sum+=goods[i];
    	if(sum>=50) sum-=10;
    	minCost = Math.min(minCost,sum);
    	return minCost;
    }
}
