package AlgoSpot;

import java.util.Scanner;

/*
버튼/시계번호
0	0, 1, 2
1	3, 7, 9, 11
2	4, 10, 14, 15
3	0, 4, 5, 6, 7
4	6, 7, 8, 10, 12
5	0, 2, 14, 15
6	3, 14, 15
7	4, 5, 7, 14, 15
8	1, 2, 3, 4, 5
9	3, 4, 5, 9, 13 
 */
public class SynchronizingClocks {
	static int C;
	static int[] clock = new int[16];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		for(int testcase=1; testcase<=C; testcase++){
			for(int i=0; i<16;i++) clock[i] = sc.nextInt();
			
		}
	}
}
