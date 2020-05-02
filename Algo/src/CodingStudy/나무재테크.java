package CodingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 10:50~
 * 봄 : 나무가 자신의 나이만큼 양분 먹음. 나이 1증가
 * 하나의 칸에 여러개의 나무가 있다면
 * 나이가 어린 나무부터 양분 먹기.
 * 만약 양분을 먹을 수 없다면 즉시 죽는다.
 * 
 * 여름 : 죽은 나무가 양분으로 변함. 죽은 나무의 
 * 나이를 2로 나눈 값이 땅에 추가 
 * 
 * 가을 : 나무 번식
 * 나이가 5의 배수인 나무는 인접한 8칸으로 나이 1인 나무로 번식
 * 
 * 겨울 : 모든 땅에 양분 추가
 * 
 * 한칸에 여러개가 있을 수 있으니 리스트를 쓰기로함.
 * 
 * 살아있는 나무의 개수 ?
 * 
 * 처음 시작할 때 양분이 모두 5인점을 놓치고 풀다가 조금 오래걸림 
 */
public class 나무재테크 {
	static int[][] addNutreint;
	static int[][] nutrient;
	static ArrayList<Tree>[][] treeList;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,K;
	static int answer;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static class Tree implements Comparable<Tree>{
		int age;
		boolean death;
		public Tree(int age, boolean death) {
			this.age = age;
			this.death = death;
		}
		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age,o.age);
		}
	}
	public static void main(String[] args) throws IOException {
		setData();
		simulation();
		getAnswer();
		System.out.println(answer);
	}
	private static void getAnswer() {
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++) {
				answer+=treeList[row][col].size();
			}
		}
	}
	private static void simulation() {
		int year=1;
		while(year<=K){//K년이 될 때 까지
			spring();
			summer();
			autumn();
			winter();
			year++;
		}
	}
	private static void winter() {
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++) {
				nutrient[row][col]+=addNutreint[row][col];
			}
		}
	}
	private static void autumn() {//나이가 5의 배수인 나무들이 번식
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++) {
				for(int i=0;i<treeList[row][col].size();i++){
					if(treeList[row][col].get(i).age%5==0){//나이가 5의 배수이면
						for(int dir=0;dir<8;dir++) {
							int nr = row+dr[dir];
							int nc = col+dc[dir];
							if(rangeCheck(nr,nc)) treeList[nr][nc].add(new Tree(1,false));
						}
					}
				}
			}
		}
	}
	private static boolean rangeCheck(int nr, int nc) {
		if(nr>=1 && nr<=N && nc>=1 && nc<=N) return true;
		return false;
	}
	private static void summer(){//죽은 나무 양분화
		for(int row=1;row<=N;row++) {
			for(int col=1;col<=N;col++) {
				for(int i=treeList[row][col].size()-1;i>=0;i--){
					if(treeList[row][col].get(i).death){//죽은 나무면
						nutrient[row][col] += treeList[row][col].get(i).age/2; //양분화
						treeList[row][col].remove(i);//나무 삭제
					}
				}
			}
		}
	}
	private static void spring(){ 
		/* 
		 * 봄 - 양분 먹기.
		 */
		for(int row=1;row<=N;row++){
			for(int col=1;col<=N;col++){
				//나무가 1개 이상이면 현재 땅 나무리스트 어린순 정렬
				if(treeList[row][col].size()>1) Collections.sort(treeList[row][col]);
				for(int i=0;i<treeList[row][col].size();i++){//나무 하나씩
					if(nutrient[row][col]>=treeList[row][col].get(i).age){//나이 이상 양분이 있으면
						nutrient[row][col]-=treeList[row][col].get(i).age;
						treeList[row][col].get(i).age+=1;//나이 증가
					}else {//나이 만큼 양분이 없으면 죽음
						treeList[row][col].get(i).death=true;
					}
				}
			}
		}
	}
	private static void setData() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nutrient = new int[N+1][N+1];
		addNutreint = new int[N+1][N+1];
		treeList = new ArrayList[N+1][N+1];
		for(int row=1;row<=N;row++)
			for(int col=1;col<=N;col++) treeList[row][col] = new ArrayList<Tree>();
		for(int row=1;row<=N;row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=1;col<=N;col++) {
				addNutreint[row][col] = Integer.parseInt(st.nextToken());
				nutrient[row][col]=5;
			}
		}
		for(int i=0,row,col,age;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			age = Integer.parseInt(st.nextToken());
			treeList[row][col].add(new Tree(age, false));
		}
	}
}
