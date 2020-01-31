package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 식재료 궁합 테이블 N by N
 * 식재료를 N/2개씩 나누어 두 개의 요리를 하려고 함 N은 짝수
 * A음식과 B음식 
 * 
 * 모든 재료에 대해서 N/2개 고름 
 * 
 * 두 맛의 차이는 절대값
 */
public class 요리사 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int foodA;
	static int foodB;
	static int[][] synergy;
	static int min = 987654321;
	static int r;
	static boolean[] visit;
	static int[] materialA;
	static int[] materialB;
	static int[] materialPick;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			nCr(1,0);//재료 가르기
			bw.write("#"+testcase+" "+min+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static void nCr(int idx, int depth){
		if(depth==r){
			setMaterialB();
			pickAndCompare();
			return;
		}
		for(int i=idx;i<=N;i++){
			materialA[depth] = i;
			visit[i] = true;
			nCr(i+1,depth+1);
			visit[i] = false;
		}
	}
	private static void pickAndCompare() {
		foodA=0;
		foodB=0;
		pickMaterialA(0,0);
		pickMaterialB(0,0);
		compare();
	}
	private static void compare() {
		int value = Math.abs(foodA-foodB);
		min = Math.min(value,min);
	}

	private static void pickMaterialA(int idx, int depth) {
		if(depth==2){
			foodA+=synergy[materialPick[0]][materialPick[1]];
			foodA+=synergy[materialPick[1]][materialPick[0]];
			return;
		}
		for(int i=idx;i<r;i++){
			materialPick[depth] = materialB[i];
			pickMaterialA(i+1,depth+1);
		}
	}
	private static void pickMaterialB(int idx, int depth) {
		if(depth==2){
			foodB+=synergy[materialPick[0]][materialPick[1]];
			foodB+=synergy[materialPick[1]][materialPick[0]];
			return;
		}
		for(int i=idx;i<r;i++){
			materialPick[depth] = materialA[i];
			pickMaterialB(i+1,depth+1);
		}
	}
	private static void setMaterialB(){
		int idx=0;
		for(int i=1;i<=N;i++){
			if(visit[i]==false) materialB[idx++]=i;
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		synergy = new int[N+1][N+1];
		visit = new boolean[N+1];
		r = N/2;
		materialA = new int[r];
		materialB = new int[r];
		materialPick = new int[2];
		min = 987654321;
		for(int row=1;row<=N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=1;col<=N;col++) {
				synergy[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
