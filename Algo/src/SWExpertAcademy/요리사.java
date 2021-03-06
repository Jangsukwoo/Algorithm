package SWExpertAcademy;

import java.util.Scanner;
/*
 * 두 손님의 식성에 최대한 비슷한 음식 만들기
 * 식재료 N개, N은 짝수
 * 식재료 N/2를 해서 두개의 요리를 만들려 함.
 * 음식은 A,B
 * 맛의 차이가 최소가 되도록 재료를 배분해야함
 * 식재료 i와 j에 대해서 시너지 Sij 발생.
 * Sij 테이블이 주어짐.
 * 두 음식간의 맛의 차이가 최소가 되는 경우를 찾고 최소값을 정답으로 출력
 */
public class 요리사 {
	static int[][] synergyTable;
	static int N;
	static int[] dataA;
	static int[] dataB;
	static int[] material;
	static int foodA;
	static int foodB;
	static boolean[] visit;
	static int r;
	static int min;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int testcase=1;testcase<=T;testcase++){
			N = sc.nextInt();
			r = N/2;
			min = Integer.MAX_VALUE;
			dataA = new int[r];
			dataB = new int[r];
			visit = new boolean[N+1];
			synergyTable = new int[N+1][N+1];
			material = new int[2];
			for(int i=1;i<=N;i++)
				for(int j=1;j<=N;j++) synergyTable[i][j] = sc.nextInt();
			//입력 끝
			
			nCr(0,0);
			
			System.out.println("#"+testcase+" "+min);
		}
	}
	private static void nCr(int idx,int cnt){
		if(cnt==r){
			setDataB();//A데이터가 결정되었으므로 B데이터를 세팅한다.
			compareFood();//foodA오 foodB를 구한다.
			return;
		}
		for(int i=idx;i<N;i++){
			if(i>0 && cnt==0) return;//이 문제 특성상 반만 보면 된다. 
			//1,2,3을 뽑았으면 나머지 4,5,6은 안봐도 되기 떄문.
			//1,5,6을 하면 나머지는 2,3,4로 알아서 결정된다.
			//1이 속하지않은 데이터 2, 3, 4라고 치면
			//어차피 1이 속했을 떄 1, 5, 6에서 2,3,4가 결정되었기 때문에 더 볼 필요가없다.
			dataA[cnt] = i+1;
			visit[i+1] = true;//nCr에서 방문한 데이터 체크. dataA
			nCr(i+1,cnt+1);
			visit[i+1] = false;
		}
	}
	private static void compareFood() {
		foodA = 0; //초기화
		foodB = 0;
		
		//결정된 식재료가 3개면 이중에 2개에 대한 모든 조합을 봐야하므로
		//3C2를 뽑아서 값을 본다.
	
		rC2ForFoodA(0,0);//A데이터 (음식A)에 대한 맛값
		rC2ForFoodB(0,0);//B데이터 (음식B)에 대한 맛값
		
		int diff = Math.abs(foodA-foodB); //차이값
		
		min = Math.min(diff,min); //값 업데이트
	}
	private static void rC2ForFoodA(int idx, int cnt) {
		if(cnt==2){
			foodA+=synergyTable[material[0]][material[1]];
			foodA+=synergyTable[material[1]][material[0]];
			return;
		}
		for(int i=idx;i<r;i++){
			material[cnt] = dataA[i];
			rC2ForFoodA(i+1,cnt+1);
		}
	}
	private static void rC2ForFoodB(int idx, int cnt) {
		if(cnt==2){
			foodB+=synergyTable[material[0]][material[1]];
			foodB+=synergyTable[material[1]][material[0]];
			return;
		}
		for(int i=idx;i<r;i++){
			material[cnt] = dataB[i];
			rC2ForFoodB(i+1,cnt+1);
		}
	}
	private static void setDataB(){
		int cnt=0;
		for(int i=1;i<=N;i++) {
			if(visit[i]==false) {
				dataB[cnt]=i;
				cnt++;
			}
		}
	}
}
