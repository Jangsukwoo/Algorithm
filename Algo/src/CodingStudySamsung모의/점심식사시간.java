package CodingStudySamsung모의;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 격자 맵에 사람 1와 입구 2이상이 있음
 * 사람은 1 계단은 2이상 
 * 1
 * 입구까지 사람이 걸리는 시간은 
 * 절대값 PR-SR + PC-SC
 * 
 * 2
 * 계단을 내려가는 시간은
 * 입구에 도착후 계단을 완전히 내려갈 때 까지의 시간
 * 입구에 도착 후 1분 후 아래칸으로 내려갈 수 있음
 * 계단 위에는 최대 3명까지 올라간다.
 * 이미 계단을 3명이 내려가고 있는 경우 그 중 한명이
 * 계단을 완전히 내려갈 떄 까지 입구에서 대기 
 * 계단의 길이 K
 * 
 * 방사이즈 N은 4이상 10이하
 * 사람의 수는 1이상 10이하
 * 계단의 입구는 반드시 2개
 * 계단의 길이 2<=K<=10
 * 
 * 
 * 계단의 길이와 상관없이 각 계단은 최대 3명 
 * 
 * 모든 사람이 계단을 내려가 이동을 완료하는 최소 시간은?
 * 
 * 6명의 사람이 있다면
 * 1번계단선택, 2번계단 선택하는 모든 경우의 수를 일단 구한다.
 * 
1
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0
 */

public class 점심식사시간 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int min;
	static int[][] room;
	static int[] pickStair;
	static int personSize;
	static int[] person;
	static Stair[] stairs = new Stair[3];
	static int[][] arrivalTable;
	static ArrayList<int[]> persons;
	static int r=0;
	static class Stair{
		int row;
		int col;
		int length;
		public Stair(int r, int c, int l) {
			row = r;
			col = c;
			length = l;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			setData();
			pickStair();
		}
	}
	private static void pickStair() {
		for(int pick=0;pick<=N;pick++){
			r = pick;
			System.out.println("R값이"+r);
			nCr(0,0);
		}
	}
	private static void nCr(int idx, int depth){
		if(r==depth){
			System.out.println(Arrays.toString(person));
			return;
		}
		for(int i=idx;i<personSize;i++){
			person[i] = 2;
			nCr(i+1,depth+1);
			person[i] = 1;
		}
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		room = new int[N][N];
		pickStair = new int[N];
		persons = new ArrayList<int[]>();
		min = 987654321;
		persons.add(new int[] {-1,-1});//인덱스 맞추기 위해 
		int stairIdx=1;
		for(int row=0;row<N;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++) {
				room[row][col] = Integer.parseInt(st.nextToken());
				if(room[row][col]==1) {
					persons.add(new int[]{row,col});
					personSize++;
				}
				if(room[row][col]>=2) stairs[stairIdx++] = new Stair(row, col, room[row][col]);//계단정보
			}
		}
		person = new int[personSize];
		arrivalTable = new int[personSize+1][3];
		for(int i=1;i<=personSize;i++){
			for(int j=1;j<=2;j++){
				int[] personRC =  persons.get(i);
				int dist = Math.abs(personRC[0]-stairs[j].row) + Math.abs(personRC[1]-stairs[j].col);
				arrivalTable[i][j] = dist+stairs[j].length;
			}
		}
		for(int i=1;i<=personSize;i++) {
			for(int j=1;j<=2;j++) {
				System.out.print(arrivalTable[i][j]+" ");
			}
			System.out.println();
		}
		Arrays.fill(person,1);
	}
}
