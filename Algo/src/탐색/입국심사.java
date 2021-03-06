package 탐색;

import java.util.Arrays;

public class 입국심사 {
	/*
	 * 이진탐색
	 */
	static int LIMIT = 1000000000;
	static int N;
	public static void main(String[] args){
		int[] limitArray = new int[100000];
		
		Arrays.fill(limitArray,LIMIT);
		
		long startTime = System.currentTimeMillis();
		
		long answer = solution(LIMIT,limitArray);
		
		long endTime =  System.currentTimeMillis();
		
		System.out.println("걸린 long 값 처리시간"+(endTime-startTime));
		
		double resultTime = (double)(endTime-startTime)/(double)1000;
		System.out.println("걸린 처리시간"+resultTime);
		System.out.println(answer);
	}
    public static long solution(int n, int[] times) {
        Arrays.sort(times); //오름차순 정렬
        long startTime = 1;
        long endTime = Integer.toUnsignedLong(times[times.length-1])*Integer.toUnsignedLong(n);
        int binaryCount = 0;
        while(startTime<=endTime){
        	binaryCount++;
        	long mid = (startTime+endTime)/2;
        	long peopleCount = getPeopleCount(times,mid);
        	if(peopleCount>=n){//심사해야하는 사람들 보다 많거나 같으면 계속 진행  
        		endTime = mid-1;
        	}else{//심사해야하는 사람들 보다 적으면 
        		startTime = mid+1;
        	}
        }
        
        System.out.println("이진탐색 처리 횟수"+binaryCount);
        
        return startTime;
    }
	private static long getPeopleCount(int[] times, long mid) {
		long peopleCount = 0;
		int n = 0;
		for(int time : times) {
			peopleCount+=(mid/time);
			n++;
		}
		System.out.println(n);
		return peopleCount;
	}
}
