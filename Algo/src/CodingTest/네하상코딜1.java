package CodingTest;

import java.math.BigInteger;

public class 네하상코딜1 {
	public static void main(String[] args) {
		solution(3,7);
	}
	public static int solution(int A, int B) {
		/*
		 * A*B 최대 천경
		 * BigInteger로 처리
		 */
		BigInteger A_BigInteger = BigInteger.valueOf(A);
		BigInteger B_BigInteger = BigInteger.valueOf(B);
		BigInteger multiValue_BigInteger = A_BigInteger.multiply(B_BigInteger);
		int answer = multiValue_BigInteger.bitCount();
		return answer;
	}
}
