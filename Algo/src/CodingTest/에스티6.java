package CodingTest;

public class 에스티6 {
	public static void main(String[] args) {
		System.out.println(solution(1));
		System.out.println(solution(-1));
		System.out.println(solution(-3));
		for(int i=-70;i<=70;i++) System.out.println(solution(i));
	}
    public static String solution(int n) {
        String answer = "0";
        long[] fibonacciArray = new long[141];
        
        fibonacciArray[70] = 0;
        fibonacciArray[71] = 1;

        //2~70
        for(int num = 72;num<=140;num++) {
        	long nextValue = fibonacciArray[num-1] + fibonacciArray[num-2];
        	fibonacciArray[num] = nextValue;
        }
        //-1~-70
        for(int num=69;num>=0;num--) {
        	long nextValue = fibonacciArray[num+2] - fibonacciArray[num+1];
        	fibonacciArray[num] = nextValue;
        }
        answer = Long.toString(fibonacciArray[n+70]);
        return answer;
    }
}
