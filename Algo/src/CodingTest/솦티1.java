package CodingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class 솦티1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		BigInteger K = new BigInteger(st.nextToken());
		BigInteger P = new BigInteger(st.nextToken());
		BigInteger N = new BigInteger(st.nextToken());
		BigInteger mod = new BigInteger("1000000007");
		BigInteger powValue = P.pow(N.intValue());
		BigInteger virusCount = powValue.multiply(K);
		BigInteger answer = virusCount.mod(mod);
		System.out.println(answer);
	}
}
