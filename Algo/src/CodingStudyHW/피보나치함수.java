package CodingStudyHW;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
/*
 * 메모를 세개나 ..
 */
public class 피보나치함수 {
	static int T;
	static int zero,one;
	static String[] memo;
	static int[] numberMemo;
	static class memoInfo{
		int zero;
		int one;
		public memoInfo(int zero, int one) {
			this.zero = zero;
			this.one = one;
		}

	}
	static memoInfo[] memoInfos;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		memo = new String[41];
		memoInfos = new memoInfo[41];
		numberMemo = new int[41];
		for(int testcase=1,n;testcase<=T;testcase++){
			n = sc.nextInt();
			zero=0;
			one=0;
			if(memo[n]==null) {
				fibonacci(n);
				memo[n]=Integer.toString(zero)+" "+Integer.toString(one)+"\n";
				memoInfos[n] = new memoInfo(zero,one);
				bw.write(zero+" "+one+"\n");
			}else bw.write(memo[n]);
		}
//		for(int i=1;i<=40;i++) {
//			if(memoInfos[i]!=null) {
//				System.out.println(i+" "+memoInfos[i].zero+" "+memoInfos[i].one);
//			}
//		}
		bw.flush();
		bw.close();
	}
	public static int fibonacci(int n){
		if (n == 0) {
			zero++;
			return 0;
		} else if (n == 1) {
			one++;
			return 1;
		} else {
			if(memoInfos[n]!=null) {
				zero+=memoInfos[n].zero;
				one+=memoInfos[n].one;
				return numberMemo[n];
			}
			int result = fibonacci(n-1) + fibonacci(n-2);
			numberMemo[n] = result;
			memoInfos[n] = new memoInfo(zero,one);

			return result;
		}
	}
}
