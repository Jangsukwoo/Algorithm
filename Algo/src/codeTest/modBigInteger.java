package codeTest;

import java.math.BigInteger;

public class modBigInteger {
	public static void main(String[] args) {
		/*
		 * K,P는 10^8
		 * N은 10^16
		 */
		solution("100000000","100000000","10000000000000000");
	}

	private static void solution(String k, String p, String n) {
		
		BigInteger K = new BigInteger(k);
		BigInteger P = new BigInteger(p);
		BigInteger N = new BigInteger(n);
		BigInteger ten = new BigInteger("10");
		BigInteger mod = new BigInteger("1000000007");
		BigInteger entireTime = N.multiply(ten);
		BigInteger finalMultiplyValue = P.modPow(entireTime, mod);
		BigInteger finalValue = K.multiply(finalMultiplyValue);
		BigInteger answer = finalValue.mod(mod);
		System.out.println(answer);
		
	}
}
