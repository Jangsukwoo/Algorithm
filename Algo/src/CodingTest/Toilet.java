package CodingTest;

import java.util.Scanner;

public class Toilet {
	static int N;
	static int[] toilet = new int[151];
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	int startTime=0;
    	int endTime=0;
    	for(int i=0;i<N;i++) {
    		startTime = sc.nextInt();
    		endTime = sc.nextInt();
    		for(int time=startTime;time<endTime;time++) toilet[time]+=1;
    	}
    	
    	int Max=0;
    	for(int i=0;i<151;i++) {
    		if(Max<=toilet[i]) Max = toilet[i];
    	}
    	System.out.println(Max);
    }
}