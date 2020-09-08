package 탐색;

import java.util.Arrays;

public class 입국심사 {
	/*
	 * 이진탐색
	 */
	public static void main(String[] args) {
		long answer = solution(1000000000,new int[] {1,1000000000});
		System.out.println(answer);
	}
    public static long solution(int n, int[] times) {
        Arrays.sort(times); //오름차순 정렬
        long startTime = 1;
        long endTime = Integer.toUnsignedLong(times[times.length-1])*Integer.toUnsignedLong(n);
        while(startTime<=endTime){
        	long mid = (startTime+endTime)/2;
        	long peopleCount = getPeopleCount(times,mid);
        	if(peopleCount>=n){//심사해야하는 사람들 보다 많거나 같으면 계속 진행  
        		endTime = mid-1;
        	}else{//심사해야하는 사람들 보다 적으면 
        		startTime = mid+1;
        	}
        }
        return startTime;
    }
	private static long getPeopleCount(int[] times, long mid) {
		long peopleCount = 0;
		for(int time : times) peopleCount+=(mid/time);
		return peopleCount;
	}
}
