package SamsungDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위쌓기 {
	static int N;
	static int[][] dice;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int max;
	static int[][] upside;
	static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		
		bruteForceDFS();
		
		System.out.println(max);
	}

	private static void bruteForceDFS() {
		/*
		 * 주사위는 총 10000개
		 * 1번 주사위부터 차곡 차곡 쌓음
		 */
		for(int firstDiceUpSide=0;firstDiceUpSide<6;firstDiceUpSide++){
			int maxSide = 0;
			for(int i=0;i<4;i++) maxSide = Math.max(maxSide,dice[0][upside[firstDiceUpSide][i]]);
			dfs(0,firstDiceUpSide,1,maxSide); //6면에 대해서 첫번째 기준을 정하는 for문
		}
	}
	private static void dfs(int idx,int beforeUpside,int stack, int maxSide){
		if(stack==N){//다 쌓았으면
			max = Math.max(maxSide,max);
			return;
		}
		
		for(int currentDiceDownSide=0;currentDiceDownSide<6;currentDiceDownSide++){
			if(dice[idx][beforeUpside]==dice[stack][currentDiceDownSide]){
				//이전 주사위의 하늘 방향 값과 쌓을 주사위의 바닥값이 같은게 발견된다면
				int nextUpside = getNextUpside(currentDiceDownSide);
				int getMaxSide = 0;
				//옆면 중 가장 큰값을가지는 옆면으로맞추기
				for(int i=0;i<4;i++) getMaxSide = Math.max(getMaxSide,dice[stack][upside[nextUpside][i]]);
				dfs(stack,nextUpside,stack+1,maxSide+getMaxSide);
				break;
			}
		}

	}
	private static int getNextUpside(int currentDiceDownSide) {
		int nextUpside = 0;
		switch (currentDiceDownSide) {
		case 0:
			nextUpside = 5;
			break;
		case 1:
			nextUpside = 3;
			break;
		case 2:
			nextUpside = 4;
			break;
		case 3:
			nextUpside = 1;
			break;
		case 4:
			nextUpside = 2;
			break;
		case 5:
			nextUpside = 0;
			break;
		}
		return nextUpside;
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];
		upside = new int[6][4];//윗면이 0일 때부터 5일때 까지 left, front, rihgt, back 저장
		upside[0] = new int[] {1,2,3,4};
		upside[1] = new int[] {5,2,0,4};
		upside[2] = new int[] {1,5,3,0};
		upside[3] = new int[] {2,5,4,0};
		upside[4] = new int[] {3,5,1,0};
		upside[5] = new int[] {1,4,3,2};
		
		//각 면의 하늘방향일 떄 left~back 인덱스 미리 선언 
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<6;j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
