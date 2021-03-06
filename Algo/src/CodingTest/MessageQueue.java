package CodingTest;
/*
 * line
 */
import java.util.Scanner;

public class MessageQueue {
	static int MessageSize;
	static int ConsumerSize;
	static int[] Message;
	static int[] Consumer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MessageSize = sc.nextInt();
		ConsumerSize = sc.nextInt();
		Message = new int[MessageSize];
		Consumer = new int[ConsumerSize];
		
		for(int i=0;i<MessageSize;i++) {
			Message[i] = sc.nextInt();
		}
		int min;
		int conNum;
		for(int m=0; m<MessageSize;m++){
			min = Integer.MAX_VALUE;
			conNum=0;
			for(int c=0;c<ConsumerSize;c++) {//최소값,인덱스 찾기
				if(Consumer[c]<=min){
					min = Consumer[c];
					conNum=c;
				}
			}
			Consumer[conNum] += Message[m];
		}
		int max=0;
		for(int c=0;c<ConsumerSize;c++) {//최소값,인덱스 찾기
			if(Consumer[c]>=max){
				max = Consumer[c];
			}
		}
		System.out.println(max);
	}
}
