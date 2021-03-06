package 알고리즘;

import java.util.Scanner;

/*
 * N : 컴퓨터수, 최대 100대
 * M : 총 연결관계
 * 다음 M개의 줄은
 * 연결관계 정보
 * 1번 컴퓨터가 웜바이러스 걸렸을 때 
 * 1번 컴퓨터를 통해 웜바이러스에 걸리는
 * 컴퓨터의 수를 출력
 * 
 * ->플로이드 와샬
 */
public class 바이러스_플로이드와샬 {
	static int N,M;
	static int INF = Integer.MAX_VALUE/10;
	static int[][] network; //adjoinMatrix
	static int result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		network = new int[N+1][N+1];
		for(int row=1;row<=N;row++)
			for(int col=1;col<=N;col++) network[row][col] = INF;
		for(int i=0;i<M;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			network[a][b] = 1;
			network[b][a] = 1;
		}
		for(int k=1;k<=N;k++){//거쳐가는 컴퓨터 번호
			for(int row=1;row<=N;row++) {
				for(int col=1;col<=N;col++){
					if(row!=col) {
						network[row][col] = Math.min(network[row][col],(network[row][k]+network[k][col]));
					}
				}
			}
		}
		for(int num=2;num<=N;num++) {
			if(network[1][num]!=INF) result++;
		}
		System.out.println(result);
	}
}
