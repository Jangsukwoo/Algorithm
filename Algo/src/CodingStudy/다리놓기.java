package CodingStudy;

import java.math.BigInteger;
import java.util.Scanner;

public class 다리놓기{
	static long T;
	static long N,M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++) {
			N = sc.nextLong();
			M = sc.nextLong();
			BigInteger result = new BigInteger("1");
			//mCn을 구하면 된다.
			for(int i=0;i<N;i++) {
				result = result.multiply(BigInteger.valueOf(M-i));
			}
			for(int i=1;i<=N;i++) {
				result = result.divide(BigInteger.valueOf(i));
			}
			System.out.println(result);
		}
		
	}
}
