package 순열조합;
import java.util.Arrays;
/*
 * r이 3개나 4개정도로 작을 땐
 * for(int i=0...)
 * for(int j=i+1....)
 * for(int k=j+1....)로 풀면 간단. 
 */
import java.util.Scanner;
public class nCr{
	public static int[] data;
	public static int[] pick;
	public static int N;
	public static int M;
	static int nCrCount;
	public static void nCrDFS(int idx, int depth) {
		if(depth==M) {
			nCrCount++;
			System.out.println(Arrays.toString(pick));
			return;
		}
		for(int i=idx; i<N; i++) {
			pick[depth] = data[i];
			nCrDFS(i+1, depth+1);
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //데이터 사이즈
		M = sc.nextInt(); //보고싶은 케이스의 사이즈
		data = new int[N];
		pick = new int[M];
		for(int i=0;i<N;i++) data[i] = i+1;
		nCrDFS(0, 0);
		System.out.println("N:"+N+" "+"M:"+M+" "+"nCr의 결과 :" + nCrCount);
	}
}