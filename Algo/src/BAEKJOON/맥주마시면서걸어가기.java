package BAEKJOON;

import java.util.Scanner;
/*
 * 두 좌표 사이의 거리 =(x2-x1)+(y2-y1) (맨해튼거리)
 * 맥주 1캔이동거리 = 50M
 * 맥주박스에 20캔있으니
 * 장소와 장소사이의 거리가 20x50M = 1000M가 넘어가면 도달할 수 없음.
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
	static Object[] nodes; //클래스 선언하기 귀찮고 int[]도 어차피 객체니까 최상위 슈퍼타입으로 선언해본다.
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
			
			//인접행렬이고 인덱스 0은 상근이집, 인덱스 placeSize-1은 페스티벌
			nodes = new Object[placeSize];
			for(int i = 0;i<placeSize;i++){
				int x = sc.nextInt();
				int y = sc.nextInt();
				nodes[i] = new int[]{x,y}; //Object -> int[]
			}
			
			//인접행렬 만들기. 장소 A와 B의 관계 행렬
			for(int A=0;A<placeSize;A++){
				for(int B=0;B<placeSize;B++){
					if(A==B) adjMatrix[A][B]=0;
					else {
						int[] nodeA = (int[]) nodes[A]; //Object -> int[]로 다운 캐스팅
						int[] nodeB = (int[]) nodes[B];
						int dist = Math.abs(nodeA[0]-nodeB[0])+Math.abs(nodeA[1]-nodeB[1]);//맨해튼 거리
						
						if(dist>1000){//두 노드의 사이 거리가 1000이 넘으면 맥주20캔으로도 불가능한 거리
							adjMatrix[A][B] = INF;
							continue;
						}
						else adjMatrix[A][B] = 1; //아니면 갈수 있음을 뜻하는 1로 적는다. 이 문제에서 거리값은 중요하지 않기도하고.
					}
				}
			}
			//플로이드 와샬
			//와샬 쓸 때 가장 바깥쪽 반복문은 거쳐가는 노드의 번호 k이다. 순서 중요!
			for(int k=0;k<placeSize;k++) {
				for(int i=0;i<placeSize;i++) {
					for(int j=0;j<placeSize;j++){
						if(adjMatrix[i][k]!=INF && adjMatrix[k][j]!=INF && i!=j){//k를 거치는게 INF가 아니면 
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
}
