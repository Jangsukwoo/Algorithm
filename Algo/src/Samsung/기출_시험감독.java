package Samsung;

import java.util.Scanner;

public class 기출_시험감독 {
	static int N;//시험장 개수
	static long[] A;//각 시험장 응시자 수
	static long B,C;//총감독관,부감독관
	static long result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new long[N];
		for(int i=0;i<N;i++) A[i] = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		for(int i=0;i<N;i++){
			boolean master = false;
			for(int j=0;j<2;j++) {
				if(master==false) {
					A[i] = A[i]-B;
					result++;
					master = true;
				}else if(master==true && A[i]>0){
					result +=  Math.ceil((double)A[i]/(double)C);
				}				
			}
		}
		System.out.println(result);
	}
}
