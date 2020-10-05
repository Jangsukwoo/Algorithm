package SamsungDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 신기한소수 {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] startArray = new int[] {2,3,5,7};
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		for(int s=0;s<startArray.length;s++) {//시작 수 
			dfs(startArray[s],0);
		}
	}
	private static void dfs(int checkNum, int depth) {
		if(depth==(N-1)) {
			System.out.println(checkNum);
			return;
		}
		checkNum*=10;
		for(int i=1;i<=10;i+=2){
			//System.out.println((checkNum+i));
			if(isPrime(checkNum+i)){//소수인가?
				//소수라면
				dfs(checkNum+i,depth+1);
			}
		}
	}
	private static boolean isPrime(int temp) {
		for(int j=3;j<=Math.sqrt(temp);j++){
			if(temp%j==0) return false;
		}
		return true;
	}
}
