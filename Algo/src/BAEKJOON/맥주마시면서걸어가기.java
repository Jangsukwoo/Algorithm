package BAEKJOON;

import java.util.Scanner;
/*
 * 두 좌표 사이의 거리 =(x2-x1)+(y2-y1) (맨해튼거리)
 * 맥주 1캔이동거리 = 50M
 * 맥주박스에 20캔있으니
 * 장소와 장소사이의 거리가 20x50M = 1000M가 넘어가면 서로 도달할 수 없음.
 * 
 *  문제에서 묻는 것은
 *  집에서 최종 목적지인 페스티벌까지 편의점을 거쳐서 도달할 수 있는가?를 묻는것이므로
 *  플로이드 와샬로 풀 수 있겠다 생각함.
 *  
 */
public class 맥주마시면서걸어가기 {
	static int[][] adjMatrix;
	static int INF = Integer.MAX_VALUE;
	static int convenienceStore;
	static int placeSize;
	static Object[] nodes; //클래스 선언하기 귀찮고 int[]도 어차피 객체니까 이렇게 선언해본다.
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++){
			convenienceStore = sc.nextInt();//편의점 개수
			//상근이 집+편의점개수+페스티벌 목적지 = 1+convenienceStore+1
			//따라서
			placeSize = convenienceStore+2;
			adjMatrix = new int[placeSize][placeSize];
			//인접행렬이고 인덱스 0은 상근이집, 인덱스 N-1은 페스티벌
			nodes = new Object[placeSize];
			for(int i = 0;i<placeSize;i++){
				int x = sc.nextInt();
				int y = sc.nextInt();
				nodes[i] = new int[]{x,y}; 
			}
			//인접행렬 만들기
			for(int row=0;row<placeSize;row++){
				for(int col=0;col<placeSize;col++){
					if(row==col) adjMatrix[row][col]=0;
					else {
						int[] nodeA = (int[]) nodes[row];
						int[] nodeB = (int[]) nodes[col];
						int dist = Math.abs(nodeA[0]-nodeB[0])+Math.abs(nodeA[1]-nodeB[1]);//맨해튼 거리
						if(dist>1000){
							adjMatrix[row][col] = INF;
							continue;
						}
						else adjMatrix[row][col] = dist;
					}
				}
			}
			//플로이드 와샬
			for(int i=0;i<placeSize;i++) {
				for(int j=0;j<placeSize;j++) {
					for(int k=0;k<placeSize;k++){
						if(adjMatrix[i][k]!=INF && adjMatrix[k][j]!=INF && i!=j) {
							adjMatrix[i][j] = Math.min(adjMatrix[i][j],adjMatrix[i][k]+adjMatrix[k][j]);
						}
					}
				}
			}
			if(adjMatrix[0][placeSize-1]==INF) sb.append("sad"+"\n");
			else sb.append("happy"+"\n");
		}
		System.out.println(sb.toString());
	}
	private static void view() {
		for(int row=0;row<placeSize;row++) {
			for(int col=0;col<placeSize;col++) {
				System.out.print(adjMatrix[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
